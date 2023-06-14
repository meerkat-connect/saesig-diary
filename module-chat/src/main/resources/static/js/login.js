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
                var form = document.createElement("form");
                form.setAttribute("charset", "UTF-8");
                form.setAttribute("method", "Post");  //Post 방식
                form.setAttribute("action", '/chat'); //요청 보낼 주소
                var hiddenField = document.createElement("input");
                hiddenField.setAttribute("type", "hidden");
                hiddenField.setAttribute("name", "memberId");
                hiddenField.setAttribute("value", targerId);
                form.appendChild(hiddenField);
                document.body.appendChild(form);
                form.submit();

        })
    }

    ,makeList : function (memberData){
        let el = '<div class="message" style="color: #4f6ebd" mekc-member-id="'+memberData.id+'">'
        el += '<div class="photo you"></div>'
        el += '<p class="text me" style="width:50%" >'
        el += memberData.nickname
        el += '</p><p></p></div>'
        $('#chat').append(el)
    }
};

$(document).ready(function () {
    chattingList.init();
})


