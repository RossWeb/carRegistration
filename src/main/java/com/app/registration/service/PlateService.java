package com.app.registration.service;

import com.app.registration.model.dto.PlateDto;

import java.util.List;
import java.util.Map;

/**
 * Created by konrad on 10.11.16.
 */
public interface PlateService {

    List<PlateDto> createByCount(String signs, Long count);
    void createIfNeeded(String signs, Long count);
    List<PlateDto> findAll();
    List<PlateDto> findUnusedBySign(int maxSize, String sign);
    Map<Long, String> getUnusedSignCount(long minCountValue, int batchSize);
    void changePlateStatus(String plateNumber);
}
