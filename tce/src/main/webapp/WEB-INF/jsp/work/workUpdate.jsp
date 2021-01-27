<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/include/declare.jspf" %>
<script src="/js/My97DatePicker/WdatePicker.js"></script>
<script src="/js/com/work_common.js"></script>
<%-- 
    JSP Name : jspName.jsp
    修改页面
--%>

<script type="text/javaScript"> 
$(function(){
    if($("#excelFileName").val() != ""){
        propertiesChk();
    }
    
    $("#btnFileDel").click(function(){
        $("#excelFileName").val('');
        propertiesUnChk();
    });
});

function propertiesChk() 
{
    $("#sendCar").val("");
    $("#sendCar").attr("readonly", true);
    $("#btnvehicleSearch").val("");
    $("#btnvehicleSearch").prop("disabled", true);
    $("#sendProductSdtm").val("");
    $("#sendProductSdtm").attr("readonly", true).attr("disable", true);
    $("#sendProductEdtm").val("");
    $("#sendProductEdtm").attr("readonly", true).attr("disable", true);
    $("#sendSalesSdtm").val("");
    $("#sendSalesSdtm").attr("readonly", true).attr("disable", true);
    $("#sendSalesEdtm").val("");
    $("#sendSalesEdtm").attr("readonly", true).attr("disable", true);
    $("#two-double-div").attr("style","display:none;");
    $("#showDiv").attr("style","display:none;");
    if($("#userAuthid").val() != "super"){
    	$("#btndeleteA").attr("style","display:none;");
    }
    if($("#userId").val() != $("#worCreauser").val()){
    	$("#btnupdateworkUpdatetwo").attr("style","display:none;");
    } 
    $(".ui-datepicker-trigger").prop("disabled", true);
}

function propertiesUnChk() 
{
    $("#sendCar").val("");
    $("#sendCar").attr("readonly", true);
    $("#btnvehicleSearch").val("");
    $("#btnvehicleSearch").prop("disabled", false);
    $("#sendProductSdtm").val("");
    $("#sendProductSdtm").attr("readonly", false).attr("disable", false);
    $("#sendProductEdtm").val("");
    $("#sendProductEdtm").attr("readonly", false).attr("disable", false);
    $("#sendSalesSdtm").val("");
    $("#sendSalesSdtm").attr("readonly", false).attr("disable", false);
    $("#sendSalesEdtm").val("");
    $("#sendSalesEdtm").attr("readonly", false).attr("disable", false);
    $("#two-double-div").attr("style","display:none;");
    $("#showDiv").attr("style","display:none;");
    $(".ui-datepicker-trigger").prop("disabled", false);
}

function validation(){
    if($('#exaDisposeData').val() == ''){
        alert("<spring:message code='msg.input.work.date'/>");
        $("#exaDisposeData").focus();
        return false;
    }else if($('#exaResult').val() == ''){
        alert("<spring:message code='msg.input.work.content'/>");
        $("#exaResult").focus();
        return false;
    }else if( $('#exaDisposeContent').val() == ''){
        alert("<spring:message code='msg.input.work.withcontent'/>");
        $("#exaDisposeContent").focus();
        return false;
    }else if( $('#exaResult').val() == 'D'){
    	 var customername='<%=request.getAttribute("userAuthid")%>';
    	 if(customername != "super"){
    		 alert("<spring:message code='msg.input.work.errorid'/>");
    	     $("#exaResult").focus();
    	     return false; 
    	 }
    	 return true;
    }
    else return true;
}

function fn_examineUpdate() {//
    //check validation.
    if(!validation()) return;
    $("#workRegistForm").attr('action',"/work/workUpdate.do" );
    $("#workRegistForm").submit(); 
    $("#two-double-div").attr("style","display:none;");
}

function fn_examineSave() {//点击添加按钮
	$("#two-double-div").attr("style","display:block;");
	$("#two-double-div-list").attr("style","display:none;");
}

function overShow(obj,e) {}
function outHide() {}
 //目录按钮
   function fn_workMain() 
   {
	   $("#worPerson").val("");
	   $("#worResult").val("");
	   $("#worClassify").val("");
       document.workRegistForm.pageGbn.value = "update";
       document.workRegistForm.action = "<c:url value='/work/workMain.do'/>";
       document.workRegistForm.submit();
   }
   /**
    * 删除
    */
   function fn_delete() {
	   $("#worPerson").val("");
	   $("#worResult").val("");
	   $("#worClassify").val("");
       if($('#worId').val() == 0){
           alert("<spring:message code='msg.select.del.work'/>");
           return;
       }
       if(!confirm("<spring:message code='msg.confirm_delete'/>")) return;
       $('#worId').val();
       $("#workRegistForm").attr('action',"/work/workDelete.do" );
       $("#workRegistForm").submit();
       
   }

   function fn_searchTakeUser() {
       var url= "/work/pop/vehicleCodePopup.do";    //팝업창 페이지 URL
       var winWidth = 700;
       var winHeight = 600;
       var popupOption= "width="+winWidth+", height="+winHeight;    //팝업창 옵션(optoin)
       
       window.open(url,"_blank",popupOption).focus();
   }
