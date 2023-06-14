
/**
 * ajax이용중 error발생시 처리
 */
jQuery(function(){
  var token = $("meta[name='_csrf']").attr("content");
  var header = $("meta[name='_csrf_header']").attr("content");

 jQuery.ajaxSetup({
  beforeSend: function(xhr) {
	 xhr.setRequestHeader("AJAX", true);
	 xhr.setRequestHeader(header, token);
  },
  error:function(xhr,e){
	  closeWorkProgress();

	  if(xhr.status==404){
	      alert('해당 요청URL을 찾을 수 없습니다.');
	  }else if(xhr.status==401){
          alert('세션이 유효하지 않습니다.\n페이지 새로고침을 해주십시오.'); // app.sso.isuse=true 일때 주석해제
		  //alert('인증에 실패하였습니다.\n로그인 페이지로 이동합니다.'); // app.sso.isuse=false 일때 주석해제
		  //location.href='/'; // app.sso.isuse=false 일때 주석해제
	  }else if(xhr.status==403){
		  alert('접근권한이 없습니다.');
          //location.href='/';
	  }else if(xhr.status==500){
		  alert('내부 서버 오류입니다.');
	  }else if(e === 'parsererror'){
		  if(xhr.responseText == 'DUPLOGOUT'){
			  alert('중복로그인되어 로그아웃 되었습니다.\n로그인 페이지로 이동합니다.');
			  location.href='/';
              xhr.responseText = 'DUPLOGOUT_COMPLETED';
		  }else{
			  alert('데이터를 파싱하는데 실패하였습니다.');
		  }
	  }else if(e === 'timeout'){
		  alert('요청시간이 초과되었습니다.');
	  }else {
		  alert('알수없는 오류가 발생하였습니다.');
	  }
  },
  complete:function(xhr,status){
    if(xhr.responseText == 'DUPLOGOUT'){
      alert('중복로그인되어 로그아웃 되었습니다.\n로그인 페이지로 이동합니다.');
      location.href='/';
    }
  }
 });
});



/*
* 트리구조 확장 / 접기 기능
* */
function fnExpendable($wrapper, option) {
    var opt = jQuery.extend({

        /* 확장 / 접기 대상 선택자 */
        expendableTargetSelector: 'li'

        /*
            확장 / 접기 시 숨겨지거나 보여질 대상 선택자
            반드시 확장/접기 대상의 자식이여야함
        */
        , expendableTargetChildSelector: 'ol'

        /* 확장 / 접기 시 고정으로 표시될 대상 선택자
            - 반드시 확장/접기 대상의 자식이여야함
            - 해당 대상 맨 앞에 확정 접기 버튼 생성
        */
        , expendableFixedTargetChildSelector: 'div'
    }, option);

    var $targets = null;

    $wrapper.on('click', '.expendable-toggle-btn', function () {
        $(this).closest(".expendable-item").toggleClass('expendable-collapse');
    });

    var refresh = function(){
        $wrapper.addClass('expendable-wrapper');
        $wrapper.find(".expendable-item").addClass('expendable-item-old')
        $targets = $wrapper.find(opt.expendableTargetSelector);
        $targets.each(function(){
            var $item = $(this);
            var $fixed = $item.children(opt.expendableFixedTargetChildSelector);
            var $child = $item.children(opt.expendableTargetChildSelector);
            var childContent = $child.html()
            if (childContent && childContent.trim()) {
                $item.removeClass('expendable-item-old').addClass('expendable-item')
                $fixed.addClass("expendable-item-fixed")
                $child.addClass("expendable-item-child")
                if($fixed && $fixed.children().find(".expendable-toggle-btn").length === 0) {
                    $fixed.find(".card-text").prepend($("<button/>").attr({type : 'button'}).addClass("expendable-toggle-btn"))
                }
            }
        });

        $wrapper
            .find('.expendable-item-old').removeClass('expendable-item expendable-item-old')
            .children('.expendable-item-child').removeClass('expendable-item-child').end()
            .children('.expendable-item-fixed').removeClass('expendable-item-fixed')
            .children('.expendable-toggle-btn').remove()
    }

    var expendAll = function(){
        $targets.removeClass('expendable-collapse')
    }
    var collapseAll = function(){
        $targets.addClass('expendable-collapse')
    }

    refresh();

    return {
        refresh: refresh,
        expendAll: expendAll,
        collapseAll: collapseAll,
    }
}

/*
    * 파일 업로더
    * $wrapper: 파일업로더가 추가될 jquery 객체
    *
    * 단일 파일 업로드의 경우
    *  - name: 단일 파일 업로드의 경우 fileId input name
    *  - fileData: FileVO
    *
    *
    * 다중 파일 업로드의 경우
    *  - name: 다중 파일 업로드의 경우 fileGrpId input name
    *  - fileData: FileVO[]
    *
    * options: {
    *    accept: string
    *      - 파일 시 허용할 파일 MIME 타입(벨리데이션 x)
    *    multiple: boolean
    *      - 다중파일 업로드의 경우 true, 기본값: false
    *    readonly: boolean
    *      - true 일 경우 목록 만 표시 추가/삭제 불가
    *    fnValid: (oldFiles: FileVO[], newFiles: fileData[]) => boolean
    *      - 파일 업로드 전 업로드 api 호출할지 확인 콜백, 기본값: () => true
    *    fnFinish: (files: FileVO[]) => void
    *      - 파일 업로드 후 콜백, 기본값: () => {}
    * }
    *
    * */

