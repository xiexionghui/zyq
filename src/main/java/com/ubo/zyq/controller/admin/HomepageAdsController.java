/**
 * 谢雄辉
 *2020年4月14日
 */
package com.ubo.zyq.controller.admin;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
import com.ubo.zyq.entity.HomepageAds;
import com.ubo.zyq.entity.LayuiMsg;
import com.ubo.zyq.entity.Response;
import com.ubo.zyq.service.HomepageAdsService;
import com.ubo.zyq.util.FtpConnection;
import com.ubo.zyq.util.FtpUtil;
import com.ubo.zyq.util.IDUtils;
import com.ubo.zyq.util.PARAM;
import com.ubo.zyq.util.SnowFlake;

/**
 * 前台广告轮番图管理界面查询
 * @author 谢雄辉
 * 2020年4月14日  
 */

@Controller
@RequestMapping("/management/homepageads")
public class HomepageAdsController {
	
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
	HomepageAdsService homepageAdsService;
	
	@GetMapping("/getTableHomepage")
	@ResponseBody
	public DataTableResult getTableHomepage(@RequestParam(required=false,defaultValue="0",name="page")int currpage,
			@RequestParam(required=false,defaultValue="20")int limit) {
		
		return homepageAdsService.findBylist(PageRequest.of((currpage - 1), limit));
	}
	
	/**
	 * 跳转到添加路径
	 * @return
	 */
	@GetMapping("/addhomepageads.html")
	public String addhomepageads() {
		return "admin/homepageads-add";
	}
	
	@PostMapping("/save")
	@ResponseBody
	public Response save(@RequestBody HomepageAds homepageAds) {
		
		 HomepageAds ads = homepageAdsService.save(homepageAds);
		
		return new Response(true,"添加成功",ads);
	}
	
	/**
	 * 根据id查询,展示到编辑页面
	 * @param id
	 * @param model
	 * @return
	 */
	@GetMapping("/findById/{id}")
	public String findById(@PathVariable("id") Long id,Model model) {
		 Optional<HomepageAds> optional = homepageAdsService.findById(id);
		  HomepageAds homepageAds = optional.get();
		model.addAttribute("homepageads", homepageAds);
		return "admin/homepageads-edit";
	}
	
	/**
	 * 修改图片功能 
	 * @param homepageAds
	 * @return
	 */
	@PostMapping("/edit")
	@ResponseBody
	public Response edid(@RequestBody HomepageAds homepageAds) {
		 Optional<HomepageAds> optional = homepageAdsService.findById(homepageAds.getId());
		 if(optional.isPresent()) {
			  HomepageAds ads = optional.get();
			  ads.setLogo(homepageAds.getLogo());
			  homepageAdsService.save(ads);
			  return new Response(true,"修改成功");
		 }
		return new Response(false,"修改失败");
		
	}
	
	
	/**
	 * 接收图片上传
	 * @param file
	 * @return
	 * @throws IllegalStateException
	 * @throws IOException
	 */
	@PostMapping("/uploadProductImgFirst")
    @ResponseBody
    public LayuiMsg uploadProductImgFirst(@RequestPart MultipartFile file) throws IllegalStateException, IOException {
		/*
		 * String realName = ""; if (file != null) { String fileName =
		 * file.getOriginalFilename(); String fileNameExtension =
		 * fileName.substring(fileName.lastIndexOf(".")); realName =
		 * UUID.randomUUID().toString() + fileNameExtension; File uploadFile = new
		 * File(PARAM.HOMEPAGEADS_IMG_PATH, realName); file.transferTo(uploadFile); }
		 * else { return new LayuiMsg(1, "上传失败，未能接收到图片", null); } return new LayuiMsg(0,
		 * "上传完成", "/getHomepageads/" + realName);
		 */
		
		try {
            //1、给上传的图片生成新的文件名
            //1.1获取原始文件名
            String oldName = file.getOriginalFilename();
            //1.2使用IDUtils工具类生成新的文件名，新文件名 = newName + 文件后缀
            String newName = IDUtils.genImageName();
            newName = newName + oldName.substring(oldName.lastIndexOf("."));
            //1.3生成文件在服务器端存储的子目录
            
            String filePath = "/Homepageads";
            
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
