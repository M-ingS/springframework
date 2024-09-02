package com.mycompany.springframework.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LoginCheckInterceptor implements HandlerInterceptor{
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		log.info("실행");
		
		try {
				// @LoginCheck가 붙어 있는지 검사 
				HandlerMethod handlerMethod = (HandlerMethod) handler;
				LoginCheck loginCheck = handlerMethod.getMethodAnnotation(LoginCheck.class);
				if (loginCheck == null) {
					// @LoginCheck가 붙어있지 않은 경우
					log.info("@LoginCheck가 붙어있지 않음");
					return true;
				} else {
					// LoginCheck가 붙어있는 경우
					log.info("@LoginCheck가 붙어 있음");
					
					//로그인 여부 확인
					HttpSession session = request.getSession();
					String login = (String) session.getAttribute("login");	// 세션에서 로그인 키가 있으면 저장("login", mid)
					if (login == null) {
						// 로그인을 하지 않았을 경우
						String contextPath = request.getServletContext().getContextPath();
						response.sendRedirect(contextPath + "/ch02/loginForm");
						return false;	//로그인을 안했으니 로그인폼으로 리다이렉트
					} else {
						// 로그인을 했을 경우
					}
				}
		} catch (Exception e) {
			
		}
		
		return true;
	}
	
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		log.info("실행");
	}
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		log.info("실행");
	}//컨트롤 + 스페이스로 HandlerInterceptor가 가지고 있는 디폴트 메소드 호출

}
