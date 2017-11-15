<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Sign up</title>
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
	function check() {
		var id = document.getElementById("id");
		var urn = document.getElementById("urn");
		var sex = document.getElementById("sex");
		var pwd = document.getElementById("pwd");
		var c_pwd = document.getElementById("c_pwd");
		if (id.value=="") {
			alert("Please enter the id");
			return false;
		}if(urn.value==""){
			alert("Please enter the username");
			return false;
		}if(sex.value==""){
			alert("Please enter the sex");
			return false;
		}if(pwd.value==""){
			alert("Please enter the password");
			return false;
		}if(c_pwd.value==""){
			alert("Please enter the confirm");
			return false;
		}if(pwd.value!=c_pwd.value){
			alert("Two input password does not match!");
			return false;
		}if(pwd.value.length<6){
			alert("Password's length must be more than 5");
			return false;
		}if(!(/^(141301)[1234]\d{4}$/.test(id.value))){
			alert("ID fomat error");
			return false;
		} if(!(/^(male)|^(female)$/.test(sex.value))){
			alert("Please input male or female");
			return false;
		}
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
				<a href="login.jsp">Back</a>
			</div>
		</div>
		<div class="clear"></div>

		<div id="bottom">
			<div id="bottom_up">
<div class="index-main">
    <div class="index-main-body" style= "margin-top: -100px;">
        <div class="index-header">
            <h2 class="subtitle"></h2>
        </div>

        <div class="desk-front sign-flow clearfix sign-flow-simple" >

            <div class="view view-signup selected" data-za-module="SignUpForm">
                <form class="zu-side-login-box" action="SignUpServlet" method="post" name="loginUpForm" id="sign-form-1" autocomplete="off">
                    <div class="group-inputs">
						
						<div class="name input-wrapper">
                            <input required type="text" id="id" name="reader_id" aria-label="reader_id" placeholder="Sturdent ID"><span id="msg"></span>
                        </div>
                        <div class="name input-wrapper">
                            <input required type="text" id="urn" name="urn" aria-label="reader_name" placeholder="reader_name">
                        </div>

						<div class="name input-wrapper">
                            <input required type="text" id="sex" name="sex" aria-label="reader_sex" placeholder="sex: male or female"><span id="msg2"></span>
                        </div>	
							
                        <div class="input-wrapper">
                            <input required type="password" id="pwd" name="pwd" aria-label="Password" placeholder="Password" autocomplete="off">
                        </div>

                        <div class="input-wrapper">
                            <input required type="password" id="c_pwd" name="c_pwd" aria-label="Confirmation" placeholder="Password Confirmation" autocomplete="off">
                        </div>

                    </div>
                    <div class="button-wrapper command">
                        <button class="sign-button submit" type="submit" onclick="return check()">Register</button>
                    </div>
                </form>
				
					<script type="text/javascript">
	
		var id = document.getElementById("id");
		var sex = document.getElementById("sex");
		var msg = document.getElementById('msg');
		var msg2 = document.getElementById('msg');
		id.onfocus = function( e ){
			this.style.borderColor = "red";
		}
		sex.onfocus = function( e ){
			this.style.borderColor = "red";
		}
		
		sex.onblur = function( e){
			
			if((/^(male)|^(female)$/.test(sex.value))){
				msg.style.color = "green";
				this.style.borderColor = 'green';
				
			} else {
				alert("Please input male or female!");
				msg.style.color = "red";
				this.style.borderColor = "red";
			}
		}
		
		id.onblur = function( e){
			
			if((/^(141301)[1234]\d{4}$/.test(id.value))){
				msg.style.color = "green";
				this.style.borderColor = 'green';
				
			} else {
				alert("format error!");
				msg.style.color = "red";
				this.style.borderColor = "red";
			}
		}
		
	</script>
				
				
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
		}
	</script>
</body>
</html>