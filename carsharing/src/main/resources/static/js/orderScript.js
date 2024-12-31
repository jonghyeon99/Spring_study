$(function () {
    showMyCarList();
});

// 동적으로 내 예약 리스트 생성
function showMyCarList(){
    $.ajax({
        url: 'showMyCarList'
        , method: 'GET'
        , success: myCarList
    });
}

function myCarList(resp){
    // 표 초기화
    $('#result').html('');

    // 데이터가 없는 경우
    if (!resp || resp.length === 0) {
        $('#result').html('<h4>예약/반납 내역이 없습니다.</h4>');
        return;
    }

    // 데이터 출력
    $('#result').append(`<table>
                            <thead>
                                <tr>
                                    <th>차량번호</th>
                                    <th>차종</th>
                                    <th>예약일자</th>
                                    <th colspan="2">반납여부</th>
                                </tr>
                            </thead>
                            <tbody id="dataResult">
                            </tbody>
                        </table>`
    );

    $(resp).each(function(index, data){
        let orderSeq        = data['orderSeq'];
        let isMyOrder       = data['isMyOrder'];
        let carId           = data['carId'];
        let carType         = data['carType'];
        let sharingDate     = data['sharingDate'].replace('T', ' ');
        let sharingStatus   = data['sharingStatus'] == '0';
        let returnBtnTagTxt = '';

        if (sharingStatus)
        {
            if (isMyOrder)
            {
                returnBtnTagTxt = `<td>예약 중</td>
                <td><input type="button" data-OrderSeq="${orderSeq}" class="returnBtn" value="반납하기"></td>`;
            }
            else
            {
                returnBtnTagTxt = `<td colspan="2">다른 회원 예약 중</td>`;
            }
        }
        else
        {
            returnBtnTagTxt = `<td colspan="2">반납완료</td>`;
        }                

        let tag = `
            <tr>
                <td>${carId}</td>
                <td>${carType}</td>
                <td>${sharingDate}</td>
                ${returnBtnTagTxt}
            </tr>
        `;
        
        $('#dataResult').append(tag);
        $('.returnBtn').on('click', onClickReturnBtn);
    });
}

function onClickReturnBtn(event)
{
    event.stopImmediatePropagation();
    returnCar($(this).attr("data-OrderSeq"));
}

function returnCar(orderSeq){
    $.ajax({
        url: 'returnCar'
        , method: 'POST'
        , data: { "orderSeq": orderSeq }
        , success: function(resp) {
            window.location.href=resp;
        }
        , error: function(error) {
            alert("예약 실패! 에러: " + error.responseText);
        }
    });
}