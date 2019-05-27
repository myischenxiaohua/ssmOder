package cn.cxh.interceptor;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

public class LoginInterceptor extends HandlerInterceptorAdapter {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//如果登录session为空重定向到401页面
     if(Objects.isNull(request.getSession().getAttribute("loginUser"))){
         response.sendRedirect("/401.jsp");
        return false;
     }

     return true;

    }
}
