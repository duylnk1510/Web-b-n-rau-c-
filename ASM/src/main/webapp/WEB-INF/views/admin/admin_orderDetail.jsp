<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
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
		<div class="col-12">

			<div class="table-responsive" style="overflow-y: scroll; height: 500px;">
				<table class="table table-striped table-bordered table-hover">
					<thead>
						<tr style="position: sticky; top: -1px; background: #cfcfcf;">
							<th>Name</th>
							<th>Image</th>
							<th>Price</th>
							<th>Quantity</th>

						</tr>
					</thead>
					<tbody>
						<c:forEach var="od" items="${odList}">
							<tr>
								<td>${od.product.name}</td>
								<td>
									<img style="width: 100px; height: 100px"
											src="/img/img_p/${od.product.image}" alt="">
								</td>
								<td>${od.price}00</td>
								<td>${od.quantity}</td>
							</tr>
						</c:forEach>
					</tbody>


				</table>
			</div>


		</div>
	</div>
</div>