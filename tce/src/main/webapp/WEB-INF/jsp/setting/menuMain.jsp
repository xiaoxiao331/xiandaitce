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
             $("#frm").attr('action',"/setting/menuMain.do" );
             $('#frm').submit();
        });
        
        //등록 버튼
        $("#btnRegist").click(function() {
            $("#frm").attr('action',"/setting/menuRegistForm.do");
            $("#frm").submit();
        });
        
        //테이블 Row클릭(상세보기)
        $(".clickable-row td").click(function() {
            var index = $(this).parent().index();
            var menuId = $("#menuIdArr"+index).val();
            $("#menuId").val(menuId);
            
            $("#frm").attr('action',"/setting/menuDetailForm.do");
            $("#frm").submit();
        });
      
        $('#myTable tr').click(function(event) {
            var mid = $(this).attr("data-href");
            if(mid == null || mid == '') {
                // nothing to do
            } else {
                $('#menuId').val(mid);
                $("#showPage").attr('action',"/com/menuDetailForm.do");
                $("#showPage").submit();
            }
        });
    });
</script>

<form id="frm" name="frm" method="post">
    <!-- 검색조건 유지 Start -->
    <input type="hidden" name="pageIndex" id="pageIndex" value="${rParam.pageIndex}" />
    <!-- 검색조건 유지 End -->
    <input type="hidden" name="menuId" id="menuId" value=0                           />
    
    <div class="wrapper">
        <div class="page">
            <div class="ribbon">
                <h1 class="title"><spring:message code="menu.admin.menu.mgmt"/></h1>
            </div>
            <div class="page-content">
                <div class="container-fluid">
                    <div class="search-box">
                        <ul class="form-group">
                            <li>
                                <label><spring:message code="lbl.menu_name"/></label>
                                <input class="form-control" name="searchVal01" style="width: 150px;" type="text" value="${rParam.searchVal01}">
                            </li>
                            <li>
                                <%-- <a class="btn btn-primary" href="#" id="btnSearch" type="button"><spring:message code="btn.search" /></a> --%>
                           		<span id="btnSearch" class="btnsch"><span><spring:message code="btn.search"/></span></span>
                            </li>
                        </ul>
                    </div>
                </div>
                <div class="row">
                    <div class="title-wrap">
                        <h2 class="board-title"><spring:message code="menu.admin.menu.mgmt"/> (Total : ${rCnt})</h2>
                        <div class="board-top">
                            <a class="btn btn-primary" href="#" id="btnRegist"  type="button"><spring:message code="btn.regist"/></a>
                        </div>
                    </div>
                    <div class="table-responsive table-wrap"> 
                        <div class="thead-wrap">
                            <table id="listTable" class="table table-hover">
                                <colgroup>
                                    <col style="width: 35px">
                                    <col style="width: 60px">
                                    <col style="width: 110px">
                                    <col style="width: 160px">
                                    <col style="width: 30px">
                                    <col style="width: 30px">
                                    <col style="width: 160px">
                                    <col style="width: 45px">
                                </colgroup>
                                <thead>
                                    <tr>
                                        <th><spring:message code="lbl.menu.id" /></th>
                                        <th><spring:message code="lbl.menu_code" /></th>
                                        <th><spring:message code="lbl.menu_name"/></th>
                                        <th><spring:message code="lbl.menu.path" /></th>
                                        <th><spring:message code="lbl.level"/></th>
                                        <th><spring:message code="lbl.order"/></th>
                                        <th><spring:message code="lbl.menu_url"/></th>
                                        <th><spring:message code="lbl.use_yn"/></th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:if test="${empty rList}">
                                        <tr>
                                            <td colspan="8" align="center"><spring:message code="msg.info.search.nofound"/></td>
                                        </tr>
                                    </c:if>
                                    <c:if test="${not empty rList}">
                                        <c:forEach var="data" items="${rList}" varStatus="status">
                                            <tr class='clickable-row'>
                                                <td>
                                                    <input type="hidden" id="menuIdArr${status.index}" value="${data.menuId}" />
                                                    <c:out value="${data.menuId}" />
                                                </td>
                                                <td><c:out value="${data.menuCode}" /></td>
                                                <td><c:out value="${data.menuNm}" /></td>
                                                <td style='text-align:left;'><c:out value="${data.upperMenuNm}" /></td>
                                                <td><c:out value="${data.menuLevel}" /></td>
                                                <td><c:out value="${data.menuSortNo}" /></td>
                                                <td style='text-align:left;'><c:out value="${data.menuUrl}" /></td>
                                                <td><c:out value="${data.useYn}" /></td>
                                            </tr>
                                        </c:forEach>
                                    </c:if>
                                </tbody>
                            </table>
                        </div>
                    </div>
                    <!-- Paging -->
                    <c:if test="${!empty rList}">${rPage.sPageHtml}</c:if>
                </div>
            </div>
        </div>
    </div>
</form>

