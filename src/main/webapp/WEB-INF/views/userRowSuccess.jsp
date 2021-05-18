<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
 <jsp:include page="/WEB-INF/viewsCommon/htmlHeader.jsp"/>
</head>

<body>
<div class="generic-container">
   <h4>${success}</h4>

   <span>
      <a href="<c:url value='/userListRefresh'/>">User List</a>
   </span>
</div>

</body>
</html>
