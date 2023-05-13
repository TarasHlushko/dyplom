package com.tarashluhsko.dyplom.config;

import com.tarashluhsko.dyplom.config.entries.AuthenticationTokenBasedEntryPoint;
import com.tarashluhsko.dyplom.config.filters.AuthorizationTokenRequestFilter;
import com.tarashluhsko.dyplom.config.filters.PreLogoutTokenBasedFilter;
import com.tarashluhsko.dyplom.config.handlers.AuthenticationLogoutTokenBasedSecurityHandler;
import com.tarashluhsko.dyplom.model.security.UserDetailsImplementationService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutFilter;
import org.springframework.security.web.authentication.rememberme.AbstractRememberMeServices;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
@RequiredArgsConstructor
public class ApplicationSecurityConfiguration {
    private final AuthenticationTokenBasedEntryPoint authenticationTokenBasedEntryPoint;
    private final AuthenticationLogoutTokenBasedSecurityHandler authenticationLogoutTokenBasedSecurityHandler;
    private final UserDetailsImplementationService userDetailsService;
    private final AuthorizationTokenRequestFilter authorizationTokenRequestFilter;
    private final PreLogoutTokenBasedFilter preLogoutTokenBasedFilter;

    @Bean
    public SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .httpBasic().disable()
                .formLogin().disable();
        http
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http
                .addFilterBefore(authorizationTokenRequestFilter, UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(preLogoutTokenBasedFilter, LogoutFilter.class);
        http
                .authorizeRequests()
                .antMatchers(HttpMethod.POST, "/login", "/logout").permitAll()
                .antMatchers("/home", "/customers/register", "/doctors/register").permitAll()
                .antMatchers("/doctors/getDoctor").hasAnyRole("DOCTOR", "HEAD")
                .antMatchers("/comments/update", "/comments/create", "/comments/delete").hasAnyRole("DOCTOR", "HEAD")
                .antMatchers("/doctors/update", "doctors/delete").hasRole("HEAD")
                .antMatchers("tests/biochemical/create", "tests/biochemical/update", "tests/biochemical/delete")
                .hasAnyRole("DOCTOR", "HEAD")
                .antMatchers("tests/arteries/create", "tests/arteries/update", "tests/arteries/delete")
                .hasAnyRole("DOCTOR", "HEAD")
                .antMatchers("tests/microvessels/create", "tests/microvessels/update", "tests/microvessels/delete" , "/customers/getPatients/biochemical/")
                .hasRole("DOCTOR")
                .antMatchers("/customers/getAccount", "/customers/update", "/customers/delete", "doctors/getAllDoctors", "/customers/getSearchedPatients")
                .authenticated()
                .antMatchers("tests/microvessels/getAll", "tests/biochemical/getAll", "tests/arteries/getAll", "/getCustomerPage").permitAll()

                .anyRequest()
                .authenticated()
                .and()
                .logout()
                .logoutSuccessHandler(authenticationLogoutTokenBasedSecurityHandler)
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout", HttpMethod.POST.name()))
                .invalidateHttpSession(true)
                .clearAuthentication(true)
                .deleteCookies("JSESSIONID", AbstractRememberMeServices.SPRING_SECURITY_REMEMBER_ME_COOKIE_KEY);
        http
                .exceptionHandling()
                .authenticationEntryPoint(authenticationTokenBasedEntryPoint);
        return http.build();
    }


    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfiguration) throws Exception {
        return authConfiguration.getAuthenticationManager();
    }


    @Bean
    public AuthenticationManager authManager(HttpSecurity http) throws Exception {
        return http.getSharedObject(AuthenticationManagerBuilder.class)
                .userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder())
                .and()
                .build();
    }

    @Bean
    protected PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(7);
    }

    @Bean
    protected DaoAuthenticationProvider repositoryAuthenticationProvider() {
        DaoAuthenticationProvider repositoryAuthenticationProvider = new DaoAuthenticationProvider();
        repositoryAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        repositoryAuthenticationProvider.setUserDetailsService(userDetailsService);
        return repositoryAuthenticationProvider;
    }
}
