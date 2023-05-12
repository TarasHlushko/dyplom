package com.tarashluhsko.dyplom.config;

import com.tarashluhsko.dyplom.repositories.CustomerRepository;
import com.tarashluhsko.dyplom.repositories.DoctorRepository;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

import java.util.List;


@Configuration
@EnableWebSecurity
public class ProjectSecurityConfig {

    private final CustomerRepository customerRepository;


    private final DoctorRepository doctorRepository;




    private final MyUserDetailsSevice myUserDetailsSevice;

    private final MyDoctorDetailsService myDoctorDetailsService;


    public ProjectSecurityConfig(CustomerRepository customerRepository, DoctorRepository doctorRepository, MyUserDetailsSevice myUserDetailsSevice, MyDoctorDetailsService myDoctorDetailsService) {
        this.customerRepository = customerRepository;
        this.doctorRepository = doctorRepository;

        this.myUserDetailsSevice = myUserDetailsSevice;
        this.myDoctorDetailsService = myDoctorDetailsService;
    }

    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http, DoctorAuthenticationProvider doctorAuthenticationProvider,
                                                   CustomerUsernamePwdAuthenticationProvider customerUsernamePwdAuthenticationProvider) throws Exception {
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and().cors()
//                .configurationSource(new CorsConfigurationSource() {
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
                .requestMatchers("/doctors/getDoctor").hasAnyRole("DOCTOR", "HEAD")
                .requestMatchers("/comments/update", "/comments/create", "/comments/delete").hasAnyRole("DOCTOR", "HEAD")
                .requestMatchers("/doctors/update", "doctors/delete").hasRole("HEAD")
                .requestMatchers("tests/biochemical/create", "tests/biochemical/update", "tests/biochemical/delete")
                .hasAnyRole("DOCTOR", "HEAD")
                .requestMatchers("tests/arteries/create", "tests/arteries/update", "tests/arteries/delete")
                .hasAnyRole("DOCTOR", "HEAD")
                .requestMatchers("tests/microvessels/create", "tests/microvessels/update", "tests/microvessels/delete" , "/customers/getPatients/biochemical/")
                .hasRole("DOCTOR")
                .requestMatchers("/customers/getAccount", "/customers/update", "/customers/delete", "doctors/getAllDoctors", "/customers/getSearchedPatients")
                .authenticated()
                .requestMatchers("tests/microvessels/getAll", "tests/biochemical/getAll", "tests/arteries/getAll", "/getCustomerPage")
                .authenticated()
                .and()
                .formLogin().and().httpBasic().disable();

        http.authenticationManager(new ProviderManager(List.of(customerUsernamePwdAuthenticationProvider, doctorAuthenticationProvider)));


        http.exceptionHandling()
                .authenticationEntryPoint(
                        (request, response, ex) -> {
                            response.sendError(
                                    HttpServletResponse.SC_UNAUTHORIZED,
                                    ex.getMessage()
                            );
                        }
                );


        return http.build();
    }


    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfiguration) throws Exception {
        return authConfiguration.getAuthenticationManager();
    }

}
