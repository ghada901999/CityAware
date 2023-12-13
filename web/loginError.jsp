<%-- 
    Document   : loginError
    Created on : Jun 2, 2023, 7:02:24 PM
    Author     : MOHAMMAD
--%>


<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>login</title>
    </head>
    <body>
        <div style="color: red; font-size: 1.2em; margin-top: 20px; text-align: center;">
    <%= request.getAttribute("errorMessage") %>
</div>


<%@ include file="admin_login.jsp" %>

    </body>
</html>
