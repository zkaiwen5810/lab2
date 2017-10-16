
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.Statement;

public class InquireAuthor {
	
	
	//@SuppressWarnings("finally")
	public String execute() throws SQLException {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://ucwxkzgsszff.mysql.sae.sina.com.cn:10602/qaz2?"
					+ "useUnicode=true&characterEncoding=utf-8&useSSL=false";
			String user = "root";
			String psw = "5810267";
			conn = DriverManager.getConnection(url,user,psw);
			stmt = conn.createStatement();
			//System.out.println(name);
			String sql = "select Title from book where AuthorID = \""+name+"\"";
			//System.out.println(sql);
			rs = stmt.executeQuery(sql);
			if (!rs.next()){
				return "ERROR";
			}
			int cnt = 0;
			do {
				authorBooks[cnt++] = rs.getString("Title");
				//System.out.println(authorBooks[cnt-1]);
			}while(rs.next());
			return "SUCCESS";
		}catch(ClassNotFoundException ex) {
			ex.getMessage();
			ex.printStackTrace();
			return "ERROR";
		}catch(SQLException e) { 
			e.getSQLState();
			e.printStackTrace();
			return "ERROR";
		}finally {
			conn.close();
		}
	}
	private String name;
	public String[] getAuthorBooks() {
		return authorBooks;
	}

	public void setAuthorBooks(String[] authorBooks) {
		this.authorBooks = authorBooks;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	private String[] authorBooks = new String[20];

}
