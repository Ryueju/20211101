package com.eju.member;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;


@WebServlet("/FileUploadServlet")
public class FileUploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public FileUploadServlet() {
        super();
        
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		
		//System.out.println(request.getParameter("author"));
		
		int maxSize  = 1024 * 1024 * 10;
		ServletContext context = getServletContext();
		String saveDir = context.getRealPath("images");
		
		MultipartRequest multi = new MultipartRequest(request,saveDir,maxSize,"UTF-8",new DefaultFileRenamePolicy());
		
		String prod_item = 	multi.getParameter("prod_item");
		String prod_desc = multi.getParameter("prod_desc");
		String like_it = multi.getParameter("likt_it");
		String origin_price = multi.getParameter("origin_price");
		String sale_price = multi.getParameter("sale_price");
		String prod_image = multi.getParameter("prod_image");
		
		ItemVO vo  = new ItemVO();
		vo.setProdName(prod_item);
		vo.setProdDesc(prod_desc);
		vo.setLikeit(Double.parseDouble(like_it));
		vo.setOriginPrice(Integer.parseInt(origin_price));
		vo.setSalePrice(Integer.parseInt(sale_price));
		vo.setProdImage(prod_image);
		
		System.out.println("실제 위치 : " + saveDir);
		PrintWriter out = response.getWriter();
		/*
		 * PrintWriter out = response.getWriter(); out.println("<h3>"+ author +
		 * "</h3>"); out.println("<h3>"+title+"</h3>");
		 * out.println("<img src=\"upload/"+fileName+"\">");
		 */
		
		//파일: 서버, 사용자 입력값: db저장
		MemDAO dao = new MemDAO();
		dao.uploadProduct(vo);
		
		
		out.println("<script>location.href=\"shop-homepage/sample.html\"</script>");
	}

}
