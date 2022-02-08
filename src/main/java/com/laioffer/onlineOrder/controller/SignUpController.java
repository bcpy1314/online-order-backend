package com.laioffer.onlineOrder.controller;

import com.laioffer.onlineOrder.entity.Customer;
import com.laioffer.onlineOrder.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;


@Controller
// use dispatch servlet to find specific controller with specific method
// controller and request mapping is to find the method that browser request
// controller and mapping must happen together
public class SignUpController {
    @Autowired
    private CustomerService customerService;

    @RequestMapping(value ="/signup", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public void signUp(@RequestBody Customer customer){
        customerService.signUp(customer);
    }
}