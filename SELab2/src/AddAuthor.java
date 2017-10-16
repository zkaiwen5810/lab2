import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

//import org.apache.struts2.ServletActionContext;

public class AddAuthor {
	private String newAuthor;
	public String getNewAuthor() {
		return newAuthor;
	}
	public void setNewAuthor(String newAuthor) {
		this.newAuthor = newAuthor;
	}
	String aName;
	int aAge;
	public int getaAge() {
		return aAge;
	}
	public void setaAge(int aAge) {
		this.aAge = aAge;
	}
	String aCountry;
	public String execute() throws SQLException, UnsupportedEncodingException {
		Connection conn = null;
		Statement stmt = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://ucwxkzgsszff.mysql.sae.sina.com.cn:10602/qaz2?"
					+ "useUnicode=true&characterEncoding=utf-8&useSSL=false";
			String user = "root";
			String psw = "5810267";
			conn = DriverManager.getConnection(url,user,psw);
			stmt = conn.createStatement();
			/*String urlParam= ServletActionContext.getRequest().getParameter("newAuthor");
			urlParam = new String(urlParam.getBytes("ISO-8859-1"), "UTF-8");*/
			String sql = "insert into author(AuthorID,Name,Age,Country) values(\'"+
					newAuthor+"\',\'"+aName+"\',\'"+aAge+"\',\'"+aCountry+"\')";
			
			stmt.executeUpdate(sql);
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
	public String getaName() {
		return aName;
	}
	public void setaName(String aName) {
		this.aName = aName;
	}
	public String getaCountry() {
		return aCountry;
	}
	public void setaCountry(String aCountry) {
		this.aCountry = aCountry;
	}
}
