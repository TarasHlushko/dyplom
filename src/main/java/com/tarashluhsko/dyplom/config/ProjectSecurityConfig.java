package com.tarashluhsko.dyplom.config;

import com.tarashluhsko.dyplom.services.CustomerService;
import com.tarashluhsko.dyplom.services.DoctorService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import java.util.List;


@Configuration
@EnableWebSecurity
public class ProjectSecurityConfig {

    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http, DoctorAuthenticationProvider doctorAuthenticationProvider,
                                                   CustomerUsernamePwdAuthenticationProvider customerUsernamePwdAuthenticationProvider) throws Exception {
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//                .and().cors().configurationSource(new CorsConfigurationSource() {
//            @Override
//            public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {
//                CorsConfiguration config = new CorsConfiguration();
//                config.setAllowedOrigins(Collections.singletonList("http://localhost:4200"));
//                config.setAllowedMethods(Collections.singletonList("*"));
//                config.setAllowedHeaders(Collections.singletonList("*"));
//                config.setExposedHeaders(Arrays.asList("Authorization"));
//                config.setAllowCredentials(true);
//                config.setMaxAge(3600L);
//                return config;
//            }
//        })
                .and().csrf().disable()
                .authorizeRequests()
                .requestMatchers("/home", "/customers/register", "/doctors/register").permitAll()
                .requestMatchers("/doctors/getDoctor").hasRole("DOCTOR")
                .requestMatchers("/comments/update", "/comments/create", "/comments/delete").hasRole("DOCTOR")
                .requestMatchers("/doctors/update", "doctors/delete").hasRole("DOCTOR")
                .requestMatchers("tests/biochemical/create", "tests/biochemical/update", "tests/biochemical/delete")
                .hasAnyRole("DOCTOR", "HEAD")
                .requestMatchers("tests/arteries/create", "tests/arteries/update", "tests/arteries/delete")
                .hasAnyRole("DOCTOR", "HEAD")
                .requestMatchers("tests/microvessels/create", "tests/microvessels/update", "tests/microvessels/delete")
                .hasAnyRole("DOCTOR", "HEAD")
                .requestMatchers("/customers/getAccount", "/customers/update", "/customers/delete", "doctors/getAllDoctors", "/customers/getSearchedPatients")
                .authenticated()
                .requestMatchers("tests/microvessels/getAll", "tests/biochemical/getAll", "tests/arteries/getAll", "/getCustomerPage")
                .authenticated()
                .and()
                .formLogin().and().httpBasic();
                http.authenticationManager(new ProviderManager(List.of(customerUsernamePwdAuthenticationProvider, doctorAuthenticationProvider)));
//        http.addFilterAfter(new AuthoritiesLoggingAfterFilter(), BasicAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public DoctorAuthenticationProvider getDoctorProvider(DoctorService doctorService, PasswordEncoder passwordEncoder) {
        DoctorAuthenticationProvider authProvider = new DoctorAuthenticationProvider(doctorService, passwordEncoder);
        return authProvider;
    }

    @Bean
    public CustomerUsernamePwdAuthenticationProvider getCustomerProvider(CustomerService customerService, PasswordEncoder passwordEncoder) {
        CustomerUsernamePwdAuthenticationProvider customerUsernamePwdAuthenticationProvider = new CustomerUsernamePwdAuthenticationProvider(customerService, passwordEncoder);
        return customerUsernamePwdAuthenticationProvider;
    }

}
