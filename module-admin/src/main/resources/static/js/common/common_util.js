/*******************************************************************************
	DHTML 관련 공통 유틸리티

*******************************************************************************/

var browserType			= checkBrowserType();	// 사용자 브라우져 타입


/**
 * 사용자 브라우져 체크
 */
function checkBrowserType() {
	var brType = "IE";
	if (document.all){
		brType = "IE";
	}
	else if (document.getElementById){
		brType = "NE";
	}
	else if (document.layers){
		brType = "NE4";
	}

	return brType;
}


/**
 * 마우스 X 위치
 */
function checkMouseX(evt) {
	var xCoord = 0;
	if (browserType == "IE"){
		xCoord = event.clientX;
	}
	else if (browserType == "NE"){
		xCoord = evt.pageX;
	}
	return xCoord;
}


/**
 * 마우스 Y 위치
 */
function checkMouseY(evt) {
	var yCoord = 0;
	if (browserType == "IE"){
		yCoord = event.clientY;
	}
	else if (browserType == "NE"){
		yCoord = evt.pageY;
	}
	return yCoord;
}


/**
 * 마우스 버튼값 구하기
 * @return left,center,right
 */
function getMouseButton(evt) {
	var button 		= "left";
	var mouseKey	= 1;
	if (browserType == "IE") {
		mouseKey = event.button
		if (mouseKey == 1) {
			button = "left";
		}
		else if (mouseKey == 2) {
			button = "right";
		}
		else if (mouseKey == 4) {
			button = "center";
		}
	}
	else {
		mouseKey = evt.which;
		if (mouseKey == 1) {
			button = "left";
		}
		else if (mouseKey == 2) {
			button = "center";
		}
		else if (mouseKey == 3) {
			button = "right";
		}
	}
	return button;
}


/**
 * 객체의 넓이 구하기
 */
function getWidth(obj) {
	if (obj) {
		var objWidth = obj.offsetWidth;
		return objWidth;
	}
}


/**
 * 객체의 높이 구하기
 */
function getHeight(obj) {
	if (obj) {
		var objHeight = obj.offsetHeight;
		return objHeight;
	}
}


/**
 * 윈도우 넓이 구하기
 */
function getWindowWidth() {
	var winWidth = 0;
	if (browserType == "IE") {
		winWidth = document.body.offsetWidth;
	}
	else if (browserType == "NE") {
		winWidth = window.innerWidth;
	}
	return winWidth;
}


/**
 * 윈도우 높이 구하기
 */
function getWindowHeight() {
	var winHeight = 0;
	if (browserType == "IE") {
		winHeight = document.body.offsetHeight;
	}
	else if (browserType == "NE") {
		winHeight = window.innerHeight;
	}
	return winHeight;
}


/**
 * 스크롤값(X) 구하기
 */
function getScrollX() {
	var scrollX		= 0;

    if (browserType == "NE") {
        scrollX		= window.pageXOffset;
    }
    else {
        scrollX		= document.body.scrollLeft;
    }

	return scrollX;
}


/**
 * 스크롤값(Y) 구하기
 */
function getScrollY() {
	var scrollY		= 0;

	if (browserType == "NE") {
        scrollY		= window.pageYOffset;
    }
    else {
        scrollY		= document.body.scrollTop;
    }

	return scrollY;
}

/**
 * 객체 DISPLAY 변경
 */
function changeDisplay(obj) {
	if (obj) {
		var displayStyle = obj.style.display;
		if (displayStyle == "block") {
			offDisplay(obj);
		}
		else if (displayStyle == "none") {
			onDisplay(obj);
		}
	}
}


/**
 * DISPLAY on
 */
function onDisplay(obj) {
	if (obj) {
		obj.style.display = "block";
	}
}


/**
 * DISPLAY off
 */
function offDisplay(obj) {
	if (obj && obj.style != undefined) {
		obj.style.display = "none";
	}
}


/**
 * 객체 보이기
 */
function visibleObject(obj) {
	if (obj) {
		obj.style.visibility = "visible";
	}
}


/**
 * 객체 숨기기
 */
function hiddenObject(obj) {
	if (obj) {
		obj.style.visibility = "hidden";
	}
}


/**
 * 객체 선택시 색상 반전
 */
function onSelectColor(obj) {
	if (obj) {
		obj.style.backgroundColor	= "highlight";
		obj.style.color				= "highlighttext";
	}
}


/**
 * 객체 선택 비활성 색상 복원
 */
function offSelectColor(obj, color, bgColor) {
	if (obj) {
		if (color == null) {
			color = "";
		}
		if (bgColor == null) {
			bgColor = "";
		}
		obj.style.color				= color;
		obj.style.backgroundColor	= bgColor;
	}
}


