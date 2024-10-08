package vn.iotstar.models;

import java.io.Serializable;

public class CategoryModel implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private int cateid;
	private String catename;
	private String icon;
	private int status;
	public CategoryModel() {
		super();
	}
	public CategoryModel(int cateid, String catename, String icon,int status) {
		super();
		this.cateid = cateid;
		this.catename = catename;
		this.icon = icon;
		this.status = status;
	}
	public int getCateid() {
		return cateid;
	}
	public void setCateid(int cateid) {
		this.cateid = cateid;
	}
	public String getCatename() {
		return catename;
	}
	public void setCatename(String catename) {
		this.catename = catename;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return "CategoryModel [cateid=" + cateid + ", catename=" + catename + ", icon=" + icon +", status=" +status + "]";
	}
   
}
