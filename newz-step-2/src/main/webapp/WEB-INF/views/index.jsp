<%@page language="java" pageEncoding="ISO-8859-1"
	contentType="text/html; ISO-8859-1" isELIgnored="false"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!doctype html>
<html lang="en">
<head>
<title>Newz Application</title>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
	integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
	crossorigin="anonymous">
<style>
body {
	background-color: lightgray;
}

.jumbotron {
	padding: 2rem 1rem;
}
</style>
</head>
<body>
	<div class="jumbotron jumbotron-fluid">
		<div class="container text-center">
			<h1 class="display-5">Newz App Assignment</h1>
		</div>
	</div>
	<div class="container-fluid">
		<div class="row p-4">
			<!-- Create a form which will have text boxes for News ID, title, author,description, content
		 along with a Send button. Handle errors like empty fields -->

			<div
				class="container card border border-secondary col-md-8 m-auto p-4">
				<div class="card-title text-center m-2 p-1">
					<h2>Add News Details</h2>
				</div>
				<form method="post" action="add" modelAttribute="newNews">
					<!-- <div class="form-group row">
						<div class="col-md-4">
							<label for="newsId">News Id</label>
						</div>
						<div class="col-md-8">
							<input type="number" class="form-control" id="newsId"
								name="newsId" placeholder="Enter Id" required>
						</div>
					</div> -->
					<div class="form-group row">
						<div class="col-md-4">
							<label for="newsTitle">News Name</label>
						</div>
						<div class="col-md-8">
							<input type="text" class="form-control" id="newsName" name="name"
								placeholder="Enter Name" required>
						</div>
					</div>
					<div class="form-group row">
						<div class="col-md-4">
							<label for="newsAuthor">News Author</label>
						</div>
						<div class="col-md-8">
							<input type="text" class="form-control" id="newsAuthor"
								name="author" placeholder="Enter Author" required>
						</div>
					</div>
					<div class="form-group row">
						<div class="col-md-4">
							<label for="newsContent">News Content</label>
						</div>
						<div class="col-md-8">
							<input type="text" class="form-control" id="newsAuthor"
								name="content" placeholder="Enter Content" required>
						</div>
					</div>
					<div class="form-group row">
						<div class="col-md-4">
							<label for="newsDescription">News Description</label>
						</div>
						<div class="col-md-8">
							<textarea row="10" col="50" type="text" class="form-control"
								id="newsDescription" name="description"
								placeholder="Enter Description" required></textarea>
						</div>
					</div>
					<div class="form-group  col-md-12 text-center">
						<button type="submit" class="btn btn-secondary btn-lg">Save</button>
					</div>
				</form>
			</div>

			<!-- display all existing news in a tabular structure with News ID, title, author,description,content
		publishedAt and Action(delete button) -->
			<div
				class="container card border border-secondary col-md-10  mt-4 p-4">
				<h3 class="text-center m-2 p-1">News Details</h3>
				<table class="table table-striped">
					<thead>
						<tr>
							<th scope="col">Id</th>
							<th scope="col">Name</th>
							<th scope="col">Author</th>
							<th scope="col">Content</th>
							<th scope="col">Description</th>
							<th scope="col">Created Date</th>
							<th scope="col">Action</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${newslist}" var="newsdata">
							<tr>
								<td>${newsdata.newsId}</td>
								<td>${newsdata.name}</td>
								<td>${newsdata.author}</td>
								<td>${newsdata.content}</td>
								<td>${newsdata.description}</td>
								<td>${newsdata.publishedAt}</td>
								<td><a href="getNewsById?newsId=${newsdata.newsId}"><i
										class="fa fa-edit"></i></a> <a
									href="delete?newsId=${newsdata.newsId}"><i
										class="fa fa-close"></i></a></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>

			<div
				class="container card border border-secondary col-md-8 mt-4 p-4">
				<div class="card-title text-center m-2 p-1">
					<h2>Update News Details</h2>
				</div>
				<form method="put" action="update" modelAttribute="updatedNews">
					<div class="row">
					   <input type="hidden" name="newsId" value="${search.newsId}">
						<div class="form-group col-md-6">
							<label for="newsTitle">News Name</label> <input type="text"
								class="form-control" id="newsName" name="name" value="${search.name}"
								placeholder="Enter Name" required>
						</div>
						<div class="form-group col-md-6">
							<label for="newsAuthor">News Author</label> <input type="text"
								class="form-control" id="newsAuthor" name="author" value="${search.author}"
								placeholder="Enter Author" required>
						</div>
						<div class="form-group col-md-6">
							<label for="newsContent">News Content</label> <input type="text"
								class="form-control" id="newsAuthor" name="content" value="${search.content}"
								placeholder="Enter Content" required>
						</div>
						<div class="form-group col-md-6">
							<label for="newsDescription">News Description</label>
							<input  type="text" class="form-control"
								id="newsDescription" name="description" value="${search.description}"
								placeholder="Enter Description" required>
						</div>
						<div class="form-group  col-md-12 text-center">
							<button type="submit" class="btn btn-secondary btn-lg">Update</button>
						</div>
					</div>
				</form>
			</div>

		</div>
	</div>
</body>
</html>



