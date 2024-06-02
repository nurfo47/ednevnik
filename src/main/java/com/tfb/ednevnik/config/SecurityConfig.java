package com.tfb.ednevnik.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.tfb.ednevnik.service.serviceImpl.CustomSuccessHandler;
import com.tfb.ednevnik.service.serviceImpl.CustomUserDetailsService;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    CustomSuccessHandler customSuccessHandler;


    @Autowired
    CustomUserDetailsService customUserDetailsService;


    @Bean
    public static PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{

        http.csrf(c -> c.disable())
        .authorizeHttpRequests(request -> request.requestMatchers("/admin-dashboard", "/registration").hasAuthority("ROLE_ADMIN")
        .requestMatchers("/user-dashboard").hasAuthority("ROLE_UCENIK")
        .requestMatchers("/profesor-dashboard").hasAuthority("ROLE_NASTAVNIK")
        .requestMatchers("/css/**","/javascript/**").permitAll()
        .anyRequest().authenticated())

        .formLogin(form -> form.loginPage("/login").permitAll().loginProcessingUrl("/login")
        .successHandler(customSuccessHandler).permitAll())

        .logout(form -> form.invalidateHttpSession(true).clearAuthentication(true)
        .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
        .logoutSuccessUrl("/login?logout").permitAll());
        
        return http.build();
    }
    

    @Autowired
    public void configure(AuthenticationManagerBuilder auth) throws Exception{
        auth.userDetailsService(customUserDetailsService).passwordEncoder(passwordEncoder());
    }
}
