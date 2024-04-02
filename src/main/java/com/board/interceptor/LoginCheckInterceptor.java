package com.board.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.board.user.domain.UserVo;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Component
public class LoginCheckInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, 
    		HttpServletResponse response, Object handler) throws Exception {
       	
        // 1. 세션에서 회원 정보 조회
        HttpSession session = request.getSession();
        
        String  requestUrl = request.getRequestURL().toString();
        System.out.println("경로:" + requestUrl + " " +  session.getAttribute("login"));
		
		// /login 페이지는 체크에서 제외해야함 
		/*
        if(requestUrl.contains("/login")) {
			return true;
		}
		*/
		
		//-----------------------------
		Object   obj = session.getAttribute("login");
		/*
		if(  obj == null ) {
			// 로그인되어 있지 않다면 /login 으로 이동하시오
			response.sendRedirect("/loginForm");
			return false;		
		}
		*/

        return HandlerInterceptor.super.preHandle(request, response, handler);
    }

}