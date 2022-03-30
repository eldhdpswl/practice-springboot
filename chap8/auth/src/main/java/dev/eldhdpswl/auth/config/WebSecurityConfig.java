package dev.eldhdpswl.auth.config;

import dev.eldhdpswl.auth.infra.CustomUserDetailsService;
import dev.eldhdpswl.auth.infra.NaverOAuth2Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity //이게 붙게되면 의존성 spring security의 설정을 조작할 준비가 되어있다는 것을 IoC에 알려준다.
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    // WebSecurityConfigurerAdapter를 extends 를 하면 security 설정들을 어느정도 확인할수 있다.


    private final UserDetailsService userDetailsService;
    private final NaverOAuth2Service naverOAuth2Service;

    public WebSecurityConfig(
            @Autowired CustomUserDetailsService customUserDetailsService,
            @Autowired NaverOAuth2Service naverOAuth2Service
    ){
        this.userDetailsService = customUserDetailsService;
        this.naverOAuth2Service = naverOAuth2Service;

    }

    /*
    * 아래 함수는 사용자의 관리, ID와 pw가 일치하는지 확인하는 곳
    *
    * inMemoryAuthentication()는 메모리상에서 user검증을 하겠다.
    * */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(this.userDetailsService);
//        auth.inMemoryAuthentication()
//                .withUser("user1")
//                //.password("user1pass") // 이 상태로 하면 password encoder가 되지 않아 오류가 발생한다.
//                .password(passwordEncoder().encode("user1pass"))
//                .roles("USER")
//                .and()
//                .withUser("admin1")
//                .password(passwordEncoder().encode("admin1pass"))
//                .roles("ADMIN");

    }


    /*
    * authorizeRequests()는 권한 확인용
    * anyRequest() 모든 요청에 대해서 처리한다. 나머지 url에 대한 설정을 하기 위해서 사용하는 거니까 마지막에 선언해야 된다.
    * .antMatchers("/home/**") url을 기준으로 어떤 것을 허용하고 안하는지를 확인한다.
                               해당 url 기준으로 들어가는데는 허가가 된다. *은 하나의 path를 의미, **여러개로 나뉜 path를 의미
    * .permitAll() // 누구든지 접속허용한다.
    * .authenticated() //실행했을때 403에러가 나온다. 허가받지 않은 사용자다라는 의미이다.
    *
    * */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/home/**", "/user/signup/**")
                .anonymous()
                .anyRequest()
                .authenticated()
            .and()
                .formLogin()
                .loginPage("/user/login")
                .defaultSuccessUrl("/home") //스프링부트에서는 로그인을 하면 바로 root페이지로 가도록 되어있다. 그래서 home페이지로 가도록 설정을 한다.
                .permitAll()
            .and()
                .oauth2Login()
                    .userInfoEndpoint()
                    .userService(this.naverOAuth2Service)
                .and()
                    .defaultSuccessUrl("/home")
            .and()
                .logout()
                .logoutUrl("/user/logout")
                .logoutSuccessUrl("/home")
                .deleteCookies("JSESSIONID")
                .invalidateHttpSession(true)
                .permitAll()
        ;
    }



}
