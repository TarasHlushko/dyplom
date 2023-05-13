package com.tarashluhsko.dyplom.model.security.token;

import com.tarashluhsko.dyplom.model.security.UserDetailsImplementationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthenticationProcessingService {
    private final AuthenticationManager authenticationManager;
    private final UserDetailsImplementationService userDetailsService;
    private final AuthorizationTokenUtility authorizationTokenUtility;

    public Map<String, Object> authenticateUserWithTokenBasedAuthorizationStrategy(String username,
                                                                                   String password,
                                                                                   HttpServletRequest request) throws AuthenticationException {
        Authentication authentication =
                authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        String token;
        if (authentication.isAuthenticated()) {
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);
            token = authorizationTokenUtility.generateToken(userDetails, request);
        } else {
            throw new RuntimeException("Could not authenticate following credentials");
        }
        Map<String, Object> response = new HashMap<>();
        response.put("token", token);
        response.put("expires_at", authorizationTokenUtility.getExpirationDateFromToken(token).getTime());
        return response;
    }
}
