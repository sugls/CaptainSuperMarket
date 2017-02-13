<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>departmentMain</title>
    <link rel="stylesheet" href="../css/reset.css">
    <link rel="stylesheet" href="../css/main.css">
	<link rel="stylesheet" href="../css/bootstrap.css">
	<script src="../js/jquery-3.1.1.min.js"></script>
	<script src="../js/zebra_datepicker.js"></script>
    <script type="text/javascript" src="../js/index.js"></script>
	<script type="text/javascript">
        $(document).ready(function () {
            $('input.datepicker').Zebra_DatePicker();
        });
        $(document).ready(function () {
			$("#billdate").val(getTodayDate(0));
			$("#duedate").val(getTodayDate(7));
        });
        function checkform() {
            if ($("#billdate").val()==""){
                alert("please choose a bill date");
                $("#billdate").click();
                return false;
            } else if ($("#duedate").val()==""){
                alert("please choose a bill due date");
                $("#duedate").click();
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
		<form action="bill?option=additem" method="post" onsubmit="return checkform()">
		<label for="billdate">Bill Date:</label><input type="text" class="datepicker" name="billdate" id="billdate"><br>
		<label for="duedate">Bill Due Date:</label><input type="text" id="duedate" class="datepicker" name="duedate"><br>
		<label for="vendor">Vendor:</label><select id="vendor" name="vendor"><c:forEach items="${listvendor}" var="vendor" varStatus="status">
		<option value="${vendor.vendorid}">${vendor.vendorname}</option>
	</c:forEach> </select>
		<input type="button" value="new Vendor" onclick="location.href='vendor?option=add'" name=""><br>
		<input type="submit" value="Add Department Item" name="">&nbsp;
		<input type="reset" value="Reset" name="">
		</form>
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