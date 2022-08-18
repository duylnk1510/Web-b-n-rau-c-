<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- Product Details Section Begin -->
    <section class="product-details spad">
        <div class="container">
            <div class="row">
                <div class="col-lg-6 col-md-6">
                    <div class="product__details__pic">
                        <div class="product__details__pic__item">
                            <img class="product__details__pic__item--large"
                                src="/img/img_p/${p.image}" alt="">
                        </div>
                    </div>
                </div>
                <div class="col-lg-6 col-md-6">
	                <form action="/detailcart" method="get">
		                <div class="product__details__text">
	                        <h3>${p.name}</h3>
	                        <div class="product__details__price">${p.price}00 VNĐ</div>
	                        <div class="product__details__quantity">
	                            <div class="quantity">
	                                <div class="pro-qty">
	                                    <input id="qt" type="text" name="qt" value="1">
	                                   
	                                </div>
	                            </div>
	                        </div>
	                       <!-- <a href="/detailcart/${p.id}" class="primary-btn">ADD TO CARD</a> -->
	                        <input id="id" type="text" name="id" value="${p.id}" hidden>
	                        <input type="submit" class="primary-btn" value="ADD TO CARD"/>
	                        
	                    </div>
	                </form>                    
                </div>
                			
            </div>
        </div>
    </section>
    <!-- Product Details Section End -->

    <!-- Related Product Section Begin -->
    <section class="related-product">
        <div class="container">
            <div class="categories__slider owl-carousel">
                <c:forEach var="dP" items="${listDatail}">
						
						<div class="col-lg-3 col-md-4 col-sm-6">
                        <div class="product__item">
                            <div class="product__item__pic set-bg" data-setbg="/img/img_p/${dP.image}">
                                <ul class="product__item__pic__hover">
                                    <li><a href="/detail/${dP.id}"><i class="fa fa-shopping-cart"></i></a></li>
       
                                </ul>
                            </div>
                            <div class="product__item__text">
                                <h6><a href="#">${dP.name}</a></h6>
                                <h5>${dP.price}00.VNĐ</h5>
                            </div>
                        </div>
                    </div>
					</c:forEach> 
            </div>
            
        </div>
    </section>
    <!-- Related Product Section End -->