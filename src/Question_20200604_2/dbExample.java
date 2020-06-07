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
		
		request.setCharacterEncoding("UTF-8");// �ѱۼ���
		response.setContentType("text/html; charset=UTF-8"); //html���� utf-8�� ����

		try {
			Class.forName(jdbc_driver).getConstructor().newInstance(); // ����̹� �ε�
			Connection con = DriverManager.getConnection(jdbc_url, "root", "root"); // ����̹� ����
			PreparedStatement st = con.prepareStatement(jdbc_url); // ����̹� ��ü ����
			String type = request.getParameter("submitOption");
			String[] data = getData(request); // request_getParameter �� data ��������
			tableCheck(con,st); // member ���̺� üũ�� ���ٸ� ����
			System.out.println("type: "+type);
			if(type.equals("����")) {
				System.out.println("submit send");
				insertUpdateCheck(con,data,st,response);// ����
			}
			else if(type.equals("DB���")) {
				System.out.println("submit dbSearch");
				ResultSet result = st.executeQuery("SELECT * FROM test.member");
				printResultSet(result,response);		
			}
			else if(type.equals("DB����")) {
				System.out.println("submit dbDelete");
				deleteAllData(st,response);
			}
			
			st.close(); // ���������� ���� �Ҵ� ����
			con.close(); // jdbc driver �Ҵ� ����		
		} catch (Exception e) {
			e.printStackTrace(); //����ó��
		}  
	}
	private static void deleteAllData(PreparedStatement st,HttpServletResponse response) throws SQLException, IOException {
		boolean result = st.execute("truncate test.member");
		if(result) {
			response.getWriter().println("���� ����");
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
		String[] listNames = {"ID","","�̸�","��ȭ��ȣ","�̸���","�к�","����","�¾ ����","�ڱ�Ұ�"};
		printData(listNames,response);
		out.println(" </tr> ");
		while(result.next()) {
			ArrayList<String> list = new ArrayList<String>();
			for(int i = 1 ; i < 10; i++) {
				list.add(result.getString(i));
			}
			String[] li = list.toArray(new String[]{""});
			out.println(" </tr> ");
			out.println(" <td> "+count+" ��° </td> ");
			printData(li,response);
			out.println(" </tr> ");
			count++;
		}
		out.println(" </table> ");
	}
	private static void printData(String[] data,HttpServletResponse response) throws IOException {
		PrintWriter out = response.getWriter();
		for(int i = 0; i < data.length;i++) {
			if(i!=1) out.println("<td> " + data[i] + " </td>");//password ���� ���
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
				//id name�� ���� �н����嵵 ���� ��.
				updateData(con,response,data);
			}
			else if(!data[1].equals(result.getString(2)) || !data[2].equals(result.getString(3))){
				//��й�ȣ�� �ٸ���
				response.getWriter().println("��й�ȣ �Ǵ� �̸��� Ʋ�Ƚ��ϴ�.");
			}
		}
		else {
			System.out.println("DB�� �ش� ID ���� ����. ���� �����մϴ�.");
			boolean check = insert(con,data); // data �迭 ����
			if(check) {
				for(int i = 0; i < data.length;i++) {
					if(i!=1) response.getWriter().println(data[i]);//password ���� ���
				}
				response.getWriter().println("������");
			}
			else {
				response.getWriter().println(data[0]+" : ������");
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
		st.executeUpdate(CREATE_TABLE_SQL); //�ش� ��ü�� ������ ����
	}
	
	private static void tableCheck(Connection con, PreparedStatement st) throws SQLException {
		// table�� �ִ��� Ȯ��
		String tableSql = "SELECT table_name FROM information_schema.tables where table_schema = ? and table_name = ?";
		PreparedStatement pstmt = con.prepareStatement(tableSql);
		pstmt.setString(1, "test");
		pstmt.setString(2, "member");
		ResultSet rs = pstmt.executeQuery();
		
		if(!rs.next()){
			CreateTable(st); //�ش� ��ü�� ������ ����
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
			response.getWriter().println("������Ʈ ����");
		    System.out.println("������Ʈ ����");
		}
		else{
			System.out.println("������Ʈ ����");
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
		      System.out.println("insert ����");
		   }
		   else{
		      result = false;
		      System.out.println("insert ����");
		   }
		pst.close(); //prepareStatement ���� ������ ���� ������ ���� �Ҵ� ����
		return result;
	}
}

