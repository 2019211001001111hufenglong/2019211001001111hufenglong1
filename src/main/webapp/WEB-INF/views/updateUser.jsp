
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="header.jsp"%>
<h1> User Update</h1>
<%
    User u = (User) session.getAttribute("user");
%>

<form method="post" action="updateUser">
   <input type="hidden" name="id" value="<%=u.getId()%>"><br/>
    Name:<input type="text" name="username" placeholder="username" value="<%=u.getUsername()%>"><br/>
    Password:<input type="text" name="password" placeholder="password" value="<%=u.getPassword()%>"><br/>
    Email:<input type="text" name="email" placeholder="email" value="<%=u.getEmail()%>"><br/>
    Gender:<input type="radio" name="gender" value="male" <%="male".equals(u.getGender())?"checked" :""%>>Male
    <input type="radio" name="gender" value="female" <%="female".equals(u.getGender())?"checked" :""%>>Female<br/>

    Birthdate:<input type="text" name="birthDate" placeholder="Date of Birth(yyyy-dd-mm format)" value="<%=u.getBirthdate()%>"><br/>
    <input type="submit" value="Save Changes">
</form>




<%@include file="footer.jsp"%>