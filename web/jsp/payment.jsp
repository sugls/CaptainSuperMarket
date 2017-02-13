<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>billPaymentMain</title>
    <link rel="stylesheet" href="../css/reset.css">
    <link rel="stylesheet" href="../css/main.css">
    <script type="text/javascript" src="../js/index.js"></script>
    <script src="../js/jquery-3.1.1.min.js"></script>
    <script type="text/javascript">
        function checkform() {
            if ($("#checkno").val()==""){
                alert("check no. cannot be empty");
                return false;
            } else {
                var reg = /(^[a-zA-Z]{0,4}[0-9];$)+/;
                if((reg.test($("#checkno").val()))==false){
                    alert("please input valid check NO.");
                    return false;
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
        <form action="bill?option=dopay&payment=${payment}&id=${bill.billid}" onsubmit="return checkform()" method="post">
            <label for="paymode">Payment Mode: </label><label id="paymode">${payment eq "1"?"pay in cash":"pay by check"}</label><br>
            <table border="1">
            <thead><th>Bill Details:</th><th>Department Name</th><th>Amount</th>
            </thead>
            <c:forEach items="${listdetails}" var="details" varStatus="status">
                <tr><td>&nbsp;</td><td>${details.departname}</td><td>${details.amount}$ </td></tr>
            </c:forEach>
            </table>
            Vendor Name: </label>${bill.vendorname} <br>
            Bill Date: ${bill.billdate} <br>
            Bill Due Date: ${bill.billduedate} <br>
            Total Amount: ${bill.sum}$ <br>
            <c:choose>
                <c:when test="${payment eq '2'}">
                    <label for="checkno">Check No.: </label><input type="text" name="checkno" id="checkno"> <br>
                    <p>*input each check NO. end with a semicolon(such as K201893144; )/p>
                </c:when>
                <c:otherwise>
                    &nbsp;
                </c:otherwise>
            </c:choose> <br>
            <input type="submit" value="Confirm and Pay" name="">
        </form>
    </div>
</div>
<jsp:include page="foot.jsp"></jsp:include>
</body>
</html>