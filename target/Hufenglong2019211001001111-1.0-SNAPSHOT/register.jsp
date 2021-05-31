<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>register</title>

</head>
<body>
<form method="post" action="register">
    Username:<input type="text" name="username" placeholder="username"><br/>
    Password:<input type="text" name="password" placeholder="password"><br/>
    Email:<input type="text" name="email" placeholder="email"><br/>
    Gender<input type="radio" name="gender" value="male">Male <input type="radio" name="gender" value="female">Female<br/>
    Birthdate:<input type="text" name="birthDate" placeholder="Date of Birth(yyyy-dd-mm format)"><br/>
    <input type="submit" class="button" value="register">
</form>
</body>
</html>

