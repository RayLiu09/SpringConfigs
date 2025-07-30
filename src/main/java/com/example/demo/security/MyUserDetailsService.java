package com.example.demo.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

/**
 * Created by Administrator on 2017/11/23.
 */
@Slf4j
@Component
public class MyUserDetailsService implements UserDetailsService {
    /**
     * 根据用户名查询用户信息
     *
     * @param username
     * @return
     * @throws UsernameNotFoundException
     */
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return null;
    }
}
