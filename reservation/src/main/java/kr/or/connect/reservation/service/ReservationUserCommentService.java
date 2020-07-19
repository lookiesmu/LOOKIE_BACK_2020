package kr.or.connect.reservation.service;

import kr.or.connect.reservation.dto.ReservationUserComment;

import java.util.List;

public interface ReservationUserCommentService {
	public static final Integer LIMIT = 5;
    public int getScore(int productId);
    public int getTotalCount();
    public List<ReservationUserComment> getComment(int start);
    public List<ReservationUserComment> getCommentByProductId(int productId,int start);
}
