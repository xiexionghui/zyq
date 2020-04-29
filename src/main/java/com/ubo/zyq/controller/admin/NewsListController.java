package com.ubo.zyq.controller.admin;

import com.ubo.zyq.entity.JsonResult;
import com.ubo.zyq.entity.LayuiMsg;
import com.ubo.zyq.entity.News;
import com.ubo.zyq.entity.Response;
import com.ubo.zyq.service.NewsService;
import com.ubo.zyq.entity.DataTableResult;
import com.ubo.zyq.util.FtpUtil;
import com.ubo.zyq.util.IDUtils;
import com.ubo.zyq.util.ImageUtil;
import com.ubo.zyq.util.PARAM;
import com.ubo.zyq.util.ZYQ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Controller
@RequestMapping("/management/News")
public class NewsListController {

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

	private final NewsService newsService;

	@Autowired
	public NewsListController(NewsService newsService) {
		this.newsService = newsService;
	}

	@GetMapping("/getNewsTable")
	@ResponseBody
	public DataTableResult getNewsTable(@RequestParam(required = false, defaultValue = "20") int limit,
			@RequestParam(name = "page", required = false, defaultValue = "0") int currPage) {
		Page<News> page = newsService.findByStatusOrderByCreateTimeDesc("0", PageRequest.of(currPage - 1, limit));
		return new DataTableResult(0, "", page.getTotalElements(), page.getContent());
	}

	@GetMapping("/edit/{id}")
	public String newsEdit(@PathVariable(name = "id", required = false) Long id, Model model) {
		News news;
		if (id == null || id == -1) {
			news = new News();
		} else {
			Optional<News> n = newsService.findById(id);
			news = n.orElseGet(News::new);
		}
		model.addAttribute("newsModel", news);
		return "admin/news-edit";
	}

	@PostMapping("/save")
	@ResponseBody
	public Response savenews(@RequestBody News newsModel) {
		if (newsModel == null || ZYQ.isNULL(newsModel.getTitle(), newsModel.getSummary(), newsModel.getContent())) {
			return new Response(false, "不可提交，文章标题，导读，内容均不可为空");
		}
		List<String> srcs = ImageUtil.getImgStr(newsModel.getContent());
		if (srcs.isEmpty()) {
			return new Response(false, "请添加一张图片");
		} else {
			String src = srcs.get(0);
			newsModel.setImg(src.substring(src.indexOf("/")));
			if (newsModel.getId() == null) {
				newsModel.setStatus("0");
				newsModel.setCreateTime(new Date());
				newsService.save(newsModel);
			} else {
				Optional<News> optional = newsService.findById(newsModel.getId());
				if (optional.isPresent()) {
					News s = optional.get();
					s.setImg(newsModel.getImg());
					s.setContent(newsModel.getContent());
					s.setNewstype(newsModel.getNewstype());
					s.setSummary(newsModel.getSummary());
					s.setTitle(newsModel.getTitle());
					newsService.save(s);
				} else {
					return new Response(false, "未能发布成功");
				}
			}
		}
		return new Response(true, "发布成功", "/management");
	}

	@PostMapping("/delNews")
	@ResponseBody
	public Response delNews(@RequestBody News newsModel) {
		Optional<News> optional = newsService.findById(newsModel.getId());
		if (optional.isPresent()) {
			News s = optional.get();
			s.setStatus("9");
			newsService.save(s);
			return new Response(true, "删除完成");
		} else {
			return new Response(false, "未能成功删除");
		}
	}

	@PostMapping("/uploadImg")
	@ResponseBody
	public JsonResult uploadImg(MultipartFile img) throws IllegalStateException, IOException {

		/*
		 * String realName = ""; if (img != null) { String fileName =
		 * img.getOriginalFilename(); String fileNameExtension =
		 * fileName.substring(fileName.lastIndexOf(".")); realName =
		 * UUID.randomUUID().toString() + fileNameExtension; File uploadFile = new
		 * File(PARAM.NEWSDETAIL_IMG_PATH, realName); img.transferTo(uploadFile);
		 * String[] str = { "/getImg/" + realName }; return new JsonResult(0, str);
		 * 
		 * }
		 */
		

		
		  try { 
			  //1、给上传的图片生成新的文件名 //1.1获取原始文件名 
			  String oldName =img.getOriginalFilename(); //1.2使用IDUtils工具类生成新的文件名，新文件名 = newName + 文件后缀
		  String newName = IDUtils.genImageName(); 
		  newName = newName + oldName.substring(oldName.lastIndexOf(".")); //1.3生成文件在服务器端存储的子目录
		  
		  String filePath = "/News";
		  
		  //3、把图片上传到图片服务器 //3.1获取上传的io流 
		  InputStream input = img.getInputStream();
		  
		  //3.2调用FtpUtil工具类进行上传 
		  boolean result = FtpUtil.uploadFile(host, port,userName, passWord, basePath, filePath, newName, input); 
		      if(result) {
		    	  String[] str = {baseUrl + filePath + "/"+ newName}; 
		    	  return new JsonResult(0, str);
		      }else { 
				  String[] strr = {"上传失败"};
				  return new JsonResult(0,strr);   
		      }
		}catch (IOException e) {
			  String[] strr = {"上传失败"}; 
			   return new JsonResult(0,strr) ;
		 }	  
	}
}
