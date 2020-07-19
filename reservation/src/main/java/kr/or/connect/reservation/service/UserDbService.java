package kr.or.connect.reservation.service;

import java.util.List;

import kr.or.connect.reservation.dto.User;
import kr.or.connect.reservation.dto.UserEntity;
import kr.or.connect.reservation.dto.UserRole;
import kr.or.connect.reservation.dto.UserRoleEntity;

public interface UserDbService {
    public UserEntity getUser(String loginUserId);
    public List<UserRoleEntity> getUserRoles(String loginUserId);
}
/*
public User getUser(String userId);
    public List<UserRole> getUserRole(String userId);
    public UserEntity getUserEntity(String userId,String password);
    public UserEntity getEntity(String userId);
 */