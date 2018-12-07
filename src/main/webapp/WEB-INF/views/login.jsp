<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>

	<head>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<title>登录</title>
		<!-- Tell the browser to be responsive to screen width -->
		<meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
		<!-- Bootstrap 3.3.7 -->
		<link rel="stylesheet" href="/static/bower_components/bootstrap/dist/css/bootstrap.min.css">
		<!-- Font Awesome -->
		<link rel="stylesheet" href="/static/bower_components/font-awesome/css/font-awesome.min.css">
		<!-- Ionicons -->
		<link rel="stylesheet" href="/static/bower_components/Ionicons/css/ionicons.min.css">
		<!-- Theme style -->
		<link rel="stylesheet" href="/static/dist/css/AdminLTE.min.css">
		<!-- iCheck -->
		<link rel="stylesheet" href="/static/plugins/iCheck/square/blue.css">
	</head>

	<body class="hold-transition login-page">
		<div class="login-box">
			<%--<div class="login-logo">
				<a href="./index.html">后台管理系统</a>
			</div>--%>
			<!-- /.login-logo -->
			<div class="login-box-body">
				<!--<p class="login-box-msg">Sign in to start your session</p>-->

				<form action="/login" method="post">
					<div class="form-group has-feedback">
						<input type="text" class="form-control" name="username" placeholder="请输入用户名" style="margin-right:9px;">
						<span class="fa fa-fw fa-user form-control-feedback login-user-img"></span>
					</div>
					<div class="form-group has-feedback">
						<input type="password" class="form-control" name="password"  placeholder="请输入密码">
						<span class="glyphicon glyphicon-lock form-control-feedback"></span>
					</div>
					<div class="form-group has-feedback">
						<input type="password" class="form-control" placeholder="请输入验证码" style="width:50%;display:inline-block;">
						<input type="button" class="code-index-yan js-index-yan" style="width:30%;height:30px;margin-left:10%;" />
					</div>
					<div class="row">
						<div class="col-xs-8">
							<div class="checkbox icheck">
								<label>
              						<input type="checkbox"> 记住我
            					</label>
							</div>
						</div>
						<div class="col-xs-4">
							<button type="submit" class="btn btn-primary btn-block btn-flat">登录</button>
						</div>
					</div>
				</form>
			</div>
		</div>
		<script src="/static/bower_components/jquery/dist/jquery.min.js"></script>
		<!-- Bootstrap 3.3.7 -->
		<script src="/static/bower_components/bootstrap/dist/js/bootstrap.min.js"></script>
		<!-- iCheck -->
		<script src="/static/plugins/iCheck/icheck.min.js"></script>
		<script>
			$(function() {
				var code; //在全局定义验证码 
				//验证码
				createCode();
				$('input').iCheck({
					checkboxClass: 'icheckbox_square-blue',
					radioClass: 'iradio_square-blue',
					increaseArea: '20%' /* optional */
				});
				//点击切换验证码
				$(".js-index-yan").click(function(){
					createCode();
				})
				/**
				 * 验证码方法
				 */
				function createCode() {
					code = "";
					var codeLength = 4; //验证码的长度 
					var random = new Array(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R',
						'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'); //随机数 
					for(var i = 0; i < codeLength; i++) { //循环操作 
						var index = Math.floor(Math.random() * 36); //取得随机数的索引（0~35） 
						code += random[index]; //根据索引取得随机数加到code上 
					}
					$(".js-index-yan").val(code); //把code值赋给验证码 
				}
			});
			
			
			//校验验证码 
//			function validate() {
//				var inputCode = document.getElementById("input").value.toUpperCase(); //取得输入的验证码并转化为大写 
//				if(inputCode.length <= 0) { //若输入的验证码长度为0 
//					alert("请输入验证码！"); //则弹出请输入验证码 
//				} else if(inputCode != code) { //若输入的验证码与产生的验证码不一致时 
//					alert("验证码输入错误！@_@"); //则弹出验证码输入错误 
//					createCode(); //刷新验证码 
//					document.getElementById("input").value = ""; //清空文本框 
//				} else { //输入正确时 
//					alert("^-^"); //弹出^-^ 
//				}
//			}
		</script>
	</body>

</html>