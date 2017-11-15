<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Login</title>
<link rel="stylesheet" href="css2/style.css">
<style type="text/css">
.clear {
	clear: both;
}

#top_left {
	width: 100px;
	float: left;
}

#top_right {
	width: 220px;
	float: right;
	margin-top: 40px;
}

#top_right a {
	color: #000;
}

#top_right a:hover {
	color: #909;
}

#bottom_up {
	margin-top: 100px;
}

#bottom_down {
	margin-top: 250px;
}
</style>

<script type="text/javascript">
	function check(form) {
		if (document.forms.loginForm.urn.value=="") {
			alert("Please enter the username!");
			document.forms.loginForm.account.focus();
			return false;
		}
		if (document.forms.loginForm.pwd.value=="") {
			alert("Please enter the password!");
			document.forms.loginForm.password.focus();
			return false;
		}
		
	}
    function librarian(form) {
        document.getElementById("isL").value = "true";
        return check(form);
    }
</script>
    <script>
        var message = '<%=(String)request.getAttribute("message")%>';
        if (message !== 'null'){
            alert(message);
        }
    </script>
</head>
<body>
	<%
	request.getSession().invalidate();
	%>
	<h1>
		<font color="black">XD Babel</font>
	</h1>
	<div id="page">
		<div id="top">
			<div id="top_left">
				<img alt="" src="images/title.png">
			</div>
			<div id="top_right">
				<a href="index.html">Return to Home</a>
			</div>
		</div>
		<div class="clear"></div>

		<div id="bottom">
			<div id="bottom_up">
	<div class="index-main">
		<div class="index-main-body">
			<div class="index-header">
				<h2 class="subtitle"></h2>
			</div>

			<div class="desk-front sign-flow clearfix sign-flow-simple">

				<div class="view view-signup selected" data-za-module="SignInForm">
					<form action="LoginServlet" method="post" name="loginForm">
						<div class="group-inputs">

							<div class="account input-wrapper">

								<input type="text" name="id" aria-label="User_id"
									placeholder="User_id" required>
							</div>
							<div class="verification input-wrapper">
								<input type="password" name="pwd" aria-label="Password"
									placeholder="Password" required />
							</div>
							<div>
								<input type="hidden" id="isL" name="isLibrarian" value="" />
							</div>

						</div>
						<div class="button-wrapper command">
							<button class="sign-button submit" type="submit"
								onclick="return check(this)">Sign In</button>
						</div>
						<div class="button-wrapper command">
							<button class="sign-button submit" type="submit"
								onclick="return librarian(this);">Librarian Sign In</button>
						</div>
						<div class="signin-misc-wrapper clearfix">

							<button type="button" class="signin-switch-button"
								onclick="window.location.href = 'SignUp.jsp'">Register</button>

						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
			</div>

			<div id="bottom_down"
				style="text-align: center; font: normal 14px/24px 'MicroSoft YaHei';">
				<p>Suitable browser：360、FireFox、Chrome、Safari、Opera、sougo and so
					on. IE8 and previous versions are not supported</p>
			</div>

		</div>
	</div>





	<script src="js/jquery.min.js"></script>
	<script src="js/common.js"></script>
	<!--背景图片自动更换-->
	<script src="js/supersized.3.2.7.min.js"></script>
	<script src="js/supersized-init.js"></script>
	<!--表单验证-->
	<script src="js/jquery.validate.min.js?var1.14.0"></script>
	<script>

  		var errori ='<%=request.getParameter("error")%>';
		if (errori == 'yes') {
			alert("Login Failed!");
			window.location.href = 'login.jsp';
		}else if(errori == 'yes1'){
			alert("Please activate !!!");
			window.location.href = 'login.jsp';
		}

	</script>
</body>
</html>