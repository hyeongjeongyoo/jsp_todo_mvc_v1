<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
		String eMassage = (String)request.getAttribute("errorMessage");
		out.println(eMassage);
	%>
	<h1>회원가입 JSP 파일입니다.</h1>
</body>
</html>