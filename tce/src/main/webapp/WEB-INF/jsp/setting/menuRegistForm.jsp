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

//appscan
function menuCodeCheck (){
    
    var menuCode = $("#menuCode").val();
    if(menuCode != ""){
        var regex = /^[a-zA-Z]*$/g;
        var result = regex.test(menuCode);
        if(result){
            return;
        }else{
            alert("请输入正确格式的菜单代码！");
        }
    }
  
}
//appscan

$(function(){
  //등록 버튼
    $("#btnRegist").click(function() {
        var menuNm = $("#menuNm").val();
        if(menuNm == '') {
            alert("<spring:message code='msg.input.menuname' />");
            return;
        }
        
        var menuCode = $("#menuCode").val();
        if(menuCode == '') {
            alert("<spring:message code='msg.input.menucode' />");
            return;
        }
        
        var msgId = $("#msgId").val();
        if(msgId == '') {
            alert("<spring:message code='msg.input.msgid' />");
            return;
        }
        
        var use_yn = $('input[name=use_yn]:checked').val();
        $("#useYn").val(use_yn);
        
        //상위 ID 설정
        var upper_menu_id = -1;
        var str = $("#upper_menu_id").val();
        if(str != '')
            upper_menu_id = str;
        $("#upperMenuId").val(upper_menu_id);
        
        if(!confirm("<spring:message code='msg.confirm_save' />")) return;
        
        $("#frm").attr('action',"/setting/menuRegist.do");
        $("#frm").submit();
    });
    
    //목록버튼
    $("#btnList").click(function() {
        $("#frm").attr('action',"/setting/menuMain.do");
        $("#frm").submit();
    });
});
</script>

<form id="frm" name="frm" method="post">
    <!-- 검색조건 유지 Start -->
    <input type="hidden" name="pageIndex"     value="1"  />
    <input type="hidden" name="searchVal01"   value=""   />
    <!-- 검색조건 유지 End -->
    
    <input type="hidden" name="useYn"         id="useYn"               />
    <input type="hidden" name="upperMenuId"   id="upperMenuId" value=0 />
    
    <div class="wrapper">
        <div class="page">
            <div class="ribbon">
                <div class="title"><spring:message code="menu.admin.menu.mgmt" /></div>
            </div>
            <div class="page-content">
                <div class="container-fluid">
                    <div class="row">
                        <div class="board-top">
                            <a href="#" class="btn btn-primary-line" id="btnRegist"><spring:message code="btn.save"/></a>
                            <a href="#" class="btn btn-primary" id="btnList"><spring:message code="btn.list"/></a>
                        </div>
                        <div class="table-wrap">
                            <table class="table tbl-typeA">
                                <colgroup>
                                    <col style="width: 20px">
                                    <col style="width: 90%">
                                </colgroup>
                                <tbody>
                                    <tr>
                                        <th><spring:message code="lbl.menu_level"/></th>
                                        <td>
                                            <select class="select" id="menuLevel" name="menuLevel" style="width:100px">
                                                <option value="1">1</option>
                                                <option value="2">2</option>
                                                <option value="3">3</option>
                                            </select>
                                        </td>
                                    </tr>
                                    <tr>
                                        <th><spring:message code="lbl.order_no"/></th>
                                        <td>
                                            <select class="select" name="menuSortNo" style="width:100px">
                                                <c:forEach begin="1" end="15" var="cnt" step="1" varStatus="status">
                                                    <option value="${status.index}">${status.index}</option>
                                                </c:forEach>
                                            </select>
                                        </td>
                                    </tr>
                                    <tr>
                                        <th><spring:message code="lbl.up.menu"/></th>
                                        <td>
                                            <select class="select" id="upper_menu_id" style="width:400px">
                                                <option value="">TOP</option>
                                                <c:if test="${not empty upperMenuList}">
                                                    <c:forEach var="menu" items="${upperMenuList}">
                                                        <option value="${menu.menuId}"><c:out value="${menu.menuNm}" /></option>
                                                    </c:forEach>
                                                </c:if>
                                            </select>
                                        </td>
                                    </tr>
                                    <tr>
                                        <th><span class="require">*</span><spring:message code="lbl.menu_name"/></th>
                                        <td><input id="menuNm" class="form-control" name="menuNm" value="" style="width:400px"></td>
                                    </tr>
                                    <tr>
                                        <th><span class="require">*</span><spring:message code="lbl.menu_code"/></th>
                                        <td><input id="menuCode" class="form-control" name="menuCode" value="" style="width:400px" onblur="menuCodeCheck()"></td>
                                    </tr>
                                    <tr>
                                        <th><span class="require">*</span><spring:message code="lbl.lang.msg_id"/></th>
                                        <td><input id="msgId" class="form-control" name="msgId" value="" style="width:400px"></td>
                                    </tr>
                                    <tr>
                                        <th><spring:message code="lbl.menu_url"/></th>
                                        <td><input id="menuUrl" class="form-control" name="menuUrl" value="" style="width:400px"></td>
                                    </tr>
                                    <tr>
                                        <th><spring:message code="lbl.use_yn"/></th>
                                        <td>
                                            <span class="radio">
                                                <input type="radio" name="use_yn" id="use_yn01" value="Y" checked>
                                                <label for="use_yn01">Y</label>
                                            </span>
                                            <span class="radio">
                                                <input type="radio" name="use_yn" id="use_yn02" value="N">
                                                <label for="use_yn02">N</label>
                                            </span>
                                        </td>
                                    </tr>
                                    <tr>
                                        <th><spring:message code="lbl.desc"/></th>
                                        <td><textarea id="description" class="form-control" name="description" style="width:400px;height:100px;"></textarea></td>
                                    </tr>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</form>