package com.example.ecommerce.controllers;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
@Controller
@RequestMapping("/error")
public class CustomErrorController implements ErrorController {

    @GetMapping
    public String handleError(HttpServletRequest request) {
        // Get the error status code (e.g., 404, 500)
        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");

        // Check for specific status codes if necessary, like 404
        if (statusCode != null) {
            if (statusCode == 404) {
                return "redirect:/error.jsp";
            }
            else if (statusCode == 500) {
                return "redirect:/error.jsp";
            }
        }

        // Default redirection for other errors
        return "redirect:/error.jsp";
    }
    public String getErrorPath() {
        return "/error";
    }
}
