const chattingList = {

    init : function (){
        this.registEvent()
    }

    ,registEvent : function (){
        const _this = this;
        $('.message').hide()
       $(chatList).each(function (){
           _this.makeList(this)
       })

        $('#chat').on('click','.text',function (){
                let chatId = $(this).closest('.message').attr('mekc-chat-id')
                _this.makeChatRoom(chatId);
        })
    }

    ,onMessage : function(msg) {//채팅방에서도 해당 메시지를 받아서, 안읽은 메시지 수와함께, 채팅방 텍스트를 변경해주어야함. -> 따라서 채팅리스트와 채팅방 각각 socket통신을 실행시켜 메시지를 송신받아야함
    let _this = this;
        console.log(msg)
    var data = JSON.parse(msg.data);
    var sessionId = data.memberId;
    var message = data.text;

    var cur_session = userId;

    cachedSessionId = sessionId;

    let chatRow = $('#chat').find('[mekc-chat-id='+data.chatId+']');
    if (data.type == 'message'){
        if (chatRow.length == 0){
            const _this = this;
            var params = {
                memberId : data.memberId
            }
            console.log(params)
            $.ajax({
                type: "post",
                async : false,
                enctype: 'multipart/form-data',
                url: "/chat/getMemberData",
                ContentType :'application/json',
                data: params,
                dataType : 'json',
                cache: false,
                success: function(result) {
                    let newChatData = {};
                    newChatData.chatId = data.chatId;
                    newChatData.title = result.memberData.nickname;
                    newChatData.lastMsg = data.text;
                    chattingList.makeList(newChatData)
                    chatRow = $('#chat').find('[mekc-chat-id='+data.chatId+']');
                },
                error: function(e) {
                    console.log(e)
                }
            });
        }
        chatRow.find('.lastChat').text(data.text)
        let unreadCnt = parseInt(chatRow.find('.unreadCnt').text());
        if (isNaN(unreadCnt)){
            unreadCnt = 0;
        }
        chatRow.find('.unreadCnt').text(unreadCnt+1);
    }else if (data.type == "readMessage"){
        chatRow.find('.unreadCnt').text('');
    }
    document.getElementById("chat").scrollTop = document.getElementById("chat").scrollHeight;
}

    ,makeList : function (chatData){
        let el = '<div class="message" mekc-chat-id="'+chatData.chatId+'">'
        el += '<div class="photo you"></div>'
        el += '<p class="text me" style="width:100%" >'
        el += '<span class="name">'+chatData.title+'</span>'
        el += '- <span class="lastChat">'+chatData.lastMsg+'</span><span class="unreadCnt" style="color: red">'
        if (chatData.unreadCnt > 0){
            el += chatData.unreadCnt
        }
        el += '</span></p><p></p></div>'
        $('#chat').prepend(el)
    }

    ,makeTargetSelect : function (){
        const _this = this;
        var params = {
            memberId      : userId
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
            if (userId != _this.memberData[i]['ID']){
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
        ,makeChatRoom : function (chatId=null,targerId = null){
        if (chatId == undefined || chatId == null || chatId == ''){
            chatId = 0
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
        hiddenField.setAttribute("value", chatId);
        form.appendChild(hiddenField);
        hiddenField = document.createElement("input");
        hiddenField.setAttribute("type", "hidden");
        hiddenField.setAttribute("name", "userId");
        hiddenField.setAttribute("value", userId);
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


