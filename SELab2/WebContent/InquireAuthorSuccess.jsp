<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
	<head>
    <title>作者所有书籍ID</title>
	</head>

	<body>
	  
	 <%--  <s:iterator value = "authorBooks" id = 'n'>
	  	
	  	<br><a href="<s:url action='showBookInfo'/><s:param name="id">123</s:param>">
	  	<s:property value = 'n'/></a></br>
	  </s:iterator> --%>
	  	<s:iterator value = "authorBooks" var = 'n'>
		  	<s:url action="showBookInfo.action" var="urlTag" >
	   		 <s:param name="requestParam"><s:property value = 'n'/></s:param>
			</s:url>
			<br><s:a href="%{urlTag}"><s:property value = 'n'/></s:a><br>
		</s:iterator>
	</body>
	
</html>
