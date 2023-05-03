package com.tarashluhsko.dyplom.config;

import com.tarashluhsko.dyplom.model.Customer;
import com.tarashluhsko.dyplom.services.CustomerService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Component
public class CustomerUsernamePwdAuthenticationProvider implements AuthenticationProvider {

    private final CustomerService customerService;


    private final PasswordEncoder passwordEncoder;


    public CustomerUsernamePwdAuthenticationProvider(CustomerService customerService, PasswordEncoder passwordEncoder) {
        this.customerService = customerService;
        this.passwordEncoder = passwordEncoder;
    }



    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
//        System.out.println(authentication.getCredentials().toString() + "  " + authentication.getName());
        String username = authentication.getName();
        String pwd = authentication.getCredentials().toString();


        Customer customer = customerService.findByEmail(username);
        if (customer != null) {
            if (passwordEncoder.matches(pwd, customer.getPassword())) {
                return new UsernamePasswordAuthenticationToken(username, pwd, getGrantedAuthorities("none"));
            }
            throw new BadCredentialsException("Invalid password");
        }

        throw new BadCredentialsException("No user exists with this credentials");
    }

    private List<GrantedAuthority> getGrantedAuthorities(String role) {
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        grantedAuthorities.add(new SimpleGrantedAuthority(role));

        return grantedAuthorities;
    }
    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }

}
