<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!-- <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd"> -->
<html>
<head>
<!-- <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1"> -->
<title>Inquiry</title>

</head>
<body>
		<hr>
		
		<h4>输入你想查询的作者的ID</h4>	
		<s:form action="InquireAuthor">
    	<s:textfield name="name" label="作者ID" />
    	<s:submit value = "查询"/>
		</s:form>
		<s:a action="bookAdd">添加书籍</s:a>
	    <hr>	
</body>
</html>