package com.suryacart.product.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.suryacart.product.model.Product;
import com.suryacart.product.repository.CategoryRepository;
import com.suryacart.product.repository.ProductRepository;

@Controller
public class HomeController {

	private final CategoryRepository categoryRepository;
	private final ProductRepository productRepository;

	public HomeController(CategoryRepository categoryRepository, ProductRepository productRepository) {
		this.categoryRepository = categoryRepository;
		this.productRepository = productRepository;
	}

	@GetMapping("/home")
	public String home(@RequestParam(value = "category", required = false) Integer categoryId, Model model) {

		model.addAttribute("categories", categoryRepository.findAll());

		List<Product> products;
		if (categoryId == null) {
			products = productRepository.findAll();
		} else {
			products = productRepository.findByCategory_CategoryId(categoryId);
		}

		model.addAttribute("products", products);
		return "home";
	}

	@GetMapping("/login")
	public String login() {

		return "login";
	}

	@PostMapping("/login")
	public String doLogin() {

		return "addProduct";
	}
}
