package com.example.emaillist.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.emaillist.dao.UserDao;
import com.example.emaillist.dao.UserDaoImpl;
import com.example.emaillist.vo.UserVo;

@WebServlet("/users")
public class UserServlet extends HttpServlet {

	
	
//	<form> 태그의 method 속성은 폼 데이터(form data)가 서버로 제출될 때 사용되는 HTTP 메소드를 명시합니다.
//	method 속성은 속성값으로는 GET과 POST 두 가지 중 하나를 선택할 수 있습니다.
//	GET 방식은 URL에 폼 데이터를 추가하여 서버로 전달하는 방식입니다.
//	GET 방식의 HTTP 요청은 브라우저에 의해 캐시되어(cached) 저장됩니다.
//	또한, GET 방식은 보통 쿼리 문자열(query string)에 포함되어 전송되므로, 길이의 제한이 있습니다.
//	따라서 보안상 취약점이 존재하므로, 중요한 데이터는 POST 방식을 사용하여 요청하는 것이 좋습니다.
//
//	POST 방식은 폼 데이터를 별도로 첨부하여 서버로 전달하는 방식입니다.
//	POST 방식의 HTTP 요청은 브라우저에 의해 캐시되지 않으므로, 브라우저 히스토리에도 남지 않습니다.
//	또한, POST 방식의 HTTP 요청에 의한 데이터는 쿼리 문자열과는 별도로 전송됩니다.
//	따라서 데이터의 길이에 대한 제한도 없으며, GET 방식보다 보안성이 높습니다. 
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String actionName = req.getParameter("a");
//		GET 요청 처리
		if ("joinform".equals(actionName)) {
			//	a=joinform
			RequestDispatcher rd = 
				req.getRequestDispatcher("/WEB-INF/views/users/joinform.jsp");
			rd.forward(req, resp);
		} else if ("joinsuccess".equals(actionName)) {
			//	a=joinsuccess
			//	가입 성공 페이지로 
			RequestDispatcher rd =
				req.getRequestDispatcher("/WEB-INF/views/users/joinsuccess.jsp");
			rd.forward(req, resp);
		} else if ("loginform".equals(actionName)) {
			//	a=loginform
			//	로그인 폼 페이지로 
			RequestDispatcher rd =
				req.getRequestDispatcher("/WEB-INF/views/users/loginform.jsp");
			rd.forward(req, resp);
		} else {
			resp.sendError(404);	//	Page Not Found
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String actionName = req.getParameter("a");
		
		if("join".equals(actionName)) {
			//	가입 절차 수행
			UserVo vo = new UserVo();
			String name = req.getParameter("name");
			String password = req.getParameter("password");
			String email = req.getParameter("email");
			String gender = req.getParameter("gender");
			
			vo.setName(name);
			vo.setPassword(password);
			vo.setEmail(email);
			vo.setGender(gender);
			
			System.out.println("UserVo:" + vo);
			
			UserDao dao = new UserDaoImpl();
			int insertedCount = dao.insert(vo);
			
			//	체크
			if (insertedCount == 1) {
				//	가입 성공
				//	-> 성공 페이지로 리다이렉트
				resp.sendRedirect(req.getContextPath() + "/users?a=joinsuccess");
			} else {
				//	가입 실패
				//	-> 가입 폼으로 리다이렉트
				resp.sendRedirect(req.getContextPath() + "/users?a=joinform");
			}
		} else if ("login".equals(actionName)) {
			//	로그인 수행
			//	파라미터 확인
			String email = req.getParameter("email");
			String password = req.getParameter("password");
			
			System.out.printf("로그인 정보: email=%s, password=%s%n", 
					email, password);
			
			UserDao dao = new UserDaoImpl();
			
			UserVo vo = dao.getUserByEmailAndPassword(email, password);
			if (vo == null) {
				//	사용자 없음 or 비밀번호 틀림
				System.err.println("사용자 없음!");
				//	로그인 폼으로 돌아가기
				resp.sendRedirect(req.getContextPath() + "/users?a=loginform");
			} else {
				//	사용자 찾음
				System.out.println("사용자 발견! " + vo);
				//	사용자 정보를 서버에 기록(세션)
				
				//	홈페이지로 리다이렉트
				resp.sendRedirect(req.getContextPath());
			}
		} else {
			resp.sendRedirect(req.getContextPath());
		}
	}
	
	
}