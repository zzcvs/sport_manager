package com.project.demo.config;

import com.project.demo.interceptor.LoginInterceptor;
import com.project.demo.service.TokenService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 */
@Configuration
@Slf4j
public class WebAppConfig implements WebMvcConfigurer {


    @Autowired
    private TokenService tokenService;
    
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //登录拦截的管理器
        InterceptorRegistration registration =
                registry.addInterceptor(loginInterceptor(tokenService));
        //拦截的地址
        registration.addPathPatterns("/**");
        //方行的地址
        registration.excludePathPatterns(
                "/user/login",          // 登录本身
                "/user/register",       // 注册
                "/user/forget_password",// 找回密码
                "/user/state",          // 登录态探针，见下
                "/user_group/get_list", // 注册页需要读取用户组
                "/auth/get_list",       // 首页/后台需要读取菜单权限
                "/member_user/add",     // 注册会员时补充会员资料
                "/upload/**",           // 静态图片，见下
                "/error"                // 错误页
        );
        //根据需要拦截，一般设置所有地址拦截，放行公共连接
    }

    @Bean
    public LoginInterceptor loginInterceptor(TokenService tokenService) {
        return new LoginInterceptor(tokenService);   // LoginInterceptor 加一个构造器存起来
    }

}
