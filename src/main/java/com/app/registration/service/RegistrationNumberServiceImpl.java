package com.app.registration.service;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by konrad on 02.01.17.
 */
@Service
public class RegistrationNumberServiceImpl implements RegistrationNumberService {

    private static final int PLATE_NUM_SIZE = 5;
    private static final int PROOF_NUM_SIZE = 9;
    private static final char PROOF_TEMP_SIGN = 'T';

    @Override
    public String generatePlateNumber(String signs) {
        return signs + RandomStringUtils.randomNumeric(PLATE_NUM_SIZE);
    }

    @Override
    public String generateProofRegistrationNumber(boolean isTemporary) {
        String proofRegistrationNumber = RandomStringUtils.randomAlphanumeric(PROOF_NUM_SIZE);
        if(isTemporary){
            proofRegistrationNumber = PROOF_TEMP_SIGN + proofRegistrationNumber;
        }else{
            while(PROOF_TEMP_SIGN == proofRegistrationNumber.charAt(0)){
                proofRegistrationNumber = RandomStringUtils.randomAlphanumeric(PROOF_NUM_SIZE);
            }
        }
        return proofRegistrationNumber;
    }
}
