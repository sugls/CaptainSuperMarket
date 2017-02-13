<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>departmentMain</title>
	<link rel="stylesheet" href="../css/reset.css">
	<link rel="stylesheet" href="../css/main.css">
	<script src="../js/index.js"></script>
	<script src="../js/jquery-3.1.1.min.js"></script>
    <script src="../js/jquery.validate.js"></script>
</head>
<body>
<jsp:include page="head.jsp"></jsp:include>
<div class="body bg-yellow">
	<c:choose><c:when test="${user.useridentity==0}"><%@ include file="managerbar.jsp"%></c:when><c:otherwise><%@include file="cashierbar.jsp"%></c:otherwise></c:choose>
	<div class="w70pct display-inline-block">
		<form class="form" action="vendor?option=addvendor" method="post">
		<label for="name">Vendor Name:</label><input type="text" name="vendorname" required id="name"><br>
		<label for="address">Vendor Address:</label><input type="text" id="address" required name="vendoraddress"><br>
		<label for="phone">Vendor Phone:</label><input type="tel" id="phone" required name="vendorphone"><br>
		<label for="fax">Vendor FAX:</label><input type="text" id="fax" name="vendorfax"><br>
		<label for="contact">Contact Person:</label><input type="text" id="contact" required name="contact"><br>
		<input type="submit" value="Add" name="" id="submit">&nbsp;
		<input type="reset" value="Reset" name="">
		</form>
	</div>
    <script type="text/javascript">
        $(function () {
            $(".form").validate(function(){
                $("#submit").click();
                }
			)
        });
        if ("${msg}"!=""){
            alert("${msg}");
        }
    </script>
</div>
<jsp:include page="foot.jsp"></jsp:include>
</body>
</html>