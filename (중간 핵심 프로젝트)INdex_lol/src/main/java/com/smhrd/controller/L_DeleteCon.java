package com.smhrd.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.smhrd.dao.L_userDAO;
import com.smhrd.entity.L_user;

public class L_DeleteCon implements L_Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		request.setCharacterEncoding("UTF-8");
		
		HttpSession session = request.getSession();
		
		L_user user = (L_user)session.getAttribute("user");
		
		String id = user.getU_id();
		String pw = request.getParameter("loginPw");

		L_user l_user = new L_user();
		l_user.setU_id(id);
		l_user.setU_pw(pw);
		
		System.out.println("원서바보"+ l_user);
		
		L_userDAO dao = new L_userDAO();
		int cnt = dao.delete(l_user);
		
		String url = "";
		
		if (cnt > 0) {
			// 회원탈퇴 성공
			System.out.println("탈퇴 성공");
			session.removeAttribute("user");
			url= "L_login";
		} else {

			System.out.println("탈퇴 실패");
			url="L_main";
		}

		return url;

	}

}
