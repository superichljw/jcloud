<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<%@ include file="/WEB-INF/include/meta.jsp"%><!-- Meta -->
<head>
    <meta charset="UTF-8">
    <script src="<%=request.getContextPath()%>/js/common.js"></script>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/style.css">
    <title>index</title>
<script>

</script>
</head>
<body>
<div class="header-index">
    <div class="logout-button">
        <form action="logout.do" method="post">
            <input type="submit" value="로그아웃">
        </form>
    </div>
<p>hello here is index page</p>
    <p>${name} 님 환영합니다</p>
    <p>저장 경로는 ${directory} 이며, 와이파이 설정값은 ${wifi} 입니다</p>
    <p>현재까지 올린 파일 갯수는 ${uploadFileCnt} 입니다</p>
</div>
<div>

</div>
<div class="file-uploader">
    <form method="post" action="fileUpload.do" enctype="multipart/form-data">
        <input type="file" name="uploadfile" multiple="multiple">
        <input type="submit" value="파일업로드">
    </form>
</div>
<div class="grid-outline">
    <c:if test="${not empty files}">
        <c:forEach var="list" items="${files}">
            <div class="grid-img">
                <div class="img-detail"><img src ="getImg.do?imgPath=${list.imgPath}"
                                                onclick="magnify(event,'${list.fileNewName}')"
                                             /></div>


<%--                <a href="getImg.do?imgPath=${list.imgPath}" download="${list.fileNewName}"><img src ="getImg.do?imgPath=${list.imgPath}" /></a>--%>
            </div>
        </c:forEach>
    </c:if>
</div>

<div id="modal" class="modal-overlay">
    <img  id="content">
    <div class="download-button">DOWNLOAD</div>
    <div class="close-area">X</div>
</div>

</body>
</html>