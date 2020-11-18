/*
 * written by bkLove(최병국)
 * 
   Fucntion List
  -------- -------------------------------- -------- ------------------------------------------------------------------------------
           Fucntion Name                     사용유무             Description
  -------- -------------------------------- -------- ------------------------------------------------------------------------------
    001    StringBuffer                         Y       Array로 버퍼형태 생성
    002    append                               Y       append시 Array에 추가
    003    toString                             Y       toString시 Array정보 출력
    004    trim                                 Y       해당 문자열의 공백을 제거한다.
    005    fn_isNull                            Y       널인지 점검한다.
    006    fn_setAtt                            Y       속성값을 설정한다.
    007       fn_getChildNode                      Y       선택한 객체의 하위 객체중 sName에 해당하는 이름의 객체를 리턴한다.
  -------- -------------------------------- -------- ------------------------------------------------------------------------------
*/

/************************************************************************
 함수명 : StringBuffer
 설 명  : Array로 버퍼형태 생성
 사용법 : new StringBuffer()
 작성일 : 2010-07-23
 작성자 : 유비스티  bkLove(최병국)
 수정일    수정자    수정내용
 ------   ------    -------------------
************************************************************************/
var StringBuffer = function() 
{
    this.buffer = new Array();
}

/************************************************************************
 함수명 : append
 설 명  : append시 Array에 추가
 사용법 : sb.append("data")
 작성일 : 2010-07-23
 작성자 : 유비스티  bkLove(최병국)
 수정일    수정자    수정내용
 ------   ------    -------------------
************************************************************************/
StringBuffer.prototype.append = function(obj) 
{
    this.buffer.push(obj);
}

/************************************************************************
 함수명 : toString
 설 명  : toString시 Array정보 출력
 사용법 : sb.toString()
 작성일 : 2010-07-23
 작성자 : 유비스티  bkLove(최병국)
 수정일    수정자    수정내용
 ------   ------    -------------------
************************************************************************/
StringBuffer.prototype.toString = function()
{
    return this.buffer.join("");
}

/************************************************************************
 함수명 : trim
 설 명  : 해당 문자열의 공백을 제거한다.
 사용법 : str.trim()
 작성일 : 2010-07-23
 작성자 : 유비스티  bkLove(최병국)
 수정일    수정자     수정내용
 ------   ------    -------------------
************************************************************************/
String.prototype.trim = function() 
{
    return this.replace(/^\s+|\s+$/g, "");
}

/************************************************************************
 함수명 : fn_isNull
 설 명  : 널인지 점검한다.
 사용법 : fn_isNull( startDate )
 작성일 : 2010-07-23
 작성자 : 유비스티  bkLove(최병국)
 수정일    수정자    수정내용
 ------   ------    -------------------
************************************************************************/
function fn_isNull( asValue ) 
{
    if (asValue == null || asValue == undefined || asValue.toString().replace(/ /g,"") == "") 
    {
        return  true;
    }

    return false;
}

/************************************************************************
 함수명 : fn_setAtt
 설 명  : 속성값을 설정한다.
 사용법 : fn_setAtt( "startDate", "value", "" )
 작성일 : 2010-07-23
 작성자 : 유비스티  bkLove(최병국)
 수정일    수정자    수정내용
 ------   ------    -------------------
************************************************************************/
function fn_setAtt( name, att, value ) 
{
    if( fn_isNull(name) )    return;

    try 
    {
        var names = name.split(",");
        for( var i=0; i < name.split(",").length; i++ ) 
        {
            if( "true" == value || "false" == value ) 
            {
                eval( "document.getElementById('"+names[i]+"')." + att + "=" + value );
            } 
            else 
            {
                eval( "document.getElementById('"+names[i]+"')." + att + "= \"" + value + "\"" );
            }
        }
    } 
    catch(ex) 
    {
        alert(  '오류위치 = tms_common.fn_setAtt \n' +
                '오류내역 = [' + ex.description + ']' );
    }
}

