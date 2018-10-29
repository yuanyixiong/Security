package com.imooc.security.croe.validate.code;

import com.imooc.security.croe.properties.SecurityProperties;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author 袁毅雄
 * @description 登录时验证验证码的过滤器
 * @date 2018/9/30
 */
public class ValidateCodeFilter extends OncePerRequestFilter implements InitializingBean {

    /**
     * @param securityProperties           : 自定义属性
     * @param authenticationFailureHandler : 登录失败处理器
     * @param sessionKey                   : session key
     * @param parameter                    : 验证码参数名称
     */
    public ValidateCodeFilter(SecurityProperties securityProperties, AuthenticationFailureHandler authenticationFailureHandler, String sessionKey, String parameter) {
        this.securityProperties = securityProperties;
        this.authenticationFailureHandler = authenticationFailureHandler;
        this.sessionKey = sessionKey;
        this.parameter = parameter;
    }

    /**
     * 登录失败处理器
     */
    private AuthenticationFailureHandler authenticationFailureHandler;

    /**
     * 自定义属性
     */
    private SecurityProperties securityProperties;

    /**
     * session key 后缀
     */
    private String sessionKey;

    /**
     * 验证码参数名称
     */
    private String parameter;

    /**
     * 需要拦截的请求
     */
    private Set<String> urls = new HashSet<>(10);


    @Override
    public void afterPropertiesSet() throws ServletException {
        super.afterPropertiesSet();

        /**
         * 图形验证码
         */
        urls.addAll(
                Arrays.stream(
                        Optional.ofNullable(StringUtils.splitByWholeSeparator(securityProperties.getValidateCode().getImageCode().getUrl(), ","))
                                .orElse(new String[0])
                ).collect(Collectors.toSet())
        );
        urls.add("/authentication/form");

        /**
         * 短信验证码
         */
        urls.addAll(
                Arrays.stream(
                        Optional.ofNullable(StringUtils.splitByWholeSeparator(securityProperties.getValidateCode().getSmsCode().getUrl(), ","))
                                .orElse(new String[0])
                ).collect(Collectors.toSet())
        );
        urls.add("/authentication/mobile");
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        //spring 的请求路径处理工具
        AntPathMatcher antPathMatcher = new AntPathMatcher();

        //校验urls中的请求
        if (urls.stream().filter(url -> antPathMatcher.match(url, request.getRequestURI())).count() > 0) {
            try {
                validate(new ServletWebRequest(request));
            } catch (ValidateCodeException e) {
                //校验出错交给失败处理器处理
                authenticationFailureHandler.onAuthenticationFailure(request, response, e);
                return;
            }
        }
        filterChain.doFilter(request, response);
    }

    /**
     * 校验请求中的验证码
     *
     * @param request 请求对象、响应对象的封装
     * @throws ServletRequestBindingException
     */
    private void validate(ServletWebRequest request) throws ServletRequestBindingException {
        //spring 的请求参数处理工具
        SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();

        //获取请求中的验证码
        ValidateCode codeInSession = (ValidateCode) sessionStrategy.getAttribute(request, sessionKey);
        String codeInRequest = ServletRequestUtils.getStringParameter(request.getRequest(), parameter);

        //校验npe
        if (StringUtils.isBlank(codeInRequest)) {
            throw new ValidateCodeException("验证码不能为空");
        }

        if (Objects.isNull(codeInSession)) {
            throw new ValidateCodeException("验证码不存在");
        }

        //校验超时
        if (codeInSession.isExpireTime()) {
            sessionStrategy.removeAttribute(request, sessionKey);
            throw new ValidateCodeException("验证码已经过期");
        }

        //校验一致性
        if (!StringUtils.equals(codeInSession.getCode(), codeInRequest)) {
            throw new ValidateCodeException("验证码不匹配");
        }

        sessionStrategy.removeAttribute(request, sessionKey);
    }
}
