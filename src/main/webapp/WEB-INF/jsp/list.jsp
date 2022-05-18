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
        <div class="row">
            <div class="col-12 my-3">
                <a href="/form" class="btn btn-primary">글 작성</a>
            </div>
            <div class="col-12">
                <table class="table table-hover">
                    <thead>
                    <tr>
                        <th scope="col" width="15%">#</th>
                        <th scope="col" width="60%">제목</th>
                        <th scope="col" width="25%">작성일자</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${page.posts}" var="post">
                        <tr>
                            <th scope="row">${post.id}</th>
                            <td><a href="/read/${post.id}">${post.title}</a></td>
                            <td><fmt:formatDate value="${post.createdAt}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
            <c:if test="${page.totalPage > 0}">
                <nav aria-label="Page navigation example">
                    <ul class="pagination">
                        <c:forEach begin="1" end="${page.totalPage}" var="i">
                            <c:choose>
                                <c:when test="${page.getPageNo() == i}">
                                    <li class="page-item active" aria-current="page"><span class="page-link">${i}</span></li>
                                </c:when>
                                <c:otherwise>
                                    <li class="page-item"><a class="page-link" href="/?pageNo=${i}">${i}</a></li>
                                </c:otherwise>
                            </c:choose>
                        </c:forEach>
                    </ul>
                </nav>
            </c:if>
        </div>
    </div>
</body>
</html>