/*
 * 객체 사이즈 설정
 */
function resizeObject(obj, newWidth, newHeight) {
	if (obj) {
		obj.style.width     = newWidth+"px";
		obj.style.height    = newHeight+"px";
	}
}


/**
 * 객체 넓이 설정
 */
 function resizeWidth(obj, newWidth) {
 	if (obj) {
		obj.style.width		= newWidth+"px";
	}
 }


/**
 * 객체 높이 설정
 */
function resizeHeight(obj, newHeight) {
	if (obj) {
		obj.style.height	= newHeight+"px";
	}
}


/**
 * 객체 이동 설정 (위치)
 */
function moveObject(obj, topPosition, leftPosition) {
	if (obj) {
		if (nullToEmpty(topPosition) != "") {
			obj.style.top       = topPosition+"px";
		}
		if (nullToEmpty(leftPosition) != "") {
			obj.style.left      = leftPosition+"px";
		}
	}
}


/**
 * 객체의 위치 (document 내에서 절대적인 위치)
 */
function getPosition(obj) {
	var pos = { x:0, y:0 };

	if ( document.layers ) {
		pos.x = obj.x;
		pos.y = obj.y;
	}
	else {
		do {
			pos.x += parseInt( obj.offsetLeft );
			pos.y += parseInt( obj.offsetTop );
			obj = obj.offsetParent;
		} while (obj);
	}

	return pos;
}


/**
 * 객체의 왼쪽 위치 (상대위치)
 */
function getLeftPosition(obj) {
	var value = 0;
	if (obj) {
		if (browserType == "IE") {
			if (obj.currentStyle.left == "auto") {
				value = 0;
			}
			else {
				value = parseInt(obj.currentStyle.left);
			}
		}
		else {
			value = parseInt(obj.style.left);
		}
	}
	return value;
}


/**
 * 객체 위쪽 위치 (상대위치)
 */
function getTopPosition(obj) {
	var value = 0;
	if (obj) {
		if (browserType == "IE") {
			if (obj.currentStyle.top == "auto") {
				value = 0;
			}
			else {
				value = parseInt(obj.currentStyle.top);
			}
		}
		else {
			value = parseInt(obj.style.top);
		}
	}
	return value;
}


/**
 * 객체의 속성값 구하기
 */
function getObjectAttribute(obj, attrName) {
	var attrValue	= null;

	if (obj) {
		attrValue	= obj.getAttribute(attrName);
	}
	return attrValue;
}


/**
 * 객체ID 구하기
 */
function getObjectId(obj) {
	var objectId = getObjectAttribute(obj, "id");
	return objectId;
}


/**
 * 객체를 맨 위로 위치하기
 */
var makeOnTopCount = 0;
function makeOnTop(obj) {
	if (obj) {
		var daiz;
		var max = 0;
		var da = null;
		if (browserType == "IE") {
			da = document.all;
		}
		else if (browserType == "NE") {
			da = document.getElementsByTagName("div");
		}

		makeOnTopCount += 1;
		obj.style.zIndex  = da.length + makeOnTopCount;
	}
}


/**
 * Attribute의 Value 추출 (name=value 에서 value 추출
 */
function getAttributeValue(attr, name) {
	attr = nullToEmpty(attr).toLowerCase();
	var value = "";
	if (attr.length > 1) {
		var idx1 = attr.indexOf(name);
		if (idx1 != -1) {
			var idx2 = attr.indexOf("=",idx1);
			if (idx2 != -1) {
				var idx3 = attr.indexOf(",",idx2);
				var oValue = "";
				if (idx3 == -1) {
					oValue = attr.substring(idx2+1);
				}
				else {
					oValue = attr.substring(idx2+1,idx3);
				}
				for (var i = 0; i < oValue.length; i++) {
					if (oValue.charCodeAt(i) != 32) {
						value += oValue.charAt(i);
					}
				}

			}
		}

	}
	return value;
}


/**
 * 이미지 변경
 */
function changeImage(imgObj, imgSrc) {
	imgObj.src = imgSrc;
}


/**
 * 문자열에서 구분자로 분자열 분리하기
 * @param str		문자열
 * @param idx		구분자를 기준으로 n번째 문자열 추출
 * @param divideStr 구분문자
 */
function getDivideString(str, idx, divideChar) {
	var result = "";
	if (nullToEmpty(str) != "") {
		var strArr = str.split(divideChar);
		result = strArr[idx-1];
	}

	if (!result) {
		result = "";
	}

	return result;
}


