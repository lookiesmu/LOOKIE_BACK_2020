package kr.or.connect.reservation.service.impl;

import kr.or.connect.reservation.dao.FileInfoDao;
import kr.or.connect.reservation.dto.FileInfo;
import kr.or.connect.reservation.service.FileInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FileInfoServiceImpl implements FileInfoService {
    @Autowired
    FileInfoDao fileInfoDao;

    @Override
    public Object selectByFileId(int fileId, Class<?> requiredType) {
        return fileInfoDao.selectByFileId(fileId, requiredType);
    }
}
