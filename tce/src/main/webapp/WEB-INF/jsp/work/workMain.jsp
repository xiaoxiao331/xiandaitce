<html>
<head>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/include/declare.jspf" %>
<script src="/js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="/js/export.js"></script>
<script type="text/javascript" src="/js/xlsx.extendscript.js"></script>
<script type="text/javascript" src="/js/xlsx.full.min.js"></script>
<title></title>
<%-- 
        工单信息展示
--%>
<script type="text/javaScript" language="javascript"> 

$(function(){
	/* $("#worPerson").val(""); */
	/**
	 * 选中数据跳到修改页面
	 */
	$(".clickable-row td").click(function() {
	    var index = $(this).parent().index();
	    $('#worId').val($("#arrayWorkId"+index).val());
	    $('#exaId').val($("#arrayWorkId"+index).val());
	    $('#workRegistForm').attr('action',"/work/workDetailForm.do");
	    $('#workRegistForm').submit();
	}); 
	// EXPOET EXCEL
    $("#export").on("click", function() {
        lfn_excelList();
    });
});


function goSearch() {
    document.workRegistForm.action = "<c:url value='/work/workMain.do'/>";
    document.workRegistForm.submit();
}
    
function fn_regist()
{
    var frm = document.workRegistForm;
    frm.target = "_self";
    frm.action = "<c:url value='/work/workRegistForm.do'/>";
    frm.submit();
}
function jsPage(page) {
    document.workRegistForm.currPage.value = page;
    goSearch(); 
}
function overShow(obj,e) {}
function outHide() {}
/**
 * asset 导出
 */
function lfn_excelList() {
    var table1 = document.querySelector("#assetStatisTable");
    var sheet = XLSX2.utils.table_to_sheet(table1);
    var time=generateTimeReqestNumber();
    var xlsxName = "<spring:message code="menu.statis.asset.info" />";    
    openDownloadDialog(sheet2blob(sheet),xlsxName+time+".xlsx");
}
</script>
 </head>
 <body> 
