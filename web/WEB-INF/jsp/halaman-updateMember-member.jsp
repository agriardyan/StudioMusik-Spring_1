<%-- 
    Document   : halaman-updateMember-member
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
                <c:if test="${!empty error}">
                    <div class="field">
                        <div class="ui error form segment">
                            <div class="ui error message">
                                <div class="header">Gagal Update</div>
                                <p>${error}</p>
                            </div>      
                        </div>
                    </div>
                </c:if>
                <!--Search box-->
                <!--End of Search box-->
                <form class="ui fluid form segment" method="POST" id="updateForm" action="validateUpdate">
                    <div class="two fields">
                        <div class="field">
                            <label>Nama</label>
                            <h3>${name}</h3>
                        </div>
                        <div class="field">
                            <label>Tempat Lahir</label>
                            <input name="tempatLahir" placeholder="Kota kelahiran anda " type="text" value="${tempatLahir}">
                        </div>
                    </div>
                    <div class="two fields">
                        <div class="field">
                            <label>Tanggal Lahir</label>
                            <input name="tanggalLahir" type="text" id="datePicker" placeholder="Tanggal lahir anda" value="${ttl}">
                        </div>
                        <div class="field">
                            <label>Alamat</label>
                            <input name="alamat" placeholder="Alamat domisili anda" type="text" value="${alamat}">
                        </div>
                    </div>    
                    <div class="two fields">
                        <div class="field">
                            <label>Telepon</label>
                            <input name="telepon" placeholder="Telepon anda" type="text" value="${noTelp}">
                        </div>
                        <div class="field">
                            <label>E-mail</label>
                            <input name="email" placeholder="E-mail anda" type="text" value="${email}">
                        </div>
                    </div>
                    <div class="two fields">
                        <div class="field">
                            <label>Username</label>
                            <h3>${username}</h3>
                        </div>
                        <div class="field">
                            <label>Password Lama</label>
                            <input name="oldPassword" placeholder="Password saat ini" type="password">
                        </div>
                        
                    </div>
                    <div class="two fields">
                        <div class="field">
                            <label>Password Baru</label>
                            <input name="password" placeholder="Password baru" type="password">
                        </div>
                        <div class="field">
                            <label>Konfirmasi Password</label>
                            <input name="cpassword" placeholder="Ketik ulang password baru" type="password">
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
                    oldPassword: {
                        identifier: 'oldPassword',
                        rules: [
                            {
                                type: 'empty',
                                prompt: 'Masukkan Password'
                            }]
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
