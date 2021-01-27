<%-- <%@page import="org.springframework.ui.Model"%> --%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/include/declare.jspf" %>
<script src="/js/My97DatePicker/WdatePicker.js"></script>

<%-- 
        监控信息展示
--%>
<script type="text/javaScript" language="javascript"> 
$(function(){
});

function goSearch() {
	if($('#monitoringDateSearch').val() == ''){
        alert("<spring:message code='msg.input.work.date'/>");
        $("#monitoringDateSearch").focus();
        return;
    }
    document.monitoringForm.action = "<c:url value='/app/monitoringMain.do'/>";
    document.monitoringForm.submit();
}
</script>

<!--***** REALCONTENTS START *****-->
<div class="wrapper">
    <div class="page">
        <div class="ribbon">
            <h1 class="title"><spring:message code="lbl.title.app.monitoring"/></h1>
        </div>
        <div class="page-content">
            <div class="container-fluid">
                <form id="monitoringForm" name="monitoringForm" enctype="multipart/form-data" method="post">
                <div class="search-box">
                    <ul class="form-group">
                        <li>
                            <select class="select" style="width: 200px" id="monitoringFieidSearch" name="monitoringFieidSearch">
                                <option value="SCENARIO" <c:if test="${monitoringResultGroup.monitoringFieidSearch eq 'SCENARIO'}">selected="selected"</c:if>>
                                <spring:message code="lbl.app.SCENARIO" /></option>
                                <option value="ALLCNT" <c:if test="${monitoringResultGroup.monitoringFieidSearch eq 'ALLCNT'}">selected="selected"</c:if>>
                                <spring:message code="lbl.app.ALLCNT" /></option>
                                <option value="SUCCESS" <c:if test="${monitoringResultGroup.monitoringFieidSearch eq 'SUCCESS'}">selected="selected"</c:if>>
                                <spring:message code="lbl.app.SUCCESS" /></option>
                                <option value="FAILCNT" <c:if test="${monitoringResultGroup.monitoringFieidSearch eq 'FAILCNT'}">selected="selected"</c:if>>
                                <spring:message code="lbl.app.FAILCNT" /></option>
                                <option value="CONTINUE" <c:if test="${monitoringResultGroup.monitoringFieidSearch eq 'CONTINUE'}">selected="selected"</c:if>>
                                <spring:message code="lbl.app.CONTINUE" /></option>
                                <option value="FAILURERATE" <c:if test="${monitoringResultGroup.monitoringFieidSearch eq 'FAILURERATE'}">selected="selected"</c:if>>
                                <spring:message code="lbl.app.FAILURERATE" /></option>
                            </select>
                        </li>
                        <li>
                            <select class="select" style="width: 100px" name="monitoringSortSearch" id="monitoringSortSearch"  >
                                <option value="ASC" <c:if test="${monitoringResultGroup.monitoringSortSearch == 'ASC'}">selected="selected"</c:if>><spring:message code="lbl.ASC" /></option>
                                <option value="DESC" <c:if test="${monitoringResultGroup.monitoringSortSearch == 'DESC'}">selected="selected"</c:if>><spring:message code="lbl.DESC" /></option>
                            </select>
                        </li>
                        <li>       
                        	<label for=""><span class="require">*</span><spring:message code="lbl.app.monitoring.serchdata"/></label>
                            <input type="text" class="Wdate" name="monitoringDateSearch" id="monitoringDateSearch" value="${queryDate}"
                            onFocus="WdatePicker({lang:'zh-cn',dateFmt:'yyyy-MM-dd HH:mm:ss',readOnly:true,})"/>
                        </li>
                        <li> 
                            <div class="btn_div">
                                <%-- <a href="#"  class="btn btn-primary" onclick="goSearch();" id="btnSearch" name="btnSearch"><spring:message code="btn.search"/></a> --%>
                            	<span onclick="goSearch();" class="btnsch"><span><spring:message code="btn.search"/></span></span>
                            </div>
                       </li>
                   </ul>  
                </div>
                 <div class="search-box-monitoring-data" style="width: 260px;">
                    <ul class="form-group-monitoring-data" >
                        <li>       
                        	<label for=""><spring:message code="lbl.app.monitoring.update.date"/></label>
                        	<label >${resultList.get(0).rgstDtm}</label>
                        </li>
                   </ul>  
                </div>
                <!--// 검색박스 end-->
                <div class="row">
                    
                    <!--테이블 start-->
                    <div class="table-responsive table-wrap">
                        <table class="table">
                            <thead>
                                <tr>
                                    <th><spring:message code="lbl.app.SCENARIO"/></th>
                                    <th><spring:message code="lbl.app.ALLCNT"/></th>
                                    <th><spring:message code="lbl.app.SUCCESS"/></th>
                                    <th><spring:message code="lbl.app.FAILCNT"/></th>
                                    <th><spring:message code="lbl.app.CONTINUE"/></th>
                                    <th><spring:message code="lbl.app.FAILURERATE"/></th>
                                </tr>
                            </thead>
                            <tbody> 
                                <tr>
                                    <c:choose>
                                        <c:when test="${empty resultList}">
                                            <tr>
                                                <td colspan="6" align="center"><spring:message code="msg.search.data.empty"/></td>
                                            </tr>
                                        </c:when>
                                        <c:otherwise>
                                        	<c:forEach var="result" items="${resultList}" varStatus="status">
                                        		<c:set scope="page" var="isErrRow" value="false"></c:set>
                                        		<c:if test="${not empty resultListError}">
	                                        		<c:forEach var="rerro2" items="${resultListError}" varStatus="status2">
	                                        			<c:if test="${rerro2.monitoringname eq result.scenario}">
	                                        				<tr class='clickable-row' style="color: ${rerro2.errorColor}" >
			                                                    <td><c:out value="${result.scenario}" /></td>
			                                                    <td><c:out value="${result.allcnt}" /></td>
			                                                    <td><c:out value="${result.success}" /></td>
			                                                    <td><c:out value="${result.failCnt}" /></td>
			                                                    <td><c:out value="${result.contiNue}" /></td>
			                                                    <td><c:out value="${result.failurerate}" /></td>
			                                                </tr>
			                                                <c:set scope="page" var="isErrRow" value="true"></c:set>
	                                        			</c:if>
	                                        		</c:forEach>
                                        		</c:if>
                                        		<c:if test="${isErrRow == false}">
	                                                <tr class='clickable-row'>
	                                                    <td><c:out value="${result.scenario}" /></td>
	                                                    <td><c:out value="${result.allcnt}" /></td>
	                                                    <td><c:out value="${result.success}" /></td>
	                                                    <td><c:out value="${result.failCnt}" /></td>
	                                                    <td><c:out value="${result.contiNue}" /></td>
	                                                    <td><c:out value="${result.failurerate}" /></td>
	                                                </tr>
                                        		</c:if>
                                            </c:forEach>
                                        </c:otherwise>
                                    </c:choose>
                                </tr>
                            </tbody>
                        </table>
                    </div>     
                </div>
                </form> 
            </div>
        </div>
    </div> 
      
</div>
<!--//***** REALCONTENTS END *****-->
