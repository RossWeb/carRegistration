package com.app.registration.service;

/**
 * Created by konrad on 10.11.16.
 */
interface RegistrationNumberService {

    String generatePlateNumber(String signs);
    String generateProofRegistrationNumber(boolean isTemporary);
}
