/**
 * 밸리데이터 디폴 값 변경
 * @since 19.04.11
 *
 * **/
//밸리데이터 옵션  alert 형식으로 변경
$.validator.setDefaults({
    debug:true,
    onkeyup:false, // 키를 뗄대 유효성 검사 off
    onclick:false, // checkbox와 radio 버튼 클릭시 유효성 검사 off
    onfocusout:false, // 포커스가 떠날때 유효성 검사 off
    errorElement: "div",
    // focusInvalid: false, // 유형 검사 후 포커서를 해당 무효 필드에 둘 것인가 여부
    focusCleanup: false, // true로 설정되어 있는 경우 잘못된 필드에 포커스가 가면 에러메시지를 지운다.
    errorClass: 'feedback-error',
    // validClass: 'feedback-valid',
    highlight:function(element, errorClass, validClass) {
        console.log($(element));
        $(element).removeClass(validClass).addClass(errorClass).
        next('label').removeAttr('data-success').attr('data-error', 'Incorrect!');
        // $(element).parents('.control-group').addClass('error');
    },

    unhighlight: function(element, errorClass, validClass) {
        console.log($(element));
        console.log($(element).parent().find('div.feedback-error'))
        $(element).parent().find('div.feedback-error').removeClass('icofont-check').addClass('icofont-exclamation');
        /*$(element).removeClass(errorClass).addClass(validClass).
        next('label').removeAttr('data-error').attr('data-success', 'Correct!');*/

        // $(element).parents('.control-group').removeClass('error');

        // $(element).parents('.control-group').addClass('success');
    },

    errorPlacement: function (error, element) {
        // error.prepend('<i class="icofont icofont-check"></i>')
        // element.parent().append(error);
    },
});

//default message 값 변경 English -> korean
$.extend( $.validator.messages,
    {
        //jquery.validate.js 디폴트 메소드 및 메세지
        required: "필수 입력 항목입니다."
        , remote: "항목을 수정해 주십시오."
        , email: "유효하지 않은 E-Mail주소입니다."
        , url: "유효하지 않은 URL입니다."
        , date: "올바른 날짜를 입력하십시오."
        , dateISO: "올바른 날짜(ISO)를 입력하십시오."
        , number: "숫자만 입력가능합니다."
        , digits: "숫자만 입력 가능합니다."
        , creditcard: "신용카드 번호가 바르지 않습니다."
        , equalTo: "같은 값을 다시 입력하십시오."
        , extension: "올바른 확장자가 아닙니다."
        , maxlength: $.validator.format( "{0}자를 넘을 수 없습니다. " ) //maxlength:[8]
        , minlength: $.validator.format( "{0}자 이상 입력하십시오." )
        , rangelength: $.validator.format( "문자 길이가 {0} 에서 {1} 사이의 값을 입력하십시오." ) //rangelength: [2, 10]
        , range: $.validator.format( "{0} 에서 {1} 사이의 값을 입력하십시오." ) //range: [2, 10]
        , max: $.validator.format( "{0} 이하의 값을 입력하십시오." )
        , min: $.validator.format( "{0} 이상의 값을 입력하십시오." )
        /***************** custom Method*******************
         * jquey.validate.add.js  추가 메소드
         *************************************************/
        , alphanumeric: "알파벳과 숫자만 입력 가능합니다."
        , passwordPolicy1 : "영문,숫자만 입력 가능합니다."
        , passwordPolicy2 : "영문,숫자,특수문자 가 하나 이상 입력되어야 합니다."
        , isBlank :"공백이 있습니다."
        , isHangulOn : "한글만 입력가능합니다."
        , isHangulOff : "한글은 입력 할 수 없습니다."
        , isSybolOff : "특수기호는 사용 할 수 없습니다."
        , isRightId : "올바르지 않은 주민번호입니다."
        , isRightBizNo : "올바르지 않는 사업자 번호입니다."
        , emailDomain : "올바른 이메일을 입력해주십시오."
        , hangulOrAlpha : "한글과 알파벳만 사용가능합니다."
        , ckRequired: '내용을 입력해 주십시오.'
    }

);



