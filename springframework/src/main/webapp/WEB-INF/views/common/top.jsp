<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
	<link
		href="${pageContext.request.contextPath}/resources/bootstrap/bootstrap.min.css"
		rel="stylesheet">
	<script
		src="${pageContext.request.contextPath}/resources/bootstrap/bootstrap.bundle.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/resources/jquery/jquery.min.js"></script>
	</head>

<body>
	<div class="d-flex flex-column vh-100">

		<%@ include file="/WEB-INF/views/common/nav.jsp" %>

		<div class="flex-grow-1 m-2">
			<div class="row">
				<div class="col-md-4">
					
					<%@ include file="/WEB-INF/views/common/menu.jsp" %>
					
				</div>

				<div class="col-md-8">
				