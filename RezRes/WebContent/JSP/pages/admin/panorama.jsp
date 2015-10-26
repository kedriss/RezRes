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


		<form action="<c:url value="/admin/filter"/>" method="post"
			role="form">
			<div class="form-group">
				<label>Date de début</label> <input type="date" class="form-control"
					name="startDate" value="" required>
			</div>
			<div class="form-group">
				<label>Heure de début</label> <input type="time"
					class="form-control" name="startTime" value="" required>
			</div>
			<div class="form-group">
				<label>Date de Fin</label> <input type="date" class="form-control"
					name="endDate" value="" required>
			</div>
			<div class="form-group">
				<label>Heure de Fin</label> <input type="time" class="form-control"
					name="endTime" value="" required>
			</div>
			<button type="submit" class="btn btn-default btn-sm">
				<span class="glyphicon glyphicon-search"></span>
			</button>
		</form>

		<table class="table tact">
			<thead>
				<tr>
					<th>Ressource reservée</th>
					<th>Utilisateur</th>
					<th>Date de début</th>
					<th>Date de fin</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${reservations}" var="reser">
					<tr class="info">
						<td>${reser.ressource.nom}</td>
						<td>${reser.utilisateur.nom}${reser.utilisateur.prenom}</td>
						<td>${reser.dateDebut}</td>
						<td>${reser.dateFin}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</div>

<jsp:include page="/JSP/common/footer.jsp"></jsp:include>