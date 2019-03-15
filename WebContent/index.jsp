<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<%-- <%
		//response.sendRedirect(request.getContextPath()+"/jsp/index.jsp");
		request.getRequestDispatcher("index").forward(request, response);
	%> --%>
	<jsp:forward page="/index"></jsp:forward>
</body>
</html>