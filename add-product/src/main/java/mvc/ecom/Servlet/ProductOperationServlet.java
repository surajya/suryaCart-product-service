package mvc.ecom.Servlet;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import mvc.ecom.dao.CategoryDao;
import mvc.ecom.dao.ProductDao;
import mvc.ecom.entity.Category;
import mvc.ecom.entity.Product;
import mvc.ecom.helper.FactoryProvider;

@MultipartConfig
public class ProductOperationServlet extends HttpServlet {

	private static final String UPLOAD_DIR = "uploads/products";

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		PrintWriter pw = response.getWriter();

		try {
			String op = request.getParameter("operation");

			if ("addCategory".equals(op)) {

				String title = request.getParameter("catTitle");
				String description = request.getParameter("catDiscription");

				Category cat = new Category();
				cat.setCategoryTitle(title);
				cat.setCategoryDiscription(description);

				CategoryDao catdao = new CategoryDao(FactoryProvider.getFactory());
				int catId = catdao.categorySave(cat);

				HttpSession session = request.getSession();
				session.setAttribute("message", "Category saved successfully: " + catId);
				response.sendRedirect("admin.jsp");
				return;
			}

			if ("addProduct".equals(op)) {

				String title = request.getParameter("pName");
				String dis = request.getParameter("pDiscription");
				int price = Integer.parseInt(request.getParameter("pPrice"));
				int discount = Integer.parseInt(request.getParameter("pDiscount"));
				int quantity = Integer.parseInt(request.getParameter("pQuantity"));
				int cid = Integer.parseInt(request.getParameter("CID"));

				Part part = request.getPart("pPic");

				CategoryDao cd = new CategoryDao(FactoryProvider.getFactory());
				Category category = cd.getCategoryById(cid);

				Product p = new Product();
				p.setpTitle(title);
				p.setpDesc(dis);
				p.setpPrice(price);
				p.setpDiscount(discount);
				p.setpQuantity(quantity);
				p.setpPhoto(part.getSubmittedFileName());
				p.setCategory(category);

				ProductDao pdao = new ProductDao(FactoryProvider.getFactory());
				int pId = pdao.productSave(p);

				// ---------- FILE UPLOAD (FIXED) ----------
				String uploadPath = System.getProperty("user.dir") + File.separator + UPLOAD_DIR;

				File uploadDir = new File(uploadPath);
				if (!uploadDir.exists()) {
					uploadDir.mkdirs();
				}

				String filePath = uploadPath + File.separator + part.getSubmittedFileName();

				try (FileOutputStream fos = new FileOutputStream(filePath); InputStream is = part.getInputStream()) {

					fos.write(is.readAllBytes());
				}

				HttpSession session = request.getSession();
				session.setAttribute("message", "Product saved successfully: " + pId);
				response.sendRedirect("admin.jsp");
			}

		} catch (Exception e) {
			pw.println(e.getMessage());
		}
	}
}
