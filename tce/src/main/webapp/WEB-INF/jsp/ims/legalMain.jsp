
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/include/declare.jspf" %>
<script src="/js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javaScript" language="javascript"> 
$(function(){
	
});
function goSearch() {
	if($('#startDate').val() == '' || $('#endDate').val() == ''){
        alert("<spring:message code='msg.input.work.date'/>");
        $("#startDate").focus();
        return;
    }
    document.networkForm.action = "<c:url value='/legal/legalMain.do'/>";
    document.networkForm.submit();
} 

</script>
<style>
<!--
clickui-row{
	text-overflow: ellipsis; overflow: hidden; white-space: nowrap; width: 98%;
}
-->
</style>
<!--***** REALCONTENTS START *****-->
<div class="wrapper">
    <div class="page">
        <div class="page-content">
            <div class="container-fluid">
                <form id="networkForm" name="networkForm" enctype="multipart/form-data" method="post">
                <div class="search-box">
                    <ul class="form-group">
                        <li>       
                            <label for=""><spring:message code="lbl.ims.legal.data"/></label>
                            <input type="text" class="Wdate" name="startDate" id="startDate" value="${carRetailInfoSearchVO.startDate}" 
                            onFocus="WdatePicker({lang:'zh-cn',dateFmt:'yyyy-MM-dd',readOnly:true,minDate:'#F{$dp.$D(\'endDate\',{d:-1});}'})"/>
                                <i>~</i>
                            <input type="text" class="Wdate" name="endDate" id="endDate" value="${carRetailInfoSearchVO.endDate}" 
                            onFocus="WdatePicker({lang:'zh-cn',dateFmt:'yyyy-MM-dd',readOnly:true,minDate:'#F{$dp.$D(\'startDate\',{d:+1});}'})"/>
                        </li>
                        <li>
	                        <label><spring:message code="lbl.ims.legal.terminal"/></label>
	                        <input class="form-control" id="csmcScn" name="csmcScn" style="width: 150px;" type="text" value="${carRetailInfoSearchVO.csmcScn}" >
                        </li>
                        <li>
	                        <label><spring:message code="lbl.ims.legal.person"/></label>
	                        <input class="form-control" id="tmnlType" name="tmnlType" style="width: 150px;" type="text" value="${carRetailInfoSearchVO.tmnlType}" >
                        </li>
                        <li> 
                            <div class="btn_div">
                                <a href="#"  class="btn btn-primary" onclick="goSearch();" id="btnSearch" name="btnSearch"><spring:message code="btn.search"/></a>
                            </div>
                       </li>
                   </ul>  
                </div>
                
                <div class="row">
                    <!--테이블 start-->
                    <div class="table-responsive table-wrap">
                        <table class="table">
                            <thead>
                                <tr>
                                    <th><spring:message code="lbl.legal.company"/></th>
                                    <th><spring:message code="lbl.legal.cartype"/></th>
                                    <th><spring:message code="lbl.legal.production"/></th>
                                    <th><spring:message code="lbl.legal.assigned"/></th>
                                    <th><spring:message code="lbl.legal.contract"/></th>
                                    <th><spring:message code="lbl.legal.retail"/></th>
                                    <th><spring:message code="lbl.legal.accumulative"/></th>
                                    <th><spring:message code="lbl.legal.communication"/></th>
                                    <th><spring:message code="lbl.legal.accruing"/></th>
                                    <th><spring:message code="lbl.legal.serve"/></th>
                                    <th><spring:message code="lbl.legal.termination"/></th>
                                    <th><spring:message code="lbl.legal.accumulativeserver"/></th>
                                    <th><spring:message code="lbl.legal.register"/></th>
                                </tr>
                            </thead>
                            <tbody>
                           		<tr>
                                    <c:choose>
                                        <c:when test="${empty resultList}">
                                            <tr>
                                                <td colspan="13" align="center"><spring:message code="msg.search.data.empty"/></td>
                                            </tr>
                                        </c:when>
                                        <c:otherwise>
                                        <c:if test="${not empty resultList}">
                                        	<c:forEach var="result" items="${resultList}" varStatus="status">
	                                                <tr class='clickable-row'>
	                                                    <td><c:out value="${result.csmcScn}" /></td>
	                                                    <td><c:out value="${result.carModel}" /></td>
	                                                    <td><c:out value="${result.productCnt}" /></td>
	                                                    <td><c:out value="${result.deliveryCnt}" /></td>
	                                                    <td><c:out value="${result.custCNTT}" /></td>
	                                                    <td><c:out value="${result.contractCnt}" /></td>
	                                                    
	                                                    <td><c:out value="${result.sumSaleCnt}" /></td>
	                                                    <td><c:out value="${result.openCnt}" /></td>
	                                                    <td><c:out value="${result.sumOpenCnt}" /></td>
	                                                    <td><c:out value="${result.provisonCnt}" /></td>
	                                                    <td><c:out value="${result.cancelCnt}" /></td>
	                                                    <td><c:out value="${result.totalupCnt}" /></td>
	                                                    <td><c:out value="${result.custAllCNTT}" /></td>
	                                                </tr>
                                            </c:forEach>
                                          </c:if>
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
