<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
    response.setHeader("Cache-Control","no-store");   
    response.setHeader("Pragma","no-cache");   
    response.setDateHeader("Expires",0);   
        
    if(request.getProtocol().equals("HTTP/1.1")){response.setHeader("Cache-Control", "no-cache");}
%>
<%--
    JSP Name : tce.jsp
    Description : tce 프레임 페이지
    author LMC
    since 2019. 4. 23.
    version 1.0
    Modification Information
       since          author              description
    ===========    =============    ===========================
    2019. 4. 23.        LMC                  최초생성
--%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Frameset//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-frameset.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <title>Work Admin Web</title>
        
        <meta http-equiv="Content-Type"        content="text/html; charset=utf-8" />
        <meta http-equiv="Content-Script-Type" content="text/javascript"          />
        <meta http-equiv="Content-Style-Type"  content="text/css"                 />
        <meta http-equiv="X-UA-Compatible"     content="IE=edge"                  />
        
        <c:if test="${SESSION_CHECK=='F'}">
            <script>alert("Session finished : Logged in from different places : 在不同地方登录");</script>
        </c:if>
        <c:if test="${SESSION_CHECK=='S'}">
            <script>alert("Session finished");</script>
        </c:if>
        <c:if test="${SESSION_CHECK=='A'}">
            <script>alert("Session finished : Change your Auth");</script>
        </c:if>
    </head>
    <%-- <frameset frameborder="no" border="0" framespacing="0">
        <frame src="<%=request.getContextPath()%>/com/loginForm.do" name="mainFrame" id="mainFrame" title="mainFrame" />
    </frameset> --%>
    <script>
        location.href = '<%=request.getContextPath()%>/com/loginForm.do';
    </script>
    
</html>