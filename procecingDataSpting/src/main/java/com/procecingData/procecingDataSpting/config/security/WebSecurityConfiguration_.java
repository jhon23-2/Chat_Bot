package com.procecingData.procecingDataSpting.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@Configuration
public class WebSecurityConfiguration_ {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .csrf(csrf->{
                    csrf.disable();
                })
                .authorizeHttpRequests(request->{

                    request.requestMatchers(HttpMethod.GET,"/application/log","/css/**","/js/**").permitAll();
                    request.requestMatchers(HttpMethod.POST, "/application/register").permitAll();
                    request.requestMatchers(HttpMethod.GET,"/application/home_admin").hasRole("ADMIN");
                    request.requestMatchers(HttpMethod.POST,"/application/send").hasRole("ADMIN");
                    request.requestMatchers(HttpMethod.POST,"/application/message").hasRole("USER");

                    request.anyRequest().authenticated();

                }).sessionManagement(session->{
                    session.sessionCreationPolicy(SessionCreationPolicy.ALWAYS);
                })
                .formLogin(login->{
                    login.loginPage("/application/log").permitAll();
                    login.defaultSuccessUrl("/application/home");
                    login.failureUrl("/application/log?error");
                    login.passwordParameter("userPassword");
                    login.usernameParameter("userName");
                })
                .logout(logout->{
                    logout.logoutSuccessUrl("/application/log?out").permitAll();
                })
                .httpBasic(Customizer.withDefaults())
                .build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationProvider provider(UserDetailsServiceImpl userDetailsService){
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();

        provider.setPasswordEncoder(passwordEncoder());
        provider.setUserDetailsService(userDetailsService);

        return provider;
    }



}
