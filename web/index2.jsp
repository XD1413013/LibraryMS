<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Administrator Page</title>
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
    var remindi ='<%=request.getParameter("return")%>';
    if (remindi == 'yes'){
        alert("Return successfully! ");
    }
    if(remindi == 'no'){
    	alert("Return failed!");
    }
</script>
<!---->
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
    <script>
        var message = '<%=(String)request.getAttribute("message")%>';
        if (message !== 'null'){
            alert(message);
        }
    </script>
</head>	
<!--banner-->
<body>

<%
		String librarian_id = (String) request.getSession().getAttribute("librarian_id");
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
		<div id="top" >
				<%
					if (librarian_id == null) {
				%>
				<a href="login.jsp" >Sign in</a>
				<%
					} else {
				%>
				<a href="login.jsp">Sign out</a>
				<%
					}
				%>
		</div>
		 <div class="container" style="margin-top: 150px;">
			 <nav class="cl-effect-1">
				<span class="menu"><img src="images/nav-icon.png" alt=""/></span>
			 <ul>
					<%
						if (librarian_id == null) {
					%>
						<li><a class="scroll" href="#home">Add</a></li>
						<li><a class="scroll" href="#home">Delete</a></li>
						<li><a class="scroll" href="#home">Change</a></li>
						<li><a class="scroll" href="#home">Query</a></li>
						<li><a class="scroll" href="#home">Borrow</a></li>
						<li><a class="scroll" href="#home">Return</a></li>
                        <li><a class="scroll" href="#home">Income</a></li>
                        <li><a class="scroll" href="#home">Personal</a></li>
					<%
						} else {
					%>
						<li><a class="scroll" href="add.jsp">Add</a></li>
						<li><a class="scroll" href="delete.jsp">Delete</a></li>
						<li><a class="scroll" href="change.jsp">Change</a></li>
						<li><a class="scroll" href="query.jsp">Query</a></li>
						<li><a class="scroll" href="borrow.jsp">Borrow</a></li>
						<li><a class="scroll" href="return.jsp">Return</a></li>
				        <li><a class="scroll" href="income.jsp">Income</a></li>
				        <li><a class="scroll" href="libPersonalServlet">Personal</a></li>
					<%
						}
					%>
			 </ul>
			 </nav>
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
				/*
				var defaults = {
				containerID: 'toTop', // fading element id
				containerHoverID: 'toTopHover', // fading element hover id
				scrollSpeed: 1200,
				easingType: 'linear' 
				};
				*/
		$().UItoTop({ easingType: 'easeOutQuart' });
});
</script>
<a href="#" id="toTop" style="display: block;"> <span id="toTopHover" style="opacity: 1;"> </span></a>
<!----> 
</body>
</html>