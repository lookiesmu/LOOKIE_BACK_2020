package kr.or.connect.reservation.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import kr.or.connect.reservation.config.ApplicationConfig;
import kr.or.connect.reservation.config.SecurityConfig;
import kr.or.connect.reservation.dao.UserDao;
import kr.or.connect.reservation.dao.UserRoleDao;
import kr.or.connect.reservation.dto.User;
import kr.or.connect.reservation.dto.UserEntity;
import kr.or.connect.reservation.dto.UserRole;
import kr.or.connect.reservation.dto.UserRoleEntity;
import kr.or.connect.reservation.service.UserService;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserDao userDao;
	@Autowired
	UserRoleDao userRoleDao;
	
    @Override
    @Transactional
    public UserEntity getUser(String userId) {
        User user = userDao.getUser(userId);
        return new UserEntity(user.getEmail(), user.getPassword());
    }

    @Override
    @Transactional
    public List<UserRoleEntity> getUserRoles(String userId) {
        List<UserRole> userRoles = userRoleDao.getRole(userId);
        List<UserRoleEntity> list = new ArrayList<>();

        for(UserRole userRole : userRoles) {
            list.add(new UserRoleEntity(userId, userRole.getRoleName()));
        }
        return list;
    }
    
    @Override
    public int getId(String userId) {
    	User user = userDao.getUser(userId);
    	if(user==null) {
    		return 0;
    	}
    	return user.getId();
    }
}
