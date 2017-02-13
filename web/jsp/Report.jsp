<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>Report</title>
    <link rel="stylesheet" href="../css/reset.css">
	<link rel="stylesheet" href="../css/main.css">
	<link rel="stylesheet" href="../css/bootstrap.css">
    <script type="text/javascript" src="../js/index.js"></script>
	<script src="../js/jquery-3.1.1.min.js"></script>
	<script src="../js/zebra_datepicker.js"></script>
	<script type="text/javascript">
        $(document).ready(function () {
            $('input.datepicker').Zebra_DatePicker();
        });
        function checkform() {
            if ($("#fromdate").val()==""){
                alert("please choose a start date");
                $("#fromdate").click();
                return false;
            } else if ($("#todate").val()==""){
                alert("please choose a end date");
                $("#todate").click();
                return false;
            }
        }
        function myPrint(obj){
            var newWindow=window.open("打印窗口","_blank");
            var docStr = obj[0].innerHTML;
            newWindow.document.write(docStr);
            newWindow.document.close();
            newWindow.print();
            newWindow.close();
        }
	</script>
</head>
<body>
<jsp:include page="head.jsp"></jsp:include>
<div class="body bg-yellow">
    <c:choose><c:when test="${user.useridentity==0}"><%@ include file="managerbar.jsp"%></c:when><c:otherwise><%@include file="cashierbar.jsp"%></c:otherwise></c:choose>
	<div class="w70pct display-inline-block">
		<form action="manage?option=report" method="post" onsubmit="return checkform()">
		<label for="departname">Department Name:</label><select name="departname" id="departname"><c:forEach items="${listdepart}" var="depart" varStatus="status"><option value="${depart.departname}"  >${depart.departname}</option></c:forEach> </select><br>
		<label>Date:from</label><input class="w200 datepicker" type="text" id="fromdate" name="fromdate" >to <input type="text" class="datepicker w200" id="todate" name="todate"><br>
		<input type="submit" value="Submit">&nbsp;<input type="reset" value="Reset" name="">
		</form>
		<c:choose><c:when test="${listreport.size()>0}">
			<div id="report">
                Income and Expense Report <br>
                <table border="1">
                <caption align="top">Date Range:${fromdate} to ${todate}</caption>
			<thead>
			<th>Department Name</th><th>Date</th><th>Income Amount</th><th>Expense Amount</th>
			</thead>
				<c:forEach items="${listreport}" var="report" varStatus="status">
					<tr><td>${report.departname}</td><td>${report.date}</td><td>${report.income}$</td><td>${report.expense}$</td></tr>
				</c:forEach>
			</table>
            </div>
			<input type="button" value="Print" onclick="myPrint($('#report'))">
		</c:when><c:otherwise><p>please choose a date range to show income and expenses</p></c:otherwise></c:choose>
	</div>
</div>
<jsp:include page="foot.jsp"></jsp:include>
</body>
</html>