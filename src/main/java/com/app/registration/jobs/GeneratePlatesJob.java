package com.app.registration.jobs;


import com.app.registration.service.PersonService;
import com.app.registration.service.PlateService;
import org.hibernate.annotations.common.util.impl.LoggerFactory;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * Created by konrad on 10.01.17.
 */
@Component("generatePlatesJob")
public class GeneratePlatesJob {

    private static final Logger LOG = LoggerFactory.logger(GeneratePlatesJob.class);
    public static final String MIN_PLATES_NUMBER = "min.plates.number";
    private static final String PLATES_JOB_BATCH_SIZE = "plates.job.batch.size";
    private PlateService plateService;
    private PersonService personService;
    private Environment environment;

    @Autowired
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }

    @Autowired
    public void setPersonService(PersonService personService) {
        this.personService = personService;
    }

    @Autowired
    public void setPlateService(PlateService plateService) {
        this.plateService = plateService;
    }

    public void execute(){
        LOG.info("Execute generate plates job");
        //todo dodac batch oraz operation lock
        long minPlatesNumber = getMinPlatesNumberFromProperty();
        int batchSize = environment.getProperty(PLATES_JOB_BATCH_SIZE, Integer.class, 10);
        Map<Long, String> platesMap = plateService.getUnusedSignCount(minPlatesNumber, batchSize);
        platesMap.forEach((plateNumber, sign) -> {
            LOG.info("Plates "+ plateNumber + " is less then minimal count - generate");
            plateService.createByCount(sign, minPlatesNumber - plateNumber);
        });
    }

    private Long getMinPlatesNumberFromProperty(){
         return environment.getProperty(MIN_PLATES_NUMBER, Long.class, 10L);
    }
}
