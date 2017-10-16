<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>

<title>更新表</title>
</head>
<body>
<hr>
<h4>修改书的信息</h4> 	
<s:form action="updateInfo?updateISBN=%{updateBook}">
<tr><td>ISBN</td><td>${updateBook }</td></tr>
<tr><td>书名</td><td>${updateBookTitle }</td></tr>
<s:textfield name="updateBookAuthor" label="作者ID"/>
<s:textfield name="updateBookPublisher" label="出版社"/>
<s:textfield name="updateBookDate" label="出版日期"/>
<s:textfield name="updateBookPrice" label="价格"/>
<s:submit value = "更新"/>
</s:form>
<hr>
</body>
</html>