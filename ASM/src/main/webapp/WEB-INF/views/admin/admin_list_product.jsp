<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link rel="stylesheet" href="/pagination/simplePagination.css" type="text/css">
<div class="container">
	<div class="row">
		<div class="col-12">
			<!--   Kitchen Sink -->
			<div class="panel panel-default">
				<div class="panel-heading">

					<a href="/admin/index"><i class="fa fa-chevron-left "
						aria-hidden="true"></i></a> LIST PRODUCT
				</div>
				<div class="panel-body">
					<div class="table-responsive">
						<table class="table table-striped table-bordered table-hover">
							<thead>
								<tr>
									<th>Name</th>
									<th>Image</th>
									<th>Price</th>
									<th>Available</th>
									<th>Name Category</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="p" items="${page.content}">
									<tr>
										<td>${p.name}</td>
										<td><img style="width: 100px; height: 100px"
											src="/img/img_p/${p.image}" alt=""></td>
										<td>${p.price}</td>
										<td>${p.available? 'Cón hàng': 'Hết hàng'}</td>
										<td>${p.category.name}</td>
										<td><a class="btn btn-primary"
											href="/admin/editProd/${p.id}">Edit</a> <a
											class="btn btn-danger" href="/admin/deleteProd/${p.id}">Delete</a>
										</td>
									</tr>
								</c:forEach>
							</tbody>


						</table>
						<nav>
							<ul class="pagination">
								<%-- <li ><a href="/admin/listProd?p=0"
									aria-label="Previous"><span aria-hidden="true">&laquo;</span></a></li>
									
								<c:forEach var="pa" items="${numP}">
									<li class="active"><a
										href="/admin/listProd?p=${pa}">${pa}<span
											class="sr-only"></span></a></li>
								</c:forEach>	
								
								<li><a href="/admin/listProd?p=${page.totalPages-1}" aria-label="Next"> <span
										aria-hidden="true">&raquo;</span>
								</a></li> --%>
								
							</ul>
							<input type="text" id="list" hidden value="${page.totalPages}">
							<input type="text" id="itP" hidden value="${param.p}">
							<%-- <ul>
								<li>Số thực thể hiện tại: ${page.numberOfElements}</li>
								<li>Trang số: ${page.number + 1}/${page.totalPages}</li>
								<li>Kích thước trang: ${page.size}</li>
								<li>Tổng số thực thể: ${page.totalElements}</li>
								<li>Tổng số trang: ${page.totalPages}</li>
							</ul> --%>
						</nav>
					</div>
				</div>
			</div>
			<!-- End  Kitchen Sink -->
		</div>

	</div>
</div>
<script src="/js/jquery-3.3.1.min.js"></script>
<script src="/pagination/jquery.simplePagination.js"></script>

<script>
    $('.pagination').pagination({
        items: $("#list").val(),// tổng số phần tử 
        itemOnPage: 5,
        currentPage: $("#itP").val(),
        cssStyle: 'light-theme',
        prevText: '<span aria-hidden="true">&laquo;</span>',
        nextText: '<span aria-hidden="true">&raquo;</span>',
        onInit: function () {
            // fire first page loading
        },
        onPageClick: function (page, evt) {
            // some code
            let link = document.getElementsByTagName("").href = "p"
            console.log(page);
            console.log(evt);
        }
    });
</script>