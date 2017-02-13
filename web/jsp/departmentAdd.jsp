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
	<script type="text/javascript">
		function checkform() {
			if (document.getElementById("departname").value==""){
			    alert("please input department name");
			    document.getElementById("departname").focus();
				return false;
			}
        }
	</script>
</head>
<body>
<jsp:include page="head.jsp"></jsp:include>
<div class="body bg-yellow">
	<c:choose><c:when test="${user.useridentity==0}"><%@ include file="managerbar.jsp"%></c:when><c:otherwise><%@include file="cashierbar.jsp"%></c:otherwise></c:choose>
	<div class="w70pct display-inline-block">
		<form action="depart?option=adddepart" method="post" onsubmit="return checkform()">
		<label for="departname">Department Name:</label><input type="text" name="departname" id="departname"><br>
		<label for="description">Description:</label><input type="text" name="description" id="description"><br>
		<input type="submit" value="Add" name="">&nbsp;
		<input type="reset" value="Reset" name="">
		</form>
        <script type="text/javascript">
            if ('${msg}'!=""){
                alert('${msg}');
            }
        </script>
	</div>
</div>
<jsp:include page="foot.jsp"></jsp:include>
</body>
</html>