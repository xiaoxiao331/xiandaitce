String.prototype.getBytes = function() {
    var str = this;
    var len = 0;
    for (var i=0; i<str.length; i++) {
        len += (str.charCodeAt(i) > 127) ? 2 : 1;
    }
    return len;
}

/**
 * 전화번호 형식 변경 (문자열 리턴)
 * 
 * @param str
 * @returns {String}
 */
function gfn_changeTelNoFormat(str) {
    if (!str) {
        return str;
    }
    
    var onlynum = "";
    for (var i=0; i<str.length; i++) {
        var ch = str.charAt(i);
        if (ch >= '0' && ch <= '9') {
            onlynum += ch;
        }
    }
    
    var rtnStr = "";
    // 서울지역 전화번호
    if (onlynum.substring(0,2) == "02") {
        if (onlynum.getBytes() <= 1) rtnStr = onlynum;
        if (onlynum.getBytes() == 2) rtnStr = onlynum + "-";
        if (onlynum.getBytes() == 3) rtnStr = onlynum.substring(0,2) + "-" + onlynum.substring(2,3);
        if (onlynum.getBytes() == 4) rtnStr = onlynum.substring(0,2) + "-" + onlynum.substring(2,4);
        if (onlynum.getBytes() == 5) rtnStr = onlynum.substring(0,2) + "-" + onlynum.substring(2,5) + "-";
        if (onlynum.getBytes() == 6) rtnStr = onlynum.substring(0,2) + "-" + onlynum.substring(2,5) + "-" + onlynum.substring(5,6);
        if (onlynum.getBytes() == 7) rtnStr = onlynum.substring(0,2) + "-" + onlynum.substring(2,5) + "-" + onlynum.substring(5,7);
        if (onlynum.getBytes() == 8) rtnStr = onlynum.substring(0,2) + "-" + onlynum.substring(2,5) + "-" + onlynum.substring(5,8);
        if (onlynum.getBytes() == 9) rtnStr = onlynum.substring(0,2) + "-" + onlynum.substring(2,5) + "-" + onlynum.substring(5,9);
        if (onlynum.getBytes() >= 10) rtnStr = onlynum.substring(0,2) + "-" + onlynum.substring(2,6) + "-" + onlynum.substring(6,10);
    }
    // 국번없는 전화번호
    else if (onlynum.substring(0,1) == "1") {
        if (onlynum.getBytes() <= 3) rtnStr = onlynum;
        if (onlynum.getBytes() == 4) rtnStr = onlynum.substring(0,4) + "-";
        if (onlynum.getBytes() == 5) rtnStr = onlynum.substring(0,4) + "-" + onlynum.substring(4,5);
        if (onlynum.getBytes() == 6) rtnStr = onlynum.substring(0,4) + "-" + onlynum.substring(4,6);
        if (onlynum.getBytes() == 7) rtnStr = onlynum.substring(0,4) + "-" + onlynum.substring(4,7);
        if (onlynum.getBytes() >= 8) rtnStr = onlynum.substring(0,4) + "-" + onlynum.substring(4,8);
    }
    // 050으로 시작하는 안심번호
    else if (onlynum.substring(0,3) == "050") {
        if (onlynum.getBytes() <= 2) rtnStr = onlynum;
        if (onlynum.getBytes() == 3) rtnStr = onlynum + "-";
        if (onlynum.getBytes() == 4) rtnStr = onlynum.substring(0,3) + "-" + onlynum.substring(3,4);
        if (onlynum.getBytes() == 5) rtnStr = onlynum.substring(0,3) + "-" + onlynum.substring(3,5);
        if (onlynum.getBytes() == 6) rtnStr = onlynum.substring(0,3) + "-" + onlynum.substring(3,6) + "-";
        if (onlynum.getBytes() == 7) rtnStr = onlynum.substring(0,3) + "-" + onlynum.substring(3,6) + "-" + onlynum.substring(6,7);
        if (onlynum.getBytes() == 8) rtnStr = onlynum.substring(0,3) + "-" + onlynum.substring(3,6) + "-" + onlynum.substring(6,8);
        if (onlynum.getBytes() == 9) rtnStr = onlynum.substring(0,3) + "-" + onlynum.substring(3,6) + "-" + onlynum.substring(6,9);
        if (onlynum.getBytes() == 10) rtnStr = onlynum.substring(0,3) + "-" + onlynum.substring(3,6) + "-" + onlynum.substring(6,10);
        if (onlynum.getBytes() == 11) rtnStr = onlynum.substring(0,3) + "-" + onlynum.substring(3,7) + "-" + onlynum.substring(7,11);
        if (onlynum.getBytes() >= 12) rtnStr = onlynum.substring(0,3) + "-" + onlynum.substring(4,8) + "-" + onlynum.substring(8,12);
    }
    // 휴대폰 및 기타 지역국번
    else if (onlynum.substring(0,1) == "0") {
        if (onlynum.getBytes() <= 2) rtnStr = onlynum;
        if (onlynum.getBytes() == 3) rtnStr = onlynum + "-";
        if (onlynum.getBytes() == 4) rtnStr = onlynum.substring(0,3) + "-" + onlynum.substring(3,4);
        if (onlynum.getBytes() == 5) rtnStr = onlynum.substring(0,3) + "-" + onlynum.substring(3,5);
        if (onlynum.getBytes() == 6) rtnStr = onlynum.substring(0,3) + "-" + onlynum.substring(3,6) + "-";
        if (onlynum.getBytes() == 7) rtnStr = onlynum.substring(0,3) + "-" + onlynum.substring(3,6) + "-" + onlynum.substring(6,7);
        if (onlynum.getBytes() == 8) rtnStr = onlynum.substring(0,3) + "-" + onlynum.substring(3,6) + "-" + onlynum.substring(6,8);
        if (onlynum.getBytes() == 9) rtnStr = onlynum.substring(0,3) + "-" + onlynum.substring(3,6) + "-" + onlynum.substring(6,9);
        if (onlynum.getBytes() == 10) rtnStr = onlynum.substring(0,3) + "-" + onlynum.substring(3,6) + "-" + onlynum.substring(6,10);
        if (onlynum.getBytes() >= 11) rtnStr = onlynum.substring(0,3) + "-" + onlynum.substring(3,7) + "-" + onlynum.substring(7,11);
    }
    // 기타형식
    else {
        rtnStr = onlynum;
    }
    
    return rtnStr;
}

