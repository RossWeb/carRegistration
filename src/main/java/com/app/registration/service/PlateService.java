package com.app.registration.service;

import com.app.registration.model.dto.PlateDto;

import java.util.List;

/**
 * Created by konrad on 10.11.16.
 */
public interface PlateService {

    List<PlateDto> createByCount(String city, int count);
    List<PlateDto> findAll();
    List<PlateDto> findUnusedByCity(int maxSize, String city);
}
