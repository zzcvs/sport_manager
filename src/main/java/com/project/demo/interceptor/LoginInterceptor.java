package com.project.demo.interceptor;


import com.project.demo.service.TokenService;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 */
@Slf4j
public class LoginInterceptor implements HandlerInterceptor {

    private String tokenName = "x-auth-token";
    private TokenService tokenService;


    public LoginInterceptor(TokenService tokenService) {
        this.tokenService = tokenService;
    }
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        setHeader(request, response);                       // 跨域头保持原样

        // ① 预检请求必须放行：浏览器发 OPTIONS 预检时规范上就不带自定义头
        if ("OPTIONS".equals(request.getMethod())) {
            return true;
        }

        // ② 解析身份（唯一一处需要懂 token 的地方）
        Integer userId = tokenService.getUserId(request.getHeader(this.tokenName));

        // ③ 校验失败：401 + JSON，并且把 body 写完再 return false
        if (userId == null || userId == 0) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);   // 401
            response.setHeader("Content-Type", "application/json;charset=UTF-8");
            response.getWriter().write("{\"error\":{\"code\":401,\"message\":\"未登录或登录已过期\"}}");
            return false;
        }

        // ④ 把身份放进去，后面的 Controller 直接拿，不用再解析一次
        request.setAttribute("userId", userId);

//        setHeader(request, response);
//        log.info("[请求接口] - {} , [请求类型] - {}",request.getRequestURL().toString(),request.getMethod());
//        if (request.getRequestURL().toString().contains("/api/user/login")){
//            return true;
//        }
//        else if (request.getRequestURL().toString().contains("/api/user/state")){
//            return true;
//        }
//        else if (request.getRequestURL().toString().contains("/api/user/register")){
//            return true;
//        }
//        if (token == null || "".equals(token)){
//            if ("POST".equals(request.getMethod())){
//                return false;
//            }else {
//                return true;
//            }
//        }else {
            return true;
//        }
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) {
        //更新token
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
    }

    private void failure(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setHeader("Content-type", "application/json;charset=UTF-8");
        response.setStatus(401);
//        response.getWriter().write("");
        response.sendRedirect("https://www.baidu.com");
    }

    private void setHeader(HttpServletRequest request, HttpServletResponse response) {
        //跨域的header设置
        response.setHeader("Access-control-Allow-Origin", request.getHeader("Origin"));
        response.setHeader("Access-Control-Allow-Methods", "GET, HEAD, POST, PUT, DELETE, TRACE, OPTIONS, PATCH");
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("Access-Control-Allow-Headers", request.getHeader("Access-Control-Request-Headers"));
        response.setHeader("Access-Control-Max-Age", "1800");
        //防止乱码，适用于传输JSON数据
        response.setHeader("Content-Type", "application/json;charset=UTF-8");
        response.setStatus(HttpStatus.OK.value());
    }

}