/**
 * 전화번호 형식 변경 (오브젝트 리턴)
 * 
 * @param telObj
 * @returns {Object}
 */
function gfn_autoTelNoFormat(telObj) {
    var str = telObj.value;
    
    var onlynum = "";
    for (var i=0; i<str.length; i++) {
        var ch = str.charAt(i);
        if (ch >= '0' && ch <= '9') {
            onlynum += ch;
        }
    }
    
    var rtnStr = "";
    // 서울지역 전화번호
    if (onlynum.substring(0,2) == "02") {
        if (onlynum.getBytes() <= 1) rtnStr = onlynum;
        if (onlynum.getBytes() == 2) rtnStr = onlynum + "-";
        if (onlynum.getBytes() == 3) rtnStr = onlynum.substring(0,2) + "-" + onlynum.substring(2,3);
        if (onlynum.getBytes() == 4) rtnStr = onlynum.substring(0,2) + "-" + onlynum.substring(2,4);
        if (onlynum.getBytes() == 5) rtnStr = onlynum.substring(0,2) + "-" + onlynum.substring(2,5) + "-";
        if (onlynum.getBytes() == 6) rtnStr = onlynum.substring(0,2) + "-" + onlynum.substring(2,5) + "-" + onlynum.substring(5,6);
        if (onlynum.getBytes() == 7) rtnStr = onlynum.substring(0,2) + "-" + onlynum.substring(2,5) + "-" + onlynum.substring(5,7);
        if (onlynum.getBytes() == 8) rtnStr = onlynum.substring(0,2) + "-" + onlynum.substring(2,5) + "-" + onlynum.substring(5,8);
        if (onlynum.getBytes() == 9) rtnStr = onlynum.substring(0,2) + "-" + onlynum.substring(2,5) + "-" + onlynum.substring(5,9);
        if (onlynum.getBytes() >= 10) rtnStr = onlynum.substring(0,2) + "-" + onlynum.substring(2,6) + "-" + onlynum.substring(6,10);
    }
    // 국번없는 전화번호
    else if (onlynum.substring(0,1) == "1") {
        if (onlynum.getBytes() <= 3) rtnStr = onlynum;
        if (onlynum.getBytes() == 4) rtnStr = onlynum.substring(0,4) + "-";
        if (onlynum.getBytes() == 5) rtnStr = onlynum.substring(0,4) + "-" + onlynum.substring(4,5);
        if (onlynum.getBytes() == 6) rtnStr = onlynum.substring(0,4) + "-" + onlynum.substring(4,6);
        if (onlynum.getBytes() == 7) rtnStr = onlynum.substring(0,4) + "-" + onlynum.substring(4,7);
        if (onlynum.getBytes() >= 8) rtnStr = onlynum.substring(0,4) + "-" + onlynum.substring(4,8);
    }
    // 050으로 시작하는 안심번호
    else if (onlynum.substring(0,3) == "050") {
        if (onlynum.getBytes() <= 2) rtnStr = onlynum;
        if (onlynum.getBytes() == 3) rtnStr = onlynum + "-";
        if (onlynum.getBytes() == 4) rtnStr = onlynum.substring(0,3) + "-" + onlynum.substring(3,4);
        if (onlynum.getBytes() == 5) rtnStr = onlynum.substring(0,3) + "-" + onlynum.substring(3,5);
        if (onlynum.getBytes() == 6) rtnStr = onlynum.substring(0,3) + "-" + onlynum.substring(3,6) + "-";
        if (onlynum.getBytes() == 7) rtnStr = onlynum.substring(0,3) + "-" + onlynum.substring(3,6) + "-" + onlynum.substring(6,7);
        if (onlynum.getBytes() == 8) rtnStr = onlynum.substring(0,3) + "-" + onlynum.substring(3,6) + "-" + onlynum.substring(6,8);
        if (onlynum.getBytes() == 9) rtnStr = onlynum.substring(0,3) + "-" + onlynum.substring(3,6) + "-" + onlynum.substring(6,9);
        if (onlynum.getBytes() == 10) rtnStr = onlynum.substring(0,3) + "-" + onlynum.substring(3,6) + "-" + onlynum.substring(6,10);
        if (onlynum.getBytes() == 11) rtnStr = onlynum.substring(0,3) + "-" + onlynum.substring(3,7) + "-" + onlynum.substring(7,11);
        if (onlynum.getBytes() >= 12) rtnStr = onlynum.substring(0,3) + "-" + onlynum.substring(4,8) + "-" + onlynum.substring(8,12);
    }
    // 휴대폰 및 기타 지역국번
    else if (onlynum.substring(0,1) == "0") {
        if (onlynum.getBytes() <= 2) rtnStr = onlynum;
        if (onlynum.getBytes() == 3) rtnStr = onlynum + "-";
        if (onlynum.getBytes() == 4) rtnStr = onlynum.substring(0,3) + "-" + onlynum.substring(3,4);
        if (onlynum.getBytes() == 5) rtnStr = onlynum.substring(0,3) + "-" + onlynum.substring(3,5);
        if (onlynum.getBytes() == 6) rtnStr = onlynum.substring(0,3) + "-" + onlynum.substring(3,6) + "-";
        if (onlynum.getBytes() == 7) rtnStr = onlynum.substring(0,3) + "-" + onlynum.substring(3,6) + "-" + onlynum.substring(6,7);
        if (onlynum.getBytes() == 8) rtnStr = onlynum.substring(0,3) + "-" + onlynum.substring(3,6) + "-" + onlynum.substring(6,8);
        if (onlynum.getBytes() == 9) rtnStr = onlynum.substring(0,3) + "-" + onlynum.substring(3,6) + "-" + onlynum.substring(6,9);
        if (onlynum.getBytes() == 10) rtnStr = onlynum.substring(0,3) + "-" + onlynum.substring(3,6) + "-" + onlynum.substring(6,10);
        if (onlynum.getBytes() >= 11) rtnStr = onlynum.substring(0,3) + "-" + onlynum.substring(3,7) + "-" + onlynum.substring(7,11);
    }
    // 기타형식
    else {
        rtnStr = onlynum;
    }
    
    return telObj.value = rtnStr;;
}

