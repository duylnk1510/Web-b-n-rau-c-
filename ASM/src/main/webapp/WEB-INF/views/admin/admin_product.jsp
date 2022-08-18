<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<div class="container">
	<div class="row">
		<div class="col-12">
			<div class="panel panel-primary">
				<div class="panel-heading">PRODUCT</div>
				<div class="panel-body">

					<form:form action="/admin/addProduct" role="form" modelAttribute="product" 
														method="post" enctype="multipart/form-data" >
						<div class="form-group">
							<label>Categories</label>
							<form:select class="form-control" path="category">
								<form:options items="${Categories}" itemLabel="name" itemValue="id"/>
							</form:select>
						</div>
						<div class="form-group">
							<label>Name</label>
							<form:input class="form-control" path="name"/>
						</div>

						<div class="form-group">
							<label>Price</label> 
							<form:input class="form-control" path="price"/>
						</div>

						<div class="form-group">
							<label>Image</label> <input type="file" name="upload" id="upload"
								onchange="getNameImg()" />

							<div id="displayImg">
								<img style="border: 1px solid;width: 100px; height: 100px" id="image" src="/img/img_p/${img!=null? img:'none.jpg'}"
									alt="">
							</div>

							
							<form:input type="hidden" class="form-control" id="nameImg" path="image"/>
						</div>


						<button type="submit" class="btn btn-info">Add</button>
						<button class="btn btn-info" formaction="/admin/updatePro/${idPro}">Update</button>
						<button class="btn btn-info" formaction="/admin/resetProd">Clear</button>
						<button class="btn btn-success" formaction="/admin/listProd">View list</button>
						

					</form:form >
					
					

				</div>
			</div>
		</div>
	</div>
</div>

<script>
	function getNameImg() {
		let file = document.getElementById('upload').files;
		if (file.length > 0) {

			let fileLoad = file[0];
			let fileRender = new FileReader();
			fileRender.onload = function(fileLoaderEvent) {
				let srcData = fileLoaderEvent.target.result;
				let newImage = document.getElementById('image');
				newImage.src = srcData;
				document.getElementById('displayImg').innerHTML = newImage.outerHTML;//lấy ra 1 ảnh                
			}
			fileRender.readAsDataURL(fileLoad);

		}

		let imgFile = document.getElementById("upload");
		let imgFileSplit = imgFile.value.split("\\");
		let nameImg = imgFileSplit[imgFileSplit.length - 1];
		console.log(nameImg);
		document.getElementById("nameImg").value = nameImg;
	}
</script>

