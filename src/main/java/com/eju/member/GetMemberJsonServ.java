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
import com.google.gson.JsonObject;

/**
 * Servlet implementation class GetMemberJsonServ
 */
@WebServlet("/GetMemberJsonServ")
public class GetMemberJsonServ extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public GetMemberJsonServ() {
        super();
    
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
			
				response.setContentType("text/html;charset=UTF-8");
				PrintWriter out = response.getWriter();
				//{"name" : "pochako", "age": 20, "phone": " 010-1234-5678"}
				//"{\"name":"phchako"}"; //
				//"[{\"id\":\"?"\, \"name\":?, birth:?, gender:? },{},{}]";
				//out.println("{\"name\" : \"pochako\", \"age\": 20, \"phone\": \" 010-1234-5678\"}");
				MemDAO dao = new MemDAO();
				List<MemberVO> list = dao.getMemberList();
				/*
				 * int cnt = list.size(); out.println("[");
				 *///여러 건 넣어야 하므로 배열 기호를 넣어줌
				/*
				 * for(int i=0; i<cnt; i++) { out.println("{\"id\":\""+ list.get(i).getUserId()
				 * + "\", \"name\":\""+list.get(i).getUserName()+"\"}"); if(i != cnt-1 ) {
				 * out.println(","); //cnt-1이 i가 아니면 이제 콤마를 찍으주고.. i면 다음 데이터를 직어줄게... } }
				 * out.println("]");
				 */
				Gson gson = new GsonBuilder().create();
				out.println(gson.toJson(list));
	}
	

	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/json;charset=UTF-8");
		//입력하는 기능
		MemDAO dao = new MemDAO();
		String userId = request.getParameter("i");//""값을 사용자가 넣어주면~
		String userName = request.getParameter("n");
		String address = request.getParameter("a");
		String phone = request.getParameter("p");
		String gender = request.getParameter("g");
		String birth = request.getParameter("b");
		
		MemberVO vo = new MemberVO();
		vo.setUserId(userId);
		vo.setUserName(userName);
		vo.setAddress(address);
		vo.setGender(gender);
		vo.setBirthDate(birth);
		vo.setPhone(phone);
		
		
		Gson gson = new GsonBuilder().create();
		JsonObject jsonObj = new JsonObject();
		
		//{"retCode":"OK","retVal": {vo}}
		//{"retCode":"NG","retVal" : "담당자에게문의하세용..."}
		
		//System.out.println(vo.toString());
//		if(dao.insertMember(vo)) {
//			response.getWriter().print("OK");
//		} else {
//			response.getWriter().print("NG");
//		}
		
		if(dao.insertMember(vo)) {
			jsonObj.addProperty("retCode", "OK");
			jsonObj.addProperty("userId", vo.getUserId());
			jsonObj.addProperty("userName", vo.getUserName());
			jsonObj.addProperty("birthDate", vo.getBirthDate());
			jsonObj.addProperty("address", vo.getAddress());
			jsonObj.addProperty("phone", vo.getPhone());
			jsonObj.addProperty("gender", vo.getGender());
			//jsonObj.addProperty("retMsg", "등록성공");
			
			
			//response.getWriter().print("{\"retCode\":\"OK\"}");
		} else {
			jsonObj.addProperty("retCode", "NG");
			jsonObj.addProperty("retMsg", "오류발생!! \n 담당자에게 문의하세용..");
			
			
			
			//response.getWriter().print("{\"retCode\":\"NG\",\"retVal\":\"담당자에게 문의하삼\"}"); //JSON타입
			
		}
		response.getWriter().println(gson.toJson(jsonObj));
		System.out.println(vo.toString());
		//값이 없으면 text형식으로 넘어가게끔.
		//dao.insertMember(null);
		//doGet(request, response);
	}

}