/**
 * 숫자만 입력
 * onkeyup="gfn_onlyNum(this)"
 */
function gfn_onlyNum(obj) {
    var value = obj.value;
    
    if (value == null || value == "") {
        return "";
    }
    
    value = value + "";
    var temp = "";
    for (var i=0; i<value.length; i++) {
        var ch = value.charAt(i);
        if (ch >= '0' && ch <= '9') {
            temp += ch;
        }
    }
    
    obj.value = temp;
}

/**
 * Paging 생성
 * 
 * @param htmlObj : 적용될 HtmlObject
 * @param curPage : 현재 페이지
 * @param total : 전체 게시물 개수
 * @param rowsPerPage : 한 페이지에 보여줄 게시물 개수
 * @param callFunc : 페이징 시 호출될 JavaScript 함수
 * @param addParams : 추가 파라미터 (배열)
 */
function gfn_paging(htmlObj, curPage, total, rowsPerPage, callFunc, addParams) {
	alert(curPage);
	alert(total);
	alert(rowsPerPage);
	alert(callFunc);
    var sb = [];
    var cntSize = 15;
    var totalPage = 0;
    var arrParams = (addParams == null || addParams == "undefined") ? new Array(0) : addParams;
    var params = "";
    
    // 추가 파라미터 생성
    for (var i=0; i<arrParams.length; i++) {
        params += "'" + arrParams[i] + "',";
    }
    
    // 마지막 페이지 수 체크
    if ((total % rowsPerPage) == 0) {
        totalPage = parseInt(total/rowsPerPage);
    } else {
        totalPage = parseInt(total/rowsPerPage) + 1;
    }
    
    // 첫번째 버튼 번호 체크
    if ((curPage % cntSize) == 0) {     // 10, 20, 30... (10의 배수 페이지일 경우)
        startPage = (parseInt(curPage/cntSize)-1)*cntSize+1; 
    } else {
        startPage = (parseInt(curPage/cntSize))*cntSize+1;
    }
    
    // 마지막 버튼 번호 체크
    endPage = parseInt(startPage)+cntSize-1;
    if (endPage >= totalPage) {
        endPage = totalPage;
    }
    
    if (parseInt(curPage) > 1) {
        sb.push("<a class=\"btn_first\" href=\"javascript:" + callFunc + "(" + params + "'1');\"></a>");
    } else {
        sb.push("<a class=\"btn_first\"></a>");
    }
    
    if (parseInt(curPage) > 1) {
        sb.push("<a class=\"btn_prev\" href=\"javascript:" + callFunc + "(" + params + "'" + (parseInt(curPage)-1) + "');\"></a>");
    } else {
        sb.push("<a class=\"btn_prev\"></a>");
    }
    
    sb.push("<ul>");
    if (total == 0) {
        sb.push("<li class=\"on\"><a href=\"#\">1</a></li>");
    } else {
        for (var i=startPage; i<=endPage; i++) {
            if (i == curPage) {
                sb.push("<li class=\"on\"><a href=\"#\">" + i + "</a></li>");
            } else {
                sb.push("<li><a href=\"javascript:" + callFunc + "(" + params + "'" + i + "');\">" + i + "</a></li>");
            }
        }
    }
    sb.push("</ul>");
    
    if (totalPage > parseInt(curPage)) {
        sb.push("<a class=\"btn_next\" href=\"javascript:" + callFunc + "(" + params + "'" + (parseInt(curPage)+1) + "');\"></a>");
    } else {
        sb.push("<a class=\"btn_next\"></a>");
    }
    
    if (totalPage > parseInt(curPage)) {
        sb.push("<a class=\"btn_last\" href=\"javascript:" + callFunc + "(" + params + "'" + totalPage + "');\"></a>");
    } else {
        sb.push("<a class=\"btn_last\"></a>");
    }
    
    jQuery("#" + htmlObj).children().remove();
    jQuery("#" + htmlObj).append(sb.join(""));
}

