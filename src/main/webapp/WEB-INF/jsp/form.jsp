<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/5.1.3/css/bootstrap.min.css">
    <link rel="stylesheet" href="common.css">
    <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/5.1.3/js/bootstrap.min.js"></script>
    <meta charset="UTF-8" />
</head>
<body>
    <div class="container">
        <div class="row mt-2">
            <div class="col-12">
                <div class="card">
                    <div class="card-body">
                        <form:form method="post" action="/save" modelAttribute="post" >
                            <form:hidden path="id"/>
                            <div class="mb-3">
                                <label for="title" class="form-label">제목</label>
                                <form:input path="title" class="form-control"/>
                                <p class="error px-2">
                                    <form:errors path="title" />
                                </p>
                            </div>
                            <div class="mb-3">
                                <label for="title" class="form-label">내용</label>
                                <form:textarea path="content" rows="10" class="form-control" />
                                <p class="error px-2">
                                    <form:errors path="content" />
                                </p>
                            </div>
                            <a href="/" class="btn btn-secondary me-2">취소</a>
                            <button type="submit" class="btn btn-primary">저장</button>
                        </form:form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</body>
</html>