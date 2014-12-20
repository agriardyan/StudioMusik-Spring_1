<%-- 
    Document   : menubar-home
    Created on : Dec 19, 2014, 8:14:14 AM
    Author     : root
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="<c:out value="${pageContext.request.contextPath}/resources/semantic-ui/packaged/css/semantic.css" />" rel="stylesheet" type="text/css">        
    </head>
    <body>
        <!--Menu bar-->
        <div class="ui blue inverted main menu">
            <div class="container">
                <a class="active item" href="welcome">
                    <i class="home icon"></i> HOME
                </a>
                <a class="item" href="member">
                    <i class="user icon"></i> MEMBER AREA
                </a>
                <a class="item" href="help">
                    <i class="video icon"></i> BANTUAN
                </a>
                <div class="right menu">
                    <a class="item" id="loginButton">
                        <i class="sign in icon"></i> LOGIN
                    </a>
                </div>
            </div>
        </div>
        <!--End of Menu bar-->

        <!--Login Sidebar-->
        <div class="ui black small vertical right sidebar menu" id="loginSidebar">
            <div class="item">
                <form class="ui form basic segment" method="POST" id="sideLogin" action="login">
                    <c:if test="${!empty messagePegawai}">
                        <div class="field">
                            <div class="ui error form segment">
                                <div class="ui error message">
                                    <div class="header">Gagal Login</div>
                                    <p>${messagePegawai}</p>
                                </div>      
                            </div>
                        </div>
                    </c:if>
                    <div class="field">
                        <div class="ui blue ribbon label">Username</div>
                        <div class="ui left labeled icon input">
                            <input name="username" id="user" type="text" placeholder="Username" value="${tempName}">
                            <i class="user icon"></i>
                        </div>
                    </div>
                    <div class="field">
                        <div class="ui blue ribbon label">Password</div>
                        <div class="ui left labeled icon input">
                            <input name="password" id="pass" type="password" placeholder="Password">
                            <i class="lock icon"></i>
                        </div>
                    </div>
                    <div class="field">
                        <input class="ui button" type="submit" name="commit" value="SIGN IN">
                    </div>
                </form>
            </div>
        </div>
        <!--End of Login Sidebar-->

        <script src="${pageContext.request.contextPath}/resources/semantic-ui/packaged/javascript/jquery-2.1.1.js" type="text/javascript"></script>
        <script src="${pageContext.request.contextPath}/resources/semantic-ui/packaged/javascript/semantic.js" type="text/javascript"></script>
        <script type="text/javascript">
            $(document).ready(function() {
                
            <c:if test="${!empty messagePegawai}">
                    $("#loginSidebar").sidebar('toggle');
            </c:if>

                //Login button handler
                $("#loginButton").click(function() {
                    $("#loginSidebar").sidebar('toggle');
                });

                //Login sidebar error prompt
                $("#sideLogin").form({
                    username: {
                        identifier: 'user',
                        rules: [
                            {
                                type: 'empty',
                                prompt: 'Please enter a username'
                            }
                        ]
                    },
                    password: {
                        identifier: 'pass',
                        rules: [
                            {
                                type: 'empty',
                                prompt: 'Please enter a password'
                            }
                        ]
                    }
                }, {
                    on: 'submit',
                    inline: 'true'
                });
            });
        </script>
    </body>
</html>