</script>
<!--***** REALCONTENTS START *****-->
<div class="wrapper">
    <div class="page">
        <div class="ribbon">
            <div class="title"><spring:message code="lbl.title.noti.update"/></div>
        </div>
        <div class="page-content">
            <div class="container-fluid">
                <form name="workRegistForm" id="workRegistForm" enctype="multipart/form-data" method="post">
                    <input type="hidden" name="worId" id="worId" value="${workMgrVO.worId}"/>
                    <input type="hidden" name="exaId" value="" id="exaId"/>
                    <input type="hidden" name="pageGbn" value="" id="pageGbn"/>
                    <input type="hidden" name="userId" id="userId" value="${userId}"/>
                   <%--  <input type="hidden" name=worPerson id="worPerson" value="${workMgrVO.worPerson}" /> --%>
                    <input type="hidden" name="worCreauser" id="worCreauser" value="${workMgrVO.worCreauser}" />
                    <input type="hidden" name=userAuthid id="userAuthid" value="${userAuthid}" />
				<div class="row">
					<div class="board-top">
	                        <a id="btnupdateworkUpdatetwo" href="javascript:fn_workUpdatetwo_doublediv();" class="btn btn-primary-line"><spring:message code="btn.update"/></a> 
	                        <a id="btndeleteA" href="#" class="btn btn-primary-line" onclick="fn_delete();"><spring:message code="btn.delete"/></a>
	                        <a href="javascript:fn_workMain();" class="btn btn-primary"><spring:message code="btn.list"/></a>
	                </div>
					<!--테이블 start-->
					<div class="table-wrap">    
							<table class="table table-view">
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
												   <input type="text" class="form-control" style="width: 150px" name="worPerson" id="worPerson" value="${workMgrVO.worPerson}" readonly="readonly"/>
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
							<!--//테이블 end-->
					</div>
				</div>
				
				<div class="row" id="two-double-div">
	                <div class="board-top">
	                        <a href="javascript:fn_examineUpdate();" class="btn btn-primary-line"><spring:message code="btn.save"/></a> 
	                </div>
					<!--테이블 start-->
					<div class="table-wrap">    
                        <table class="table table-view">
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
               </form>
                <div class="row" id="two-double-div-list">
                	<div class="board-top">
	                        <a href="javascript:fn_examineSave();" class="btn btn-primary-line"><spring:message code="btn.regist"/></a> 
	                </div>
                    <!--테이블 start-->
                    <div class="table-responsive table-wrap">
                        <table class="table">
                            <colgroup>
                                <col style="width: 80px">
                                <col style="width: 130px">
                                <col style="width: 450px">
                                <col style="width: auto">
                                <col style="width: 130px">
                                <col style="width: 100px"> 
                            </colgroup>
                            <thead>
                                <tr>
                                    <th><spring:message code="lbl.work.exatakeuser"/></th>
                                    <th><spring:message code="lbl.work.exadisposedata"/></th>
                                    <th colspan="2"><spring:message code="lbl.work.exadisposecontent"/></th>
                                    <th><spring:message code="lbl.work.exasavedate"/></th>
                                    <th><spring:message code="lbl.work.exaresult"/></th>
                                </tr>
                            </thead>
                            <tbody> 
                                <tr>
                                    <c:choose>
                                        <c:when test="${empty resultExamineList}">
                                            <tr>
                                                <td colspan="6" align="center"><spring:message code="msg.search.data.empty"/></td>
                                            </tr>
                                        </c:when>
                                        <c:otherwise>
                                            <c:forEach var="result" items="${resultExamineList}" varStatus="status">
                                                <tr class='clickable-row'>
                                                    <td><c:out value="${result.exaTakeuser}" /></td>
                                                    <td><c:out value="${result.exaDisposedata}" /></td>
                                                    <td colspan="2" style='text-align:left;'>
                                                    <div class="clickable-row-td" onmouseover="overShow(this,event)" onmouseout="outHide()">
                                                    	<c:out value="${result.exaDisposeContent}" />
                                                    </div>
                                                    </td>
                                                    <td><c:out value="${result.exaSavedata}" /></td>
                                                    <td><c:out value="${result.worresult}" /></td>
                                                </tr>
                                            </c:forEach>
                                        </c:otherwise>
                                    </c:choose>
                                </tr>
                            </tbody>
                        </table>
                    </div>     
                </div>
             </div>
				
			</div>
        </div>
    </div> 
    <!-- <div id="showDiv" class="wortitle-updateshouDiv"></div> -->
</div> 