package com.eju.member;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


@WebServlet("/GetMemberPageServlet")
public class GetMemberPageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;


	public GetMemberPageServlet() {
		super();
		// TODO Auto-generated constructor stub
	}


	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/json;charset=UTF-8");

		//?cmd=cnt => total count
		//?cmd=list&page=2 => 

		String cmd = request.getParameter("cmd");
		cmd = (cmd == null) ? "cnt": cmd;

		MemDAO dao = new MemDAO();
		if(cmd.equals("cnt")){
			///	response.setContentType("text/html;charset=UTF-8");
			int result = dao.getTotalCount();
			//{"totalCnt":134}
			response.getWriter().println("{\"totalCnt\": "+result+"}");

		}else if(cmd.equals("list")) {
			String page = request.getParameter("page");//page라는 이름으로 파라미터를 넘김
			page = (page == null) ? "1" :page;

			//MemDAO dao = new MemDAO(); 
			List<MemberVO> list = dao.getMemberByPage(page);  //파라미터로 넘김

			Gson gson = new GsonBuilder().create();
			response.getWriter().println(gson.toJson(list));
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
