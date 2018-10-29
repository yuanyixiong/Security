package com.imooc.security.browser.authentication;

import com.alibaba.fastjson.JSON;
import com.imooc.security.croe.properties.login.LoginType;
import com.imooc.security.croe.properties.SecurityProperties;
import com.imooc.security.croe.result.ResultUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

/**
 * @author 袁毅雄
 * @description 登录失败处理器
 * @date 2018/9/30
 */
@Slf4j
@Component
//public class ImoocAuthenticationFailureHandler implements AuthenticationFailureHandler {
public class ImoocAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {

    @Autowired
    private SecurityProperties securityProperties;

    @Override
    public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException authenticationException) throws IOException, ServletException {
        log.info("登录失败");
        if (Objects.equals(securityProperties.getBrowser().getLoginType(), LoginType.JSON)) {
            httpServletResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
            httpServletResponse.setContentType("application/json;charset=UTF-8");
            httpServletResponse.getWriter().write(JSON.toJSONString(ResultUtil.success(authenticationException.getMessage())));
        } else {
            super.onAuthenticationFailure(httpServletRequest, httpServletResponse, authenticationException);
        }
    }
}
