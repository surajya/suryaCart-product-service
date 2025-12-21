package com.suryacart.product.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

	@GetMapping("/products")
	public String homeProduct() {
		return "redirect:/products/index.html";
	}

	@GetMapping("/categories")
	public String homeCategory() {
		return "redirect:/categories/index.html";
	}
}
