package com.eju.member;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet({ "/GetMemberServlet", "/get_member" })

public class GetMemberServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
    public GetMemberServlet() {
        super();
      
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				//doGet :엔터쳤을 때 창이 바뀌게 됨
		response.setContentType("text/xml;charset=UTF-8");
		//응답을처리해주는객체
		PrintWriter out = response.getWriter();
		
		MemDAO dao = new MemDAO();
		List<MemberVO>list = dao.getMemberList(); //list타입을 리턴함
		out.println("<record>");
		
		for(MemberVO member : list) {
			out.println("<title>Hello,타이틀이다</title>");
				out.println("<row>" +
		"<id>"+ member.getUserId() + "</id>" + 
		"<name>" + member.getUserName() + "</name> " + 
						"<birth>"+ member.getBirthDate() + "</birth>" + 
		"<gender>" + member.getGender() +"</gender>" +  "</row>");
		}
		out.println("</record>");
	
//		response.getWriter().append("Served at: ").append(request.getContextPath()).append(" I WANNA GO HOME RIGHT NOW");
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
