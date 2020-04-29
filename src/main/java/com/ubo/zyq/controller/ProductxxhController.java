package com.ubo.zyq.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ProductxxhController {

	
	@GetMapping(value="/productxxh")
	public String productIndex() {
		return "product";
	}
	
	
}
