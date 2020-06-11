package Question_20200604_1;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
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
		// request에서 데이터를 받아옴
		String v_ = request.getParameter("value");
		int v = Integer.parseInt(v_);
		String op = request.getParameter("operator");
		
		
		Cookie[] cookies = request.getCookies();
		ServletContext application = request.getServletContext();
		HttpSession session = request.getSession();
		
		if(op.equals("=")) {
			// 각각 데이터 초기화
			int cookies_result = 0;
			int application_result = 0;
			int session_result = 0;
			
			// 쿠키에서 데이터를 받아옴
			int cookies_prev_v = 0;
			String cookies_prev_op = "";
			for(Cookie c:cookies) {
				if(c.getName().equals("value"))
					cookies_prev_v = Integer.parseInt(c.getValue());
				if(c.getName().equals("operator"))
					cookies_prev_op = c.getValue();
			}
			//Servlet Context에서 데이터를 받아옴
			int application_prev_v = (int)request.getServletContext().getAttribute("value");
			String application_prev_op = (String)request.getServletContext().getAttribute("operator");
			
			//Session 에서 데이터를 받아옴
			int session_prev_v = (int)session.getAttribute("value");
			String session_prev_op = (String)session.getAttribute("operator");
			
			
			//쿠키, ServletContext, Session에서 각각 데이터를 받아 계산
			if(cookies_prev_op.equals("+") && application_prev_op.equals("+") && session_prev_op.equals("+")) {
				cookies_result = cookies_prev_v + v;
				application_result = application_prev_v + v;
				session_result = session_prev_v + v;
			}
			else {
				cookies_result = cookies_prev_v - v;
				application_result = application_prev_v - v;
				session_result = session_prev_v - v;
			}
			
			response.getWriter().printf("%d %s %d Result is %d of using cookies\n",cookies_prev_v,cookies_prev_op,v, cookies_result);	
			response.getWriter().printf("%d %s %d Result is %d of using application\n",application_prev_v,application_prev_op,v, application_result);	
			response.getWriter().printf("%d %s %d Result is %d of using session\n",session_prev_v,session_prev_op,v, session_result);	
		}
		else {
			
			// servlet context 데이터 전송
			application.setAttribute("value", v);
			application.setAttribute("operator", op);
			
			//데이터를 세션에 넣어 전송
			session.setAttribute("value", v);
			session.setAttribute("operator", op);
			
			//데이터를 쿠키로 만들고
			Cookie valueCookie = new Cookie("value",String.valueOf(v));
			Cookie opCookie = new Cookie("operator",op);
			
			//쿠키를 페이지로 전송
			response.addCookie(valueCookie);
			response.addCookie(opCookie);
			RequestDispatcher dispatcher = request.getRequestDispatcher("Calculator2.html");
			dispatcher.forward(request, response);
		
			//response.sendRedirect("Calculator2.html");

		}
	}

}
