(function ($) {
    $(document).on('click', '.file-open-btn', function() {
        $(this).parent().find('input').click();
    })
}(jQuery));