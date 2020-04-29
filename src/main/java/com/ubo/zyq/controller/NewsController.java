package com.ubo.zyq.controller;

import com.ubo.zyq.entity.EnterPriseinFormation;
import com.ubo.zyq.entity.News;
import com.ubo.zyq.service.EnterPriseinFormationService;
import com.ubo.zyq.service.NewsService;
import com.ubo.zyq.util.ZYQ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class NewsController {

	
	@Autowired
	EnterPriseinFormationService enterpriseinformationservice;
	
    private final NewsService newsService;

    @Autowired
    public NewsController(NewsService newsService) {
        this.newsService = newsService;
    }

    @GetMapping("/news")
    public String listNews(Model model) {
        Page<News> page = newsService.listNewsByNewstype("1", PageRequest.of(0, 6));
        model.addAttribute("page", page);
        model.addAttribute("newslist1", page.getContent());
        return "news";
    }

    @GetMapping("/news/{id}")
    public String viewNewsDetail(@PathVariable("id") Long id, HttpServletRequest request, Model model) {
        Optional<News> news = newsService.getNewsById(id);
        
      //显示网站的一些信息数据 头部与底部, 数据只有一条 所以给个固定参数值 Optional<EnterPriseinFormation>
		  Optional<EnterPriseinFormation> findById = enterpriseinformationservice.getEnterPriseinFormationRepositoryById(1L);
		  
		  // Optional通过get方法转义成需要的对象,不转义的话 前台拿不到值会报错 EnterPriseinFormation
		  EnterPriseinFormation enterPriseinFormation = findById.get();
		  model.addAttribute("enterPriseinFormation", enterPriseinFormation);
        
        boolean isMobile = ZYQ.JudgeIsMoblie(request);
        if (news.isPresent()) {
            newsService.readingIncrease(id);
            News news1 = news.get();
            if(isMobile){
                news1.setContent(news1.getContent().replace("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;","　　" ));
                news1.setContent(news1.getContent().replace("max-width:70%;", "max-width:85%;"));
            }
            model.addAttribute("news", news1);
        }
        return isMobile ? "mobile/news-detail" : "news-detail";
    }


    @GetMapping("/news/list/{newsType}/{page}")
    public String chooseNewsType(@PathVariable("newsType") String newsType, @PathVariable("page") int currPage, Model model) {
        Page<News> page = newsService.listNewsByNewstype(newsType, PageRequest.of(currPage - 1, 6));
        model.addAttribute("page", page);
        model.addAttribute("newslist1", page.getContent());
        return "news::#newsListReplace";
    }

    @GetMapping("/news/indexlist/{id}")
    public String indexNewsType(@PathVariable("id") String id, Model model) {
        Sort sort = new Sort(Sort.Direction.DESC, "createTime");
        Pageable pageable = PageRequest.of(0, 4, sort);
        Page<News> type1 = newsService.listNewsByNewstype(id, pageable);
        List<News> list1 = new ArrayList<>(type1.getContent());
        model.addAttribute("firstNews", list1.size() > 0 ? list1.get(0) : new News(null, null, null));
        if (list1.size() > 0) {
            list1.remove(0);
        }
        model.addAttribute("newslist1", list1);
        return "index::#newsListReplace";
    }
    
    @GetMapping("/xnew")
    public String xxhnew(Model model) {
    	
    	//显示网站的一些信息数据 头部与底部, 数据只有一条 所以给个固定参数值 Optional<EnterPriseinFormation>
		  Optional<EnterPriseinFormation> findById = enterpriseinformationservice.getEnterPriseinFormationRepositoryById(1L);
		  
		  // Optional通过get方法转义成需要的对象,不转义的话 前台拿不到值会报错 EnterPriseinFormation
		  EnterPriseinFormation enterPriseinFormation = findById.get();
		  model.addAttribute("enterPriseinFormation", enterPriseinFormation);
    	
    	 Page<News> page = newsService.listNewsByNewstype("1", PageRequest.of(0, 6));
         model.addAttribute("page", page);
         model.addAttribute("newslist1", page.getContent());
       
    	return "xnew";
    }
    
    @GetMapping("/news/listTwo/{newsType}/{page}")
    public String chooseNewsTypeTwo(@PathVariable("newsType") String newsType, @PathVariable("page") int currPage, Model model) {
        Page<News> page = newsService.listNewsByNewstype(newsType, PageRequest.of(currPage - 1, 6));
        model.addAttribute("page", page);
        model.addAttribute("newslist1", page.getContent());
        return "xnew::#newsListReplace";
    }
}
