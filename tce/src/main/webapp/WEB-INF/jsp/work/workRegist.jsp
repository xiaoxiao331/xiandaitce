<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/include/declare.jspf" %>
<script src="/js/My97DatePicker/WdatePicker.js"></script>
<%-- 
               新增页面
--%>
<script type="text/javaScript">

function propertiesChk() 
{
    $("#worPerson").val("");
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
    $(".ui-datepicker-trigger").prop("disabled", true);
}

//目录按钮
function fn_workMain() 
{
    document.workRegistForm.pageGbn.value = "save";
    document.workRegistForm.action = "<c:url value='/work/workMain.do'/>";
    document.workRegistForm.submit();
}

function validation(){
    if($('#worTitle').val() == ''){
        alert("<spring:message code='msg.input.work.title'/>");
        $("#worTitle").focus();
        return false;
    }else if($('#worDatail').val() == ''){
        alert("<spring:message code='msg.input.work.withcontent'/>");
        $("#worDatail").focus();
        return false;
    }else if($('#worClassify').val() == ''){
    	alert("<spring:message code='msg.input.work.classify'/>");
        $("#worClassify").focus();
        return false;
    }else if($('#worExigency').val() == ''){
    	alert("<spring:message code='msg.input.work.exigency'/>");
        $("#worExigency").focus();
        return false;
    }else if($('#worPerson').val() == ''){
    	alert("<spring:message code='msg.input.work.person'/>");
        $("#worPerson").focus();
        return false;
    }else if($('#worIntheDate').val() == ''){
    	alert("<spring:message code='msg.input.work.worinthedate'/>");
        $("#worIntheDate").focus();
        return false;
    }else if($('#worGeginDate').val() == ''){
    	alert("<spring:message code='msg.input.work.worgegindate'/>");
        $("#worGeginDate").focus();
        return false;
    }
    else if($('#worRate').val() == ''){
    	alert("<spring:message code='msg.input.work.worrate'/>");
        $("#worRate").focus();
        return false;
    }
    else return true;
  } 
  
function fn_workRegist() {
     if(!validation()) return;
     if(doubleSubmitCheck()) return;
     $("#workRegistForm").attr('action',"/work/workRegist.do" );
     $("#workRegistForm").submit();
}

function fn_searchTakeUser() {
    var url= "/work/pop/vehicleCodePopup.do";    //팝업창 페이지 URL
    var winWidth = 700;
    var winHeight = 600;
    var popupOption= "width="+winWidth+", height="+winHeight;    //팝업창 옵션(optoin)
    
    window.open(url,"_blank",popupOption).focus();
}

/**
 * @returns {Boolean}
 */
var doubleSubmitFlag = false;
function doubleSubmitCheck(){
    if(doubleSubmitFlag){
        return doubleSubmitFlag;
    }else{
        doubleSubmitFlag = true;
        return false;
    }
} 

