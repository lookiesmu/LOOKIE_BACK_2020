package kr.or.connect.reservation.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import kr.or.connect.reservation.dao.PriceDao;
import kr.or.connect.reservation.dao.ReservationInfoDao;
import kr.or.connect.reservation.dto.Price;
import kr.or.connect.reservation.dto.ReservationInfo;
import kr.or.connect.reservation.service.ReservationInfoService;

@Service
public class ReservationInfoServiceImpl implements ReservationInfoService {
	@Autowired
	ReservationInfoDao reservationInfoDao;
	@Autowired
	PriceDao priceDao;
	
	@Override
	public List<ReservationInfo> getReservationInfo(int userId){
		return reservationInfoDao.getReservationInfo(userId);
	}
	
	@Override
	public int getSize(int userId) {
		return reservationInfoDao.getSize(userId);
	}
	
	@Override
	public int postReservation(int productId, int displayInfoId, int userId) {
		return reservationInfoDao.postReservation(productId, displayInfoId, userId);
	}
	
	@Override
	public int getId(int productId, int displayInfoId, int userId) {
		return reservationInfoDao.getId(productId, displayInfoId, userId);
	}
	
	@Override
	public int postPrice(int reservationInfoId, int productPriceId, int count){
		return priceDao.postPrice(reservationInfoId, productPriceId, count);
	}
	
	@Override
	public String deleteId(int id) {
		try {
			reservationInfoDao.deletePrice(id);
			reservationInfoDao.deleteId(id);
		}
		catch(EmptyResultDataAccessException e) {
			return "fail";
		}
		return "success";
	}
}
