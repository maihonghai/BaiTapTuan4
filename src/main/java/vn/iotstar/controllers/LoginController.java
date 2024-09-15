package vn.iotstar.controllers;

import java.io.IOException;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import vn.iotstar.Service.*;
import vn.iotstar.Service.impl.UserServiceImpl;
import vn.iotstar.models.UserModel;
import vn.iotstar.utils.Constant;

@SuppressWarnings("serial")
@WebServlet(urlPatterns = {"/login","/forgot-password"})

public class LoginController extends HttpServlet {
	
	private static final long serialWersionUID = 1L;
	//Lay toan bo ham trong service
	IUserService service = new UserServiceImpl();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		 String action = req.getServletPath();
	        switch (action) {
	            case "/login":
	                req.getRequestDispatcher("/views/login.jsp").forward(req, resp);
	                break;
	            case "/forgot-password":
	                req.getRequestDispatcher("/views/forgotpassword.jsp").forward(req, resp);
	                break;
	            default:
	                resp.sendRedirect(req.getContextPath() + "/login");
	                break;
	        }
		
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html");
		resp.setCharacterEncoding("UTF-8");
		req.setCharacterEncoding("UTF-8");
		
		 String action = req.getServletPath();
        switch (action) {
            case "/login":
                handleLogin(req, resp);
                break;
            case "/forgot-password":
                postForgotPassword(req, resp);
                break;
            default:
                resp.sendRedirect(req.getContextPath() + "/login");
                break;
        }
		
		
	}
	 private void handleLogin(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	        // Lấy tham số từ view
	        String username = req.getParameter("uname");
	        String password = req.getParameter("psw");
	        String remember = req.getParameter("remember");

	        // Kiểm tra tham số
	        boolean isRememberMe = "on".equals(remember);
	        String alertMsg = "";

	        if (username.isEmpty() || password.isEmpty()) {
	            alertMsg = "Tài khoản hoặc mật khẩu không được rỗng";
	            req.setAttribute("alert", alertMsg);
	            req.getRequestDispatcher("/views/login.jsp").forward(req, resp);
	            return;
	        }

	        // Xử lý bài toán
	        UserModel user = service.login(username, password);
	        if (user != null) {
	            HttpSession session = req.getSession(true);
	            session.setAttribute("account", user);
	            if (isRememberMe) {
	                saveRememberMe(resp, username);
	            }
	            resp.sendRedirect(req.getContextPath() + "/waiting");
	        } else {
	            alertMsg = "Tài khoản hoặc mật khẩu không đúng";
	            req.setAttribute("alert", alertMsg);
	            req.getRequestDispatcher("/views/login.jsp").forward(req, resp);
	        }
	    }


	private void saveRememberMe(HttpServletResponse resp, String username) {
		Cookie cookie = new Cookie(Constant.COOKIE_REMEMBER, username);
		cookie.setMaxAge(30 * 60);
		resp.addCookie(cookie);
	}
	
	protected void postForgotPassword(HttpServletRequest req,HttpServletResponse resp) throws ServletException,IOException {
		String email = req.getParameter("email");
        String alertMsg = "";

        // Tìm người dùng theo email
        UserModel user = service.FindByEmail(email);
        if (user != null) {
            alertMsg = "Mật khẩu của bạn là: " + user.getPassWord();
        } else {
            alertMsg = "Email không tồn tại trong hệ thống.";
        }

        // Trả về kết quả cho người dùng
        req.setAttribute("alert", alertMsg);
        req.getRequestDispatcher("/views/forgotpassword.jsp").forward(req, resp);
    }
		
	
	}
	
