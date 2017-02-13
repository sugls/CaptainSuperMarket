<%@ page pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>incomeMain</title>
    <link rel="stylesheet" href="../css/reset.css">
    <link rel="stylesheet" href="../css/main.css">
    <link rel="stylesheet" href="../css/bootstrap.css">
	<script src="../js/jquery-3.1.1.min.js"></script>
    <script type="text/javascript" src="../js/index.js"></script>
	<script src="../js/zebra_datepicker.js"></script>
</head>
<body>
<jsp:include page="head.jsp"></jsp:include>
<div class="body bg-yellow">
    <c:choose><c:when test="${user.useridentity==0}"><%@ include file="managerbar.jsp"%></c:when><c:otherwise><%@include file="cashierbar.jsp"%></c:otherwise></c:choose>
	<div class="w70pct display-inline-block">
		<label for="choose-date">choose a date:</label><input class="datepicker" type="text" id="choose-date" name="choose-date" >
        <input type="button" value="display" id="display">
        <script type="text/javascript">
            $(document).ready(function () {
                $('input.datepicker').Zebra_DatePicker();
            });
            document.getElementById("display").onclick=function () {
                if (document.getElementById("choose-date").value==""){
                    alert("please choose a date firstly");
                    $("#choose-date").click();
                } else {
                    var date = $("#choose-date").val();
                    location.href="income?option=show&date="+date;
                }
            };
            $(document).ready(function (){
            if ("${date}"!=""){
                document.getElementById("choose-date").value = '${date}';
            } else {
                document.getElementById("choose-date").value = getTodayDate(0);
            }
            });
        </script>
        <table border="1">
			<c:choose><c:when test="${listincome.size()>0}">
            <thead>
				<th>Department Name</th><th>Bussiness Date</th><th>Last Modify Date</th><th>Daily Income</th><th>Update</th>
			</thead>
            </c:when><c:otherwise><p>no data to show,please add a record or choose another date</p></c:otherwise>
            </c:choose>
			<c:forEach items="${listincome}" var="income" varStatus="status"><tr><td>${income.departname}</td><td>${income.businessdate}</td><td>${income.lastmoddate}</td><td>${income.dailyincome}$</td><td><button onclick="location.href='income?option=update&id=${income.incomeid}'">Go</button></td></tr></c:forEach>
		</table>
		<input type="button" value="Add Income" name="" onclick="location.href='income?option=add'">
	</div>
</div>
<jsp:include page="foot.jsp"></jsp:include>
</body>
</html>