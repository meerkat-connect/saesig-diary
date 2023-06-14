/*
function GridHelper(jsGridId, contentPanelId) {
    this.contentToggle = false;
    this.jsGridId = jsGridId;
    this.contentPanelId = contentPanelId;
    this.showContent = function(){
        if(this.contentToggle) return;
        this.contentToggle = !this.contentToggle;
        $('#'+this.contentPanelId).show();
    };
    this.hideContent = function(){
        if(!this.contentToggle) return;
        this.contentToggle = !this.contentToggle;
        $('#'+this.contentPanelId).hide();
    };
    this.loadContent = function(uri){ // 등록/수정 컨텐츠영역 로드시 사용(컨텐츠영역 노출)
        var contentPanelId = this.contentPanelId;
        this.removeHighlight();
        $.ajax({
            cache: false,
            url: uri, 
            type: 'GET',
            async: 'false',
            dataType: 'html',
            success: function(result){
                $('#'+contentPanelId).html(result);
                scrollIntoView(contentPanelId);
            }
        });
        this.showContent();
    };
    this.resetListContent = function(){ // 검색/저장/수정/삭제후 사용(컨텐츠영역 숨김)
        //리셋하고 현재 페이지로 돌아간다.
        var curPage = $("#"+this.jsGridId).jsGrid("option", "pageIndex");
        $("#"+this.jsGridId).jsGrid("reset").done(function(){
            $("#"+this.jsGridId).jsGrid("openPage", curPage);
        });
        this.hideContent(); 
    };
    this.toggleContent = function(){ // 컨텐츠영역에서 취소시 사용(컨텐츠 토글)
        this.contentToggle ? $('#'+this.contentPanelId).hide() : $('#'+this.contentPanelId).show();
        this.contentToggle = !this.contentToggle;
    }
    this.rowClick = function(args) { // 그리드 row클릭시 하이라이트 처리
        this.removeHighlight();
        var $row = $("#"+this.jsGridId).jsGrid("rowByItem", args.item)
        $row.toggleClass("highlight");
    }
    this.removeHighlight = function() { // 그리드 하이라이트 제거(등록)
        var selectedRow = $("#"+this.jsGridId).find('table tr.highlight');
        if (selectedRow.length) {
            selectedRow.toggleClass('highlight');
        };
    }
}
*/
class GridHelper {
    constructor(jsGridId, contentPanelId) {
        this.contentToggle = false;
        this.jsGridId = jsGridId;
        this.contentPanelId = contentPanelId;
    }
    showContent(){
        if(this.contentToggle) return;
        this.contentToggle = !this.contentToggle;
        $('#'+this.contentPanelId).show();
    }
    hideContent(){
        if(!this.contentToggle) return;
        this.contentToggle = !this.contentToggle;
        $('#'+this.contentPanelId).hide();
    }
    loadContent(uri){ // 등록/수정 컨텐츠영역 로드시 사용(컨텐츠영역 노출)
        var contentPanelId = this.contentPanelId;
        this.removeHighlight();
        $.ajax({
            cache: false,
            url: uri, 
            type: 'GET',
            async: 'false',
            dataType: 'html',
            success: function(result){
                $('#'+contentPanelId).html(result);
                scrollIntoView(contentPanelId);
            }
        });
        this.showContent();
    }
    loadContentToTarget(uri,targetElement){ // 등록/수정 컨텐츠영역 로드시 사용(컨텐츠영역 노출, 그리드 숨김)
         $.ajax({
            cache: false,
            url: uri, 
            type: 'GET',
            async: 'false',
            dataType: 'html',
            success: function(result){ //요청이 성공했을때 실행되는 콜백 함수
                  $(targetElement).html(result);
            }
        });
        this.showContent(); 
    }
    loadContentAgain(uri){ // 등록/수정 컨텐츠영역 로드시 사용(컨텐츠영역 노출)
        var contentPanelId = this.contentPanelId;
        $.ajax({
            cache: false,
            url: uri, 
            type: 'GET',
            async: 'false',
            dataType: 'html',
            success: function(result){
                $('#'+contentPanelId).html(result);
                scrollIntoView(contentPanelId);
            }
        });
    }
    resetListContent(){ // 검색/저장/삭제후 사용(컨텐츠영역 숨김)
        //리셋하고 첫번째 페이지로 돌아간다.
        var curPage = $("#"+this.jsGridId).jsGrid("option", "pageIndex");
        $("#"+this.jsGridId).jsGrid("reset");
        this.hideContent(); 
    }
    resetPageContent(){ // 수정후 사용(현재 페이지를 유지할 때 사용, 컨텐츠영역 숨김)
        //리셋하고 현재 페이지로 돌아간다.
        var curPage = $("#"+this.jsGridId).jsGrid("option", "pageIndex");
        $("#"+this.jsGridId).jsGrid("openPage", curPage);
        this.hideContent(); 
    }
    toggleContent(){ // 컨텐츠영역에서 취소시 사용(컨텐츠 토글)
        this.contentToggle ? $('#'+this.contentPanelId).hide() : $('#'+this.contentPanelId).show();
        this.contentToggle = !this.contentToggle;
    }
    rowClick(args) { // 그리드 row클릭시 하이라이트 처리
        this.removeHighlight();
        var $row = $("#"+this.jsGridId).jsGrid("rowByItem", args.item)
        $row.toggleClass("highlight");
    }
    removeHighlight() { // 그리드 하이라이트 제거(등록시 사용)
        var selectedRow = $("#"+this.jsGridId).find('table tr.highlight');
        if (selectedRow.length) {
            selectedRow.toggleClass('highlight');
        };
    }
}

