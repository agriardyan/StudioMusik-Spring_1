<%-- 
    Document   : home
    Created on : Nov 9, 2014, 12:35:02 PM
    Author     : Lorencius
--%>

<!DOCTYPE html>
<html>
    <head>
        <title>Sabha IS</title>
        <link rel="shortcut icon" href="img/OM-Item_Logo.png" type="image/png">
        <link href="${pageContext.request.contextPath}/resources/semantic-ui/packaged/css/semantic.css" rel="stylesheet" type="text/css">
        <link href="${pageContext.request.contextPath}/resources/bxslider/jquery.bxslider.css" rel="stylesheet" type="text/css">
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
<!--                
                <a class="item" href="help">
                    <i class="video icon"></i> HELP
                </a>-->
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
                    <div class="field">
                        <div class="ui blue ribbon label">Username</div>
                        <div class="ui left labeled icon input">
                            <input name="username" id="user" type="text" placeholder="Username">
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

        <!--Main body-->
        <div class="main container">
            <div class="ui grid" style="position: absolute;">
                <div class="row">
                    <div class="three wide column">
                        <h4 class="ui top attached inverted blue block header">
                            BENEFIT
                        </h4>
                        <div class="ui segment attached">
                            <img src="${pageContext.request.contextPath}/resources/img/OMitem_Benefit.png" style="width: 90%">
                        </div>
                    </div>
                    <div class="ten wide column">
                        <h4 class="ui top center aligned attached inverted blue block header">
                            LATEST NEWS
                        </h4>
                        <div class="ui segment attached">
                            <ul id="slider1">
                                <li><img src="${pageContext.request.contextPath}/resources/img/Kucing Berdoa.jpg" alt="slide1" title="Kucing Berdoa"></li>
                                <li><img src="${pageContext.request.contextPath}/resources/img/Kucing Galau.jpg" alt="slide2" title="Kucing Galau"></li>
                                <li><img src="${pageContext.request.contextPath}/resources/img/Kucing Natal.jpg" alt="slide2" title="Kucing Natal"></li>
                                <li><img src="${pageContext.request.contextPath}/resources/img/Kucing Fotografer.jpg" alt="slide2" title="Kucing Fotografer"></li>
                            </ul>
                        </div>
                        <h4 class="ui top center aligned attached inverted blue block header">
                            UPCOMING EVENT
                        </h4>
                        <div class="ui segment attached">
                            <ul id="slider2">
                                <li><img src="${pageContext.request.contextPath}/resources/img/11.jpg" alt="slide1" title="Domo"></li>
                                <li><img src="${pageContext.request.contextPath}/resources/img/12.jpg" alt="slide2" title="AKB48"></li>
                                <li><img src="${pageContext.request.contextPath}/resources/img/13.jpg" alt="slide3" title="The North Face"></li>
                                <li><img src="${pageContext.request.contextPath}/resources/img/12.jpg" alt="slide2" title="AKB48"></li>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!--End of Main body-->

        <!--Script-->
        <script src="${pageContext.request.contextPath}/resources/semantic-ui/packaged/javascript/jquery-2.1.1.js" type="text/javascript"></script>
        <script src="${pageContext.request.contextPath}/resources/bxslider/jquery.bxslider.js" type="text/javascript"></script>
        <script src="${pageContext.request.contextPath}/resources/semantic-ui/packaged/javascript/semantic.js" type="text/javascript"></script>
        <script type="text/javascript">
            $(document).ready(function() {
                //Slideshow 1
                $('#slider1').bxSlider({
                    speed: 1000, //transition speed
                    mode: 'horizontal', //transition mode
                    captions: true, //captions based on its title
                    auto: true, //auto slide
                    autoStart: true, //auto start when the page load
                    autoControls: false,
                    adaptiveHeight: true
                });

                //Slideshow 2
                $('#slider2').bxSlider({
                    speed: 1000,
                    auto: true,
                    autoStart: true,
                    autoControls: false,
                    captions: true,
                    slideWidth: 200,
                    minSlides: 4,
                    maxSlides: 4,
                    moveSlides: 1
                });

                //Login button handler
                $("#loginButton").click(function() {
                    $("#loginSidebar")
                            .sidebar('toggle');
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
                    on: 'blur',
                    inline: 'true'
                });
            });
        </script>
    </body>
</html>

