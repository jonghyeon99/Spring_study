$(function() {
    init();
    $('#save').on('click', regist)
});

// 초기화 함수
function init() {
    $.ajax({
        url: '/guestbook/selectAll',
        method: 'GET',
        success : output
    });
}

// 출력 함수
function output(resp) {
	let tag = `
			<table>
				<tr>
					<th>번호</th>
					<th>작성자</th>
					<th>방명록 내용</th>
					<th>기록일</th>
					<th></th>
				<tr>`;
	
    $.each(resp, function(index, item) {
        tag += `
            <tr>
                <td>${index + 1}</td>
                <td>${item["guestName"]}</td>
                <td>${item["content"]}</td>
                <td>${item["regdate"]}</td>
                <td>
                    <input type="button" value="삭제" data-seqno="${item['seqno']}" class="deleteBtn">
                </td>
            </tr>`;
    });
    tag += `</table>`;

    $('#outputdata').html(tag);
    $('.deleteBtn').on('click', deleteItem);
}

// 글 삭제(Ajax)
function deleteItem() {
    let seqno =  $(this).attr('data-seqno');
	let pwd = prompt('비밀번호를 입력하시오');
	
	let sendData = {"seqno" : seqno, "guestPwd" : pwd};
	
	let answer = confirm("정말 지우시겠습니까?");
	
	if(!answer) return;
	
	$.ajax({
		url: "/guestbook/delete",
		method: "GET",
		data : sendData,
		success : init
	})
}

// 글 등록 함수(Ajax)
function regist() {
    let name = $('#guestName').val();
    let pwd = $('#guestPwd').val();
    let content = $('#content').val();

    if (name.trim().length < 1) {	// 공백 제거, ltrim(), rtrim(), trim()
        alert("이름을 입력하세요.");
        $('#guestName').focus();
        return;
    }
	
	if (pwd.trim().length < 3 || pwd.trim().length > 5) {
        alert("비밃번호를 정확이 3~5자리로 입력하세요.");
        $('#guestPwd').select();
        return;
	}
	
	if (content.trim().length < 1) {
        alert("방명록을 입력하세요.");
        $('#content').focus();
        return;
	}

    // 서버로 보낼 데이터 작성
    let sendData = {"guestName" : name, "guestPwd" : pwd, "content" : content};
    // alert(JSON.stringify(sendData));	// JSON.stringify() json을 문자열로
	// let temp = JSON.parse('{"guestName" : name, "guestPwd" : pwd, "content" : content}') // 문자열을 JSON으로 바꿔줌
	
	$.ajax({
		url: '/guestbook/guestbookRegist',
		method: 'POST',
		data : sendData,
		success : function(resp) {
			init();
			clearAll();
		}
	});
}

// 입력상자 지우는 함수
function clerAll() {
	$('#guestName').val('');
	$('#guestPwd').val('');
	$('#content').val('');
}