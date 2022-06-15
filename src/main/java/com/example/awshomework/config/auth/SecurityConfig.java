package com.example.awshomework.config.auth;

import com.example.awshomework.domain.user.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.oauth2.client.OAuth2LoginConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig{

    private final CustomOAuth2UserService customOAuth2UserService;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .headers().frameOptions().disable()
                .and()
                    .authorizeRequests()
               // authorizeRequests -- URL 별 권한 관리를 설정하는 옵션의 시작점
               // antMatchers -- 권환 관리 대상을 지정하는 옵션
               // -- URL, HTTP 메소드 별로 관리 가능
               // anyRequest -- 설정된 값 이외 나머지 URL 
               // authenticated -- 인증된 사용자 == 로그인한 사용자들
                        .antMatchers("/","/css/**","images/**","/js/**",
                            "/h2-console/**").permitAll()
                        .antMatchers("/api/v1/**").hasRole(Role.USER.name())
                        .anyRequest().authenticated()
                .and()
                    .logout()
                    .logoutSuccessUrl("/") // 로그아웃 기능에 대한 여러 설정의 진입점
                .and()
                    .oauth2Login()// 로그인 기능에 대한 여러 설정의 진입점
                        .userInfoEndpoint()//로그인 성공 이후 사용장 정보를 가져올 때의 설정을 담당
                            .userService(customOAuth2UserService); // 소셜 로그인 성공시 후속 조치를 진행할 UserService 인터페이스의 구현체를 등록

        return http.build();
    }
}
