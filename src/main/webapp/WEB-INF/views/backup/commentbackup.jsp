<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="<c:url value='/css/comment.css'/>">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <script src="https://code.jquery.com/jquery-1.11.3.js"></script>
    <title>Document</title>
</head>
<body>

<div id="commentList">
    <ul id="test"></ul>

    <!-- 페이징 -->
    <div class="paging-container">
        <div class="paging">
            <a class="page" href="#">&lt;</a>
            <a class="page" href="#">1</a>
            <a class="page" href="#">2</a>
            <a class="page" href="#">3</a>
            <a class="page" href="#">4</a>
            <a class="page paging-active" href="#">5</a>
            <a class="page" href="#">6</a>
            <a class="page" href="#">7</a>
            <a class="page" href="#">8</a>
            <a class="page" href="#">9</a>
            <a class="page" href="#">10</a>
            <a class="page" href="#">&gt;</a>
        </div>
    </div>

    <!-- 댓글 입력창 -->
    <div id="comment-writebox">
        <div class="commenter commenter-writebox">${id}</div>
        <div class="comment-writebox-content">
            <textarea name="comment" cols="30" rows="3" placeholder="댓글을 남겨보세요"></textarea>
        </div>
        <div id="comment-writebox-bottom">
            <div class="register-box">
                <a href="#" class="btn" id="btn-write-comment">등록</a>
            </div>
        </div>
    </div>
</div>

<!-- 대댓글 창 -->
<div id="reply-writebox">
    <div class="commenter commenter-writebox">${id}</div>
    <div class="reply-writebox-content">
        <textarea name="" id="" cols="30" rows="3" placeholder="댓글을 남겨보세요"></textarea>
    </div>
    <div id="reply-writebox-bottom">
        <div class="register-box">
            <a href="#" class="btn" id="btn-write-reply">등록</a>
            <a href="#" class="btn" id="btn-cancel-reply">취소</a>
        </div>
    </div>
</div>

<!-- 수정버튼 클릭시 보여줄 모달창-->
<div id="modalWin" class="modal">
    <!-- Modal content -->
    <div class="modal-content">
        <span class="close">&times;</span>
        <p>
        <h2> | 댓글 수정</h2>
        <div id="modify-writebox">
            <div class="commenter commenter-writebox"></div>
            <div class="modify-writebox-content">
                <textarea name="comment2" id="" cols="30" rows="5" placeholder="댓글을 남겨보세요"></textarea>
            </div>
            <div id="modify-writebox-bottom">
                <div class="register-box">
                    <a href="#" class="btn" id="btn-write-modify">등록</a>
                </div>
            </div>
        </div>
        </p>
    </div>
</div>


