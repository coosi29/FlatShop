<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body id="home">
	<div class="toolbar">
		<div class="sorter">
			<div class="view-mode">
				<a href="productlitst.html" class="list"> List </a> <a href="#"
					class="grid active"> Grid </a>
			</div>
			<form action="search" method="get">
				<div class="sort-by">
					Sort By Price: <select name="sort" onchange="this.form.submit()">
						<option
							<c:if test="${sort eq 'default'}">
						selected="selected"
						</c:if>
							value="default">Default</option>
						<option
							<c:if test="${sort eq 'ASC'}">
						selected="selected"
						</c:if>
							value="ASC">ASC</option>
						<option
							<c:if test="${sort eq 'DESC'}">
						selected="selected"
						</c:if>
							value="DESC">DESC</option>
					</select> <input type="hidden" value="${categoryId}" name="categoryId" /> <input
						type="hidden" value="${pricing}" name="pricing" />
						<input
						type="hidden" value="${text}" name="text" />
				</div>
			</form>
			<div class="limiter">
				Show : <select name="">
					<option value="3" selected>3</option>
					<option value="6">6</option>
					<option value="9">9</option>
				</select>
			</div>
		</div>
		<div class="pager">
			<a href="#" class="prev-page"> <i class="fa fa-angle-left"> </i>
			</a> <a href="#" class="active"> 1 </a> <a href="#"> 2 </a> <a href="#">
				3 </a> <a href="#" class="next-page"> <i class="fa fa-angle-right">
			</i>
			</a>
		</div>
	</div>
</body>
</html>