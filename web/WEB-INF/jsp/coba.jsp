<%-- 
    Document   : coba
    Created on : Dec 1, 2014, 6:28:29 PM
    Author     : root
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World! Coba</h1>
        <c:forEach items="${list}" var="daftar">

            <c:out value="${daftar.mNamaPegawai}"/> <br/>

        </c:forEach>
    </body>
</html>