<script>
    let id = 'asdf';
    let bno = 646

    //댓글 목록을 보여줄 함수
    let showList = function (bno) {
        $.ajax({
            type: 'GET',       // 요청 메서드
            url: '/web/comments?bno=' + bno,  // 요청 URI
            success: function (result) {
                $("#test").html(toHtml(result))
            },
            error: function () {
                alert("error")
            } // 에러가 발생했을 때, 호출될 함수
        }); // $.ajax()
    }//showList

    // 날짜 0추가
    let addZero = function (value = 1) {
        return value > 9 ? value : "0" + value;
    }

    //댓글 등록 날짜 시간 출력함수
    let dateToString = function (ms = 0) {
        let date = new Date(ms);

        let yyyy = date.getFullYear();
        let mm = addZero(date.getMonth() + 1);
        let dd = addZero(date.getDate());

        let HH = addZero(date.getHours());
        let MM = addZero(date.getMinutes());
        let ss = addZero(date.getSeconds());

        return yyyy + "." + mm + "." + dd + " " + HH + ":" + MM + ":" + ss;
    }

    $(document).ready(function () {
        showList(bno);

        //댓글 등록
        $("#btn-write-comment").click(function () {
            let comment = $("textarea[name=comment]").val();
            let bno = 646;
            if (comment.trim() == '') {
                alert("댓글을 입력해주세요.");
                $("input[name=comment]").focus()
                return;
            }//if
            $.ajax({
                type: 'POST',       // 요청 메서드
                url: '/web/comments?bno=' + bno,  // 요청 URI
                headers: {"content-type": "application/json"}, // 요청 헤더
                data: JSON.stringify({bno: bno, comment: comment}),  // 서버로 전송할 데이터. stringify()로 직렬화 필요.
                success: function (result) {
                    alert(result);
                    $("textarea[name=comment]").val('');
                    showList(646);
                },
                error: function () {
                    alert("error")
                } // 에러가 발생했을 때, 호출될 함수
            }); // $.ajax()
        });//#btn-write-comment

        //댓글 삭제
        $("#commentList").on("click", "a.btn-delete", function () {
            let cno = $(this).attr("data-cno");
            let bno = $(this).attr("data-bno");
            $.ajax({
                type: 'DELETE',                                 //요청 메서드
                url: '/web/comments/' + cno + '?bno=' +bno,     //요청 URI
                success: function (result){
                    alert(result);
                    showList(bno);
                },  //success
                error: function (){
                    alert("error")
                }//에러가 발생했을 때, 호출될 함수
            })//ajax
        });//a.btn-delete

        //댓글 수정 모달창 띄우기
        $("#commentList").on("click", ".btn-modify", function (e){
            let target = e.target;
            let cno = target.getAttribute("data-cno");
            let bno = target.getAttribute("data-bno");
            let pcno = target.getAttribute("data-pcno");

            let li = $("li[data-cno="+cno+"]");
            let commenter = $(".commenter", li).first().text();
            let comment = $(".comment-content", li).first().text();
            console.log(cno,bno,pcno,commenter,comment);


            $("#modalWin .commenter").text(commenter);
            $("#modalWin textarea").text(comment);
            $("#btn-write-modify").attr("data-cno", cno);
            $("#btn-write-modify").attr("data-pcno", pcno);
            $("#btn-write-modify").attr("data-bno", bno);

            // 팝업창을 열고 내용을 보여준다.
            $("#modalWin").css("display", "block");
        });//$("#commentList").on("click", ".btn-modify", function (e){


        //댓글수정 모달창에서 등록버튼 클릭시 동작이벤트
        $("#btn-write-modify").click(function () {
            // 1. 변경된 내용을 서버로 전송
            let cno = $(this).attr("data-cno");
            let comment = $("textarea[name=comment2]").val();
            console.log(comment);
            if(comment.trim()==''){ //수정내용이 비어있다면 종료
                alert("댓글을 입력해주세요.");
                $("textarea[name=comment2]").focus()
                return;
            }//if
            $.ajax({
                type: 'PATCH',       // 요청 메서드
                url: '/web/comments/'+cno,  // 요청 URI
                headers: {"content-type": "application/json"}, // 요청 헤더
                // dataType : 'text', // 전송받을 데이터의 타입, 생략하면 json임
                data: JSON.stringify({cno: cno, comment: comment}),  // 서버로 전송할 데이터. stringify()로 직렬화 필요.
                success: function (result) {          // result는 서버가 전송한 데이터
                    alert(result);
                    showList(bno);
                },
                error: function () {
                    alert("error")
                } // 에러가 발생했을 때, 호출될 함수
            }); // $.ajax()
            // 2. 모달 창을 닫는다.
            $(".close").trigger("click");
        });//$("#btn-write-modify")

        $(".close").click(function () {
            $("#modalWin").css("display", "none");
        });


        //답글쓰기 버튼 눌렀을때 보여줄 모달창
        $("#commentList").on("click", ".btn-write", function(e){
            let target = e.target;
            let cno = target.getAttribute("data-cno")
            let bno = target.getAttribute("data-bno")
            let pcno = target.getAttribute("data-pcno")
            let repForm = $("#reply-writebox");
            repForm.appendTo($("li[data-cno="+cno+"]"));
            repForm.css("display", "block");
            repForm.attr("data-pcno", pcno);
            repForm.attr("data-bno",  bno);
        });// $("#commentList").on("click", ".btn-write", function(e){

        //답글모달창 등록버튼 이벤트
        $("#btn-write-reply").click(function (e) {
            //여기구현해야됨
        });

        //답글모달창 취소버튼 이벤트
        $("#btn-cancel-reply").click(function (e) {
            $("#reply-writebox").css("display", "none");
        });

    });//document.ready

    let toHtml = function (comments) {
        let tmp = ""
        let i = 0;
        comments.forEach(function (comment) {
            tmp += '<li class="comment-item" data-cno=' + comment.cno + ' data-bno="1070">';
            tmp += '<span class="comment-img">'
            tmp += '<i class="fa fa-user-circle" aria-hidden="true"></i>'
            tmp += '</span>'
            tmp += '<div class="comment-area">'
            tmp += '<div class="commenter">' + comment.commenter + '</div>'
            tmp += '<div class="comment-content">' + comment.comment + '</div>'
            tmp += '<div class="comment-bottom">'
            let date = dateToString(comment.up_date);
            tmp += '<span class="up_date">' + date +'</span>'
            tmp += '<a href="#" class="btn-write" data-cno=' + comment.cno +  ' data-bno=' + comment.bno + ' data-pcno="0">답글쓰기</a>'
            tmp += '<a href="#" class="btn-modify" data-cno=' + comment.cno + ' data-bno=' + comment.bno + ' data-pcno="0">수정</a>'
            tmp += '<a href="#" class="btn-delete" data-cno=' + comment.cno + ' data-bno=' + comment.bno + ' data-pcno="0">삭제</a>'
            tmp += '</div>'
            tmp += '</div>'
            tmp += '</li>'
        })
        return tmp;
    }//tohtml
</script>
</body>
</html>