const chattingList = {

    init : function (){
        this.registEvent()
    }

    ,registEvent : function (){
        const _this = this;
       $(memberList).each(function (){
           _this.makeList(this)
       })

        $('.text').on('click',function (){
                let targerId = $(this).closest('.message').attr('mekc-member-id')
                let chat_id = $(this).closest('.message').attr('mekc-chat-id')
                const form = document.createElement("form");
                form.setAttribute("charset", "UTF-8");
                form.setAttribute("method", "Post");  //Post 방식
                form.setAttribute("action", '/chat/chatting'); //요청 보낼 주소
                let hiddenField = document.createElement("input");
                hiddenField.setAttribute("type", "hidden");
                hiddenField.setAttribute("name", "member_id");
                hiddenField.setAttribute("value", targerId);
                form.appendChild(hiddenField);
                hiddenField = document.createElement("input");
                hiddenField.setAttribute("type", "hidden");
                hiddenField.setAttribute("name", "chat_id");
                hiddenField.setAttribute("value", chat_id);
                form.appendChild(hiddenField);
                hiddenField = document.createElement("input");
                hiddenField.setAttribute("type", "hidden");
                hiddenField.setAttribute("name", "meId");
                hiddenField.setAttribute("value", meId);
                form.appendChild(hiddenField);
                document.body.appendChild(form);
                form.submit();
        })
    }

    ,makeList : function (memberData){
        let el = '<div class="message" mekc-member-id="'+memberData.id+'" mekc-chat-id="'+memberData.chat_id+'">'
        el += '<div class="photo you"></div>'
        el += '<p class="text me" style="width:100%" >'
        el += memberData.nickname
        el += '- last chatting</p><p></p></div>'
        $('#chat').append(el)
    }
};

$(document).ready(function () {
    chattingList.init();
})


