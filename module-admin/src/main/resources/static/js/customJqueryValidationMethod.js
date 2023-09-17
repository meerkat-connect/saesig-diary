$.validator.addMethod('ckRequired', function(value,element){
    const $textarea = $(element);
    $textarea.val(ckeditorApp.getEditorData($textarea.prop('id')).trim());
    return $textarea.val().length > 0;
});