
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
		  alert('세션이 유효하지 않습니다.\n페이지 새로고침을 해주십시오.');
		  //location.href='/';
	  }else if(xhr.status==403){
		  alert('접근권한이 없습니다.');
          //location.href='/';
	  }else if(xhr.status==500){
		  alert('내부 서버 오류입니다.');
	  }else if(e === 'parsererror'){
		  if(xhr.responseText == 'DUPLOGOUT'){
			  alert('중복로그인되어 로그아웃 되었습니다.\n로그인 페이지로 이동합니다.');
			  location.href='/login.html';
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
      location.href='/login.html';
    }
  }
 });
});


jQuery(function(){
    $("#this_href_naver_share_process").click(function(e) {
        e.preventDefault();
        var t = $("#currentPageUrl").val()
          , a = $("#currentPageNavi").val()
          , n = "http://share.naver.com/web/shareView.nhn?url=" + o(t) + "&title=" + o(a);
        if (!confirm("현재 페이지를 네이버에 공유하시겠습니까?"))
            return !1;
        window.open(n)
    });
    
    $("#this_href_kakaostory_share_process").click(function(e) {
        e.preventDefault();
        var t = $("#currentPageUrl").val()
          , a = $("#currentPageNavi").val()
          , n = "https://story.kakao.com/s/share?url=" + o(t) + "&text=" + o(a);
        if (!confirm("현재 페이지를 카카오스토리에 공유하시겠습니까?"))
            return !1;
        window.open(n)
    });
    
    $("#this_href_twitter_share_process").click(function(e) {
        e.preventDefault();
        var t = $("#currentPageUrl").val()
          , a = $("#currentPageNavi").val()
          , n = "https://twitter.com/intent/tweet?url=" + o(t) + "&text=" + o(a);
        if (!confirm("현재 페이지를 트위터에 공유하시겠습니까?"))
            return !1;
        window.open(n)
    });
    
    $("#this_href_facebook_share_process").click(function(e) {
        e.preventDefault();
        var t = $("#currentPageUrl").val()
          , a = $("#currentPageNavi").val()
          , n = "https://www.facebook.com/sharer/sharer.php?u=" + o(t) + "&t=" + o(a);
        if (!confirm("현재 페이지를 페이스북에 공유하시겠습니까?"))
            return !1;
        window.open(n)
    });
});

function o(e) {
    if (null == e || "" == e)
        return "";
    if (e instanceof Array) {
        for (var t = ""; 0 < e.length; ++s)
            "" != t && (t += "&"),
            t += o(e[0]);
        return t
    }
    return e = (e = (e = (e = (e = (e = encodeURIComponent(e)).replace(/\!/g, "%21")).replace(/\*/g, "%2A")).replace(/\'/g, "%27")).replace(/\(/g, "%28")).replace(/\)/g, "%29")
}

/*
* table 태그 내에서 파일 첨부 기능 사용시 업로드 이벤트를 등록하는 함수
* inputTagId : file 타입의 input 태그 id 입력
* filegrpNm : application.yml에서 설정한 uploadPath 입력
* uploadSuccessTagId : 파일 등록 후 추가 되는 li 태그의 부모 ul 태그 id 입력
* atchfileCnt : 첨부파일 개수 (환경동아리에 사용)
* atchfileSize : 첨부파일 크기 (환경동아리에 사용)
* $currentFileCnt : 기존 첨부파일 개수 element (환경동아리에 사용)
* */
function addFileUploadEvent(inputTagId, filegrpNm, uploadSuccessTagId, atchfileCnt, atchfileSize, $currentFileCnt) {
    $('#' + inputTagId).on("change", function (event) {
        let isCurrentFileCnt = $currentFileCnt ? $currentFileCnt.val() : 0
        let uploadFileCnt = atchfileCnt && atchfileSize ? isCurrentFileCnt : 1;
        let objFile = document.querySelector('#' + inputTagId);
        let formData = new FormData();

        for (i = 0; i < objFile.files.length; i++) {
            if(atchfileCnt && atchfileSize){
                if(objFile.files[i].size > (atchfileSize*1024*1024)){
                    alert(atchfileSize + "MG 초과하는 파일은 업로드 하실수 없습니다. : "+objFile.files[i].name); break;
                }else if(uploadFileCnt +objFile.files.length > atchfileCnt){
                    alert("첨부파일은 "+atchfileCnt+ "개까지 가능합니다." ); break;
                }else{
                    uploadFileCnt++;
                }
            }
            formData.append("files", objFile.files[i]);
        }

        formData.append("filegrpid", $("#filegrpid").val());
        formData.append("filegrpNm", filegrpNm);

        if(uploadFileCnt > isCurrentFileCnt){
            if (displayWorkProgress(true)) {
                $.ajax({
                    url: '/uploadMultipleFiles.do',
                    processData: false,
                    contentType: false,
                    data: formData,
                    type: 'POST',
                    success: function (response) {
                        if (response.result == 'fail') {
                            alert(response.msg);
                        } else {
                            if ($("#filegrpid").val() == '0' || $("#filegrpid").val() == '' || $("#filegrpid").val() == null) {
                                $("#filegrpid").val(response[0].filegrpid);
                            }
                            if(atchfileCnt && atchfileSize){
                                $currentFileCnt.val(Number(isCurrentFileCnt)+1);
                            }
                            for (i = 0; i < response.length; i++) {
                                var result = `
                                    <li class='file-block' data_ext='${response[i].fileExtsn.substr(1)}' id="${response[i].fileid}">
                                        <span class="name">
                                            <a href="javascript:void(0)" data-fileid="${response[i].fileid}" data-file-idntfc-key="${response[i].fileIdntfcKey}"
                                             onclick="javascript:downloadFileByFileid(this.dataset.fileid, this.dataset.fileIdntfcKey)"> ${response[i].orginlFileNm}
                                            </a>
                                        </span>

                                        <button type="button" class="file-delete" title="삭제" data-fileid="${response[i].fileid}" data-file-idntfc-key="${response[i].fileIdntfcKey}"
                                              onclick="deleteFile(this.dataset.fileid, this.dataset.fileIdntfcKey)">
                                            <span class="icon icon-circle-close"></span>
                                        </button>
                                    </li>
                                `;
                                $('#' + uploadSuccessTagId).append(result);
                            }
                        }
                        closeWorkProgress();
                    }
                });
                $("#" + inputTagId).val("");
            }
        }
    });
};

function deleteFile(fileid, fileIdntfcKey) {

    if (!confirm("파일을 삭제하시겠습니까?")) {
        return;
    }
    let $currentFileCnt = $('#currentFileCnt');
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
                $currentFileCnt.val($currentFileCnt.val() - 1);
            } else {
                alert("파일삭제중 에러가 발생하였습니다. 관리자에게 문의 부탁드립니다.");
            }
        },
        error: function (error) {
        }
    })
}

