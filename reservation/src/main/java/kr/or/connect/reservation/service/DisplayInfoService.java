package kr.or.connect.reservation.service;

import kr.or.connect.reservation.dto.DisplayInfo;

import java.util.List;

public interface DisplayInfoService {
    public int getTotalCount();
    public int getCountByCategoryId(int categoryId);
    public List<DisplayInfo> selectDisplayInfos(int start, int categoryId);
    public DisplayInfo selectDisplayInfoById(int displayId);
}
