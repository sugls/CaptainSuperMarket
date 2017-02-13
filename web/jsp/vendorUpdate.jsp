<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>departmentUpdate</title>
	<link rel="stylesheet" href="../css/reset.css">
	<link rel="stylesheet" href="../css/main.css">
	<script src="../js/index.js"></script>
	<script src="../js/jquery-3.1.1.min.js"></script>
	<script src="../js/jquery.validate.js"></script>
    <script type="text/javascript">
        function equals(object) {
            if (object.val().trim()==object.attr('placeholder')){
                return true;
            } else {
                return false;
            }
        }
        $(document).ready(function () {
           $("#form").validate(function () {
               $("#form").submit();
           })
        });
        function check() {
            if (equals($("#name"))){
                $("#flag").val("false");
            } else {
                $("#flag").val("true");
            }
           if (equals($("#name"))&&equals($("#address"))&&equals($("#phone"))
           &&equals($("#fax"))&&equals($("#contact"))){
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
        <form  method="post" action="vendor?option=updatevendor&id=${vendor.vendorid}" id="form">
            <input type="hidden" id="flag" name="flag">
            <label for="name">Vendor Name:</label><input type="text" onfocus="this.value=this.placeholder" name="vendorname" id="name" required placeholder=${vendor.vendorname}><br>
		<label for="address">Vendor Address:</label><input type="text" id="address" onfocus="this.value=this.placeholder" name="vendoraddress" required placeholder="${vendor.vendoraddress}"><br>
		<label for="phone">Vendor Phone:</label><input type="text" id="phone" name="vendorphone" required onfocus="this.value=this.placeholder" placeholder="${vendor.vendorphone}"><br>
		<label for="fax">Vendor FAX:</label><input type="text" id="fax" name="vendorfax" placeholder="${vendor.vendorfax}" onfocus="this.value=this.placeholder"><br>
		<label for="contact">Contact Person:</label><input type="text" id="contact" name="contact" required placeholder="${vendor.contact}" onfocus="this.value=this.placeholder"><br>
		<input type="submit" value="Update" onclick="return check()" id="submit">&nbsp;
		<input type="reset" value="Reset" name="">
        </form>
	</div>
</div>
<jsp:include page="foot.jsp"></jsp:include>
</body>
<script type="text/javascript">
    if ("${msg}"!=""){
        alert("${msg}");
    }
</script>
</html>