function fileUploader($wrapper, filegrpNm, name, fileData, options) {
    $wrapper.addClass('file-uploader-wrapper');
    const opt = $.extend({multiple: false, fnValid: ()=> true, fnFinish: ()=> {}, accept: '*', readonly: false}, options);
    const $btn = $("<button/>").attr({'type': 'button'}).addClass('btn btn-inverse btn-sm').text(opt.multiple ? '파일 추가' : '파일 선택');
    const $fileInp = $("<input>").attr({'type': 'file'}).prop({multiple: opt.multiple, accept: opt.accept});
    const $idInp = $("<input>").attr({'type': 'hidden', name});
    const $fileList = $("<div>").addClass('file-uploader-list').append($("<span/>").text("파일이 존재하지 않습니다.").addClass('no-file-msg'));
    const $fileTotCntParam = $("<span/>").addClass('file-uploader-tot-cnt-param').text('0');
    const $fileTotSizeParam = $("<span/>").addClass('file-uploader-tot-size-param').text('0B');
    const $fileListInfo = $("<div>").addClass('file-uploader-file-info').append([
        '총 ',
        $fileTotCntParam,
        '개 파일 (',
        $fileTotSizeParam,
        ')'
    ]);
    var $fileItemTemplate = $("<div>").addClass("btn-group file-uploader-list-item").append([
        $("<button/>").addClass('btn btn-inverse btn-sm btn-outline-inverse file-uploader-download-btn').attr({type: 'button'}).append([
            $("<i/>").addClass('icofont icofont-paper-clip'),
            $("<span/>").addClass('file-uploader-filename-param'),
            " (",
            $("<span/>").addClass('file-uploader-filesize-param'),
            ")",
        ]),
        opt.readonly ? '' : $("<button/>").addClass('btn btn-inverse btn-sm btn-outline-inverse file-uploader-delete-btn').attr({type: 'button'}).append(
            $("<i/>").addClass('icofont icofont-ui-delete')
        ),
    ]);

    const fileDataArr = [];
    const append = (item) => {
        if (!opt.multiple) {
            fileDataArr.length = 0;
            $fileList.children(":not(.no-file-msg)").remove();
        }
        $fileList.append(
            $fileItemTemplate.clone(true).data({fileid: item.fileid, filegrpid: item.filegrpid, fileIdntfcKey: item.fileIdntfcKey})
                .find('.file-uploader-filename-param').text(item.orginlFileNm).css("text-transform", "none").end()
                .find('.file-uploader-filesize-param').text(fileSizePretty(item.fileSize)).end()
        )
        fileDataArr.push(item);
    }
    const refresh = () => {
        $fileTotCntParam.text(fileDataArr.length);
        $fileTotSizeParam.text(fileSizePretty(fileDataArr.reduce((val, item) => val + item.fileSize, 0)));
    }

    const resetFileInp = () => {
        if (detectBrowser() == "other") {
            $fileInp.val("");
        } else {
            $fileInp.replaceWith($fileInp.clone(true))
        }
    }

    let chkConfirmFg = false;
    const chkConfirm = () => {
        if (!chkConfirmFg) {
            chkConfirmFg = confirm("파일 추가/삭제 페이지 저장여부와 관계없이 반영이 진행됩니다. 진행하시겠습니까?");
        }
        return chkConfirmFg;
    }

    let isNew = true;
    let filegrpid = null;
    if (opt.multiple) {
        if (fileData && fileData.length) {
            filegrpid = fileData[0].filegrpid;
            $idInp.val(filegrpid);
            isNew = false;
            for (const item of fileData) {
                append(item);
            }
        }
    } else if (fileData){
        filegrpid = fileData.filegrpid;
        filegrpid = fileData.filegrpid;
        $idInp.val(fileData.fileid);
        isNew = false;
        append(fileData);
    }
    if (!filegrpid) {
        filegrpid = 0
    }
    refresh();

    $btn.on('click', function () {
        $fileInp.click();
    });

    $fileInp.on('change', function () {
        if (this.files.length) {
            const oldFileDataArr  = [...(fileDataArr.map(item => ({...item})))];
            if (opt.fnValid(oldFileDataArr, this.files)) {
                if(!isNew && opt.multiple && !chkConfirm()) {
                    $fileInp.val("");
                    return;
                }

                if(displayWorkProgress()){
                    var formData = new FormData();
                    formData.append("filegrpNm", filegrpNm);
                    formData.append("filegrpid", filegrpid);
                    if (opt.multiple) {
                        for(const file of this.files) {
                            formData.append("files", file);
                        }

                        $.ajax({
                            url : '/uploadMultipleFiles.do',
                            processData : false,
                            contentType : false,
                            data : formData,
                            type : 'POST',
                            success : function(response) {
                                closeWorkProgress();
                                if (response.result === 'fail') {
                                    alert(response.msg);
                                } else {
                                    $idInp.val(response[0].filegrpid);
                                    for (const file of response) {
                                        append(file);
                                    }
                                    refresh();
                                }
                            }
                        })

                        resetFileInp();
                    } else {
                        formData.append("file", this.files[0]);
                        $.ajax({
                            url : '/uploadFile.do',
                            processData : false,
                            contentType : false,
                            data : formData,
                            type : 'POST',
                            success : function(response) {
                                closeWorkProgress();
                                isNew = true;
                                if (response.result === 'fail') {
                                    alert(response.msg);
                                } else {
                                    filegrpid = response.filegrpid;
                                    $idInp.val(response.fileid);
                                    append(response);
                                    refresh();
                                }
                            }
                        });
                    }
                }
                resetFileInp();
            }
        }
    });

    $wrapper
        .on('click', '.file-uploader-download-btn', function(){
            const $parent = $(this).closest('.file-uploader-list-item');
            const data = $parent.data();
            downloadFileByFileid(data.fileid, data.fileIdntfcKey);
        }).on('click', '.file-uploader-delete-btn', function(){
        const $parent = $(this).closest('.file-uploader-list-item');
        const data = $parent.data();

        if(!isNew && !chkConfirm()) {
            $fileInp.val("");
            return;
        }
        if(displayWorkProgress()){
            $.ajax({
                url : '/deleteFile.do',
                type: 'GET',
                cache : false,
                dataType: 'json',
                data: {
                    fileid: data.fileid,
                    file_idntfc_key: data.fileIdntfcKey
                },
                success : function (data){
                    closeWorkProgress();
                    if(data.result==='success'){

                        if (!opt.multiple) {
                            $idInp.val("");
                        }

                        $parent.remove()
                        fileDataArr.splice(fileDataArr.indexOf(item => item.fileid === data.fileid), 1)
                        refresh();
                    }else{
                        alert("파일삭제중 에러가 발생하였습니다. 관리자에게 문의 부탁드립니다.");
                    }
                }
            })
        }
    })

    $wrapper.append([
        opt.readonly ? '' : $btn,
        $fileList,
        opt.multiple ? $fileListInfo : '',
        opt.readonly ? '' : $fileInp,
        $idInp
    ]);
}


