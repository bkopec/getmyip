package com.bkopec.getmyip.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.RequestAttributes;

import com.bkopec.getmyip.util.IpAddressUtil;

@RestController // This is crucial for marking this class as a REST endpoint
public class IpController {

    @GetMapping("/ip") // This is the path for this endpoint
    public String getIp() {
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        // It's good practice to check for null if this service might be called outside a web request context
        if (requestAttributes != null) {
            String remoteAddress = IpAddressUtil.getClientIpAddress(requestAttributes);
            return remoteAddress;
        } else {
            return "";
        }
    }

    // You can add other endpoints here if they logically belong to this controller
    @GetMapping("/")
    public String home() {
        return "Welcome to the Spring Boot App!";
    }
}