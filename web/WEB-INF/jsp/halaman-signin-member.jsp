<%-- 
    Document   : halaman-signin-member
    Created on : Nov 13, 2014, 9:26:57 PM
    Author     : Lorencius
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Member Area</title>
        <link rel="shortcut icon" href="${pageContext.request.contextPath}/resources/img/OM-Item_Logo.png" type="image/png">
        <link href="${pageContext.request.contextPath}/resources/semantic-ui/packaged/css/semantic.css" rel="stylesheet" type="text/css">
    </head>
    <body>
        <!--Menu bar-->
        <div class="container">
            <div class="ui blue inverted menu">
                <a class="item" href="welcome">
                    <i class="home icon"></i> HOME
                </a>
                <a class="item" href="member">
                    <i class="user icon"></i> MEMBER AREA
                </a>
                <a class="item" href="help">
                    <i class="video icon"></i> HELP
                </a>
            </div>
        </div>
        <!--End of Menu bar-->

        <!--Main body-->
        <div class="main container">
            <div class="ui error message">
                <div class="header">
                    You have to sign in
                </div>
                <p>Fill the form with your User ID and Password if you are already a member of our M-TIX program
                    then do sign in by clicking the Sign in button. <br> If you are facing a problem please contact 
                    our Administrator.
                </p>
            </div>
            <div class="ui grid">
                <div class="four wide column">
                    <h4 class="ui top center aligned attached inverted blue block header">
                        SIGN IN
                    </h4>
                    <form class="ui form segment attached" id="mtixSignin" action="loginmember" method="POST">
                        <div class="field">
                            <div class="ui blue ribbon label">Username</div>
                            <div class="ui left labeled icon input">
                                <input name="username" type="text" placeholder="Username">
                                <i class="user icon"></i>
                            </div>
                        </div>
                        <div class="field">
                            <div class="ui blue ribbon label">Password</div>
                            <div class="ui left labeled icon input">
                                <input name="password" type="password" placeholder="Password">
                                <i class="lock icon"></i>
                            </div>
                        </div>
                        <div class="field">${message}</div>
                        <div class="field">
                            <input class="ui tiny blue submit button" type="submit" name="commit" value="Sign in">
                        </div>
                    </form>
                </div>
            </div>
        </div>
        <!--End of Main body-->

        <!--Script-->
        <script src="${pageContext.request.contextPath}/resources/semantic-ui/packaged/javascript/jquery-2.1.1.js" type="text/javascript"></script>
        <script src="${pageContext.request.contextPath}/resources/semantic-ui/packaged/javascript/semantic.js" type="text/javascript"></script>
        <script type="text/javascript">
            //Reset login sidebar value when reload
            var originalState = $('#mtixSignin').clone();
            $('#mtixSignin').replaceWith(originalState);

            $(document).ready(function() {
                $("#mtixSignin").form({
                    username: {
                        identifier: 'username',
                        rules: [
                            {
                                type: 'empty',
                                prompt: 'Masukkan username'
                            }
                        ]
                    },
                    password: {
                        identifier: 'password',
                        rules: [
                            {
                                type: 'empty',
                                prompt: 'Masukkan password'
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
