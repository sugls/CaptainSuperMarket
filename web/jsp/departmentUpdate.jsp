<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>departmentUpdate</title>
	<link rel="stylesheet" href="../css/reset.css">
	<link rel="stylesheet" href="../css/main.css">
	<script type="text/javascript" src="../js/index.js"></script>
    <script type="text/javascript">
        function checkform() {
            var name = document.getElementById("departname").placeholder;
            var description = document.getElementById("description").placeholder;
            if (document.getElementById("departname").value==""){
                document.getElementById("departname").value = name;
            }
            if (document.getElementById("description").value==""){
                document.getElementById("description").value = description;
            }
            if (document.getElementById("departname").value.trim()==""){
                alert("department name cannot be empty");
                document.getElementById("departname").focus();
                return false;
            }
            if (document.getElementById("departname").value.trim()==name && document.getElementById("description").value.trim()==description){
                alert("please make some changes");
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
        <form action="depart?option=updatedepart&id=${depart.departid}" method="post" onsubmit="return checkform()">
        <label for="departname">Department Name:</label><input onfocus="this.value=this.placeholder" type="text" name="departname" id="departname" placeholder="${depart.departname}"><br>
		<label for="description">Description:</label><input onfocus="this.value=this.placeholder" type="text" name="description" id="description" placeholder="${depart.description}"><br>
		<input type="submit" value="Update" name="">&nbsp;
		<input type="reset" value="Reset" name="">
        </form>
    </div>
</div>
<jsp:include page="foot.jsp"></jsp:include>
<script type="text/javascript">
    if ('${msg}'!=""){
        alert('${msg}')
    }
</script>
</body>
</html>