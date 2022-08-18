<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!-- Shoping Cart Section Begin -->
<section class="shoping-cart spad">
	<div class="container">
		<div class="row">
			<div class="col-lg-12">
				<div class="shoping__cart__table">
					<table>
						<thead>
							<tr>
								<th class="shoping__product">Products</th>
								<th>Price</th>
								<th>Quantity</th>
								<th>Total</th>
								<th></th>
							</tr>
						</thead>
						<tbody>

							<c:forEach var="item" items="${cart.items}">
								<form action="/cart/update/${item.id}" method="post">
									<tr>
										<td class="shoping__cart__item"><img
											src="/img/img_p/${item.img}" style="width: 100px; height: 100px" alt="">
											<h5>${item.name}</h5></td>
										<td class="shoping__cart__price">${item.price}00VNĐ</td>
										<td class="shoping__cart__quantity">
											<div class="quantity">
												<!-- <div class="pro-qty"> -->

												
													<button class="btn btn-secondary btn-xs"
													style="padding: 0px 6px 5px 6px; background-color: #d1d2d3; border: none"
													onclick="this.parentNode.querySelector('input[type=number]').stepDown()">
													-</button>
												<input type="number" name="qty" value="${item.qty}"
													onblur="this.form.submit()"
													style="width: 20%; border: none;">

												<button class="btn btn-secondary btn-xs"
													style="padding: 0px 6px 5px 6px; background-color: #d1d2d3; border: none"
													onclick="this.parentNode.querySelector('input[type=number]').stepUp()">
													+</button>
											</div> <!-- </div> -->
										</td>
										<td class="shoping__cart__total">
											<fmt:formatNumber type="number" pattern="#,###" value="${item.price * item.qty}" />.000VNĐ
										</td>
										<td class="shoping__cart__item__close"><a
											href="/cart/delete/${item.id}"><span class="icon_close"></span></a>
										</td>
									</tr>
								</form>

							</c:forEach>


						</tbody>
					</table>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-lg-6 offset-6">
				<div class="shoping__checkout">
					<h5>Cart Total</h5>
					<ul>
						<li>Total
							 <span>
								<fmt:formatNumber type="number" pattern="#,###" value="${amount}"/>.000VNĐ			
							</span>
						</li>
					</ul>
					<a href="/cart/checkout" class="primary-btn">PROCEED TO CHECKOUT</a>
				</div>
			</div>
		</div>
	</div>
</section>
<!-- Shoping Cart Section End -->