package com.tarashluhsko.dyplom.config;

import com.tarashluhsko.dyplom.model.Doctor;
import com.tarashluhsko.dyplom.services.DoctorService;
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

@Component
public class DoctorAuthenticationProvider implements AuthenticationProvider {
    private final DoctorService doctorService;


    private PasswordEncoder passwordEncoder;


    public DoctorAuthenticationProvider(DoctorService doctorService, PasswordEncoder passwordEncoder) {
        this.doctorService = doctorService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        System.out.println("TOOO");
        String username = authentication.getName();
        String pwd = authentication.getCredentials().toString();

        Doctor doctor = doctorService.findByEmail(username);
        if (doctor != null) {
            if (passwordEncoder.matches(pwd, doctor.getPassword())) {
                return new UsernamePasswordAuthenticationToken(username, pwd, getGrantedAuthorities(doctor.getRole()));
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
