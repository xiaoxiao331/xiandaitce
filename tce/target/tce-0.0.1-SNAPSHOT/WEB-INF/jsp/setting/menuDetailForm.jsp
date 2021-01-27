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
  //삭제 버튼
    $("#btnDelete").click(function() {
        if(!confirm("<spring:message code='msg.confirm_delete'/>")) return;
        
        $("#frm").attr('action',"/setting/menuDelete.do");
        $("#frm").submit();
    });
    
    //수정 버튼
    $("#btnUpdate").click(function() {
        var menuNm = $("#menuNm").val();
        if(menuNm == '') {
            alert("<spring:message code='msg.input.menuname'/>");
            return;
        }
        
        var menuCode = $("#menuCode").val();
        if(menuCode == '') {
            alert("<spring:message code='msg.input.menucode'/>");
            return;
        }
        
        var msgId = $("#msgId").val();
        if(msgId == '') {
            alert("<spring:message code='msg.input.msgid'/>");
            return;
        }
        
        var use_yn = $('input[name=use_yn]:checked').val();
        $("#useYn").val(use_yn);
        
        //상위 ID 설정
        var upper_menu_id = -1;
        var str = $("#upper_menu_id").val();
        if(str != '')
            upper_menu_id = str;
        $("#upperMenuId").val(upper_menu_id);
        
        if(!confirm("<spring:message code='msg.confirm_update'/>")) return;
        
        $("#frm").attr('action',"/setting/menuUpdate.do");
        $("#frm").submit();
    });
    
    //목록버튼
    $("#btnList").click(function() {
        $("#frm").attr('action',"/setting/menuMain.do");
        $("#frm").submit();
    });
});
</script>

<form id="frm" name="frm" method="post">
    <!-- 검색조건 유지 Start -->
    <input type="hidden" name="pageIndex"     value="${rParam.pageIndex}"   />
    <input type="hidden" name="searchVal01"   value="${rParam.searchVal01}" />
    <!-- 검색조건 유지 End -->
    
    <input type="hidden" name="useYn"         id="useYn"               />
    <input type="hidden" name="upperMenuId"   id="upperMenuId" value=0 />
    
    <div class="wrapper">
        <div class="page">
            <div class="ribbon">
                <div class="title"><spring:message code="menu.admin.menu.mgmt"/></div>
            </div>
            <div class="page-content">
                <div class="container-fluid">
                    <div class="row">
                        <div class="board-top">
                            <a href="#" class="btn btn-primary-line" id="btnDelete"><spring:message code="btn.delete"/></a>
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
                                        <th><spring:message code="lbl.menu_level"/></th>
                                        <td><input type="text" class="form-control" name="menuId" id="menuId" value="${rData.menuId}" style="width:100px" readonly="readonly"></td>
                                    </tr>
                                    <tr>
                                        <th><spring:message code="lbl.menu_level"/></th>
                                        <td>
                                            <select class="select" id="menuLevel" name="menuLevel" style="width:100px">
                                                <c:forEach begin="1" end="3" var="cnt" step="1" varStatus="status">
                                                    <option value="${status.index}" <c:if test="${rData.menuLevel eq status.index}">selected</c:if>>${status.index}</option>
                                                </c:forEach>
                                            </select>
                                        </td>
                                    </tr>
                                    <tr>
                                        <th><spring:message code="lbl.menu.id"/></th>
                                        <td>
                                            <select class="select" name="menuSortNo" style="width:100px">
                                                <c:forEach begin="1" end="15" var="cnt" step="1" varStatus="status">
                                                    <option value="${status.index}" <c:if test="${rData.menuSortNo eq status.index}">selected</c:if>>${status.index}</option>
                                                </c:forEach>
                                            </select>
                                        </td>
                                    </tr>
                                    <tr>
                                        <th><spring:message code="lbl.order"/></th>
                                        <td>
                                            <select class="select" id="upper_menu_id" style="width:400px">
                                                <option value="" <c:if test="${menu.upperMenuId eq ''}">selected</c:if>>TOP</option>
                                                <c:if test="${not empty upperMenuList}">
                                                    <c:forEach var="upperMenu" items="${upperMenuList}">
                                                        <option value="${upperMenu.menuId}" <c:if test="${upperMenu.menuId eq rData.upperMenuId}">selected</c:if>>
                                                            <c:out value="${upperMenu.menuNm}" /></option>
                                                    </c:forEach>
                                                </c:if>
                                            </select>
                                        </td>
                                    </tr>
                                    <tr>
                                        <th><span class="require">*</span><spring:message code="lbl.menu_name"/></th>
                                        <td><input id="menuNm" class="form-control" name="menuNm" value="${rData.menuNm}" style="width:400px"></td>
                                    </tr>
                                    <tr>
                                        <th><span class="require">*</span><spring:message code="lbl.menu_code"/></th>
                                        <td><input id="menuCode" class="form-control" name="menuCode" value="${rData.menuCode}" style="width:400px"></td>
                                    </tr>
                                    <tr>
                                        <th><span class="require">*</span><spring:message code="lbl.lang.msg_id"/></th>
                                        <td><input id="msgId" class="form-control" name="msgId" value="${rData.msgId}" style="width:400px"></td>
                                    </tr>
                                    <tr>
                                        <th><spring:message code="lbl.menu_url"/></th>
                                        <td><input id="menuUrl" class="form-control" name="menuUrl" value="${rData.menuUrl}" style="width:400px"></td>
                                    </tr>
                                    <tr>
                                        <th><spring:message code="lbl.use_yn"/></th>
                                        <td>
                                            <span class="radio">
                                                <input type="radio" name="use_yn" id="use_yn01" value="Y" <c:if test="${rData.useYn eq 'Y'}">checked</c:if>>
                                                <label for="use_yn01">Y</label>
                                            </span>
                                            <span class="radio">
                                                <input type="radio" name="use_yn" id="use_yn02" value="N" <c:if test="${rData.useYn eq 'N'}">checked</c:if>>
                                                <label for="use_yn02">N</label>
                                            </span>
                                        </td>
                                    </tr>
                                    <tr>
                                        <th><spring:message code="lbl.desc"/></th>
                                        <td><textarea id="description" class="form-control" name="description" style="width:400px;height:100px;">${rData.description}</textarea></td>
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