package kr.or.connect.reservation.service;

import java.util.List;

import kr.or.connect.reservation.service.security.UserDbService;
import kr.or.connect.reservation.service.security.UserEntity;
import kr.or.connect.reservation.service.security.UserRoleEntity;

public interface MemberService extends UserDbService {
	public UserEntity getUser(String loginUserId);
	public Long getUserIdNum(String loginUserId);
	public List<UserRoleEntity> getUserRoles(String loginUserId);
}
