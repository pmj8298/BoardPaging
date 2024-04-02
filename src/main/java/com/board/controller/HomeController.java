package com.board.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.board.user.domain.UserVo;
import com.board.user.mapper.UserMapper;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class HomeController {
	
	@Autowired
	private  UserMapper   userMapper;
	
	// http://localhost:9090
	@RequestMapping("/")
	public  String   home() {
		return "home";
	}
	
	 // 로그인
	@GetMapping("/loginForm")
	public  String  loginForm() {
		return "users/login";
	}
	
    @PostMapping("/login")
    public String login(HttpServletRequest request) {

        // 1. 회원 정보 조회
        String loginId  = request.getParameter("loginId");
        String password = request.getParameter("password");
        UserVo userVo   = userMapper.login( loginId, password );
        System.out.println(userVo);

        // 2. 세션에 회원 정보 저장 & 세션 유지 시간 설정
        if (userVo != null) {
            HttpSession session = request.getSession();
            session.setAttribute("login", userVo);
            session.setMaxInactiveInterval(60 * 30);
        }
        
        return "redirect:/";
    }

    // 로그아웃
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/loginForm";
    }
		
	
}
