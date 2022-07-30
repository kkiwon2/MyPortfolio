<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false"%>
<c:set var="loginId" value="${pageContext.request.getSession(false)==null ? '' : pageContext.request.session.getAttribute('id')}"/>
<c:set var="loginOutLink" value="${loginId=='' ? '/login/login' : '/login/logout'}"/>
<c:set var="loginOut" value="${loginId=='' ? 'Login' : 'Logout'}"/>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>MyPortfolio</title>
    <link rel="stylesheet" href="<c:url value='/css/menu.css'/>">
    <link rel="stylesheet" href="<c:url value='/css/loginForm.css'/>">
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

<form action="<c:url value="/login/login"/>" method="post" onsubmit="return formCheck(this);">
    <h3 id="title">Login</h3>
    <div id="msg">
        <c:if test="${not empty param.msg}">
            <i class="fa fa-exclamation-circle"> ${param.msg}</i>
        </c:if>
    </div>
    <input type="text" name="id" value="${cookie.id.value}" placeholder="이메일 입력" autofocus>
    <input type="password" name="pwd" placeholder="비밀번호">
    <%--로그인 안한상태에서 boardlist게시판으로 클릭했을 때 로그인한 뒤 바로 이동시키기 위해 hideend으로 값을 심어놨음  --%>
    <input type="hidden" name="toURL" value="${param.toURL}">
    <button>로그인</button>
    <div>
        <label><input type="checkbox" name="rememberId" ${empty cookie.id.value ? "":"checked"}> 아이디 기억</label> |
        <a href="">비밀번호 찾기</a> |
        <a href="<c:url value="/register/add"/>">회원가입</a>
    </div>
    <script>
        function formCheck(frm) {
            let msg ='';
            if(frm.id.value.length==0) {
                setMessage('id를 입력해주세요.', frm.id);
                return false;
            }
            if(frm.pwd.value.length==0) {
                setMessage('password를 입력해주세요.', frm.pwd);
                return false;
            }
            return true;
        }
        function setMessage(msg, element){
            document.getElementById("msg").innerHTML = ` ${'${msg}'}`;
            if(element) {
                element.select();
            }
        }
    </script>
</form>
</body>
</html>