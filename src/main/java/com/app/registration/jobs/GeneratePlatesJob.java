package com.app.registration.jobs;


import com.app.registration.service.PersonService;
import com.app.registration.service.PlateService;
import org.hibernate.annotations.common.util.impl.LoggerFactory;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by konrad on 10.01.17.
 */
@Component("generatePlatesJob")
public class GeneratePlatesJob {

    private static final Logger LOG = LoggerFactory.logger(GeneratePlatesJob.class);
    private static final String MIN_PLATES_NUMBER = "min.plates.number";
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
        //todo dodac batch
        List<String> signsList = personService.getSignsList();
        Long minPlatesNumber = getMinPlatesNumberFromProperty();
        signsList.forEach(s -> {
            Long signCount = plateService.getUnusedSignCount(s);
            if (signCount < minPlatesNumber) {
                LOG.info("Plates "+ s + " is less then minimal count - generate");
                plateService.createByCount(s, minPlatesNumber - signCount);
            }else{
                LOG.info("Plates "+ s +" is max count in database");
            }
        });
    }

    private Long getMinPlatesNumberFromProperty(){
         return environment.getProperty(MIN_PLATES_NUMBER, Long.class, 10L);
    }
}
