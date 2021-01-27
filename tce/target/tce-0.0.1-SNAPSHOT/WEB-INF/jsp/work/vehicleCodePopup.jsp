<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/include/declare.jspf" %>
<%-- 
    担当者查询
--%>
<!DOCTYPE html>
<html lang="ok">
<head>
<script type="text/javaScript" language="javascript">
$(document).ready(function(){ 
    $('.slimscroll').slimscroll({
        axis: 'y',
        height: 277
    });

    //차량코드 대문자
    $('#vehicleCd').bind("keyup", function() {
        $(this).val($(this).val().toUpperCase());
        
    })
 
});

//appscan
function vehicleCdCheck (){
  
      var vehicleCode = $("#vehicleCd").val();
      if(vehicleCode != ""){
          var regex = /^[0-9a-zA-Z]*$/g;
          var result = regex.test(vehicleCode);
          if(result){
              return;
          }else{
              alert("请输入正确的编码！");
          }
      }
    
}
//appscan

function fn_search() {

    var sendData = $('#popForm').serialize(); 
    $.ajax({
        type: "post",
        url : "/work/pop/selectVehicleCdList.do",
        data:sendData,
        error : function(request, status, error ) {   // 오류가 발생했을 때 호출된다. 
            alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
            }, 
        success : function(data){
            
            var div = document.querySelector('#tbodyList');
            
            if(data.resultCdList.length > 0){
                html = '<table class="table">';
                for (var i = 0; i < data.resultCdList.length; i++) { 
                    
                    html += '<tr>'
                    + '<td><input type="radio" name="selectedVehlCd" id="selectedVehlCd" value="'+data.resultCdList[i].userId+'"></td>' 
                    + '<td> ' + data.resultCdList[i].userUseYn + '</td>' 
                    + '<td> ' + data.resultCdList[i].userId + '</td>' 
                    +'</tr>';  
                }
                html += '</table>'; 
                
            }else{
                html = '<table class="table">';
                html += '<tr>' 
                    + '<td colspan="3" align="center"><spring:message code="msg.search.data.empty"/></td>' 
                    +'</tr>';  
                html += '</table>'; 
            }
            div.innerHTML = html;
        }
    }); 
}

function fn_SelectVehicel() {
	
    if($('input[name="selectedVehlCd"]:checked').length == 0){
        alert("请重新选择");
        return;
    }
    var radioVal = $('input[name="selectedVehlCd"]:checked').val();
    window.opener.document.workRegistForm.worPerson.value = radioVal;
    window.close();
}

</script>
</head>
<body>
<div class="winpop" id="popTypeSearch">
    <div class="modal-content">
        <div class="modal-header">
        </div>
        <form id="popForm" name="popForm" method="post">
        <input type="hidden" name="userId1" value="${userId}"/>
        <div class="modal-body">
            <div class="search-box">
                <ul class="form-group">
                    <li>
                        <label><spring:message code="lbl.take.name"/></label>
                        <input type="text" class="form-control" style="width: 200px" id="userId" name="userId" value="${userVO.userId}">
                    </li>
                    <li>
                        <div class="btn_div">
                            <a href="#"  class="btn btn-primary" id="btnSearch" name="btnSearch" value="조회" onclick="fn_search();"><spring:message code="btn.search"/></a>
                        </div>
                    </li>
                </ul>
                </ul>
            </div>
            <div class="table-wrap">
                <div class="thead-wrap">
                    <table class="table">
                        <colgroup>
                            <col style="width: 10%">
                            <col style="width: 30%">
                            <col style="width: 40%">
                        </colgroup>
                        <thead>
                            <tr>
                                <th><spring:message code="btn.select" /></th>
                                <th>使用与否</th>
                                <th><spring:message code="lbl.take.name"/></th>
                            </tr>
                        </thead>
                    </table>
                </div>
                <div class="slimscroll">
                    <div class="tbody-wrap">
                        <table class="table">
                            <colgroup>
                                <col style="width: 10%">
                                <col style="width: 30%">
                                <col style="width: 40%">
                            </colgroup>
                            <tbody id="tbodyList">
                              <c:choose>
                                  <c:when test="${empty resultcdList}">
                                      <tr>
                                          <td colspan="3" align="center"><spring:message code="msg.search.data.empty"/></td>
                                      </tr>
                                  </c:when>
                                  <c:otherwise>
                                      <c:forEach var="result" items="${resultcdList}"
                                          varStatus="status">
                                          <tr>
                                              <td><input type="radio" name="selectedVehlCd" id="selectedVehlCd" value="${result.userId }"></td>
                                              <td><c:out value="${result.userUseYn }" /></td>
                                              <td><c:out value="${result.userId }" /></td>
                                          </tr>
                                      </c:forEach>
                                  </c:otherwise>
                              </c:choose>
                          </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
        </form>
        <div class="modal-footer">
            <button type="button" class="btn btn-primary" id="selectedVehl" name="selectedVehl" onclick="fn_SelectVehicel();"><spring:message code="btn.select" /></button>
        </div>
    </div>
</div>
</body>
</html>
