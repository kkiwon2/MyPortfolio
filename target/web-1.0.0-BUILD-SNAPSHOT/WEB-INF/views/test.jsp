<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script src="https://code.jquery.com/jquery-1.11.3.js"></script>
</head>
<body>
<h2>commentTest</h2>
comment : <input type="text" name="comment">
<br>
<button id="sendBtn" type="button">SEND</button>
<button id="modBtn" type="button">수정</button>
<div id="commentList"></div>
<div id="replyForm" style="display:none">
    <input type="text" name="replyComment">
    <button id="wrtRepBtn" type="button">등록</button>
</div>

<script>
    let bno = 646;
    //댓글 목록을 가져와서 보여주는 함수
    let showList = function (bno) {
        $.ajax({
            type: 'GET',       // 요청 메서드
            url: '/web/comments?bno=' + bno,  // 요청 URI
            success: function (result) {
                $("#commentList").html(toHtml(result));    // 서버로부터 응답이 도착하면 호출될 함수
            },
            error: function () {
                alert("error")
            } // 에러가 발생했을 때, 호출될 함수
        }); // $.ajax()
    }


    $(document).ready(function () {
        showList(bno);

        //입력
        $("#sendBtn").click(function () {
            let comment = $("input[name=comment]").val();

            if(comment.trim()==''){
                alert("댓글을 입력해주세요.");
                $("input[name=comment]").focus()
                return;
            }

            $.ajax({
                type: 'POST',       // 요청 메서드
                url: '/web/comments?bno='+bno,  // 요청 URI
                headers: {"content-type": "application/json"}, // 요청 헤더
                // dataType : 'text', // 전송받을 데이터의 타입, 생략하면 json임
                data: JSON.stringify({bno: bno, comment: comment}),  // 서버로 전송할 데이터. stringify()로 직렬화 필요.
                success: function (result) {          // result는 서버가 전송한 데이터
                    alert(result);
                    showList(bno);
                },
                error: function () {
                    alert("error")
                } // 에러가 발생했을 때, 호출될 함수
            }); // $.ajax()
        }); //senBtn


        //댓글 수정
        $("#modBtn").click(function () {
            let cno = $(this).attr("data-cno");
            let comment = $("input[name=comment]").val();

            if(comment.trim()==''){
                alert("댓글을 입력해주세요.");
                $("input[name=comment]").focus()
                return;
            }

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
        }); //senBtn

        //댓글 수정버튼을 눌렀을 때 수정할 내용 보여주기
        $("#commentList").on("click", ".modBtn", function () {
            let cno = $(this).parent().attr("data-cno");
            let comment = $("span.comment", $(this).parent()).text()

            // 1. comment의 내용을 input에 뿌려주기
            $("input[name=comment]").val(comment);
            // 2. cno에 전달하기
            $("#modBtn").attr("data-cno", cno);
        });


        //댓글 삭제
        $("#commentList").on("click", ".delBtn", function () {
            let cno = $(this).parent().attr("data-cno");
            let bno = $(this).parent().attr("data-bno");
            $.ajax({
                type: 'DELETE',       // 요청 메서드
                url: '/web/comments/' + cno + '?bno=' + bno,  // 요청 URI
                success: function (result) {              // 서버로부터 응답이 도착하면 호출될 함수
                    alert(result);
                    showList(bno);
                },
                error: function () {
                    alert("error")
                } // 에러가 발생했을 때, 호출될 함수
            }); // $.ajax()
        });//delBtn

        //답글 화면을 옴겨서 보여주기
        $("#commentList").on("click", ".replyBtn", function () {
            //1. replyForm을 옮긴다. on()함수에 2번째 매개변수가 this가 가리키는 객체임 그 부모면 li태그겠지
            $("#replyForm").appendTo($(this).parent())
            //1. 답글을 입력할 폼을 보여준다
            $("#replyForm").css("display", "block");
        });

        //답글(댓글의 댓글) 등록 내용 전송
        $("#wrtRepBtn").click(function () {
            let comment = $("input[name=replyComment]").val();
            let pcno = $("#replyForm").parent().attr("data-pcno");

            if(comment.trim()==''){
                alert("댓글을 입력해주세요.");
                $("input[name=replyComment]").focus()
                return;
            }

            $.ajax({
                type: 'POST',       // 요청 메서드
                url: '/web/comments?bno='+bno,  // 요청 URI
                headers: {"content-type": "application/json"}, // 요청 헤더
                // dataType : 'text', // 전송받을 데이터의 타입, 생략하면 json임
                data: JSON.stringify({pcno:pcno, bno: bno, comment: comment}),  // 서버로 전송할 데이터. stringify()로 직렬화 필요.
                success: function (result) {          // result는 서버가 전송한 데이터
                    alert(result);
                    showList(bno);
                },
                error: function () {
                    alert("error")
                } // 에러가 발생했을 때, 호출될 함수
            }); // $.ajax()

            $("#replyForm").css("display", "none");
            $("input[name=replyComment]").val();
            $("#replyForm").appendTo("body");
        }); //wrtRepBtn
    })// $(document).ready(function() {

    let toHtml = function (comments) {
        let tmp = "<ul>";

        comments.forEach(function (comment) {
            tmp += '<li data-cno=' + comment.cno
            tmp += ' data-pcno=' + comment.pcno
            tmp += ' data-bno=' + comment.bno + '>'
            // if(comment.cno!=comment.pcno)
            //     tmp += 'ㄴ'
            tmp += ' commenter=<span class="commenter">' + comment.commenter + '</span>'
            tmp += ' comment=<span class="comment">' + comment.comment + '</span>'
            tmp += ' up_date=' + comment.up_date
            tmp += '<button class="delBtn">삭제</button>'
            tmp += '<button class="modBtn">수정</button>'
            tmp += '<button class="replyBtn">답글</button>'
            tmp += '</li>'
        })

        return tmp + "</ul>";
    }
</script>
</body>
</html>