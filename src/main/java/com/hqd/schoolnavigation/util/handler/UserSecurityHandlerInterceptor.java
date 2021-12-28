//package com.hqd.schoolnavigation.util.handler;
//
///**
// * @author 屈燃希
// * @version 1.0
// * @project
// */
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.hqd.schoolnavigation.dto.AjaxResult;
////import com.hqd.schoolnavigation.util.jwt.UserSecurityUtil;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.HttpMethod;
//import org.springframework.http.HttpStatus;
//import org.springframework.web.servlet.HandlerInterceptor;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//
///**
// * UserSecurityHandlerInterceptor 自定义拦截器类
// */
//public class UserSecurityHandlerInterceptor implements HandlerInterceptor {
////    @Autowired
////    private UserSecurityUtil userSecurityUtil;
//    /**
//     * 进行token验证和权限验证
//     * @throws Exception
//     */
//    @Override
//    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//        //判断是否是跨域请求，并且是options的请求，直接返回true
//        if (request.getHeader(HttpHeaders.ORIGIN) != null & HttpMethod.OPTIONS.matches(request.getMethod())) {
//            return true;
//        }
//        System.err.println("UserSecurityHandlerInterceptor preHandle ...");
//        boolean check = true;
//        //校验的方法封装在了UserSecurityUtil这个类中，后面有这个类的代码
//        check = userSecurityUtil.verifyWebToken(request, response);
//        if (!check) {
//            writeResponse(response, HttpStatus.UNAUTHORIZED,AjaxResult.success());
//            return false;
//        }
//        return check;
//    }
//
//    @Override
//    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
//    }
//
//    private void writeResponse(HttpServletResponse resp, HttpStatus status, AjaxResult ajaxResult) throws IOException {
//        ObjectMapper objectMapper = new ObjectMapper();
//        resp.setStatus(status.value());
//        resp.setContentType("application/json; charset=utf-8");
//        resp.getWriter().write(objectMapper.writeValueAsString(ajaxResult));
//    }
//}