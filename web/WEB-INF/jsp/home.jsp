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
        <%@include file="menubar-home.jsp" %>
        <!--Main body-->
        <div class="main container">
            <div class="ui grid" style="position: absolute;">
                <div class="row">
                    <div class="three wide column">
                        <h4 class="ui top attached center aligned inverted blue block header">
                            KEUNTUNGAN MEMBER
                        </h4>
                        <div class="ui segment attached">
                            <img src="${pageContext.request.contextPath}/resources/img/OMitem_Benefit.png" style="width: 90%">
                        </div>
                    </div>
                    <div class="ten wide column">
                        <h4 class="ui top center aligned attached inverted blue block header">
                            WARTA TERBARU
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
                            EVENT YANG AKAN DATANG
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

            });
        </script>
    </body>
</html>

