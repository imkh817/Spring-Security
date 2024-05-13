package org.example.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity // 스프링시큐리티를 활성화
public class SecurityConfig {

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder(){ // 암호화 클래스 Bean 등록
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((auth)->auth
                        .requestMatchers("/","/login","/join","joinProc").permitAll() // "/" 과 "/login"의 주소는 모두 허용
                        .requestMatchers("/admin").hasRole("ADMIN") // "/admin"의 주소는 ADMIN이라는 역할을 가진 사람만 허용
                        .requestMatchers("/my/**").hasAnyRole("ADMIN","USER") // "/my/**"로 되어있는 주소는 ADMIN과 USER 역할을 가진 사람만 허용
                        .anyRequest().authenticated() // 나머지 주소는 모두 허용
                        // 위에서 실행이 되면 아래 코드는 실행이 되지 않기 때문에 순서를 잘 적어야한다.
                );

        http
                .formLogin((auth)->auth.loginPage("/login") // /login으로 오는 주소로 반환되는 페이지를 로그인 페이지로 지정
                .loginProcessingUrl("/loginProc") // 로그인 페이지 폼에서 지정한 로그인 후 진행 url 지정
                .permitAll()); // 전부 허용

        http
                .csrf((auth)->auth.disable()); // 위변조 csrf 기술 적용 X
        // 기술을 적용하지 않는 이유는 csrf 설정을 하게되면 post 요청을 보낼때 csrf 토큰도 같이 보내주어야 로그인이 진행되기 때문에

        http
                .sessionManagement((auth) -> auth
                        .maximumSessions(1)  // 하나의 아이디에 대한 다중 로그인 허용 개수
                        .maxSessionsPreventsLogin(true)); // 다중 로그인 개수 초과시 방법 true : 새로운 로그인 차단 false : 기존 세션 하나 삭제

        http
                .sessionManagement((auth) -> auth
                        .sessionFixation().changeSessionId()); // 세선 고정 보호 : 로그인 시 동일한 세션에 대한 id 변경


        return http.build();
    }
}
