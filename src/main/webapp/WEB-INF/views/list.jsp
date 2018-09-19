<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	pageContext.setAttribute("APP_PATH", request.getContextPath());
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>员工列表</title>
<!-- 引入JQuery -->
<script src="/ssm-crud/src/main/webapp/static/js/jquery-3.3.1.slim.min.js"></script>
<!-- 最新版本的 Bootstrap 核心 CSS 文件 -->
<link rel="stylesheet"
	href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css"
	integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u"
	crossorigin="anonymous">

<!-- 可选的 Bootstrap 主题文件（一般不用引入） -->
<link rel="stylesheet"
	href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap-theme.min.css"
	integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp"
	crossorigin="anonymous">

<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
<script
	src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"
	integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa"
	crossorigin="anonymous"></script>
</head>
<body>
<p>${APP_PATH}</p>
	<div class="container">
		<!-- 标题 -->
		<div class="row">
			<div class="col-md-12">
				<h1>SSM- CRUD</h1>
			</div>
		</div>
		<!-- 按钮 -->
		<div class="row">
			<div class="col-md-2 col-md-offset-9">
				<button type="button" class="btn btn-primary">添加</button>
				<button type="button" class="btn btn-danger">删除</button>
			</div>
		</div>
		<!-- 显示数据表格 -->
		<div class="row">
			<div class="col-md-12">
				<table class="table table-hover">
					<tr>
						<th>#</th>
						<th>empName</th>
						<th>gender</th>
						<th>email</th>
						<th>deptName</th>
						<th>操作</th>
					</tr>

					<c:forEach items="${pageInfo.list}" var="emp">
						<tr>
							<th>${emp.empId}</th>
							<th>${emp.empName}</th>
							<th>${emp.gender=="M"?"男":"女"}</th>
							<th>${emp.email}</th>
							<th>${emp.department.deptName}</th>
							<th>
								<button type="button" class="btn btn-primary btn-sm">
									<span class="glyphicon glyphicon-pencil"></span>编辑
								</button>
								<a href="emp-del?empId=${emp.empId}">
								<button type="button" class="btn btn-danger btn-sm">
									删除<span class="glyphicon glyphicon-plus"></span>
								</button>
								</a>
							</th>
						</tr>
					</c:forEach>
				</table>
			</div>
		</div>
		<!-- 页码 -->
		<div class="row">
			<!-- 分页文字信息 -->
			<div class="col-md-6">
				当前第${pageInfo.pageNum}页，共${pageInfo.pages}页，共${pageInfo.total}条记录</div>
			<!-- 分页条信息 -->
			<div class="col-md-6">
				<ul class="pagination">
					<li><a href="#">首页</a></li>
					<c:choose>
						<c:when test="${pageInfo.pageNum==1}">
							<li class="disabled"><a href="#" aria-label="Previous">
									<span aria-hidden="true">&laquo;</span>
							</a></li>
						</c:when>
						<c:otherwise>
							<li><a href="#" aria-label="Previous"> <span
									aria-hidden="true">&laquo;</span></a></li>
						</c:otherwise>
					</c:choose>

					<c:forEach items="${pageInfo.navigatepageNums}" var="page_Num">
						<c:choose>
							<c:when test="${pageInfo.pageNum==page_Num }">
								<li class="active"><a href="#">${page_Num}</a></li>
							</c:when>
							<c:otherwise>
								<li><a href="emps?pn=${page_Num}">${page_Num}</a></li>
							</c:otherwise>
						</c:choose>

					</c:forEach>
					<li><a href="#" aria-label="Next"> <span
							aria-hidden="true">&raquo;</span>
					</a></li>
					<li><a href="#">尾页</a></li>
				</ul>
			</div>
		</div>
	</div>
</html>