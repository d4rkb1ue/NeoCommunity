<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" 
"http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<title>Neo Initial System Demo</title>
</head>
<body>

	<s:form action="login">
		<s:textfield name="userBean.userName" />
		<s:textfield name="userBean.password" />
		<s:submit key="submit" align="center" />
	</s:form>
	
	
</body>
</html>