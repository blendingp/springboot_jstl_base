<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/5.1.3/css/bootstrap.min.css">
    <link rel="stylesheet" href="/common.css">
    <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/5.1.3/js/bootstrap.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
</head>
<body>
<div class="container">
    <div class="row mt-2">
        <div class="col-12 chat">
        </div>
        <div class="col-12 mt-3">
            <form class="row g-3" onsubmit="return false;">
                <div class="col-auto">
                    <input type="text" class="form-control" id="msg" placeholder="메시지 입력">
                </div>
                <div class="col-auto">
                    <button type="submit" class="btn btn-primary mb-3">전송</button>
                </div>
            </form>
        </div>
    </div>
</div>
<script>
$(function(){
    /* 웹소켓 생성 */
    var ws = new WebSocket('ws://localhost:8080/ws/chat');
    /* 메시지가 수신되었을 때 */
    ws.onmessage = function (e) {
        var msg = e.data;
        var tag = '<div class="alert alert-primary py-2" role="alert">' + msg + '</div>';
        $('.chat').append(tag);
    }
    /* 전송 버튼 클릭시 메시지 전송 */
    $('button[type=submit]').click(function () {
        var msg = $('#msg').val();
        if (msg.trim().length == 0) {
            $('#msg').focus();
            return alert('메시지를 입력해주세요.');
        }
        /* 웹소켓 메시지 전송 */
        ws.send(msg);
        $('#msg').val('');
    });
});
</script>
</body>
</html>