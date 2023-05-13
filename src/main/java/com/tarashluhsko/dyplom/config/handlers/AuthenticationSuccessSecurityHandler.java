package com.tarashluhsko.dyplom.config.handlers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.lang.NonNull;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component
public class AuthenticationSuccessSecurityHandler implements AuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(@NonNull HttpServletRequest request,
                                        @NonNull HttpServletResponse response,
                                        Authentication authentication) throws IOException {
        ObjectMapper jacksonMapper = new ObjectMapper();
        Map<String, Object> responseBodyMap = new HashMap<>();
        responseBodyMap.put("authenticated", true);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.setStatus(HttpStatus.OK.value());
        response.getWriter().write(jacksonMapper.writeValueAsString(responseBodyMap));
        response.getWriter().flush();
    }
}
