package kr.or.connect.reservation.dto;

import java.util.*;

import com.fasterxml.jackson.annotation.*;

public class ReservationsDto {
	private int reservationInfoId;
	private int productId;
	private int displayInfoId;
	private String reservationName;
	private String reservationTel;
	private String reservationEmail;
	private int cancelFlag;
	
	@JsonFormat(pattern = "yyyy-MM-dd", timezone="Asia/Seoul")
	private Date reservationDate;
	
	@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS", timezone="Asia/Seoul")
	private Date createDate;
	@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS", timezone="Asia/Seoul")
	private Date modifyDate;
	
	private DisplayInfoDto displayInfo;
	
	private int totalPrice;
	
	@Override
	public String toString() {
		return "ReservationsDto [reservationInfoId=" + reservationInfoId + ", productId=" + productId
				+ ", displayInfoId=" + displayInfoId + ", reservationName=" + reservationName
				+ ", reservationTelephone=" + reservationTel + ", reservationEmail=" + reservationEmail
				+ ", cancelYn=" + cancelFlag + ", reservationDate=" + reservationDate + ", createDate=" + createDate
				+ ", modifyDate=" + modifyDate + ", displayInfo=" + displayInfo + ", totalPrice=" + totalPrice + "]";
	}

	public int getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(int totalPrice) {
		this.totalPrice = totalPrice;
	}

	public DisplayInfoDto getDisplayInfo() {
		return displayInfo;
	}

	public void setDisplayInfo(DisplayInfoDto displayInfo) {
		this.displayInfo = displayInfo;
	}
	
	public int getReservationInfoId() {
		return reservationInfoId;
	}
	public void setId(int reservationInfoId) {
		this.reservationInfoId = reservationInfoId;
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
	public String getReservationName() {
		return reservationName;
	}
	public void setReservationName(String reservationName) {
		this.reservationName = reservationName;
	}
	public String getReservationTel() {
		return reservationTel;
	}
	public void setReservationTel(String reservationTel) {
		this.reservationTel = reservationTel;
	}
	public String getReservationEmail() {
		return reservationEmail;
	}
	public void setReservationEmail(String reservationEmail) {
		this.reservationEmail = reservationEmail;
	}
	public int getCancelFlag() {
		return cancelFlag;
	}
	public void setCancelFlag(int cancelFlag) {
		this.cancelFlag = cancelFlag;
	}
	public Date getReservationDate() {
		return reservationDate;
	}
	public void setReservationDate(Date reservationDate) {
		this.reservationDate = reservationDate;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public Date getModifyDate() {
		return modifyDate;
	}
	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}
	
	
}
