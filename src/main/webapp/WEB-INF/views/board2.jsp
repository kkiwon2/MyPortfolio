<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>fastcampus</title>
    <link rel="stylesheet" href="<c:url value='/css/menu.css'/>">
    <script src="https://code.jquery.com/jquery-1.11.3.js"></script>
</head>
<body>
<div id="menu">
    <ul>
        <li id="logo">fastcampus</li>
        <li><a href="<c:url value='/'/>">Home</a></li>
        <li><a href="<c:url value='/board/list'/>">Board</a></li>
        <li><a href="<c:url value='/login/login'/>">login</a></li>
        <li><a href="<c:url value='/register/add'/>">Sign in</a></li>
        <li><a href=""><i class="fas fa-search small"></i></a></li>
    </ul>
</div>
<div style="text-align:center">
    <h2>게시물 ${mode=="new" ? "글쓰기" : "읽기"}</h2>
    <form action="" id="form">
        <input type="hidden" name="bno" value="${boardDto.bno}" readonly="readonly">
        <input type="text" name="title" value="${boardDto.title}" ${mode =="new" ? "" : "readonly='readonly'"}>
        <textarea name="content" id="" cols="30" rows="10" ${mode =="new" ? "" : "readonly='readonly'"}>${boardDto.content}</textarea>
        <button type="button" id="writeBtn" class="btn">등록</button>
        <button type="button" id="modifyBtn" class="btn">수정</button>
        <button type="button" id="removeBtn" class="btn">삭제</button>
        <button type="button" id="listBtn" class="btn">목록</button>
        <input type="hidden" name="test" value="여기있는것도 form으로 보낼때 가는거지?">
    </form>
</div>

<script>
    let msg = "${msg}";
    if (msg=="WRT_ERR") alert("게시물 등록에 실패했습니다.");
    $(document).ready(function (){
        $('#listBtn').on("click", function (){
            location.href = "<c:url value='/board/list'/>?page=${page}&pageSize=${pageSize}";
        })//lisetBtn

        $('#removeBtn').on("click", function (){ //Post로 처리해야됨
            if(!confirm("게시물을 삭제합니다.")) return;
            let form = $('#form');
            form.attr("action", "<c:url value="/board/remove"/>?page=${page}&pageSize=${pageSize}");
            form.attr("method","post");
            form.submit();
        })//removeBtn

        $('#writeBtn').on("click", function (){ //Post로 처리해야됨
            let form = $('#form');
            form.attr("action", "<c:url value="/board/write"/>");
            form.attr("method","post");
            form.submit();
        })//writeBtn

        $('#modifyBtn').on("click", function (){ //Post로 처리해야됨
            let form = $('#form');
            let isReadOnly = $("input[name=title]").attr("readonly");

            if(isReadOnly=='readonly'){
                $("input[name=title]").attr("readonly",false);
                $("textarea").attr("readonly",false);
                $("#modifyBtn").html("등록");
                $("h2").html("게시물 수정");
                return; //수정상태이면 이것만 받고 나가야됨
            }

            form.attr("action", "<c:url value="/board/modify"/>");
            form.attr("method","post");
            form.submit();
        })//modifyBtn


    })
</script>
</body>
</html>
