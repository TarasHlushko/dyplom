package com.tarashluhsko.dyplom.config.filters;

import com.tarashluhsko.dyplom.model.security.UserDetailsImplementationService;
import com.tarashluhsko.dyplom.model.security.token.AuthorizationTokenUtility;
import io.jsonwebtoken.ExpiredJwtException;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@RequiredArgsConstructor
public class AuthorizationTokenRequestFilter extends OncePerRequestFilter {
    private final UserDetailsImplementationService userDetailsImplementationService;
    private final AuthorizationTokenUtility authorizationTokenUtility;

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request,
                                    @NonNull HttpServletResponse response,
                                    @NonNull FilterChain chain) throws ServletException, IOException {
        String authorizationHeaderValue = request.getHeader("Authorization");
        if (authorizationHeaderValue != null && authorizationHeaderValue.startsWith("Bearer ")) {
            String authorizationToken = authorizationHeaderValue.substring(7);
            try {
                String username = authorizationTokenUtility.getUsernameFromToken(authorizationToken);
                if (!username.isEmpty() && SecurityContextHolder.getContext().getAuthentication() == null) {
                    UserDetails userDetails = userDetailsImplementationService.loadUserByUsername(username);
                    if (authorizationTokenUtility.validateToken(authorizationToken, userDetails, request)) {
                        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                                new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                        usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                        SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
                    }
                }
            } catch (IllegalArgumentException e) {
                logger.error("Unable to fetch JWT Token");
            } catch (ExpiredJwtException e) {
                logger.error("JWT Token is expired");
            } catch (Exception e) {
                logger.error(e.getMessage());
            }
        } else {
            if (!request.getRequestURI().equals("/login") && !request.getRequestURI().startsWith("/stomp")) {
                logger.warn("Authorization header value does not begin with Bearer, incorrect token type");
            }
        }
        chain.doFilter(request, response);
    }
}
