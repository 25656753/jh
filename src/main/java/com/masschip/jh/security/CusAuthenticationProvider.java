package com.masschip.jh.security;

import com.masschip.jh.enties.User;
import com.masschip.jh.service.CusUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
public class CusAuthenticationProvider
        implements AuthenticationProvider {
    @Autowired
    private CusUserDetailsService userDetailsService;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;


    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String userName = authentication.getName();// 这个获取表单输入中返回的用户名;
        String password = (String) authentication.getCredentials();// 这个是表单中输入的密码；
        System.out.println("CusAuthenticationProvider------>"+userName+"---"+password);
        // 这里构建来判断用户是否存在和密码是否正确
        User userInfo = (User) userDetailsService.loadUserByUsername(userName); // 这里调用我们的自己写的获取用户的方法；
        if (userInfo == null) {
            throw new BadCredentialsException("用户名不存在");
        }
        if (!passwordEncoder.matches(password, userInfo.getPassword())) {
            throw new BadCredentialsException("密码不正确");
        }
        Collection<? extends GrantedAuthority> authorities = userInfo.getAuthorities();
        // 构建返回的用户登录成功的token
        return new UsernamePasswordAuthenticationToken(userInfo, password, authorities);

    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }




    /**
     * 登陆与授权.
     *  此段代码目前暂时没用，用于自定义认证和和返回js调用可用的token
     * @param username .
     * @param password .
     * @return
     */
    @Autowired
    private AuthenticationManager authenticationManager;

    private String generateToken(String username, String password) {
        UsernamePasswordAuthenticationToken upToken = new UsernamePasswordAuthenticationToken(username, password);
        // Perform the security
        final Authentication authentication = authenticationManager.authenticate(upToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        // Reload password post-security so we can generate token
        final UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        // 持久化的redis
        String token="";
       // String token = CommonUtils.encrypt(userDetails.getUsername());
      //  redisTemplate.opsForValue().set(token, userDetails.getUsername());
        return token;
    }
}
