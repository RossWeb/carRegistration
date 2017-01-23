package com.app.registration.controller;

import com.app.registration.controller.request.DocumentPrintRequest;
import com.app.registration.controller.response.DocumentPrintResponse;
import com.app.registration.service.PrinterProcessorService;
import com.sun.org.apache.xpath.internal.operations.String;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by konrad on 21.11.16.
 */
@RequestMapping(value = "/document")
public class DocumentController {

    private PrinterProcessorService printerProcessorService;

    @Autowired
    @Qualifier("printerProcessorServiceMock")
    public void setPrinterProcessorService(PrinterProcessorService printerProcessorService) {
        this.printerProcessorService = printerProcessorService;
    }

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

    @RequestMapping(value = "/print", method = RequestMethod.POST)
    @ResponseBody
    public DocumentPrintResponse printDocument(@RequestBody DocumentPrintRequest printRequest) {
        DocumentPrintResponse response = new DocumentPrintResponse();
        response.setStatus(Boolean.toString(printerProcessorService.print()));
        return response;
    }
}
