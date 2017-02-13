/**
 * Created by lsc on 17-2-2.
 */
function getTime() {
    var date = new Date();
    var yy = date.getFullYear();
    var month = date.getMonth();
    var dd = date.getDate();
    var mm;
    switch (month){
        case 0:
            mm = "January";
            break;
        case 1:
            mm = "February";break;
        case 2:
            mm = "March";break;
        case 3:
            mm = "April";break;
        case 4:
            mm = "May"; break;
        case 5:
            mm = "June";break;
        case 6:
            mm = "July";break;
        case 7:
            mm = "August";break;
        case 8:
            mm = "September";break;
        case 9:
            mm = "October";break;
        case 10:
            mm = "November";break;
        case 11:
            mm = "December";break;
    }
    var DATE = mm +'&nbsp;&nbsp;'+ dd + " , " + yy;
    var hh = (date.getHours()<10?"0":"") + date.getHours();
    var MM = (date.getMinutes()<10?"0":"") + date.getMinutes();
    var ss = (date.getSeconds()<10?"0":"") + date.getSeconds();
    var TIME = hh +":"+ MM +":"+ ss;
    document.getElementById("date").innerHTML = DATE +'&nbsp;&nbsp&nbsp;&nbsp;'+TIME+'&nbsp;&nbsp&nbsp;&nbsp;';
    setTimeout("getTime()",1000);
}
function format(s) {
    return s < 10 ? '0' + s: s;
}
function getTodayDate(days) {
    var date = new Date();
    var yy = date.getFullYear();
    var month = date.getMonth();
    var dd = date.getDate()+days;
    return format(yy)+"-"+format(month+1)+"-"+format(dd);
}