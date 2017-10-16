import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
//import org.apache.struts2.ServletActionContext;

public class showBookInfo {
	String requestParam;
	String bookIsbn;
	public String getBookIsbn() {
		return bookIsbn;
	}
	public void setBookIsbn(String bookIsbn) {
		this.bookIsbn = bookIsbn;
	}
	public String getAuthorId() {
		return authorId;
	}
	public void setAuthorId(String authorId) {
		this.authorId = authorId;
	}
	public String getPublishInc() {
		return publishInc;
	}
	public void setPublishInc(String publishInc) {
		this.publishInc = publishInc;
	}
	public String getPublishDate() {
		return publishDate;
	}
	public void setPublishDate(String publishDate) {
		this.publishDate = publishDate;
	}
	public String getBookPrice() {
		return bookPrice;
	}
	public void setBookPrice(String bookPrice) {
		this.bookPrice = bookPrice;
	}

	String authorId;
	String publishInc;
	String publishDate;
	String bookPrice;
	String authorName;
	public String getAuthorName() {
		return authorName;
	}
	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}
	public int getAuthorAge() {
		return authorAge;
	}
	public void setAuthorAge(int authorAge) {
		this.authorAge = authorAge;
	}
	public String getAuthorCountry() {
		return authorCountry;
	}
	public void setAuthorCountry(String authorCountry) {
		this.authorCountry = authorCountry;
	}

	int authorAge;
	String authorCountry;
	public String execute() throws UnsupportedEncodingException, SQLException {
		
		/*String urlParam= ServletActionContext.getRequest().getParameter("requestParam");
		urlParam = new String(urlParam.getBytes("ISO-8859-1"), "UTF-8");
		requestParam = urlParam;*/
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
			
			String sql = "select * from book where Title = \""+requestParam+"\"";
			rs = stmt.executeQuery(sql);
			if (!rs.next()){
				return "ERROR";
			}
			bookIsbn = rs.getString("ISBN");
			authorId = rs.getString("AuthorID");
			publishInc = rs.getString("Publisher");
			publishDate = rs.getString("PublishDate");
			bookPrice = rs.getString("Price");
			sql = "select * from author where AuthorID = \'"+authorId+"\'";
			rs = stmt.executeQuery(sql);
			if (rs.next()) {
				authorName = rs.getString("Name");
				authorAge = rs.getInt("Age");
				authorCountry = rs.getString("Country");
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
	public String getRequestParam() {
		return requestParam;
	}

	public void setRequestParam(String requestParam) {
		this.requestParam = requestParam;
	}
}
