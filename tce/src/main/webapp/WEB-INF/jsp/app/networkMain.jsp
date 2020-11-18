
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/include/declare.jspf" %>
<script src="/js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javaScript" language="javascript"> 
$(function(){
	$("#showDiv").attr("style","display:none;");
	/**
	 * 选中数据跳到修改页面
	 */
	$(".clickable-row td").click(function() {
	    var index = $(this).parent().index();
	    /* $('#worId').val($("#arrayWorkId"+index).val());
	    $('#exaId').val($("#arrayWorkId"+index).val());
	    $('#notiForm').attr('action',"/work/workDetailForm.do");
	    $('#notiForm').submit(); */
	});
});
function goSearch() {
	if($('#networkDateSearch').val() == ''){
        alert("<spring:message code='msg.input.work.date'/>");
        $("#networkDateSearch").focus();
        return;
    }
    document.networkForm.action = "<c:url value='/app/networkMainList.do'/>";
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
    	<div class="ribbon">
            <h1 class="title"><spring:message code="lbl.title.app.network"/></h1>
        </div>
        <div class="page-content">
            <div class="container-fluid">
                <form id="networkForm" name="networkForm" enctype="multipart/form-data" method="post">
                <div class="search-box">
                    <ul class="form-group">
                        <li>       
                        	<label for=""><span class="require">*</span><spring:message code="lbl.app.monitoring.serchdata"/></label>
                            <input type="text" class="Wdate" name="networkDateSearch" id="networkDateSearch" value="${queryDate}"
                            onFocus="WdatePicker({lang:'zh-cn',dateFmt:'yyyy-MM-dd HH:mm:ss',readOnly:true,})"/>
                        </li>
                        <li> 
                            <div class="btn_div">
                                <a href="#"  class="btn btn-primary" onclick="goSearch();" id="btnSearch" name="btnSearch"><spring:message code="btn.search"/></a>
                            </div>
                       </li>
                   </ul>  
                </div>
                <div class="search-box-monitoring-data" style="width: 260px;">
                    <ul class="form-group-monitoring-data" >
                        <li>       
                        	<label for=""><spring:message code="lbl.app.monitoring.update.date"/></label>
                        	<label >${resultListNet.get(0).rgstDtm}</label>
                        </li>
                   </ul>  
                </div>
                <div class="row">
                    <!--테이블 start-->
                    <div class="table-responsive table-wrap">
                        <table class="table">
                            <tbody>
                            <div class="account-l fl">
	                           		<ul id="accordion" class="accordion">
	                           		<c:forEach var="result" items="${resultListNet}" varStatus="status">
										<li>
											<div class="link"><i class="fa fa-chevron-down"></i>
												<div class="inside1"><c:out value="${result.srcSvrNm}" />&nbsp;-->&nbsp;<c:out value="${result.tgtSvrNm}" /></div>
												<div class="inside2">ping</i></div>
												<div class="inside_${result.pingResult}"></div>
												<div class="inside3">talnat</div>
												<div class="inside_${result.telnetResult}"></div>
											</div>
											
											<ul class="submenunet">
												<li><a>srcSvrIp&nbsp;&nbsp;:&nbsp;&nbsp; ${result.srcSvrIp}(${result.srcSvrNm})</a></li>
												<li><a>tgtSvrIp&nbsp;&nbsp;:&nbsp;&nbsp;${result.tgtSvrIp}(${result.tgtSvrNm})</a></li>
												<li><a>pingResult&nbsp;&nbsp;:&nbsp;&nbsp;<div class="inside_${result.pingResult}"></div></a></li>
												<li><a>pingContent&nbsp;&nbsp;:&nbsp;&nbsp;${result.pingContent}</a></li>
												<li><a>telnetResult&nbsp;&nbsp;:&nbsp;&nbsp;<div class="inside_${result.telnetResult}"></div></a></li>
												<li><a>telnetContent&nbsp;&nbsp;:&nbsp;&nbsp;${result.telnetContent}</a></li>
											</ul>
										</li>
									</c:forEach>
									</ul>
                           		</div>
                            
                            </tbody>
                        </table>
                    </div>     
                    
                </div>
                </form> 
            </div>
        </div>
    </div> 
      
</div>
