<%-- 
    Document   : halaman-summary-member
    Created on : Dec 7, 2014, 12:00:35 AM
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
        <title>Summary Penyewaan</title>
    </head>
    <body>
        <!--Menu bar-->
        <%@include file="menubar-member.jsp" %>
        <!--End of Menu bar-->

        <div class="ui one column page grid" id="formCek">
            <div class="column">
                <!--Search box-->
                <!--End of Search box-->
                <form class="ui fluid form segment" action="cekJadwal" method="GET" id="cekForm">
                    <div class="field">
                        <h2>Konfirmasi Penyewaan</h2>
                        <h4>Silakan review ulang data berikut ini. Setelah pencetakan nota, data tidak bisa diubah dan uang tidak dapat dikembalikan!</h4>
                        <br/>
                    </div>
                    <div class="two fields">
                        <div class="field">
                            <label>Tanggal Sewa</label>
                            <!--<label>Tanggal Sewa</label>-->
                            <h3>${tanggalSewa}</h3>
                        </div>
                        <div class="field">
                            <label>Jam Sewa</label>
                            <!--<label>Jam Sewa</label>-->
                            <h3>${jamSewa} - ${jamSelesai}</h3>
                        </div>                        
                    </div>
                    <div class="two fields">                        
                        <div class="field">
                            <label>Studio</label>
                            <h3>${namaStudio}</h3>
                        </div>
                        <div class="field">
                            <label>Durasi Sewa</label>
                            <h3>${durasiSewa} JAM</h3>
                        </div>
                    </div>    
                    <div class="two fields">
                        <div class="field">
                            <label>Nama Penyewa</label>
                            <h3>${namaPenyewa}</h3>
                        </div>
                        <div class="field">
                            <label>Nomor Telepon</label>
                            <h3>${noTelp}</h3>
                        </div>
                    </div>
                    <div class="two fields">
                        <div class="field">
                            <label>Biaya Sewa</label>
                            <h3>Biaya sewa sebesar Rp ${biaya}</h3>
                        </div>
                        <div class="field">
                            <label>Sisa Saldo</label>
                            <h3>Sisa saldo anda setelah transaksi adalah Rp ${remainSaldo}</h3>
                        </div>
                    </div>

                </form>
            </div>
        </div>

        <!--Search box-->
        <!--End of Search box-->

        <div class="ui two column page grid">
            <div class="left floated left aligned column">
                <div class="field">
                    <form class="ui fluid form segment" action="revisi" method="POST" id="penyewaForm">
                        <input class="ui blue submit button" type="submit" name="commit" value="REVISI" />
                        <input type="hidden" name="tanggalSewa" value="${tanggalSewa}" />
                        <input type="hidden" name="jamSewa" value="${jamSewa}" />
                        <input type="hidden" name="durasiSewa" value="${durasiSewa}" />
                        <input type="hidden" name="jamSelesai" value="${jamSelesai}" />
                        <input type="hidden" name="studio" value="${studio}" />
                        <input type="hidden" name="namaPenyewa" value="${namaPenyewa}" />
                        <input type="hidden" name="noTelp" value="${noTelp}" />
                        <input type="hidden" name="biaya" value="${biaya}" />
                        <input type="hidden" name="biayaunfmt" value="${biayaunfmt}" />
                        <input type="hidden" name="sisaSaldo" value="${sisaSaldo}" />
                    </form>
                </div>
            </div>
            <div class="right floated right aligned column">
                <div class="field">
                    <form class="ui fluid form segment" action="simpan" method="POST" id="penyewaForm">
                        <input class="ui blue submit button" type="submit" name="commit" value="SIMPAN DATA" />
                        <input type="hidden" name="tanggalSewa" value="${tanggalSewa}" />
                        <input type="hidden" name="jamSewa" value="${jamSewa}" />
                        <input type="hidden" name="durasiSewa" value="${durasiSewa}" />
                        <input type="hidden" name="jamSelesai" value="${jamSelesai}" />
                        <input type="hidden" name="studio" value="${studio}" />
                        <input type="hidden" name="namaPenyewa" value="${namaPenyewa}" />
                        <input type="hidden" name="noTelp" value="${noTelp}" />
                        <input type="hidden" name="biaya" value="${biaya}" />
                        <input type="hidden" name="biayaunfmt" value="${biayaunfmt}" />
                        <input type="hidden" name="sisaSaldo" value="${sisaSaldo}" />
                    </form>
                </div>
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