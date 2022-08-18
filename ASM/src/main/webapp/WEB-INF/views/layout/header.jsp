<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<!-- Humberger Begin -->
<div class="humberger__menu__overlay"></div>
<div class="humberger__menu__wrapper">
	<div class="humberger__menu__logo">
		<a href="#"><img src="img/logo.png" alt=""></a>
	</div>

	<div class="humberger__menu__widget">

		<div class="header__top__right__auth">
			<a href="#"><i class="fa fa-user"></i> Login</a>
		</div>
	</div>
	<nav class="humberger__menu__nav mobile-menu">
		<li class="active"><a href="#">Fresh Meat</a></li>
		<ul>
			<li><a href="#">Vegetables</a> <!-- <ul class="header__menu__dropdown">
                        <li><a href="#">Vegetable 1</a></li>
                        <li><a href="#">Vegetable 2</a></li>
                        <li><a href="#">Vegetable 3</a></li>
                    </ul> --></li>

			<li><a href="#">Fruit & Nut Gifts</a></li>
			<li><a href="#">Fresh Berries</a></li>
			<li><a href="#">Ocean Foods</a></li>
			<li><a href="#">Butter & Eggs</a></li>
			<li><a href="#">Fastfood</a></li>
			<li><a href="#">Fresh Onion</a></li>
			<li><a href="#">Papayaya & Crisps</a></li>
			<li><a href="#">Oatmeal</a></li>
			<li><a href="#">Fresh Bananas</a></li>
		</ul>
	</nav>
	<div id="mobile-menu-wrap"></div>
	<div class="header__top__right__social">
		<a href="#"><i class="fa fa-facebook"></i></a> <a href="#"><i
			class="fa fa-twitter"></i></a> <a href="#"><i class="fa fa-linkedin"></i></a>
		<a href="#"><i class="fa fa-pinterest-p"></i></a>
	</div>
	<div class="humberger__menu__contact">
		<ul>
			<li><i class="fa fa-envelope"></i> hello@colorlib.com</li>
			<li>Free Shipping for all Order of $99</li>
		</ul>
	</div>
</div>
<!-- Humberger End -->

<!-- Header Section Begin -->
<header class="header">
	<div class="header__top">
		<div class="container">
			<div class="row">
				<div class="col-lg-6">
					<div class="header__top__left">
						<ul>
							<li><i class="fa fa-envelope"></i> hello@colorlib.com</li>
							<li>Free Shipping for all Order of $99</li>
						</ul>
					</div>
				</div>
				<div class="col-lg-6">
					<div class="header__top__right">
						<div class="header__top__right__social">
							<a href="#"><i class="fa fa-facebook"></i></a> <a href="#"><i
								class="fa fa-twitter"></i></a> <a href="#"><i
								class="fa fa-linkedin"></i></a> <a href="#"><i
								class="fa fa-pinterest-p"></i></a>
						</div>

						<div class="header__top__right__auth">
						<c:if test="${sessionScope.user == null}">
							<a href="/account/login"><i class="fa fa-user"></i> Login</a>
						</c:if>
						<c:if test="${sessionScope.user != null}">
							<a href="/account/logout"><i class="fa fa-user"></i> Logout</a>
						</c:if>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="container">
		<div class="row">
			<div class="col-lg-3">
				<div class="header__logo">
					<a href="/home/index"><img src="/img/logo.png" alt=""></a>
				</div>
			</div>
			<div class="col-lg-6"></div>
			<div class="col-lg-3">
				<div class="header__cart">
					<ul>

						<li><a href="/home/cart"><i class="fa fa-shopping-bag"></i> <span>${sumQty}</span></a></li>
					</ul>
					<div class="header__cart__price">
						item: <span>$150.0</span>
					</div>
				</div>
			</div>
		</div>
		<div class="humberger__open">
			<i class="fa fa-bars"></i>
		</div>
	</div>
</header>
<!-- Header Section End -->


<!-- Hero Section Begin -->
<section class="hero hero-normal">
	<div class="container">
		<div class="row">
			<div class="col-lg-3">
				<div class="hero__categories">
					<div class="hero__categories__all">
						<i class="fa fa-bars"></i> <span>All Category</span>
					</div>
					<ul>
						<li><a href="/home/index">All</a></li>
						<c:forEach var="item" items="${listCategories}">
							<li><a href="/home/${item.id}">${item.name}</a></li>
						</c:forEach>
						
					</ul>
				</div>
			</div>
			<div class="col-lg-9">
				<div class="hero__search mb-4">
					<div class="hero__search__form">
						<form action="/home/search">

							<input type="text" name="searchProd" placeholder="What do yo u need?" required>
							<button type="submit" class="site-btn">SEARCH</button>
						</form>
					</div>
					<div class="hero__search__phone">
						<div class="hero__search__phone__icon">
							<i class="fa fa-phone"></i>
						</div>
						<div class="hero__search__phone__text">
							<h5>+65 11.188.888</h5>
							<span>support 24/7 time</span>
						</div>
					</div>
				</div>
				<c:if test="${display == 'yes'}">
				<div class="hero__item set-bg" data-setbg="/img/hero/banner.jpg">
					<div class="hero__text">
						<span>FRUIT FRESH</span>
						<h2>
							Vegetable <br />100% Organic
						</h2>
						<p>Free Pickup and Delivery Available</p>
						<a href="#" class="primary-btn">SHOP NOW</a>
					</div>
				</div>
				</c:if>
			</div>
		</div>
	</div>
</section>
<!-- Hero Section End -->