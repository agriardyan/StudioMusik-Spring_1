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
        <link href="<c:out value="${pageContext.request.contextPath}/resources/date/redmond.datepick.css" />" rel="stylesheet" type="text/css">
        <link href="<c:out value="${pageContext.request.contextPath}/resources/jclockpicker/jquery-clockpicker.min.css" />" rel="stylesheet" type="text/css">
        <title>Halaman Utama Operator</title>
    </head>
    <body>
        <!--Menu bar-->
        <%@include file="menubar-member.jsp" %>
        <!--End of Menu bar-->

        <div class="ui one column page grid" id="formCek">
            <div class="column">
                <!--Search box-->
                <!--End of Search box-->
                <form class="ui fluid form segment" action="cetakNota" id="cekForm" target="_blank">
                    <div class="fields">
                        <h2><font face="calibri"> Terima kasih </font></h2>
                        <h4><font face="calibri"> Data persewaan studio musik sudah disimpan</font></h4>
                        <br>
                    </div>    
                    <div class="two fields">
                        <div class="field">
                            <br>
                            <input type="hidden" name="kodeSewa" value="${kodeSewa}">
                            <input class="ui blue submit button" value="CETAK NOTA">
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
        <script src="<c:out value="${pageContext.request.contextPath}/resources/date/jquery.plugin.js" />" type="text/javascript"></script>
        <script src="<c:out value="${pageContext.request.contextPath}/resources/date/jquery.datepick.js" />" type="text/javascript"></script>
        <script src="<c:out value="${pageContext.request.contextPath}/resources/jclockpicker/jquery-clockpicker.min.js" />" type="text/javascript"></script>
        <script type="text/javascript">
            var originalState = $('#formCek').clone();

            $('#formCek').replaceWith(originalState);

            $(document).ready(function() {
                $('#popupClockpicker').clockpicker({autoclose: true});
                $('#popupDatepicker').datepick({dateFormat: 'dd-M-yyyy'});
                $('.ui.dropdown').dropdown();

                $('#cekForm').form({
                    name: {
                        identifier: 'tanggalSewa',
                        rules: [
                            {
                                type: 'empty',
                                prompt: 'Masukkan tanggal penyewaan'
                            }
                        ]
                    },
                    gender: {
                        identifier: 'jamSewa',
                        rules: [
                            {
                                type: 'empty',
                                prompt: 'Masukkan jam sewa'
                            }
                        ]
                    },
                    username: {
                        identifier: 'studio',
                        rules: [
                            {
                                type: 'empty',
                                prompt: 'Masukkan studio yang dipilih'
                            }
                        ]
                    },
                    password: {
                        identifier: 'durasiSewa',
                        rules: [
                            {
                                type: 'empty',
                                prompt: 'Masukkan durasi sewa'
                            }
                        ]
                    }
                }, {
                    on: 'submit',
                    inline: 'true'
                });

            });



        </script>
    </body>
</html>
