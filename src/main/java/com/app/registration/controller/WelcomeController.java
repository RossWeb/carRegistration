package com.app.registration.controller;

import com.app.registration.controller.request.*;
import com.app.registration.model.dto.*;
import com.app.registration.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

    @RequestMapping(value = {"/", "/start"}, method = RequestMethod.GET)
    public ModelAndView doWelcome() {
        final ModelMap model = new ModelMap();
        return new ModelAndView("pages/welcome", model);
    }

    @RequestMapping(value = {"/debug"}, method = RequestMethod.GET)
    @ResponseBody
    public String doDebug() {
        String status = HttpStatus.OK.getReasonPhrase();;
        try{
            createDataToDatabase();
        }catch (Exception e){
            status = HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase();
        }
        return status;
    }

    private void createDataToDatabase(){
        AddressRequest addressRequest = new AddressRequest();
        AddressDto addressDto = new AddressDto();
        addressDto.setCity("Warszawa");
        addressDto.setPostCode("00-001");
        addressDto.setStreet("Kwiatowa 1");
        addressRequest.setAddressDto(addressDto);
        addressDto = personService.createAddress(addressRequest);
        PersonRequest personRequest = new PersonRequest();
        PersonDto personDto = new PersonDto();
        personDto.setSurname("Surname");
        personDto.setName("Name");
        personDto.setPesel("12345678910");
        personDto.setPhoneNumber("999999999");
        personDto.setAddressId(addressDto.getId());
        personRequest.setPersonDto(personDto);
        PersonRequest personRequest2 = new PersonRequest();
        PersonDto personDto2 = new PersonDto();
        personDto2.setSurname("Surname2");
        personDto2.setName("Name2");
        personDto2.setPesel("22345678910");
        personDto2.setPhoneNumber("299999999");
        personDto2.setAddressId(addressDto.getId());
        personRequest2.setPersonDto(personDto2);
        personService.createPerson(personRequest);
        personService.createPerson(personRequest2);
        CarRequest carRequest = new CarRequest();
        CarDto carDto = new CarDto();
        carDto.setVin("123456789");
        carDto.setOwnerPesel(personDto.getPesel());
        carDto.setName("Opel");
        carDto.setProductionDate(new Date());
        carRequest.setCarDto(carDto);
        carService.createCar(carRequest);
        InsuranceRequest insuranceRequest = new InsuranceRequest();
        InsuranceAgreementDto insuranceAgreementDto = new InsuranceAgreementDto();
        insuranceAgreementDto.setPurchaseDate(new Date());
        insuranceAgreementDto.setInsuranceCompanyName("Company One");
        insuranceAgreementDto.setInsuranceNumber("1XADFS342");
        insuranceAgreementDto.setBuyerPesel(personDto.getPesel());
        insuranceAgreementDto.setCarVin(carDto.getVin());
        List<String> otherPersonList = new ArrayList<>();
        otherPersonList.add(personDto2.getPesel());
        insuranceAgreementDto.setOtherOwnerId(otherPersonList);
        insuranceRequest.setInsuranceAgreementDto(insuranceAgreementDto);
        plateService.createByCount("WA", 5L);
        insuranceAgreementService.create(insuranceRequest);
        ProofRegistrationRequest proofRegistrationRequest = new ProofRegistrationRequest();
        ProofRegistrationDto proofRegistrationDto = new ProofRegistrationDto();
        proofRegistrationDto.setPlateNumber(plateService.findAll().stream().findFirst().get().getPlateNumber());
        proofRegistrationDto.setOtherOwnerPesels(otherPersonList);
        proofRegistrationDto.setMainOwnerPesel(personDto.getPesel());
        proofRegistrationDto.setFirstRegistrationDate(new Date());
        proofRegistrationDto.setCarVin(carDto.getVin());
        proofRegistrationDto.setNumberCardVehicle(proofRegistrationService.getProofRegistrationNumber(true));
        proofRegistrationDto.setRegistrationDate(new Date());
        proofRegistrationDto.setFirstRegistrationDate(new Date());
        proofRegistrationDto.setTemporaryProof(true);
        proofRegistrationRequest.setProofRegistrationDto(proofRegistrationDto);
        proofRegistrationService.createProofRegistration(proofRegistrationRequest);
        plateService.changePlateStatus(proofRegistrationDto.getPlateNumber());
    }
}
