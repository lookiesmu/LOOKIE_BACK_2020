package kr.or.connect.reservation.service;

import java.util.Date;
import java.util.List;

import kr.or.connect.reservation.dto.Price;
import kr.or.connect.reservation.dto.ReservationInfo;

public interface ReservationInfoService {
	public List<ReservationInfo> getReservationInfo(int userId);
	public int getSize(int userId);
	public int postReservation(int productId, int displayInfoId, int userId);
	public int postPrice(int reservationInfoId, int productPriceId, int count);
	public int getId(int productId, int displayInfoId, int userId);
	public String deleteId(int id);
}
