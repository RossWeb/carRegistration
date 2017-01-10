package com.app.registration.service;

import com.app.registration.model.dto.PlateDto;

import java.util.List;

/**
 * Created by konrad on 10.11.16.
 */
public interface PlateService {

    List<PlateDto> createByCount(String signs, Long count);
    List<PlateDto> findAll();
    List<PlateDto> findUnusedBySign(int maxSize, String sign);
    Long getUnusedSignCount(String sign);
}
