<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>billPayment</title>
	<link rel="stylesheet" href="../css/reset.css">
	<link rel="stylesheet" href="../css/main.css">
	<script src="../js/index.js"></script>
    <script src="../js/jquery-1.4.4.min.js"></script>
    <link rel="stylesheet" href="../css/reveal.css">
    <script src="../js/jquery.reveal.js"></script>
    <link rel="stylesheet" href="../css/fancySelect.css">
    <script src="../js/fancySelect.js"></script>
    <script src="../js/jquery-3.1.1.min.js"></script>
    <script type="text/javascript">
        function goPay(id) {
            var sid = "#"+id;
            if ($(sid).val()==''){
                alert('please select a payment pattern');
            } else {
                location.href = 'bill?option=pay&id='+id+'&payment='+$(sid).val();
            }
        }
            function showPaymentSelect(id) {
                var payment = "#"+id;
                $(payment).fancySelect();
            }
    </script>
</head>
<body>
<jsp:include page="head.jsp"></jsp:include>
<div class="body bg-yellow">
    <c:choose><c:when test="${user.useridentity==0}"><%@ include file="managerbar.jsp"%></c:when><c:otherwise><%@include file="cashierbar.jsp"%></c:otherwise></c:choose>
    <div class="w70pct display-inline-block">
		<c:choose><c:when test="${billlist.size()>0}">
            <table border="1">
                <thead>
                <th>Vendor Name</th><th>Bill Date</th><th>Due Date</th><th>paidFlag</th><th>sum</th><th>Pay</th>
                </thead>
                <c:forEach items="${billlist}" var="bill" varStatus="status">
                    <tr><td>${vendorname}</td><td>${bill.billdate}</td><td>${bill.billduedate}</td>
                        <td><input type="checkbox" readonly="readonly"></td><td>${bill.sum}$</td><td><a href="#" onclick="showPaymentSelect(${bill.billid})" data-reveal-id="myModal"><input type="button" name="${bill.billid}" value="Go"></a></td></tr>
                    <div id="myModal" class="reveal-modal">

                        <p style="text-align: center; font-size: 16px; font-weight: bold;">choose payment pattern</p>
                        <br>
                        <select id="${bill.billid}" style="margin: auto;">
                            <option value="">select payment pattern</option>
                            <option value="1">pay in cash</option>
                            <option value="2">pay by check</option>
                        </select>

                        <a class="close-reveal-modal">&#215;</a>

                        <br>
                        <input type="button" value="Go to Pay" onclick="goPay('${bill.billid}')">
                    </div>
                </c:forEach>
            </table>
        </c:when><c:otherwise>
			<p>There is no bills of ${vendorname} ,or all bills of ${vendorname} have paid off.</p>
		</c:otherwise></c:choose>
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