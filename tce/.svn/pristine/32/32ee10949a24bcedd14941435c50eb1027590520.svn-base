<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/WEB-INF/jsp/include/declare.jspf" %> 

<%-- 
    Class Name  :  
    Description : 사용자 상세 페이지
    author      : 
    since       : 2019.05.30 

    Modification Information
           수정일           수정자               수정내용 
    -------   --------   --------------------------- 
 
--%>
<script type="text/javaScript" language="javascript">
    $(function(){
        //검색조건 기간 날짜선택
        $("#startDate").datepicker({dateFormat: "yymmdd", showOn: "button",
            buttonImage: "../../img/calendar.gif",
            buttonImageOnly: true});
        $("#endDate").datepicker({dateFormat: "yymmdd", showOn: "button",
            buttonImage: "../../img/calendar.gif",
            buttonImageOnly: true});
        
        //날짜 포맷 선언
        $("#startDate").datepicker({dateFormat: "yymmdd"});
        $("#endDate").datepicker({dateFormat: "yymmdd"});
        
        //삭제 버튼
        $("#btnDelete").click(function() {
            if(!confirm("<spring:message code='msg.confirm_delete'/>")) return;
            $("#frm").attr('action',"/setting/userDelete.do" );
            $("#frm").submit();
        });
        
        //초기화 버튼
        $("#btnInit").click(function() {
            if(!confirm("<spring:message code='msg.confirm_init'/>")) return;
            $("#frm").attr('action',"/setting/userInit.do" );
            $("#frm").submit();
        });
        
        //수정 버튼
        $("#btnUpdate").click(function() {
            //유저명 체크
            var name = $("#userNm").val();
            if(name == '') {
                alert("<spring:message code='msg.input.user_name'/>");
                return;
            }
            
            var use_yn = $('input[name=use_yn]:checked').val();
            $("#userUseYn").val(use_yn);
            $("#acctStartDate").val($("#startDate").val());
            $("#acctEndDate").val($("#endDate").val());
            
            var lastPw = $('#chkPw01').val();
            if(lastPw != '') {
                var userPw = btoa(unescape(encodeURIComponent(lastPw)));
                $("#userPw").val(userPw);
            }
            
            if(!confirm("<spring:message code='msg.confirm_update'/>")) return;
            $("#frm").attr('action',"/setting/userUpdate.do" );
            $("#frm").submit();
        });
        
        //목록 버튼
        $("#btnList").click(function() {
            goUserList();
        });
    });
    
    //목록 이동
    function goUserList(){
        $('#frm').attr('action',"/setting/userMain.do" );
        $('#frm').submit();
    }
</script>

