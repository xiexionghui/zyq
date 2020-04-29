package com.ubo.zyq.controller;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ubo.zyq.entity.EnterPriseinFormation;
import com.ubo.zyq.entity.Product;
import com.ubo.zyq.service.EnterPriseinFormationService;
import com.ubo.zyq.service.ProductCateService;
import com.ubo.zyq.service.ProductService;
import com.ubo.zyq.util.ZYQ;

/**
 * @author zyq
 * @date 2020-1-16 14:15
 */
@Controller
@RequestMapping("/product")
public class ProductController {
	@Autowired
	private ProductCateService productCateService;

	
	@Autowired
	EnterPriseinFormationService enterpriseinformationservice;
	
	
	@Autowired
	ProductService productService;

	/*
	 * @GetMapping() public String product(HttpServletRequest request, Model model)
	 * { if (ZYQ.JudgeIsMoblie(request)) { return "mobile/product"; } else {
	 * model.addAttribute("cateTypeList",
	 * productCateService.getTabCardTextAndChildCate()); return "product"; } }
	 */

	/**
	 * 产品中心页面 带分页处理 一页显示 8条信息
	 * 
	 * @param request
	 * @param model
	 * @return
	 */
	@GetMapping()
	public String product(HttpServletRequest request, Model model) {

		if (ZYQ.JudgeIsMoblie(request)) {
			return "mobile/product";
		}
		Page<Product> page = productService.listProductByProducttype(10, PageRequest.of(0, 8));
		model.addAttribute("page", page);
		model.addAttribute("product1", page.getContent());
		return "xproduct";
	}

	/**
	 * 分页跳转处理
	 * 
	 * @param currPage
	 * @param model
	 * @return
	 */
	@GetMapping("/product/list/{page}")
	public String chooseNewsType(@PathVariable("page") int currPage, Model model) {
		Page<Product> page = productService.listProductByProducttype(10, (PageRequest.of(currPage - 1, 8)));
		model.addAttribute("page", page);
		model.addAttribute("product1", page.getContent());
		return "xproduct::#productListReplace";
	}

	/**
	 * 
	 * @method:产品详情页面数据处理并展示
	 * @param id
	 * @return
	 */
	@GetMapping(value = "product.html/{id}")
	public String productHtml(@PathVariable("id") Long id, Model model) {
		
		
		//显示网站的一些信息数据 头部与底部, 数据只有一条 所以给个固定参数值 Optional<EnterPriseinFormation>
		  Optional<EnterPriseinFormation> findById = enterpriseinformationservice.getEnterPriseinFormationRepositoryById(1L);
		  
		  // Optional通过get方法转义成需要的对象,不转义的话 前台拿不到值会报错 EnterPriseinFormation
		  EnterPriseinFormation enterPriseinFormation = findById.get();
		  model.addAttribute("enterPriseinFormation", enterPriseinFormation);

		// 根据主键id查找数据 并返回,通过 get()方法转成 产品对象 并存储,通过
		// Model类里addAttribute方法进行添加属性,带到前台页面并展示;
		Optional<Product> productFindById = productService.findById(id);
		Product product = productFindById.get();
		model.addAttribute("product", product);
		
		
		//查询产品表随机显示3条 并带到详情页面去
		 List<Product> listProduct = productService.findProductRandomLimitThere();
		 model.addAttribute("listProduct", listProduct);
		 
		 return "xproduct-detail";
	}
	
	
	
	@GetMapping(value="productType")
	public String productType(Model model) {
		 //显示网站的一些信息数据 头部与底部, 数据只有一条 所以给个固定参数值 Optional<EnterPriseinFormation>
		  Optional<EnterPriseinFormation> findById = enterpriseinformationservice.getEnterPriseinFormationRepositoryById(1L);
		  
		  // Optional通过get方法转义成需要的对象,不转义的话 前台拿不到值会报错 EnterPriseinFormation
		  EnterPriseinFormation enterPriseinFormation = findById.get();
		  model.addAttribute("enterPriseinFormation", enterPriseinFormation);
		 List<Product> list1 = productService.findProductConditionProductTypeAndStatusOrderByCreateTime(1);
		 List<Product> list2 = productService.findProductConditionProductTypeAndStatusOrderByCreateTime(2);
		 List<Product> list3 = productService.findProductConditionProductTypeAndStatusOrderByCreateTime(3);
		 List<Product> list4 = productService.findProductConditionProductTypeAndStatusOrderByCreateTime(4);
		 model.addAttribute("list", list1);
		 model.addAttribute("list2", list2);
		 model.addAttribute("list3", list3);
		 model.addAttribute("list4", list4);
		 return "xproductType";
	}
	
	
}
