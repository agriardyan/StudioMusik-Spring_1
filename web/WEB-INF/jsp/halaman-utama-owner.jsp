<%-- 
    Document   : halaman-utama
    Created on : Nov 22, 2014, 9:16:05 AM
    Author     : Agustinus Agri
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="<c:out value="${pageContext.request.contextPath}/resources/semantic-ui/packaged/css/semantic.css" />" rel="stylesheet" type="text/css">
        <link href="<c:out value="${pageContext.request.contextPath}/resources/date/redmond.datepick.css" />" rel="stylesheet" type="text/css">
        <link href="<c:out value="${pageContext.request.contextPath}/resources/jclockpicker/jquery-clockpicker.min.css" />" rel="stylesheet" type="text/css">
        <title>Halaman Utama Owner</title>
    </head>
    <body>
        <!--Menu bar-->
        <div class="container">
            <div class="ui menu">
                <a class="active item" href="halamanutamaowner">
                    <i class="book icon"></i> LIHAT LAPORAN
                </a>
                <!--a class="item" href="">
                    <i class="money icon"></i> UBAH HARGA
                </a-->
                <div class="right menu">
                    <form method="POST">
                        <div class="ui dropdown link item">
                            <i class="user icon"></i> OWNER <i class="dropdown icon"></i>
                            <div class="menu">
                                <table class="ui basic table">
                                    <tr>
                                        <td>Nama</td>
                                        <td>${name}</td>
                                    </tr>
                                    <tr>
                                        <td >ID</td>
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

            <h3 class="ui white inverted center aligned top attached header">Laporan Bulanan</h3>
            <div class="ui bottom attached segment">
                <form action="lihatlaporan" method="GET">
                    <div id="formCek" class="ui two column middle aligned relaxed grid basic segment">
                        <div class="column">
                            <div class="ui form basic segment">
                                <div class="two fields">
                                    <div class="field">
                                        <label>Bulan</label>
                                        <div class="ui fluid selection dropdown">
                                            <input name="bulan" type="hidden" id="bulan" value="${bulan}">
                                            <div class="default text">Bulan</div>
                                            <i class="dropdown icon"></i>
                                            <div class="menu">
                                                <div class="item" data-value="1" >Januari</div>
                                                <div class="item" data-value="2" >Februari</div>
                                                <div class="item" data-value="3" >Maret</div>
                                                <div class="item" data-value="4" >April</div>
                                                <div class="item" data-value="5" >Mei</div>
                                                <div class="item" data-value="6" >Juni</div>
                                                <div class="item" data-value="7" >Juli</div>
                                                <div class="item" data-value="8" >Agustus</div>
                                                <div class="item" data-value="9" >September</div>
                                                <div class="item" data-value="10" >Oktober</div>
                                                <div class="item" data-value="11" >November</div>
                                                <div class="item" data-value="12" >Desember</div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="field">
                                        <label>Tahun</label>
                                        <div class="ui left labeled icon input">
                                            <input placeholder="Tahun" type="text" name="tahun" value="${tahun}">
                                            <i class="time icon"></i>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="ui vertical divider">
                        </div>
                        <div class="column">
                            <div class="field">
                                <input type="submit" name="commit" class="big blue ui fluid button" value="Lihat Laporan!">
                            </div>
                        </div>
                    </div>
                </form>
            </div>
        </div>


        <div class="ui one column grid">
            <div class="column">
                <h3 class="ui white inverted center aligned top attached header">Laporan</h3>
                <div class="ui form attached segment"> 
                    <table class="ui table">
                        <thead>
                            <tr>
                                <th>KODE SEWA</th>
                                <th>KODE STUDIO</th>
                                <th>NAMA PENYEWA</th>
                                <th>NOMOR TELEPON</th>
                                <th>TANGGAL SEWA</th>
                                <th>JAM SEWA</th>
                                <th>JAM SELESAI</th>
                                <th>BIAYA PELUNASAN</th>
                                <th>STATUS PELUNASAN</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${dataListByMonth}" var="dataList" >
                                <tr>
                                    <td><c:out value="${dataList.mKodeSewa}" /></td>
                                    <td><c:out value="${dataList.mKodeStudio}" /></td>
                                    <td><c:out value="${dataList.mNamaPenyewa}" /></td>
                                    <td><c:out value="${dataList.mNomorTeleponPenyewa}" /></td>
                                    <td><c:out value="${dataList.mTanggalSewa}" /></td>
                                    <td><c:out value="${dataList.mJamSewa}" /></td>
                                    <td><c:out value="${dataList.mJamSelesai}" /></td>
                                    <td><c:out value="${dataList.mBiayaPelunasan}" /></td>
                                    <td><c:out value="${dataList.mStatusPelunasan}" /></td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>

                </div>
            </div>

        </div>



        <div class="ui three column grid">
            <div class="column"></div>
            <div class="column">
                <div class="field">
                    <!--input type="submit" name="cetak" class="big yellow ui fluid button" value="Cetak Laporan"-->
                </div>
            </div>
            <div class="column"></div>
        </div>


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
            });
        </script>
    </body>
</html>
