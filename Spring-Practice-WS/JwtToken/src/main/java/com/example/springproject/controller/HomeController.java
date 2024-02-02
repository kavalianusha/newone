package com.example.springproject.controller;

import com.example.springproject.entity.ResultResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @RequestMapping("/welcome")
    public ResultResponse welcome() {
        String text = "I am Anusha and not authorized to access this page";
        ResultResponse result = new ResultResponse();
        result.setResult(text);
        return result;
    }


}
