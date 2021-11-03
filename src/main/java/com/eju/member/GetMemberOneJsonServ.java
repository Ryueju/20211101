package com.eju.member;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Servlet implementation class GetMemberJsonServ
 */
@WebServlet("/GetMemberOneJsonServ")
public class GetMemberOneJsonServ extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public GetMemberOneJsonServ() {
		super();

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/json;charset=UTF-8");
		String id = request.getParameter("id"); //파라미터가 무엇인지에따라 달라짐
		MemDAO dao = new MemDAO();
		MemberVO vo = dao.getMember(id);
		Gson gson = new GsonBuilder().create();
		response.getWriter().println(gson.toJson(vo));

//				PrintWriter out = response.getWriter();
		// {"name" : "pochako", "age": 20, "phone": " 010-1234-5678"}
		// "{\"name":"phchako"}"; //
		// "[{\"id\":\"?"\, \"name\":?, birth:?, gender:? },{},{}]";
		// out.println("{\"name\" : \"pochako\", \"age\": 20, \"phone\": \"
		// 010-1234-5678\"}");
//				MemDAO dao = new MemDAO();
//				List<MemberVO> list = dao.getMemberList();
		/*
		 * int cnt = list.size(); out.println("[");
		 */// 여러 건 넣어야 하므로 배열 기호를 넣어줌
		/*
		 * for(int i=0; i<cnt; i++) { out.println("{\"id\":\""+ list.get(i).getUserId()
		 * + "\", \"name\":\""+list.get(i).getUserName()+"\"}"); if(i != cnt-1 ) {
		 * out.println(","); //cnt-1이 i가 아니면 이제 콤마를 찍으주고.. i면 다음 데이터를 직어줄게... } }
		 * out.println("]");
		 */
//				Gson gson = new GsonBuilder().create();
//				out.println(gson.toJson(list));
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 입력하는 기능
		MemDAO dao = new MemDAO();
		String userId = request.getParameter("id");// ""값을 사용자가 넣어주면~
		String userName = request.getParameter("name");
		String address = request.getParameter("addr");

		MemberVO vo = new MemberVO();
		vo.setUserId(userId);
		vo.setUserName(userName);
		vo.setAddress(address);

		dao.insertMember(vo);

		response.getWriter().println("OK"); // 값이 없으면 text형식으로 넘어가게끔.
		// dao.insertMember(null);
		// doGet(request, response);
	}

}
