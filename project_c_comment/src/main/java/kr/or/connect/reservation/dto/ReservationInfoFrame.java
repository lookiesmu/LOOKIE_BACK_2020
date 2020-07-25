package kr.or.connect.reservation.dto;

public class ReservationInfoFrame {
	int id;
	int productId;
	int displayInfoId;
	int cancelFlag;
	String productDescription;
	String productCountent;
	int userId;
	int sumPrice;
	String reservationDate;
	String createDate;
	String modifyDate;
	@Override
	public String toString() {
		return "ReservationInfoFrame [id=" + id + ", productId=" + productId + ", displayInfoId=" + displayInfoId
				+ ", cancelFlag=" + cancelFlag + ", productDescription=" + productDescription + ", productCountent="
				+ productCountent + ", userId=" + userId + ", sumPrice=" + sumPrice + ", reservationDate="
				+ reservationDate + ", createDate=" + createDate + ", modifyDate=" + modifyDate + "]";
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	public int getDisplayInfoId() {
		return displayInfoId;
	}
	public void setDisplayInfoId(int displayInfoId) {
		this.displayInfoId = displayInfoId;
	}
	public int getCancelFlag() {
		return cancelFlag;
	}
	public void setCancelFlag(int cancelFlag) {
		this.cancelFlag = cancelFlag;
	}
	public String getProductDescription() {
		return productDescription;
	}
	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}
	public String getProductCountent() {
		return productCountent;
	}
	public void setProductCountent(String productCountent) {
		this.productCountent = productCountent;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getSumPrice() {
		return sumPrice;
	}
	public void setSumPrice(int sumPrice) {
		this.sumPrice = sumPrice;
	}
	public String getReservationDate() {
		return reservationDate;
	}
	public void setReservationDate(String reservationDate) {
		this.reservationDate = reservationDate;
	}
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	public String getModifyDate() {
		return modifyDate;
	}
	public void setModifyDate(String modifyDate) {
		this.modifyDate = modifyDate;
	}
	
	

}
