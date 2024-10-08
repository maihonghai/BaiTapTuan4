package vn.iotstar.Service.impl;


import vn.iotstar.Service.IUserService;
import vn.iotstar.models.UserModel;
import vn.iotstar.dao.IUserDao;
import vn.iotstar.dao.impl.UserDaoImpl;


public class UserServiceImpl implements IUserService  {
	IUserDao userDao = new UserDaoImpl();

	@Override
	public UserModel login(String username, String password) {
		UserModel user = this.FindByUserName(username);
		if (user != null && password.equals(user.getPassWord())) {
		return user;
		}
		return null;
	}
	
	@Override
	public void insert(UserModel user) {
		userDao.insert(user);
	}

	@Override
	public boolean register(String email, String password, String username, String fullname, String phone) {
		if (userDao.checkExistUsername(username)) {
			return false;
			}
			long millis=System.currentTimeMillis();
			java.sql.Date date=new java.sql.Date(millis);
			userDao.insert(new UserModel(username,password,email,fullname,null,1,phone,date));
			return true;
	}

	@Override
	public boolean checkExistUsername(String username) {
		return userDao.checkExistUsername(username);
	}
	@Override
	public boolean checkExistEmail(String email) {
		return userDao.checkExistEmail(email);
	}
	@Override
	public UserModel FindByUserName(String username) {
		return userDao.findByUserName(username);
	}

	@Override
	public boolean checkExistPhone(String phone) {
		return userDao.checkExistPhone(phone);
	}

	@Override
	public UserModel FindByEmail(String email) {
		// TODO Auto-generated method stub
		return userDao.FindByEmail(email);
	}

	@Override
	public UserModel findById(int id) {
		return userDao.findById(id);
	}

	@Override
	public void update(UserModel user) {
		UserModel oldU = userDao.findById(user.getId());
		// Cập nhật các thuộc tính từ đối tượng user mới vào oldU
	    if (oldU != null) {
	        oldU.setUserName(user.getUserName());
	        oldU.setPassWord(user.getPassWord());
	        oldU.setEmail(user.getEmail());
	        oldU.setFullName(user.getFullName());
	        oldU.setImages(user.getImages());
	        oldU.setRoleid(user.getRoleid());
	        oldU.setPhone(user.getPhone());
	        oldU.setCreatedDate(user.getCreatedDate()); // Nếu cần thiết
	        
	        userDao.update(oldU); // Gọi phương thức update để lưu thay đổi vào DB
	        System.out.println("User with ID " + user.getId() + " updated successfully");
	    } else {
	        System.out.println("User not found with ID: " + user.getId());
	    
	    }
		
	}
	
	

}