/**
 * null 값을 ""으로 변환
 */
function nullToEmpty(value) {
	if (value == null) {
		value = "";
	}
	return value;
}


/**
 * URL 엔코딩
 */
function getUrlEncode(url) {
	var result = "";
	if (nullToEmpty(url) != "") {
		result = encodeURIComponent(url);
		result = result.replace(/\%/g,'*');
	}
	return result;
}


/**
 * URL 디코딩
 */
function getUrlDecode(url) {
	var result = "";
	if (nullToEmpty(url) != "") {
		result = url.replace(/\*/g,'%');
		result = decodeURIComponent(result);
	}
	return result;
}


/**
 * 파일의 확장명 반환
 */
function getFileExtention(fileName) {
	var ext = "";

	if (nullToEmpty(fileName) != "") {
		var idx = fileName.lastIndexOf(".");
		if (idx > -1) {
			ext = (fileName.substring(idx+1)).toLowerCase();
		}
	}

	return ext;
}


var workProgressBox			= null;		// 작업중 박스 객체
var workProgressBox2		= null;		// 작업중 박스 객체
var workProgressBoxWidth	= 64;
var workProgressBoxHeight	= 64;

/**
 * 작업중 박스 표시
 */
function displayWorkProgress(hidden) {
    if (hidden) {
        workProgressBox = {};
        return true;
    }else if (workProgressBox == null && hidden == undefined) {
		var winWidth	= getWindowWidth();
		var winHeight	= getWindowHeight();

		var winLeft		= (winWidth - workProgressBoxWidth) / 2;
		/*
		var winTop		= (winHeight - workProgressBoxHeight) / 2;
		if (winTop < 0) {
			winTop = 0;
		}

		var scrollTop = document.body.scrollTop;

		if(scrollTop == undefined) scrollTop = 0;

		winTop = winTop + parseInt(scrollTop, 10);
		*/
		workProgressBox = document.createElement("div");
		workProgressBox.style.position	= "absolute";
		workProgressBox.style.display	= "block";

		workProgressBox.style.left		= winLeft + "px";
		workProgressBox.style.top		= "50%";//winTop + "px";

		//workProgressBox.style.backgroundColor = "white";
		//workProgressBox.style.border	= "1px solid #B8BDED";
		workProgressBox.style.zIndex="9999";

		var boxContent = ""

			+ " <div class='loadingBox'>"
			+ " 	<img src=\"/images/mng/loading03.gif\" style=\"width:25%;height:25%\">"
			+ " </div>";

		workProgressBox.innerHTML = boxContent;
		document.body.appendChild(workProgressBox);
        return true;
	}else {
		//alert('처리중인 작업이 있습니다.');
        return false;
		//onDisplay(workProgressBox);
	}
}

function displayWorkProgress2() {
	closeWorkProgress2();
	//if (workProgressBox == null) {
		var winWidth	= getWindowWidth();
		var winHeight	= getWindowHeight();

		var winLeft		= (winWidth - workProgressBoxWidth) / 2;
		var winTop		= (winHeight - workProgressBoxHeight) / 2;
		if (winTop < 0) {
			winTop = 0;
		}

		var scrollTop = document.body.scrollTop;

		if(scrollTop == undefined) scrollTop = 0;

		winTop = winTop + parseInt(scrollTop, 10);

		workProgressBox2 = document.createElement("div");
		workProgressBox2.style.position	= "absolute";
		workProgressBox2.style.display	= "block";
		workProgressBox2.style.left		= winLeft;
		workProgressBox2.style.top		= winTop;

		workProgressBox2.style.backgroundColor = "white";
		workProgressBox2.style.border	= "1px solid #B8BDED";
		workProgressBox2.style.zIndex="99999999";

		var boxContent = ""
			+ "<table border=0 cellapcing=0 cellpadding=0>"
			+ "<tr>"
			+ "  <td>"
			+ "    <object classid='clsid:d27cdb6e-ae6d-11cf-96b8-444553540000'"
			+ "        codebase='http://fpdownload.macromedia.com/pub/shockwave/cabs/flash/swflash.cab#version=8,0,0,0'"
			+ "        id='workPregress'"
			+ "        width='"+workProgressBoxWidth+"' height='"+workProgressBoxHeight+"' align='middle'>"
			+ "    <param name='allowScriptAccess' value='always' />"
			+ "    <param name='movie' value='/images/back/common/loading.swf' />"
			+ "    <param name='quality' value='high' />"
			+ "    <param name='scale' value='exactfit' />"
			+ "    <param name='wmode' value='transparent' />"
			+ "    <embed src='/images/back/common/loading.swf' "
			+ "        wmode='transparent' scale='exactfit' quality='high'"
			+ "        width='"+workProgressBoxWidth+"' height='"+workProgressBoxHeight+"'"
			+ "        id='workProgress' name='workProgress' align='middle' allowScriptAccess='always'"
			+ "        type='application/x-shockwave-flash' pluginspage='http://www.macromedia.com/go/getflashplayer' />"
			+ "    </object>"
			+ "  </td>"
			+ "</tr>"
			+ "</table>";

		workProgressBox2.innerHTML = boxContent;
		document.body.appendChild(workProgressBox2);
	//}
	//else {
	//	onDisplay(workProgressBox);
	//}
}


