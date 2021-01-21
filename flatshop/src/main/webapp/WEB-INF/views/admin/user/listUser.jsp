<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title>Dashboard | Klorofil - Free Bootstrap Dashboard Template</title>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0">
<!-- VENDOR CSS -->
<link rel="stylesheet"
	href="<c:url value='../resource/admin/assets/vendor/bootstrap/css/bootstrap.min.css'/>">
<link rel="stylesheet"
	href="<c:url value='../resource/admin/assets/vendor/font-awesome/css/font-awesome.min.css'/>">
<link rel="stylesheet"
	href="<c:url value='../resource/admin/assets/vendor/linearicons/style.css'/>">
<link rel="stylesheet"
	href="<c:url value='../resource/admin/assets/vendor/chartist/css/chartist-custom.css'/>">
<!-- MAIN CSS -->
<link rel="stylesheet"
	href="<c:url value='../resource/admin/assets/css/main.css'/>">
<link rel="stylesheet"
	href="<c:url value='../resource/admin/assets/css/demo.css'/>">
<!-- GOOGLE FONTS -->
<link
	href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,600,700"
	rel="stylesheet">

<!-- ICONS -->
<link rel="apple-touch-icon" sizes="76x76"
	href="<c:url value='../resource/admin/assets/img/apple-icon.png'/>">
<link rel="icon" type="image/png" sizes="96x96"
	href="<c:url value='../resource/admin/assets/img/favicon.png'/>">
<script src='https://kit.fontawesome.com/a076d05399.js'></script>
<link href="https://fonts.googleapis.com/icon?family=Material+Icons"
	rel="stylesheet">
</head>
<body>
	<!-- WRAPPER -->
	<div id="wrapper">
		<div th:replace="admin/common/header :: header"></div>
		<div th:replace="admin/common/category :: category"></div>
		<!-- MAIN -->
		<!-- MAIN CONTENT -->
		<div class="main">
			<div class="main-content">
				<div class="container-fluid">
					<!-- OVERVIEW -->
					<div class="panel panel-headline">
						<div class="panel-body">
							<form th:action="@{/admin/user-delete}" method="get">
								<div class="row">
									<div class="col-md-12">
										<div class="panel">
											<div class="panel-heading">
												<h3 class="panel-title">USER LIST</h3>
												<div class="right">
													<button type="submit">
														<span class="label label-danger"
															style="font-size: 15px; margin-right: 15px;">Delete</span>
													</button>
													<a th:href="@{../admin/user-create}"><span
														class="label label-success" style="font-size: 15px;">Create
															new account</span></a>
												</div>
											</div>
											<div class="panel-body no-padding">
												<table class="table table-striped" style="margin: auto;">
													<thead>
														<tr>
															<th><input type="checkbox" name="all" id="checkAll" /></th>
															<th>ID</th>
															<th style="width: 5%;">Avatar</th>
															<th style="padding-left: 20px;">Username</th>
															<th>Full Name</th>
															<th>Phone</th>
															<th style="width: 25%;">Address</th>
															<th>Gender</th>
															<th style="width: 5%;">Role</th>
															<th>Edit</th>
														</tr>
													</thead>
													<tbody>
														<tr th:each="user, iter : ${users}">
															<td style="vertical-align: middle;"><input
																class="checkbox" type="checkbox" name="userId" th:value="${user.userId}"
																th:id="${iter.index + 1}" /></td>
															<td style="vertical-align: middle;"
																th:text="${user.userId}"></td>
															<td style="vertical-align: middle;"><img
																th:src="@{'/download?image=' + ${user.avatar}}"
																alt="Avatar" class="avatar img-circle"
																style="width: 100%;"></td>
															<td style="vertical-align: middle; padding-left: 25px;"
																th:text="${user.username}">username</td>
															<td style="vertical-align: middle;"
																th:text="${user.fullName}">fullName</td>
															<td style="vertical-align: middle;"
																th:text="${user.phone}">phone</td>
															<td style="vertical-align: middle;"
																th:text="${user.address}">address</td>
															<td style="vertical-align: middle;"
																th:if="${user.gender == true}">Male</td>
															<td style="vertical-align: middle;"
																th:if="${user.gender == false}">Female</td>
															<td style="vertical-align: middle;"
																th:text="${user.role.role}"></td>
															<td style="vertical-align: middle;"><a
																th:href="@{'/admin/user-update?userId=' + ${user.userId}}"> <span
																	class="label label-warning" style="font-size: 15px;">Update</span>
															</a></td>
														</tr>
													</tbody>
												</table>
											</div>
										</div>
										<nav aria-label="Page navigation example"
											style="margin-top: -30px;">
											<ul class="pagination">
												<li class="page-item"
													th:each="page : ${#numbers.sequence(0, totalPage - 1)}">
													<a class="page-link"
													th:style="${page == pageIndex} ? 'background-color: #F0AD4E; color: white;'"
													th:text="${page + 1}"
													th:href="@{'/admin/user-list?pageIndex=' + ${page}}"></a>
												</li>
											</ul>
										</nav>
									</div>
									<div id="headline-chart" class="ct-chart"></div>
								</div>
							</form>
						</div>
					</div>
				</div>
				<!-- END OVERVIEW -->
			</div>
			<!-- END MAIN CONTENT -->
		</div>
		<!-- END MAIN -->
		<div class="clearfix"></div>
		<footer>
			<div class="container-fluid">
				<p class="copyright">
					&copy; 2017 <a href="https://www.themeineed.com" target="_blank">Theme
						I Need</a>. All Rights Reserved.
				</p>
			</div>
		</footer>
	</div>
	<!-- END WRAPPER -->
	<!-- Javascript -->
	<script>
            let userSelection = document.getElementsByClassName('checkbox');
            let checkAll = document.getElementById('checkAll')


            checkAll.addEventListener('click', () => {
                if (checkAll.checked == true) {
                    for (let i = 1; i <= userSelection.length; i++) {
                        document.getElementById(i).checked = true
                    }
                } else {
                    for (let i = 1; i <= userSelection.length; i++) {
                        document.getElementById(i).checked = false
                    }
                }

            })
   </script>
</body>
</body>
</html>