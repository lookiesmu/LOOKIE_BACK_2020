package kr.or.connect.reservation.service;

import kr.or.connect.reservation.dto.Category;

import java.util.List;

public interface CategoryService {
    public List<Category> getCategory();
    public int getCount();
}
