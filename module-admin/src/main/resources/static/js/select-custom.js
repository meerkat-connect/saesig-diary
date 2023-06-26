(function ($) {

    $('.custom-select').each(function() {

        var $select =  $(this);

        $select.hide();

        const customSelectTag = `<div style="position:relative; display:none;" class="test-custom" >
                                            <button class="form-control form-select form-control-round" style="text-align:left;"> 선택 </button>
                                            <ul style="width:100%; border-radius: 10px; margin-top: 5px; z-index:2; position:absolute; padding: 0.5em 0; background:white; border:1px solid #585858;" class="custom-select-ul">
                                                <li style="padding: 0.7em;">option 1</li>
                                                <li style="padding: 0.7em;">option 1</li>
                                                <li style="padding: 0.7em;">option 1</li>
                                                <li style="padding: 0.7em;">option 1</li>
                                                <li style="padding: 0.7em;">option 1</li>
                                            </ul>
                                        </div>`
        $select.after(customSelectTag);


    });

}(jQuery));