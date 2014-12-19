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
        <link href="<c:out value="${pageContext.request.contextPath}/resources/semantic-ui/packaged/css/semantic.css" />" rel="stylesheet" type="text/css">        
    </head>
    <body>
        <!--Menu bar-->
        <div class="ui menu">
            <a class="item" id="op" href="halamanutamaoperator">
                <i class="book icon"></i> SEWA
            </a>
            <div class="ui pointing dropdown link item">
                <i class="user icon"></i> MEMBER <i class="dropdown icon"></i>
                <div class="menu">
                    <a class="item" id="reg" href="registrasi"><i class="add icon"></i>Registrasi Member</a>
                    <a class="item" id="topup" href="topup"><i class="dollar icon"></i>Top Up Saldo</a>
                </div>
            </div>
            <a class="item" id="sche" href="halamanLihatJadwal">
                <i class="book icon"></i> LIHAT JADWAL
            </a>
            <div class="right menu">
                <form action="logout" method="POST">
                    <div class="ui dropdown link item">
                        <i class="user icon"></i> OPERATOR <i class="dropdown icon"></i>
                        <div class="menu">
                            <table class="ui basic table">
                                <tr>
                                    <td>Nama</td>
                                    <td>${name}</td>
                                </tr>
                                <tr>
                                    <td >Username</td>
                                    <td>${username}</td>
                                </tr>
                            </table>
                            <input class="ui fluid tiny submit button" type="submit" name="logoutAd" value="Logout">
                        </div>
                    </div>
                </form>
            </div>
        </div>
        <!--End of Menu bar-->

        

        <!--Script-->
        <script src="<c:out value="${pageContext.request.contextPath}/resources/semantic-ui/packaged/javascript/jquery-2.1.1.js" />" type="text/javascript"></script>
        <script src="<c:out value="${pageContext.request.contextPath}/resources/semantic-ui/packaged/javascript/semantic.js" />" type="text/javascript"></script>
        
        <script type="text/javascript">

            $(document).ready(function() {
                $('.ui.dropdown').dropdown();
            });


        </script>
    </body>
</html>
