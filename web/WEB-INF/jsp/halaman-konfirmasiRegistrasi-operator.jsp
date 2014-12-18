<%-- 
    Document   : halaman-registrasiMember-operator
    Created on : Nov 15, 2014, 10:26:46 PM
    Author     : Agustinus Agri
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="shortcut icon" href="img/OM-Item_Logo.png" type="image/png">
        <link href="<c:out value="${pageContext.request.contextPath}/resources/semantic-ui/packaged/css/semantic.css" />" rel="stylesheet" type="text/css">
        <title>Halaman Utama Operator</title>
    </head>
    <body>
        <!--Menu bar-->
        <%@include file="menubar-operator.jsp" %>
        <!--End of Menu bar-->

        <div class="ui one column page grid" id="formCek">
            <div class="column">
                <!--Search box-->
                <!--End of Search box-->
                <form class="ui fluid form segment" action="halamanutamaoperator" id="cekForm">
                    <div class="fields">
                        <h2><font face="calibri"> Terima kasih </font></h2>
                        <h4><font face="calibri"> Data registrasi member studio musik sudah disimpan</font></h4>
                        <br>
                    </div>    
                    <div class="two fields">
                        <div class="field">
                            <br>
                            <input type="submit" class="ui blue submit button" value="KEMBALI">
                        </div>
                        <div class="field">

                        </div>
                    </div>
                </form>
            </div>
        </div>

        <!--Script-->
        <script src="<c:out value="${pageContext.request.contextPath}/resources/semantic-ui/packaged/javascript/jquery-2.1.1.js" />" type="text/javascript"></script>
        <script src="<c:out value="${pageContext.request.contextPath}/resources/semantic-ui/packaged/javascript/semantic.js" />" type="text/javascript"></script>

    </body>
</html>
