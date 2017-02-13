<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>billPaymentMain</title>
	<link rel="stylesheet" href="../css/reset.css">
	<link rel="stylesheet" href="../css/main.css">
	<script type="text/javascript" src="../js/index.js"></script>
</head>
<body>
<jsp:include page="head.jsp"></jsp:include>
<div class="body bg-yellow">
	<c:choose><c:when test="${user.useridentity==0}"><%@ include file="managerbar.jsp"%></c:when><c:otherwise><%@include file="cashierbar.jsp"%></c:otherwise></c:choose>
	<div class="w70pct display-inline-block">
		<form action="bill?option=billpayment" method="post">
		<label for="vendorname">Vendor Name:</label><select id="vendorname" name="vendorname"><c:forEach items="${listvendor}" var="vendor" varStatus="status">
		<option value="${vendor.vendorid}/${vendor.vendorname}">${vendor.vendorname}</option>
	</c:forEach> </select><br>
		<input type="submit" value="submit" name="">&nbsp;<input type="reset" value="Reset" name="">
		</form>
	</div>
</div>
<jsp:include page="foot.jsp"></jsp:include>
</body>
</html>