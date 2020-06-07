package Question_20200604_2;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Array;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Servlet implementation class dbExample
 */
@WebServlet("/dbExample")
public class dbExample extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static final String CREATE_TABLE_SQL= ""
			+ "CREATE TABLE test.member ("
            + "id VARCHAR(20) NOT NULL,"
            + "password VARCHAR(20) NOT NULL,"
            + "name VARCHAR(45) NOT NULL,"
            + "tel VARCHAR(45) NOT NULL,"
            + "email VARCHAR(60) NOT NULL,"
            + "dept VARCHAR(20) NOT NULL,"
            + "gender VARCHAR(20) NOT NULL,"
            + "birth VARCHAR(10) NOT NULL,"
            + "introduction TEXT NOT NULL,"
            + "PRIMARY KEY (id))";
       
    public dbExample() {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String jdbc_driver = "com.mysql.cj.jdbc.Driver";
		String jdbc_url = "jdbc:mysql://localhost:3307/test?serverTimezone=UTC";
		
		request.setCharacterEncoding("UTF-8");// 한글설정
		response.setContentType("text/html; charset=UTF-8"); //html에도 utf-8로 설정

		try {
			Class.forName(jdbc_driver).getConstructor().newInstance(); // 드라이버 로드
			Connection con = DriverManager.getConnection(jdbc_url, "root", "root"); // 드라이버 연결
			PreparedStatement st = con.prepareStatement(jdbc_url); // 드라이버 객체 생성
			String type = request.getParameter("submitOption");
			String[] data = getData(request); // request_getParameter 로 data 가져오기
			tableCheck(con,st); // member 테이블 체크후 없다면 생성
			System.out.println("type: "+type);
			if(type.equals("전송")) {
				System.out.println("submit send");
				insertUpdateCheck(con,data,st,response);// 삽입
			}
			else if(type.equals("DB출력")) {
				System.out.println("submit dbSearch");
				ResultSet result = st.executeQuery("SELECT * FROM test.member");
				printResultSet(result,response);		
			}
			else if(type.equals("DB삭제")) {
				System.out.println("submit dbDelete");
				deleteAllData(st,response);
			}
			
			st.close(); // 정적쿼리문 실행 할당 해제
			con.close(); // jdbc driver 할당 해제		
		} catch (Exception e) {
			e.printStackTrace(); //예외처리
		}  
	}
	private static void deleteAllData(PreparedStatement st,HttpServletResponse response) throws SQLException, IOException {
		boolean result = st.execute("truncate test.member");
		if(result) {
			response.getWriter().println("삭제 성공");
		}
	}
	private static void printResultSet(ResultSet result,HttpServletResponse response) throws IOException, SQLException {
		int count = 1;
		PrintWriter out = response.getWriter();
		out.println("<style>\r\n" + 
				"table, th, td {\r\n" + 
				"  border: 1px solid black;\r\n" + 
				"  border-collapse: collapse;\r\n" + 
				"}\r\n" + 
				"th, td {\r\n" + 
				"  padding: 5px;\r\n" + 
				"  text-align: left;    \r\n" + 
				"}\r\n" + 
				"</style>");
		out.println(" <table style=\"width:100%\" > ");
		
		out.println(" </tr> ");
		out.println(" <td>  </td> ");
		String[] listNames = {"ID","","이름","전화번호","이메일","학부","성별","태어난 계절","자기소개"};
		printData(listNames,response);
		out.println(" </tr> ");
		while(result.next()) {
			ArrayList<String> list = new ArrayList<String>();
			for(int i = 1 ; i < 10; i++) {
				list.add(result.getString(i));
			}
			String[] li = list.toArray(new String[]{""});
			out.println(" </tr> ");
			out.println(" <td> "+count+" 번째 </td> ");
			printData(li,response);
			out.println(" </tr> ");
			count++;
		}
		out.println(" </table> ");
	}
	private static void printData(String[] data,HttpServletResponse response) throws IOException {
		PrintWriter out = response.getWriter();
		for(int i = 0; i < data.length;i++) {
			if(i!=1) out.println("<td> " + data[i] + " </td>");//password 빼고 출력
		}
	}
	private static boolean insertUpdateCheck(Connection con,String[] data,PreparedStatement st,HttpServletResponse response) throws SQLException, IOException {
		String query = "SELECT id,password,name "
		+ "FROM test.member "
		+ "WHERE id = \""+data[0]+"\"";
		System.out.println(query);
		
		ResultSet result = st.executeQuery(query);
		if(result.next()) {
			System.out.println(data[1] + " / " + result.getString(2));
			if(data[1].equals(result.getString(2)) && data[2].equals(result.getString(3))) {
				//id name이 같고 패스워드도 같을 때.
				updateData(con,response,data);
			}
			else if(!data[1].equals(result.getString(2)) || !data[2].equals(result.getString(3))){
				//비밀번호가 다를때
				response.getWriter().println("비밀번호 또는 이름이 틀렸습니다.");
			}
		}
		else {
			System.out.println("DB에 해당 ID 값이 없음. 새로 삽입합니다.");
			boolean check = insert(con,data); // data 배열 삽입
			if(check) {
				for(int i = 0; i < data.length;i++) {
					if(i!=1) response.getWriter().println(data[i]);//password 빼고 출력
				}
				response.getWriter().println("성공함");
			}
			else {
				response.getWriter().println(data[0]+" : 실패함");
			}
		}

		return false;
	}
	
	private static String[] getData(HttpServletRequest request) {
		String id = request.getParameter("id");
		String password = request.getParameter("pwd");
		String name = request.getParameter("name");
		String tel = request.getParameter("tel");
		String email = request.getParameter("email");
		
		String dept = request.getParameter("dept");
		String gender = request.getParameter("gender");
		String birth = request.getParameter("birth");
		String introduction = request.getParameter("introduction");
		String[] data = {id,password,name,tel,email,dept,gender,birth,introduction};
		return data;
	}
	
	private static void CreateTable(PreparedStatement st) throws SQLException {
		st.executeUpdate(CREATE_TABLE_SQL); //해당 객체로 쿼리문 전송
	}
	
	private static void tableCheck(Connection con, PreparedStatement st) throws SQLException {
		// table이 있는지 확인
		String tableSql = "SELECT table_name FROM information_schema.tables where table_schema = ? and table_name = ?";
		PreparedStatement pstmt = con.prepareStatement(tableSql);
		pstmt.setString(1, "test");
		pstmt.setString(2, "member");
		ResultSet rs = pstmt.executeQuery();
		
		if(!rs.next()){
			CreateTable(st); //해당 객체로 쿼리문 전송
		} 
	}
	private static boolean updateData(Connection con,HttpServletResponse response,String[] data) throws SQLException, IOException {
		PreparedStatement pst = con.prepareStatement("UPDATE test.member SET tel = ? "
				+ ", email = ? "
				+ ", dept = ? "
				+ ", gender = ? "
				+ ", birth = ? "
				+ ", introduction = ? "
				+ "WHERE id = ? && " + "password = ? && name = ? ");
		
		
		for(int i = 3; i < data.length;i++) {
			pst.setString(i-2,data[i]);
		}
		for(int i = 0; i < 3;i++) {
			pst.setString(i+7,data[i]);
		}
		int result2 = pst.executeUpdate();
		if(result2==1) {
			printData(data,response);
			response.getWriter().println("업데이트 성공");
		    System.out.println("업데이트 성공");
		}
		else{
			System.out.println("업데이트 실패");
		}
		return false;
	}
	private static boolean insert(Connection con,String[] data) throws SQLException {
		PreparedStatement pst = con.prepareStatement("INSERT INTO test.member VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)");
		for(int i = 0; i < data.length;i++) {
			System.out.println("data : "+data[i]+"/");
			pst.setString(i+1,data[i]);
		}
		
		int result2 = pst.executeUpdate();
		
		boolean result;
		   if(result2==1) {
		      result = true;
		      System.out.println("insert 성공");
		   }
		   else{
		      result = false;
		      System.out.println("insert 실패");
		   }
		pst.close(); //prepareStatement 재사용 가능한 동적 쿼리문 실행 할당 해제
		return result;
	}
}

