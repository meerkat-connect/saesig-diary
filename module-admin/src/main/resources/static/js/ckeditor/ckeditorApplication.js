var ckeditorApp = {
    editorList : {},

    init : function () {
        this.setckeditor()
    }

    ,setckeditor : function (){
        const _this = ckeditorApp;

        const ckEditorList = $("#pcoded").find("[mekc-ck-editor]");
        if( ckEditorList.length > 0 ){
            ckEditorList.each( function(){
                _this.editorList[$(this).attr('id')] = {}
                ClassicEditor
                    .create( this,
                        {extraPlugins: [MyCustomUploadAdapterPlugin],
                            fontSize: {options: ['8px','12px','16px','22px','28px','36px']
                            }
                        }
                    )
                    .then( newEditor => {
                        _this.editorList[$(this).attr('id')] = newEditor;
                    });
            })
        }
    }

    ,getEditorData : function (id) {
        if (this.editorList[id] != undefined){
            return this.editorList[id].getData(id)
        }
    }
}

$(document).ready(function (){
    ckeditorApp.init();
})