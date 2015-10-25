<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>

<jsp:include page="/JSP/common/header.jsp"></jsp:include>

<div class="container-fluid">
	<jsp:include page="/JSP/common/dash.jsp"></jsp:include>

	<c:choose>
		<c:when test="${modification}">

			<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
				<h2>Modification de la ressource : ${ressource.id}</h2>
				<form action="http://localhost:8080/RezRes/admin/ressources/modify"
					method="post" role="form" accept-charset="ISO-8859-1">
					<div class="form-group">
					<input type="hidden" class="form-control" name="id" value="${ressource.id}" required>
					</div>
					<div class="form-group">
						<label>Nom</label> <input type="text" class="form-control"
							name="nom" value="${ressource.nom}" required>
					</div>
					<div class="form-group">
						<label>Description</label> <input type="text" class="form-control"
							name="description" value="${ressource.description}" required>
					</div>
					<div class="form-group">
						<label>Localité</label> <input type="text" class="form-control"
							name="localite" value="${ressource.localite}" type="tel" required>
					</div>
					<div class="form-group">
						<label>Responsable</label> <input type="number"
							class="form-control" name="responsable"
							value="${ressource.responsable}" required>
					</div>
					<div class="form-group">
						<label>Type</label><select name="type" class="form-control">
						<c:forEach items="${typeRessources}" var="typeRessource">
							<option value="${typeRessource.cle}">${typeRessource.libelle}</option>
						</c:forEach>
						</select>
					</div>					
					<button type="submit" class="btn btn-default btn-sm">
						<span class="glyphicon glyphicon-ok"></span>
					</button>
				</form>
			</div>
		</c:when>

		<c:when test="${creation}">

			<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
				<form action="http://localhost:8080/RezRes/admin/ressources/create"
					method="post" role="form" accept-charset="ISO-8859-1">
					<div class="form-group">
						<label>Nom</label> <input type="text" class="form-control"
							name="nom" value="${ressource.nom}" required>
					</div>
					<div class="form-group">
						<label>Description</label> <input type="text" class="form-control"
							name="description" value="${ressource.description}" required>
					</div>
					<div class="form-group">
						<label>Localité</label> <input type="text" class="form-control"
							name="localite" value="${ressource.localite}" type="tel" required>
					</div>
					<div class="form-group">
						<label>Responsable</label> 
						<select type="number"
							class="form-control" name="responsable" required>
							<c:forEach items="${Utilisateurs}" var="U">
								<option value="${U.id}">${U.login}</option>
							</c:forEach>
						</select>
					</div>
					<div class="form-group">
						<label>Type</label><select name="type" class="form-control">
						<c:forEach items="${typeRessources}" var="typeRessource">
							<option value="${typeRessource.cle}">${typeRessource.libelle}</option>
						</c:forEach>
						</select>
					</div>
					<button type="submit" class="btn btn-default btn-sm">
						<span class="glyphicon glyphicon-plus"></span>
					</button>
				</form>
			</div>
		</c:when>

		<c:otherwise>
			<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
				<h1 class="page-header">${body}</h1>
				<table class="table tact">
					<thead>
						<tr>
							<th>Id</th>
							<th>Nom</th>
							<th>Description</th>
							<th>Localité</th>
							<th>Responsable</th>
							<th>Type</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${ressources}" var="res">
							<tr class="info">
								<td>${res.id}</td>
								<td>${res.nom}</td>
								<td>${res.description}</td>
								<td>${res.localite}</td>
								<td>${res.responsable}</td>
								<td>${res.typeRessource.libelle}</td>
								<td>
									<form action="/RezRes/admin/ressources/modify/form"
										class="form-inline" role="form" method="post">
										<input value="${res.id}" type="hidden" name="id">
										<button type="submit" class="btn btn-default btn-sm"><span class="glyphicon glyphicon-pencil"></span></button>
									</form>
									<form action="/RezRes/admin/ressources/delete"
										class="form-inline" role="form" method="post">
										<input value="${res.id}" type="hidden" name="id">
										<button type="submit" class="btn btn-default btn-sm">
											<span class="glyphicon glyphicon-trash"></span>
										</button>
									</form>
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>

				<form action="/RezRes/admin/ressources/create/form"
					class="form-inline" role="form" method="post">
					<button type="submit" class="btn btn-default btn-sm">
					<span class="glyphicon glyphicon-plus"></span>
					</button>
				</form>
			</div>
		</c:otherwise>
	</c:choose>


</div>

<jsp:include page="/JSP/common/footer.jsp"></jsp:include>