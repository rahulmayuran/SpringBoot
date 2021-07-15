package com.flight.app.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

@Controller
public class MyErrorController implements ErrorController {

    @RequestMapping(path = "/error", method = RequestMethod.GET)
    public String handleError(HttpServletRequest request){
        String statusCode = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE).toString();
        System.out.println(statusCode);
        int status = Integer.parseInt(statusCode);

        if(status==404|status==500){
            return "error.html";
        }

        return "error.html";
    }

//    @Override
//    public String getErrorPath(){
//        return null;
//    }
}