<form name="frm" id="frm" method="post" enctype="multipart/form-data">
    <!-- 검색조건 유지 Start -->
    <input type="hidden" name="pageIndex"     value="${rParam.pageIndex}"   />
    <input type="hidden" name="searchVal01"   value="${rParam.searchVal01}" />
    <input type="hidden" name="searchVal02"   value="${rParam.searchVal02}" />
    <input type="hidden" name="searchVal03"   value="${rParam.searchVal03}" />
    <input type="hidden" name="searchVal04"   value="${rParam.searchVal04}" />
    <input type="hidden" name="searchVal05"   value="${rParam.searchVal05}" />
    <!-- 검색조건 유지 End -->
    <input type="hidden" name="userUseYn"     id="userUseYn"                />
    <input type="hidden" name="userLockYn"    value="N"                     />
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
                                    <col style="width: 20px">
                                    <col style="width: 40%">
                                    <col style="width: 20px">
                                    <col style="width: 40%">
                                </colgroup>
                                <tbody>
                                    <tr>
                                        <th><spring:message code="lbl.id"/></th>
                                        <td><input type="text" class="form-control" name="userId" id="userId" value="${rData.userId}" readonly="readonly"></td>
                                        <th><span class="require">*</span><spring:message code="lbl.user_name"/></th>
                                        <td><input type="text" class="form-control" name="userNm" id="userNm" value="${rData.userNm}"></td>
                                    </tr>
                                    <tr>
                                        <th><spring:message code="lbl.csmc_scn"/></th>
                                        <td>
                                            <select class="select" id="userGubun" name="userGubun" style="width:200px">
                                                <option value="H" <c:if test="${rData.userGubun eq 'H'}">selected</c:if>><spring:message code="code.corp.hyundai"/></option>
                                                <option value="K" <c:if test="${rData.userGubun eq 'K'}">selected</c:if>><spring:message code="code.corp.kia"/></option>    
                                                <option value="G" <c:if test="${rData.userGubun eq 'G'}">selected</c:if>><spring:message code="code.corp.genesis"/></option>
                                            </select>
                                        </td>
                                        <th><spring:message code="lbl.email"/></th>
                                        <td><input type="text" class="form-control" name="userEmlAddr" id="userEmlAddr" value="${rData.userEmlAddr}"></td>
                                    </tr>
                                    <tr>
                                        <th><spring:message code="lbl.passwd"/></th>
                                        <td><input type="password" class="form-control" id="chkPw01" value="" placeholder="Password" value=""></td>
                                        <th><spring:message code="lbl.passwd_confirm"/></th>
                                        <td><input type="password" class="form-control" id="chkPw02" value="" placeholder="Password" value=""></td>
                                    </tr>
                                    <tr>
                                        <th><spring:message code="lbl.phone_num"/></th>
                                        <td><input type="text" class="form-control" name="userPhone" id="userPhone" value="${rData.userPhone}" ></td>
                                        <th><spring:message code="lbl.mobile_num"/></th>
                                        <td><input type="text" class="form-control" name="userMobile" id="userMobile" value="${rData.userMobile}"></td>
                                    </tr>
                                    <tr>
                                        <th><spring:message code="lbl.use_startdate"/></th>
                                        <td><input type="text" class="form-control calendar" id="startDate" readonly value="${rData.acctStartDate}"/></td>
                                        <th><spring:message code="lbl.use_yn"/></th>
                                        <td>
                                            <span class="radio">
                                                <input type="radio" name="use_yn" id="use_yn01" value="Y" <c:if test="${rData.userUseYn eq 'Y'}">checked</c:if>>
                                                <label for="use_yn01">Y</label>
                                            </span>
                                            <span class="radio">
                                                <input type="radio" name="use_yn" id="use_yn02" value="N" <c:if test="${rData.userUseYn eq 'N'}">checked</c:if>>
                                                <label for="use_yn02">N</label>
                                            </span>
                                        </td>
                                    </tr>
                                    <tr>
                                        <th><spring:message code="lbl.use_enddate"/></th>
                                        <td><input type="text" class="form-control calendar" id="endDate" readonly value="${rData.acctEndDate}"/></td>
                                        <th><spring:message code="lbl.auth"/></th>
                                        <td><select name="authId" id="authId" class="select" style="width:200px">
                                                <c:if test="${not empty authList}">
                                                    <c:forEach var="auth" items="${authList}">
                                                        <option value="${auth.authId}" <c:if test="${auth.authId eq rData.authId}">selected</c:if>><c:out value="${auth.authNm}" /></option>
                                                    </c:forEach>
                                                </c:if>
                                            </select>
                                        </td>
                                    </tr>
                                </tbody>
                            </table>
                        </div>
                        <div class="btn-wrap tac">
                            <c:if test="${Delete eq 'YES'}">
                                <a href="#" class="btn btn-primary-line" id="btnDelete"><spring:message code="btn.delete"/></a>
                            </c:if>
                            <a href="#" class="btn btn-primary-line" id="btnInit"><spring:message code="btn.reset"/></a>
                            <a href="#" class="btn btn-primary-line" id="btnUpdate"><spring:message code="btn.update"/></a>
                            <a href="#" class="btn btn-primary" id="btnList"><spring:message code="btn.list"/></a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</form>