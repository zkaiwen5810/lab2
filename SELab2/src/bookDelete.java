import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.DriverManager;
//import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
//import org.apache.struts2.ServletActionContext;
//import java.sql.ResultSet;

public class bookDelete {
	String id;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	public String execute() throws SQLException, UnsupportedEncodingException {
		/*String urlParam= ServletActionContext.getRequest().getParameter("id");
		urlParam = new String(urlParam.getBytes("ISO-8859-1"), "UTF-8");
		id = urlParam;*/
		Connection conn = null;
		Statement stmt = null;
		//ResultSet rs = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://ucwxkzgsszff.mysql.sae.sina.com.cn:10602/qaz2?"
					+ "useUnicode=true&characterEncoding=utf-8&useSSL=false";
			String user = "root";
			String psw = "5810267";
			conn = DriverManager.getConnection(url,user,psw);
			stmt = conn.createStatement();
			String sql;
			/*String sql = "select AuthorID from book where ISBN = \'"+id+"\'";
			rs = stmt.executeQuery(sql);
			rs.next();
			String deleteID =rs.getString("AuthorID");
			sql = "delete from author where AuthorID = \'"+ deleteID+"\'";
			stmt.executeUpdate(sql);*/
			sql = "delete from book where ISBN = \'"+id+"\'";
			stmt.executeUpdate(sql);
			return "SUCCESS";
		}catch(ClassNotFoundException ex) {
			ex.getMessage();
			ex.printStackTrace();
			return "ERROR";
		}catch(SQLException e) { 
			System.out.println(e.getSQLState());
			e.printStackTrace();
			return "ERROR";
		}finally {
			conn.close();
		}
	}
}
