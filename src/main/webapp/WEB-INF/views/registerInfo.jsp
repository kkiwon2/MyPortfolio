<%@taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Insert title here</title>
</head>
<body>
<h1>id=${userDto.id}</h1>
<h1>pwd=${userDto.pwd}</h1>
<h1>name=${userDto.name}</h1>
<h1>email=${userDto.email}</h1>
${userDto.birth}
<h1>birth=<fmt:formatDate value="${userDto.birth}" pattern="yyyy-MM-dd" type="date"/></h1>

<a ></a>
</body>
</html>