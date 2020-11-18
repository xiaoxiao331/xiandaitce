<html>
<head>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/include/declare.jspf" %>
<script src="/js/My97DatePicker/WdatePicker.js"></script>
<title></title>
<%-- 
        工单信息展示
--%>
<script type="text/javaScript" language="javascript"> 
$(function(){
	/**
	 * 选中数据跳到修改页面
	 */
	$(".clickable-row td").click(function() {
	    var index = $(this).parent().index();
	    $('#worId').val($("#arrayWorkId"+index).val());
	    $('#exaId').val($("#arrayWorkId"+index).val());
	    $('#notiForm').attr('action',"/work/workDetailForm.do");
	    $('#notiForm').submit();
	});
});


function goSearch() {
    document.notiForm.action = "<c:url value='/work/workMain.do'/>";
    document.notiForm.submit();
}
    
function fn_regist()
{
    var frm = document.notiForm;
    frm.target = "_self";
    frm.action = "<c:url value='/work/workRegistForm.do'/>";
    frm.submit();
}

function jsPage(page) {
    document.notiForm.currPage.value = page;
    goSearch(); 
}
function overShow(obj,e) {}
function outHide() {}
</script>
 </head>
 <body> 
<!--***** REALCONTENTS START *****-->
<div class="wrapper" id="app">
    <div class="page">
        <div class="ribbon">
        	 <h1 class="title">
           		 <spring:message code="lbl.title.noti.search"/>
            </h1>
        </div>
        <div class="page-content">
            <div class="container-fluid">
                <form id="notiForm" name="notiForm" method="post">
                <input type="hidden" name="worId" value="" id="worId"/>
                <input type="hidden" name="selectedBrdSn" />
                <input type="hidden" name="currPage" value=""/>
                <input type="hidden" name="exaId" value="" id="exaId"/>
                <div class="search-box">
                    <ul class="form-group">
                    	<li>
	                        <label><spring:message code="lbl.worPerson"/></label>
	                        <input class="form-control" id="worPerson" name="worPerson" style="width: 150px;" type="text" value="${workMgrVO.worPerson}" >
                        </li>
                        <li>
                            <label><spring:message code="lbl.sendyn"/></label>
                                <select class="select" style="width: 100px" name="worResult" id="worResult" >
                                    <option selected="selected" value=""><spring:message code="lbl.total" /></option>
                                    <option value="A" ${workMgrVO.worResult eq 'A' ? 'selected="selected"' : ''}><spring:message code="code.mt.receive"/></option>
                                    <option value="B" ${workMgrVO.worResult eq 'B' ? 'selected="selected"' : ''}><spring:message code="code.mt.processing"/></option>
                                    <option value="C" ${workMgrVO.worResult eq 'C' ? 'selected="selected"' : ''}><spring:message code="code.mt.complete"/></option>
                                    <option value="D" ${workMgrVO.worResult eq 'D' ? 'selected="selected"' : ''}><spring:message code="code.mt.end"/></option>
                                </select>
                        </li>
                        <li>
                            <label><spring:message code="lbl.noti.type"/></label>
                            <select class="select" style="width: 100px" id="worClassify" name="worClassify">
                                <option selected="selected" value=""><spring:message code="lbl.total" /></option>
                                <option value="A" ${workMgrVO.worClassify eq 'A' ? 'selected="selected"' : ''}><spring:message code="code.mt.work.operati"/></option>
                                <option value="B" ${workMgrVO.worClassify eq 'B' ? 'selected="selected"' : ''}><spring:message code="code.mt.work.wxwork"/></option>
                                <option value="C" ${workMgrVO.worClassify eq 'C' ? 'selected="selected"' : ''}><spring:message code="code.mt.work.emergency"/></option>
                                <option value="D" ${workMgrVO.worClassify eq 'D' ? 'selected="selected"' : ''}><spring:message code="code.mt.work.improvement"/></option>
                                <option value="E" ${workMgrVO.worClassify eq 'E' ? 'selected="selected"' : ''}><spring:message code="code.mt.work.functional"/></option>
                            </select>
                        </li>
                        <li>       
                            <label for=""><spring:message code="lbl.success.vin.cnt"/></label>
                            <input type="text" class="Wdate" name="searchStartDate" id="searchStartDate" value="${workMgrVO.searchStartDate}" 
                            onFocus="WdatePicker({lang:'zh-cn',dateFmt:'yyyy-MM-dd',readOnly:true,minDate:'#F{$dp.$D(\'searchEndDate\',{d:-1});}'})"/>
                                <i>~</i>
                            <input type="text" class="Wdate" name="searchEndDate" id="searchEndDate" value="${workMgrVO.searchEndDate}" 
                            onFocus="WdatePicker({lang:'zh-cn',dateFmt:'yyyy-MM-dd',readOnly:true,minDate:'#F{$dp.$D(\'searchStartDate\',{d:+1});}'})"/>
                        </li>
                        <li> 
                            <div class="btn_div">
                                <a href="#"  class="btn btn-primary" title="조회" id="btnSearch" name="btnSearch" onclick="goSearch();"><spring:message code="btn.search"/></a>
                            </div>
                       </li>
                   </ul>  
                </div>
                <!--// 검색박스 end-->
                <div class="row">
                    <div class="board-top">
                    	<div class="insideWorkMainone">
                    		<div class="insideWorkMain_A"></div>
                    		<div class="insideReceive">接收</div>
                    		<div class="insideWorkMain_B"></div>
                    		<div class="insideProcessing">处理中</div>
                    		<div class="insideWorkMain_C"></div>
                    		<div class="insideComplete">处理完成</div>
                    		<div class="insideWorkMain_D"></div>
                    		<div class="insideEnd">结束</div>
                    	</div>
                    	<div class="insideWorkMaintwo">
                    		<a href="#" class="btn btn-primary" onclick="fn_regist();"><spring:message code="btn.regist"/></a>
                    	</div>
                    	<div class="insideWorkMainthree">
                    		<div class="insideWorkMainthree_A"></div>
                    		<div class="insideReceive">低</div>
                    		<div class="insideWorkMainthree_B"></div>
                    		<div class="insideProcessing">中</div>
                    		<div class="insideWorkMainthree_C"></div>
                    		<div class="insideComplete">高</div>
                    		<div class="insideWorkMainthree_D"></div>
                    		<div class="insideEnd">紧急</div>
                    	</div>
                    	
                    </div>
                    <!--테이블 start-->
                    <div class="table-responsive table-wrap">
                        <table class="table table-hover">
                            <colgroup>
                                <!-- <col style="width: 30px"> -->
                                <col style="width: 80px">
                                <col style="width: 80px">
                                <col style="width: 95px">
                                <col style="width: 43%">
                                <col style="width: 9%">
                                <col style="width: 8%">
                                <col style="width: 160px">
                            </colgroup>
                            <thead>
                                <tr>
                                	<%-- <th>
                                        <span class="checkbox nolabel">
                                            <input id="selectAll" name="selectAll" type="checkbox" >
                                            <label for="selectAll"><spring:message code="lbl.all.check"/></label>
                                        </span>
                                    </th> --%>
                                    <th><spring:message code="lbl.noti.type"/></th>
                                    <th><spring:message code="lbl.urgency"/></th>
                                    <th><spring:message code="lbl.work.worCreauser"/></th>
                                    <th><spring:message code="lbl.work.worTitle"/></th>
                                    <th><spring:message code="lbl.worPerson"/></th>
                                    <th><spring:message code="lbl.sendyn"/></th>
                                    <th><spring:message code="lbl.success.vin.cnt"/></th>
                                </tr>
                            </thead>
                            <tbody> 
                            	<c:if test="${empty resultList}">
                            		<tr>
                                       <td colspan="7" align="center"><spring:message code="msg.search.data.empty"/></td>
                                    </tr>
                            	</c:if>
                            	<c:if test="${not empty resultList}">
                            		<c:forEach var="result" items="${resultList}" varStatus="status">
                                          <tr class='clickable-row'>
                                              <!-- <input type="hidden" id="notiIndex$" value="" /> -->
                                              <%-- <td>
                                                  <span class="checkbox nolabel">
                                                  	<input id="subChk${status.index}" name="selChk" type="checkbox" value="<c:out value="${result.worid}"/>">
                                                      <label for="subChk${status.index}"><spring:message code="btn.select"/></label>
                                                  </span>
                                                  <input type="hidden" id="arrayWorkId${status.index}" value="${result.worid}" />
                                              </td> --%>
                                               <input type="hidden" id="arrayWorkId${status.index}" value="${result.worid}" />
                                              <td><c:out value="${result.worclassify}" /></td>
                                              <td>
                                              	<%-- <c:out value="${result.worexigency}" /> --%>
                                              	<div class="meterWorexigency_${result.worexigency}">
													<span></span>
												</div>
                                              </td>
                                              <td><c:out value="${result.worcreauser}" /></td>
                                              <td style='text-align:left;'>
	                                               <div class="clickable-row-td" onmouseover="overShow(this,event)" onmouseout="outHide()">
	                                               	<c:out value="${result.wortitle}" />
	                                               </div> 
                                           	  </td>
                                              <td><c:out value="${result.worperson}" /></td>
                                              <td>
                                              	<%-- <div class="insideWorkMain_${result.worresult}"></div> --%>
                                              	<div class="meter_${result.worresult}">
													<span></span>
												</div>
												<div class="meterworresule_${result.worresult}">
												</div>
                                              </td>
                                              <td><c:out value="${result.worupdatedate}" /></td>
                                          </tr>
                                   </c:forEach>
                            	</c:if>
                                
                            </tbody>
                        </table>
                    </div>     
                    <!--//테이블 end-->  
                    <div class="paging"> 
                    	<able:paging currPage="${currPage}" jsFunction="jsPage" totalRecordCount="${totalRecordCount}" />
                	</div>
                </div>
                </form> 
            </div>
        </div>
    </div> 
     
</div>
<!--//***** REALCONTENTS END *****-->
</body>

</html>

