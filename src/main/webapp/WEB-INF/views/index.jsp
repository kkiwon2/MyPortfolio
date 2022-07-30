<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false"%>
<!-- session="false"값을 주어서 sessionScope.id 사용 할 수 없음 밑으로 대체 -->
<%-- sessionScope와 pageContext.session을 사용 불가능하다는 소리이다.--%>
<%-- pageContext.request.getSession(false).getAttribute("id")로 변경해야된다. -> 에러떠도 상관없음 -->
<%-- <c:set var="loginId" value="${sessionScope.id}"/>--%>
<c:set var="loginId" value="${pageContext.request.getSession(false)==null ? '' : pageContext.request.session.getAttribute('id')}"/>
<c:set var="loginOutLink" value="${loginId=='' ? '/login/login' : '/login/logout'}"/>
<c:set var="loginOut" value="${loginId=='' ? 'Login' : 'Logout'}"/>
<%--<c:set var="loginOut" value="${loginId=='' ? 'Login' : 'ID='+=loginId}"/>--%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>MyPortfolio</title>
  <link rel="stylesheet" href="<c:url value='/css/menu.css'/>">
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.8.2/css/all.min.css"/>
</head>
<body>
<div id="menu">
  <ul class="menu-ul">
    <li id="logo">MyPortfolio</li>
    <li><a class="menu-a" href="<c:url value='/'/>">Home</a></li>
    <li><a class="menu-a" href="<c:url value='/board/list'/>">Board</a></li>
    <li><a class="menu-a" href="<c:url value='${loginOutLink}'/>">${loginOut}</a></li>
    <li><a class="menu-a" href="<c:url value='/register/add'/>">Sign in</a></li>
    <li><a class="menu-a" href=""><i class="fa fa-search"></i></a></li>
  </ul>
</div>

<div style="text-align:center">
  <h1>This is HOME</h1>
  <h1>This is HOME</h1>
  <h1>This is HOME</h1>
</div>
</body>
</html>