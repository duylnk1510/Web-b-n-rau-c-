

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!-- Categories Section Begin -->
<section class="categories mb-4">
	<div class="container">
		<c:if test="${loadProducts == null}">
			<section class="section-title">
				<h2>Sản phẩm bán chạy</h2>
			</section>
		</c:if>
		<div class="row">
				<div class="categories__slider owl-carousel">

					<c:forEach var="p" items="${topP}">

						<div class="col-lg-3 col-md-4 col-sm-6">
							<div class="product__item">
								<div class="product__item__pic set-bg"
									data-setbg="/img/img_p/${p.image}">
									<ul class="product__item__pic__hover">
										<li><a href="/detail/${p.id}"><i
												class="fa fa-shopping-cart"></i></a></li>

									</ul>
								</div>
								<div class="product__item__text">
									<h6>
										<a href="#">${p.name}</a>
									</h6>
									<h5>${p.price}00VNĐ</h5>
								</div>
							</div>
						</div>
					</c:forEach>

				</div>
			</div>

		<c:forEach var="item" items="${loadCategories}">
			<section class="section-title">
				<h2>${item.name}</h2>
			</section>
			<div class="row">
				<div class="categories__slider owl-carousel">

					<c:forEach var="iP" items="${item.products}">

						<div class="col-lg-3 col-md-4 col-sm-6">
							<div class="product__item">
								<div class="product__item__pic set-bg"
									data-setbg="/img/img_p/${iP.image}">
									<ul class="product__item__pic__hover">
										<li><a href="/detail/${iP.id}"><i
												class="fa fa-shopping-cart"></i></a></li>

									</ul>
								</div>
								<div class="product__item__text">
									<h6>
										<a href="#">${iP.name}</a>
									</h6>
									<h5>${iP.price}00VNĐ</h5>
								</div>
							</div>
						</div>
					</c:forEach>

				</div>
			</div>
		</c:forEach>

		<!-- Load products search -->
		<div class="row">



			<c:forEach var="iP" items="${loadProducts}">

				<div class="col-lg-3 col-md-4 col-sm-6">
					<div class="product__item">
						<div class="product__item__pic set-bg"
							data-setbg="/img/img_p/${iP.image}">
							<ul class="product__item__pic__hover">
								<li><a href="/detail/${iP.id}"><i
										class="fa fa-shopping-cart"></i></a></li>
							</ul>
						</div>
						<div class="product__item__text">
							<h6>
								<a href="#">${iP.name}</a>
							</h6>
							<h5>${iP.price}00VNĐ</h5>
						</div>
					</div>
				</div>
			</c:forEach>

		</div>
		<!--End Load products search -->

	</div>
</section>


<!-- Categories Section End -->

