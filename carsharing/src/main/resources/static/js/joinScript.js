let lenCheck = false;    
let dupleCheck = false;  

$(function() {
  $('#idDuplCheckBtn').on('click', duplicateCheck);
  $('#joinBtn').on('click', join);
});

function lengthCheck() {
  const userId = $('#userId').val().trim();

  if (userId.length < 3 || userId.length > 10) {
    alert('ID는 3~10글자 사이로 입력해주세요.');
    lenCheck = false;
  } else {
    lenCheck = true;
  }
}

function duplicateCheck() {
  const userId = $('#userId').val().trim();

  $.ajax({
    url: '/user/idCheck',
    method: 'POST',
    data: { "userId": userId },
    success: function(resp) {
      if (resp) {
        dupleCheck = true;
        alert('사용할 수 있는 ID입니다.');
      } else {
        dupleCheck = false;
        alert('사용할 수 없는 ID입니다.');
      }
    }
  });
}

function join() {
  lengthCheck();  
  if (!lenCheck) {
    return false;
  }

  if (!dupleCheck) {
    alert('아이디 중복확인을 해주세요.');
    return false;
  }

  const userPw = $('#userPw').val().trim();
  const userPwCheck = $('#userPwCheck').val().trim();

  if (userPw.length < 4 || userPw.length > 12) {
    alert('비밀번호는 4~12글자 사이로 입력해주세요.');
    $('#userPw').focus();
    return false;
  }

  if (userPw !== userPwCheck) {
    alert('비밀번호를 확인해주세요.');
    $('#userPwCheck').focus();
    return false;
  }

  return true;
}
