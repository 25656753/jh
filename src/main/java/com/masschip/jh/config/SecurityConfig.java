package com.masschip.jh.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

/*    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers(HttpMethod.GET).permitAll()
                .antMatchers(HttpMethod.POST).permitAll()
                .antMatchers("/api/**").permitAll();

        http.formLogin().loginPage("/login")
                .loginProcessingUrl("/form")
                .defaultSuccessUrl("/index") //成功登陆后跳转页面
                .failureUrl("/loginError").permitAll();

        http.logout().logoutUrl("/api/session/logout")
                // 登出前调用，可用于日志
                .addLogoutHandler(customLogoutHandler)
                // 登出后调用，用户信息已不存在
                .logoutSuccessHandler(customLogoutHandler);

        http.exceptionHandling()
                // 已登入用户的权限错误
                .accessDeniedHandler(customAccessDeniedHandler)
                // 未登入用户的权限错误
                .authenticationEntryPoint(customAccessDeniedHandler);

        http.csrf().disable();

        http.addFilterAt(customAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);


    }*/



    @Bean
   public  NoOpPasswordEncoder passwordEncoder()
   {
       return (NoOpPasswordEncoder) NoOpPasswordEncoder.getInstance();
   }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("root").password("abcd1234").roles("USER");


    }
}
