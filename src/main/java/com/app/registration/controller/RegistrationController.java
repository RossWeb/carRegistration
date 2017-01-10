package com.app.registration.controller;

import com.app.registration.controller.request.ProofRegistrationRequest;
import com.app.registration.controller.request.ProofRegistrationTemplateDataRequest;
import com.app.registration.controller.response.ProofRegistrationResponse;
import com.app.registration.controller.response.ProofRegistrationTemplateDataResponse;
import com.app.registration.controller.response.ProofRegistrationsResponse;
import com.app.registration.model.dto.PlateDto;
import com.app.registration.service.PersonService;
import com.app.registration.service.PlateService;
import com.app.registration.service.ProofRegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.stream.Collectors;

/**
 * Created by User on 2016-11-05.
 */
@Controller
@RequestMapping(value = "/registration")
public class RegistrationController {

    private final int PLATE_COUNT = 10;

    private ProofRegistrationService proofRegistrationService;
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

    @Autowired
    public void setProofRegistrationService(ProofRegistrationService proofRegistrationService) {
        this.proofRegistrationService = proofRegistrationService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView doRegistration() {
        final ModelMap model = new ModelMap();
        return new ModelAndView("pages/registrationMain", model);
    }

    @RequestMapping(value = "/new", method = RequestMethod.POST)
    @ResponseBody
    public ProofRegistrationResponse newRegistration(@RequestBody ProofRegistrationRequest proofRegistrationRequest) {
        ProofRegistrationResponse proofRegistrationResponse = new ProofRegistrationResponse();
        proofRegistrationResponse.setProofRegistrationDto(proofRegistrationService.createProofRegistration(proofRegistrationRequest));
        return proofRegistrationResponse;
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    @ResponseBody
    public ProofRegistrationResponse editRegistration(@RequestBody ProofRegistrationRequest proofRegistrationRequest) {
        ProofRegistrationResponse proofRegistrationResponse = new ProofRegistrationResponse();
        proofRegistrationResponse.setProofRegistrationDto(proofRegistrationService.update(proofRegistrationRequest));
        return proofRegistrationResponse;
    }

    @RequestMapping(value = "/getRegistrationTemplateData", method = RequestMethod.POST)
    @ResponseBody
    public ProofRegistrationTemplateDataResponse getCardVehicleNumber(@RequestBody ProofRegistrationTemplateDataRequest registrationTemplateDataRequest) {
        ProofRegistrationTemplateDataResponse response = new ProofRegistrationTemplateDataResponse();
        String city = personService.getCityPersonByPesel(registrationTemplateDataRequest.getPesel());
        response.setCardVehicleNumber(proofRegistrationService.getProofRegistrationNumber(true));
        response.setPlateNumbers(plateService.findUnusedByCity(PLATE_COUNT,city).stream().map(PlateDto::getPlateNumber).collect(Collectors.toList()));
        return response;
    }

    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    @ResponseBody
    public ProofRegistrationsResponse getAllRegistrations() {
        ProofRegistrationsResponse proofRegistrationsResponse = new ProofRegistrationsResponse();
        proofRegistrationsResponse.setProofRegistrationList(proofRegistrationService.getAllProofRegistrations());
        return proofRegistrationsResponse;
    }

    @RequestMapping(value = "/getRegistrationsCount", method = RequestMethod.GET)
    @ResponseBody
    public ProofRegistrationsResponse getTemporaryRegistrationsCount() {
        ProofRegistrationsResponse proofRegistrationsResponse = new ProofRegistrationsResponse();
        proofRegistrationsResponse.setProofRegistrationsCount(proofRegistrationService.getRegistrationsCount());
        proofRegistrationsResponse.setTemporaryProofRegistrationsCount(proofRegistrationService.getTemporaryRegistrationsCount());
        return proofRegistrationsResponse;
    }

    @RequestMapping(value = "/search/{cardVehicleNumber}", method = RequestMethod.GET)
    @ResponseBody
    public ProofRegistrationResponse searchByCardVehicleRegistration(@PathVariable("cardVehicleNumber") String cardVehicleNumber) {
        ProofRegistrationResponse proofRegistrationResponse = new ProofRegistrationResponse();
        proofRegistrationResponse.setProofRegistrationDto(proofRegistrationService.searchByCardVehicleNumber(cardVehicleNumber));
        return proofRegistrationResponse;
    }

    @RequestMapping(value = "/delete/{cardVehicleNumber}", method = RequestMethod.GET)
    @ResponseBody
    public void deleteRegistration(@PathVariable("cardVehicleNumber") String cardVehicleNumber) {
        proofRegistrationService.removeProofRegistration(cardVehicleNumber);
    }

    @RequestMapping(value = "/finalize", method = RequestMethod.POST)
    @ResponseBody
    public ModelAndView doWelcome() {
        final ModelMap model = new ModelMap();
        return new ModelAndView("pages/welcome", model);
    }
}
