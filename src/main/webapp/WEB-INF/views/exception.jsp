<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang='en'>
<head>
 <jsp:include page="/WEB-INF/viewsCommon/htmlHeader.jsp"/>
</head>

<body>
<div class="container">

	<c:if test="${not empty errorCode}">
		<h3>Error Code: ${errorCode}</h3>
	</c:if>
	
	<c:if test="${empty errorCode}">
		<h3>Error:</h3>
	</c:if>

	<c:if test="${not empty errorMessage}">
		<h4>${errorMessage}</h4>
	</c:if>

</div>

</body>
</html>
