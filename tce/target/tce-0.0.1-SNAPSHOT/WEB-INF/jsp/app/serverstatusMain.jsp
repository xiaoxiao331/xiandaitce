
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/include/declare.jspf" %>
<script src="/js/My97DatePicker/WdatePicker.js"></script>

<script type="text/javaScript" language="javascript"> 

function goSearch() {
	if($('#serviceStatusDateSearch').val() == ''){
        alert("<spring:message code='msg.input.work.date'/>");
        $("#serviceStatusDateSearch").focus();
        return;
    }
    document.servesForm.action = "<c:url value='/app/serverstatusMain.do'/>";
    document.servesForm.submit();
} 
</script>

<!--***** REALCONTENTS START *****-->
<div class="wrapper">
    <div class="page">
    	<div class="ribbon">
            <h1 class="title"><spring:message code="lbl.title.app.serverstatus"/></h1>
        </div>
        <div class="page-content">
            <div class="container-fluid">
                <form id="servesForm" name="servesForm" enctype="multipart/form-data" method="post">
               	<div class="search-box">
                    <ul class="form-group">
                        <li>
                            <select class="select" style="width: 200px" id="serviceStatusFieidSearch" name="serviceStatusFieidSearch">
                               	<option value="ibs" <c:if test="${serviceStatusResultRroup.serviceStatusFieidSearch eq 'ibs'}">selected="selected"</c:if>>
                                <spring:message code="lbl.app.IBS" /></option>
                                <option value="dlw" <c:if test="${serviceStatusResultRroup.serviceStatusFieidSearch eq 'dlw'}">selected="selected"</c:if>>
                                <spring:message code="lbl.app.DLW" /></option>
                                <option value="ims" <c:if test="${serviceStatusResultRroup.serviceStatusFieidSearch eq 'ims'}">selected="selected"</c:if>>
                                <spring:message code="lbl.app.IMS" /></option>
                                <option value="ntc" <c:if test="${serviceStatusResultRroup.serviceStatusFieidSearch eq 'ntc'}">selected="selected"</c:if>>
                                <spring:message code="lbl.app.NTC" /></option>
                                <option value="sbs" <c:if test="${serviceStatusResultRroup.serviceStatusFieidSearch eq 'sbs'}">selected="selected"</c:if>>
                                <spring:message code="lbl.app.SBS" /></option>
                                <option value="ccw" <c:if test="${serviceStatusResultRroup.serviceStatusFieidSearch eq 'ccw'}">selected="selected"</c:if>>
                                <spring:message code="lbl.app.CCW" /></option>
                                 <option value="tmsdb" <c:if test="${serviceStatusResultRroup.serviceStatusFieidSearch eq 'tmsdb'}">selected="selected"</c:if>>
                                <spring:message code="lbl.app.TMS-DB" /></option>
                                 <option value="omsdb" <c:if test="${serviceStatusResultRroup.serviceStatusFieidSearch eq 'omsdb'}">selected="selected"</c:if>>
                                <spring:message code="lbl.app.OMS-DB" /></option>
                            </select>
                        </li>
                        <li>       
                        	<label for=""><span class="require">*</span><spring:message code="lbl.app.monitoring.serchdata"/></label>
                            <input type="text" class="Wdate" name="serviceStatusDateSearch" id="serviceStatusDateSearch" value="${queryDate}"
                            onFocus="WdatePicker({lang:'zh-cn',dateFmt:'yyyy-MM-dd HH:mm:ss',readOnly:true,})"/>
                        </li>
                        <li> 
                            <div class="btn_div">
                                <a href="#"  class="btn btn-primary" onclick="goSearch();" id="btnSearch" name="btnSearch"><spring:message code="btn.search"/></a>
                            </div>
                       </li>
                   </ul>  
                </div>
                <!--// end-->
                <div class="row">
                    <!--테이블 start-->
                    <div class="table-responsive table-wrap">
                        <table class="table">
                        	<thead>
                                <tr>
                                    <th><spring:message code="lbl.app.hostName"/></th>
                                    <th><spring:message code="lbl.app.rgstDtm"/></th>
                                    <th><spring:message code="lbl.app.serverIp"/></th>
                                    <th><spring:message code="lbl.app.cpu"/></th>
                                    <th><spring:message code="lbl.app.memoryTotal"/></th>
                                    <th><spring:message code="lbl.app.memoryFree"/></th>
                                    <th><spring:message code="lbl.app.memoryUsed"/></th>
                                    <th><spring:message code="lbl.app.threads"/></th>
                                    <th><spring:message code="lbl.app.threadsUsage"/></th>
                                    <th><spring:message code="lbl.app.rgstId"/></th>
                                </tr>
                            </thead>
                           	<tbody>
                           		<tr>
                                    <c:choose>
                                        <c:when test="${empty serverList}">
                                            <tr>
                                                <td colspan="10" align="center"><spring:message code="msg.search.data.empty"/></td>
                                            </tr>
                                        </c:when>
                                        <c:otherwise>
                                        <c:if test="${not empty serverList}">
                                        	<c:forEach var="result" items="${serverList}" varStatus="status">
	                                                <tr class='clickable-row'>
	                                                    <td><c:out value="${result.hostName}" /></td>
	                                                    <td><c:out value="${result.rgstDtm}" /></td>
	                                                    <td><c:out value="${result.serverIp}" /></td>
	                                                    <td><c:out value="${result.cpu}" /></td>
	                                                    <td><c:out value="${result.memoryTotal}" /></td>
	                                                    <td><c:out value="${result.memoryFree}" /></td>
	                                                    <td><c:out value="${result.memoryUsed}" /></td>
	                                                    <td><c:out value="${result.threads}" /></td>
	                                                    <td><c:out value="${result.threadsUsage}" /></td>
	                                                    <td><c:out value="${result.rgstId}" /></td>
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
