<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<form action="hello" method="post" enctype
="multipart/form-data" name="form1" id="form1">
<center>
<table border="2">
<tr>
<td align="right"><b>Employee Name:</td>
<td ><input type="text" name="emp_name"></td>
</tr>
<tr>
<td align="right"><b>Employee Address:</td>
<td ><input type="text" name="address1"></td>


</tr>
<tr>
<td>
</td>
<td>
<input type="text" name="address2">
</td>
</tr>
<tr>
<td align="right"><b>Contact Number:</td>
<td ><input type="text" name="contact_number"></td>
</tr>
<tr>
<td align="right"><b>Employee Email ID:</td>
<td ><input type="text" name="email_id"></td>
</tr>


<tr>
<td align="right"><b>Employee Image </td>
<td>
<input name="file" type="file" id="file">
<td>
</tr>



<tr>
<td align="center">
<input type="submit" name="Submit" value="Submit"/>
<input type="reset" name="Reset" value="Reset"/>

</td>
</tr>
</table>
</center>

</form>
</body>
</html>