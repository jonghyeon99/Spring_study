$(function () {
    showCarList();
});

function showCarList() {
    $.ajax({
        url: 'showCarList'
        , method: 'GET'
        , success: carList
    });
}

function carList(resp) {
    $('#cars').html('');

    $(resp).each(function(index, data) {
        let carSeq     = data['carSeq'];
        let carType    = data['carType'];
        let carStatus  = data['carStatus'] == '0';
        let ststusValue = carStatus? '예약가능' : '예약 중';
        let statusActivate = carStatus? '':'disabled';

        let tag = `
            <tr>
                <td>${carSeq}</td>
                <td>${carType}</td>
                <td>
                    <input type="button" data-carSeq="${carSeq}" class="carStatusBtn" value="${ststusValue}" ${statusActivate}>
                </td>
            </tr>
        `;
        
        $('#cars').append(tag);
        $('.carStatusBtn').on('click', onClickStatusBtn);
    });
}

function onClickStatusBtn(event) {
    event.stopImmediatePropagation();
    reserveCar($(this).attr("data-carSeq"));
}

function reserveCar(carSeq) {
    $.ajax({
        url: 'reserveCar'
        , method: 'POST'
        , data: { "carSeq": carSeq }
        , success: function(resp) {
            window.location.href=resp;
        }
        , error: function(error) {
            alert("예약 실패! 에러: " + error.responseText);
        }
    });
}