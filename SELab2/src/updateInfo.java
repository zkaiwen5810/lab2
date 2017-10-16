import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

//import org.apache.struts2.ServletActionContext;

public class updateInfo {
	String updateISBN;
	String updateBookAuthor;
	String updateBookPublisher;
	String updateBookDate;
	String updateBookPrice;
	String BookAuthor;
	public String getBookAuthor() {
		return BookAuthor;
	}
	public void setBookAuthor(String bookAuthor) {
		BookAuthor = bookAuthor;
	}
	public String getUpdateBookPrice() {
		return updateBookPrice;
	}
	public void setUpdateBookPrice(String updateBookPrice) {
		this.updateBookPrice = updateBookPrice;
	}
	public String getUpdateISBN() {
		return updateISBN;
	}
	public void setUpdateISBN(String updateISBN) {
		this.updateISBN = updateISBN;
	}
	public String getUpdateBookAuthor() {
		return updateBookAuthor;
	}
	public void setUpdateBookAuthor(String updateBookAuthor) {
		this.updateBookAuthor = updateBookAuthor;
	}
	public String getUpdateBookPublisher() {
		return updateBookPublisher;
	}
	public void setUpdateBookPublisher(String updateBookPublisher) {
		this.updateBookPublisher = updateBookPublisher;
	}
	public String getUpdateBookDate() {
		return updateBookDate;
	}
	public void setUpdateBookDate(String updateBookDate) {
		this.updateBookDate = updateBookDate;
	}
	public String execute() throws SQLException, UnsupportedEncodingException {
		/*String urlParam= ServletActionContext.getRequest().getParameter("updateISBN");
		urlParam = new String(urlParam.getBytes("ISO-8859-1"), "UTF-8");
		updateISBN = urlParam;*/
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
			String sql = "select AuthorID from book where ISBN = \'"+updateISBN+"\'";
			rs = stmt.executeQuery(sql);
			rs.next();
			String id = rs.getString("AuthorID");
			sql = "update book set AuthorID = \'"+updateBookAuthor+"\',"+
						"Publisher = \'"+updateBookPublisher+"\',"+
						"PublishDate = \'"+updateBookDate+"\',"+
						"Price = \'"+updateBookPrice+"\' where ISBN = \'"+updateISBN+"\'";
			stmt.executeUpdate(sql);
			BookAuthor = updateBookAuthor;
			if (id.equals(updateBookAuthor))
				return "SUCCESS";
			else
				return "NOAUTHOR";
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
}
