<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" 
"http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta charset="utf-8" />
<title>登陆</title>
<link rel="stylesheet" type="text/css" href="css/login.css" />
</head>
<body>
	<div class="content">
		<div class="contentleft">
			<img src="img/loginleft.png" />
		</div>
		<div class="contentright">
			<div id="crtop">
				<img src="img/logoNEO.png" />
				<h2>NEO</h2>
			</div>
			<div id="crbottom">
				<span>小区账户</span>
				<form action="login" method="post">
					<input type="text" name="userBean.username" placeholder="账号"
						class="input" /> <input type="password" name="userBean.password"
						placeholder="密码" class="input" /> <input type="checkbox"
						id="ckbox" /> <span>使我保持登录状态</span> <input type="submit"
						id="loginbtn" value="登录" /> <a id="notlogin" href="#">无法访问你的账户？</a>
				</form>
				<s:form action="login" theme="simple">
					<s:textfield name="userBean.userName" key="user.name" />
					<s:textfield name="userBean.password" key="user.password" />
					<p></p>
					<s:submit key="submit" align="center" />
				</s:form>
			</div>
		</div>
	</div>
	<!--end content-->
	<div class="footer">
		<div id="footercon">
			<div id="NEO">NEO</div>
			<div id="members">
				<p class="at">@2015 NEO</p>
				<p class="name">吴庆龄</p>
				<p class="name">路亚辉</p>
				<p class="name">康媛</p>
				<p class="name">苏圣</p>
			</div>
		</div>
	</div>





</body>
</html>