var ckeditorApp = {
    init : function () {
        this.setckeditor()
    }

    ,setckeditor : function (){
        let editorList = {};
        const ckEditorList = $("#pcoded").find("[mekc-ck-editor]");
        if( ckEditorList.length > 0 ){
            console.log(this);
            ckEditorList.each( function(){
                editorList[$(this).attr('id')] = {}
                ClassicEditor
                    .create( this,
                        {extraPlugins: [MyCustomUploadAdapterPlugin],
                            fontSize: {options: ['8px','12px','16px','22px','28px','36px']
                            }
                        }
                    )
                    .then( newEditor => {
                        editorList[$(this).attr('id')] = newEditor;
                    });
            })
        }
    }

    ,getEditorData : function (id) {
        if (editorList[id] != undefined){
            return editorList[id].getData(id)
        }
    }
}

$(document).ready(function (){
    ckeditorApp.init();
})