/**
 * Paging 서버 처리용 
 * @param nextPageNum : 다음페이지
 * @param url : url
 */
function gfn_goToPage(nextPageNum, url) {
    $("input[name='pageIndex']").val(nextPageNum);
    $('#frm').attr('action', url).submit();
}

/**
 * 메시지 레이어 팝업 + ClickEvent
 * 
 * @param p_title 팝업 타이틀
 * @param p_content 팝업 내용
 * @param p_fnNm 팝업 로드 후 호출할 함수명
 */
function gfn_showDialog(p_content, p_title, p_fnNm) {
    var title   = (p_title == "" || p_title == null) ? "Notice" : p_title;
    var content = (p_content == "" || p_content == null) ? "" : p_content;
    var fnNm    = (p_fnNm == "" || p_fnNm == null) ? "" : p_fnNm;
    
    $("#popup-title").html(title);
    $("#popup-content").html(content);
    $("#simplePopup").click();
    
    if(fnNm!=""){
        $("#popup-btn").click(function(){
            var fn = window[p_fnNm];
            fn();
        });
    }
}

/**
 * 테이블 로딩 이미지 추가
 * 
 * @param tableId
 */
function gfn_createLoadingImage(tableId) {
    var html = "";
        html += "<div class='loadme'>";
        html += "   <div class='loadme-fadingCircle'>";
        html += "       <div class='loadme-fadingCircle-child1 loadme-fadingCircle-child'></div>";
        html += "       <div class='loadme-fadingCircle-child2 loadme-fadingCircle-child'></div>";
        html += "       <div class='loadme-fadingCircle-child3 loadme-fadingCircle-child'></div>";
        html += "       <div class='loadme-fadingCircle-child4 loadme-fadingCircle-child'></div>";
        html += "       <div class='loadme-fadingCircle-child5 loadme-fadingCircle-child'></div>";
        html += "       <div class='loadme-fadingCircle-child6 loadme-fadingCircle-child'></div>";
        html += "       <div class='loadme-fadingCircle-child7 loadme-fadingCircle-child'></div>";
        html += "       <div class='loadme-fadingCircle-child8 loadme-fadingCircle-child'></div>";
        html += "       <div class='loadme-fadingCircle-child9 loadme-fadingCircle-child'></div>";
        html += "       <div class='loadme-fadingCircle-child10 loadme-fadingCircle-child'></div>";
        html += "       <div class='loadme-fadingCircle-child11 loadme-fadingCircle-child'></div>";
        html += "       <div class='loadme-fadingCircle-child12 loadme-fadingCircle-child'></div>";
        html += "   </div>";
        html += "   <div class='loadme-mask'></div>";
        html += "</div>";
    
    jQuery("#" + tableId + "Div").append(html);
}