/**
 * 작업중 박스 닫기
 */
function closeWorkProgress() {
	if (workProgressBox != null) {
		offDisplay(workProgressBox);
		workProgressBox = null;
	}
}

function closeWorkProgress2() {
	if (workProgressBox2 != null) {
		offDisplay(workProgressBox2);
	}
}


function MM_swapImgRestore() { //v3.0
  var i,x,a=document.MM_sr; for(i=0;a&&i<a.length&&(x=a[i])&&x.oSrc;i++) x.src=x.oSrc;
}

function MM_preloadImages() { //v3.0
  var d=document; if(d.images){ if(!d.MM_p) d.MM_p=new Array();
    var i,j=d.MM_p.length,a=MM_preloadImages.arguments; for(i=0; i<a.length; i++)
    if (a[i].indexOf("#")!=0){ d.MM_p[j]=new Image; d.MM_p[j++].src=a[i];}}
}

function MM_findObj(n, d) { //v4.01
  var p,i,x;  if(!d) d=document; if((p=n.indexOf("?"))>0&&parent.frames.length) {
    d=parent.frames[n.substring(p+1)].document; n=n.substring(0,p);}
  if(!(x=d[n])&&d.all) x=d.all[n]; for (i=0;!x&&i<d.forms.length;i++) x=d.forms[i][n];
  for(i=0;!x&&d.layers&&i<d.layers.length;i++) x=MM_findObj(n,d.layers[i].document);
  if(!x && d.getElementById) x=d.getElementById(n); return x;
}

function MM_swapImage() { //v3.0
  var i,j=0,x,a=MM_swapImage.arguments; document.MM_sr=new Array; for(i=0;i<(a.length-2);i+=3)
   if ((x=MM_findObj(a[i]))!=null){document.MM_sr[j++]=x; if(!x.oSrc) x.oSrc=x.src; x.src=a[i+2];}
}

function iecompattest(){
	return (document.compatMode && document.compatMode!="BackCompat")? document.documentElement : document.body
}

function toUTF8(szInput) {
	var wch,x,uch="",szRet="";
 	for (x=0; x<szInput.length; x++) {
  		wch=szInput.charCodeAt(x);
  		if (!(wch & 0xFF80)) {
   			szRet += "%" + wch.toString(16);
  		} else if (!(wch & 0xF000)) {
   			uch = "%" + (wch>>6 | 0xC0).toString(16) +"%" + (wch & 0x3F | 0x80).toString(16);
   			szRet += uch;
  		}else {
			uch = "%" + (wch >> 12 | 0xE0).toString(16) +
      		"%" + (((wch >> 6) & 0x3F) | 0x80).toString(16) +
      		"%" + (wch & 0x3F | 0x80).toString(16);
   			szRet += uch;
  		}
 	}
 	return(szRet);
}

function WeditorSetValue(instance, obj){
	obj.value = getMovieName(instance).getHtml();
}

//input hidden 생성
function fnAppendHidden(p_form, p_obj){
	var hiddenTag = "";
	var chk = false;
	jQuery.map(p_obj, function(value, index){
		chk = false;
		jQuery("[name="+p_form+"]").find("input[name='"+index+"']").each(function(){
			chk = true;
			return chk;
		});
		if(!chk){
			hiddenTag += '<input type="hidden" name="'+index+'" id="'+index+'" value="'+value+'" />';
		}
	});
	jQuery("[name="+p_form+"]").append(hiddenTag);
}


function fileSizePretty(number) {
    if (number === 0) return "0 B";
    var UNITS = ['B', 'KB', 'MB', 'GB', 'TB', 'PB', 'EB', 'ZB', 'YB'];
    var exponent = Math.min(Math.floor(Math.log(number) * Math.LOG10E / 3), UNITS.length - 1);
    var unit = UNITS[exponent];
    return Number((number / Math.pow(1024, exponent)).toPrecision(3)).toLocaleString() + ' ' + unit;
}
