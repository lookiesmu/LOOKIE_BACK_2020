package kr.or.connect.reservation.service.impl;

import kr.or.connect.reservation.dao.CategoryDao;
import kr.or.connect.reservation.dto.Category;
import kr.or.connect.reservation.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CategoryImpl implements CategoryService {
    @Autowired
    CategoryDao categoryDao;

    @Override
    @Transactional
    public int getCount() {
        return categoryDao.getCount();
    }

    @Override
    @Transactional
    public List<Category> getCategory() {
        return categoryDao.getCategory();
    }
}