jQuery(function (){
    $('.open-msg-btn').on('click',function (event){
        const {target, trgtId} = $(this).data();
        layerPopup.open({target, callback: msgSendFormInit(target, trgtId)},event);
    });
    $('#msgForm textarea[name=cn]').on('keyup input', function() {
        $(this).parent().find('#txtSize').text($(this).val().length);
    });
    $('#msgSendBtn').on('click',function (){
        $('#msgForm').validate({
            rules: {
                cn: {required: true}
            },
            messages: {
                cn: {required: "쪽지 내용을 입력해 주십시오."},
            }
        });
        if(!$('#msgForm').valid()) return;
        let data = $('#msgForm').serialize();
        if (displayWorkProgress(true)) {
            $.ajax({
                url : "/front/msg/insertMsg.do",
                type: 'POST',
                cache : false,
                dataType: 'json',
                data : data,
                success : function (result){
                    alert(result.msg);
                    layerPopup.close({target:'msgFormPopup'});
                }
            });
        }
    });

    msgAddBtn();
});
function msgSendFormInit(target, trgtId) {
    const targetWrap = $('[data-layer-id="' + target + '"]');
    targetWrap.find('textarea[name=cn]').val('').css({height: '70px'}).end()
        .find('.feedback').text('').removeClass('invalid').end()
        .find('#txtSize').text('0').end();

    $.ajax({
        url : "/front/msg/selectTrgtInfo.do",
        type: 'POST',
        cache : false,
        dataType: 'json',
        data : {trgtId: trgtId},
        success : function (result){
            if(result.data.delYn === 'Y'){
                alert('탈퇴한 회원님께는 쪽지 발송이 불가합니다.');
                layerPopup.close({target:'msgFormPopup'});
            }else{
                targetWrap.find('input[name=trgtid]').val(trgtId).end()
                    .find('#trgtName').val(result.data.trgtNm).end()
            }
        }
    });
}
function msgAddBtn(){
    const $listMsgAddBtn = $('.list-msg-add-btn');
    const $clone = $('.msg-context-menu-clone > .toggleParent');

    $listMsgAddBtn.each(function (){
        let $cloneBody = $clone.clone(true);
        let {trgtId} = $(this).data();
        let maskName = $(this).text();
        $(this).text('').append(
            $cloneBody.find('.user-name-span > span').text(maskName).end()
                .find('.open-msg-btn').data({'trgt-id':trgtId}).end()
        );
    });
}

/*
게시글 수 변경
rowPerPage : 게시글 수
listFunc : 리스트 로드 함수
*/
function onChangePageSize(rowPerPage, listFunc) {
    $("#rowPerPage").val(rowPerPage);
    listFunc();
}