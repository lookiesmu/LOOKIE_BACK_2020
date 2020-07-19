package kr.or.connect.reservation.service.impl;

import kr.or.connect.reservation.dao.ReservationUserCommentDao;
import kr.or.connect.reservation.dto.ReservationUserComment;
import kr.or.connect.reservation.service.ReservationUserCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ReservationUserCommentServiceImpl implements ReservationUserCommentService {
    @Autowired
    ReservationUserCommentDao reservationUserCommentDao;

    @Override
    public int getTotalCount() {
        return reservationUserCommentDao.getTotalCount();
    }
    
    @Override
    public List<ReservationUserComment> getComment(int start) {
        List<ReservationUserComment> comments = reservationUserCommentDao.getComment(start, ReservationUserCommentService.LIMIT);

        return comments;
    }

    @Override
    public List<ReservationUserComment> getCommentByProductId(int productId,int start) {

        List<ReservationUserComment> comments = reservationUserCommentDao.getCommentByProductId(productId, start, ReservationUserCommentService.LIMIT);

        return comments;
    }

    public int getScore(int productId){
        return reservationUserCommentDao.getScore(productId);
    }
}
