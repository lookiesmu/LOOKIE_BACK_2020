package kr.or.connect.reservation.service;

import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;

import kr.or.connect.reservation.dto.User;
import kr.or.connect.reservation.dto.UserEntity;
import kr.or.connect.reservation.dto.UserRole;

public interface UserService extends UserDbService{
	public int getId(String userId);
}
