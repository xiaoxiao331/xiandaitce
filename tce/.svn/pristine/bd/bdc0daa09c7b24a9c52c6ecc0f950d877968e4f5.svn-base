<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ page import="tce.setting.vo.*" %>
<%@ page import="tce.com.vo.*" %>
<%@ page import="java.util.*" %>
<%-- 
    JSP Name : header.jsp
    Description : 설명을 기술합니다.
    author LMC
    since 2019. 4. 24.
    version 1.0
    Modification Information
       since          author              description
    ===========    =============    ===========================
    2019. 4. 24.     LMC        최초 생성
--%>

<!DOCTYPE html>
<html lang="ko">
<head>
<script type="text/javascript">
    // 관리메뉴이동
    function goToMgmtMenu(url){
        $("#moveForm").attr('action',url);
        $("#moveForm").submit();
    }
    
    // 로그 아웃 
    function goToLogout(url){
        if(!confirm("로그아웃 하시겠습니까?/您想要注销吗?")) return;
        $("#moveForm").attr('action',url);
        $("#moveForm").submit();
    }

</script>
</head>
<body>
<%  SessionVO svo = (SessionVO)request.getSession().getAttribute("ssInfo");
    if(svo != null) {
%>
    <header class="header">
        <nav class="navbar">
            <a class="navbar-brand" href="#">
                <span class="navbar-brand-logo"><img src="../img/logo_brand.png" alt="HYUNDAI MOTOR GROUP"></span>
                <span class="navbar-logo"><img src="../img/logo_tit.png" alt="工单事项管理系统"></span>
            </a>
            <div class="depthbar"></div>
            <ul class="navbar-nav">
            <% boolean first = true;
               int lastLevel = 0;
               try {
               List<MenuVO> menuList = svo.getMenuList();
               for(MenuVO vo : menuList) {
                   if(vo.getUpperMenuId() == -2) { 
                       if(lastLevel == 2) { 
                           %></ul></li> <%
                       } else
                       if(lastLevel == 1) {
                           %></li> <% 
                       }
                       break;
                   }
            %>
                    <%  if(vo.getUpperMenuId() == -1 ) { 
                           if(lastLevel == 1) { %>
                              </li>
                            <% lastLevel = 0; first = true; %>
                        <%  } else %>
                        
                        <%  if(lastLevel == 2) { %>
                              </ul></li>
                            <% lastLevel = 0; first = true; %>
                        <%  } else %>

                        <% if(lastLevel == 0) { %>
                            <li class="nav-item">
                                <a class="nav-link" href="#"><spring:message code="<%=vo.getMsgId()%>"/></a>
                             <% first = false; 
                                lastLevel = 1;
                         } %>
                    <%  } else { %>
                           <% if(lastLevel == 1) { %>
                              <ul class="depth2">
                           <% } %>
                        <%   lastLevel = 2;  %>
                                <li><a href="#" onclick="goToMgmtMenu('<%=vo.getMenuUrl()%>')"><spring:message code="<%=vo.getMsgId()%>"/></a></li>
                    <%  
                        } 
                    %>
            <% } } catch(Exception e) { e.printStackTrace(); } %>
                
            </ul>
            <div class="navbar-right">
                <div class="users">
                    <span><%=svo.getUser().getUserNm()%> / <%=svo.getUser().getAuthNm()%></span>
                </div>
                <a href="#" onclick="goToLogout('/com/logout.do')" class="btn btn-logout">Logout</a>
            </div>
        </nav>
    </header>
    <!-- /.container-fluid -->
<% } %>

<form id="moveForm" name="moveForm" method="post"></form>
</body>
</html>