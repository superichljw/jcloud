<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<%@ include file="/WEB-INF/include/meta.jsp"%><!-- Meta -->
<head>
    <meta charset="UTF-8">
    <script src="<%=request.getContextPath()%>/js/common.js"></script>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/style.css">
    <title>main</title>
    <script></script>
</head>
<body>
<div class="wrap">
    <div class="loginPg">
        <form id="login" name="login" method="post" >
                <div>
                    <img src="<%=request.getContextPath()%>/image/jcloud.png">
                </div>
                <div class="login-id-input">
                    <input placeholder="ID" type="text" id="userId" name="userId">
                </div>
                <div class="login-pw-input">
                    <input placeholder="PASSWORD" type="password" id="userPw" name="userPw">
                </div>
                <div class="login-submit">
                    <input type="submit" value="로그인" onclick="login_check()">
                </div>
        </form>
    </div>
</div>
</body>
</html>