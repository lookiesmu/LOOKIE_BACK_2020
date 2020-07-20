package kr.or.connect.reservation.service;

import java.util.*;

import kr.or.connect.reservation.dto.*;

public interface ReservationInfoService {
	
	public List <ReservationsDto> getReservations(String email);
	public List<totalPriceDto> getTotalPrice(int reservationInfoId);
	public List<ReservationsDto> getReservationId(String email);
	public List<ReservationsDto> getReservationById(int reservationInfoId);
	public List<PricesDto> getReservationPricesByID(int reservationInfoId);
	public int insertReservation(ReservationsDto reservation);
	public int insertReservedPrices(PricesDto prices);
	public int cancelRerservation(int reservationInfoId);
	public int deleteReservationPrices(int reservationInfoId);
	

}
