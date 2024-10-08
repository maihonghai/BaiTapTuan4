package vn.iotstar.Service.impl;

import java.util.List;
import java.io.File;
import vn.iotstar.dao.CategoryDao;
import vn.iotstar.dao.impl.CategoryDaoImpl;
import vn.iotstar.Service.CategoryService;
import vn.iotstar.models.CategoryModel;

public class CategoryServiceImpl implements CategoryService {
	public CategoryDao categoryDao = new CategoryDaoImpl();

	@Override
	public void insert(CategoryModel category) {
//		CategoryModel oldCategory = categoryDao.findByID(newCategory.getCateid());
//		oldCategory.setCatename(newCategory.getCatename());
//		if (newCategory.getIcon() != null) {
//		// XOA ANH CU DI
//		String fileName = oldCategory.getIcon();
//		final String dir = "E:\\upload";
//		File file = new File(dir + "/category" + fileName);
//		if (file.exists()) {
//		file.delete();
//		}
//		oldCategory.setIcon(newCategory.getIcon());
//		}
		categoryDao.insert(category);
	}

	@Override
	public void update(CategoryModel category) {
		CategoryModel oldCate = new CategoryModel();
		oldCate = categoryDao.findByID(category.getCateid());
		//oldCate.setCatename(category.getCatename());
		
		if(oldCate != null) {
			categoryDao.update(category);
		}
	}

	@Override
	public void delete(int id) {
		CategoryModel oldCate = new CategoryModel();
		oldCate = categoryDao.findByID(id);
		//oldCate.setCatename(category.getCatename());
		
		if(oldCate != null) {
			categoryDao.delete(id);
		}
	}

	@Override
	public CategoryModel findByID(int id) {
		return categoryDao.findByID(id);
	}

	@Override
	public CategoryModel get(String name) {
		return categoryDao.get(name);
	}

	@Override
	public List<CategoryModel> findAll() {
		return categoryDao.findAll();
	}

	@Override
	public List<CategoryModel> search(String keyword) {
		return categoryDao.search(keyword);
	}

}
