<%--
  Created by IntelliJ IDEA.
  User: 89789
  Date: 2017/10/24
  Time: 22:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Reader Operation</title>
    <link href="css3/bootstrap.css" rel="stylesheet" type="text/css" media="all">
    <link href="css3/style.css" rel="stylesheet" type="text/css" media="all" />
    <link rel="stylesheet" href="css3/chocolat.css" type="text/css" media="screen"/>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta name="keywords" content="Progress Responsive web template, Bootstrap Web Templates, Flat Web Templates, Andriod Compatible web template,
Smartphone Compatible web template, free webdesigns for Nokia, Samsung, LG, SonyErricsson, Motorola web design" />

    <script type="application/x-javascript"> addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } </script>
    <script src="js/modernizr.custom.97074.js"></script>
    <script src="js/jquery-1.8.3.min.js"></script>
    <!---->
    <script type="text/javascript" src="js2/move-top.js"></script>
    <script type="text/javascript" src="js2/easing.js"></script>
    <script type="text/javascript">
        jQuery(document).ready(function($) {
            $(".scroll").click(function(event){
                event.preventDefault();
                $('html,body').animate({scrollTop:$(this.hash).offset().top},1200);
            });
        });
    </script>
 <script>
    var remindi ='<%=request.getParameter("re")%>';
    if (remindi == 'no'){
        alert("You have not reserved books!!!");
    }
</script>
    <!---->
 <script>
    var remindi ='<%=request.getParameter("result")%>';
    if (remindi == 'no'){
        alert("Please try again !!!");
    }
    if (remindi == 'yes'){
        alert("Cancel Successfully !!!");
    }
    var remindi1 ='<%=request.getParameter("reserve")%>';
    if (remindi1 == 'no'){
        alert("There have no enough book");
    }
    if (remindi1 == 'yes'){
        alert("Reserve successfully!");
    }
    if (remindi1 == 'no2'){
        alert("Reserve failed!");
    }  if (remindi1 == 'no3'){
        alert("More than number limition!");
    }
    var remindi2 ='<%=request.getParameter("personal")%>';
    if (remindi2 == 'no'){
        alert("There have no records!");
    }
    var remindi3 ='<%=request.getParameter("remind")%>';
    if (remindi3 == 'yes'){
        alert("Modify Successfully!");
    }
</script>
</head>
<!--banner-->
<body>

<%
    String id = (String) request.getSession().getAttribute("reader_id");
%>
<div id="home" class="banner">
    <div class="header">
        <div class="container">
            <div class="logo">
                <a href="index.html"><img src="images/title.png"
                                          alt="xidian-logo" /></a> <h1><a href="#">XD Babel</a></h1>
            </div>
        </div>
    </div>
    <div class="top-menu">
        <div style="margin-top: -70px; float: right; width: 140px; font-size: 18px">
            <%
                if (id == null) {
            %>
            <a href="login.jsp">Sign in</a>
            <%
            } else {
            %>
            <a href="login.jsp">Sign out</a>&nbsp;&nbsp;&nbsp;<a href="search.jsp" >Back</a>
            <%
                }
            %>
        </div>
    </div>
    <div class="banner-form" style="margin-top: 50px">
        <div class="container">
            <%
                if (id != null) {
            %>
            <form action="HistoryServlet" method="post">
                <br>
                <input type="submit" value="   Borrow History   ">
            </form>
            <br>
            <form action="PersonalServlet" method="post">
                <br>
                <input type="submit" value="Personal Information">
            </form>
            <br>
            <form action="QueryReserveServlet" method="post">
                <br>
                <input type="submit" value="  Personal_Reserve  ">
            </form>
            <%
            } else {
            %>
            <div style="margin-top: 10px;font-size: 30px" align="center" > 
            <a href="login.jsp">Please Sign in !</a>
            </div>
            <%
                }
            %>
        </div>
    </div>
</div>
<!---->
<script src="js2/responsiveslides.min.js"></script>
<script>
    // You can also use "$(window).load(function() {"
    $(function () {
        $("#slider2").responsiveSlides({
            auto: true,
            pager: true,
            speed: 300,
            namespace: "callbacks",
        });
    });
</script>
<!---->
<div class="footer">
    <div class="container">
        <div class="copywrite">
            <p>Copyright &copy; 2015.Company name All rights reserved</p>
        </div>
        <div class="social">
            <a href="#"><i class="facebook"></i></a>
            <a href="#"><i class="twitter"></i></a>
            <a href="#"><i class="dribble"></i></a>
            <a href="#"><i class="google"></i></a>
            <a href="#"><i class="youtube"></i></a>
        </div>
        <div class="clearfix"></div>
    </div>
</div>
<!---->
<script type="text/javascript">
    $(document).ready(function() {
        $().UItoTop({ easingType: 'easeOutQuart' });
    });
</script>
<a href="#" id="toTop" style="display: block;"> <span id="toTopHover" style="opacity: 1;"> </span></a>
<!---->
</body>
</html>