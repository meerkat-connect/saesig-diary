const adminCommon = {
    toggleSwitch: function ($switchElement, isEnabled) {
        if (isEnabled) {
            if(!$switchElement[0].checked) {
                $switchElement.trigger('click').prop("checked", true);
            }
        } else {
            if ($switchElement[0].checked) {
                $switchElement.trigger('click').removeProperty("checked");
            }
        }
    }
}