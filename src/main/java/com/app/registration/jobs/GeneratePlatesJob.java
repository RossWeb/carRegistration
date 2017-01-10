package com.app.registration.jobs;


import com.app.registration.service.PersonService;
import com.app.registration.service.PlateService;
import org.hibernate.annotations.common.util.impl.LoggerFactory;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by konrad on 10.01.17.
 */
@Component("generatePlatesJob")
public class GeneratePlatesJob {

    private static final Logger LOG = LoggerFactory.logger(GeneratePlatesJob.class);
    private PlateService plateService;
    private PersonService personService;

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
        //todo sprawdzic czy lista nie zawiera duplikatow
        List<String> signsList = personService.getSignsList();
        signsList.forEach(s -> {
            Long signCount = plateService.getUnusedSignCount(s);
            if (signCount != 10) {
                LOG.info("Plates "+ s + " is less then mininal count - generate");
                plateService.createByCount(s, 10 - signCount);
            }else{
                LOG.info("Plates "+ s +" is max count in database");
            }
        });
    }
}
