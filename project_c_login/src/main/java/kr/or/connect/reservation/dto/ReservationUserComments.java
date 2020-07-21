package kr.or.connect.reservation.dto;

public class ReservationUserComments {
	private Integer id;
	private Integer productId;
	private Integer reservationInfoId;
	private Integer score;
	private String reservationEmail;
	private String comment;
	private String createDate;
	private String modifyDate;
	private ReservationUserCommentImages reservationUserCommentImages;
	@Override
	public String toString() {
		return "ReservationUserComments [id=" + id + ", productId=" + productId + ", reservationInfoId="
				+ reservationInfoId + ", score=" + score + ", reservationEmail=" + reservationEmail + ", comment="
				+ comment + ", createDate=" + createDate + ", modifyDate=" + modifyDate
				+ ", reservationUserCommentImages=" + reservationUserCommentImages + "]";
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
	public Integer getReservationInfoId() {
		return reservationInfoId;
	}
	public void setReservationInfoId(Integer reservationInfoId) {
		this.reservationInfoId = reservationInfoId;
	}
	public Integer getScore() {
		return score;
	}
	public void setScore(Integer score) {
		this.score = score;
	}
	public String getReservationEmail() {
		return reservationEmail;
	}
	public void setReservationEmail(String reservationEmail) {
		this.reservationEmail = reservationEmail;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
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
	public ReservationUserCommentImages getReservationUserCommentImages() {
		return reservationUserCommentImages;
	}
	public void setReservationUserCommentImages(ReservationUserCommentImages reservationUserCommentImages) {
		this.reservationUserCommentImages = reservationUserCommentImages;
	}
	
}	
