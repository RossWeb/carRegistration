package com.app.registration.controller;

import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by konrad on 21.11.16.
 */
@RequestMapping(value = "/verify")
public class VerificationController {

    @RequestMapping(value = "/car", method = RequestMethod.POST)
    @ResponseBody
    public ModelAndView newDocument() {
        final ModelMap model = new ModelMap();
        return new ModelAndView("pages/welcome", model);
    }
}
