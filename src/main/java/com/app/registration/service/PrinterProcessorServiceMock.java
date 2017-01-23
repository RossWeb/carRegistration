package com.app.registration.service;

import org.springframework.stereotype.Service;

/**
 * Created by konrad on 23.01.17.
 */
@Service
public class PrinterProcessorServiceMock implements PrinterProcessorService {

    @Override
    public boolean print() {
        return true;
    }
}
