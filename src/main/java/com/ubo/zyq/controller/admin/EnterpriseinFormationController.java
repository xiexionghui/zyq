package com.ubo.zyq.controller.admin;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;
import java.util.UUID;

import org.joda.time.DateTime;
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
import com.ubo.zyq.entity.EnterPriseinFormation;
import com.ubo.zyq.entity.LayuiMsg;
import com.ubo.zyq.entity.Response;
import com.ubo.zyq.service.EnterPriseinFormationService;
import com.ubo.zyq.util.FtpUtil;
import com.ubo.zyq.util.IDUtils;
import com.ubo.zyq.util.PARAM;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/management/Enter")
@Slf4j
public class EnterpriseinFormationController {

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
	EnterPriseinFormationService enterpriseinformationservice;

	/**
	 * @author 谢雄辉 表格数据展示 要不要分页无所谓,数据只有一条,并且只能有一条
	 * @param limit
	 * @param currPage
	 * @return
	 * 
	 */

	@GetMapping("/getEnterpriseinformationTable")
	@ResponseBody
	public DataTableResult getEnterpriseinformationTable(@RequestParam(required = false, defaultValue = "20") int limit,
			@RequestParam(name = "page", required = false, defaultValue = "0") int currPage) {

		Page<EnterPriseinFormation> page = enterpriseinformationservice
				.getEnterPriseinFormationList(PageRequest.of(currPage - 1, limit));

		return new DataTableResult(0, "", page.getTotalElements(), page.getContent());

	}

	/**
	 * ,根据id查询信息并展示到编辑页面
	 * 
	 * @param id
	 * @param model
	 * @return
	 */
	@GetMapping("/event/{id}")
	public String findByIdEnterpriseinformation(@PathVariable("id") long id, Model model) {
		Optional<EnterPriseinFormation> optional = enterpriseinformationservice
				.getEnterPriseinFormationRepositoryById(id);
		EnterPriseinFormation enterPriseinFormation = optional.get();
		model.addAttribute("enterPriseinFormation", enterPriseinFormation);

		return "admin/enterpriseinformation-edit";
	}

	@PostMapping("/edit/save")
	@ResponseBody
	public Response editEnterpriseinformation(@RequestBody EnterPriseinFormation ep, Model model) {

		Optional<EnterPriseinFormation> optional = enterpriseinformationservice
				.getEnterPriseinFormationRepositoryById(ep.getId());
		if (optional.isPresent()) {
			EnterPriseinFormation enterPriseinFormation = optional.get();
			enterPriseinFormation.setBrowserTitle(ep.getBrowserTitle());
			enterPriseinFormation.setBottomServiceHotline(ep.getBottomServiceHotline());
			enterPriseinFormation.setCompanyaddress(ep.getCompanyaddress());
			enterPriseinFormation.setCompanyEmail(ep.getCompanyEmail());
			enterPriseinFormation.setServiceRe(ep.getServiceRe());
			enterPriseinFormation.setSubHeading(ep.getSubHeading());
			enterPriseinFormation.setLogo(ep.getLogo());
			enterPriseinFormation.setLogotwo(ep.getLogotwo());
			enterpriseinformationservice.updateEnterpriseinformation(enterPriseinFormation);
			return new Response(true, "修改成功");
		}

		return new Response(false, "修改失败");
	}

	/**
	 * 接收图片上传
	 * 
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
		 * UUID.randomUUID().toString() + fileNameExtension;
		 * 
		 * File uploadFile = new File(PARAM.Enter_IMG_PATH, realName);
		 * file.transferTo(uploadFile);
		 * 
		 * return new LayuiMsg(0, "上传完成", "/getEnter/" + realName); } else { return new
		 * LayuiMsg(1, "上传失败，未能接收到图片", null); }
		 */
		
		 try {
	            //1、给上传的图片生成新的文件名
	            //1.1获取原始文件名
	            String oldName = file.getOriginalFilename();
	            //1.2使用IDUtils工具类生成新的文件名，新文件名 = newName + 文件后缀
	            String newName = IDUtils.genImageName();
	            newName = newName + oldName.substring(oldName.lastIndexOf("."));
	            //1.3生成文件在服务器端存储的子目录
	            
	            String filePath = "/Enter";
	            
	            /*String filePath = new DateTime().toString("/yyyy/MM/dd");*/
	                        
	               
	            //3、把图片上传到图片服务器
	            //3.1获取上传的io流
	            InputStream input = file.getInputStream();
	            
	            //3.2调用FtpUtil工具类进行上传
	            boolean result = FtpUtil.uploadFile(host, port, userName, passWord, basePath, filePath, newName, input);
	            if(result) {
	            	return new LayuiMsg(0, "上传完成", baseUrl + filePath + "/"+ newName);
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
