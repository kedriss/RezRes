<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>

<jsp:include page="/JSP/common/header.jsp"></jsp:include>



<div class="container-fluid">
	<jsp:include page="/JSP/common/dash.jsp"></jsp:include>

<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
	<jsp:include page="/JSP/common/message.jsp"></jsp:include>
	<c:choose>
		<c:when test="${formCreate}">

				<h1>Que souhaitez vous?</h1>
				<form action="/RezRes/user/reservation" method="post" role="form">
					<div class="form-group">
						<label>Type</label><select name="type" class="form-control">
							<c:forEach items="${typeRessources}" var="typeRessource">
								<option value="${typeRessource.cle}">${typeRessource.libelle}</option>
							</c:forEach>
						</select>
					</div>
					<div class="form-group">
						<label>Date de début</label> <input type="date"
							class="form-control" name="startDate" value="${startDate}" required>
					</div>
					<div class="form-group">
						<label>Heure de début</label> <input type="time"
							class="form-control" name="startTime" value="${startTime}" required>
					</div>
					<div class="form-group">
						<label>Date de Fin</label> <input type="date" class="form-control"
							name="endDate" value="${endDate}" required>
					</div>
					<div class="form-group">
						<label>Heure de Fin</label> <input type="time"
							class="form-control" name="endTime" value="${endTime}" required>
					</div>
					<button type="submit" class="btn btn-default btn-sm">
						<span class="glyphicon glyphicon-ok"></span>
					</button>
				</form>
		
		</c:when>

		<c:otherwise>
				<h1>Selectionner la ressource à réserver</h1>
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
								<td>${res.utilisateur.login}</td>
								<td>${res.typeRessource.libelle}</td>
								<td>
									<form action="/RezRes/user/reservation/create"
										class="form-inline" role="form" method="post">
										<input value="${res.id}" type="hidden" name="id"> <input
											value="${startDate}" type="hidden" name="startDate">
										<input value="${startTime}" type="hidden" name="startTime">
										<input value="${endDate}" type="hidden" name="endDate">
										<input value="${endTime}" type="hidden" name="endTime">
										<button type="submit" class="btn btn-default btn-sm">
											<span class="glyphicon glyphicon-ok"></span>
										</button>
									</form>
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			
		</c:otherwise>
	</c:choose>
	</div>
</div>

<jsp:include page="/JSP/common/footer.jsp"></jsp:include>