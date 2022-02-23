<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>시작페이지</title>
</head>
<body>
<div class="loginPg">
    <form id="login" name="login" action="loginCheck.do" method="post">
        <div>
            <table>
                <tr>
                    <td>
                        <span>아이디</span>
                        <input type="text" id="userId" name="userId">
                    </td>
                    <td>
                        <span>비밀번호</span>
                        <input type="password" id="userPw" name="userPw">
                    </td>
                </tr>
                <tr>
                    <input type="submit" value="로그인" >

                </tr>
            </table>
        </div>
    </form>
</div>
</body>
</html>