package com.app.registration.controller;

import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by konrad on 21.11.16.
 */
@RequestMapping(value = "/document")
public class DocumentController {

    @RequestMapping(value = "/new", method = RequestMethod.POST)
    @ResponseBody
    public ModelAndView newDocument() {
        final ModelMap model = new ModelMap();
        return new ModelAndView("pages/welcome", model);
    }

    @RequestMapping(value = "/search", method = RequestMethod.POST)
    @ResponseBody
    public ModelAndView searchDocument() {
        final ModelMap model = new ModelMap();
        return new ModelAndView("pages/welcome", model);
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    @ResponseBody
    public ModelAndView editDocument() {
        final ModelMap model = new ModelMap();
        return new ModelAndView("pages/welcome", model);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public ModelAndView deleteDocument() {
        final ModelMap model = new ModelMap();
        return new ModelAndView("pages/welcome", model);
    }

    @RequestMapping(value = "/type/new", method = RequestMethod.POST)
    @ResponseBody
    public ModelAndView newDocumentType() {
        final ModelMap model = new ModelMap();
        return new ModelAndView("pages/welcome", model);
    }

    @RequestMapping(value = "/type/search", method = RequestMethod.POST)
    @ResponseBody
    public ModelAndView searchDocumentType() {
        final ModelMap model = new ModelMap();
        return new ModelAndView("pages/welcome", model);
    }

    @RequestMapping(value = "/type/edit", method = RequestMethod.POST)
    @ResponseBody
    public ModelAndView editDocumentType() {
        final ModelMap model = new ModelMap();
        return new ModelAndView("pages/welcome", model);
    }

    @RequestMapping(value = "type/delete", method = RequestMethod.POST)
    @ResponseBody
    public ModelAndView deleteDocumentType() {
        final ModelMap model = new ModelMap();
        return new ModelAndView("pages/welcome", model);
    }
}
