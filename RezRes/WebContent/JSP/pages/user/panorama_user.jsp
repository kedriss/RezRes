<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>

<jsp:include page="/JSP/common/header.jsp"></jsp:include>



<div class="container-fluid">
	<jsp:include page="/JSP/common/dash.jsp"></jsp:include>

	<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
		<h1>Mes réservations</h1>
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
						<td><fmt:formatDate value="${reser.dateDebut}" pattern="yyyy-MM-dd HH:mm" /></td>
						<td><fmt:formatDate value="${reser.dateFin}" pattern="yyyy-MM-dd HH:mm" /></td>
						<td>
							<form action="/RezRes/user/reservation/delete"
								class="form-inline" role="form" method="post">
								<input value="${reser.id}" type="hidden" name="id">
								<button type="submit" class="btn btn-default btn-sm">
									<span class="glyphicon glyphicon-trash"></span>
								</button>
							</form>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>

		<form action="/RezRes/user/reservation/create/form"
			class="form-inline" role="form" method="post">
			<button type="submit" class="btn btn-default btn-sm">
				<span class="glyphicon glyphicon-plus"></span>
			</button>
		</form>
	</div>
</div>

<jsp:include page="/JSP/common/footer.jsp"></jsp:include>