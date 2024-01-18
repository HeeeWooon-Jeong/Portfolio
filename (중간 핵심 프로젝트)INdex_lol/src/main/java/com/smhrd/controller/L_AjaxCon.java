package com.smhrd.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import com.smhrd.dao.L_userDAO;

import com.smhrd.entity.L_user;

public class L_AjaxCon implements L_Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// ajax.do 의 요청이 넘어오는 Controller
		System.out.println("요청이 들어왔습니다 !");

		// 데이터 수집
		request.setCharacterEncoding("UTF-8");
		String data = request.getParameter("data");

		// 기능 수행

		// 결과 따른 처리
		response.setCharacterEncoding("UTF-8");

		PrintWriter out = response.getWriter();
		
		
		// 회원 목록의 정보를 모두 응답으로 보내보기!
		L_userDAO dao = new L_userDAO();
		List<L_user> list = dao.udview();
		
		
		// JSON 형태로 대량의 데이터 전달하기
		// csv -> , 기준 데이터 판단 , , , , , ,
		// XML -> <> 태그를 기준으로 데이터 판단
		// JSON -> [ {key : value} , {key : value} ]
		
		// list 데이터의 형태를 JSON으로 변환!
		Gson gson = new Gson();
		String json = gson.toJson(list);
		System.out.println("json : " + json);
		
			

		// 응답되는 데이터
		out.print(json);

		return null;
	}

}
