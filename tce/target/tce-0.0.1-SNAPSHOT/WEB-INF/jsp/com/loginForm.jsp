<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/jsp/include/declare.jspf" %>
<%-- 
    Class Name  :  
    Description : login page 
    author      : LMC
    since       : 2019.04.23 

    Modification Information
           수정일           수정자               수정내용 
    -------   --------   --------------------------- 
    2020/1/19  zhangpeiyun   appscan
--%>

<script type="text/javascript" src="<c:url value='/js/com/tms_common.js'/>"></script>

<script type="text/javaScript" language="javascript">
    $(function(){
        $("#userIdInput:text").keydown(function(e){
            if(e.keyCode == 13){
                fnLogin();
            }
        });
        
        $("#userPwInput:password").keydown(function(e){
            if(e.keyCode == 13){
                fnLogin();
            }
        });
        
        // 로그인 버튼 클릭 이벤트
        $("#btn_login").click(function() {
            fnLogin();
        });
        
        //초기패스워드 및 비밀번호 만료
        <c:choose>
            <c:when test="${checkDefault eq 'Y'}">
            alert('<spring:message code="msg.login.first" />');
                fnCallPwUpdatePopup();
            </c:when>
            <c:when test="${checkExpire eq 'Y'}">
            alert('<spring:message code="msg.login.pwd_expire" />');
                fnCallPwUpdatePopup();
            </c:when>
        </c:choose>
    });
    
    //암호 변경 팝업창 호출
    function fnCallPwUpdatePopup(){
        var pUserId = $('#userIdInput').val(); 
        var url = "/com/changeInitPwdForm.do?userId="+pUserId;
        var popObj = window.open(url, "pwUpdate", "width=660px, height=480px");
        try {
            popObj.focus();
        } catch (e) {}
    }
    
    // 로그인 로직
    function fnLogin(){
        
        if( $('#userIdInput').val() == '') {
            alert('<spring:message code="msg.input.id"/>');
            return;
        }
        if( $('#userPwInput').val() == '') {
            alert('<spring:message code="msg.input.password"/>');
            return;
        }
        
        var userId = btoa(unescape(encodeURIComponent($('#userIdInput').val())));
        var userPw = btoa(unescape(encodeURIComponent($('#userPwInput').val())));
        
        $('#userId').val(userId);
        $('#userPw').val(userPw);
        $('#locale').val($('input[name="login-lang"]:checked').val());
        
        $('#userPwInput').val('');

        $("#loginForm").attr('action', "/com/login.do");
        $("#loginForm").submit();
    } 
    
   //==========================================================================
   //appscan
     var InterValObj; //timer变量，控制时间
     var count = 60; //间隔函数，1秒执行
     var curCount;//当前剩余秒数
     
     /* sendMessage 方法 */
     function sendMessage() {
         curCount = count;
    //loginFrm.action = "<c:url value='/com/sendSMS.do' />";
    //loginFrm.submit();
            var phone = $("#phone").val();
           //var telephonechk = /^1(3|4|5|7|8)\d{9}$/; 
            if (phone != "") {
                var regex = /^1(3|4|5|7|8)\d{9}$/;
                var telephonechk = regex.test(phone);
                if(telephonechk){
                }else{
                    alert("请输入正确的手机号！");
                }
                /* var isok = telephonechk.test(phone);
                alert(isok);
                if(!isok){
                    return;
                    } */
            $("#btn_code").attr("disabled", "true");
            $("#btn_code").val(curCount + "秒后可重新发送");
            InterValObj = window.setInterval(SetRemainTime, 1000); //启动计时器，1秒执行一次请求后台发送验证码 TODO
            //请求后台
            console.log(phone);
            $.ajax({
               url : "/com/sendSMS.do",  //发送请求 
               type : "post",
               data : {
                    "strUserHptn" : phone
                },
                dataType:"json",
                success : function(result) {
                sms = result;
                }
            });
            }else{
                alert("请先输入手机号码！");
            }
     }
     //timer处理函数
     function SetRemainTime() {
         if (curCount == 0) {
             window.clearInterval(InterValObj);//停止计时器
             $("#btn_code").removeAttr("disabled");//启用按钮
             $("#btn_code").val("重新发送验证码");
         } else {
             curCount--;
             $("#btn_code").val(curCount + "秒后可重新发送");
         }
     }

   //appscan
   //================================================================================================

</script>
 

<!-- appscan -->
<style type="text/css">
#login {
    width: 450px;
    height: 100px;
    margin: 126px auto;
}

#btn {
    margin-left: 128px;
    margin-top: -44px;
    width: 70px;
    height: 18px;
    font-size: 11px;
}
</style>
<!-- appscan -->
<!-- appscan -->

<div class="login_wrap">
    <div class="inner">
        <div class="login">
            <h1 class="logo"><span>HYUNDAI MOTOR GROUP</span><strong>工单事项管理系统</strong></h1>
            <p class="greeting"></p>
            <form id="loginForm" name="loginForm" method="post">
                <input type="hidden" name="userId"  id="userId"  value="">
                <input type="hidden" name="userPw"  id="userPw"  value="">
                <input type="hidden" name="locale"  id="locale"  value="">
                <fieldset>
                    <legend>Login</legend>
                    <ul class="login_input">
                        <li class="id">
                            <label for="id">id</label>
                            <!--  <input type="text" id="userIdInput" class="input_box" placeholder="ID" value="admin" autofocus /> -->
                             <input type="text" id="userIdInput" class="input_box" placeholder="ID" value="${rParam.userId}" autofocus />
                        </li>
                        <li class="pwd">
                            <label for="pwd">pwd</label>
                            <input type="password"  autocomplete="off" id="userPwInput" placeholder="Password" value="">
                        </li>
                        <!-- ================================================================================================ -->
                        <!-- appscan -->
                        <li class="">
                            <label for="pwd">phone</label>
                            <input style="margin-top: 15px" type="text" id="phone" placeholder="请输入您的手机号" value="">
                        </li>
                        <li class="">
                            <label for="pwd">code</label>
                            <input style="margin-top:15px" type="text" id="code" placeholder="验证码" value="">
                        </li>
                        <!-- appscan -->
                        <!-- ================================================================================================ -->
                    </ul>
                    
                   <ul>
                    <li class="">  
                    <label for="btn_login"></label>
                    <a href="#" class="btn_login" id="btn_login">Login</a>
                    </li>
                    
                    <li class="">
                    <label for="btn_code"></label>
                  <!--   <a href="#" class="btn_code" style="margin-top: 15px" id="btn_code" onClick="sendMessage()">发送验证码</a> -->
                    <input class="btn_code" style="margin-top: 15px" type="button" id="btn_code" placeholder="发送验证码" onClick="sendMessage()" value="发送验证码">
                    </li>
                    
                   </ul>
                </fieldset>
                <ul class="login_lang">
                <li>
                    <span class="radio">
                        <input type="radio" id="login-lang-zh" name="login-lang" value="ZH" checked>
                        <label for="login-lang-zh">Chinese</label> 
                    </span>
                </li>
                <li>
                    <span class="radio">
                        <input type="radio" id="login-lang-ko" name="login-lang" value="KR">
                        <label for="login-lang-ko">Korean</label>
                    </span>
                </li>
                </ul>
            </form>
        </div>
    </div> 
</div>
