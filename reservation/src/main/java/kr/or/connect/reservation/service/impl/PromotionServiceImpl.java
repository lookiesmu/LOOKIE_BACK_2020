package kr.or.connect.reservation.service.impl;

import kr.or.connect.reservation.dao.PromotionDao;
import kr.or.connect.reservation.dto.Promotion;
import kr.or.connect.reservation.service.PromotionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PromotionServiceImpl implements PromotionService {
    @Autowired
    PromotionDao promotionDao;

    @Override
    @Transactional
    public List<Promotion> selectAll() {
        return promotionDao.selectAll();
    }

    @Override
    @Transactional
    public int getCount() {
        return promotionDao.getCount();
    }
}
