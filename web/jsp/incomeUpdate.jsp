<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>incomeUpdate</title>
	<link rel="stylesheet" href="../css/reset.css">
	<link rel="stylesheet" href="../css/main.css">
	<script type="text/javascript" src="../js/index.js"></script>
	<script src="../js/jquery-3.1.1.min.js"></script>
	<script type="text/javascript">
		function checkform() {
		    if (document.getElementById("income").value==""){
			    alert("please input changes");
			    return false;
            }
        }
        window.onload=function () {
            var sel = document.getElementById("departname");
            sel.onchange=function () {
               // alert(document.getElementById(sel.options[sel.selectedIndex].value)==null);
                if (document.getElementById(sel.options[sel.selectedIndex].value)==null){
                    document.getElementById("income").placeholder = 0;
                } else {
                    document.getElementById("income").placeholder = document.getElementById(sel.options[sel.selectedIndex].value).value;
                }
            }
        }
	</script>
</head>
<body>
<jsp:include page="head.jsp"></jsp:include>
<div class="body bg-yellow">
	<c:choose><c:when test="${user.useridentity==0}"><%@ include file="managerbar.jsp"%></c:when><c:otherwise><%@include file="cashierbar.jsp"%></c:otherwise></c:choose>
	<div class="w70pct display-inline-block">
		<form action="income?option=updateincome" method="post" onsubmit="return checkform()">
		<label for="departname">Department Name</label><select name="departname" id="departname"><c:forEach items="${listdepart}" var="depart" varStatus="status"><option value="${depart.departid}"  <c:if test="${depart.departname eq income.departname}">selected="selected"</c:if> >${depart.departname}</option></c:forEach> </select><br>
		<label for="income">Daily Income Sum:</label><input type="number" name="income" id="income" placeholder="${income.dailyincome}">$ * input without unit<br>
		<input type="submit" value="Update" name="">&nbsp;
		<input type="reset" value="Reset" name="">&nbsp;
		<input type="button" value="Add Department" onclick="location.href='depart?option=add'">
		</form>
        <c:forEach items="${listincome}" var="income"><input type="hidden" id="${income.departid}" value="${income.dailyincome}"></c:forEach>
	</div>
</div>
<div></div>
</body>
</html>