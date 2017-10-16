<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>

<title>书的信息</title>
</head>
<body>

<table border="1">  
	  
	<tr><td>ISBN号</td><td>${bookIsbn }</td></tr>  
	<tr><td>书名</td><td>${requestParam }</td></tr>  
	<tr><td>作者ID </td><td>${authorId } </td></tr>
	<tr><td>作者名字 </td><td>${authorName } </td></tr>
	<tr><td>作者年纪 </td><td>${authorAge } </td></tr>
	<tr><td>作者国籍 </td><td>${authorCountry } </td></tr>
	<tr><td>出版社 </td><td>${publishInc } </td></tr>
	<tr><td>出版日期 </td><td>${publishDate } </td></tr>
	<tr><td>价格 </td><td>${bookPrice } </td></tr>  
	<tr>
	<td colspan="1"></td>
	<td><s:a action="bookDelete?id=%{bookIsbn}" onclick="return confirm('确定要删除吗？')">删除</s:a></td>
	</tr>
	<tr>
	<td colspan="1"></td>
	<td><s:a action="bookUpdate?updateBook=%{bookIsbn}">修改</s:a></td>  
	</tr>
	
</table>
            <s:a action="bookAdd">添加</s:a>  
</body>
</html>