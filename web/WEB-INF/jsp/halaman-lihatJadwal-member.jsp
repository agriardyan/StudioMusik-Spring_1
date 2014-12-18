<%-- 
    Document   : halaman-lihatJadwal-operator
    Created on : Dec 18, 2014, 7:24:15 AM
    Author     : root
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
        <title>Halaman Lihat Jadwal</title>
    </head>
    <body>
        <!--Menu bar-->
        <%@include file="menubar-member.jsp" %>
        <!--End of Menu bar-->

        <div class="ui three column page grid" >
            <div class="column"></div>
            <div class="column">
                <form class="ui fluid form segment" action="lihatJadwal" method="GET" id="reportForm">
                    <h4 class="ui horizontal header divider">
                        <i class="file text outline icon"></i>
                        LIHAT JADWAL
                    </h4>
                    <div class="field">
                        <div class="field">
                            <label class="ui red teal tag label" >Tanggal Penyewaan</label>
                            <input name="tanggalSewa" type="text" id="datePicker1" placeholder="Tanggal Penyewaan">
                        </div>
                    </div>
                    <div class="field">
                        <div class="ui checkbox">
                            <input id="hariIni" type="checkbox">
                            <label>Hari ini</label>
                        </div>
                    </div>
                    <div class="field">
                        <button class="ui button" type="submit" name="download">
                            <i class="cloud download icon"></i>
                            Lihat Jadwal
                        </button>
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
                                <th>KODE STUDIO</th>
                                <th>NAMA PENYEWA</th>
                                <th>MULAI SEWA</th>
                                <th>SELESAI SEWA</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${dataListByDate}" var="dataList" >
                                <tr>
                                    <td><c:out value="${dataList.mKodeStudio}" /></td>
                                    <td><c:out value="${dataList.mNamaPenyewa}" /></td>
                                    <td><c:out value="${dataList.mMulaiSewa}" /></td>
                                    <td><c:out value="${dataList.mSelesaiSewa}" /></td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>

                </div>
            </div>

        </div>

        <script src="<c:out value="${pageContext.request.contextPath}/resources/semantic-ui/packaged/javascript/jquery-2.1.1.js" />" type="text/javascript"></script>
        <script src="<c:out value="${pageContext.request.contextPath}/resources/semantic-ui/packaged/javascript/semantic.js" />" type="text/javascript"></script>
        <script src="<c:out value="${pageContext.request.contextPath}/resources/date/jquery.plugin.js" />" type="text/javascript"></script>
        <script src="<c:out value="${pageContext.request.contextPath}/resources/date/jquery.datepick.js" />" type="text/javascript"></script>
        <script src="<c:out value="${pageContext.request.contextPath}/resources/jclockpicker/jquery-clockpicker.min.js" />" type="text/javascript"></script>
        <script type="text/javascript">
            var originalState = $('#reportForm').clone();
            $('#reportForm').replaceWith(originalState);

            $(document).ready(function() {
                $(document.getElementById("hal")).addClass("active");
                $('.ui.checkbox')
                        .checkbox({
                            'onChecked': function() {
                                $("#datePicker1")
                                        .datepick('disable');
                                $("#datePicker2")
                                        .datepick('disable');
                            },
                            'onUnchecked': function() {
                                $("#datePicker1")
                                        .datepick('enable');
                                $("#datePicker2")
                                        .datepick('enable');
                            }
                        });

                $('#popupClockpicker').clockpicker({autoclose: true});
                $('#datePicker1').datepick({dateFormat: 'dd-M-yyyy'});
                $('#datePicker2').datepick({dateFormat: 'dd-M-yyyy'});
                $('.ui.dropdown').dropdown();

                $(document.getElementById("rep")).addClass("active");




            });


        </script>

    </body>
</html>
