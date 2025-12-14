package mvc.ecom.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpSession;
import mvc.ecom.dao.CategoryDao;
import mvc.ecom.dao.ProductDao;
import mvc.ecom.dao.UserDao;
import mvc.ecom.entity.Category;
import mvc.ecom.entity.User;
import mvc.ecom.helper.FactoryProvider;

@Controller
@RequestMapping("/admin")
public class AdminController {

	@GetMapping("/dashboard")
	public String adminDashboard(HttpSession session, Model model) {

		User user = (User) session.getAttribute("current_user");

		if (user == null || "Normal".equals(user.getUserType())) {
			session.setAttribute("message", "Invalid access");
			return "redirect:/login";
		}

		model.addAttribute("totalUsers", UserDao.TotalUsers());

		CategoryDao cd = new CategoryDao(FactoryProvider.getFactory());
		List<Category> categories = cd.getCategory();
		model.addAttribute("categories", categories);
		model.addAttribute("totalCategories", categories.size());

		ProductDao pd = new ProductDao(FactoryProvider.getFactory());
		model.addAttribute("totalProducts", pd.getProduct().size());

		return "admin.html";
	}
}
