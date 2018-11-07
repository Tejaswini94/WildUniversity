<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"
	import = "java.util.List,com.example.bean.Student"    
%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<jsp:include page="header.html"></jsp:include>

<!-- Iterate the student list  -->
<!-- Scriplet <% %> -->
<BR>
<table>
<tr><th>UNo</th>
	<th>Name</th>
</tr>	

<c:out value="Hello World"></c:out>



<c:forEach items="${StudentList}" var="student">
   <tr><td> ${student.uno}</td>
   	   <td>${student.name}</td>
   </tr>
</c:forEach>

</table>

<BR>
<a href="HomePage.jsp">Go Back</a>






</body>
</html>