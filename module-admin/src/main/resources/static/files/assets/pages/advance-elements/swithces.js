"use strict";
var switchCherryArr=[] ;
var switchCherryCallback;
$(document).ready(function() {
    // Single swithces small (22/11/16 JM sm버전을 위해 추가함
    var elemsingleSmallList = document.querySelectorAll('.js-single-small');
    for(var i=0; i < elemsingleSmallList.length; i++){
        switchCherryArr[elemsingleSmallList[i].getAttribute('id')]  = new Switchery(elemsingleSmallList[i], { color: '#4680ff', jackColor: '#fff', size : 'small'});
    }
    if(switchCherryCallback != undefined && typeof switchCherryCallback === 'function'){
        switchCherryCallback();
    }
});
