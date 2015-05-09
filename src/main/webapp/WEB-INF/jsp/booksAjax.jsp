<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page session="false" %>
<%@ taglib uri="/WEB-INF/customTag.tld" prefix="ct" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<%@ include file="/WEB-INF/config/bootstrapInclude.jsp" %>
<title>Book Store - AJAX</title>
</head>

<body>

<div class="container" align="center">
 <h1>Book Store</h1>
 
  <!-- Trigger the modal with a button -->
  <div align="right">
  	<button type="button" class="btn btn-info btn-sm" data-toggle="modal" data-target="#myModal">Add a Book</button>
  </div>
	</br>
	
	<c:if test="${!empty books}" >
		<table class="table">
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
		            <td><a href="<c:url value='/editAjax/${book.id}' />" >Edit</a></td>
		            <td><a href="<c:url value='/removeAjax/${book.id}' />" >Delete</a></td>
		        </tr>
		    </c:forEach>
		    </table>
		</c:if> 
</div>

<!-- Modal -->
  <div class="modal fade bs-example-modal-lg" id="myModal" role="dialog" >
    <div class="modal-dialog">
    
      <!-- Modal content-->
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal">&times;</button>
          <h4 class="modal-title">Add a Book</h4>
        </div>
        <div class="result"></div>
        <div class="modal-body">
              <form:form modelAttribute="book" action="" commandName="book" method="POST" id="bookForm">
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
				            <form:input path="title"  class="form-control" id="title"/>
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
				            <form:input path="author"  class="form-control" />
				            <form:errors path="author" cssClass="error"/>
				        </td>
				    </tr>
				    <tr></tr>
		   </table>
		   </form:form>
				           
        </div>
        <div class="modal-footer">
           <button type="button" class="btn btn-default" id="submit">Submit</button>
          <button type="button" class="btn btn-default" id="close" data-dismiss="modal">Close</button>
        </div>
      </div>
      
    </div>
  </div>

<script type="text/javascript">
	$(document).ready(function(){
		$("#submit").click(function(){

			$.ajax({  
			    type : 'POST',
			url : '/create-bookAjax',
			data: {
	            title : $( "#title" ).val(),
	            author : $( "#author" ).val()
	        }, 
			success : function(response) {
			     $('.result').html(response);}
			});
		});
		
		$("#close").click(function(){
			
			
		});
		
	});	

</script>

<script type="text/javascript">
if (("${book.title}".length > 0) || (error == "True")) {
    $(window).load(function(){
        $('#myModal').modal('show');
    });
} 
</script>



</body>
</html>
