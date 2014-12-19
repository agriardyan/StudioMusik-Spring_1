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
        <title>Registrasi Member</title>
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
                <h4 class="ui top center aligned attached inverted blue block header">FORM REGISTRASI</h4>
                <!--Search box-->
                <!--End of Search box-->
                <form class="ui fluid form segment" action="simpanMember" method="POST" id="registrasiForm">
                    <div class="two fields">
                        <div class="field">
                            <label>Nama</label>
                            <input name="nama" placeholder="Nama" type="text">
                        </div>
                        <div class="field">
                            <label>Alamat</label>
                            <input name="alamat" placeholder="Alamat" type="text">
                        </div>                        
                    </div>
                    <div class="two fields">                        
                        <div class="field">
                            <label>Tempat Lahir</label>
                            <input name="tempatLahir" placeholder="Tempat Lahir" type="text">
                        </div>
                        <div class="field">
                            <label>Tanggal Lahir</label>
                            <input name="tanggalLahir" type="text" id="datePicker" placeholder="Tanggal Lahir">
                        </div>
                    </div>    
                    <div class="two fields">
                        <div class="field">
                            <label>Telepon</label>
                            <input name="telepon" placeholder="Telepon" type="text">
                        </div>
                        <div class="field">
                            <label>E-mail</label>
                            <input name="email" placeholder="E-mail" type="text">
                        </div>
                    </div>
                    <div class="two fields">
                        <div class="field">
                            <label>Username</label>
                            <input name="username" placeholder="Username" type="text">
                        </div>
                        <div class="field">
                            <label>Password</label>
                            <input name="password" placeholder="Password" type="password">
                        </div>
                    </div>
                    <div class="two fields">
                        <div class="field">
                            <label>Saldo Awal</label>
                            <h3>Rp 200.000,00</h3>
                            <input name="saldoAwal" placeholder="Rp 200.000,00" type="hidden" disabled="">
                            <input name="saldo" value="200000" type="hidden">
                        </div>
                        <div class="field">
                            <label>Confirm Password</label>
                            <input name="cpassword" placeholder="Password" type="password">
                        </div>
                    </div>
                    <input class="ui blue submit button" name="commit" value="SIMPAN">
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
            $(document).ready(function() {
                $(document.getElementById("reg")).addClass("active");

                var originalState = $('#registrasiForm').clone();

                $('#registrasiForm').replaceWith(originalState);

                $('.ui.dropdown').dropdown({on: 'hover'});

                $("#datePicker").datepick({dateFormat: 'dd-M-yyyy'});

                //Update Form error prompt
                $("#registrasiForm").form({
                    nama: {
                        identifier: 'nama',
                        rules: [
                            {
                                type: 'empty',
                                prompt: 'Masukkan Nama'
                            }
                        ]
                    },
                    tempatLahir: {
                        identifier: 'tempatLahir',
                        rules: [
                            {
                                type: 'empty',
                                prompt: 'Masukkan Tempat Lahir'
                            }
                        ]
                    },
                    tanggalLahir: {
                        identifier: 'tanggalLahir',
                        rules: [
                            {
                                type: 'empty',
                                prompt: 'Masukkan Tanggal Lahir'
                            }]
                    },
                    alamat: {
                        identifier: 'alamat',
                        rules: [
                            {
                                type: 'empty',
                                prompt: 'Masukkan Alamat'
                            }
                        ]
                    },
                    telepon: {
                        identifier: 'telepon',
                        rules: [
                            {
                                type: 'empty',
                                prompt: 'Masukkan Nomor Telepon'
                            }]
                    },
                    email: {
                        identifier: 'email',
                        rules: [
                            {
                                type: 'empty',
                                prompt: 'Masukkan Alamat E-mail'
                            }
                        ]
                    },
                    username: {
                        identifier: 'username',
                        rules: [
                            {
                                type: 'empty',
                                prompt: 'Masukkan Username yang Dipilih'
                            }
                        ]
                    },
                    password: {
                        identifier: 'password',
                        rules: [
                            {
                                type: 'empty',
                                prompt: 'Masukkan Password'
                            },
                            {
                                type: 'length[6]',
                                prompt: 'Password harus lebih dari 6 karakter'
                            }]
                    },
                    cpassword: {
                        identifier: 'cpassword',
                        rules: [
                            {
                                type: 'empty',
                                prompt: 'Ketik ulang password'
                            },
                            {
                                type: 'match[password]',
                                prompt: 'Password yang Anda masukkan tidak sesuai'
                            }]
                    }
                },
                {
                    on: 'submit',
                    inline: 'true'
                });
            });
        </script>
    </body>
</html>
