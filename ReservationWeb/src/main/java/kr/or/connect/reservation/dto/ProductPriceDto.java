package kr.or.connect.reservation.dto;

import java.util.*;

import com.fasterxml.jackson.annotation.*;

public class ProductPriceDto {
	private int productPriceId;
	private int productId;
	private String priceTypeName;
	private int price;
	private int discountRate;
	
	@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS", timezone="Asia/Seoul")
	private Date createDate;
	@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS", timezone="Asia/Seoul")
	private Date modifyDate;
	
	
	@Override
	public String toString() {
		return "ProductPriceDto [productPriceId=" + productPriceId + ", productId=" + productId + ", priceTypeName="
				+ priceTypeName + ", price=" + price + ", discountRate=" + discountRate + ", createDate=" + createDate
				+ ", modifyDate=" + modifyDate + "]";
	}
	
	public int getProductPriceId() {
		
		
		return productPriceId;
	}
	public void setId(int productPriceId) { //sql문 응답을 받아 dao에서 jdbc 의 rowMapper가 처리할때 set+"" 부분의 이름을 DB와 비교하고 매칭함.
		this.productPriceId = productPriceId;
	}
	public int getProductId() {
		return productId;
	}
	public void setProduct_Id(int productId) {
		this.productId = productId;
	}
	public String getPriceTypeName() {
		return priceTypeName;
	}
	public void setPrice_Type_Name(String priceTypeName) {
		this.priceTypeName = priceTypeName;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getDiscountRate() {
		return discountRate;
	}
	public void setDiscountRate(int discountRate) {
		this.discountRate = discountRate;
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
