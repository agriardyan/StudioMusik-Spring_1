<%-- 
    Document   : halaman-toUpSaldoMember-operator
    Created on : Nov 15, 2014, 10:26:46 PM
    Author     : Lorencius
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Top Up Member</title>
        <link rel="shortcut icon" href="img/OM-Item_Logo.png" type="image/png">
        <link href="<c:out value="${pageContext.request.contextPath}/resources/semantic-ui/packaged/css/semantic.css" />" rel="stylesheet" type="text/css">
        <link href="<c:out value="${pageContext.request.contextPath}/resources/date/redmond.datepick.css" />" rel="stylesheet" type="text/css">
    </head>
    <body>
        <!--Menu bar-->
        <%@include file="menubar-operator.jsp" %>
        <!--End of Menu bar-->

        <div class="ui one column page grid">
            <div class="column">
                <c:if test="${!empty message}">
                    <div class="field">
                        <div class="ui error form segment">
                            <div class="ui error message">
                                <div class="header">Gagal Update</div>
                                <p>${message}</p>
                            </div>      
                        </div>
                    </div>
                </c:if>
                <!--Search box Form-->
                <form class="ui fluid form segment" action="cariUser" method="POST" id="updateSaldo">
                    <div class="three fields">
                        <div class="field">
                            <!--Search box-->
                            <div class="ui search" id="searchInput">
                                <div class="ui icon input">
                                    <input class="prompt" placeholder="Cari User ..." type="text" name="user" value="${user}">
                                    <button class="ui icon button" type="submit" name="cariData">
                                        <i class="search icon"></i>
                                    </button>
                                </div>
                                <div class="results"></div>
                            </div>
                            <!--End of Search box-->
                        </div>
                        <div class="field"></div>
                        <div class="field"></div>
                    </div>
                </form>
                <!--End of Search box Form-->
                <div class="ui fluid form segment">
                    <h4 class="ui horizontal header divider">
                        <i class="info icon"></i>
                        Personal Information
                    </h4>
                    <div class="three fields">
                        <!--User Info-->
                        <div class="field">
                            <label>Id Member</label>
                            <input id="id" placeholder="ID Member" type="text" disabled="disabled" value="${id}">
                        </div>
                        <div class="field">
                            <label>Nama</label>
                            <input id="nama" placeholder="Nama" type="text" disabled="disabled" value="${nama}">
                        </div>
                        <div class="field">
                            <label>Saldo</label>
                            <input id="saldo" placeholder="Saldo" type="text" disabled="disabled" value="${saldo}">
                        </div>
                        <!--End of User Info-->
                    </div>
                    <div class="two fields">
                        <div class="field">
                            <div class="ui blue disabled button"  id="tambahSaldo">Tambah Saldo</div>
                        </div>
                        <div class="field">
                            <!--Success Message-->
                            <div class="ui positive message" id="success">
                                <div class="header">
                                    Proses Penambahan Saldo Berhasil
                                </div>
                                <p>Nominal Saldo <b>Rp. ${saldo}</b> berhasil ditambahkan pada account Anda.</p>
                            </div>
                            <!--End of Success Message-->
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <!--Pop up Add Pulse-->
        <div class="ui small modal saldo">
            <i class="close icon"></i>
            <div class="header">
                Pilih Jumlah Saldo
            </div>
            <div class="content">
                <form class="ui form segment" action="updateSaldo" method="POST" id="formSaldo">
                    <div class="field">
                        <input type="hidden" name="idT" value="${id}">
                        <input type="hidden" name="namaT" value="${nama}">
                        <input type="hidden" name="saldoT" value="${saldo}">
                        <input type="hidden" name="userT" value="${user}">
                        <div class="ui fluid selection dropdown">
                            <input name="saldo" type="hidden">
                            <div class="default text">Pilih Saldo</div>
                            <i class="dropdown icon"></i>
                            <div class="menu">
                                <div class="item" data-value="150000" >Rp. 150.000</div>
                                <div class="item" data-value="200000" >Rp. 200.000</div>
                                <div class="item" data-value="250000" >Rp. 250.000</div>
                            </div>
                        </div>
                    </div>
                    <div class="field">
                        <button class="ui blue button" type="submit" name="tambah">Tambah</button>
                    </div>
                </form>
            </div>
        </div>
        <!--End of Pop up Add Pulse-->
        <!--Script-->
        <script src="<c:out value="${pageContext.request.contextPath}/resources/semantic-ui/packaged/javascript/jquery-2.1.1.js" />" type="text/javascript"></script>
        <script src="<c:out value="${pageContext.request.contextPath}/resources/semantic-ui/packaged/javascript/semantic.js" />" type="text/javascript"></script>
        <script src="<c:out value="${pageContext.request.contextPath}/resources/date/jquery.plugin.js" />" type="text/javascript"></script>
        <script src="<c:out value="${pageContext.request.contextPath}/resources/date/jquery.datepick.js" />" type="text/javascript"></script>
        <script src="<c:out value="${pageContext.request.contextPath}/resources/jclockpicker/jquery-clockpicker.min.js" />" type="text/javascript"></script>
        <script type="text/javascript">
            $(document).ready(function() {
                $(document.getElementById("topup")).addClass("active");
                //Hide and Show success message
                $(document.getElementById("success")).hide();
            <%if (null != request.getParameter("saldo")) {%>
                $(document.getElementById("success")).show();
            <%}%>
                $(document.getElementById("sal")).addClass("active");

                //Activated tambah saldo button 
            <% if (request.getParameter("user") != null) { %>
                $(document.getElementById("tambahSaldo")).removeClass("disabled");
            <%    }%>

                //Show modal onclick tambah saldo
                $('.ui.small.modal.saldo')
                        .modal('attach events', '#tambahSaldo', 'show');

                //Show dropdown on hover 
                $('.ui.dropdown').dropdown({on: 'hover'});

                //List of Username in search box

                //Search box error prompt
                $("#updateSaldo").form({
                    idfilm: {
                        identifier: 'user',
                        rules:
                                [
                                    {type: 'empty', prompt: 'Masukkan Username Member'}
                                ]
                    }
                },
                {
                    on: 'submit',
                    inline: true
                });

                //Tambah saldo error prompt
                $("#formSaldo").form({
                    idfilm: {
                        identifier: 'saldo',
                        rules:
                                [
                                    {type: 'empty', prompt: 'Pilih nominal saldo'}
                                ]
                    }
                },
                {
                    on: 'submit',
                    inline: true
                });
            });
        </script>
    </body>
</html>
