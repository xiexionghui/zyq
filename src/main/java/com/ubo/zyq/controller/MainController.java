package com.ubo.zyq.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.ubo.zyq.entity.ContactUs;
import com.ubo.zyq.entity.EnterPriseinFormation;
import com.ubo.zyq.entity.HomepageAds;
import com.ubo.zyq.entity.News;
import com.ubo.zyq.service.ContactUsService;
import com.ubo.zyq.service.EnterPriseinFormationService;
import com.ubo.zyq.service.HomepageAdsService;
import com.ubo.zyq.service.NewsService;
import com.ubo.zyq.util.ZYQ;

@Controller
public class MainController {

	private final NewsService newsService;

	@Autowired
	public MainController(NewsService newsService) {
		this.newsService = newsService;
	}

	
	  @Autowired EnterPriseinFormationService enterpriseinformationservice;
	 
	  
	  @Autowired
		ContactUsService contactUsService;
	  
	  @Autowired
	  HomepageAdsService homepageAdsService;

	@GetMapping("/")
	public String root(HttpServletRequest request, Model model) {
		Sort sort = new Sort(Sort.Direction.DESC, "createTime");
		Pageable pageable = PageRequest.of(0, 4, sort);
		if (ZYQ.JudgeIsMoblie(request)) {
			Page<News> newsPage = newsService.findByStatusOrderByCreateTimeDesc("0", pageable);
			model.addAttribute("newslist", newsPage.getContent());
			return "mobile/index";
		} else {

			
			  //显示网站的一些信息数据 头部与底部, 数据只有一条 所以给个固定参数值 Optional<EnterPriseinFormation>
			  Optional<EnterPriseinFormation> findById = enterpriseinformationservice.getEnterPriseinFormationRepositoryById(1L);
			  
			  // Optional通过get方法转义成需要的对象,不转义的话 前台拿不到值会报错 EnterPriseinFormation
			  EnterPriseinFormation enterPriseinFormation = findById.get();
			  model.addAttribute("enterPriseinFormation", enterPriseinFormation);
			  
			  
			//首页广告轮番图动态展示
			   List<HomepageAds> homepageAds = homepageAdsService.findAll();
			   model.addAttribute("homepageAds", homepageAds);

			Page<News> type1 = newsService.listNewsByNewstype("1", pageable);
			List<News> list1 = new ArrayList<>(type1.getContent());
			
		
			
			
			News firstNews = list1.get(0);
			model.addAttribute("firstNews", firstNews);
			if (list1.size() > 0) {
				list1.remove(0);
			}
			model.addAttribute("newslist1", list1);

			return "index";
		}

	}

	@GetMapping("/index")
	public String index() {
		return "forward:/";
	}

	@GetMapping("/about")
	public String about(HttpServletRequest request,Model model) {
		if (ZYQ.JudgeIsMoblie(request)) {
			return "mobile/about";
		} else {
			 //显示网站的一些信息数据 头部与底部, 数据只有一条 所以给个固定参数值 Optional<EnterPriseinFormation>
			  Optional<EnterPriseinFormation> findById = enterpriseinformationservice.getEnterPriseinFormationRepositoryById(1L);
			  
			  // Optional通过get方法转义成需要的对象,不转义的话 前台拿不到值会报错 EnterPriseinFormation
			  EnterPriseinFormation enterPriseinFormation = findById.get();
			  model.addAttribute("enterPriseinFormation", enterPriseinFormation);
			
			return "about";
		}
	}

	@GetMapping("/contact")
	public String contact(Model model) {
		 //显示网站的一些信息数据 头部与底部, 数据只有一条 所以给个固定参数值 Optional<EnterPriseinFormation>
		  Optional<EnterPriseinFormation> findById = enterpriseinformationservice.getEnterPriseinFormationRepositoryById(1L);
		  
		  // Optional通过get方法转义成需要的对象,不转义的话 前台拿不到值会报错 EnterPriseinFormation
		  EnterPriseinFormation enterPriseinFormation = findById.get();
		  model.addAttribute("enterPriseinFormation", enterPriseinFormation);
		return "contact";
	}

	@GetMapping("/honor")
	public String honor(HttpServletRequest request,Model model) {
		if (ZYQ.JudgeIsMoblie(request)) {
			return "mobile/honor";
		} else {
			 //显示网站的一些信息数据 头部与底部, 数据只有一条 所以给个固定参数值 Optional<EnterPriseinFormation>
			  Optional<EnterPriseinFormation> findById = enterpriseinformationservice.getEnterPriseinFormationRepositoryById(1L);
			  
			  // Optional通过get方法转义成需要的对象,不转义的话 前台拿不到值会报错 EnterPriseinFormation
			  EnterPriseinFormation enterPriseinFormation = findById.get();
			  model.addAttribute("enterPriseinFormation", enterPriseinFormation);
			return "honor";
		}
	}

	@GetMapping("/robots.txt")
	public String robots() {
		return "robots.txt";
	}

	@GetMapping("/lianxi")
	public String lianxi(Model model) {
		 //显示网站的一些信息数据 头部与底部, 数据只有一条 所以给个固定参数值 Optional<EnterPriseinFormation>
		  Optional<EnterPriseinFormation> findById = enterpriseinformationservice.getEnterPriseinFormationRepositoryById(1L);
		  
		  // Optional通过get方法转义成需要的对象,不转义的话 前台拿不到值会报错 EnterPriseinFormation
		  EnterPriseinFormation enterPriseinFormation = findById.get();
		  model.addAttribute("enterPriseinFormation", enterPriseinFormation);
		  
		  
		//固定就一条数据 所以这里参数值 固定是1L;
		ContactUs contactUs = contactUsService.findById(1L);
		model.addAttribute("contactus", contactUs);
		  
		  
		return "lianxi";
	}
}