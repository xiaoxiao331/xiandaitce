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

<script type="text/javaScript" language="javascript" >

    $(function(){
        //조회 버튼
        $("#btnSearch").click(function() {
            $("#pageIndex").val(1);
            $('#frm').attr('action',"/setting/userMain.do" );
            $('#frm').submit();
        });
        
        //등록 버튼
        $("#btnRegist").click(function() {
            $('#frm').attr('action',"/setting/userRegistForm.do" );
            $('#frm').submit();
        });
        
        //체크박스 전체 선택/해제
        $("input:checkbox[name='selectAll']").change(function(){
            var checkVal = $(this).is(":checked");
            $("input:checkbox[name='selectBoxArr']").each(function(){
                this.checked = checkVal;
            });
        });
        
        //상세 보기 클릭
        $(".clickable-row td").click(function() {
            var index = $(this).parent().index();
            var tdIndex = this.cellIndex + 1;
            //체크박스 선택시 상세 이동 방지를 위한 값(tdIndex)
            if(tdIndex != "1"){
                $('#userId').val($("#arrayUserId"+index).val());
                $('#frm').attr('action',"/setting/userDetailForm.do");
                $('#frm').submit();
            }            
        });
        
        //암호잠김 해제 버튼
        $("#btnRelease").click(function() {
            var list  = [];
            $.each($("input[name='selectBoxArr']:checked"), function() {
                list.push($(this).val()); 
            });
            
            if(list.length == 0) {
                gfn_showDialog("<spring:message code='msg.check.user.unlock'/>","");
                return;
            }
            if(!confirm("<spring:message code='msg.confirm.user.unlock'/>")) return;
            
            $("#userId").val(list.join(","));
            $('#frm').attr('action',"/setting/userRelease.do");
            $('#frm').submit();
        });
    });
    
  //appscan
    function userIdCheck (){
        
        var userId = $("#searchVal01").val();
        if(userId != ""){
            var regex = /^[0-9a-zA-Z]*$/g;
            var result = regex.test(userId);
            if(result){
                return;
            }else{
                alert("请输入正确的用户ID！");
            }
        }
      
  }
  //appscan

</script>

<form id="frm" name="frm" method="post">
    <!-- 검색조건 유지 Start -->
    <input type="hidden" name="pageIndex" id="pageIndex" value="${rParam.pageIndex}" />
    <!-- 검색조건 유지 End -->
    <input type="hidden" name="userId"    id="userId" value="" />
    
    <div class="wrapper">
        <div class="page">
            <div class="ribbon">
                <h1 class="title">
                    <spring:message code="menu.admin.user.mgmt"/>
                </h1>
            </div>
            <div class="page-content">
                <div class="container-fluid">
                    <div class="search-box">
                        <ul class="form-group">
                            <li><label><spring:message code="lbl.user_id"/></label>
                                <input class="form-control" id="searchVal01" name="searchVal01" style="width: 250px;" type="text" value="${rParam.searchVal01}" onblur="userIdCheck()"></li>
                            <li><label><spring:message code="lbl.user_name"/></label>
                                <input class="form-control" name="searchVal02" style="width: 250px;" type="text" value="${rParam.searchVal02}" ></li>
                            <li><label><spring:message code="lbl.auth"/></label>
                                <select class="select" name="searchVal03">
                                    <option selected="selected" value=""><spring:message code="code.all"/></option>
                                    <c:if test="${not empty authList}">
                                        <c:forEach var="auth" items="${authList}">
                                            <option value="${auth.authId}" <c:if test="${auth.authId==rParam.searchVal03}">selected="selected"</c:if>><c:out value="${auth.authNm}" /></option>
                                        </c:forEach>
                                    </c:if>
                                </select>
                            </li>
                            <li><label><spring:message code="lbl.use_yn"/></label>
                                <select class="select" name="searchVal04">
                                    <option selected="selected" value=""><spring:message code="code.all"/></option>
                                    <option value="Y" <c:if test="${rParam.searchVal04 == 'Y'}">selected="selected"</c:if>>Y</option>
                                    <option value="N" <c:if test="${rParam.searchVal04 == 'N'}">selected="selected"</c:if>>N</option>
                                </select>
                            </li>
                            <li>
                                <a class="btn btn-primary" href="#" id="btnSearch" type="button"><spring:message code="btn.search"/></a>
                            </li>
                        </ul>
                    </div>
                </div>
                <div class="row">
                    <div>
                        <h2 class="board-title"><spring:message code="menu.admin.user.mgmt"/> (Total : ${rCnt})</h2>
                        <div class="board-top">
                            <a class="btn btn-primary" href="#" id="btnRelease" type="button"><spring:message code="btn.unlock"/></a>
                            <a class="btn btn-primary" href="#" id="btnRegist"  type="button"><spring:message code="btn.regist"/></a>
                        </div>
                    </div>
                    <div class="table-responsive table-wrap"> 
                        <table id="listTable" class="table table-hover">
                            <colgroup>
                                <col style="width: 43px">
                                <col style="width: 100px">
                                <col style="width: 100px">
                                <col style="width: 80px">
                                <col style="width: 210px">
                                <col style="width: 60px">
                                <col style="width: 160px">
                                <col style="width: 160px">
                            </colgroup>
                            <thead>
                                <tr>
                                    <th>
                                        <span class="checkbox nolabel">
                                            <input id="selectAll" name="selectAll" type="checkbox" >
                                            <label for="selectAll"><spring:message code="lbl.all.check"/></label>
                                        </span>
                                    </th>
                                    <th><spring:message code="lbl.user_id"/></th>
                                    <th><spring:message code="lbl.user_name"/></th>
                                    <th><spring:message code="lbl.auth"/></th>
                                    <th><spring:message code="lbl.email"/></th>
                                    <th><spring:message code="lbl.use_yn"/></th>
                                    <th><spring:message code="lbl.use_startdate"/></th>
                                    <th><spring:message code="lbl.use_enddate"/></th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:if test="${empty rList}">
                                    <tr>
                                        <td colspan="8" align="center"><spring:message code="msg.search.data.empty"/></td>
                                    </tr>
                                </c:if>
                                <c:if test="${not empty rList}">
                                    <c:forEach var="data" items="${rList}" varStatus="status">
                                        <tr class='clickable-row'>
                                            <td>
                                                <span class="checkbox nolabel">
                                                    <input id="subChk${status.index}" name="selectBoxArr" type="checkbox" value="<c:out value="${data.userId}"/>"/>
                                                    <label for="subChk${status.index}"><spring:message code="lbl.select"/></label>
                                                </span>
                                                <input type="hidden" id="arrayUserId${status.index}" value="${data.userId}" />
                                            </td>
                                            <td>${data.userId}</td>
                                            <td>${data.userNm}</td>
                                            <td>${data.authNm}</td>
                                            <td>${data.userEmlAddr}</td>
                                            <td>${data.userUseYn}</td>
                                            <td>${data.acctStartDate}</td>
                                            <td>${data.acctEndDate}</td>
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