/************************************************************************
함수명 : f_getChildNode
설 명  : 선택한 객체의 하위 객체중 sName에 해당하는 이름의 객체를 리턴한다.
사용법 : f_getChildNode( form, "startDate" )
작성일 : 2010-07-26
작성자 : 유비스티  bkLove(최병국)
수정일    수정자    수정내용
------   ------    -------------------
************************************************************************/
function fn_getChildNode( oTarget, sName )
{
    var f = oTarget.getElementsByTagName("*");
    var arrR  = null;
    sName = fn_isNull(sName) ? "" : sName;

     for( p in f )
     {
        if( f[p].name == sName || f[p].id == sName ) 
        {
             arrR = f[p];
             break;
        }
     }
    return arrR;
}
/************************************************************************
함수명 : fn_GetEvent
설 명  : 키코드 정보 획득
사용법 : fn_GetEvent(event)
작성일 : 2010-08-04
작성자 : 컨피테크 오국헌
수정일    수정자    수정내용
------   ------    -------------------
************************************************************************/
function fn_GetEvent(e)
{
    if(navigator.appName == 'Netscape')
    {
        keyVal = e.which;
    }
    else
    {
        keyVal = event.keyCode ;
    }
    return keyVal;
}
/************************************************************************
함수명 : fn_numbersonly
설 명  : 숫자만 입력되게 한다.
사용법 : onkeydown="fn_numbersonly(event);" style="ime-mode:disabled"
	     onkeypress는 아스키코드라서 키코드값이 틀리니 onkeydown으로 해야함
작성일 : 2010-08-04
작성자 : 컨피테크 오국헌
수정일    수정자    수정내용
------   ------    -------------------
************************************************************************/
function fn_numbersonly(evt)
{
    var myEvent = window.event ? window.event : evt;
    var isWindowEvent = window.event ? true : false;
    var keyVal = fn_GetEvent(evt);
    var result = false;
    if(myEvent.shiftKey)
    {
        result = false;
    }
    else
    {
    	if((keyVal >= 48 && keyVal <=57) || (keyVal >= 96 && keyVal <=105) || (keyVal == 8) || (keyVal == 9) || (keyVal == 17) || (keyVal == 37) || (keyVal == 39) || (keyVal == 46) )
        {
            result = true;
        }
        else
        {
            result = false;
        }
    }
    if(!result)
    {
        if(!isWindowEvent)
        {
            myEvent.preventDefault();
        }
        else
        {
            myEvent.returnValue=false;
        }
    }
}
/************************************************************************
함수명 : fn_stronly
설 명  : 문자&숫자&'@'만 입력되게 한다.
사용법 : onkeydown="fn_stronly(event);" style="ime-mode:disabled"
작성일 : 2010-08-04
작성자 : 컨피테크 오국헌
수정일    수정자    수정내용
------   ------    -------------------
************************************************************************/
function fn_stronly(evt)
{
    var myEvent = window.event ? window.event : evt;
    var isWindowEvent = window.event ? true : false;
    var keyVal = fn_GetEvent(evt);
    var result = false;
    if(myEvent.shiftKey)
    {
       if(keyVal > 63 && keyVal < 91)
       {
    	   result = true;
       }
       
    }
    else
    {
        if((keyVal > 32 && keyVal < 37 || keyVal > 57 && keyVal < 64) || (keyVal > 90 && keyVal < 97) || (keyVal > 185 &&  keyVal < 193) || (keyVal > 218 &&  keyVal < 223))
        {
            result = false;
        }
        else
        {
            result = true;
        }
    }
    if(!result)
    {
        if(!isWindowEvent)
        {
            myEvent.preventDefault();
        }
        else
        {
            myEvent.returnValue=false;
        }
    }
}
/************************************************************************
함수명 : fn_checkAll(obj1,obj2)
설 명  : 체크박스 전체 선택
사용법 : onClick="fn_checkAll(testFrom.chk_All, testForm.chkUserID)"
         isy/icc/ComCdProcess.jsp 참고
작성일 : 2010-08-09
작성자 : 컨피테크 오국헌
수정일   수정자    수정내용
------   ------    -------------------
************************************************************************/
function fn_checkAll(obj1, obj2)
{
    if (obj1 == null || obj2 == null) return;

    var isCheck = obj1.checked;

    if (obj2.length > 1)
    {
        for (var i=0; i<obj2.length; i++)
        {
           obj2[i].checked = isCheck;
        }
    }
    else
    {
        obj2.checked = isCheck;
    }
}
/************************************************************************
함수명 : fn_checkBytes(expression)
설 명  : 한글(2Byte), 영문,숫자(1Byte)로 계산된 문자열의 실제 바이트수를 리턴하는 함수
사용법 : fn_checkBytes(value);
         isy/usm/UserRegist.jsp 참고
작성일 : 2010-08-09
작성자 : 컨피테크 오국헌
수정일   수정자    수정내용
------   ------    -------------------
************************************************************************/
function fn_checkBytes(expression)
{
    var VLength=0;
    var temp = expression;
    var EscTemp;
    if(temp!="" & temp !=null)
    {
        for(var i=0;i<temp.length;i++)
        {
            if (temp.charAt(i)!=escape(temp.charAt(i)))
            {
                EscTemp=escape(temp.charAt(i));
                if (EscTemp.length>=6)
                {
                    VLength+=2;
                }
                else
                {
                    VLength+=1;
                }
            }
        }
    }
    else
    {
        VLength+=1;
    }
    return VLength;
}
/************************************************************************
함수명 : fn_check_browser()
설 명  : 브라우저 체크
사용법 : fn_check_browser();
작성일 : 2010-11-17
작성자 : 컨피테크 오국헌
수정일   수정자    수정내용
------   ------    -------------------
************************************************************************/
function fn_check_browser()
{
    var chkBrs = "";
    if (navigator.appName.indexOf("Microsoft") != -1)
    {
        chkBrs = "ie"; // Internet Explorer
        return chkBrs; 
    }
    else if(navigator.appName.indexOf("Netscape") != -1)
    {
        if(navigator.appVersion.indexOf("Chrome") != -1)
        {
            chkBrs = "cr"; // Chrome
            return chkBrs; 
        }
        else if(navigator.appVersion.indexOf("Safari") != -1)
        {
        	chkBrs = "sa"; // Safari
            return chkBrs;
        }
        else
        {
        	chkBrs = "ff"; // Mozilla Firefox
            return chkBrs;
        }
    }
    else
    {
    	return chkBrs; 
    }
}

