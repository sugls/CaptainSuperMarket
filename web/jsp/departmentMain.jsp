<%@ page pageEncoding="UTF-8" %>
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
    <c:choose><c:when test="${user.useridentity==0}"><%@ include file="managerbar.jsp"%></c:when><c:otherwise><%@include file="cashierbar.jsp"%></c:otherwise></c:choose><div class="w70pct display-inline-block">
	<table border="1">
			<thead>
				<th>Department Name</th><th>Description</th><th>Update</th>
			</thead>
			<c:forEach items="${listdepart}" var="depart" varStatus="status"><tr><td>${depart.departname}</td><td>${depart.description}</td><td>
				<input type="button" onclick="location.href='depart?option=update&id=${depart.departid}'" value="Go"></td></tr></c:forEach>
		</table>
		<input type="button" value="Add Department" name="" onclick="location.href='depart?option=add'">
	</div>
</div>
<jsp:include page="foot.jsp"></jsp:include>
<script type="text/javascript">
    if ('${msg}'!=""){
        alert('${msg}');
    }
</script>
</body>
</html>