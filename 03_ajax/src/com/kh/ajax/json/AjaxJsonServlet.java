package com.kh.ajax.json;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.kh.member.model.vo.Member;

/**
 * Servlet implementation class AjaxJsonServlet
 */
@WebServlet("/json")
public class AjaxJsonServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1. 사용자입력값
		String searchId = request.getParameter("searchId");
		
		//2. 업무로직
		List<Member> list = new ArrayList<>();
		list.add(new Member("hjs", "황제성", "hwang.jpg"));
		list.add(new Member("daftfunk", "다프트펑크", "daftpunk.jpg"));
		list.add(new Member("hyundog", "현타", "hyunta.jpg"));
		list.add(new Member("jyj", "장윤주", "jang_yoon_ju.jpg"));
		
		//검색어가 존재하는 경우, id일치 회원 조회
		Member member = null;
		if(searchId != null) {
			for(Member m : list) {
				if(searchId.equals(m.getId())) {
					member = m;
					break;
				}
			}
		}
		
		//3. JSON 문자열로 변환 및 응답메시지에 출력하기
		response.setContentType("application/json; charset=utf-8");
		Gson gson = new Gson();
		String jsonStr = gson.toJson(searchId!=null? member:list);
		
		System.out.println(jsonStr);
		PrintWriter out = response.getWriter();
		out.print(jsonStr);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1. 사용자입력값
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		System.out.println(id);
		System.out.println(name);
		
		//2. 업무로직
		
		
		//3. 응답메시지 작성
		response.setContentType("text/plain; charset=utf-8");
		response.getWriter().println("회원가입 성공!");
	}

}
