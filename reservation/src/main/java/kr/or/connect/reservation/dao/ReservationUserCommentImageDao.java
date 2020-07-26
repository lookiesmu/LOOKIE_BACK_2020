package kr.or.connect.reservation.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import kr.or.connect.reservation.dto.ReservationUserCommentImage;
import static kr.or.connect.reservation.dao.sqls.ReservationUserCommentImageSqls.GET_PRODUCT_ID;
import static kr.or.connect.reservation.dao.sqls.ReservationUserCommentImageSqls.POST_RESERVATION_IMAGE;
import static kr.or.connect.reservation.dao.sqls.ReservationUserCommentImageSqls.POST_FILE;
import static kr.or.connect.reservation.dao.sqls.ReservationUserCommentImageSqls.GET_RESERVATION_IMAGE;
import static kr.or.connect.reservation.dao.sqls.ReservationUserCommentImageSqls.POST_RESERVATION_COMMENT_IMAGE;
import static kr.or.connect.reservation.dao.sqls.ReservationUserCommentImageSqls.GET_FILE_ID;
import static kr.or.connect.reservation.dao.sqls.ReservationUserCommentImageSqls.GET_RESERVATION_COMMENT_ID;
import static kr.or.connect.reservation.dao.sqls.ReservationUserCommentImageSqls.GET_FILE_NAME;
import static kr.or.connect.reservation.dao.sqls.ReservationUserCommentImageSqls.GET_FILE_TYPE;

@Repository
public class ReservationUserCommentImageDao {
    private NamedParameterJdbcTemplate jdbc;
    private RowMapper<ReservationUserCommentImage> rowMapper = BeanPropertyRowMapper.newInstance(ReservationUserCommentImage.class);

    public ReservationUserCommentImageDao(DataSource dataSource){
        this.jdbc = new NamedParameterJdbcTemplate(dataSource);
    }
    
    public int getProductId(int reservationInfoId) {
    	Map<String, Integer> params = new HashMap<>();
    	params.put("reservationInfoId", reservationInfoId);
    	return jdbc.queryForObject(GET_PRODUCT_ID, params, Integer.class);
    }
    
    public int getReservationCommentId(int productId, int reservationInfoId, int userId) {
    	Map<String, Integer> params = new HashMap<>();
    	
    	params.put("productId", productId);
    	params.put("reservationInfoId", reservationInfoId);
    	params.put("userId", userId);
    	return jdbc.queryForObject(GET_RESERVATION_COMMENT_ID, params, Integer.class);
    }
    
    public int getFileId(String fileName) {
    	Map<String, String> params = new HashMap<>();
    	params.put("fileName", fileName);
    	return jdbc.queryForObject(GET_FILE_ID, params, Integer.class);
    }
    
    public String getFileName(int fileId) {
    	Map<String, Integer> params = new HashMap<>();
    	params.put("fileId", fileId);
    	return jdbc.queryForObject(GET_FILE_NAME, params, String.class);
    }
    
    public String getFileType(int fileId) {
    	Map<String, Integer> params = new HashMap<>();
    	params.put("fileId", fileId);
    	return jdbc.queryForObject(GET_FILE_TYPE, params, String.class);
    }
    
    public int postComment(int productId, int reservationInfoId, int userId, int score, String comment) {
    	Map<String, Object> params = new HashMap<>();
    	
    	params.put("productId", productId);
    	params.put("reservationInfoId", reservationInfoId);
    	params.put("userId", userId);
    	params.put("score", score);
    	params.put("comment", comment);
    	
    	return jdbc.update(POST_RESERVATION_IMAGE, params); 
    }
    
    public int postCommentImage(int reservationInfoId, int reservationUserCommentId, int fileId) {
    	Map<String, Object> params = new HashMap<>();
    	
    	params.put("reservationInfoId", reservationInfoId);
    	params.put("reservationUserCommentId", reservationUserCommentId);
    	params.put("fileId", fileId);
    	
    	return jdbc.update(POST_RESERVATION_COMMENT_IMAGE, params); 
    }
    
    public int postImage(String fileName, String saveFileName, String contentType, int deleteFlag) {
    	Map<String, Object> params = new HashMap<>();
    	
    	params.put("fileName", fileName);
    	params.put("saveFileName", saveFileName);
    	params.put("contentType", contentType);
    	params.put("deleteFlag", deleteFlag);
    	
    	return jdbc.update(POST_FILE, params); 
    }
    
    public List<ReservationUserCommentImage> getReservationImage(int reservationInfoId){
    	Map<String, Integer> params = new HashMap<>();
    	params.put("reservationInfoId", reservationInfoId);
    	if(jdbc.query(GET_RESERVATION_IMAGE, params, rowMapper)==null) {
    		return null;
    	}
    	return jdbc.query(GET_RESERVATION_IMAGE, params, rowMapper);
    }
}
