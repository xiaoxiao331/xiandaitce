<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/include/declare.jspf" %>
<script>
$(function(){
    $("#chgUpdateButton").click(function() {
        var userIdInput = $("#chgUserIdInput").val();
        if(userIdInput == '') {
            alert("<spring:message code='msg.input.userid' />");
            return;
        }
        var searchUserPw = $("#chgSearchUserPw").val();
        if(searchUserPw == '') {
            alert("<spring:message code='msg.input.currentpwd' />");
            return;
        }
        var newPwInput = $("#chgNewPwInput").val();
        if(newPwInput == '') {
            alert("<spring:message code='msg.input.newpwd' />");
            return;
        }
        var confirmPwInput = $("#chgConfirmPwInput").val();
        if(confirmPwInput == '') {
            alert("<spring:message code='msg.input.newpwd_confirm' />");
            return;
        }
        if(newPwInput != confirmPwInput) {
            alert("<spring:message code='msg.input.samepwd' />");
            return;
        }
        
        //비밀번호 체크
        if(!chkPwd( $.trim(newPwInput))){
            $('#chgNewPwInput').val('');
            $('#chgConfirmPwInput').val('');
            $('#chgNewPwInput').focus();
            return false;
        }
        
        var userId = btoa(unescape(encodeURIComponent(userIdInput)));
        var userNewPw = btoa(unescape(encodeURIComponent(newPwInput)));
        var userOldPw = btoa(unescape(encodeURIComponent(searchUserPw)));
        
        $("#userId").val(userId);
        $("#userPw").val(userNewPw);
        $("#oldPasswd").val(userOldPw);
        
        $("#frm").attr('action', "/com/changeInitPwd.do");
        $("#frm").submit();
    });
    
    //우측 상단 닫기 버튼
    $("#chgUpdateClose01").click(function() {
        self.close();
    });
    
    //하단 닫기 버튼
    $("#chgUpdateClose02").click(function() {
        self.close();
    });
});
</script>

<c:if test="${!empty msg}">
    <script type="text/javaScript" language="javascript">
        alert('${msg}');
        opener.location.href = '<%=request.getContextPath()%>/tce.do';
        window.close();
    </script>
</c:if>
    
<form id="frm" name="frm" method="post">
    <input type="hidden" name="userId"    id="userId"    value="">
    <input type="hidden" name="userPw"    id="userPw"    value="">
    <input type="hidden" name="oldPasswd" id="oldPasswd" value="">
</form>

<div class="modal-content">
    <div class="modal-header">
        <h5 class="modal-title">Update Password</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close" id="chgUpdateClose01">
            <span class="sr-only" aria-hidden="true"><spring:message code="btn.close" /></span>
        </button>
    </div>
    <div class="modal-body">
        <div class="login">
            <form>
                <fieldset>
                    <legend>Login</legend>
                    <ul class="login_input">
                        <li>
                            <label for="id">id</label>
                            <input disabled type="text" name="chgUserIdInput" id="chgUserIdInput" title="ID" class="input_box" value="${rParam.userId}" />
                        </li>
                        <li>
                            <label class="pwd">pwd</label>
                            <input placeholder="Current Password" type="password" name="chgSearchUserPw" id="chgSearchUserPw" title="Old PW" class="input_box" autofocus/>
                        </li>
                        <li>
                            <label class="pwd">pwd</label>
                            <input placeholder="New Password" type="password" name="chgNewPwInput" id="chgNewPwInput" title="New PW" class="input_box"/>
                        </li>
                        <li>
                            <label class="pwd">pwd</label>
                            <input placeholder="Confirm Password" type="password" name="chgConfirmPwInput" id="chgConfirmPwInput" title="Confirm PW" class="input_box"/>
                        </li>
                    </ul>
                    <div class="board-bottom">
                        <button class="btn btn-primary" type="button" id="chgUpdateButton"><spring:message code="btn.update" /></button>
                        <button class="btn btn-primary-line" type="button" data-dismiss="modal" id="chgUpdateClose02"><spring:message code="btn.close" /></button>  
                    </div>
                </fieldset>
            </form>
        </div>
    </div>
</div>
