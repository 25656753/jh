package com.masschip.jh.config;

import com.masschip.jh.service.CusUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.EnableGlobalAuthentication;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true,securedEnabled=true,jsr250Enabled=true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private AuthenticationProvider provider;

    @Autowired
    private AuthenticationSuccessHandler cusAuthenticationSuccessHandler;
    @Autowired
    private AuthenticationFailureHandler cusAuthenticationFailHander;
    @Autowired
    private CusUserDetailsService cusUserDetailsService;
    @Autowired
    private DataSource dataSource;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .formLogin().loginPage("/login").loginProcessingUrl("/login/form")
                .successHandler(cusAuthenticationSuccessHandler)
                .failureHandler(cusAuthenticationFailHander)
                .permitAll()  //表单登录，permitAll()表示这个不需要验证 登录页面，登录失败页面
                .and()
                .rememberMe()
                .rememberMeParameter("remember-me").userDetailsService(cusUserDetailsService)
                .tokenRepository(persistentTokenRepository())
                .tokenValiditySeconds(60)
                .and()
                .authorizeRequests()
                .antMatchers("/whoim").access("@rbacService.hasPermission(request,authentication)")    //必须经过认证以后才能访问
                .antMatchers("/whoim1").permitAll()
                .anyRequest().authenticated()
               // .and()
           //     .authorizeRequests().anyRequest()
//                      .antMatchers("/index").permitAll()
//                .antMatchers("/whoim").hasRole("ADMIN")
//                .antMatchers(HttpMethod.POST,"/user/*").hasRole("ADMIN")
//                .antMatchers(HttpMethod.GET,"/user/*").hasRole("USER")

               // .authorizeRequests().anyRequest().authenticated()
                .and()
                .csrf().disable();

    }


    @Bean
    public BCryptPasswordEncoder passwordEncoder() {

        return new BCryptPasswordEncoder();
    }


     /**
     * 开启注解spring security需要引出的Bean
     * @return
     * @throws Exception
     */
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {

            return super.authenticationManager();

    }

    /**
     * 记住我功能的token存取器配置Bean
     * @return
     */
    @Bean
    public PersistentTokenRepository persistentTokenRepository() {
        JdbcTokenRepositoryImpl tokenRepository = new JdbcTokenRepositoryImpl();
        tokenRepository.setDataSource(dataSource);
        return tokenRepository;
    }

    /*  @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("root")
                .password("$2a$10$ggetqngmAtvkW2e3.NnyG.YZcRVkNAhXiUQQPUUOcCecNDh/nUME.")
                .roles("USER");


    }*/
}
