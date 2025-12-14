package mvc.ecom.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CustomerController {

	@GetMapping("/customer")
	public String customerPage() {
		System.out.println("this is call");
		return "normal.html";
	}
}
