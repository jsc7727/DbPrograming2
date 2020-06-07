package Question_20200604_1;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class myexample2
 */
@WebServlet("/my2")
public class myexample2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public myexample2() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8"); //html에도 utf-8로 설정
		String id = request.getParameter("id");
		String password = request.getParameter("pwd");
		String name = request.getParameter("name");
		String tel = request.getParameter("tel");
		String email = request.getParameter("email");
		
		String dept = request.getParameter("dept");
		String gender = request.getParameter("gender");
		String birth = request.getParameter("birth");
		String introduction = request.getParameter("introduction");

		PrintWriter out = response.getWriter(); 
		out.println(id);
		out.println(password);
		out.println(name);
		out.println(tel);
		out.println(email);
		out.println(dept);
		out.println(gender);
		out.println(birth);
		out.println(introduction);
		out.close();
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
