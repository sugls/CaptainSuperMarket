<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>incomeMain</title>
	<link rel="stylesheet" href="../css/reset.css">
	<link rel="stylesheet" href="../css/main.css">
	<script type="text/javascript" src="../js/index.js"></script>
	<script type="text/javascript">
		function checkform() {
			if (document.getElementById("income").value==""){
			    alert("please input daily income");
			    document.getElementById("income").focus();
				return false;
			} else {
			    return true;
			}
        }
	</script>
</head>
<body>
<jsp:include page="head.jsp"></jsp:include>
<div class="body bg-yellow">
	<c:choose><c:when test="${user.useridentity==0}"><%@ include file="managerbar.jsp"%></c:when><c:otherwise><%@include file="cashierbar.jsp"%></c:otherwise></c:choose>
	<div class="w70pct display-inline-block">
		<form action="income?option=addincome" method="post" onsubmit="return checkform()">
		<label for="departname">DepartmentName</label><select name="departname" id="departname"><c:forEach items="${listdepart}" var="depart" varStatus="status"><option>${depart.departname}</option></c:forEach> </select><br>
		<label for="income">Daily Income:</label><input type="number" name="income" id="income">$ *input daily income without unit<br>
		<input type="submit" value="Add" name="">&nbsp;
		<input type="reset" value="Reset" name="">
		</form>
	</div>
</div>
<jsp:include page="foot.jsp"></jsp:include>
</body>
</html>