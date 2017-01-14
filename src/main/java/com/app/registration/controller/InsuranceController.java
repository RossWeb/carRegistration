package com.app.registration.controller;

import com.app.registration.controller.request.InsuranceRequest;
import com.app.registration.controller.response.InsuranceResponse;
import com.app.registration.controller.response.InsurancesResponse;
import com.app.registration.service.InsuranceAgreementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by konrad on 21.11.16.
 */
@Controller
@RequestMapping(value = "/insurance")
public class InsuranceController {

    private InsuranceAgreementService insuranceAgreementService;

    @Autowired
    public void setInsuranceAgreementService(InsuranceAgreementService insuranceAgreementService) {
        this.insuranceAgreementService = insuranceAgreementService;
    }

    @RequestMapping(value = "/new", method = RequestMethod.POST)
    @ResponseBody
    public InsuranceResponse newInsurance(@RequestBody InsuranceRequest insuranceRequest) {
        InsuranceResponse insuranceResponse = new InsuranceResponse();
        insuranceResponse.setInsuranceAgreement(insuranceAgreementService.create(insuranceRequest));
        return insuranceResponse;
    }

    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    @ResponseBody
    public InsurancesResponse getAllInsurances() {
        InsurancesResponse insurancesResponse = new InsurancesResponse();
        insurancesResponse.setInsuranceAgreementList(insuranceAgreementService.getAll());
        return insurancesResponse;
    }

    @RequestMapping(value = "/search", method = RequestMethod.POST)
    @ResponseBody
    public InsurancesResponse searchInsurance(@RequestBody InsuranceRequest insuranceRequest) {
        InsurancesResponse insurancesResponse = new InsurancesResponse();
        insurancesResponse.setInsuranceAgreementList(insuranceAgreementService.search(insuranceRequest));
        return insurancesResponse;
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    @ResponseBody
    public InsuranceResponse editCar(@RequestBody InsuranceRequest insuranceRequest) {
        InsuranceResponse insuranceResponse = new InsuranceResponse();
        insuranceResponse.setInsuranceAgreement(insuranceAgreementService.update(insuranceRequest));
        return insuranceResponse;
    }

    @RequestMapping(value = "/delete/{insuranceNumber}", method = RequestMethod.GET)
    @ResponseBody
    public void deleteInsurance(@PathVariable(value="insuranceNumber") String insuranceNumber) {
        insuranceAgreementService.deleteInsuranceByNumber(insuranceNumber);
    }
}
