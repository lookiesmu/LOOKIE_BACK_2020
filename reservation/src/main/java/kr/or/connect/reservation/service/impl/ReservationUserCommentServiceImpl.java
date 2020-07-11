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

    private List<String> getUserCommentImages(ReservationUserComment comment){
        int reservationUserCommentId = comment.getId();
        List<String> UserCommentImageFileNamaList = reservationUserCommentDao.getUserCommentImagesByCommentId(reservationUserCommentId);

        return UserCommentImageFileNamaList;
    }
    
    @Override
    public List<ReservationUserComment> selectAll(int start) {
        List<ReservationUserComment> comments = reservationUserCommentDao.selectAll(start);

        for (ReservationUserComment comment : comments){
            List<String> commentImages = getUserCommentImages(comment);
            comment.setReservationUserCommentImages(commentImages);
        }
        return comments;
    }

    @Override
    public List<ReservationUserComment> selectByProductId(int productId,int start) {

        List<ReservationUserComment> comments = reservationUserCommentDao.selectByProductId(productId, start);

        for (ReservationUserComment comment : comments){
            List<String> commentImages = getUserCommentImages(comment);
            comment.setReservationUserCommentImages(commentImages);
        }
        return comments;
    }

    public int getScore(int productId){
        return reservationUserCommentDao.getScore(productId);
    }
}
