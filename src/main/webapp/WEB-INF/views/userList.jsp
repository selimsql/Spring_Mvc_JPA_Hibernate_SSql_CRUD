<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>

<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html lang='en'>
<head>
 <jsp:include page="/WEB-INF/viewsCommon/htmlHeader.jsp"/>
</head>

<body>

<div class="generic-container">

<!-- Default panel contents -->
<h2>
 <span>User List</span>
</h2>

 <c:url value="/userQuery" var="actionUrl"/>
 <f:form method="POST" action="${actionUrl}" modelAttribute="userDTO">
 <table>
   <tr>
      <td align="right">Code</td>
      <td>
         <f:input type="text" path="code" id="code"/>
      </td>

      <td align="right">Name</td>
      <td>
         <f:input type="text" path="name" id="name"/>
      </td>

      <td align="right">Surname</td>
      <td>
         <f:input type="text" path="surname" id="surname"/>
      </td>
   </tr>

   <tr>
      <td>&nbsp;</td>
      <td colspan="3">
         <input type="submit" value="Query"/>

         &nbsp;&nbsp;
         <a href="<c:url value='/newUser'/>">New User</a>
      </td>
   </tr>
 </table>
 </f:form>

<br/>
<table>
  <thead>
   <tr>
     <th align="right">Id</th>
     <th align="left">Code</th>
     <th align="left">Name</th>
     <th align="left">Surname</th>
     <th align="left">Email</th>
     <th align="center">Status</th>
     <th align="left">Operation</th>
   </tr>
  </thead>

  <tbody>
   <c:forEach items="${listUser}" var="user">
     <tr>
       <td align="right">${user.id}</td>
       <td align="left">${user.code}</td>
       <td align="left">${user.name}</td>
       <td align="left">${user.surname}</td>
       <td align="left">${user.email}</td>
       <td align="center"><fmt:message key="Status${user.status}"/></td>

       <td>
         <a href="<c:url value='/showUser-${user.id}'/>">Show</a>
         <a href="<c:url value='/updateUser-${user.id}'/>">Update</a>
         <a href="<c:url value='/deleteUser-${user.id}'/>">Delete</a>
       </td>
     </tr>
    </c:forEach>
  </tbody>
</table>
</div>

</body>
</html>
