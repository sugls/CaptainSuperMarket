<%@ page pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>index</title>
    <link rel="stylesheet" href="css/reset.css">
	<link rel="stylesheet" href="css/main.css">
    <script type="text/javascript" src="js/index.js"></script>
    <script type="text/javascript">
        function checkform() {
            if (document.getElementById("username").value==""){
                alert("please input username");
                document.getElementById("username").focus();
                return false;
            }
            else if (document.getElementById("passwd").value==""){
				alert("please input password");
				document.getElementById("passwd").focus();
				return false;
            }
            else
				return true;
        }

    </script>

</head>
<body>
<jsp:include page="jsp/head.jsp"></jsp:include>
<div class="body bg-img">
	<div class="floatR w270">
        <form action="login" method="post" onsubmit="return checkform()">
            <p>Input your username and password</p><br>
            <input id="error" type="hidden" value="${error}">
            <script type="text/javascript">
                if (document.getElementById("error").value!=""){
                    alert(document.getElementById("error").value);
                }
            </script>
	    <input style="width:245px" type="text" name="username" id="username" autofocus placeholder="your username"><br><br>
	    <input style="width:245px" type="password" name="passwd" id="passwd" placeholder="your password"><br><br>
	    <div id="btn"><input class="btn" type="submit" value="go"></div>
        </form>
	</div>
</div>
<jsp:include page="jsp/foot.jsp"></jsp:include>
</body>
</html>