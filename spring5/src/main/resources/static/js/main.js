let sendData = document.getElementById("sendData");     // 전역 변수(Global Variable)
sendData.addEventListener('click', validate);

// 이벤트 핸들러
function validate() {
    let fname = document.getElementById("fname").value; // 지역 변수(Local Variable)
    let phone = document.getElementById("phone").value;

    if (fname.length == 0) {
        alert("이름을 입력하세요.");
        document.getElementById("fname").focus();
        return;
    }

    if (isNaN(phone) || phone.length != 11) {
        alert("전화번호를 잘 입력하세요.")
        document.getElementById("phone").select();
        return;
    }
    let sendForm = document.getElementById("sendForm");
    sendForm.submit();
}