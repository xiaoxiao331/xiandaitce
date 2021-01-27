<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<% 
   /* 보안 권고 사항 */
   response.setStatus(400); 
%>
<%-- 
    JSP Name : error.jsp
    Description : error
    author LMC
    since 2019. 5. 31.
    version 1.0
    Modification Information
       since          author              description
    ===========    =============    ===========================
    2019. 5. 31.         LMC                 CREATE
--%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <title>DKMS - ERROR</title>
        <link rel="stylesheet" type="text/css" href="<c:url value='/css/style.css'/>">
    </head>
    <body>
        <div class="wrapper">
            <div class="page">
                <div class="ribbon">
                    <h1 class="title">
                        <spring:message code="lbl.error.page" />
                    </h1>
                </div>
                <div class="page-content">
                       <spring:message code="msg.error.notice" />
                    <a class="btn btn-primary" href="#" type="button" onclick="javascript:history.back(-1);" >后退</a>
                </div>
            </div>
        </div>
    </body>
</html>