</script>
    <div class="wrapper">
        <div class="page">
            <div class="ribbon">
                <div class="title"><spring:message code="lbl.title.noti.regist"/></div>
            </div>
            <div class="page-content">
                <div class="container-fluid">
                <form name="workRegistForm" id="workRegistForm" enctype="multipart/form-data" method="post">
                    <input type="hidden" name="pageGbn" value=""/>
                    <%-- <input type="hidden" name="userId" value="${userId}"/> --%>
                    <div class="row">
                        <div class="board-top">
                            <a href="#" class="btn btn-primary-line" name="btnRegist" id="btnRegist" onclick="fn_workRegist()"><spring:message code="btn.save"/></a>
                            <a href="#" class="btn btn-primary" onclick="fn_workMain();"><spring:message code="btn.list"/></a>
                        </div>
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
                                    <th><span class="require">*</span><spring:message code="lbl.title"/></th>
                                    <td colspan="6"><input maxlength="50" type="text" class="form-control" name="worTitle" id="worTitle" placeholder="<spring:message code='msg.input.work.title'/>"/></td>
                                </tr>
                                <tr>
                                    <th><span class="require">*</span><spring:message code="lbl.noti.content"/></th>
                                    <td colspan="6">
                                        <textarea maxlength="500" class="form-control" placeholder="<spring:message code='msg.input.work.withcontent'/>" name="worDatail" id="worDatail"></textarea>
                                    </td> 
                                </tr>
                                    <tr>
                                       <th><span class="require">*</span><spring:message code="lbl.noti.type"/></th>
                                           <td colspan="2">
                                                <select class="select" name="worClassify" id="worClassify">
                                                    <option selected="selected" value=""><spring:message code="lbl.total" /></option>
                                                    <option value="A"><spring:message code="code.mt.work.operati"/></option>
                                                    <option value="B"><spring:message code="code.mt.work.wxwork"/></option>
                                                    <option value="C"><spring:message code="code.mt.work.emergency"/></option>
                                                    <option value="D"><spring:message code="code.mt.work.improvement"/></option>
                                                    <option value="E"><spring:message code="code.mt.work.functional"/></option>
                                                </select>
                                           </td>
                                        
                                        <th><span class="require">*</span><spring:message code="lbl.urgency"/></th>
                                        <td>
                                            <select class="select" name="worExigency" id="worExigency">
                                                <option selected="selected" value=""><spring:message code="lbl.total" /></option>
                                                <option value="A" ><spring:message code="code.mt.work.urgency"/></option>
                                                <option value="B" ><spring:message code="code.mt.work.tall"/></option>
                                                <option value="C" ><spring:message code="code.mt.work.center"/></option>
                                                <option value="D" ><spring:message code="code.mt.work.low"/></option>
                                            </select>
                                        </td>
                                    </tr>
                                    <tr>
                                        <th><span class="require">*</span><spring:message code="lbl.prd.date"/></th>
                                        <td colspan="2">
                                            <input type="text" class="Wdate" name="worGeginDate" id="worGeginDate" title=""  
                                            onFocus="WdatePicker({lang:'zh-cn',dateFmt:'yyyy-MM-dd HH:mm:ss',readOnly:true,minDate:'#F{$dp.$D(\'worIntheDate\',{d:-1});}'})"/>
                                        </td>
                                        <th><span class="require">*</span><spring:message code="lbl.prd.date2"/></th>
                                        <td colspan="2">
                                            <input type="text" class="Wdate" name="worIntheDate" id="worIntheDate" title="" 
                                            onFocus="WdatePicker({lang:'zh-cn',dateFmt:'yyyy-MM-dd HH:mm:ss',readOnly:true,minDate:'#F{$dp.$D(\'worGeginDate\',{d:1});}'})"/>
                                        </td>
                                    </tr>
                                    <tr>
                                    	<th><span class="require">*</span><spring:message code="lbl.take"/></th>
                                        <td colspan="2">
                                            <div class="input-group">
                                                <input type="text" class="form-control" style="width: 150px" name="worPerson" id="worPerson" value="" readonly="readonly">
                                                <a href="#" class="btn btn-primary" name="btnvehicleSearch" style="margin-top: 6px;" id="btnvehicleSearch" onclick="fn_searchTakeUser();">
                                                	<spring:message code="lbl.cartake.search"/>
                                                </a>
                                            </div>  
                                        </td>
                                     	<th><span class="require">*</span><spring:message code="lbl.work.progress"/></th>
                                        <td>
                                            <select class="select" name="worRate" id="worRate" title="긴급도">
                                                <option selected="selected" value=""><spring:message code="lbl.total" /></option>
                                                <option value="A" ><spring:message code="code.progress"/></option>
                                                <option value="B" ><spring:message code="code.progress0"/></option>
                                                <option value="C" ><spring:message code="code.progress1"/></option>
                                                <option value="D" ><spring:message code="code.progress2"/></option>
                                                <option value="E" ><spring:message code="code.progress3"/></option>
                                                <option value="F" ><spring:message code="code.progress4"/></option>
                                                <option value="G" ><spring:message code="code.progress5"/></option>
                                                <option value="H" ><spring:message code="code.progress6"/></option>
                                                <option value="I" ><spring:message code="code.progress7"/></option>
                                                <option value="Z" ><spring:message code="code.progress8"/></option>
                                                <option value="K" ><spring:message code="code.progress9"/></option>
                                            </select>
                                     	</td>
                                    </tr>
                                     
                                </tbody>
                            </table>
                        </div>
                    </div>
                    </form>
                </div>
            </div>
        </div>
        <!-- /.container-fluid -->
    </div>
    <!-- /.wrapper --> 