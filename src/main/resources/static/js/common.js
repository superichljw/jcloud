/*login validation check*/

function login_check(){
    var form = document.login;
    if(!form.userId.value){
        alert('아이디를 입력하세요');
        form.userId.focus();
        return;
    }
    if(!form.userPw.value){
        alert('비밀번호를 입력하세요');
        form.userPw.focus();
        return;
    }
    form.action = "login.do";
    form.submit();

}

/*파일 다운로드 스크립트단 처리*/
function file_download(filename){
    var path = document.querySelector('img').src
    var selected = document.createElement('a');
    selected.setAttribute('href',path);
    selected.setAttribute('download',filename);
    document.body.appendChild(selected);
    selected.click();
    selected.parentNode.removeChild(selected);
}