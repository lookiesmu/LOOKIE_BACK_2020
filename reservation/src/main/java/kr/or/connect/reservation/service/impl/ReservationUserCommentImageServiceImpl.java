package kr.or.connect.reservation.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.or.connect.reservation.dao.ReservationUserCommentImageDao;
import kr.or.connect.reservation.dto.ReservationUserCommentImage;
import kr.or.connect.reservation.service.ReservationUserCommentImageService;

@Service
public class ReservationUserCommentImageServiceImpl implements ReservationUserCommentImageService {
	@Autowired
	ReservationUserCommentImageDao reservationUserCommentImageDao;
	
	@Override
	public int getProductId(int reservationInfoId) {
		return reservationUserCommentImageDao.getProductId(reservationInfoId);
	}
	
	@Override
	@Transactional
	public int postComment(int productId, int reservationInfoId, int userId, int score, String comment) {
		return reservationUserCommentImageDao.postComment(productId, reservationInfoId, userId, score, comment);
	}
	
	@Override
	@Transactional
	public int postImage(String fileName, String saveFileName, String contentType, int deleteFlag) {
		return reservationUserCommentImageDao.postImage(fileName, saveFileName, contentType, deleteFlag);
	}
	
	@Override
	@Transactional
	public int postCommentImage(int reservationInfoId, int reservationUserCommentId, int fileId) {
		return reservationUserCommentImageDao.postCommentImage(reservationInfoId, reservationUserCommentId, fileId);
	}
	
	@Override
	public List<ReservationUserCommentImage> getReservationImage(int reservationInfoId){
		return reservationUserCommentImageDao.getReservationImage(reservationInfoId);
	}
	
	@Override
	public int getReservationCommentId(int productId, int reservationInfoId, int userId) {
		return reservationUserCommentImageDao.getReservationCommentId(productId, reservationInfoId, userId);
	}
	
	@Override
	public int getFileId(String fileName) {
		return reservationUserCommentImageDao.getFileId(fileName);
	}
	
	@Override
	public String getFileName(int fileId) {
		return reservationUserCommentImageDao.getFileName(fileId);
	}
	
	@Override
	public String getFileType(int fileId) {
		return reservationUserCommentImageDao.getFileType(fileId);
	}
}
