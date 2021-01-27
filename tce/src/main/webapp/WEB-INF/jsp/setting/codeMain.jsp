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
    $(document).ready(function(){
        $('.slimscroll').slimscroll({
            height: 350
        });
    });

    $(function(){
        //조회 버튼
        $("#btnSearch").click(function() {
            //그룹조회조건 값 설정
            $("#searchVal01").val($("#searchGroupVal").val());
            //상세조회조건 값 초기화
            $("#groupCodeId").val('');
            
            $("#frm").attr('action',"/setting/codeMain.do" );
            $('#frm').submit();
        });
      
        //그룹코드 등록버튼
        $("#btnRegist").click(function() {
            $("#frm").attr('action',"/setting/codeGroupRegistForm.do");
            $("#frm").submit();
        });
        
        //그룹코드 테이블 Row클릭
        $(".clickable-row td").click(function() {
            var index = $(this).parent().index();
            var tdIndex = this.cellIndex + 1;
            var selectGroupCodeId = $("#groupCodeIdArr"+index).val();
            $("#groupCodeId").val(selectGroupCodeId);
            //상세보기 버튼 제외
            if(tdIndex != 4){
                detailCodeList(selectGroupCodeId);
            }
        });
        
        //상세코드 등록 버튼
        $("#btnRegistDetail").click(function() {
            //그룹코드 선택 체크
            var groupCodeId = $("#groupCodeId").val();
            if(groupCodeId == ''){
                gfn_showDialog("<spring:message code='msg.group.code.choose'/>","");
                return;
            }
            
            $("#frm").attr('action',"/setting/codeDetailRegistForm.do");
            $("#frm").submit();
        });
        
        //상세코드 등록시 리스트 유지
        if('${rFlag}' == 'Y'){
            $("#groupCodeId").val('${rParam.groupCodeId}');
            detailCodeList('${rParam.groupCodeId}');
        }
        else{
            var html = "<td colspan='5'><spring:message code="msg.group.code.choose"/>.</td>";
            $("#detailCodeTable > tbody").html(html);
        }
    });
    
    //그룹코드 상세보기
    function groupCodeDetailView(groupCodeId){
        $("#groupCodeId").val(groupCodeId);
        $("#frm").attr('action',"/setting/codeGroupDetailForm.do" );
        $("#frm").submit();
    }
    
    //상세코드 리스트
    function detailCodeList(selectGroupCodeId){
        $.ajax({
            url: "/setting/codeDetailList.do",
            type: "post",
            async: true,
            dataType: "json",
            data: {
                groupCodeId: selectGroupCodeId,
            },
            beforeSend: function() {
                //gfn_createLoadingImage(tableId);
            },
            complete: function() {
                //gfn_removeLoadingImage(tableId);
            },
            success: function(data) {
                detailCodeTableSetting(data.rList);
            },
            error: function(xhr, status, error) {
                console.log(error);
            }
        });
    }
    
    //상세코드 테이블 세팅
    function detailCodeTableSetting(rList){
        var html = "";
        if(rList.length > 0){
            for (var i=0; i<rList.length; i++) {
                html = html + "<tr onClick=detailCodeTableView('" + rList[i].codeId + "');>";
                html = html + "<td>" + rList[i].groupCodeId + "</td>";
                html = html + "<td>" + rList[i].codeId      + "</td>";
                html = html + "<td>" + rList[i].codeNm      + "</td>";
                html = html + "<td>" + rList[i].codeSortNo  + "</td>";
                html = html + "<td>" + rList[i].useYn       + "</td>";
                html = html + "</tr>";
            }
        }
        else{
            html = "<td colspan='5'><spring:message code="msg.info.search.nofound"/></td>";
        }
        $("#detailCodeTable > tbody").html(html);
    }
    
    //상세코드 상세보기
    function detailCodeTableView(selectCodeId){
        $("#codeId").val(selectCodeId);
        $("#frm").attr('action',"/setting/codeDetailForm.do" );
        $("#frm").submit();
    }
</script>

<form id="frm" name="frm" method="post">
    <input type="hidden" name="searchVal01" id="searchVal01" />
    <input type="hidden" name="groupCodeId" id="groupCodeId" />
    <input type="hidden" name="codeId"      id="codeId"      />
</form>

