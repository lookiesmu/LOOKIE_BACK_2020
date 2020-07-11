package kr.or.connect.reservation.service;

import kr.or.connect.reservation.dto.DisplayInfoImage;

import java.util.List;

public interface DisplayInfoImageService {
    public List<DisplayInfoImage> selectDisplayInfoImagesByDisplayInfoId(int displayInfoId);
}
