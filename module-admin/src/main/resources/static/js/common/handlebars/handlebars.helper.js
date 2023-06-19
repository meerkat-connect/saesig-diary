/*******handlebar custom function******/ 
Handlebars.registerHelper('trimString', function(passedString) {
    var theString = passedString.substring(11,16);
    return new Handlebars.SafeString(theString)
});

Handlebars.registerHelper('isYN', function(str, comparingStr) {
    if(str == comparingStr)return true;
    else return false;
});

Handlebars.registerHelper('dowNm', function(dow) {
    switch(dow) {
        case 1:
            return '월';
            break;
        case 2:
            return '화';
            break;  
        case 3:
            return '수';
            break;  
        case 4:
            return '목';
            break;  
        case 5:
            return '금';
            break;  
        case 6:
            return '토';
            break;  
        case 7:
            return '일';
            break;
    }
});

Handlebars.registerHelper('monthDayPointFormat', function(date) {
    var goodDate = /^(?:(19|20)[0-9]{2}[\-.](0[1-9]|1[012])[\-.](0[1-9]|[12][0-9]|3[01]))$/;
    const dateArr = goodDate.exec(date);
    if(dateArr){
        return dateArr[2] + "." + dateArr[3];
    }else{
        return date;
    }
});

Handlebars.registerHelper('castNumber', function(number) {
    return Number(number);
});
/**************************************/