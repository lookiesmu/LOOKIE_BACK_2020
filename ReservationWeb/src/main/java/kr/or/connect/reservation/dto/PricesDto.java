package kr.or.connect.reservation.dto;

public class PricesDto {
	private int reservationInfoPriceId;
	private int reservationInfoId;
	private int productPriceId;
	private int count;
	
	
	public int getReservationInfoPriceId() {
		return reservationInfoPriceId;
	}
	public void setId(int reservationInfoPriceId) {
		this.reservationInfoPriceId = reservationInfoPriceId;
	}
	public int getReservationInfoId() {
		return reservationInfoId;
	}
	public void setReservationInfoId(int reservationInfoId) {
		this.reservationInfoId = reservationInfoId;
	}
	public int getProductPriceId() {
		return productPriceId;
	}
	public void setProductPriceId(int productPriceId) {
		this.productPriceId = productPriceId;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	

}
