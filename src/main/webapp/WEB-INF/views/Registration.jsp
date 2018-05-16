<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Registration page</title>
<script src='https://www.google.com/recaptcha/api.js'></script>

</head>
<body>

	<form:form method="post" action="addUsr" modelAttribute="user">

		<table>
			<tr>
				<td><form:label path="login"><spring:message code="label.login"/></form:label></td>
				<td><form:input path="login"/></td>
				<td><form:errors path="login"/></td>
			</tr>
			<tr>
				<td><form:label path="pass"><spring:message code="label.pass"/></form:label></td>
				<td><form:input type="password" path="pass" /></td>
				<td><form:errors path="pass" /></td>
			</tr>

		
			
			<tr>
				<td><form:label path="email"><spring:message code="label.email"/></form:label></td>
				<td><form:input path="email" /></td>
				<td><form:errors path="email" /></td>
			</tr>
			
			<tr>
				<td><form:label path="tel"><spring:message code="label.tel"/></form:label></td>
				<td><form:input path="tel" /></td>
				<td><form:errors path="tel" /></td>
			</tr>
			
			<tr>
				<td colspan="2">
					<div class="g-recaptcha"
						data-sitekey="6LdSo04UAAAAABMrn9SCCne-pI33FtMi_gCW-n8a"></div></td>
						<td>${message}</td>
				
			</tr>
			<tr>
				<td colspan="2"><input type="submit" value="<spring:message code="label.addUsr"/>" /></td>
			</tr>
			

		</table>
	</form:form>
	<br>
</body>
</html>