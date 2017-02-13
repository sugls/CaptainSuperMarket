<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>Backup</title>
    <link rel="stylesheet" href="../css/reset.css">
    <link rel="stylesheet" href="../css/main.css">
	<link rel="stylesheet" href="../css/bootstrap.css">
	<script src="../js/jquery-3.1.1.min.js"></script>
    <script type="text/javascript" src="../js/index.js"></script>
	<script src="../js/zebra_datepicker.js"></script>
	<script type="text/javascript">
		$(document).ready(function () {
            $(document).ready(function () {
                $('input.datepicker').Zebra_DatePicker();
            });
        });
	</script>
</head>
<body>
<jsp:include page="head.jsp"></jsp:include>
<div class="body bg-yellow">
    <c:choose><c:when test="${user.useridentity==0}"><%@ include file="managerbar.jsp"%></c:when><c:otherwise><%@include file="cashierbar.jsp"%></c:otherwise></c:choose>
    <div class="w70pct display-inline-block">
		<label for="tablename">Table Name:</label><select id="tablename"><c:forEach items="${listtable}" var="table" varStatus="status">
		<option value="${table}">${table}</option>
	</c:forEach> </select><br>
		<label>Date:from</label><input class="datepicker w200" type="text" id="fromdate" name="">to <input class="datepicker w200" type="text" id="todate" name=""><br>
		<input type="submit" value="Submit" name="">&nbsp;<input type="reset" value="Reset" name="">
	</div>
</div>
<jsp:include page="foot.jsp"></jsp:include>
</body>
</html>