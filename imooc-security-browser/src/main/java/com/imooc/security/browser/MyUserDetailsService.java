package com.imooc.security.browser;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * @author 袁毅雄
 * @description
 * @date 2018/9/30
 */
@Slf4j
@Component
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        //注册的时候加密
        String password = passwordEncoder.encode("123456");

        log.info("password:{}", password);
        return new User(
                username,
                password,
                //可用(是否被删除)
                true,
                //账户没有过期
                true,
                //密码没有过期
                true,
                // 没被锁定(是否被冻结)
                true,
                AuthorityUtils.commaSeparatedStringToAuthorityList("admin"));
    }
}
