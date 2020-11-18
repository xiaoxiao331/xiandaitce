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
        var code_nm = $("#codeNm").val();
        if(code_nm == '') {
            alert("<spring:message code='msg.input.codename'/>");
            return;
        }
        
        var msgId = $("#msgId").val();
        if(code_nm == '') {
            alert("<spring:message code='msg.input.msgid'/>");
            return;
        }
       
        var use_yn = $('input[name=use_yn]:checked').val();
        $("#useYn").val(use_yn);
        
        if(!confirm("<spring:message code='msg.confirm_update'/>")) return;
        
        $("#frm").attr('action',"/setting/codeGroupUpdate.do");
        $("#frm").submit();
    });
    
    //목록버튼
    $("#btnList").click(function() {
        $("#frm").attr('action',"/setting/codeMain.do");
        $("#frm").submit();
    });
});
</script>

<form id="frm" name="frm" method="post">
    <input type="hidden" name="useYn" id="useYn"/>
    <div class="wrapper">
        <div class="page">
            <div class="ribbon">
                <div class="title"><spring:message code="lbl.group.code.mgmt"/></div>
            </div>
            <div class="page-content">
                <div class="container-fluid">
                    <div class="row">
                        <div class="board-top">
                            <a href="#" class="btn btn-primary-line" id="btnUpdate"><spring:message code="btn.update"/></a>
                            <a href="#" class="btn btn-primary" id="btnList"><spring:message code="btn.list"/></a>
                        </div>
                        <div class="table-wrap">
                            <table class="table tbl-typeA">
                                <colgroup>
                                    <col style="width: 20px">
                                    <col style="width: 90%">
                                </colgroup>
                                <tbody>
                                    <tr>
                                        <th><span class="require">*</span><spring:message code="lbl.group.code"/></th>
                                        <td><input id="groupCodeId" class="form-control" name="groupCodeId" value="${rData.groupCodeId}" style="width:400px" readonly="readonly"></td>
                                    </tr>
                                    <tr>
                                        <th><span class="require">*</span><spring:message code="lbl.group.code_name"/></th>
                                        <td><input id="groupCodeNm" class="form-control" name="groupCodeNm" value="${rData.groupCodeNm}" style="width:400px"></td>
                                    </tr>
                                    <tr>
                                        <th><span class="require">*</span><spring:message code="lbl.lang.msg_id" /></th>
                                        <td><input id="msgId" class="form-control" name="msgId" value="${rData.msgId}" style="width:400px"></td>
                                    </tr>
                                    <tr>
                                        <th><spring:message code="lbl.use_yn"/></th>
                                        <td>
                                            <input type="radio" name="use_yn" value="Y" <c:if test="${rData.useYn eq 'Y'}">checked</c:if>>&nbsp;Y&nbsp;&nbsp;
                                            <input type="radio" name="use_yn" value="N" <c:if test="${rData.useYn eq 'N'}">checked</c:if>>&nbsp;N
                                        </td>
                                    </tr>
                                    <tr>
                                        <th><spring:message code="lbl.desc"/></th>
                                        <td><textarea id="description" class="form-control" name="description" style="width:400px;height:100px;" >${rData.description}</textarea></td>
                                    </tr>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</form>