<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<%@include file="home.jsp" %>

<!--<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>SMS</title>
        <link rel="shortcut icon" href="/img/" type="image/png">
        <link href="<c:out value="${pageContext.request.contextPath}/resources/semantic-ui/packaged/css/semantic.css" />" rel="stylesheet" type="text/css">
        <style>
            @font-face{
                font-family: "Amplify_PersonalUseOnly";
                font-weight: bold;
                src: url('${pageContext.request.contextPath}/resources/font/Amplify_PersonalUseOnly.ttf');
            }
        </style>
    </head>
    <body style="
          background-image: url('<c:out value="${pageContext.request.contextPath}/resources/img/Studio.jpg" />'); 
          background-repeat: no-repeat; 
          background-size: cover; 
          background-attachment: fixed; 
          background-position: center;
          opacity: 1.0;">

        <div class="ui grid">
            <div class="row">
                <div class="six wide column">
                    <div class="ui basic segment" style="
                         /*width: 90%;*/ 
                         position: absolute;">
                        <form action="login/pegawai" method="POST">
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
                                div class="field">
                                    <div class="ui selection dropdown">
                                        <input name="role" type="hidden" id="role">
                                        <div class="default text">Connect As</div>
                                        <i class="dropdown icon"></i>
                                        <div class="menu">
                                            <div class="item" data-value="admin" >Admin</div>
                                            <div class="item" data-value="operator" >Operator</div>
                                        </div>
                                    </div>
                                </div
                                <p style="color: white">${message}</p>
                                <br>
                                <div class="field">
                                    <input class="ui tiny submit button" type="submit" name="commit" value="Login">
                                </div>
                            </div>
                        </form>
                        <form action="login/member" method="POST">
                            <h1 style="font-family: 'Amplify_PersonalUseOnly'; font-size: 600%; color: white">
                                Login Member
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
                                div class="field">
                                    <div class="ui selection dropdown">
                                        <input name="role" type="hidden" id="role">
                                        <div class="default text">Connect As</div>
                                        <i class="dropdown icon"></i>
                                        <div class="menu">
                                            <div class="item" data-value="admin" >Admin</div>
                                            <div class="item" data-value="operator" >Operator</div>
                                        </div>
                                    </div>
                                </div
                                <p style="color: white">${message}</p>
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
        <script src="<c:out value="${pageContext.request.contextPath}/resources/semantic-ui/packaged/javascript/jquery-2.1.1.js" />" type="text/javascript"></script>
        <script src="<c:out value="${pageContext.request.contextPath}/resources/semantic-ui/packaged/javascript/semantic.js" />" type="text/javascript"></script>
        <script type="text/javascript">
            $(document).ready(function() {
                $('.ui.dropdown').dropdown();
            });


        </script>
    </body>
</html>-->

<!--html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Welcome to Spring Web MVC project</title>
    </head>

    <body>
        <p>Hello! This is the default welcome page for a Spring Web MVC project.</p>
        <p><i>To display a different welcome page for this project, modify</i>
            <tt>index.jsp</tt> <i>, or create your own welcome page then change
                the redirection in</i> <tt>redirect.jsp</tt> <i>to point to the new
                welcome page and also update the welcome-file setting in</i>
            <tt>web.xml</tt>.</p>
        <form action="coba/coba.htm" method="get"><input type="submit"></form>
    </body>
</html-->
