<%-- 
    Document   : halaman-registrasiMember-operator
    Created on : Nov 15, 2014, 10:26:46 PM
    Author     : Lorencius
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
        <%@include file="menubar-operator.jsp" %>
        <!--End of Menu bar-->

        <div class="ui one column page grid" id="formCek">
            <div class="column">
                <h4 class="ui top center aligned attached inverted blue block header">CEK JADWAL</h4>
                <!--Search box-->
                <!--End of Search box-->
                <form class="ui fluid form segment" action="cekJadwal" method="GET" id="cekForm">
                    <div class="two fields">
                        <div class="field">
                            <label>Tanggal Sewa</label>
                            <!--<label>Tanggal Sewa</label>-->
                            <div class="ui left labeled icon input">
                                <input id="popupDatepicker" placeholder="Tanggal Sewa" type="text" name="tanggalSewa" value="${tanggalSewa}" />
                                <i class="calendar icon"></i>
                            </div>
                        </div>
                        <div class="field">
                            <label>Jam Sewa</label>
                            <!--<label>Jam Sewa</label>-->
                            <div class="ui left labeled icon input">
                                <input id="popupClockpicker" placeholder="Jam Sewa" type="text" name="jamSewa" value="${jamSewa}" />
                                <i class="time icon"></i>
                            </div>
                        </div>                        
                    </div>
                    <div class="two fields">                        
                        <div class="field">
                            <label>Studio</label>
                            <div class="ui fluid selection dropdown">
                                <input name="studio" type="hidden" id="noStudio" value="${studio}" />
                                <div class="default text">Studio</div>
                                <i class="dropdown icon"></i>
                                <div class="menu">
                                    <div class="item" data-value="101" >Studio 1</div>
                                    <div class="item" data-value="102" >Studio 2</div>
                                    <div class="item" data-value="103" >Studio 3</div>
                                </div>
                            </div>
                        </div>
                        <div class="field">
                            <label>Durasi Sewa</label>
                            <input name="durasiSewa" type="text" id="datePicker" placeholder="Durasi Sewa" value="${durasiSewa}" />
                        </div>
                    </div>    
                    <div class="two fields">
                        <div class="field">
                            <input class="ui blue submit button" type="submit" name="commit" value="CEK KETERSEDIAAN!">
                        </div>
                        <div class="field">
                            <div class="field">
                                <!--Success Message-->
                                <c:if test="${ketersediaan == 1}">
                                    <c:set var="boxcolor" value="positive"></c:set>
                                </c:if>
                                <c:if test="${ketersediaan < 1}">
                                    <c:set var="boxcolor" value="negative"></c:set>
                                </c:if>
                                <div class="ui ${boxcolor} message" id="success">
                                    <div class="header">
                                        ${message}
                                    </div>
                                    <c:if test="${ketersediaan == -1}">
                                        <p>Biaya sewa sebesar <b>Rp ${biaya}</b></p>
                                    </c:if>
                                    <c:if test="${ketersediaan == 1}">
                                        <p>Biaya sewa sebesar <b>Rp ${biaya}</b></p>
                                    </c:if>
                                </div>
                                <!--End of Success Message-->
                            </div>
                        </div>
                    </div>
                </form>
            </div>
        </div>

        <div class="ui one column page grid">
            <div class="column">
                <h4 class="ui top center aligned attached inverted blue block header">PENYEWA</h4>
                <!--Search box-->
                <!--End of Search box-->
                <form class="ui fluid form segment" action="summarysewa" method="POST" id="penyewaForm">
                    <div class="two fields">
                        <div class="field">
                            <label>Nama Penyewa</label>
                            <input type="text" name="namaPenyewa" placeholder="Nama Penyewa" value="${namaPenyewa}" ${disable}  />
                        </div>
                        <div class="field">
                            <label>Nomor Telepon</label>
                            <input type="text" name="noTelp" placeholder="Nomor Telepon Penyewa" value="${noTelp}" ${disable} />
                        </div>
                    </div>
                    <input type="hidden" name="tanggalSewa" value="${tanggalSewa}" />
                    <input type="hidden" name="jamSewa" value="${jamSewa}" />
                    <input type="hidden" name="studio" value="${studio}" />
                    <input type="hidden" name="durasiSewa" value="${durasiSewa}" />
                    <input type="hidden" name="biayaunfmt" value="${biayaunfmt}" />
                    <input type="hidden" name="biaya" value="${biaya}" />
                    <div class="fields">
                        <div class="field">
                            <input class="ui blue submit button" type="submit" name="commit" value="SUBMIT" />
                            <!--<input type="submit">-->
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
                $(document.getElementById("op")).addClass("active");
                $('#popupClockpicker').clockpicker({autoclose: true});
                $('#popupDatepicker').datepick({dateFormat: 'dd-M-yyyy'});
                $('.ui.dropdown').dropdown();

                $('#cekForm').form({
                    tanggalSewa: {
                        identifier: 'tanggalSewa',
                        rules: [
                            {
                                type: 'empty',
                                prompt: 'Masukkan tanggal penyewaan'
                            }
                        ]
                    },
                    jamSewa: {
                        identifier: 'jamSewa',
                        rules: [
                            {
                                type: 'empty',
                                prompt: 'Masukkan jam sewa'
                            }
                        ]
                    },
                    studio: {
                        identifier: 'studio',
                        rules: [
                            {
                                type: 'empty',
                                prompt: 'Masukkan studio yang dipilih'
                            }
                        ]
                    },
                    durasiSewa: {
                        identifier: 'durasiSewa',
                        rules: [
                            {
                                type: 'empty',
                                prompt: 'Masukkan durasi sewa'
                            }
                        ]
                    },
                    namaPenyewa: {
                        identifier: 'namaPenyewa',
                        rules: [
                            {
                                type: 'empty',
                                prompt: 'Masukkan durasi sewa'
                            }
                        ]
                    }, 
                    noTelp: {
                        identifier: 'noTelp',
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
                
                $('#penyewaForm').form({
                    namaPenyewa: {
                        identifier: 'namaPenyewa',
                        rules: [
                            {
                                type: 'empty',
                                prompt: 'Masukkan nama penyewa'
                            }
                        ]
                    }, 
                    noTelp: {
                        identifier: 'noTelp',
                        rules: [
                            {
                                type: 'empty',
                                prompt: 'Masukkan nomor telepon penyewa'
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
