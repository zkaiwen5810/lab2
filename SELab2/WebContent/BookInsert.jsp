<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>

<title>新添书籍</title>
</head>
<body>
	<hr>
	<h4>输入新增书籍的相关信息</h4> 	
	<s:form action="BookInsert">
   	<s:textfield name="BookIsbn" label="ISBN"/>
   	<s:textfield name="BookTitle" label="书名"/>
   	<s:textfield name="BookAuthor" label="作者ID"/>
   	<s:textfield name="BookPublisher" label="出版社"/>
   	<s:textfield name="BookPublishDate" label="出版日期"/>
   	<s:textfield name="BookPrice" label="价格"/>
   	<s:submit value = "添加"/>
	</s:form>
    <hr>	
</body>
</html>