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


	

}
