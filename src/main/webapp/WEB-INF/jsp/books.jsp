<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page session="false" %>
<%@ taglib uri="/WEB-INF/customTag.tld" prefix="ct" %>

<html>
<head>
    <title>Book Page</title>
    <style type="text/css">
        .tg  {border-collapse:collapse;border-spacing:0;border-color:#ccc;}
        .tg td{font-family:Arial, sans-serif;font-size:14px;padding:10px 5px;border-style:solid;border-width:1px;overflow:hidden;word-break:normal;border-color:#ccc;color:#333;background-color:#fff;}
        .tg th{font-family:Arial, sans-serif;font-size:14px;font-weight:normal;padding:10px 5px;border-style:solid;border-width:1px;overflow:hidden;word-break:normal;border-color:#ccc;color:#333;background-color:#f0f0f0;}
        .tg .tg-4eph{background-color:#f9f9f9}
        .red {background-color: red}
        .error {color: red; font-weight: bold;}
</style>
    
</head>


<body> 
<div align="center">
<h1>
    Add a Book , <ct:today format="MMMM dd, yyyy" />
</h1>
  
<c:url var="addAction" value="/create-book" ></c:url>
 
 <form:errors />
 
<form:form modelAttribute="book" action="${addAction}" commandName="book" method="POST">
<table>
    <c:if test="${!empty book.title}">
    <tr>
        <td>
            <form:label path="id">
                <spring:message text="ID"/>
            </form:label>
        </td>
        <td>
            <form:input path="id" readonly="true" size="8"  disabled="true" />
            <form:hidden path="id" />
        </td> 
    </tr>
    </c:if>
    <tr>
        <td>
            <form:label path="title">
                <spring:message text="Title"/>
            </form:label>
        </td>
        <td>
            <form:input path="title" />
            <form:errors path="title" cssClass="error"/>
        </td> 
    </tr>
    <tr>
        <td>
            <form:label path="author">
                <spring:message text="Author"/>
            </form:label>
        </td>
        <td>
            <form:input path="author" />
            <form:errors path="author" cssClass="error"/>
        </td>
    </tr>
    <tr></tr>
    <tr align="right">
        <td colspan="2">
            <c:if test="${!empty book.title}">
                <input type="submit"
                    value="<spring:message text="Edit Book"/>" />
            </c:if>
            <c:if test="${empty book.title}">
                <input type="submit"
                    value="<spring:message text="Create Book"/>" />
            </c:if>
        </td>
    </tr>
</table>  
</form:form>

<c:if test="${empty books}">
	<a href="<c:url value='/books' />" >Back to book list</a>
</c:if>

<br>

<c:if test="${!empty books}">
<h3>Book List</h3>
    <table class="tg">
    <tr>
        <th width="80">Book ID</th>
        <th width="120">Book Title</th>
        <th width="120">Author</th>
        <th width="60">Edit</th>
        <th width="60">Delete</th>
    </tr>
    <c:forEach items="${books}" var="book">
        <tr>
            <td>${book.id}</td>
            <td>${book.title}</td>
            <td>${book.author}</td>
            <td><a href="<c:url value='/edit/${book.id}' />" >Edit</a></td>
            <td><a href="<c:url value='/remove/${book.id}' />" >Delete</a></td>
        </tr>
    </c:forEach>
    </table>
</c:if>
</div>
 </body>
</html>