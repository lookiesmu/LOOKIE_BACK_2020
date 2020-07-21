package kr.or.connect.reservation.service.impl;

import kr.or.connect.reservation.dao.DisplayInfoImageDao;
import kr.or.connect.reservation.dto.DisplayInfoImage;
import kr.or.connect.reservation.service.DisplayInfoImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class DisplayInfoImageServiceImpl implements DisplayInfoImageService {
    @Autowired
    DisplayInfoImageDao displayInfoImageDao;

    @Override
    public DisplayInfoImage getDisplayInfoImagesByDisplayInfoId(int displayInfoId) {
        return displayInfoImageDao.getDisplayInfoImageInfosByDisplayInfoId(displayInfoId);
    }
}
