import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

//import org.apache.struts2.ServletActionContext;

public class bookUpdate {
	String updateBook;
	String updateBookTitle;
	String updateBookAuthor;
	String updateBookPublisher;
	String updateBookDate;
	String updateBookPrice;
	public String getUpdateBookTitle() {
		return updateBookTitle;
	}

	public void setUpdateBookTitle(String updateBookTitle) {
		this.updateBookTitle = updateBookTitle;
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

	public String getUpdateBookPrice() {
		return updateBookPrice;
	}

	public void setUpdateBookPrice(String updateBookPrice) {
		this.updateBookPrice = updateBookPrice;
	}

	public String getUpdateBook() {
		return updateBook;
	}

	public void setUpdateBook(String updateBook) {
		this.updateBook = updateBook;
	}
	public String execute() throws SQLException, UnsupportedEncodingException {
		/*String urlParam= ServletActionContext.getRequest().getParameter("updateBook");
		urlParam = new String(urlParam.getBytes("ISO-8859-1"), "UTF-8");
		updateBook = urlParam;*/
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
			
			String sql = "select * from book where ISBN = \'"+updateBook+"\'";
			//System.out.println(sql);
			rs = stmt.executeQuery(sql);
			if(rs.next()) {
				updateBookTitle = rs.getString("Title");
				updateBookAuthor = rs.getString("AuthorID");
				updateBookPublisher = rs.getString("Publisher");
				updateBookDate = rs.getString("PublishDate");
				updateBookPrice = rs.getString("Price");
			}
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
}