<div class="wrapper">
    <div class="page">
        <div class="ribbon">
            <h1 class="title">
                <spring:message code="menu.admin.code.mgmt"/>
            </h1>
        </div>
        <div class="page-content">
            <div class="container-fluid">    
                <div class="search-box">
                    <ul class="form-group">
                        <li>
                            <label><spring:message code="lbl.group.code_name"/></label>
                            <input id="searchGroupVal" class="form-control" style="width: 200px;" size="20" />
                        </li>
                        <li>
                            <!-- <a class="btn btn-primary" href="#" id="btnSearch" type="button">Search</a> -->
                        	<span id="btnSearch" class="btnsch"><span><spring:message code="btn.search"/></span></span>
                        </li>
                    </ul>
                </div>
                <div class="row" style="width: 49%; margin: 1px; float: left; padding-top: 10px;">
                    <div class="title-wrap">
                        <h2 class="board-title"><spring:message code="lbl.group.code"/></h2>
                        <div class="board-top">
                            <a class="btn  btn-primary-line" href="#" id="btnRegist" type="button"><spring:message code="btn.regist"/></a>
                        </div>
                    </div>
                    <div class="table-wrap">
                        <table class="table">
                            <colgroup>
                                <col style="width: 20px">
                                <col style="width: 50px">
                                <col style="width: 15px">
                                <col style="width: 20px">
                            </colgroup>
                            <thead>
                                <tr>
                                    <th><spring:message code="lbl.group.code"/></th>
                                    <th><spring:message code="lbl.group.name"/></th>
                                    <th><spring:message code="lbl.use_yn"/></th>
                                    <th><spring:message code="lbl.detail"/></th>
                                </tr>
                            </thead>
                        </table>
                    </div>
                    <div class="slimscroll">
                        <div class="tbody-wrap">
                            <table class="table" id="groupCodeTable" >
                                <colgroup>
                                    <col style="width: 20px">
                                    <col style="width: 50px">
                                    <col style="width: 15px">
                                    <col style="width: 20px">
                                </colgroup>
                                <tbody>
                                    <c:if test="${not empty rList}">
                                        <c:forEach var="code" items="${rList}" varStatus="status">
                                            <tr class='clickable-row'>
                                                <input type="hidden" id="groupCodeIdArr${status.index}" value="${code.groupCodeId}" />
                                                <td><c:out value="${code.groupCodeId}" /></td>
                                                <td><c:out value="${code.groupCodeNm}" />
                                                <td><c:out value="${code.useYn}" /></td>
                                                <td><button class="btn  btn-primary-line" type="button" onclick="groupCodeDetailView('${code.groupCodeId}')"><spring:message code="btn.show"/></button></td>
                                            </tr>
                                        </c:forEach>
                                    </c:if>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
                <div class="row" style="width: 49%; margin: 1px; float: right; padding-left: 20px; padding-top: 10px;">
                    <div class="title-wrap">
                        <h2 class="board-title"><spring:message code="lbl.detailcode.code"/></h2>
                        <div class="board-top">
                            <a class="btn  btn-primary-line" href="#" id="btnRegistDetail" type="button"><spring:message code="btn.regist"/></a>
                        </div>
                    </div>
                    <div class="table-wrap">
                        <table class="table">
                            <colgroup>
                                <col style="width: 4px;">
                                <col style="width: 4px;">
                                <col style="width: 8px;">
                                <col style="width: 3px;">
                                <col style="width: 3px;">
                            </colgroup>
                            <thead>
                                <tr>
                                    <th><spring:message code="lbl.group.code"/></th>
                                    <th><spring:message code="lbl.detailcode.code"/></th>
                                    <th><spring:message code="lbl.detailcode.name"/></th>
                                    <th><spring:message code="lbl.order"/></th>
                                    <th><spring:message code="lbl.use_yn"/></th>
                                </tr>
                            </thead>
                        </table>
                    </div>
                    <div class="slimscroll">
                        <div class="tbody-wrap">
                            <table id="detailCodeTable" class="table">
                                <colgroup>
                                    <col style="width: 4px;">
                                    <col style="width: 4px;">
                                    <col style="width: 8px;">
                                    <col style="width: 3px;">
                                    <col style="width: 3px;">
                                </colgroup>
                                <tbody id="detailCodeBody">
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
