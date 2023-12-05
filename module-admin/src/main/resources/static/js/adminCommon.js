jQuery(function () {

    jQuery.ajaxSetup({
        beforeSend: function (xhr) {
        },
        error: function (xhr, e) {
            if(xhr.status == '401') {
                alert('세션이 유효하지 않습니다. 로그인 페이지로 이동합니다.');
                location.href = '/admin/login';
            } else if(xhr.status == '403') {
                alert('접근 권한이 없습니다.')
            } else {
                alert('에러가 발생하였습니다.');
            }
            // 중복로그인 체크
        },
        complete: function (xhr, status) {
            if (xhr.responseText == 'DUPLOGOUT') {
                alert('중복로그인되어 로그아웃 되었습니다.\n로그인 페이지로 이동합니다.');
                location.href = '/';
            }
        }
    });
})

const adminCommon = {
    toggleSwitch: function ($switchElement, isEnabled) {
        if (isEnabled) {
            if(!$switchElement[0].checked) {
                $switchElement.trigger('click').prop("checked", true);
            }
        } else {
            if ($switchElement[0].checked) {
                $switchElement.trigger('click').prop("checked", false);
            }
        }
    }
}

/*
* dateRangePicker
* pickerid  : dateRangePicker id
* startDtId : 시작일시 element id (hidden)
* endDtId   : 종료일시 element id (hidden)
* */
function initDateRangePicker(pickerid,startDtId,endDtId){
    $("#"+pickerid).daterangepicker({
        opens: 'left',
        autoUpdateInput: false,
        locale: {
            "separator": " ~ ",                     // 시작일시와 종료일시 구분자
            "format": 'YYYY-MM-DD',     // 일시 노출 포맷
            "applyLabel": "적용",                    // 확인 버튼 텍스트
            "cancelLabel": "취소",                   // 취소 버튼 텍스트
            "daysOfWeek": ["일", "월", "화", "수", "목", "금", "토"],
            "monthNames": ["1월", "2월", "3월", "4월", "5월", "6월", "7월", "8월", "9월", "10월", "11월", "12월"]
        }
    }, function(start, end, label) {
        console.log("A new date selection was made: " + start.format('YYYY-MM-DD') + ' to ' + end.format('YYYY-MM-DD'));
    });
    $("#"+pickerid).on('apply.daterangepicker', function(ev, picker) {
        $("#"+startDtId).val(picker.startDate.format('YYYY-MM-DD'));
        $("#"+endDtId).val(picker.endDate.format('YYYY-MM-DD'));
        $(this).val(picker.startDate.format('YYYY-MM-DD') + '   ~   ' + picker.endDate.format('YYYY-MM-DD'));
    });
    $("#"+pickerid).on('cancel.daterangepicker', function(ev, picker) {
        $("#"+startDtId).val("");
        $("#"+endDtId).val("");
        $(this).val('');
    });
}


