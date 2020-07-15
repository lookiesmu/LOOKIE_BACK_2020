package kr.or.connect.reservation.service;

import kr.or.connect.reservation.dto.FileInfo;

import java.util.List;

public interface FileInfoService {
    public Object selectByFileId(int fileId, Class<?> requiredType);
}
