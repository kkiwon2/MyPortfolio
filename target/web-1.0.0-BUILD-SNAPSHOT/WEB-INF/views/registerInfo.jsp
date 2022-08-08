<%@ page contentType="text/html;charset=utf-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt_rt" %>
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
<body>
<form action="<c:url value="/register/update"/>" method="post" onsubmit="return formCheck(this)">
  <div class="title">회원정보</div>
  <div id="msg" class="msg">

  </div>
  <label for="">아이디</label>
  <input class="input-field" type="text" name="id" value="<c:out value='${user.id}'/>"  maxlength="12" readonly>
  <label for="">비밀번호</label>
  <input class="input-field" type="password" name="pwd" value="<c:out value=''/>"  maxlength="12">
  <label for="">비밀번호 재확인</label>
  <input class="input-field" type="password" name="pwd2" value="<c:out value=''/>" maxlength="12">
  <label for="">이름</label>
  <input class="input-field" type="text" name="name" value="<c:out value='${user.name}'/>" >
  <label for="">이메일</label>
  <input class="input-field" type="email" name="email" value="<c:out value='${user.email}'/>">
  <label for="">생일</label>
  <input class="input-field" type="text" name="birth" value="<fmt:formatDate value="${user.birth}" pattern="yyyy-MM-dd" type="date"/>"  maxlength="10">
  <div class="bir_wrap">
    <button>정보 수정</button>
    <button class="cancel">취소</button>
  </div>
</form>



<script>
  let msg = "${param.msg}";
  function formCheck(frm) {

    //날짜 정규식
    let pattern = /^[a-z0-9]{4,12}$/
    let date_pattern = /(^\d{4})-(0[1-9]|1[0-2])-(0[1-9]|[12][0-9]|3[01])$/

    if(!pattern.test(frm.id.value)) {
      setMessage('아이디는 4~12자리의 영대소문자와 숫자 조합이어야 합니다.', frm.id);
      return false;
    }

    if(!pattern.test(frm.pwd.value)) {
      setMessage('패스워드는 5~12자리의 영대소문자와 숫자 조합이어야 합니다.', frm.pwd);
      return false;
    }

    if(frm.pwd.value!==frm.pwd2.value)
    {
      console.log(frm.pwd.value)
      console.log(frm.pwd2.value)
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

    if(!date_pattern.test(frm.birth.value)){
      console.log(frm.birth.value)
      setMessage('생일의 입력 형식이 다릅니다. yyyy-mm-dd와 같이 입력해주세요.', frm.birth)
      return false;
    }
    return true;
  }//formCheck()

  console.log(msg);
  if(msg != null && msg != "") {
    setMessage(msg)
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