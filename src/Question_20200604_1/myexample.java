package Question_20200604_1;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class myexample
 */
//@WebServlet("/my")
public class myexample extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public myexample() {
        super();
    }

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("service called");
		super.service(request, response);
	}
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");// ������ html�� ������ ������ utf-8�� ���ڵ�
		response.setContentType("text/html; charset=UTF-8"); //html���� utf-8�� ����
		String name = request.getParameter("name");
		System.out.println(name);
		PrintWriter out = response.getWriter();
		out.print("<html><head><title>Test</title></head>");
	    out.print("<body><h1>doGet "+name +" �ȳ�!!</h1></body>");
	    out.print("</html>");
	    out.close();
	}
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
