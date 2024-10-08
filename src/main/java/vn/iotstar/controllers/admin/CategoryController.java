package vn.iotstar.controllers.admin;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import vn.iotstar.Service.CategoryService;
import vn.iotstar.Service.impl.CategoryServiceImpl;
import vn.iotstar.models.CategoryModel;
import static vn.iotstar.utils.Constant.*;

@MultipartConfig(fileSizeThreshold = 1024 * 1024, maxFileSize = 1024 * 1024 * 5, maxRequestSize = 1024 * 1024 * 5 * 5)
@WebServlet(urlPatterns = { "/admin/categories","/admin/category/add",
		"/admin/category/insert","/admin/category/edit","/admin/category/update" ,
		"/admin/category/delete","/admin/category/search"})
public class CategoryController extends HttpServlet{
	
	private static final long serialVersionUID = 1L;
	public CategoryService cateService = new CategoryServiceImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	String url = req.getRequestURI();
	req.setCharacterEncoding("UTF-8");
	resp.setCharacterEncoding("UTF-8");
	
	if(url.contains("categories")) {
		List<CategoryModel> cateList = cateService.findAll();
		req.setAttribute("cateList", cateList);
		req.getRequestDispatcher("/views/admin/category-list.jsp").forward(req, resp);
	}
	else if(url.contains("add")) {
		req.getRequestDispatcher("/views/admin/category-add.jsp").forward(req, resp);
	}
	else if(url.contains("edit")) {
		int id = Integer.parseInt(req.getParameter("id"));
		
		CategoryModel category= cateService.findByID(id);
		
		req.setAttribute("cate", category);
		
		req.getRequestDispatcher("/views/admin/category-edit.jsp").forward(req, resp);
	}else if(url.contains("delete")) {
		int cateid = Integer.parseInt(req.getParameter("id"));
		cateService.delete(cateid);
		resp.sendRedirect(req.getContextPath()+ "/admin/categories");
	}
	
	}
	
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	String url = req.getRequestURI();
    	req.setCharacterEncoding("UTF-8");
    	resp.setCharacterEncoding("UTF-8");
    	
    	if(url.contains("insert")) {
    		CategoryModel category = new CategoryModel();
    		String catename = req.getParameter("catename");
    		String status = req.getParameter("status");
    		int statuss = Integer.parseInt(status);
    		category.setCatename(catename);
    		category.setStatus(statuss);
    		
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
    				category.setIcon(fname);
    			}else {
    				category.setIcon("");
    			}
    		}catch(Exception e) {
    			e.printStackTrace();    		
    		}	
    		
    		cateService.insert(category);
    		resp.sendRedirect(req.getContextPath()+ "/admin/categories");
    	}
    	else if(url.contains("update")){
    		int cateid = Integer.parseInt(req.getParameter("cateid"));
    		String catename = req.getParameter("catename");
    		String status = req.getParameter("status");
    		int statuss = Integer.parseInt(status);
    		
    		CategoryModel category = new CategoryModel();
    		category.setCateid(cateid);
    		category.setCatename(catename);
    		category.setStatus(statuss);
    		//Luu hinh cu
    		CategoryModel cateold = cateService.findByID(cateid);
    		String fileold =  cateold.getIcon();
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
    				category.setIcon(fname);
    			}else {
    				category.setIcon(fileold);
    			}
    		}catch(Exception e) {
    			e.printStackTrace();    		
    		}	
    		
    		cateService.update(category);
    		resp.sendRedirect(req.getContextPath()+ "/admin/categories");
    	}
    	
    }
}
