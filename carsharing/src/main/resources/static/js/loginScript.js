$(function() {
	$('#loginBtn').on('click', login);
});
// 로그인
function login() {
	let userPw = $('#userPw').val();
	let userId = $('#userId').val();
	
	// 아이디 입력 체크
	if(userId.trim().length < 3 || userId.trim().length > 10) {
		alert('아이디를 3~10글자 이내로 입력하세요.');
		$('#userId').focus();
		return false;	// submit 진행을 중지
	}

	// 비밀번호 길이 체크 (3~5글자)
	if(userPw.trim().length < 4 || userPw.trim().length > 12) {
		alert('비밀번호를 4~12글자 이내로 입력하세요.');
		$('#userPw').focus();
		return false;
	}
	return true;
}