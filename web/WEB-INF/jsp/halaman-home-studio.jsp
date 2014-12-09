<%-- 
    Document   : halaman-home-studio
    Created on : Nov 15, 2014, 10:26:47 AM
    Author     : Lorencius
--%>

<%@page import="com.rplt.studioMusik.model.DataPegawai"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <%
        if (session.getAttribute("username") != null) {
            session.invalidate();
        }
    %>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>SMS</title>
        <link rel="shortcut icon" href="img/" type="image/png">
        <link href="semantic-ui/packaged/css/semantic.css" rel="stylesheet" type="text/css">
        <style>
            @font-face{
                font-family: "Amplify_PersonalUseOnly";
                font-weight: bold;
                src: url('font/Amplify_PersonalUseOnly.ttf');
            }
        </style>
    </head>
    <body style="
          background-image: url('img/Studio.jpg'); 
          background-repeat: no-repeat; 
          background-size: cover; 
          background-attachment: fixed; 
          background-position: center;
          opacity: 1.0;">


        <%
            if (null != request.getParameter("commit")) {
                String username = request.getParameter("username");
                String password = request.getParameter("password");
                String role = request.getParameter("role");
                int login = DataPegawai.validateLoginCredential(username, password, role);

                switch (login) {
                    case 0:
                        out.print("<script type=\"text/javascript\">");
                        out.print("alert(\"Unregistered username\");");
                        out.print("</script>");
                        break;
                    case 1:
                        out.print("<script type=\"text/javascript\">");
                        out.print("alert(\"Username or Password or Role was incorrect, bro!\");");
                        out.print("</script>");
                        break;
                    case 2:
                        session.setAttribute("role", "Operator");
                        session.setAttribute("name", request.getParameter("username"));
                        session.setAttribute("username", request.getParameter("username"));
                        response.sendRedirect("halaman-utama-operator.jsp");
                        break;
                    case 3:
                        session.setAttribute("role", "Admin");
                        session.setAttribute("name", request.getParameter("username"));
                        session.setAttribute("username", request.getParameter("username"));
                        response.sendRedirect("halaman-utama-admin.jsp");
                        break;
                    default:
                        System.err.println("ENTER DEFAULT");
                        break;
                }

            }
        %>


        <div class="ui grid">
            <div class="row">
                <div class="six wide column">
                    <div class="ui basic segment" style="
                         /*width: 90%;*/ 
                         position: absolute;">
                        <form method="POST">
                            <h1 style="font-family: 'Amplify_PersonalUseOnly'; font-size: 600%; color: white">
                                Sabha Music Studio
                            </h1>
                            <div class="form segment" id="mtixSignin">
                                <div class="field">
                                    <div class="ui left labeled icon input">
                                        <input name="username" type="text" placeholder="Username">
                                        <i class="user icon"></i>
                                    </div>
                                </div>
                                <br>
                                <div class="field">
                                    <div class="ui left labeled icon input">
                                        <input name="password" type="password" placeholder="Password">
                                        <i class="lock icon"></i>
                                    </div>
                                </div>
                                <br>
                                <div class="field">
                                    <div class="ui selection dropdown">
                                        <input name="role" type="hidden" id="role">
                                        <div class="default text">Connect As</div>
                                        <i class="dropdown icon"></i>
                                        <div class="menu">
                                            <div class="item" data-value="admin" >Admin</div>
                                            <div class="item" data-value="operator" >Operator</div>
                                        </div>
                                    </div>
                                </div>
                                <br>
                                <div class="field">
                                    <input class="ui tiny submit button" type="submit" name="commit" value="Login">
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
        <script src="semantic-ui/packaged/javascript/jquery-2.1.1.js" type="text/javascript"></script>
        <script src="semantic-ui/packaged/javascript/semantic.js" type="text/javascript"></script>
        <script type="text/javascript">
            $(document).ready(function() {
                $('.ui.dropdown').dropdown();
            });
            
            
        </script>
    </body>
</html>
