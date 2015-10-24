<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>

<jsp:include page="/JSP/common/header.jsp"></jsp:include>



<div class="container-fluid">
	<jsp:include page="/JSP/common/dash.jsp"></jsp:include>

	<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
		<h1 class="page-header">${body}</h1>
		<c:if test="${not empty warning}">
			<div class="alert alert-warning">
				<strong>Warning:</strong> 
				${warning}
			</div>
		</c:if>
		
		<h2>Rajouter un type ressource :</h2>
		<form action="/RezRes/admin/types/create" class="form-inline"
			role="form" method="post">
			<label>Nom du type :</label> <input class="form-control" type="nom"
				name="nom">
			<button type="submit" class="btn btn-default">Créer</button>
		</form>

		<c:if test="${mod_form == true}">
			<form action="/RezRes/admin/types/modify" class="form-inline"
				role="form" method="post">
				<label>Nouveau nom :</label>
				 <input class="form-control" type="nom" name="nom" value="${old_name}">
				 <input value="${old_cle}" type="hidden" name="cle">
				<button type="submit" class="btn btn-default">MODIFIER</button>
			</form>
		</c:if>

		<h2>Liste des types de ressources :</h2>
		<table class="table table-bordered table-hover">
			<thead>
				<tr>
					<th>ID</th>
					<th>NOM</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${list_type_res}" var="tres">
					<tr class="info">
						<td>${tres.cle}</td>
						<td>${tres.libelle}</td>
						<td>
							<form action="/RezRes/admin/types/modify" class="form-inline"
								role="form" method="post">
								<input value="${tres.cle}" type="hidden" name="cle">
								<button type="submit" class="btn btn-warning">MODIFIER</button>
							</form>
						</td>
						<td>
							<form action="/RezRes/admin/types/delete" class="form-inline"
								role="form" method="post">
								<input value="${tres.cle}" type="hidden" name="cle">
								<button type="submit" class="btn btn-danger">SUPPRIMER</button>
							</form>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</div>

<jsp:include page="/JSP/common/footer.jsp"></jsp:include>