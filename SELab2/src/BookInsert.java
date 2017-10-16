import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class BookInsert {
	String BookIsbn;
	String BookTitle;
	String BookAuthor;
	String BookPublisher;
	String BookPublishDate;
	String BookPrice;
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
			
			String sql = "insert into book (ISBN,Title,AuthorID,Publisher,PublishDate,Price) values(\'"+
					BookIsbn+"\',\'"+BookTitle+"\',\'"+BookAuthor+"\',\'"+BookPublisher+"\',\'"+BookPublishDate+"\',\'"+BookPrice+"\')";
			//System.out.println(sql);
			stmt.executeUpdate(sql);
			sql = "select * from author where AuthorID = \'"+BookAuthor+"\'";
			rs = stmt.executeQuery(sql);
			if (!rs.next()){
				return "NOAUTHOR";
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
	public String getBookIsbn() {
		return BookIsbn;
	}
	public void setBookIsbn(String bookIsbn) {
		BookIsbn = bookIsbn;
	}
	public String getBookTitle() {
		return BookTitle;
	}
	public void setBookTitle(String bookTitle) {
		BookTitle = bookTitle;
	}
	public String getBookAuthor() {
		return BookAuthor;
	}
	public void setBookAuthor(String bookAuthor) {
		BookAuthor = bookAuthor;
	}
	public String getBookPublisher() {
		return BookPublisher;
	}
	public void setBookPublisher(String bookPublisher) {
		BookPublisher = bookPublisher;
	}
	public String getBookPublishDate() {
		return BookPublishDate;
	}
	public void setBookPublishDate(String bookPublishDate) {
		BookPublishDate = bookPublishDate;
	}
	public String getBookPrice() {
		return BookPrice;
	}
	public void setBookPrice(String bookPrice) {
		BookPrice = bookPrice;
	}
}
