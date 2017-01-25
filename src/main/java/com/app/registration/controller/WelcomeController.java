package com.app.registration.controller;

import com.app.registration.model.builder.*;
import com.app.registration.model.dto.*;
import com.app.registration.service.*;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;

/**
 * Created by konrad on 21.11.16.
 */
@Controller
public class WelcomeController {


    private PersonService personService;
    private CarService carService;
    private InsuranceAgreementService insuranceAgreementService;
    private ProofRegistrationService proofRegistrationService;
    private PlateService plateService;

    @Autowired
    public void setProofRegistrationService(ProofRegistrationService proofRegistrationService) {
        this.proofRegistrationService = proofRegistrationService;
    }

    @Autowired
    public void setPlateService(PlateService plateService) {
        this.plateService = plateService;
    }

    @Autowired
    public void setInsuranceAgreementService(InsuranceAgreementService insuranceAgreementService) {
        this.insuranceAgreementService = insuranceAgreementService;
    }

    @Autowired
    public void setPersonService(PersonService personService) {
        this.personService = personService;
    }

    @Autowired
    public void setCarService(CarService carService) {
        this.carService = carService;
    }

    @RequestMapping(value = {"/debug"}, method = RequestMethod.GET)
    @ResponseBody
    public String doDebug() {
        String status = HttpStatus.OK.getReasonPhrase();;
        try{
            createDebugDataToDatabase();
        }catch (Exception e){
            status = HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase();
        }
        return status;
    }

    private void createDebugDataToDatabase(){
        for (int i = 0; i < 10; i++) {
            AddressDto addressDto = personService.createAddress(
                new AddressDtoBuilder()
                    .setAddress("Warszawa", "00-001", "Kwiatowa " + i)
                    .decorateRequest());
            PersonDto personDto1 = personService.createPerson(new PersonDtoBuilder()
                    .setPersonName("Name1", "Surname" + i)
                    .setPersonPeselAndPhone("123456789" + i, "99999999")
                    .setAddressIdAfterCreated(addressDto.getId())
                    .decorateRequest());
            PersonDto personDto2 = personService.createPerson(new PersonDtoBuilder()
                    .setPersonName("Name2", "Surname" + i)
                    .setPersonPeselAndPhone("223456789" + i, "99999999")
                    .setAddressIdAfterCreated(addressDto.getId())
                    .decorateRequest());
            CarDto carDto = carService.createCar(new CarDtoBuilder()
                    .setCarCredentials("1234567" + i, "Opel", new Date())
                    .setOwner(personDto1.getPesel())
                    .decorateRequest());
            plateService.createByCount("WA", 10L);
            InsuranceAgreementDto insuranceAgreementDto = insuranceAgreementService.create(new InsuranceDtoBuilder()
                    .setInsuranceCredentials(RandomStringUtils.randomAlphanumeric(9), "Company " + i, new Date())
                    .setInsuranceCar(carDto.getVin())
                    .setInsuranceOwner(personDto1.getPesel())
                    .addInsuranceOtherOwner(personDto2.getPesel())
                    .decorateRequest());
            ProofRegistrationDto proofRegistrationDto = proofRegistrationService.createProofRegistration(new ProofRegistrationDtoBuilder()
                    .setRegistrationMainCredentials(proofRegistrationService.getProofRegistrationNumber(true),
                            plateService.findAll().stream().findFirst().get().getPlateNumber(),
                            true)
                    .setRegistrationDateCredentials(new Date(), new Date())
                    .setOwnerAndCar(personDto1.getPesel(), carDto.getVin())
                    .addOtherOwner(personDto2.getPesel())
                    .decorateRequest());
            plateService.changePlateStatus(proofRegistrationDto.getPlateNumber());
        }


    }
}
