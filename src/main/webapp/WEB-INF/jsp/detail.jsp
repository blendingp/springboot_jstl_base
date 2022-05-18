<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/5.1.3/css/bootstrap.min.css">
    <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/5.1.3/js/bootstrap.min.js"></script>
</head>
<body>
    <div class="container">
        <div class="row mt-2">
            <div class="col-12">
                <div class="card">
                    <div class="card-body">
                    <c:choose>
                        <c:when test="${post != null}">
                            <h5 class="card-title">${post.title}</h5>
                            <h6 class="card-subtitle mb-2 text-muted"><fmt:formatDate value="${post.createdAt}" pattern="yyyy-MM-dd HH:mm:ss"/></h6>
                            <p class="card-text mt-3">${post.content}</p>
                            <a href="/form/${post.id}" class="card-link">수정</a>
                            <a href="/delete/${post.id}" class="card-link">삭제</a>
                            <a href="/" class="card-link">목록</a>
                        </c:when>
                        <c:otherwise>
                            작성글이 존재하지 않습니다.
                        </c:otherwise>
                    </c:choose>
                    </div>
                </div>
            </div>
        </div>
    </div>
</body>
</html>