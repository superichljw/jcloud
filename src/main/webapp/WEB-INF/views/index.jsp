<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
<%--    서버용--%>
<%--    <link rel="stylesheet" href="/jcloud/css/style.css">--%>
<%--    로컬용--%>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/style.css">
    <title>시작페이지</title>
    <script>
        function file_download(filename){
            var path = document.querySelector('img').src
            var selected = document.createElement('a');
            selected.setAttribute('href',path);
            selected.setAttribute('download',filename);
            document.body.appendChild(selected);
            selected.click();
            selected.parentNode.removeChild(selected);
        }
    </script>
</head>
<body>
<div class="header-index">
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
                <div class="img-detail"><img src ="getImg.do?imgPath=${list.imgPath}" onclick="file_download('${list.fileNewName}')"/></div>
<%--                <a href="getImg.do?imgPath=${list.imgPath}" download="${list.fileNewName}"><img src ="getImg.do?imgPath=${list.imgPath}" /></a>--%>
            </div>
        </c:forEach>
    </c:if>
</div>
<%--<table>--%>
<%--        <c:if test="${not empty files}">--%>
<%--            <c:forEach var="list" items="${files}">--%>
<%--                <tr>--%>
<%--                    <td><img style=" width: 200px;height: 200px " src = "getImg.do?imgPath=${list.imgPath}"/></td>--%>
<%--&lt;%&ndash;                    <td>${list.imgPath}</td>&ndash;%&gt;--%>
<%--&lt;%&ndash;                    <td>${list.fileDir}${list.fileNewName}</td>&ndash;%&gt;--%>
<%--&lt;%&ndash;                    <td>${list.fileNewName}</td>&ndash;%&gt;--%>
<%--&lt;%&ndash;                    <td>${list.fileDir}</td>&ndash;%&gt;--%>
<%--&lt;%&ndash;                    <td>${list.fileSize}</td>&ndash;%&gt;--%>
<%--                </tr>--%>
<%--            </c:forEach>--%>
<%--        </c:if>--%>
<%--</table>--%>
</body>
</html>