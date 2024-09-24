package vn.iotstar.controllers;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.FileOutputStream;

import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.apache.commons.beanutils.BeanUtils;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import vn.iotstar.models.UserModel;
import vn.iotstar.Service.IUserService;
import vn.iotstar.Service.impl.UserServiceImpl;
import vn.iotstar.dao.impl.UserDaoImpl;

@WebServlet(urlPatterns = { "/home", "/profile" })
@MultipartConfig(fileSizeThreshold = 1024*1024*10, maxFileSize = 1024*1024*50,maxRequestSize= 1024*1024*50)

public class HomeController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	IUserService userService = new UserServiceImpl();
	

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String requestUri = req.getRequestURI();

		if (requestUri.endsWith("/profile")) {
			int id = Integer.parseInt(req.getParameter("id"));
			
			UserModel user = userService.findById(id);
			 if (user != null) {
		            req.setAttribute("user", user);
		            RequestDispatcher rd = req.getRequestDispatcher("/views/profile.jsp");
		            rd.forward(req, resp);
		        } else {
		            // Xử lý trường hợp không tìm thấy người dùng
		            req.setAttribute("alert", "Không tìm thấy người dùng!");
		            req.getRequestDispatcher("/views/home.jsp").forward(req, resp);
		        }
		} else if (requestUri.endsWith("/home")) {
			req.getRequestDispatcher("/views/home.jsp").forward(req, resp);
		}

	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		update(req, resp);

	}

	private void update(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
	    req.setCharacterEncoding("UTF-8");
	    resp.setCharacterEncoding("UTF-8");
	    

	    // Lấy thông tin từ form
	    String name = req.getParameter("fullname");
	    String phone = req.getParameter("phone");
	    String image = req.getParameter("images");
	    int id = Integer.parseInt(req.getParameter("id"));

	    System.out.println(name);

	    // Tạo model User và cập nhật thông tin
	    UserModel user = new UserModel();
	    user.setId(id);
	    user.setFullName(name);
	    user.setPhone(phone);
	    user.setImages(image); // Lưu đường dẫn ảnh vào database

	    // Cập nhật thông tin người dùng
	    boolean isUpdateSuccessful = true;
	    try {
	        userService.update(user);
	    } catch (Exception e) {
	        isUpdateSuccessful = false;
	        e.printStackTrace();
	    }

	    // Thông báo kết quả cập nhật
	    String alert = isUpdateSuccessful ? "Cập nhật thông tin thành công!" : "Cập nhật thông tin thất bại!";
	    req.setAttribute("alert", alert);
	    req.setAttribute("user", user);

	    // Điều hướng về trang profile
	    //req.getRequestDispatcher("/views/profile.jsp").forward(req, resp);
	    resp.sendRedirect(req.getContextPath() + "/home");

	}


}
