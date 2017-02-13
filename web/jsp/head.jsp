<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: lsc
  Date: 17-2-1
  Time: 下午3:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>head</title>
</head>
<body>
<div class="head">
<img src="../pic/title.png" width="800" height="150" alt="head">
    <div class="label">
    <c:choose><c:when test="${user != null}"><span class="identity">welcome,<c:if test="${user.useridentity==0}">manager </c:if>
    <c:if test="${user.useridentity==1}">cashier </c:if>
    ${user.username}</span>
    </c:when></c:choose>
<span class="time" id="date"></span>
    </div>
<script type="text/javascript">
    getTime();
</script>
</div>
</body>
</html>
