<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
<title>Spring MVC form submission</title>
</head>

<form:errors />

<body>
	<h2>Fill your form!</h2>

	<form:form method="POST" commandName="book" action="create-book">
		<table>
			<tr>
				<td>Enter your name:</td>
				<td><form:input path="title" /></td>
				<td><form:errors path="title" cssStyle="color: #ff0000;"/></td>
			</tr>

			<tr>
				<td><input type="submit" name="submit" value="Submit"></td>
			</tr>
			<tr>
		</table>
	</form:form>

</body>
</html>