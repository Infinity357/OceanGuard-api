package com.example.Ocean.Disaster.Survey.security
//
//import org.springframework.context.annotation.Bean
//import org.springframework.context.annotation.Configuration
//import org.springframework.security.config.annotation.web.builders.HttpSecurity
//import org.springframework.security.config.http.SessionCreationPolicy
//import org.springframework.security.web.SecurityFilterChain
//
//
//@Configuration
//class SecurityConfig {
//
//    @Bean
//    fun filterChain(httpSecurity: HttpSecurity): SecurityFilterChain {
//        return httpSecurity
//            .csrf { csrf-> csrf.disable() }
//            .authorizeHttpRequests { it.anyRequest().permitAll() }
//            .httpBasic { it.disable() }
//            .sessionManagement { it.sessionCreationPolicy(SessionCreationPolicy.STATELESS) }
//            .build()
//    }
//}