<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
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
$(function(){
    //수정 버튼
    $("#btnUpdate").click(function() {
        var authNm = $("#authNm").val();
        if(authNm == '') {
            alert("<spring:message code='msg.check.auth.name'/>");
            return;
        }
       
        var use_yn = $('input[name=use_yn]:checked').val();
        $("#authUseYn").val(use_yn);
        
        if(!confirm("<spring:message code='msg.confirm_update'/>")) return;
        
        $("#frm").attr('action',"/setting/authUpdate.do");
        $("#frm").submit();
    });
    
    //목록버튼
    $("#btnList").click(function() {
        $("#frm").attr('action',"/setting/authMain.do");
        $("#frm").submit();
    });
});
</script>

<form id="frm" name="frm" method="post">
    <!-- 검색조건 유지 Start -->
    <input type="hidden" name="pageIndex"     value="${rParam.pageIndex}"   />
    <input type="hidden" name="searchVal01"   value="${rParam.searchVal01}" />
    <!-- 검색조건 유지 End -->
    <input type="hidden" name="authUseYn"     id="authUseYn" />
    
    <div class="wrapper">
        <div class="page">
            <div class="ribbon">
                <div class="title"><spring:message code="menu.admin.auth.mgmt"/></div>
            </div>
            <div class="page-content">
                <div class="container-fluid">
                    <div class="row">
                        <div class="table-wrap">
                            <table class="table tbl-typeA">
                                <colgroup>
                                    <col style="width: 20px">
                                    <col style="width: 90%">
                                </colgroup>
                                <tbody>
                                    <tr>
                                        <th><span class="require">*</span><spring:message code="lbl.auth.group_id"/></th>
                                        <td><input id="authId" class="form-control" name="authId" value="${rData.authId}" style="width:400px" readonly="readonly"></td>
                                    </tr>
                                    <tr>
                                        <th><span class="require">*</span><spring:message code="lbl.auth.group_name"/></th>
                                        <td><input id="authNm" class="form-control" name="authNm" value="${rData.authNm}" style="width:400px"></td>
                                    </tr>
                                    <tr>
                                        <th><spring:message code="lbl.auth.use_yn"/></th>
                                        <td>
                                            <span class="radio">
                                                <input type="radio" name="use_yn" id="use_yn01" value="Y" <c:if test="${rData.authUseYn eq 'Y'}">checked</c:if>>
                                                <label for="use_yn01">Y</label>
                                            </span>
                                            <span class="radio">
                                                <input type="radio" name="use_yn" id="use_yn02" value="N" <c:if test="${rData.authUseYn eq 'N'}">checked</c:if>>
                                                <label for="use_yn02">N</label>
                                            </span>
                                        </td>
                                    </tr>
                                </tbody>
                            </table>
                        </div>
                        <div class="btn-wrap tac">
                            <a href="#" class="btn btn-primary-line" id="btnUpdate"><spring:message code="btn.update"/></a>
                            <a href="#" class="btn btn-primary" id="btnList"><spring:message code="btn.list"/></a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</form>