<!--***** REALCONTENTS START *****-->
<div class="wrapper" id="app">
    <div class="page">
        <%-- <div class="ribbon">
        	 <h1 class="title">
           		 <spring:message code="lbl.title.noti.search"/>
            </h1>
        </div> --%>
        <div class="page-content">
            <div class="container-fluid">
                <form id="workRegistForm" name="workRegistForm" method="post">
                <input type="hidden" name="worId" value="" id="worId"/>
                <input type="hidden" name="selectedBrdSn" />
                <input type="hidden" name="currPage" value=""/>
                <input type="hidden" name="exaId" value="" id="exaId"/>
                <div class="search-box">
                    <ul class="form-group">
                    	<li>
	                        <label><spring:message code="lbl.worPerson"/></label>
                        	<select class="select" id="worPerson" name="worPerson" style="width:130px;" >
                               	  <option selected="selected" value=""><spring:message code="lbl.total" /></option>   
                                  <c:forEach var="list" items="${resultcdListPro}">
                                      <option value="${list.userId}"<c:if test="${list.userId == workMgrVO.worPerson}">selected</c:if> > 
                                      ${list.userId}</option>
                                  </c:forEach>
                              </select>
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
                                <%-- <a href="#"  class="btn btn-primary" title="조회" id="btnSearch" name="btnSearch" onclick="goSearch();"><spring:message code="btn.search"/></a> --%>
                            	<span onclick="goSearch();" class="btnsch"><span><spring:message code="btn.search"/></span></span>
                            </div>
                       </li>
                   </ul>  
                   <ul class="form-groupl">
                   		<li> 
                   			<label><spring:message code="lbl.urgency"/></label>
                             <input type="checkbox" class="Wdate1" name="searchExigency" id="" value="A" ${workMgrVO.searchExigency eq "'A'" ? 'checked="checked"' : ''}/>
                   				<label><spring:message code="code.mt.work.low"/></label>             
                             <input type="checkbox" class="Wdate1" name="searchExigency" id="" value="B" ${workMgrVO.searchExigency eq "'B'" ? 'checked="checked"' : ''}/>
                             	<label><spring:message code="code.mt.work.center"/></label>          
                             <input type="checkbox" class="Wdate1" name="searchExigency" id="" value="C" ${workMgrVO.searchExigency eq "'C'" ? 'checked="checked"' : ''}/>
                             	<label><spring:message code="code.mt.work.tall"/></label>
                             <input type="checkbox" class="Wdate1" name="searchExigency" id="" value="D" ${workMgrVO.searchExigency eq "'D'" ? 'checked="checked"' : ''}/>
                       			<label><spring:message code="code.mt.work.urgency"/></label>
                       
                       </li>
                   </ul>
                </div>
                <!--// 검색박스 end-->
                <div class="row">
                    <div>
                    	<h2 class="board-title"><spring:message code="lbl.title.noti.search"/></h2>
                    	<div class="board-top">
                    		<a href="#" class="btn btn-primary" id="export" name="export"><spring:message code="btn.excel"/></a>
                    		<a href="#" class="btn btn-primary" onclick="fn_regist();"><spring:message code="btn.regist"/></a>
                    	</div>
                    </div>
                    <!--테이블 start-->
                    <div class="table-responsive table-wrap">
                        <table id="assetStatisTable" class="table table-hover">
                            <colgroup>
                                <col style="width: 30px">
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
                                    <th><spring:message code="lbl.noti.id"/></th>
                                    <th><spring:message code="lbl.noti.type"/></th>
                                    <th><spring:message code="lbl.urgency"/></th>
                                    <th><spring:message code="lbl.work.worCreauser"/></th>
                                    <th><spring:message code="lbl.work.worTitle"/></th>
                                    <th><spring:message code="lbl.worPerson"/></th>
                                    <th><spring:message code="lbl.sendyn"/></th>
                                    <th><spring:message code="lbl.prd.date2"/></th>
                                </tr>
                            </thead>
                            <tbody> 
                            	<c:if test="${not empty resultList}">
                            		<c:forEach var="result" items="${resultList}" varStatus="status">
                                          <tr class='clickable-row'>
                                              <td><c:out value="${result.worid}" /><input type="hidden" id="arrayWorkId${status.index}" value="${result.worid}" /></td>
                                              <td><c:out value="${result.worclassify}" /></td>
                                              <td><c:out value="${result.worexigency}" /></td>
                                              <td><c:out value="${result.worcreauser}"/></td>
                                              <td style='text-align:left;'>
	                                               <div class="clickable-row-td" onmouseover="overShow(this,event)" onmouseout="outHide()">
	                                               	<c:out value="${result.wortitle}" />
	                                               </div> 
                                           	  </td>
                                              <td><c:out value="${result.worperson}" /></td>
                                              <td><c:out value="${result.worresult}" /></td>
                                              <td><c:out value="${result.worinthedate}" /></td>
                                          </tr>
                                   </c:forEach>
                            	</c:if>
                            	<c:if test="${empty resultList}">
                            		<tr>
                                       <td colspan="8" align="center"><spring:message code="msg.search.data.empty"/></td>
                                    </tr>
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
    
 <!-- /***************/ -->
 <div class="page-content">
    <table>
       <tr>
          <td width="49%">
          		<div class="board-top">
          		<table width="100%">
                    <tbody><tr>
                        <td class="board-titlepu">工单信息</td>
                        </td>
                        <td width="60px" align="right">
                            <span onclick="excel_down_popup(&#39;QUD058&#39;);" class="btn btn-primary"><span>修改</span></span>
                        </td>
                    </tr>
                </tbody></table>
                </div>
             	<!--테이블 start-->		
				<div class="table-wrap">    
						<table class="table table-viewpu">
							<colgroup>
								<col style="width: 90px">
								<col style="width: 10%">
								<col style="width: 20%">
								<col style="width: 80px">
								<col style="width: 25%">
								<col style="width: 80px">
								<col style="width: *">
							</colgroup>  
							<tbody>
								<tr>
									<th><spring:message code="lbl.title"/></th>
									<td colspan="6"><input type="text" class="form-control" name="worTitle" id="worTitle" value="${workMgrVO.worTitle}"></td>
								</tr>
								 <tr>
									<th><spring:message code="lbl.noti.content"/></th>
									<td colspan="6">
										<textarea class="form-control" name="worDatail" id="worDatail">${workMgrVO.worDatail}</textarea>
									</td> 
								</tr>
								<tr>
									<th><spring:message code="lbl.noti.type"/></th>
									<td colspan="2">
										<select class="select" name="worClassify" id="worClassify">
											<option selected="selected" value=""><spring:message code="lbl.total" /></option>
											<option value="A" ${workMgrVO.worClassify eq 'A' ? 'selected="selected"' : ''}><spring:message code="code.mt.work.operati"/></option>
											<option value="B" ${workMgrVO.worClassify eq 'B' ? 'selected="selected"' : ''}><spring:message code="code.mt.work.wxwork"/></option>
											<option value="C" ${workMgrVO.worClassify eq 'C' ? 'selected="selected"' : ''}><spring:message code="code.mt.work.emergency"/></option>
											<option value="D" ${workMgrVO.worClassify eq 'D' ? 'selected="selected"' : ''}><spring:message code="code.mt.work.improvement"/></option>
											<option value="E" ${workMgrVO.worClassify eq 'E' ? 'selected="selected"' : ''}><spring:message code="code.mt.work.functional"/></option>
										</select> 
									</td>
									<th><spring:message code="lbl.urgency"/></th>
									<td>
										<select class="select" name="worExigency" id="worExigency" >
											<option selected="selected" value=""><spring:message code="lbl.total" /></option>
											<option value="A" ${workMgrVO.worExigency eq 'A' ? 'selected="selected"' : ''}><spring:message code="code.mt.work.urgency"/></option>
											<option value="B" ${workMgrVO.worExigency eq 'B' ? 'selected="selected"' : ''}><spring:message code="code.mt.work.tall"/></option>
											<option value="C" ${workMgrVO.worExigency eq 'C' ? 'selected="selected"' : ''}><spring:message code="code.mt.work.center"/></option>
											<option value="D" ${workMgrVO.worExigency eq 'D' ? 'selected="selected"' : ''}><spring:message code="code.mt.work.low"/></option>
										</select>
									</td>
								</tr>
								<tr>
									<th><spring:message code="lbl.prd.date"/></th>
										<td colspan="2">
											<input type="text" class="Wdate" name="worGeginDate" id="worGeginDate" title=""
											 onFocus="WdatePicker({lang:'zh-cn',dateFmt:'yyyy-MM-dd HH:mm:ss',readOnly:true})" value="${workMgrVO.worGeginDate}" />
										</td>
									 <th><spring:message code="lbl.prd.date2"/></th>
										<td colspan="2">
											<input type="text" class="Wdate" name="worIntheDate" id="worIntheDate" title="" 
											onFocus="WdatePicker({lang:'zh-cn',dateFmt:'yyyy-MM-dd HH:mm:ss',readOnly:true})" value="${workMgrVO.worIntheDate}" />
										</td>
								</tr>
								<tr>
									<th><spring:message code="lbl.take"/></th>
									   <td colspan="2">
										   <div class="input-group">
											   <input type="text" class="form-control" style="width: 60px" name="worPerson" id="worPerson" value="${workMgrVO.worPerson}" readonly="readonly"/>
										   		<a href="#" class="btn btn-primary" style="margin-top: 6px;" onclick="fn_searchTakeUser();"><spring:message code="lbl.cartake.search"/></a>
										   </div>  
									   </td>
										<th><spring:message code="lbl.work.progress"/></th>
									   <td>
										   <select class="select" name="worRate" id="worRate" >
											   <option selected="selected" value=""><spring:message code="lbl.total" /></option>
											   <option value="A" ${workMgrVO.worRate eq 'A' ? 'selected="selected"' : ''}><spring:message code="code.progress"/></option>
											   <option value="B" ${workMgrVO.worRate eq 'B' ? 'selected="selected"' : ''}><spring:message code="code.progress0"/></option>
											   <option value="C" ${workMgrVO.worRate eq 'C' ? 'selected="selected"' : ''}><spring:message code="code.progress1"/></option>
											   <option value="D" ${workMgrVO.worRate eq 'D' ? 'selected="selected"' : ''}><spring:message code="code.progress2"/></option>
											   <option value="E" ${workMgrVO.worRate eq 'E' ? 'selected="selected"' : ''}><spring:message code="code.progress3"/></option>
											   <option value="F" ${workMgrVO.worRate eq 'F' ? 'selected="selected"' : ''}><spring:message code="code.progress4"/></option>
											   <option value="G" ${workMgrVO.worRate eq 'G' ? 'selected="selected"' : ''}><spring:message code="code.progress5"/></option>
											   <option value="H" ${workMgrVO.worRate eq 'H' ? 'selected="selected"' : ''}><spring:message code="code.progress6"/></option>
											   <option value="I" ${workMgrVO.worRate eq 'I' ? 'selected="selected"' : ''}><spring:message code="code.progress7"/></option>
											   <option value="Z" ${workMgrVO.worRate eq 'Z' ? 'selected="selected"' : ''}><spring:message code="code.progress8"/></option>
											   <option value="K" ${workMgrVO.worRate eq 'K' ? 'selected="selected"' : ''}><spring:message code="code.progress9"/></option>
										   </select>
										</td>
								</tr>
							</tbody>
						</table>
				</div>
				<!--//테이블 end-->
        	</td>
        	<td width="2%"></td>
        	<td valign="top">
        		<div class="row" id="two-double-div">
	                <div class="board-top">
	                	<table width="100%">
		                    <tbody><tr>
		                        <td class="board-titlepu">处理窗口</td>
		                        </td>
		                        <td width="60px" align="right">
		                            <span onclick="excel_down_popup(&#39;QUD058&#39;);" class="btn btn-primary"><span>添加</span></span>
		                        </td>
		                    </tr>
		                </tbody></table>
	                </div>
					<!--테이블 start-->
					<div class="table-wrap">    
                        <table class="table table-viewpu">
                            <colgroup>
                                <col style="width: 90px">
                                <col style="width: 10%">
                                <col style="width: 20%">
                                <col style="width: 80px">
                                <col style="width: 25%">
                                <col style="width: 80px">
                                <col style="width: *">
                            </colgroup>  
                            <tbody>
                                <tr>
                                	<th><span class="require">*</span><spring:message code="lbl.success.vin.cnt"/></th>
                                        <td colspan="2">
                                            <input type="text" class="Wdate" name="exaDisposeData" id="exaDisposeData" title="" onFocus="WdatePicker({lang:'zh-cn',dateFmt:'yyyy-MM-dd HH:mm:ss',readOnly:true})"/>
                                        </td>
                                    <th><span class="require">*</span><spring:message code="lbl.sendyn"/></th>
                                    <td colspan="2">
                                        <select class="select" name="exaResult" id="exaResult" >
                                            <option selected="selected" value=""><spring:message code="lbl.total" /></option>
                                        	<option value="A"><spring:message code="code.mt.receive"/></option>
                                            <option value="B"><spring:message code="code.mt.processing"/></option>
                                            <option value="C"><spring:message code="code.mt.complete"/></option>
                                            <option value="D"><spring:message code="code.mt.end"/></option>
                                        </select> 
                                    </td>
                                </tr>
                                 <tr>
                                    <th><span class="require">*</span><spring:message code="lbl.sendyn.content" /></th>
                                    <td colspan="6">
                                        <textarea maxlength="500" class="form-control" name="exaDisposeContent" id="exaDisposeContent" ></textarea>
                                    </td> 
                                </tr>
                            </tbody>
                        </table>
                        <!--//테이블 end-->
                    </div>
                </div>
        	</td>
    	</tr>
    </table>
	
</div>
 <!-- /***************/ -->
     
</div>
<!--//***** REALCONTENTS END *****-->
</body>

</html>

