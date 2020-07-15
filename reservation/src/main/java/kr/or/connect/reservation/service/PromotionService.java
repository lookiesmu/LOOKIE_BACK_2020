package kr.or.connect.reservation.service;

import kr.or.connect.reservation.dto.Promotion;

import java.util.List;

public interface PromotionService {
    public List<Promotion> selectAll();
    public int getCount();
}
