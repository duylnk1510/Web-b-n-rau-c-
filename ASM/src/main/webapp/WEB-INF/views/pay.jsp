<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<section class="checkout spad" style="padding: 0 0 5px">
	<div class="container">

		<div class="checkout__form">
			<h4>Billing Details</h4>
			<form action="/cart/pay">
				<div class="row">
					<div class="col-lg-8 col-md-6">
						<div class="row">
							<div class="col-12">
								<div class="checkout__input">
									<p>Usernname</p>
									<input type="text" value="${user.fullname}" required>
								</div>
							</div>

						</div>

						<div class="checkout__input">
							<p>Address</p>
							<input type="text" name="address" class="checkout__input__add" required>

						</div>

						<div class="row">
							<div class="col-12">
								<div class="checkout__input">
									<p>
										Phone
									</p>
									<input type="phone" required>
								</div>
							</div>

						</div>

					</div>
					<div class="col-lg-4 col-md-6">
						<div class="checkout__order">
							<h4>Your Order</h4>
							<div class="checkout__order__products">
								Products <span>Total</span>
							</div>
							<ul>
								<c:forEach var="i" items="${cart.items}">
									<li>${i.name}(x${i.qty}) <span>${i.price}00</span></li>
								</c:forEach>

							</ul>
							<hr>
							<div class="checkout__order__total">
								Total <span>${amount}00VNĐ</span>
							</div>

							<button type="submit" class="site-btn">Pay</button>
						</div>
					</div>
				</div>
			</form>
		</div>
	</div>
</section>