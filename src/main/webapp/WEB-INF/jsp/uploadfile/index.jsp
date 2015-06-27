<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.Enumeration" %>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
</head>
<body>
<h2>FileUpload!</h2>
<form action="/fileupload/upload" method="POST" enctype="multipart/form-data">
	<input type="file" id="file" name="file"/><br/><br/>
	<input type="submit" value="上传"/>
</form>
<%
Enumeration attrs = session.getAttributeNames();
while(attrs.hasMoreElements()){
	String attr = (String)attrs.nextElement();
	out.print("<br>" + attr + ":" + session.getAttribute(attr) + "===");
}
%>
</body>
</html>
