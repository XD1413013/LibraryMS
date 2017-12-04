<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Modify</title>
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
	
<script type="text/javascript">
	function check(form) {
		if (document.forms.loginForm.pwd.value=="") {
			alert("Please enter the password!");
			document.forms.loginForm.password.focus();
			return false;
		}
		
	}
</script>
<script>
    var remindi ='<%=request.getParameter("remind4")%>';
    if (remindi == 'no'){
        alert("Please try again");
    }
</script>

<script type="text/javascript">
	function check(form) {
		var psw = document.getElementById("psw");
		var c_psw = document.getElementById("c_psw");
		if (psw.value==""||psw.value.length>20) {
			alert("Please enter the password");
			return false;
		}
		if(c_psw.value==""||c_psw.value.length>20){
			alert("Please enter the confirm");
			return false;
		}
		if(psw.value!=c_psw.value){
			alert("Two input password does not match!");
			return false;
		}
		if(psw.value.length<6){
			alert("Password's length must be more than 5");
			return false;
		}
	}
</script>

<style type="text/css">

        #top{
            width: 280px;
            float: right;
            margin-top: -70px;
        }

        #top a {
            color: #2e6da4;
            font-size:18px;
        }

        #top a:hover {
            color: #909;
        }
</style>
<!---->
</head>	
<!--banner-->
<body>

<%
		String id = (String) request.getSession().getAttribute("librarian_id");
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
					<div id="top">
					<%
						if (id == null) {
					%>
					<a href="login.jsp">Sign in</a>
					<a href="index2.jsp">&nbsp;&nbsp; Back</a>
					<%
						} else {
					%>
					<a href="login.jsp">Sign out</a>
					<a href="index2.jsp">&nbsp;Back</a>
					<%
						}
					%>
			</div>
		<!-- script-for-menu -->
		<script>
            $("span.menu").click(function(){
                $(".top-menu ul").slideToggle("slow" , function(){
                });
            });
		</script>
		<!-- script-for-menu -->
	</div>
	 <div class="banner-form" style="margin-top: 50px">
		 <div class="container">
			<%
				if (id != null) {
			%>
				 <form action="libPersonalModifyServlet" method="post">
					<br>
					<input required type="text" value="" placeholder="New Password" name="pwd" id="psw"><br>
					<input required type="text" value="" placeholder="Confirm" name="c_pwd" id="c_psw"><br>
					<input type="submit" onclick="return check(this)" value="Send">
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