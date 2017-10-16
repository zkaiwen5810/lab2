<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>

<title>添加作者</title>
</head>
<body>
<hr>
<h4>作者不存在请先添加作者信息</h4> 	
<s:form action="AddAuthor?newAuthor=%{BookAuthor}">
<%-- <s:textfield name="aID" label="作者ID"/> --%>
<%-- 作者ID<s:property value = "BookAuthor" ></s:property> --%>
<tr><td>作者ID</td><td>${BookAuthor }</td></tr>
<s:textfield name="aName" label="作者名字"/>
<s:textfield name="aAge" label="作者年龄"/>
<s:textfield name="aCountry" label="作者国籍"/>
<s:submit value = "添加"/>
</s:form>
<hr>
</body>
</html>