/**
 * 테이블 로딩 이미지 삭제
 * 
 * @param tableId
 */
function gfn_removeLoadingImage(tableId) {
    jQuery("#" + tableId + "Div > .loadme").remove();
}

/**
 * 날짜 정보 리턴
 * 
 * @param yyyy 년도
 * @param mm 월
 * @param dd 일
 */
function gfn_getAgoDate(yyyy, mm, dd) {
    var today = new Date();
    var year = today.getFullYear();
    var month = today.getMonth();
    var day = today.getDate();
    
    var resultDate = new Date(year+yyyy, month+mm, day+dd);
    
    year = resultDate.getFullYear();
    month = resultDate.getMonth() + 1;
    day = resultDate.getDate();
    
    month = (month < 10) ? "0" + month : month;
    day = (day < 10) ? "0" + day : day;
    
    return year + "" + month + "" + day;
}

/**
 * 월 정보 리턴
 * 
 * @param yyyy 년도
 * @param mm 월
 */
function gfn_getAgoMonth(yyyy, mm) {
    var today = new Date();
    var year = today.getFullYear();
    var month = today.getMonth();
    var day = 1;
    
    var resultDate = new Date(year+yyyy, month+mm, day);
    
    year = resultDate.getFullYear();
    month = resultDate.getMonth() + 1;
    
    month = (month < 10) ? "0" + month : month;
    
    return year + "-" + month;
}

/**
 * 날짜 형식 체크
 * 
 * @param param1 날짜(YYYYMMDD 형식)
 */
function gfn_isValidDate(param) {
    try {
        var ymd = param.replace(/-/g,"");
        
        // 자리수가 맞지 않을 때
        if (isNaN(ymd) || ymd.length != 8) {
            return false;
        }
        
        var year = parseInt(ymd.substring(0,4),10);
        var month = parseInt(ymd.substring(4,6),10);
        var day = parseInt(ymd.substring(6,8),10);
        
        var dd = day / 0;
        
        if (month < 1 || month > 12) {
            return false;
        }
        
        var maxDaysInMonth = [31,28,31,30,31,30,31,31,30,31,30,31];
        var maxDay = maxDaysInMonth[month-1];
        
        // 윤년 체크
        if (month == 2 && (year % 4 == 0 && year % 100 != 0 && year % 400 == 0)) {
            maxDay = 29;
        }
        
        if (day <= 0 || day > maxDay) {
            return false;
        }
        
        return true;
    } catch (e) {
        return false;
    }
}

/**
 * 두 날짜 사이 일수 계산
 * 
 * @param param1 시작일
 * @param param2 종료일
 * @returns 종료일-시작일 or -1(오류)
 */
