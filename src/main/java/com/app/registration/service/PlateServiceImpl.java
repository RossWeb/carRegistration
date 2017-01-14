package com.app.registration.service;

import com.app.registration.model.PlateEntity;
import com.app.registration.model.dto.PlateDto;
import com.app.registration.repository.CriteriaFilter.PlateCriteriaFilter;
import com.app.registration.repository.PlateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by konrad on 04.01.17.
 */
@Service
@Transactional(transactionManager = "platformTransactionManager", propagation = Propagation.REQUIRES_NEW)
public class PlateServiceImpl implements PlateService {

    private PlateRepository plateRepository;
    private RegistrationNumberService registrationNumberService;

    @Autowired
    public void setRegistrationNumberService(RegistrationNumberService registrationNumberService) {
        this.registrationNumberService = registrationNumberService;
    }

    @Autowired
    public void setPlateRepository(PlateRepository plateRepository) {
        this.plateRepository = plateRepository;
    }

    @Override
    public List<PlateDto> createByCount(String signs, Long count) {
        List<PlateDto> plateDtos = new ArrayList<>();
        for (int i = 0; i < count ; i++) {
            PlateEntity plateEntity = new PlateEntity();
            plateEntity.setSigns(signs);
            plateEntity.setUsed(false);
            plateEntity.setPlateNumber(registrationNumberService.generatePlateNumber(signs));
            plateDtos.add(convertEntityToDto(plateRepository.create(plateEntity)));
        }
        return plateDtos;
    }

    @Override
    public void createIfNeeded(String signs, Long count) {
        if(plateRepository.isExistedBySign(signs)){
            createByCount(signs, count);
        }
    }

    @Override
    public List<PlateDto> findAll() {
        return plateRepository.findAll().stream().map(PlateServiceImpl::convertEntityToDto).collect(Collectors.toList());
    }

    @Override
    public List<PlateDto> findUnusedBySign(int maxSize, String sign) {
        PlateCriteriaFilter plateCriteriaFilter = new PlateCriteriaFilter();
        plateCriteriaFilter.setSign(sign);
        plateCriteriaFilter.setUsed(false);
        plateCriteriaFilter.setMaxResults(maxSize);
        return plateRepository.find(plateCriteriaFilter).stream().map(PlateServiceImpl::convertEntityToDto).collect(Collectors.toList());
    }

    @Override
    public Map<Long, String> getUnusedSignCount(long minCountValue, int batchSize) {
        Map<Long, String> plateMap = new HashMap<>();
        plateRepository.getUnusedSignCount(minCountValue, batchSize).forEach(o -> {
            plateMap.put((Long)o[0], (String)o[1]);
        });
        return plateMap;
    }

    @Override
    public void changePlateStatus(String plateNumber) {
        plateRepository.changePlateStatus(plateNumber);
    }

    private static PlateDto convertEntityToDto(PlateEntity plateEntity){
        PlateDto plateDto = new PlateDto();
        plateDto.setSigns(plateEntity.getSigns());
        plateDto.setPlateNumber(plateEntity.getPlateNumber());
        plateDto.setUsed(plateEntity.isUsed());
        return plateDto;
    }
}
