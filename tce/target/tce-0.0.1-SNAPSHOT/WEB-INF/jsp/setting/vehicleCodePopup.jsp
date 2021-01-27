<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/include/declare.jspf" %>
<%-- 
    JSP Name : vehicleCodePopup.jsp
    Description : 설명을 기술합니다.
    author 
    since 2019. 5. 02.
    version 1.0
    Modification Information
       since          author              description
    ===========    =============    ===========================
    2019. 5. 02.                            최초 생성
--%>
 
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
        url : "/noti/pop/selectVehicleCdList.do",
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
                    + '<td><input type="radio" name="selectedVehlCd" id="selectedVehlCd" value="'+data.resultCdList[i].saleVehicleCd+'"></td>' 
                    + '<td> ' + data.resultCdList[i].seq + '</td>' 
                    + '<td> ' + data.resultCdList[i].vehicleNm + '</td>' 
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

    window.opener.document.notiRegistForm.sendCar.value = radioVal;
    
    window.close();
}

</script>

<div class="winpop" id="popTypeSearch">
    <div class="modal-content">
        <div class="modal-header">
        </div>
        <form id="popForm" name="popForm" method="post">
        <div class="modal-body">
            <div class="search-box">
                <ul class="form-group">
                    <li>
                        <label><spring:message code="lbl.take.name"/></label>
                        <input type="text" class="form-control" style="width: 200px" id="vehicleNm" name="vehicleNm" value="${carMstVO.vehicleNm}">
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
                            <col style="width: 15%">
                            <col style="width: 40%">
                            <col style="width: auto">
                        </colgroup>
                        <thead>
                            <tr>
                                <th><spring:message code="btn.select" /></th>
                                <th>NO</th>
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
                                <col style="width: 15%">
                                <col style="width: 40%">
                                <col style="width: auto">
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
                                              <td><input type="radio" name="selectedVehlCd" id="selectedVehlCd" value="${result.saleVehicleCd }"></td>
                                              <td><c:out value="${result.seq }" /></td>
                                              <td><c:out value="${result.vehicleNm }" /></td>
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

