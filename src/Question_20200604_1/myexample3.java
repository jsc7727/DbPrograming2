package Question_20200604_1;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class myexample3
 */
@WebServlet("/my3")
public class myexample3 extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public myexample3() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String v_ = request.getParameter("value");
		int v = Integer.parseInt(v_);
		String op = request.getParameter("operator");
		
		//ServletContext application = request.getServletContext();
		//HttpSession session = request.getSession();
		Cookie[] cookies = request.getCookies();
		
		if(op.equals("=")) {
			int result = 0;
//			int prev_v = (int)application.getAttribute("value");
//			String prev_op = (String)application.getAttribute("operator");
			
//			int prev_v = (int)session.getAttribute("value");
//			String prev_op = (String)session.getAttribute("operator");
			
			int prev_v = 0;
			String prev_op = "";
			for(Cookie c:cookies) {
				if(c.getName().equals("value"))
					prev_v = Integer.parseInt(c.getValue());
				if(c.getName().equals("operator"))
					prev_op = c.getValue();
			}
			if(prev_op.equals("+")) {
				result = prev_v + v;
			}
			else {
				result = prev_v - v;
			}
			
			response.getWriter().printf("Result is %d\n", result);	
		}
		else {
//			application.setAttribute("value", v);
//			application.setAttribute("operator", op);
			
//			session.setAttribute("value", v);
//			session.setAttribute("operator", op);
			
			Cookie valueCookie = new Cookie("value",String.valueOf(v));
			Cookie opCookie = new Cookie("operator",op);
			
			response.addCookie(valueCookie);
			response.addCookie(opCookie);

		}
	}

}
