package kr.or.connect.reservation.dto;

public class Promotion {
	private Integer id;
	private Integer productId;
	private Integer categoryId;
	private String categoryName;
	private String description;
	private Integer fileId;
	@Override
	public String toString() {
		return "Promotion [id=" + id + ", productId=" + productId + ", categoryId=" + categoryId + ", categoryName="
				+ categoryName + ", description=" + description + ", fileId=" + fileId + "]";
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getProductId() {
		return productId;
	}
	public void setProductId(Integer productId) {
		this.productId = productId;
	}
	public Integer getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Integer getFileId() {
		return fileId;
	}
	public void setFileId(Integer fileId) {
		this.fileId = fileId;
	}
	
	
	
	
	
	}

