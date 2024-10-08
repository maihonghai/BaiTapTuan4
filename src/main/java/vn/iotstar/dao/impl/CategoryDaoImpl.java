package vn.iotstar.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import vn.iotstar.dao.CategoryDao;
import vn.iotstar.configs.DBConnectSQL;

import vn.iotstar.models.CategoryModel;

public class CategoryDaoImpl extends DBConnectSQL implements CategoryDao {

	@Override
	public void insert(CategoryModel category) {
		String sql = "INSERT INTO Category(cate_name,icons,status) VALUES (?,?,?)";
		try {
			Connection con = super.getConnection();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, category.getCatename());
			ps.setString(2, category.getIcon());
			ps.setInt(3, category.getStatus());
			ps.executeUpdate();
			con.close();
			ps.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void update(CategoryModel category) {
		String sql = "UPDATE Category SET cate_name = ?, icons=?, status = ? WHERE cate_id = ?";
		try {
			Connection con = super.getConnection();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, category.getCatename());
			ps.setString(2, category.getIcon());
			ps.setInt(3, category.getStatus());
			ps.setInt(4, category.getCateid());
			ps.executeUpdate();
			con.close();
			ps.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void delete(int id) {
		String sql = "DELETE FROM Category WHERE cate_id = ?";
		try {
			Connection con = super.getConnection();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, id);
			ps.executeUpdate();
			con.close();
			ps.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public CategoryModel findByID(int id) {
		String sql = "SELECT * FROM Category WHERE cate_id = ? ";
		try {
			Connection con = super.getConnection();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				CategoryModel category = new CategoryModel();
				category.setCateid(rs.getInt("cate_id"));
				category.setCatename(rs.getString("cate_name"));
				category.setIcon(rs.getString("icons"));
				category.setStatus(rs.getInt("status"));
				return category;
			}
			con.close();
			ps.close();
			rs.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public CategoryModel get(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CategoryModel> findAll() {
		List<CategoryModel> categories = new ArrayList<CategoryModel>();
		String sql = "SELECT * FROM Category";
		try {
			Connection conn = super.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				CategoryModel category = new CategoryModel();
				category.setCateid(rs.getInt("cate_id"));
				category.setCatename(rs.getString("cate_name"));
				category.setIcon(rs.getString("icons"));
				category.setStatus(rs.getInt("status"));
				categories.add(category);
			}
			conn.close();
			ps.close();
			rs.close();
			return categories;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<CategoryModel> search(String keyword) {
		List<CategoryModel> categories = new ArrayList<CategoryModel>();
		String sql = "SELECT * FROM Category WHERE cate_name like ?";
		try {
			Connection conn = super.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, "%" + keyword + "%" );
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				CategoryModel category = new CategoryModel();
				category.setCateid(rs.getInt("cate_id"));
				category.setCatename(rs.getString("cate_name"));
				category.setIcon(rs.getString("icons"));
				category.setStatus(rs.getInt("status"));
				categories.add(category);
			}
			conn.close();
			ps.close();
			rs.close();
			return categories;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