var listToggle = true;
var contentToggle = false;

function loadContent(uri){ // 등록/수정 컨텐츠영역 로드시 사용(컨텐츠영역 노출, 그리드 숨김)
   $.ajax({
        cache: false,
        url: uri, 
        type: 'GET',
        async: 'false',
        dataType: 'html',
        success: function(result){ //요청이 성공했을때 실행되는 콜백 함수
              $('#contentPanel').html(result);
              scrollIntoView('contentPanel');
        }
    });
	showContent(); 
	//hideList();
}
function loadContentToTarget(uri,targetElement){ // 등록/수정 컨텐츠영역 로드시 사용(컨텐츠영역 노출, 그리드 숨김)
   $.ajax({
        cache: false,
        url: uri, 
        type: 'GET',
        async: 'false',
        dataType: 'html',
        success: function(result){ //요청이 성공했을때 실행되는 콜백 함수
              $(targetElement).html(result);
        }
    });
	showContent(); 
	//hideList();
}

function toggleListContent(){
	/*$("#jsGrid").jsGrid("reset");
	toggleContent(); 
	toggleList();*/	
}

function resetListContent(){ // 검색/저장/삭제후 사용(컨텐츠영역 숨김, 그리드 노출)
	//리셋하고 첫번째 페이지로 돌아간다.
	var curPage = $("#jsGrid").jsGrid("option", "pageIndex");
	$("#jsGrid").jsGrid("reset");
	hideContent(); 
	//showList();	
}
function resetPageContent(){ //수정 사용( 혀재 페이지 유지 할 떄 사용, 컨텐츠영역 숨김, 그리드 노출)
	//리셋하고 현재 페이지로 돌아간다.
	var curPage = $("#jsGrid").jsGrid("option", "pageIndex");
	$("#jsGrid").jsGrid("openPage", curPage);
	hideContent(); 
	//showList();	
}

function showList(){
	/*if(listToggle) return;
	listToggle = !listToggle;
	$('#collapseList').collapse('show');*/
}

function hideList(){
	/*if(!listToggle) return;
	listToggle = !listToggle;
	$('#collapseList').collapse('hide');*/
}

function showContent(){
	if(contentToggle) return;
	contentToggle = !contentToggle;
	$('#contentPanel').show();
}

function hideContent(){
	if(!contentToggle) return;
	contentToggle = !contentToggle;
	$('#contentPanel').hide();
}

function toggleList(){ // panel-heading 에서 클릭시 그리드를 접거나 펼때 사용
	/*listToggle ? $('#collapseList').collapse('hide') : $('#collapseList').collapse('show');	
	listToggle = !listToggle;*/
}

function toggleContent(){ // 컨텐츠영역에서 취소시 사용(컨텐츠 토글, 그리드 토글)
	contentToggle ? $('#contentPanel').hide() : $('#contentPanel').show();
	//if(contentToggle && !listToggle) toggleList();
	contentToggle = !contentToggle;
}

function escapeGridData(data){ // 그리드에서 로드한 데이터를 escape 처리함(무조건 사용).
	return JSON.parse($('<div/>').text(JSON.stringify(data)).html());
}