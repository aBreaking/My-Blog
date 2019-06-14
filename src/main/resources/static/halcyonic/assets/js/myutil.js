/*
*  by liwei
* */

/**
 * 时间戳的友好处理
 * @param timestamp
 * @returns {string}
 */
function dateFormatByTimestamp(timestamp){
    var ts = timestamp.toString().length==10?timestamp*1000:timestamp;
    var dateTime = new Date(ts);
    var nowTime = new Date();
    if(dateTime.getFullYear()!=nowTime.getFullYear()){
        return dateTime.getFullYear()+"年"+(dateTime.getMonth()+1)+"月"+dateTime.getDate()+"日";
    }
    if(nowTime.getDate()==(dateTime.getDate()+1)){	//昨天的
        return "昨天";
    }
    if(nowTime.getDate()==(dateTime.getDate()+2)){	//前天的
        return "前天";
    }

    if(nowTime.getDate() == dateTime.getDate()){	//同一天
        if(nowTime.getHours()>dateTime.getHours()){
            return (nowTime.getHours()-dateTime.getHours())+"小时之前";
        }
        if(nowTime.getMinutes()>dateTime.getMinutes()){
            return (nowTime.getMinutes()-dateTime.getMinutes())+"分钟之前";
        }
        if(nowTime.getSeconds()>dateTime.getSeconds()){
            return (nowTime.getSeconds()-dateTime.getSeconds())+"秒之前";
        }

    }
    var mouth = dateTime.getMonth()+1;
    var day = dateTime.getDate();
    return mouth+"月"+day+"日";
}