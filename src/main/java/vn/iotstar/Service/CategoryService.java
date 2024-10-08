package vn.iotstar.Service;

import java.util.List;

import vn.iotstar.models.CategoryModel;

public interface CategoryService {
	void insert(CategoryModel category);
	void update(CategoryModel category);
	void delete(int id);
	CategoryModel findByID(int id);
	CategoryModel get(String name);
	List<CategoryModel> findAll();
	List<CategoryModel> search(String keyword);

}
