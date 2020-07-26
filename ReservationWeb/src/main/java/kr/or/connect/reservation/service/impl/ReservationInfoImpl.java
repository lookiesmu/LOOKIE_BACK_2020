package kr.or.connect.reservation.service.impl;

import java.util.*;

import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

import kr.or.connect.reservation.dao.*;
import kr.or.connect.reservation.dto.*;
import kr.or.connect.reservation.service.*;

@Service
public class ReservationInfoImpl implements ReservationInfoService{
	@Autowired
	ReservationInfoDao reservationInfoDao;
	@Autowired
	ReservedPriceDao reservedPriceDao;
	@Autowired
	CommentsDao commentsDao;
	@Autowired
	CommentImageDao commentImageDao;
	@Autowired
	FileInfoDao fileInfoDao;
	
	@Override
	public List<ReservationsDto> getReservations(String email) {
		// TODO Auto-generated method stub
		List<ReservationsDto> list=reservationInfoDao.selectReservationInfoByEmail(email);
		
		return list;
	}


	@Override
	public List<totalPriceDto> getTotalPrice(int reservationInfoId) {
		// TODO Auto-generated method stub
		List<totalPriceDto> totalPrice=reservationInfoDao.getTotalPriceById(reservationInfoId);
		
		return totalPrice;
	}


	@Override
	public int insertReservation(ReservationsDto reservation) {
		// TODO Auto-generated method stub
		
		
		return reservationInfoDao.insertReservation(reservation);
	}


	@Override
	public List<ReservationsDto> getReservationId(String email) {
		// TODO Auto-generated method stub
		return reservationInfoDao.getReservationId(email);
	}
	
	@Override
	public List<PricesDto> getReservationPricesByID(int reservationInfoId) {
		// TODO Auto-generated method stub
		return reservedPriceDao.getReservationPrices(reservationInfoId);
	}
	


	@Override
	public int insertReservedPrices(PricesDto prices) {
		// TODO Auto-generated method stub
		return reservedPriceDao.insertReservedPrices(prices);
	}


	@Override
	public List<ReservationsDto> getReservationById(int reservationInfoId) {
		// TODO Auto-generated method stub
		
		return reservationInfoDao.getReservationById(reservationInfoId);
	}


	@Override
	public int cancelRerservation(int reservationInfoId) {
		// TODO Auto-generated method stub
		return reservationInfoDao.cancelReservationById(reservationInfoId);
	}


	@Override
	public int deleteReservationPrices(int reservationInfoId) {
		// TODO Auto-generated method stub
		return reservedPriceDao.deleteReservationById(reservationInfoId);
	}


	@Override
	public int insertComment(CommentsDto comment) {
		// TODO Auto-generated method stub
		
		
		return commentsDao.insertComment(comment);
	}


	@Override
	public int insertCommentImageInfo(CommentImageDto commentImage) {
		// TODO Auto-generated method stub
		return commentImageDao.insertCommentImage(commentImage);
	}


	@Override
	public int insertImageFileInfo(FileInfoDto fileInfo) {
		// TODO Auto-generated method stub
		return fileInfoDao.insertImageFileInfo(fileInfo);
	}
	

}
