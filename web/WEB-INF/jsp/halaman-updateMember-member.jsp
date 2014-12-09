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
        <title>Edit Data Member</title>
        <link rel="shortcut icon" href="img/OM-Item_Logo.png" type="image/png">
        <link href="<c:out value="${pageContext.request.contextPath}/resources/semantic-ui/packaged/css/semantic.css" />" rel="stylesheet" type="text/css">
        <link href="<c:out value="${pageContext.request.contextPath}/resources/date/redmond.datepick.css" />" rel="stylesheet" type="text/css">
    </head>
    <body>
        <!--Menu bar-->
        <div class="ui menu">
                <a class="active item" href="booking">
                    <i class="book icon"></i> SEWA
                </a>
                <div class="ui pointing dropdown link item">
                    <i class="user icon"></i> MEMBER <i class="dropdown icon"></i>
                    <div class="menu">
                        <a class="item" href="update"><i class="add icon"></i>Update My Data</a>
                    </div>
                </div>
                <div class="right menu">
                    <form method="POST">
                        <div class="ui dropdown link item">
                            <i class="user icon"></i> MEMBER <i class="dropdown icon"></i>
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

        <div class="ui one column page grid">
            <div class="column">
                <!--Search box-->
                <!--End of Search box-->
                <form class="ui fluid form segment" method="POST" id="updateForm">
                    <div class="two fields">
                        <div class="field">
                            <label>Nama</label>
                            <input name="nama" placeholder="Nama" type="text">
                        </div>
                        <div class="field">
                            <label>Tempat Lahir</label>
                            <input name="tempatLahir" placeholder="Tempat Lahir" type="text">
                        </div>
                    </div>
                    <div class="two fields">
                        <div class="field">
                            <label>Tanggal Lahir</label>
                            <input name="tanggalLahir" type="text" id="datePicker" placeholder="Tanggal Lahir">
                        </div>
                        <div class="field">
                            <label>Alamat</label>
                            <input name="alamat" placeholder="Alamat" type="text">
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
                            <input name="username" placeholder="Username" disabled="disabled" type="text">
                        </div>
                        <div class="field">
                            <label>Password</label>
                            <input name="password" placeholder="Password" type="password">
                        </div>
                    </div>
                    <div class="two fields">
                        <div class="field">
                        </div>
                        <div class="field">
                            <label>Confirm Password</label>
                            <input name="cpassword" placeholder="Password" type="password">
                        </div>
                    </div>
                    <input class="ui blue submit button" name="commit" value="UPDATE MY DATA!">
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
                    idfilm: {
                        identifier: 'nama',
                        rules: [
                            {
                                type: 'empty',
                                prompt: 'Masukkan Nama'
                            }
                        ]
                    },
                    judul: {
                        identifier: 'tempatLahir',
                        rules: [
                            {
                                type: 'empty',
                                prompt: 'Masukkan Tempat Lahir'
                            }
                        ]
                    },
                    genre: {
                        identifier: 'tanggalLahir',
                        rules: [
                            {
                                type: 'empty',
                                prompt: 'Masukkan Tanggal Lahir'
                            }]
                    },
                    status: {
                        identifier: 'alamat',
                        rules: [
                            {
                                type: 'empty',
                                prompt: 'Masukkan Alamat'
                            }
                        ]
                    },
                    kategori: {
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
                                type: 'match',
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
