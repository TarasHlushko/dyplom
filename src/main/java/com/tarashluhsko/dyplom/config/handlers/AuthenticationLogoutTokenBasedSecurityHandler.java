package com.tarashluhsko.dyplom.config.handlers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tarashluhsko.dyplom.model.security.UserDetailsImplementationService;
import com.tarashluhsko.dyplom.model.security.token.AuthorizationTokenUtility;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.lang.NonNull;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component
@RequiredArgsConstructor
@Slf4j
public class AuthenticationLogoutTokenBasedSecurityHandler implements LogoutSuccessHandler {
    private final UserDetailsImplementationService userDetailsImplementationService;
    private final AuthorizationTokenUtility authorizationTokenUtility;

    @Override
    public void onLogoutSuccess(@NonNull HttpServletRequest request,
                                @NonNull HttpServletResponse response,
                                Authentication authentication) throws IOException {
        if (request.getRequestURI().equals("/logout")) {
            String authorizationHeaderValue = request.getHeader("Authorization");
            if (authorizationHeaderValue != null && authorizationHeaderValue.startsWith("Bearer ")) {
                String authorizationToken = authorizationHeaderValue.substring(7);
                String username = authorizationTokenUtility.getUsernameFromToken(authorizationToken);
                UserDetails userDetails = userDetailsImplementationService.loadUserByUsername(username);
                if (!authorizationToken.isEmpty()
                        && authorizationTokenUtility.validateToken(authorizationToken, userDetails, request)) {
                    authorizationTokenUtility.blacklistToken(authorizationToken);
                    ObjectMapper jacksonMapper = new ObjectMapper();
                    Map<String, Object> responseBodyMap = new HashMap<>();
                    responseBodyMap.put("logout", true);
                    response.setContentType("application/json");
                    response.setCharacterEncoding("UTF-8");
                    response.setStatus(HttpStatus.OK.value());
                    response.getWriter().write(jacksonMapper.writeValueAsString(responseBodyMap));
                    response.getWriter().flush();
                }
            }
        }
    }
}
