package kr.or.connect.reservation.service;

import java.util.List;

import kr.or.connect.reservation.dto.ReservationUserCommentImage;

public interface ReservationUserCommentImageService {
	public int getProductId(int reservationInfoId);
	public int postComment(int productId, int reservationInfoId, int userId, int score, String comment);
	public int postImage(String fileName, String saveFileName, String contentType, int deleteFlag);
	public List<ReservationUserCommentImage> getReservationImage(int reservationInfoId);
	public int postCommentImage(int reservationInfoId, int reservationUserCommentId, int fileId);
	public int getReservationCommentId(int productId, int reservationInfoId, int userId);
	public int getFileId(String fileName);
	public String getFileName(int fileId);
	public String getFileType(int fileId);
}
