<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%-- 
	Class Name  :  
	Description : Main Page 
	author	  : LMC
	since	   : 2019.04.26 

	Modification Information
	수정일		 수정자		수정내용 
	----------   --------   ----------------------------- 
 	2019-07-03	 홍성욱		대시보드 UI 구성 및 차트 적용
--%>




<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="java.util.*"%>



<style>
	.main-row-title {font-size:21px; color:#222222; margin-bottom:3px; text-align:center;}
	.main-row-subtitle {font-size:14px; float:right; margin:0px 10px 4px 0px;}
	.main-table-wrap {border-top:2px solid #4F5F6F; margin:0px 10px; clear:both;}
	.main-div-row-full {width:100%; display:inline-block; margin-bottom:30px;}
	.main-div-row-left {width:50%; float:left;}
	.main-div-row-right {width:50%; float:right;}
</style>


    <script>
      function gradeChange(vehl){
            lfn_getAssetChart(vehl);
        }
    </script>





<script type="text/javaScript" language="javascript">
	var period = 6; // 보여줄 개월 수 설정
	var startDate = gfn_getAgoMonth(0,-period+1);
	var endDate = gfn_getAgoMonth(0,0);
    var a = <%request.getSession().getAttribute("list");%>
	/* 화면 초기 Function */
	jQuery(document).ready(function() {
		lfn_getSmsChart();
		lfn_getDigiKeyChart();
		lfn_getAssetChart();
		lfn_getNoticeList();
		vehlcdquery();
	});
	
	
	 
	
	function lfn_showCorp() {
        jQuery("#showMain").submit();
	}
</script>

<div class="wrapper">
	<div class="page">
		<div class="ribbon">
			<h1 class="title"><spring:message code="menu.main.dashboard"/></h1>
		</div>
		 
		
	</div>
</div>
