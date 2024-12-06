package com.muratkistan.spring_security_6_jwt.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/secure")
public class DemoController {
    
        @RequestMapping("/hello")
        public String hello() {
            return "Hello World!";
        }
    
        @PreAuthorize("hasRole('ROLE_ADMIN')")
        @RequestMapping("/admin")
        public String admin() {
            return "Hello Admin!";
        }
    
        @RequestMapping("/user")
        public String user() {
            return "Hello User!";
        }
    
        @RequestMapping("/all")
        public String all() {
            return "Hello All!";
        }

}
