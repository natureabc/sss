<html>
<head>
	<link rel="stylesheet" href="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
	<script src="https://cdn.staticfile.org/jquery/2.1.1/jquery.min.js"></script>
	<script src="https://cdn.staticfile.org/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
	<style>
		body
		{
			background-color: #96b97d;
		}
		.mycenter
		{
			margin-top: 100px;
			margin-left: auto;
			margin-right: auto;
			height: 350px;
			width: 500px;
			padding: 5%;
			padding-left: 5%;
			padding-right: 5%;
		}
		.mycenter mysign
		{
			width: 440px;
		}
		.mycenter input, checkbox, button
		{
			margin-top: 2%;
			margin-left: 10%;
			margin-right: 10%;
		}
		.mycheckbox
		{
			margin-top: 10px;
			margin-left: 40px;
			margin-bottom: 10px;
			height: 10px;
		}
	</style>
</head>
<body>
<#--<form id="from" action="/login/doLogin" method="post">-->
	<div class="mycenter">
		<div class="mysign">
			<div class="col-lg-11 text-center text-info">
				<h2>
					后台管理</h2>
			</div>
			<div class="col-lg-10">
				<input type="text" class="form-control" id="account" name="userName" placeholder="请输入账户名" required
					   autofocus />
			</div>
			<div class="col-lg-10">
			</div>
			<div class="col-lg-10">
				<input type="password" class="form-control" id="password" name="password" placeholder="请输入密码" required
					   autofocus />
			</div>
			<div class="col-lg-10">
			</div>
			<#if error?exists>
				<div class="col-lg-10 " style="margin-left: 10%;">
					<span  style="color:#ff1f46">用户名密码不正确</span>
				</div>
			<#else >

			</#if>
			<#--<div class="col-lg-10 mycheckbox checkbox">
				<input type="checkbox" class="col-lg-1">记住密码</input>
			</div>-->
			<div class="col-lg-10">
			</div>
			<div class="col-lg-10">
				<input type="submit" id="btn" class="btn btn-success col-lg-12" value="登录" onclick="doLogin()">
			</div>
		</div>
	</div>
<#--</form>-->
</body>

<script type="text/javascript">

	<#--var error=${{Session["error"]};
	if(error){
		$("#errorMsg").html(error);
	}-->
	function doLogin(){
		//console.log("cccsss")
		var account=$("#account").val();
		var password=$("#password").val();
		window.location.href="/login/doLogin?userName="+account+"&password="+password;
	}

</script>
</html>