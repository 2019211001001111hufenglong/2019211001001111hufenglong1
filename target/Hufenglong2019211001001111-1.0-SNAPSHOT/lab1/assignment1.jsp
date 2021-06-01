<%@ page import="com.lab1.Person" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%@ page import="com.lab1.Dog" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Assignemnt1</title>
</head>
<body>
<%@taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<h1> c:forEach loog to print 1 to 10</h1>

    <ul>
        <c:forEach var="c" begin="1" end="10">
        <li>${c}</li>
        </c:forEach>
    </ul>
    <%-- todo 1 : run assignement1.jsp  --%>
    <%-- todo 2 : use c:forEach to print list 1,3,5,7,9   --%>
    <ul>
    <c:forEach var="c" begin="1" step="2" end="10">
        <li>${c}</li>
    </c:forEach>
    </ul>
   <%-- todo 3 : use c:forEach to print 2,4,6,8,10   --%>
    <ul>
        <c:forEach var="c" begin="1" step="2" end="10">
            <li>${c+1}</li>
        </c:forEach>
    </ul>


<% String[] Words ={"one","two","three","four","five","six","seven","eight","nine","ten"};
   pageContext.setAttribute("Words", Words);
%>
    <%-- todo 4 : use c:forEach to print all elements of array --%>
    <ul>
        <c:forEach var="w" items="${pageScope.Words}">
            <li>${w}</li>
        </c:forEach>
    </ul>
    <%-- todo 5 : use c:forEach to print "one","three","five","seven","nine"  of array --%>
    <ul>
        <c:forEach var="w" items="${pageScope.Words}" varStatus="parm">
            <c:if test="${parm.count%2==1}">
            <li>${w}</li>
            </c:if>
        </c:forEach>
    </ul>
<%
List<Person> personArrayList=new ArrayList<Person>();
    personArrayList.add(new Person("hufenglong",new Dog("jerry")));
    personArrayList.add(new Person("Tombition",new Dog("Tom")));
// todo 6 : add a new person into PersonList - person name and dog name
request.setAttribute("personArrayList",personArrayList);
%>
<%--//todo 7: use c:forEach to print person name :   and persons' dog name : --%>
<c:forEach var="p" items="${requestScope.personArrayList}">
    <c:out value="${p.name}"/>&nbsp;
    <c:out value="${p.dog.name}"/><br>
</c:forEach>
<%
    String str="one:two:three-four-five";
%>
<%--todo 8 : use c:forToken to print all words --%>
<c:forTokens items="<%=str%>" delims=": -" var="num">
    <c:out value="${num}"/><br>
</c:forTokens>
</body>
</html>
