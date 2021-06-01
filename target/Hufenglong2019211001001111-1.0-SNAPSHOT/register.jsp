
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="header.jsp"%>
<form method="post" action="register">
    id:<input type="text" name="id" placeholder="id"><br/>
    Name:<input type="text" name="username" placeholder="username"><br/>
    Password:<input type="text" name="password" placeholder="password"><br/>
    Email:<input type="text" name="email" placeholder="email"><br/>
    gender:<input type="text" name="gender" placeholder="gender"><br/>
    Birthdate:<input type="text" name="birthDate" placeholder="Date of Birth(yyyy-dd-mm format)"><br/>
    <input type="submit" class="button" value="register">
</form>
<%@include file="footer.jsp"%>

