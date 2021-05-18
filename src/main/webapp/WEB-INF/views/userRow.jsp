<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>

<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html>
<head>
 <jsp:include page="/WEB-INF/viewsCommon/htmlHeader.jsp"/>
</head>

<body>
<div class="container">

<h3>User Row(${operation})</h3>

<br/>
<f:form method="POST" modelAttribute="user">
   <table>
    <tr>
      <td align="right">Id (*)</td>
      <td>
         <c:choose>
            <c:when test="${operation eq 'Add'}">
               [AUTO]
            </c:when>
            <c:otherwise>
               ${user.id}
            </c:otherwise>
         </c:choose>
      </td>
    </tr>


    <tr>
      <td align="right">Name (*)</td>
      <td>
         <f:input type="text" path="name" id="name"/>
         <f:errors path="name" class="has-error"/>
      </td>
    </tr>

    <tr>
      <td align="right">Surname (*)</td>
      <td>
         <f:input type="text" path="surname" id="surname"/>
         <f:errors path="surname" class="has-error"/>
      </td>
    </tr>

    <tr>
      <td align="right">Code (*)</td>
      <td>
         <f:input type="text" path="code" id="code"/>
         <f:errors path="code" class="has-error"/>
      </td>
    </tr>

    <tr>
      <td align="right">Password (*)</td>
      <td>
         <f:input type="password" path="password" id="password"/>
         <f:errors path="password" class="has-error"/>
      </td>
    </tr>

    <tr>
      <td align="right">Email (*)</td>
      <td>
         <f:input type="text" path="email" id="email"/>
         <f:errors path="email" class="has-error"/>
      </td>
    </tr>

    <tr>
      <td align="right">Telephone</td>
      <td>
         <f:input type="text" path="phone" id="phone"/>
      </td>
    </tr>


    <tr>
      <td align="right">Status (*)</td>
      <td>
         <f:select path="status" id="status">
            <f:option value="1"><fmt:message key="Status1"/></f:option>
            <f:option value="0"><fmt:message key="Status0"/></f:option>
            <f:option value="9"><fmt:message key="Status9"/></f:option>
         </f:select>
         <f:errors path="status" class="has-error"/>
      </td>
    </tr>

    <tr>
      <td>&nbsp;</td>
      <td>
       <c:choose>
         <c:when test="${operation eq 'Show'}">
            <a href="<c:url value='/userListShow'/>">Okay</a>
         </c:when>
         <c:otherwise>
            <input type="submit" value="${operation}"/>
            &nbsp; or &nbsp;
            <a href="<c:url value='/userListShow'/>">Cancel</a>
         </c:otherwise>
       </c:choose>
      </td>
    </tr>
   </table>
</f:form>

</div>

</body>
</html>