/*
* 엑셀 다운로드 유틸
* $searchForm: 검색폼 jquery 객체
* url: 엑셀 다운로드 주소
* */
function excelDownload($searchForm, url) {
    const inpArr = $searchForm.serializeArray();
    $.fileDownload(url + "?" + $.param(inpArr));
}

function deleteFile(fileid, fileIdntfcKey) {
    if (!confirm("파일을 삭제하시겠습니까?")) {
        return;
    }

    let deleteFileUrl = "/deleteFile.do?fileid=" + fileid + "&file_idntfc_key=" + fileIdntfcKey;
    $.ajax({
        url: deleteFileUrl,
        type: 'GET',
        cache: false,
        dataType: 'json',
        success: function (data) {
            if (data.result == "success") {
                $("#" + fileid).remove();
                alert("파일삭제가 완료 되었습니다.");
            } else {
                alert("파일삭제중 에러가 발생하였습니다. 관리자에게 문의 부탁드립니다.");
            }
        },
        error: function (error) {
        }
    })
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

/*
* popupId : 팝업을 로드할 태그 id 값
* formId : 엑셀 다운로드시 사용할 form id 값
* excelDownUrl : 엑셀 다운로드시 사용되는 경로
* */
function openExcelDownRsnPopup(popupId, formId, excelDownUrl){
    const queryString = `?popupId=${popupId}&formId=${formId}&excelDownUrl=${excelDownUrl}`;
    openPopup(popupId, queryString);

}

/*
* filegrpid : 파일 그룹 아이디
* fileid : 파일 아이디
* fileIdntfcKey : 파일 식별 키
*/
function openFileDownRsnPopup(popupId, filegrpid, fileid, fileIdntfcKey) {
    const queryString = `?filegrpid=${filegrpid}&fileid=${fileid}&fileIdntfcKey=${fileIdntfcKey}`;
    openPopup(popupId, queryString)
}

function openPopup(popupId, queryString) {
    const $popup = $('#' + popupId);
    const popupUrl = '/mng/dwnldDsctn/dwnldDsctnPopup.html' + queryString;

    $popup.load(popupUrl, function (response, status, xhr) {
        if (status == "success") {
            $popup.modal('show');
        }
    });
}
function excelDownloadByMultiForm(multiFormid, url) {
    const formArr = multiFormid.split("/")
    const paramArr = []; 
    formArr.forEach(function(frm){
        paramArr.push(...$("#"+frm).serializeArray())
    })
    $.fileDownload(url + "?" + $.param(paramArr));
}
