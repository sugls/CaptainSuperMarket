<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>billAddDepItem</title>
	<link rel="stylesheet" href="../css/reset.css">
	<link rel="stylesheet" href="../css/main.css">
	<script src="../js/index.js"></script>
    <script src="../js/jquery-3.1.1.min.js"></script>
    <script type="text/javascript">
        function checkform() {
            if ($("#sum").val()==""){
                alert("please select some items");
                return false;
            }
        }
        function inputsum() {
            var sum = 0;
            $("input[name='confirm']").each(function () {
                $(this).bind("change",function () {
                    var id = "#"+$(this).attr("id")+"a";
                    var val = $(id).val();
                    if (val==""){
                        alert("please input amount of this item firstly");
                        $(this).prop("checked",false);
                    } else {
                        if ($(this).is(':checked')==true){
                            sum += parseFloat(val);
                            if ($(this).val().indexOf(",")>0){
                                $(this).attr("value",$(this).val());
                            } else {
                                $(this).attr("value",$(this).val()+","+val);
                            }
                        } else {
                            sum -= parseFloat(val);
                        }
                        if (sum==0){
                            $("#sum").attr("value","");
                        } else {
                            $("#sum").attr("value",sum+" $");
                        }
                    }
                });
            });
        }
        $(document).ready(function(){
            $("#s-all").bind("click",function () {
                if ($("#s-all").val()=="select all"){
                    var str = "";
                    $("input[name='amount']").each(function () {
                        str += $(this).val();
                    });
                    if (str==""){
                        alert("please add some items firstly");
                    } else {
                        $("input[name='confirm']").each(function () {
                            var id = "#"+$(this).attr("value")+"a";
                            var val = $(id).val();
                            if (val!=""){
                               // $(this).prop("checked",true);
                                if ($(this).is(':checked')==false){
                                    $(this).click();
                                }
                                $("#s-all").attr("value","select none");
                            }
                        });
                    }
                } else {
                    //$("[name = confirm]:checkbox").prop("checked",false);
                    $("[name = confirm]:checkbox").each(function () {
                       if ($(this).is(':checked')==true){
                           $(this).click();
                       }
                    });
                    $("#s-all").attr("value","select all");
                }
            });
            inputsum();
        });
    </script>
</head>
<body>
<jsp:include page="head.jsp"></jsp:include>
<div class="body bg-yellow">
	<c:choose><c:when test="${user.useridentity==0}"><%@ include file="managerbar.jsp"%></c:when><c:otherwise><%@include file="cashierbar.jsp"%></c:otherwise></c:choose>
	<div class="w70pct display-inline-block">
		<form action="bill?option=adddepitem" method="post" onsubmit="return checkform()">
		<p>Bill date:<span>${bill.billdate}</span>&nbsp;&nbsp;Vendor:<span>${bill.vendorname}</span></p>
		<table border="1">
			<thead>
				<th>Department Name</th><th>Amount</th><th>Confirm</th>
			</thead>
			<c:forEach items="${listdepart}" var="depart" varStatus="status">
				<tr>
					<td>${depart.departname}</td><td><input type="number" step="0.01" id="${depart.departid}a" name="amount">$</td><td><input id="${depart.departid}" value="${depart.departid}" type="checkbox"  name="confirm"></td>
				</tr>
			</c:forEach>
			<tr>
				<td>sum</td><td><input type="text" id="sum" name="sum" readonly></td><td><input type="button" id="s-all" value="select all"></td>
			</tr>
		</table>
		<input type="submit" value="submit" name="">&nbsp;<input type="reset" value="Reset" name="">
        </form>
    </div>
</div>
<jsp:include page="foot.jsp"></jsp:include>
</body>
</html>