function initDateRangePicker2(pickerEle, startDtEle, endDtEle, option){
    const daysOfWeek = ["일", "월", "화", "수", "목", "금", "토"];
    const ampm = {'AM': '오전', 'PM': '오후'};
    const $picker = $(getDOMElement(pickerEle))
    const $startDt = $(getDOMElement(startDtEle))
    const $endDt = $(getDOMElement(endDtEle))
    const pickerInpFormat = option && option.time ? 'YYYY-MM-DD HH:mm:ss' : 'YYYY-MM-DD'
    $picker.prop({readonly: true}).daterangepicker({
        opens: 'left',
        autoUpdateInput: false,
        startDate: $startDt.val() ? moment($startDt.val()) : moment().startOf('day'),
        endDate: $endDt.val() ? moment($endDt.val()) : moment().startOf('day').hour(12),
        timePicker: !!(option && option.time),
        timePickerIncrement: (option && option.time && typeof option.time === 'object' && option.time.minute) || 5,
        timePicker24Hour: !(option && option.time && typeof option.time === 'object' && option.time.ampm !== undefined ? !!option.time.ampm : false),
        locale: {
            "separator": " ~ ",                     // 시작일시와 종료일시 구분자
            "format": option && option.time ? 'YYYY-MM-DD HH:mm' : 'YYYY-MM-DD',     // 일시 노출 포맷
            "applyLabel": "적용",                    // 확인 버튼 텍스트
            "cancelLabel": "취소",                   // 취소 버튼 텍스트
            daysOfWeek,
            "monthNames": ["1월", "2월", "3월", "4월", "5월", "6월", "7월", "8월", "9월", "10월", "11월", "12월"],
        }
    });

    $picker.on('apply.daterangepicker', function(ev, picker) {
        $startDt.val(picker.startDate.format(pickerInpFormat));
        $endDt.val(picker.endDate.format(pickerInpFormat));
        $picker.val(getDtRange(picker.startDate, picker.endDate));
    });

    $picker.on('cancel.daterangepicker', function(ev, picker) {
        $startDt.val('');
        $endDt.val('');
        $picker.val('');
    });

    if($startDt.val() && $endDt.val()) {
        $picker.val(getDtRange(moment($startDt.val()), moment($endDt.val())))
    }

    function getDtRange(stDt, enDt){
        if(option && option.time) {
            if (stDt.year() !== enDt.year()) {
                return `${stDt.format('YYYY년 MM월 DD일')} (${daysOfWeek[stDt.day()]}) ${ampm[stDt.format('A')]} ${stDt.format('h:mm')} ~ ${enDt.format('YYYY년 MM월 DD일')} (${daysOfWeek[enDt.day()]})  ${ampm[enDt.format('A')]} ${enDt.format('h:mm')}`
            } else if (stDt.month() !== enDt.month()) {
                return `${stDt.format('YYYY년 MM월 DD일')} (${daysOfWeek[stDt.day()]}) ${ampm[stDt.format('A')]} ${stDt.format('h:mm')} ~ ${enDt.format('MM월 DD일')} (${daysOfWeek[enDt.day()]})  ${ampm[enDt.format('A')]} ${enDt.format('h:mm')}`
            } else if (stDt.date() !== enDt.date()) {
                return `${stDt.format('YYYY년 MM월 DD일')} (${daysOfWeek[stDt.day()]}) ${ampm[stDt.format('A')]} ${stDt.format('h:mm')} ~ ${enDt.format('MM월 DD일')} (${daysOfWeek[enDt.day()]})  ${ampm[enDt.format('A')]} ${enDt.format('h:mm')}`
            } else if (stDt.format('A') !== enDt.format('A')) {
                return `${stDt.format('YYYY년 MM월 DD일')} (${daysOfWeek[stDt.day()]}) ${ampm[stDt.format('A')]} ${stDt.format('h:mm')} ~ ${ampm[enDt.format('A')]} ${enDt.format('h:mm')}`
            } else if (stDt.hour() !== enDt.hour()) {
                return `${stDt.format('YYYY년 MM월 DD일')} (${daysOfWeek[stDt.day()]}) ${ampm[stDt.format('A')]} ${stDt.format('h:mm')} ~ ${enDt.format('h:mm')}`
            } else if (stDt.minute() !== enDt.minute()) {
                return `${stDt.format('YYYY년 MM월 DD일')} (${daysOfWeek[stDt.day()]}) ${ampm[stDt.format('A')]} ${stDt.format('h:mm')} ~ ${enDt.format('mm')}`
            } else {
                return `${stDt.format('YYYY년 MM월 DD일')} (${daysOfWeek[stDt.day()]}) ${ampm[stDt.format('A')]} ${stDt.format('h:mm')}`
            }
        } else {
            if (stDt.year() !== enDt.year()) {
                return `${stDt.format('YYYY년 MM월 DD일')} (${daysOfWeek[stDt.day()]}) ~ ${enDt.format('YYYY년 MM월 DD일')} (${daysOfWeek[enDt.day()]})`
            } else if (stDt.month() !== enDt.month()) {
                return `${stDt.format('YYYY년 MM월 DD일')} (${daysOfWeek[stDt.day()]}) ~ ${enDt.format('MM월 DD일')} (${daysOfWeek[enDt.day()]})`
            } else if (stDt.date() !== enDt.date()) {
                return `${stDt.format('YYYY년 MM월 DD일')} (${daysOfWeek[stDt.day()]}) ~ ${enDt.format('MM월 DD일')} (${daysOfWeek[enDt.day()]})`
            } else {
                return `${stDt.format('YYYY년 MM월 DD일')} (${daysOfWeek[stDt.day()]})`
            }
        }
    }
}

function getDOMElement(target) {
    if(target instanceof jQuery) { // jquery
        return target[0]
    } else if (typeof target === 'string') { // Id
        return document.getElementById(target)
    } else {
        return target
    }
}