function fn_load_menu(requestURI)
{
	//alert(requestURI);
	try{parent.topFrame.fn_load_topMenu(requestURI);}catch(e){}
	try{parent.leftFrame.fn_load_LeftMenu(requestURI);}catch(e){}
}

/************************************************************************
함수명 : fn_printPage()
설 명  : 영역 프린트
사용법 : fn_printPage(idName)
        idName: 프린트 할 영역의 ID
작성일 : 2011-12-12
작성자 : 컨피테크 이영탁
수정일   수정자    수정내용
------   ------    -------------------
************************************************************************/
function fn_printPage(idName){
    var printpop = window.open( '' ,'prt'
    ,'dependent,location=no,toolbar=yes, menubar=yes, resizable=yes,scrollbars=yes,width=830,height=500' );
    var tmp = printpop.document;
    tmp.write(document.getElementById(idName).outerHTML);
    tmp.close();
    printpop.print();
    printpop.close();
} 

/************************************************************************
함수명 : fn_check_dayCountName(fromObjName, toObjName)
		fn_check_dayCountObj(fromObj, toObj)
		fn_check_dayCount(from,to)
설 명  : 날짜 범위 6개월 이내인지 체크
사용법 : if(fn_check_dayCountName('dateFrom','dateTo'))
		if(fn_check_dayCountObj(fromObj.value,toObj.value))
		if(fn_check_dayCount('2010-01-01'or'20100101','2010-06-01'or'20100601'))
	     opm/flm/FailuresBackgroundList.jsp 참고
작성일 : 2010-12-15
작성자 : 유비스티 추풍령
수정일   수정자    수정내용
------   ------    -------------------
************************************************************************/

/**
 * 날자 범위 체크
 * @param fromObjName   from 날짜정보를 가진 element id
 * @param toObjName   	to 	 날짜정보를 가진 element id
 * @param month	   		범위 단위(월간격): ex) 6개월 범위시, month=6
 */
