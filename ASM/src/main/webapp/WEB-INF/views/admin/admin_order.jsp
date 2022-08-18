<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<style>
/* scroll */
::-webkit-scrollbar {
	width: 5px;
	background-color: white;
}

::-webkit-scrollbar-thumb {
	background-color: gray;
	border-radius: 10px;
}
</style>
<div class="container">
	<div class="row">
		<div class="col-12" style="padding-left: 15px;">
			<form action="/admin/order" class="navbar-form navbar-left" role="search">
				<div class="form-group">
					<input type="text" class="form-control" name="search" value="${param.search}" placeholder="Fullname">
				</div>
				<button type="submit" class="btn btn-default">Search</button>
			</form>
		</div>
	</div>
	<div class="panel-body">
		<div class="table-responsive"
			style="overflow-y: scroll; height: 500px;">
			<table class="table table-striped table-bordered table-hover">
				<thead>
					<tr style="position: sticky; top: -1px; background: #cfcfcf;">
						<th>Order Id</th>
						<th>Fullname</th>
						<th>Address</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="o" items="${oList}">

						<tr>

							<td style="text-align: center;"><a href="/admin/orderDetail/${o.id}"
								style="display: block; text-decoration: none;">${o.id}</a></td>
							<td>${o.account.fullname}</td>
							<td>${o.address}</td>

						</tr>

					</c:forEach>
				</tbody>

			</table>
		</div>
	</div>
</div>