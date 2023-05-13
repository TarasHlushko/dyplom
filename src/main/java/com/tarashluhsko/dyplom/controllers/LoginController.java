package com.tarashluhsko.dyplom.controllers;

import com.tarashluhsko.dyplom.model.Customer;
import com.tarashluhsko.dyplom.model.Doctor;
import com.tarashluhsko.dyplom.model.User;
import com.tarashluhsko.dyplom.model.security.token.AuthenticationProcessingService;
import com.tarashluhsko.dyplom.services.CustomerService;
import com.tarashluhsko.dyplom.services.DoctorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class LoginController {
    private final AuthenticationProcessingService authenticationProcessingService;

    private final CustomerService customerService;

    private final DoctorService doctorService;

    @RequestMapping("/user")
    public <T extends User> T getCustomerDetailsAfterLogin(Authentication authentication) {
        Customer customer = customerService.findByEmail(authentication.getName());
        if (customer != null) {
            return (T) customer;
        } else {
            Doctor doctor = doctorService.findByEmail(authentication.getName());
            if (doctor != null) {
                return (T) doctor;
            }
        }
        return null;
    }

    @PostMapping("/login")
    public ResponseEntity<Object> loginUser(@RequestParam("username") String username,
                                            @RequestParam("password") String password,
                                            HttpServletRequest request) throws AuthenticationException {
        return ResponseEntity.ok(authenticationProcessingService
                .authenticateUserWithTokenBasedAuthorizationStrategy(username, password, request));
    }

}
