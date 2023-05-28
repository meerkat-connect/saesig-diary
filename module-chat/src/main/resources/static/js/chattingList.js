const chattingList = {

    init : function (){
        this.registEvent()
    }

    ,registEvent : function (){
        const _this = this;
       $(chatList).each(function (){
           _this.makeList(this)
       })

        $('.text').on('click',function (){
                let chat_id = $(this).closest('.message').attr('mekc-chat-id')
                _this.makeChatRoom(chat_id);
        })
    }

    ,makeList : function (memberData){
        let el = '<div class="message" mekc-chat-id="'+memberData.chat_id+'">'
        el += '<div class="photo you"></div>'
        el += '<p class="text me" style="width:100%" >'
        el += memberData.title
        el += '- '+memberData.last_msg+'</p><p></p></div>'
        $('#chat').append(el)
    }

    ,makeTargetSelect : function (){
        const _this = this;
        var params = {
            memberId      : meId
        }
        $.ajax({
            type: "post",
            enctype: 'multipart/form-data',
            url: "/chat/getMemberList",
            ContentType :'application/json',
            processData: false,

            cache: false,
            success: function(result) {
                $("#resultDiv").replaceWith(result);
                _this.memberData = JSON.parse($('#resultDiv p').text());
                $("#resultDiv").empty();
                _this.makeMemberSelect()
            },
            error: function(e) {
                console.log(e)
            }
        });
    }

    ,makeMemberSelect : function (){
        $('#memberSelectBox').remove()
        const _this = this;
        var select = document.createElement('select');
        select.id = 'memberSelectBox';
        select.name = 'memberSelectBox';

        var option = document.createElement("option");
        option.value = null
        option.text = "선택";
        select.appendChild(option);

        for(var i in _this.memberData){
            if (meId != _this.memberData[i]['ID']){
                var option = document.createElement("option");
                option.value = _this.memberData[i]['ID'];
                option.text = _this.memberData[i]['NICKNAME'];
                select.appendChild(option);
            }
        }
        $('.footer-chat').prepend(select);
        $(select).on('change',function (){
            _this.makeChatRoom(null,$(this).val());
        })
    }
        ,makeChatRoom : function (chat_id=null,targerId = null){
        if (chat_id == undefined || chat_id == null || chat_id == ''){
            chat_id = 0
        }

        if (targerId == undefined || targerId == null || targerId == ''){
            targerId = 0
        }
        const form = document.createElement("form");
        form.setAttribute("charset", "UTF-8");
        form.setAttribute("method", "Post");  //Post 방식
        form.setAttribute("action", '/chat/chatting'); //요청 보낼 주소

        let hiddenField = document.createElement("input");
        hiddenField.setAttribute("type", "hidden");
        hiddenField.setAttribute("name", "chatId");
        hiddenField.setAttribute("value", chat_id);
        form.appendChild(hiddenField);
        hiddenField = document.createElement("input");
        hiddenField.setAttribute("type", "hidden");
        hiddenField.setAttribute("name", "meId");
        hiddenField.setAttribute("value", meId);
        form.appendChild(hiddenField);
        hiddenField = document.createElement("input");
        hiddenField.setAttribute("type", "hidden");
        hiddenField.setAttribute("name", "memberId");
        hiddenField.setAttribute("value", targerId);
        form.appendChild(hiddenField);
        $(form).appendTo('body')
        form.submit();
    }
};

$(document).ready(function () {
    chattingList.init();
})


