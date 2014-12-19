<%-- 
    Document   : halaman-lihatData-member
    Created on : Nov 15, 2014, 10:26:46 PM
    Author     : Lorencius
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Edit Data Member</title>
        <link rel="shortcut icon" href="img/OM-Item_Logo.png" type="image/png">
        <link href="<c:out value="${pageContext.request.contextPath}/resources/semantic-ui/packaged/css/semantic.css" />" rel="stylesheet" type="text/css">
        <link href="<c:out value="${pageContext.request.contextPath}/resources/date/redmond.datepick.css" />" rel="stylesheet" type="text/css">
    </head>
    <body>
        <!--Menu bar-->
        <%@include file="menubar-member.jsp" %>
        <!--End of Menu bar-->

        <div class="ui one column page grid">
            <div class="column">
                <!--Search box-->
                <!--End of Search box-->
                <form class="ui fluid form segment" method="POST" id="updateForm" action="halamanutamamember">
                    <div class="two fields">
                        <div class="field">
                            <label>Nama</label>
                            <h3>${name}</h3>
                        </div>
                        <div class="field">
                            <label>Tempat Lahir</label>
                            <h3>${tempatLahir}</h3>
                        </div>
                    </div>
                    <div class="two fields">
                        <div class="field">
                            <label>Tanggal Lahir</label>
                            <h3>${ttl}</h3>
                        </div>
                        <div class="field">
                            <label>Alamat</label>
                            <h3>${alamat}</h3>
                        </div>
                    </div>    
                    <div class="two fields">
                        <div class="field">
                            <label>Telepon</label>
                            <h3>${noTelp}</h3>
                        </div>
                        <div class="field">
                            <label>E-mail</label>
                            <h3>${email}</h3>
                        </div>
                    </div>
                    <div class="two fields">
                        <div class="field">
                            <label>Username</label>
                            <h3>${username}</h3>
                        </div>
                        <div class="field">
                            <label>Saldo</label>
                            <h3>Rp ${saldoNow}</h3>
                        </div>
                    </div>
                    <input class="ui blue submit button" name="commit" value="KEMBALI">
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
                $('.ui.dropdown').dropdown({on: 'hover'});

                $("#datePicker").datepick({dateFormat: 'dd-M-yyyy'});

                //Update Form error prompt
                $("#updateForm").form({
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
