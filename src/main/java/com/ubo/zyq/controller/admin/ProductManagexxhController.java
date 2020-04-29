package com.ubo.zyq.controller.admin;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.ubo.zyq.entity.DataTableResult;
import com.ubo.zyq.entity.LayuiMsg;
import com.ubo.zyq.entity.Product;
import com.ubo.zyq.entity.Response;
import com.ubo.zyq.service.ProductService;
import com.ubo.zyq.util.FtpConnection;
import com.ubo.zyq.util.FtpUtil;
import com.ubo.zyq.util.IDUtils;

/**
 * @author 谢雄辉 2020年4月9日
 */

@Controller
@RequestMapping("/management/product")
public class ProductManagexxhController {

	@Value("${FTP.ADDRESS}")
	private String host;
	// 端口
	@Value("${FTP.PORT}")
	private int port;
	// ftp用户名
	@Value("${FTP.USERNAME}")
	private String userName;
	// ftp用户密码
	@Value("${FTP.PASSWORD}")
	private String passWord;
	// 文件在服务器端保存的主目录
	@Value("${FTP.BASEPATH}")
	private String basePath;
	// 访问图片时的基础url
	@Value("${IMAGE.BASE.URL}")
	private String baseUrl;
	
	@Autowired
	ProductService productService;

	/**
	 * .产品表格数据
	 * 
	 * @param limit
	 * @param currPage
	 * @return
	 */

	@GetMapping(value = "/getProductTable")
	@ResponseBody
	public DataTableResult getProductTable(@RequestParam(required = false, defaultValue = "20") int limit,
			@RequestParam(name = "page", required = false, defaultValue = "0") int currPage) {
		Page<Product> page = productService.findByProduct(PageRequest.of(currPage - 1, limit));
		return new DataTableResult(0, "", page.getTotalElements(), page.getContent());
	}

	/**
	 * .根据产品id查询产品表 并展示数据到编辑页面
	 * 
	 * @param model
	 * @return
	 */
	@GetMapping(value = "/getProductTable/{id}")
	public String editProductInfo(@PathVariable Long id, Model model) {
		Optional<Product> optional = productService.findById(id);
		Product product = optional.get();
		model.addAttribute("product", product);
		return "admin/product-edit";

	}

	/**
	 * .产品下架功能
	 * 
	 * @param pt
	 * @return
	 */

	@PostMapping("/editstatus")
	@ResponseBody
	public Response editstatus(@RequestBody Product pt) {
		Optional<Product> optional = productService.findById(pt.getId());

		if (optional.isPresent()) {
			Product product = optional.get();
			// 状态20 产品下架
			product.setStatus(20);
			productService.save(product);
			return new Response(true, "下架成功", product);
		}
		return new Response(false, "下架失败");
	}

	/**
	 * @author 谢雄辉 2020年4月10日 .产品上架功能
	 * @param pt
	 * @return
	 */
	@PostMapping("/upstatus")
	@ResponseBody
	public Response downstatus(@RequestBody Product pt) {
		Optional<Product> optional = productService.findById(pt.getId());
		if (optional.isPresent()) {
			Product product = optional.get();
			// 状态10 产品上架
			product.setStatus(10);
			productService.save(product);
			return new Response(true, "上架成功", product);
		}
		return new Response(false, "上架失败");
	}

	@PostMapping("/editProduct")
	@ResponseBody
	public Response editProduct(@RequestBody Product pt) {
		Optional<Product> optional = productService.findById(pt.getId());
		if (optional.isPresent()) {
			Product product = optional.get();
			product.setProductImages(pt.getProductImages());
			product.setProductMaterial(pt.getProductMaterial());
			product.setProductName(pt.getProductName());
			product.setProductSeries(pt.getProductSeries());
			product.setProductType(pt.getProductType());
			productService.save(product);
			return new Response(true, "修改成功");
		}

		return new Response(false, "编辑失败");
	}

	/**
	 * .跳转到添加页面
	 * 
	 * @return
	 */
	@GetMapping("/addproduct")
	public String addproduct(Model model) {

		return "admin/product-add";

	}

	@PostMapping(value = "/saveproduct")
	@ResponseBody
	public Response saveproduct(@RequestBody Product product) {

		if (product.getProductImages() == null || product.getProductName() == null || product.getProductSeries() == null
				|| product.getProductMaterial() == null || product.getProductType() == null) {

			new Response(false, "提交的内容不允许为空,请填写完毕后确认提交");
		}
		product.setCreateTime(new Date());
		product.setStatus(10);
		Product pt = productService.save(product);
		return new Response(true, "添加成功");

	}

	/**
	 * 接收图片上传
	 * @param file
	 * @return
	 * @throws IllegalStateException
	 * @throws IOException
	 */
	@PostMapping("/uploadProductImg")
    @ResponseBody
    public LayuiMsg uploadProductImgFirst(@RequestPart MultipartFile file) throws IllegalStateException, IOException {
        /*String realName = "";
        if (file != null) {
            String fileName = file.getOriginalFilename();
            String fileNameExtension = fileName.substring(fileName.lastIndexOf("."));
            realName = UUID.randomUUID().toString() + fileNameExtension;
            File uploadFile = new File(PARAM.PRODUCT_IMG_PATH, realName);
            file.transferTo(uploadFile);
        } else {
            return new LayuiMsg(1, "上传失败，未能接收到图片", null);
        }
        return new LayuiMsg(0, "上传完成", "/getProductImg/" + realName);
    }*/
		try {
            //1、给上传的图片生成新的文件名
            //1.1获取原始文件名
            String oldName = file.getOriginalFilename();
            //1.2使用IDUtils工具类生成新的文件名，新文件名 = newName + 文件后缀
            String newName = IDUtils.genImageName();
            newName = newName + oldName.substring(oldName.lastIndexOf("."));
            //1.3生成文件在服务器端存储的子目录
            
            String filePath = "/Product";
            
            /*String filePath = new DateTime().toString("/yyyy/MM/dd");*/
                        
               
            //3、把图片上传到图片服务器
            //3.1获取上传的io流
            InputStream input = file.getInputStream();
            
            //3.2调用FtpUtil工具类进行上传
            FtpConnection ftpcn = new FtpConnection();
            boolean result = FtpUtil.uploadFile(host, port, userName, passWord, basePath, filePath, newName, input);
            if(result) {
            	//baseUrl + filePath + "/"+ newName
            	return new LayuiMsg(0, "上传完成",baseUrl + filePath + "/"+ newName);
            }else {
            	return new
            			LayuiMsg(1, "上传失败，未能接收到图片", null);
            }
        } catch (IOException e) {
        	return new
        			LayuiMsg(1, "上传失败，未能接收到图片", null);
        }
    }
}
