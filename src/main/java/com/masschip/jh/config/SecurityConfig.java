package com.masschip.jh.config;

import com.masschip.jh.service.CusUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.util.matcher.RequestMatcher;

import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;
import java.util.regex.Pattern;

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
        /**
         * 允许登录页面无需登陆
         * 允许静态文件，脚本无需登陆
         */
        http.formLogin().loginPage("/login").loginProcessingUrl("/login/form")
                .failureForwardUrl("/lgoinfail")
                .permitAll()
                .and().logout()//自定义登出
                .logoutUrl("/logout") //自定义登出api，无需自己实现
                .logoutSuccessUrl("/login")
                .permitAll()
                .and().authorizeRequests()
                .antMatchers("/css/**","/js/**","/loginfail",
                        "/images/**","/assert/**","/fonts/**","/","/logout")
                .permitAll();
        /**
         * 开启记住我
         */
        http.rememberMe()
                .rememberMeParameter("remember-me").userDetailsService(cusUserDetailsService)
                .tokenRepository(persistentTokenRepository())
                .tokenValiditySeconds(60);

        /**
         * 其它资源需登陆和RBAC授权访问
         */
        http.authorizeRequests().antMatchers("/**").access("@rbacService.hasPermission(request,authentication)")    //必须经过认证以后才能访问
                .and().exceptionHandling().accessDeniedPage("/Access_Denied");


        http.authorizeRequests().anyRequest().authenticated();




        /**
         * 关闭csft
         */
      //  http.csrf().disable();

        /**
         * 前端js可操作的cookie XSRF-TOKEN
         */
       /* http.csrf()
                .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
                .requireCsrfProtectionMatcher(new RequestMatcher() {

                    private Pattern allowmethod=Pattern
                            .compile("^(GET|HEAD|TRACE|OPTIONS)$");
                    @Override
                    public boolean matches(HttpServletRequest httpServletRequest) {
                        if (httpServletRequest.getServletPath().contains("/login")) {
                            return false;
                        }
                        return !allowmethod.matcher(httpServletRequest.getMethod()).matches();
                    }
                });
*/
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
