<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<form method="get" action="/checkslot">
		Enter Pin: <input type="text" name="pincode" /><br> <br>
		Enter Date: <input type="date" name="date"><br> <br>
		<input type="submit" value="submit">
	</form>
	
	${respdata}
</body>
</html>