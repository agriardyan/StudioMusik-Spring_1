<%-- 
    Document   : halaman-help-home
    Created on : Dec 19, 2014, 7:54:43 AM
    Author     : root
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Bantuan</title>
        <link rel="shortcut icon" href="img/OM-Item_Logo.png" type="image/png">
        <link href="<c:out value="${pageContext.request.contextPath}/resources/semantic-ui/packaged/css/semantic.css" />" rel="stylesheet" type="text/css">
        <link href="<c:out value="${pageContext.request.contextPath}/resources/date/redmond.datepick.css" />" rel="stylesheet" type="text/css">
    </head>
    <body>
        <!--Menu bar-->
        <%@include file="menubar-home.jsp" %>
        <!--End of Menu bar-->

        <div class="ui one column page grid">
            <div class="column">
                <h4 class="ui top center aligned attached inverted blue block header">SELAMAT DATANG DI WEBSITE SABHA MUSIC STUDIO</h4>
                <!--Search box-->
                <!--End of Search box-->
                <form class="ui fluid form segment" action="welcome" method="POST" id="registrasiForm">
                    <div class="field">
                        <h3>Petunjuk Member</h3>
                        <ol>
                            <li>Untuk menjadi member, anda harus mendaftar terlebih dahulu. Silakan datangi petugas kami di Studio Musik Sabha</li>
                            <li>Setelah mendaftar, anda akan memiliki username dan password. Anda dapat login ke dalam situs sebagai member. <a href="member">Lewat sini</a></li>
                            <li>Di dalam halaman utama member, anda dapat menyewa studio. Saldo yang anda miliki akan digunakan untuk pembayaran.</li>
                            <li>Anda akan mendapatkan bukti sewa berupa nota. Anda dapat simpan atau mencetak nota tersebut sebagai bukti penyewaan. </li>
                            <li>Anda juga dapat mengubah data akun anda. Arahkan mouse ke menu bar, pilih Akun Saya, lalu klik Update Data Akun</li>
                            <li>Anda juga dapat hanya melihat saja data akun anda. Arahkan mouse ke menu bar, pilih Akun Saya, lalu klik Lihat Data Akun</li>
                            <li>Untuk logout, arahkan mouse ke menu bar, pilih Member, lalu klik Logout.</li>
                        </ol>
                        <h3>Petunjuk Operator</h3>
                        <ol>
                            <li>Sebagai operator, anda dapat login ke dalam situs sebagai operator. Caranya klik Login dan akan muncul sidebar. </li>
                            <li>Di dalam halaman utama operator, anda dapat membantu customer untuk menyewa studio. Silakan input data sesuai pesanan customer disitu.</li>
                            <li>Customer akan mendapatkan bukti sewa berupa nota. Anda harus mencetak nota tersebut sebagai bukti penyewaan. Caranya klik Cetak Nota setelah transaksi berhasil. </li>
                            <li>Anda juga dapat mendaftarkan member. Arahkan mouse ke menu bar, pilih Member, lalu klik Registrasi Member.</li>
                            <li>Anda juga dapat menambah (top up) saldo member. Arahkan mouse ke menu bar, pilih Member, lalu klik Top Up Saldo.</li>
                            <li>Untuk logout, arahkan mouse ke menu bar, pilih Operator, lalu klik Logout.</li>
                        </ol>
                        <h3>Petunjuk Owner</h3>
                        <ol>
                            <li>Sebagai owner, anda dapat login ke dalam situs sebagai owner. Caranya klik Login dan akan muncul sidebar. </li>
                            <li>Di dalam halaman utama owner, anda dapat meminta pembuatan laporan pemasukan dengan rentang waktu tertentu. Silakan pilih tanggal yang diinginkan lalu klik cetak nota.</li>
                            <li>Anda akan mendapatkan laporan pemasukan tersebut. Anda dapat melihat saja, atau menyimpan dan atau mencetak laporan pemasukan tersebut. </li>
                            <li>Untuk logout, arahkan mouse ke menu bar, pilih Owner, lalu klik Logout.</li>
                        </ol>
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
