package com.project.jcloud.interceptor;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

public class Interceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        boolean result = false;
        String root = request.getContextPath();
        String user = (String)request.getSession().getAttribute("user");

        if(user == null){
            PrintWriter printwriter = response.getWriter();
            printwriter.print("<script>alert('시간만료! 로그인을 다시 해주십시오');</script>");
            printwriter.flush();
            printwriter.close();
            response.sendRedirect(root + "/");
            result = false;
        }else{
            result = true;
        }
        return result;
//        System.out.println("어드민 컨트롤러 요청 입니다.!!");
//        return super.preHandle(request,response,handler);

    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

        String root = request.getContextPath();
        String user = (String)request.getSession().getAttribute("user");

        if(user == null){
            PrintWriter printwriter = response.getWriter();
            printwriter.print("<script>alert('시간만료! 로그인을 다시 해주십시오');</script>");
            printwriter.flush();
            printwriter.close();
            response.sendRedirect(root + "/");
        }
        System.out.println("어드민 컨트롤러 응답 입니다.!!");
        System.out.println("root :: " + root);
        System.out.println("user :: " + user);
        System.out.println("last access time :: " +  request.getSession().getLastAccessedTime());
        System.out.println("max time :: " + request.getSession().getMaxInactiveInterval());
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("어드민 컨트롤러 응답까지 완료 입니다.");
    }

    @Override
    public void afterConcurrentHandlingStarted(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("비동기 요청시 수행됩니다, postHandle, afterCompletion 수행 X");
    }
}
