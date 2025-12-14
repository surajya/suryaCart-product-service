package mvc.ecom.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpSession;
import mvc.ecom.dao.CategoryDao;
import mvc.ecom.dao.ProductDao;
import mvc.ecom.entity.Category;
import mvc.ecom.entity.Product;
import mvc.ecom.helper.FactoryProvider;

@Controller
public class HomeController {

	@GetMapping("/")
	public String home(@RequestParam(value = "category", required = false) String category, Model model) {

		CategoryDao categoryDao = new CategoryDao(FactoryProvider.getFactory());
		List<Category> categories = categoryDao.getCategory();
		model.addAttribute("categories", categories);

		ProductDao productDao = new ProductDao(FactoryProvider.getFactory());
		List<Product> products;

		if (category == null || category.equalsIgnoreCase("All")) {
			products = productDao.getProduct();
		} else {
			int cid = Integer.parseInt(category);
			products = productDao.getProductById(cid);
		}

		model.addAttribute("products", products);

		return "index.html";
	}

	@GetMapping("/login")
	public String loginPage(HttpSession session) {
		// SAME LOGIC as JSP
		session.removeAttribute("current_user");
		session.removeAttribute("message");
		return "login.html";
	}

	@PostMapping("/login")
	public String doLogin(@RequestParam String userEmail, @RequestParam String userPassword, HttpSession session) {

		// your existing Login servlet logic here
		session.removeAttribute("message");
		return "redirect:/";
	}

	@GetMapping("/register")
	public String registerPage(HttpSession session) {
		return "register.html";
	}
}
