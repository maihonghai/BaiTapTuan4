package vn.iotstar.controllers;

import static vn.iotstar.utils.Constant.DIR;

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
import vn.iotstar.models.CategoryModel;
import vn.iotstar.models.UserModel;
import vn.iotstar.utils.Constant;
import vn.iotstar.Service.IUserService;
import vn.iotstar.Service.impl.UserServiceImpl;
import vn.iotstar.dao.impl.UserDaoImpl;


@WebServlet(urlPatterns = { "/home","/home/profiles", "/home/profile/update" })
@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 1024 * 1024 * 5, maxRequestSize = 1024 * 1024 * 5 * 5)

public class HomeController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	IUserService userService = new UserServiceImpl();
	
		@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

			String url = req.getRequestURI();
			req.setCharacterEncoding("UTF-8");
			resp.setCharacterEncoding("UTF-8");

		if (url.contains("profiles")) {
			int id = Integer.parseInt(req.getParameter("id"));
			
			UserModel user = userService.findById(id);
			req.setAttribute("user", user);
			
			req.getRequestDispatcher("/views/profile.jsp").forward(req, resp);
		} else if (url.contains("/home")) {
			req.getRequestDispatcher("/views/home.jsp").forward(req, resp);
		}

	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String url = req.getRequestURI();
		req.setCharacterEncoding("UTF-8");
		 resp.setCharacterEncoding("UTF-8");
		 if(url.contains("update")){
			 update(req, resp);
	    	}
		

	}
	private void update(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
	
	    // Lấy thông tin từ form
	    String name = req.getParameter("fullname");
	    String phone = req.getParameter("phone");
	    int id = Integer.parseInt(req.getParameter("id"));

	    // Tạo model User và cập nhật thông tin
	    UserModel user = new UserModel();
	    user.setId(id);
	    user.setFullName(name);
	    user.setPhone(phone);
	    //user.setImages(image); // Lưu đường dẫn ảnh vào database

	  //Luu hinh cu
		UserModel userold = userService.findById(id);
		String fileold =  userold.getImages();
		//xu ly images
		String fname="";
		String uploadPath= DIR;
		File uploadDir= new File(uploadPath);
		if(!uploadDir.exists()) {
			uploadDir.mkdir();
		}
		try {
			Part part=req.getPart("images");
			if(part.getSize()>0) {
				String filename=Paths.get(part.getSubmittedFileName()).getFileName().toString();
				//doi ten file
				int index=filename.lastIndexOf(".");
				String ext=filename.substring(index+1);
				fname = System.currentTimeMillis()+"."+ext;
				//upload file
				part.write(uploadPath + "/" + fname);
				//ghi teen file vao data
				user.setImages(fname);
			}else {
				user.setImages(fileold);
			}
		}catch(Exception e) {
			e.printStackTrace();    		
		}	
		
		userService.update(user);
		resp.sendRedirect(req.getContextPath()+ "/home");
	

	}
}
