package com.app.registration.controller;

import com.app.registration.controller.request.CarRequest;
import com.app.registration.controller.response.CarResponse;
import com.app.registration.controller.response.CarsResponse;
import com.app.registration.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by konrad on 21.11.16.
 */
@Controller
@RequestMapping(value = "/car")
public class CarController {

    private CarService carService;

    @Autowired
    public void setCarService(CarService carService) {
        this.carService = carService;
    }

    @RequestMapping(value = "/new", method = RequestMethod.POST)
    @ResponseBody
    public CarResponse newCar(@RequestBody CarRequest carRequest) {
        CarResponse carResponse = new CarResponse();
        carResponse.setCarDto(carService.createCar(carRequest));
        return carResponse;
    }

    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    @ResponseBody
    public CarsResponse getAllCars() {
        CarsResponse carsResponse = new CarsResponse();
        carsResponse.setCarsDto(carService.findAllCars());
        return carsResponse;
    }

    @RequestMapping(value = "/searchByVin/{vin}", method = RequestMethod.GET)
    @ResponseBody
    public CarResponse searchCarByVin(@PathVariable(value="vin") String vin) {
        CarResponse carResponse = new CarResponse();
        carResponse.setCarDto(carService.findCarByVin(vin));
        return carResponse;
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    @ResponseBody
    public CarResponse editCar(@RequestBody CarRequest carRequest) {
        CarResponse carResponse = new CarResponse();
        carResponse.setCarDto(carService.updateCar(carRequest));
        return carResponse;
    }

    @RequestMapping(value = "/delete/{vin}", method = RequestMethod.GET)
    @ResponseBody
    public void deleteCar(@PathVariable(value="vin") String vin) {
        carService.deleteCarByVin(vin);
    }
}
