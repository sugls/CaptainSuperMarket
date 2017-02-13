<%@ page pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>departmentMain</title>
    <link rel="stylesheet" href="../css/reset.css">
    <link rel="stylesheet" href="../css/main.css">
    <script type="text/javascript" src="../js/index.js"></script>
</head>
<body>
<jsp:include page="head.jsp"></jsp:include>
<div class="body bg-yellow">
    <c:choose><c:when test="${user.useridentity==0}"><%@ include file="managerbar.jsp"%></c:when><c:otherwise><%@include file="cashierbar.jsp"%></c:otherwise></c:choose>
    <div class="w70pct display-inline-block">
		<c:choose><c:when test="${listvendor.size()>0}">
			<table border="1">
				<thead>
				<th>Vendor Name</th><th>Vendor Address</th><th>Vendor Phone</th><th>Vendor FAX</th>
				<th>Contact Person</th><th>Update</th>
				</thead>
				<c:forEach items="${listvendor}" var="vendor" varStatus="status">
					<tr><td>${vendor.vendorname}</td><td>${vendor.vendoraddress}</td>
					<td>${vendor.vendorphone}</td><td>${vendor.vendorfax}</td><td>${vendor.contact}</td>
					<td><input type="button" value="Go" onclick="location.href='vendor?option=update&id=${vendor.vendorid}'"></td></tr>
				</c:forEach>
			</table>
		</c:when><c:otherwise>
			<p>There is no vendor to show,please add some vendors;</p>
		</c:otherwise></c:choose>

		<input type="button" value="Add Vendor" onclick="location.href='vendor?option=add'">
	</div>
</div>
<jsp:include page="foot.jsp"></jsp:include>
<script type="text/javascript">
    if ("${msg}"!=""){
        alert("${msg}");
    }
</script>
</body>
</html>