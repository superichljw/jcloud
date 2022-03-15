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
</head>
<body>
<div class="wrap">
    <div class="loginPg">
        <form id="login" name="login" action="login.do" method="post">
                <div>
<%--                    서버용--%>
<%--                    <img src="/jcloud/image/jcloud.png">--%>
<%--                    로컬용--%>
                    <img src="<%=request.getContextPath()%>/image/jcloud.png">
                </div>
                <div class="login-id-input">
                    <input placeholder="ID" type="text" id="userId" name="userId">
                </div>
                <div class="login-pw-input">
                    <input placeholder="PASSWORD" type="password" id="userPw" name="userPw">
                </div>
                <div class="login-submit">
                    <input type="submit" value="로그인" >
                </div>
        </form>
    </div>
</div>
</body>
</html>