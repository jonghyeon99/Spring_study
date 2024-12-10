/**
 * 
 */
$(function() {
	$('#join').on('click', validation)
});

function validation() {
	let userid = $('#userid').val();
	if (userid.length < 3 || userid.length > 5) {
		alert("아이디를 다시 입력하세요.");
		$('#userid').select();
		return;
	}
	let email = $('#email').val();
	if (email.length == 0) {
		alert("이메일을 입력하세요.");
		$('#email').select();
		return;
	}
	let age = $('#age').val();
	if(age < 20 || age > 50) {
		alert("나이는 20~50살 사이만 가입 가능합니다.");
		$('#age').select();
		return;
	}
	
	// 데이터를 서버로 전송
	$('#joinForm').submit();
}