function gfn_getDateDiff(param1, param2) {
    // 유효성 체크
    if (!gfn_isValidDate(param1) || !gfn_isValidDate(param2)) {
        return -1;
    }
    
    var from_year = parseInt(param1.substring(0,4),10);
    var from_month = parseInt(param1.substring(4,6),10)-1;
    var from_day = parseInt(param1.substring(6,8),10);
    var to_year = parseInt(param2.substring(0,4),10);
    var to_month = parseInt(param2.substring(4,6),10)-1;
    var to_day = parseInt(param2.substring(6,8),10);
    
    var from_ymd = new Date(from_year, from_month, from_day);
    var to_ymd = new Date(to_year, to_month, to_day);
    var diff = Math.ceil((to_ymd - from_ymd) / (3600 * 24 * 1000));
    
    if (diff < 0) {
        return -1;
    } else {
        return diff;
    }
}

/**
 * 두 날짜 사이 월수 계산
 * 
 * @param param1 시작월 (yyyy-mm)
 * @param param2 종료월 (yyyy-mm)
 * @returns 종료월-시작월 or -1(오류)
 */
function gfn_getMonthDiff(param1, param2) {
    var from_year = parseInt(param1.substring(0,4),10);
    var from_month = parseInt(param1.substring(5,7),10);
    var to_year = parseInt(param2.substring(0,4),10);
    var to_month = parseInt(param2.substring(5,7),10);
    
    var diff = ((to_year - from_year) * 12) - from_month + to_month;
    
    if (diff < 0) {
        return -1;
    } else {
        return diff;
    }
}

/**
 * 3자리마다 콤마(,) 찍기
 * @param str
 * @returns {String}
 */
function gfn_addComma(str) {
    str = String(str);
    return str.replace(/(\d)(?=(?:\d{3})+(?!\d))/g, '$1,');
}

/**
 * Statements
 */
function gfn_getDatePatten(jDateStr, jGugn){
    var jOrgStr = jDateStr.trim();
    var retVal  = "";
    
    if(jOrgStr.length==8){
        retVal += jOrgStr.substring(0,4) + jGugn;
        retVal += jOrgStr.substring(4,6) + jGugn;
        retVal += jOrgStr.substring(6,8);
    }else{
        retVal = jDateStr;
    }    
    return retVal;
}

/**
 * 패스워드 정규화
 * if(pw.search(/(!|#)/) != -1){
        alert("! 과 # 은 제외하고 사용해주세요.");
        return false;
    }
 */
function chkPwd(str) {
    var pw = str;
    var num = pw.search(/[0-9]/g);
    var eng = pw.search(/[a-zA-Z]/ig);
    var spe = pw.search(/[`~!@@#$%^&*|₩₩₩'₩";:₩/?]/gi);
    var userId = '${loginUserInfo.user_id}';

    if(pw.length < 8 || pw.length > 20){
        alert("<spring:message code='msg.input.password.length'/>");
         return false;
    }

    if(pw.search(/₩s/) != -1){
        alert("<spring:message code='msg.input.password.space'/>");
        return false;
    } if(num < 0 || eng < 0 || spe < 0 ){
        alert("<spring:message code='msg.input.password.alpha'/>");
        return false;
    }
    
    // 동일한 문자/숫자 4이상, 연속된 문자
    if(/(\w)\1\1\1/.test(pw) || isContinuedValue(pw))
    {
        alert("<spring:message code='msg.input.password.conti'/>");
        return false;
    }
     
    // 아이디 포함 여부
    if(pw.search(userId)>-1)
    {
        alert("<spring:message code='msg.input.password.include'/>");
        return false;
    }
    
    return true;
}

// 패스워드 정규화. isContinuedValue 정의하는 함수
function isContinuedValue(value) {
    console.log("value = " + value);
    var intCnt1 = 0;
    var intCnt2 = 0;
    var temp0 = "";
    var temp1 = "";
    var temp2 = "";
    var temp3 = "";
    
    for (var i = 0; i < value.length-3; i++) {
        temp0 = value.charAt(i);
        temp1 = value.charAt(i + 1);
        temp2 = value.charAt(i + 2);
        temp3 = value.charAt(i + 3);
    
        if (temp0.charCodeAt(0) - temp1.charCodeAt(0) == 1
         && temp1.charCodeAt(0) - temp2.charCodeAt(0) == 1
         && temp2.charCodeAt(0) - temp3.charCodeAt(0) == 1) {
     intCnt1 = intCnt1 + 1;
        }
    
        if (temp0.charCodeAt(0) - temp1.charCodeAt(0) == -1
         && temp1.charCodeAt(0) - temp2.charCodeAt(0) == -1
         && temp2.charCodeAt(0) - temp3.charCodeAt(0) == -1) {
     intCnt2 = intCnt2 + 1;
        }
    }
    
    return (intCnt1 > 0 || intCnt2 > 0);
}