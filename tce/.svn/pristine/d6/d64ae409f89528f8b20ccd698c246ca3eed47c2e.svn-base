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
  //등록 버튼
    $("#btnRegist").click(function() {
        var code_id = $("#codeId").val();
        if(code_id == '') {
            alert("<spring:message code='msg.input.codeid'/>");
            return;
        }
        
        var code_nm = $("#codeNm").val();
        if(code_nm == '') {
            alert("<spring:message code='msg.input.codename'/>");
            return;
        }
        
        var msgId = $("#msgId").val();
        if(msgId == '') {
            alert("<spring:message code='msg.input.msgid'/>");
            return;
        }
       
        var use_yn = $('input[name=use_yn]:checked').val();
        $("#useYn").val(use_yn);
        
        if(!confirm("<spring:message code='msg.confirm_save'/>")) return;
        
        $("#frm").attr('action',"/setting/codeDetailRegist.do");
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
    <!-- 상세등록시 리스트 유지 -->
    <input type="hidden" name="searchVal05" id="searchVal05"/>
    
    <div class="wrapper">
        <div class="page">
            <div class="ribbon">
                <div class="title"><spring:message code="lbl.detailcode.mgmt"/></div>
            </div>
            <div class="page-content">
                <div class="container-fluid">
                    <div class="row">
                        <div class="board-top">
                            <a href="#" class="btn btn-primary-line" id="btnRegist"><spring:message code="btn.save"/></a>
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
                                        <th><spring:message code="lbl.group.code"/></th>
                                        <td><input id="groupCodeId" class="form-control" name="groupCodeId" value="${rParam.groupCodeId}" style="width:400px" readonly="readonly"></td>
                                    </tr>
                                    <tr>
                                        <th><span class="require">*</span><spring:message code="lbl.detailcode.code"/></th>
                                        <td><input id="codeId" class="form-control" name="codeId" value="" style="width:400px"></td>
                                    </tr>
                                    <tr>
                                        <th><span class="require">*</span><spring:message code="lbl.detailcode.name"/></th>
                                        <td><input id="codeNm" class="form-control" name="codeNm" value="" style="width:400px"></td>
                                    </tr>
                                    <tr>
                                        <th><span class="require">*</span><spring:message code="lbl.lang.msg_id"/></th>
                                        <td><input id="msgId" class="form-control" name="msgId" value="" style="width:400px"></td>
                                    </tr>
                                    <tr>
                                        <th><span class="require">*</span><spring:message code="lbl.order"/></th>
                                        <td>
                                            <select class="select" name="codeSortNo" style="width:100px">
                                                <c:forEach begin="1" end="10" var="cnt" step="1" varStatus="status">
                                                    <option value="${status.index}">${status.index}</option>
                                                </c:forEach>
                                            </select>
                                        </td>
                                    </tr>
                                    <tr>
                                        <th><spring:message code="lbl.use_yn"/></th>
                                        <td>
                                            <input type="radio" name="use_yn" value="Y" checked>&nbsp;Y&nbsp;&nbsp;
                                            <input type="radio" name="use_yn" value="N">&nbsp;N
                                        </td>
                                    </tr>
                                    <tr>
                                        <th><spring:message code="lbl.desc"/></th>
                                        <td><textarea id="description" class="form-control" name="description" style="width:400px;height:100px;"></textarea></td>
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