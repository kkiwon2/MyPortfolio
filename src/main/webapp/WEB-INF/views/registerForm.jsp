<%@ page contentType="text/html;charset=utf-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ page session="false"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.8.2/css/all.min.css" />
    <link rel="stylesheet" href="<c:url value='/css/registerForm.css'/>">
    <script src="https://code.jquery.com/jquery-1.11.3.js"></script>
    <title>Register</title>
</head>
<script>
    let msg = "${msg}";
</script>
<body>
<form action="<c:url value="/register/add"/>" method="post" onsubmit="return formCheck(this)">
    <div class="title">Register</div>
    <div id="msg" class="msg">
        <c:if test="${not empty msg}">
            <i class="fa fa-exclamation-circle"> ${msg}</i>
        </c:if>
    </div>
    <label for="">아이디</label>
    <input class="input-field" type="text" name="id" value="<c:out value='${user.id}'/>" placeholder="5~12자리의 영대소문자와 숫자 조합" maxlength="12">
    <label for="">비밀번호</label>
    <input class="input-field" type="password" name="pwd" value="<c:out value='${user.pwd}'/>" placeholder="5~12자리의 영대소문자와 숫자 조합" maxlength="12">
    <label for="">비밀번호 재확인</label>
    <input class="input-field" type="password" name="pwd2" value="" maxlength="12">
    <label for="">이름</label>
    <input class="input-field" type="text" name="name" value="<c:out value='${user.name}'/>" placeholder="홍길동" maxlength="12">
    <label for="">이메일</label>
    <input class="input-field" type="email" name="email" value="<c:out value='${user.email}'/>"placeholder="example@test.co.kr" maxlength="25">
    <label for="">생일</label>
    <input class="input-field" type="text" name="birth" value="<c:out value='${user.birth}'/>" placeholder="1994-10-21" maxlength="10">
<%--
    <label for="">생년월일</label>
    <div class="#bir_wrap">
    <input class="bir_yy" type="text" name="yy" value="<c:out value='${user.birth}'/>" maxlength="4" placeholder="2020/12/31">
    <input class="bir_mm" type="text" name="mm" value="<c:out value='${user.birth}'/>" placeholder="2020/12/31">
    <input class="bir_mm" type="text" name="dd" value="<c:out value='${user.birth}'/>" placeholder="2020/12/31">
    </div>
    --%>
    <div class="bir_wrap">
    <button>회원 가입</button>
        <button class="cancel">취소</button>
    </div>
</form>



<script>
    function formCheck(frm) {

        if(frm.id.value.length<5 || frm.id.value.length>12) {
            setMessage('아이디의 길이는 5이상이어야 합니다.', frm.id);
            return false;
        }
        if(frm.pwd.value.length<5 || frm.pwd.value.length>12) {
            setMessage('패스워드의 길이는 5이상이어야 합니다.', frm.pwd);
            return false;
        }

        if(frm.pwd.value!==frm.pwd2.value)
        {
            console.log(frm.pwd.value)
            console.log(frm.pwd2)
            setMessage('비밀번호가 다릅니다.', frm.pwd2)
            return false;
        }

        if(frm.name.value.length==0) {
            setMessage('이름을 입력해주세요', frm.name);
            return false;
        }
        if(frm.email.value.length==0){
            setMessage('이메일을 입력해주세요', frm.email)
            return false;
        }

        if(frm.birth.value.length==0){
            setMessage('생일을 입력해주세요', frm.birth)
            return false;
        }

        return true;
    }
    function setMessage(msg, element){
        document.getElementById("msg").innerHTML = `<i class="fa fa-exclamation-circle"> ${'${msg}'}</i>`;
        if(element) {
            element.select();
        }
    }

    $(document).ready(function (){
        $(".cancel").on("click",function (e){
            e.preventDefault();
            location.href="<c:url value='/'/>"
        });
    });
</script>
</body>
</html>