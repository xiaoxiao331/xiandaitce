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
        //조회 버튼
        $("#btnSearch").click(function() {
            $("#pageIndex").val(1);
            $("#frm").attr('action',"/setting/authMenuMain.do" );
            $('#frm').submit();
        });
        
        //수정 버튼
        $("#btnUpdate").click(function() {
            var checkList = [];
            $.each($("input[name='selectBoxArr']:checked"), function() {
                checkList.push($(this).val()); 
            });
            
            var uncheckList = [];
            $.each($("input[name='selectBoxArr']:not(:checked)"), function() {
                uncheckList.push($(this).val()); 
            });
            
            $("#checkList").val(checkList.join(","));
            $("#unCheckList").val(uncheckList.join(","));
            
            if(!confirm("<spring:message code='msg.confirm_update' />")) return;
            $("#frm").attr('action',"/setting/authMenuUpdate.do");
            $('#frm').submit();
        });
    });
</script>


<form id="frm" name="frm" method="post">
    <!-- 검색조건 유지 Start -->
    <input type="hidden" name="pageIndex"   id="pageIndex"   value="${rParam.pageIndex}" />
    <!-- 검색조건 유지 End -->

    <input type="hidden" name="menuId"      id="menuId"      value=0                     />
    <input type="hidden" name="checkList"   id="checkList"   value=""                    />
    <input type="hidden" name="unCheckList" id="unCheckList" value=""                    />
    
    <div class="wrapper">
        <div class="page">
            <div class="ribbon">
                <h1 class="title"><spring:message code="menu.admin.authmenu.mgmt" /></h1>
            </div>
            <div class="page-content">
                <div class="container-fluid">
                    <div class="search-box">
                        <ul class="form-group">
                            <li>
                                <label><spring:message code="lbl.authgroup" /></label>
                                <select id="searchVal01" name="searchVal01" class="select" style="width:150px">
                                    <option selected="selected" value=""><spring:message code="lbl.total" /></option>
                                    <c:if test="${not empty authList}">
                                        <c:forEach var="auth" items="${authList}">
                                            <option value="${auth.authId}" <c:if test="${auth.authId==rParam.searchVal01}">selected="selected"</c:if>>${auth.authNm}</option>
                                        </c:forEach>
                                    </c:if>
                                </select>
                            </li>
                            <li>
                                <a class="btn btn-primary" href="#" id="btnSearch" type="button"><spring:message code="btn.search" /></a>
                            </li>
                        </ul>
                    </div>
                </div>
                <div class="row">
                    <div class="title-wrap">
                        <h2 class="board-title"><spring:message code="menu.admin.authmenu.mgmt" /> (Total : ${rCnt})</h2>
                        <div class="board-top">
                            <a class="btn btn-primary" href="#" id="btnUpdate"  type="button"><spring:message code="btn.update" /></a>
                        </div>
                    </div>
                    <div class="table-wrap"> 
                        <table id="listTable" class="table table-hover">
                            <colgroup>
                                <col style="width: 40px">
                                <col style="width: 60px">
                                <col style="width: 150px">
                                <col style="width: 20px">
                                <col style="width: 20px">
                            </colgroup>
                            <thead>
                                <tr>
                                    <th><spring:message code="lbl.authgroup" /></th>
                                    <th><spring:message code="lbl.menu_name"/></th>
                                    <th><spring:message code="lbl.menu.path" /></th>
                                    <th><spring:message code="lbl.menu_level"/></th>
                                    <th><spring:message code="lbl.menu.useyn" /></th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:if test="${empty rList}">
                                    <tr>
                                        <td colspan="5" align="center"><spring:message code="msg.info.search.nofound"/></td>
                                    </tr>
                                </c:if>
                                <c:if test="${not empty rList}">
                                    <c:forEach var="data" items="${rList}" varStatus="status">
                                        <tr class='clickable-row'>
                                            <td><c:out value="${data.authNm}" /></td>
                                            <td><c:out value="${data.menuNm}" /></td>
                                            <td style='text-align:left;'><c:out value="${data.menuPath}" /></td>
                                            <td><c:out value="${data.menuLevel}" /></td>
                                            <td>
                                                <span class="checkbox nolabel">
                                                    <input id="selectBoxArr${status.index}" name="selectBoxArr" type="checkbox" value="<c:out value="${data.mapId}"/>" <c:if test="${data.useYn eq 'Y'}">checked</c:if>/>
                                                                                                        <label for="selectBoxArr${status.index}"><spring:message code="lbl.menu.useyn" /></label>
                                                </span>
                                            </td>
                                        </tr>
                                    </c:forEach>
                                </c:if>
                            </tbody>
                        </table>
                    </div>
                    <!-- Paging -->
                    <c:if test="${!empty rList}">${rPage.sPageHtml}</c:if>
                </div>
            </div>
        </div>
    </div>
</form>