function fn_check_dayCountName(fromObjName, toObjName, month){
	return fn_check_dayCountObj(document.getElementById(fromObjName), document.getElementById(toObjName), month);
}

/**
* 날자 범위 체크
* @param fromObj   from 날짜정보를 가진 element object
* @param toObj     to 	날짜정보를 가진 element object
* @param month	   범위 단위(월간격): ex) 6개월 범위시, month=6
*/
function fn_check_dayCountObj(fromObj, toObj, month){
	return fn_check_dayCount(fromObj.value, toObj.value, month);
}

/**
* 날자 범위 체크
* @param from   	from 날짜정보 String
* @param to     	to 	 날짜정보 String
* @param month	   	범위 단위(월간격): ex) 6개월 범위시, month=6
*/
function fn_check_dayCount(from, to, month){
    var arrFrom     = from.split('-');
    var arrTo       = to.split('-');
    var fromDate;						// from 날짜 object
    var toDate;							// to   날짜 object
    var thisCount;						// 사용자 입력 날짜 범위 수치
    var compareCount;					// 사용자 입력 월간격 수치(thisCount 비교 값)

    /*
     * date format이 YYYY-MM-DD 일 경우,	'-'간격으로 배열로 분류하여 사용자 입력 날짜 간격 계산
     * date format이 YYYYMMDD 일 경우,	substring으로  사용자 입력 날짜 분류 후, 간격 계산
     * 위의 경우가 아닐 경우,				error 메시지 출력후, false 값 return
     */
    if(arrFrom.length == 3 && arrTo.length == 3){
        fromDate    = new Date(arrFrom[0],arrFrom[1],arrFrom[2]);
        toDate      = new Date(arrTo[0],arrTo[1],arrTo[2]);
        compareCount= toDate-new Date(arrTo[0],arrTo[1]-month,arrTo[2]);
    }else if(arrFrom.length == 1 && arrTo.length == 1){
        if(from.length == 8 && to.length == 8){
        	var yearFrom,monFrom,dayFrom;
        	var yearTo,monTo,dayTo;

        	yearFrom   = from.substring(0,4);
        	monFrom    = from.substring(4,6);
        	dayFrom    = from.substring(6); 
        	yearTo     = to.substring(0,4);
        	monTo      = to.substring(4,6);
        	dayTo      = to.substring(6); 

        	fromDate    = new Date(yearFrom,monFrom,dayFrom);
            toDate      = new Date(yearTo,monTo,dayTo);
            compareCount= toDate-new Date(yearTo,monTo-month,dayTo);
        }else{
        	alert("Error01: invalide date format");
            return false;
        }
    }else{
        alert("Error02: invalide date format");
        return false;
    }
    
    var thisCount   = toDate-fromDate;
    return Boolean(thisCount<compareCount);
}
function fn_check_param(param)
{

    var value  = document.getElementById(param).value;
    var frm = document.userRegForm;

    var    strPgmNm   = /^[\u4e00-\u9fa5]{1,6}$/;
    var schMdnPattern = /^([0-9]{0,11})$/i;
    var schIccidPattern = /^([0-9]{0,19})$/i;
    var schSeqPattern = /^([a-z]+[0-9]+[a-z0-9]*|[0-9]+[a-zA-Z]+[a-z0-9]*)$/i; 

    if(value==null || value==""){
        
        return true;    
    }
    //ID 체크 유무
    if(param == "schIccid"){

        if(!schIccidPattern.test(document.getElementById(param).value))
          {                
              return false;
          }
        }
    if(param == "schMdn"){

      if(!schMdnPattern.test(document.getElementById(param).value))
        {                
            return false;
        }
      }
    if(param == "schSeq"){

        if(!schSeqPattern.test(document.getElementById(param).value))
          {                
              return false;
          }
        }
    if(param == "strPgmNm" ||  param == "strPgmName"){

        if(document.getElementById(param).value.length > 6 || !schSeqPattern.test(document.getElementById(param).value))
          {                
              return false;
          }
        }
    return true; 
    }
