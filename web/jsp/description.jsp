<%@ page pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>description</title>
    <link rel="stylesheet" href="../css/reset.css">
	<link rel="stylesheet" href="../css/main.css">
    <script type="text/javascript" src="../js/index.js"></script>
</head>
<body>
<jsp:include page="head.jsp"></jsp:include>
<div class="body bg-yellow">
    <c:choose><c:when test="${user.useridentity==0}"><%@ include file="managerbar.jsp"%></c:when><c:otherwise><%@include file="cashierbar.jsp"%></c:otherwise></c:choose>
	<div class="w70pct display-inline-block">
        <p>description <br>
        Store Manager manually enter the daily income into the application.
        </p>
    </div>
</div>
<jsp:include page="foot.jsp"></jsp:include>
</body>
</html>