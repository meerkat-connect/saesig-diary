
(function($){
    var originalVal = $.fn.val;
    $.fn.val = function(){
        var result =originalVal.apply(this,arguments);
        if(arguments.length>0){
        	if($(this).attr('selectSearchCondition') != undefined){ // 기관/사이트/지역/시도/시군구 검색 select
        	    if($(this).val() == ''){
                    $('#select' + $(this).attr('selectSearchCondition') + $(this).attr('id')).val(' ').trigger('change');
                }else{
                    $('#select' + $(this).attr('selectSearchCondition') + $(this).attr('id')).val($(this).val()).trigger('change');
                }
            }
        }
        return result;
    };
})(jQuery);