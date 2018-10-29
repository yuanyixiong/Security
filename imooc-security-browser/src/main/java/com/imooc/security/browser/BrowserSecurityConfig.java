package com.imooc.security.browser;

import com.imooc.security.browser.authentication.ImoocAuthenticationFailureHandler;
import com.imooc.security.browser.authentication.ImoocAuthenticationSuccessHandler;
import com.imooc.security.croe.authentication.mobile.SmsCodeAuthenticationSecurityConfig;
import com.imooc.security.croe.properties.SecurityProperties;
import com.imooc.security.croe.validate.code.ValidateCodeFilter;
import com.imooc.security.croe.validate.code.ValidateCodeProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import javax.sql.DataSource;

/**
 * @author 袁毅雄
 * @description
 * @date 2018/9/30
 */
@Configuration
public class BrowserSecurityConfig extends WebSecurityConfigurerAdapter {


    /**
     * 密码加密、解密
     *
     * @return
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    private SecurityProperties securityProperties;

    /**
     * 登录：成功处理器
     */
    @Autowired
    private ImoocAuthenticationSuccessHandler authenticationSuccessHandler;

    /**
     * 登录：失败处理器
     */
    @Autowired
    private ImoocAuthenticationFailureHandler authenticationFailureHandler;


    @Autowired
    private DataSource dataSource;

    /**
     * 记住我
     *
     * @return
     */
    @Bean
    public PersistentTokenRepository persistentTokenRepository() {
        JdbcTokenRepositoryImpl jdbcTokenRepository = new JdbcTokenRepositoryImpl();
        jdbcTokenRepository.setDataSource(dataSource);
        //jdbcTokenRepository.setCreateTableOnStartup(true);
        return jdbcTokenRepository;
    }

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private SmsCodeAuthenticationSecurityConfig smsCodeAuthenticationSecurityConfig;

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        //图片验证码过滤器
        ValidateCodeFilter imageValidateCodeFilter = new ValidateCodeFilter(securityProperties, authenticationFailureHandler, ValidateCodeProcessor.SESSION_KEY + "_IMAGE", "imageCode");
        imageValidateCodeFilter.afterPropertiesSet();

        //短信验证码过滤器
        ValidateCodeFilter smsValidateCodeFilter = new ValidateCodeFilter(securityProperties, authenticationFailureHandler, ValidateCodeProcessor.SESSION_KEY + "_SMS", "smsCode");
        smsValidateCodeFilter.afterPropertiesSet();
        http
                .addFilterBefore(smsValidateCodeFilter, UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(imageValidateCodeFilter, UsernamePasswordAuthenticationFilter.class)
                //弹窗登录
//              .httpBasic()
                //表单登录
                .formLogin()
                //登录页面配置
                .loginPage("/authentication/require")
                //提交登录的表单请求
                .loginProcessingUrl("/authentication/form")
                //登录成功后的：成功处理器
                .successHandler(authenticationSuccessHandler)
                //登录失败后的：失败处理器
                .failureHandler(authenticationFailureHandler)
                .and()
                //记住我
                .rememberMe()
                //数据库操作
                .tokenRepository(persistentTokenRepository())
                //cookie 中 token有效时间
                .tokenValiditySeconds(3600)
                //登录操作
                .userDetailsService(userDetailsService)
                .and()
                //请求授权
                .authorizeRequests()
                //不需要身份认证
                .antMatchers("/authentication/require", "/code/*", securityProperties.getBrowser().getLoginPage()).permitAll()
                //任何请求
                .anyRequest()
                //授权
                .authenticated()
                .and()
                //跨站请求伪造
                .csrf().disable()
                .apply(smsCodeAuthenticationSecurityConfig);
    }

}
