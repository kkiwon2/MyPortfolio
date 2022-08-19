<%@ page contentType="text/html;charset=utf-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page session="false" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.8.2/css/all.min.css"/>
    <link rel="stylesheet" href="<c:url value='/css/register2.css'/>">
    <script src="https://code.jquery.com/jquery-1.11.3.js"></script>
    <title>Register</title>
</head>
<script>
    let msg = "${msg}";
</script>
<body>
<form:form modelAttribute="userDto" onsubmit="return formCheck(this)">
    <%--<form action="<c:url value="/register/add"/>" method="post" onsubmit="return formCheck(this)">--%>
    <div class="register">
        <h3>회원가입</h3>
        <div class="flex">
                <%--            <div id="msg" class="msg">--%>
                <%--                <c:if test="${not empty msg}">--%>
                <%--                    <i class="fa fa-exclamation-circle"> ${msg}</i>--%>
                <%--                </c:if>--%>
                <%--            </div>--%>

            <div id="msg" class="msg"><form:errors path="id"/></div>
            <ul class="container">
                <li class="item center">
                    아이디
                </li>
                <li class="item">
                    <input class="input-field" type="text" name="id" value="<c:out value='${user.id}'/>"
                           placeholder="4~12자리의 영대소문자와 숫자 조합" maxlength="12">
                </li>
                <li class="item">
                    <button id="validator" class="idcheck">중복확인</button>
                </li>
            </ul>

            <div class="msg"><form:errors path="pwd"/></div>
            <ul class="container">
                <li class="item center">
                    비밀번호
                </li>
                <li class="item">
                    <input class="input-field" type="password" name="pwd" value="<c:out value='${user.pwd}'/>"
                           placeholder="5~12자리의 영대소문자와 숫자 조합" maxlength="12">
                </li>
                <li class="item">

                </li>
            </ul>

            <div id="pwd" class="msg"></div>
            <ul class="container">
                <li class="item center">
                    비밀번호 확인
                </li>
                <li class="item">
                    <input class="input-field" type="password" name="pwd2" value="" maxlength="12">
                </li>
                <li class="item">

                </li>
            </ul>

            <div class="msg"><form:errors path="name"/></div>
            <ul class="container">
                <li class="item center">
                    이름
                </li>
                <li class="item">
                    <input class="input-field" type="text" name="name" value="<c:out value='${user.name}'/>"
                           placeholder="홍길동" maxlength="12">
                </li>
                <li class="item">

                </li>
            </ul>

            <div id="birth" class="msg"><form:errors path="birth"/></div>
            <ul class="container">
                <li class="item center">
                    생년월일
                </li>
                <li class="item">
                    <input class="input-field" type="date" name="birth"
                           value="<fmt:formatDate value="${user.birth}" pattern="yyyy-MM-dd" type="date"/>"
                           placeholder="1994-10-21" maxlength="10">
                </li>
                <li class="item">

                </li>
            </ul>

            <div class="msg"><form:errors path="gender"/></div>
            <ul class="container">
                <li class="item center">
                    성별
                </li>
                <li class="item">
                    <select name="gender">
                        <option value="" selected>선택</option>
                        <option value="1">남성</option>
                        <option value="2">여성</option>
                    </select>
                </li>
                <li class="item">

                </li>
            </ul>

            <div class="msg"><form:errors path="email"/></div>
            <ul class="container">
                <li class="item center">
                    이메일
                </li>
                <li class="item">
                    <input class="input-field" type="email" name="email" value="<c:out value='${user.email}'/>"
                           placeholder="example@test.co.kr" maxlength="25">
                </li>
                <li class="item">

                </li>
            </ul>

            <ul class="container">
                <li class="item center">

                </li>
                <li class="item">
                    <button class="submit">회원 가입</button>
                    <button class="cancel">취소</button>
                </li>
                <li class="item">

                </li>
            </ul>
        </div>
    </div>
    <%--</form>--%>
</form:form>


<script>
    function formCheck(frm) {
        // 날짜 정규식
        // let pattern = /^[a-z0-9]{4,12}$/
        let date_pattern = /(^\d{4})-(0[1-9]|1[0-2])-(0[1-9]|[12][0-9]|3[01])$/
        //
        //             if (!pattern.test(frm.id.value)) {
        //                 setMessage('아이디는 4~12자리의 영대소문자와 숫자 조합이어야 합니다.', frm.id);
        //                 return false;
        //             }
        //
        //
        //             if (!pattern.test(frm.pwd.value)) {
        //                 setMessage('패스워드는 4~12자리의 영대소문자와 숫자 조합이어야 합니다.', frm.pwd);
        //                 return false;
        //             }
        //
        if (frm.pwd.value !== frm.pwd2.value) {
            console.log(frm.pwd.value)
            console.log(frm.pwd2.value)
            setMessage('비밀번호가 다릅니다.', "pwd", frm.pwd2)
            return false;
        }
        //
        //             if (frm.name.value.length == 0) {
        //                 setMessage('이름을 입력해주세요', frm.name);
        //                 return false;
        //             }
        //             if (frm.email.value.length == 0) {
        //                 setMessage('이메일을 입력해주세요', frm.email)
        //                 return false;
        //             }
        //
        if (frm.birth.value.length == 0) {
            msg = "생일을 입력해주세요"
            document.getElementById("birth").innerHTML = `<i class="fa fa-exclamation-circle"> ${'${msg}'}</i>`;
            frm.birth.select();
            return false;
        }

        if (!date_pattern.test(frm.birth.value)) {
            console.log(frm.birth.value)
            msg = "생일의 입력 형식이 다릅니다. yyyy-mm-dd와 같이 입력해주세요"
            document.getElementById("birth").innerHTML = `<i class="fa fa-exclamation-circle"> ${'${msg}'}</i>`;
            frm.birth.select();
            return false;
        }
        //
        //             if(frm.gender.value == ""){
        //                 console.log(frm.gender.value)
        //                 setMessage('성별을 선택해주세요.')
        //                 return false;
        //             }


        return true;
    }//formCheck()

    function setMessage(msg, id, element) {
        document.getElementById("msg").innerHTML = `<i class="fa fa-exclamation-circle"> ${'${msg}'}</i>`;
        if (element) {
            element.select();
        }
    }


    $(document).ready(function () {
        $(".cancel").on("click", function (e) {
            e.preventDefault();
            location.href = "<c:url value='/'/>"
        });

        $(".idcheck").on("click", function (e){
            e.preventDefault();

            let id = $("input[name=id]").val()
            $.ajax({
                type: 'GET',       // 요청 메서드
                url: '/web/register/validate?id=' + id, // 요청 URI
                success: function (result) {
                    let msg = decodeURIComponent(result).replace(/\+/g, '');
                    document.getElementById("msg").innerHTML = `<i class="fa fa-exclamation-circle">${'${msg}'}</i>`;
                },
                error: function () {
                    alert("error")
                } // 에러가 발생했을 때, 호출될 함수
            }); // $.ajax()
        });
    });


</script>
</body>
</html>