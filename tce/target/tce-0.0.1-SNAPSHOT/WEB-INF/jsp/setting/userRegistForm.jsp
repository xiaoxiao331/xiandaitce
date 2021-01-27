<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/WEB-INF/jsp/include/declare.jspf" %> 
<%-- 
    Class Name  :  
    Description : Main Page 
    author      : 
    since       : 2019.04.26 

    Modification Information
           수정일           수정자               수정내용 
    -------   --------   --------------------------- 
 
--%>

<script type="text/javaScript" language="javascript">

//appscan
function userIdInputCheck (){
    
    var userIdInput = $("#userIdInput").val();
    if(userIdInput != ""){
        var regex = /^[0-9a-zA-Z]*$/g;
        var result = regex.test(userIdInput);
        if(result){
            return;
        }else{
            alert("请设置正确的用户ID名称！");
        }
    }
  
}
//appscan

    $(function(){
        //검색조건 기간 날짜선택
        $("#startDate").datepicker({dateFormat: "yymmdd", showOn: "button", minDate:0,
            buttonImage: "../../img/calendar.gif",
            buttonImageOnly: true});
        $("#endDate").datepicker({dateFormat: "yymmdd", showOn: "button", minDate:0,
            buttonImage: "../../img/calendar.gif",
            buttonImageOnly: true});
        
        //등록 버튼
        $("#btnRegist").click(function() {
            //유저ID체크
            var user_id = $("#userIdInput").val();
            if(user_id == '') {
                alert("<spring:message code='msg.input.id'/>");
                return;
            }
            //유저명 체크
            var name = $("#userNm").val();
            if(name == '') {
                alert("<spring:message code='msg.input.user_name'/>");
                return;
            }
            
            //비밀번호 체크
            if(!chkPwd( $.trim($('#chkPw01').val()))){
                $('#chkPw01').val('');
                $('#chkPw02').val('');
                $('#chkPw01').focus();
                return false;
            }
            
            var use_yn = $('input[name=use_yn]:checked').val();
            $("#userUseYn").val(use_yn);
            $("#acctStartDate").val($("#startDate").val());
            $("#acctEndDate").val($("#endDate").val());
            
            var userId = btoa(unescape(encodeURIComponent($('#userIdInput').val())));
            $("#userId").val(userId);
                
            //최종 비밀번호 암호화
            var lastPw = $('#chkPw01').val();
            if(lastPw != '') {
                var userPw = btoa(unescape(encodeURIComponent(lastPw)));
                $("#userPw").val(userPw);
            }
            
            if(!confirm("<spring:message code='msg.confirm_save'/>")) return;
            $("#frm").attr('action',"/setting/userRegist.do" );
            $("#frm").submit();
        });
        
        //목록 이동
        $("#btnList").click(function() {
            $('#frm').attr('action',"/setting/userMain.do" );
            $('#frm').submit();
        });
    });
</script>

