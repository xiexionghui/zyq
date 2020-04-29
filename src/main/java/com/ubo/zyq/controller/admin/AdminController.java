package com.ubo.zyq.controller.admin;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/management")
public class AdminController {

    @GetMapping
    public String admin() {
        return "admin/admin";
    }

    @GetMapping("/SelectNews")
    public String newsList() {
        return "admin/news-list";
    }

    @GetMapping("/welcome")
    public String welcome() {
        return "admin/welcome";
    }

    @GetMapping("/SelectHonor")
    public String honorlist(){return "admin/honor-list";}
    
    
    
    @GetMapping("/SelectEnter")
    public String enterpriseinformationList(){
    	
    	return "admin/enterpriseinformation-list";
    }
    
    @GetMapping("/SelectProduct")
    public String ProductList() {
    	return "admin/product-list";
    }
    
    @GetMapping("/SelectContactus")
    public String  SelectContactus() {
    	return "admin/contactus-list";
    }
    
    @GetMapping("/SelectHomepageads")
    public String SelectHomepageads() {
    	
    	return "admin/homepage-list";
    }
    
}
