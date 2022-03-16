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
function file_download(path,filename){
    // var filename = document.querySelector('img').value
    // var path = document.querySelector('img').src
    var selected = document.createElement('a');
    selected.setAttribute('href',path);
    selected.setAttribute('download',filename);
    document.body.appendChild(selected);
    selected.click();
    selected.parentNode.removeChild(selected);
}

/*이미지 클릭시 모달띄욱기*/
function magnify(event,filename){

    let modal = document.getElementById("modal");
    let image = document.getElementById('content');

    event.target.addEventListener('click',e=>{
        image.src = event.target.src;
        modal.style.display = 'block';
        image.style.display = 'block';

        modal.style.position='fixed';
        down_btn(event.target.src,filename);
        close_btn();
    })

    modal.addEventListener("click", e => {
        const evTarget = e.target
        if(evTarget.classList.contains("modal-overlay")) {
            modal.style.display = "none"
        }
    });
}
/*다운로드와 버튼닫기는 두번 호출되서 따로 함수뺌*/
function down_btn(src,filename){
    let modal = document.getElementById("modal");
    const downBtn = modal.querySelector(".download-button")
    downBtn.addEventListener('click',e=>{
        file_download(src,filename);
    })
}

function close_btn(){
    let modal = document.getElementById("modal");
    const closeBtn = modal.querySelector(".close-area")
    closeBtn.addEventListener("click", e => {
        modal.style.display = "none"
    });
}