<form name="frm" id="frm" method="post">
    <!-- 검색조건 유지 Start -->
    <input type="hidden" name="pageIndex"     value="1" />
    <input type="hidden" name="searchVal01"   value=""  />
    <!-- 검색조건 유지 End -->
    
    <input type="hidden" name="userUseYn"     id="userUseYn"                />
    <input type="hidden" name="userId"        id="userId"                   />
    <input type="hidden" name="userPw"        id="userPw"                   />
    <input type="hidden" name="acctStartDate" id="acctStartDate" value=""   />
    <input type="hidden" name="acctEndDate"   id="acctEndDate"   value=""   />
    
    <div class="wrapper">
        <div class="page">
            <div class="ribbon">
                <div class="title"><spring:message code="menu.admin.user.mgmt"/></div>
            </div>
            <div class="page-content">
                <div class="container-fluid">
                    <div class="row">
                        <div class="table-wrap">
                            <table class="table tbl-typeA">
                                <colgroup>
                                    <col style="width: 160px">
                                    <col style="width: 450px">
                                    <col style="width: 140px">
                                    <col style="width: 450px">
                                </colgroup>
                                <tbody>
                                    <tr>
                                        <th><span class="require">*</span><spring:message code="lbl.id"/></th>
                                        <td><input type="text" class="form-control" name="userIdInput" id="userIdInput" value="" onblur="userIdInputCheck()"></td>
                                        <th><span class="require">*</span><spring:message code="lbl.user_name"/></th>
                                        <td><input type="text" class="form-control" name="userNm" id="userNm" value=""></td>
                                    </tr>
                                    <tr>
                                        <th><spring:message code="lbl.csmc_scn"/></th>
                                        <td>
                                            <select class="select" name="userGubun" id="userGubun" style="width:200px">
                                                <option value="H"><spring:message code="code.corp.hyundai"/></option>
                                                <option value="K"><spring:message code="code.corp.kia"/></option>
                                                <option value="G"><spring:message code="code.corp.genesis"/></option>
                                            </select>
                                        </td>
                                        <th><spring:message code="lbl.email"/></th>
                                        <td><input type="text" class="form-control" name="userEmlAddr" id="userEmlAddr" value=""></td>
                                    </tr>
                                    <tr>
                                        <th><span class="require">*</span><spring:message code="lbl.passwd"/></th>
                                        <td><input type="password" class="form-control" id="chkPw01" value="" placeholder="Password" value="">* 8 ~ 20자리 이내로 영문 대/소,숫자,특수문자를 혼용 4자 이상의 연속 또는 반복 문자 및 숫자를 사용하실 수 없습니다.
                                        </td>
                                        <th><span class="require">*</span><spring:message code="lbl.passwd_confirm"/></th>
                                        <td><input type="password" class="form-control" id="chkPw02" value="" placeholder="Password" value=""></td>
                                    </tr>
                                    <tr>
                                        <th><spring:message code="lbl.phone_num"/></th>
                                        <td><input type="text" class="form-control" name="userPhone" id="userPhone" value="" ></td>
                                        <th><spring:message code="lbl.mobile_num"/></th>
                                        <td><input type="text" class="form-control" name="userMobile" id="userMobile" value=""></td>
                                    </tr>
                                    <tr>
                                        <th><spring:message code="lbl.use_startdate"/></th>
                                        <td><input type="text" class="form-control calendar" id="startDate" readonly value="${nowDay}"/></td>
                                        <th><spring:message code="lbl.auth"/></th>
                                        <td><select name="authId" id="authId" class="select" style="width:200px">
                                                <c:if test="${not empty authList}">0
                                                    <c:forEach var="auth" items="${authList}">
                                                        <option value="${auth.authId}"><c:out value="${auth.authNm}" /></option>
                                                    </c:forEach>
                                                </c:if>
                                            </select>
                                        </td>
                                    </tr>
                                    <tr>
                                        <th><spring:message code="lbl.use_enddate"/></th>
                                        <td><input type="text" class="form-control calendar" id="endDate" readonly value="${dftAftDay}"/></td>
                                        <th><spring:message code="lbl.use_yn"/></th>
                                        <td>
                                            <span class="radio">
                                                <input type="radio" name="use_yn" id="use_yn01" value="Y" checked>
                                                <label for="use_yn01">Y</label>
                                            </span>
                                            <span class="radio">
                                                <input type="radio" name="use_yn" id="use_yn02" value="N">
                                                <label for="use_yn02">N</label>
                                            </span>
                                        </td>
                                    </tr>
                                </tbody>
                            </table>
                        </div>
                        <div class="btn-wrap tac">
                            <a href="#" class="btn btn-primary-line" id="btnRegist"><spring:message code="btn.save"/></a>
                            <a href="#" class="btn btn-primary" id="btnList"><spring:message code="btn.list"/></a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</form>
