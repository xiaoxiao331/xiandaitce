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
        $(function(){
            //조회 버튼
            $("#btnSearch").click(function() {
                $("#pageIndex").val(1);
                $("#frm").attr('action',"/setting/authMain.do" );
                $('#frm').submit();
            });
            
            //등록 버튼
            $("#btnRegist").click(function() {
                $("#frm").attr('action',"/setting/authRegistForm.do");
                $("#frm").submit();
            });
            
            //테이블 Row클릭(상세보기)
            $("#listTable tr").click(function() {
                var authId = $(this).attr("data-href");
                
                $("#authId").val(authId);
                $("#frm").attr('action',"/setting/authDetailForm.do");
                $("#frm").submit();
            });
        });
    });
</script>

<form id="frm" name="frm" method="post">
    <!-- 검색조건 유지 Start -->
    <input type="hidden" name="pageIndex" id="pageIndex" value="${rParam.pageIndex}" />
    <!-- 검색조건 유지 End -->
    <input type="hidden" name="authId"    id="authId"    value="" />
    
    <div class="wrapper">
        <div class="page">
            <div class="ribbon">
                <h1 class="title">
                    <spring:message code="menu.admin.auth.mgmt"/>
                </h1>
            </div>
            <div class="page-content">
                <div class="container-fluid">
                    <div class="search-box">
                        <ul class="form-group">
                            <li>
                                <label><spring:message code="lbl.auth.group_name"/></label>
                                <input class="form-control" name="searchVal01" style="width: 250px;" type="text" value="${rParam.searchVal01}">
                            </li>
                            <li>
                                <%-- <a class="btn btn-primary" href="#" id="btnSearch" type="button"><spring:message code="btn.search"/></a> --%>
                            	<span id="btnSearch" class="btnsch"><span><spring:message code="btn.search"/></span></span>
                            </li>
                        </ul>
                    </div>
                </div>
                <div class="row">
                    <div class="title-wrap">
                        <h2 class="board-title"><spring:message code="menu.admin.auth.mgmt"/> (Total : ${rCnt})</h2>
                        <div class="board-top">
                            <a class="btn btn-primary" href="#" id="btnRegist" type="button"><spring:message code="btn.regist"/></a>
                        </div>
                    </div>
                    <div class="table-responsive table-wrap"> 
                        <table id="listTable" class="table table-hover">
                            <colgroup>
                                <col style="width: 100px">
                                <col style="width: 100px">
                                <col style="width: 100px">
                            </colgroup>
                            <thead>
                                <tr>
                                    <th><spring:message code="lbl.auth.group_code"/></th>
                                    <th><spring:message code="lbl.auth.group_name"/></th>
                                    <th><spring:message code="lbl.auth.use_yn"/></th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:if test="${empty rList}">
                                    <tr>
                                        <td colspan="3" align="center"><spring:message code="msg.search.data.empty"/></td>
                                    </tr>
                                </c:if>
                                <c:if test="${not empty rList}">
                                    <c:forEach var="data" items="${rList}" varStatus="status">
                                        <tr class='clickable-row' data-href="${data.authId}">
                                            <td><c:out value="${data.authId}" /></td>
                                            <td><c:out value="${data.authNm}" /></td>
                                            <td><c:out value="${data.authUseYn}" /></td>
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