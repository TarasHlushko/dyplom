package com.tarashluhsko.dyplom.config;

import com.tarashluhsko.dyplom.model.User;
import com.tarashluhsko.dyplom.repositories.UserRepository;
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

//@Component
//public class UserUsernamePwdAuthenticationProvider implements AuthenticationProvider {
//    @Autowired
//    private UserRepository userRepository;
//
//    @Autowired
//    private PasswordEncoder passwordEncoder;
//
//    @Override
//    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
//
//        String username = authentication.getName();
//        String pwd = authentication.getCredentials().toString();
//
//        User user = userRepository.findByEmail(username);
//        if (user != null) {
//            if (passwordEncoder.matches(pwd, user.getPassword())) {
//                return new UsernamePasswordAuthenticationToken(username, pwd, getGrantedAuthorities(user.getRole()));
//            }
//            throw new BadCredentialsException("Invalid password");
//        }
//
//        throw new BadCredentialsException("No user exists with this credentials");
//    }
//
//    private List<GrantedAuthority> getGrantedAuthorities(String role) {
//        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
//
//        grantedAuthorities.add(new SimpleGrantedAuthority(role));
//
//        return grantedAuthorities;
//    }
//
//    @Override
//    public boolean supports(Class<?> authentication) {
//        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
//    }
//}
