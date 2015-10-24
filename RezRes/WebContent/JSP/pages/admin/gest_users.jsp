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


<c:choose>

<c:when test ="${modification==true}">

<!-- CAS DE MODIFICATION D'UN UTILISATEUR -->
Modification des information de l'utilisateur : ${utilisateur.login}
<form action="http://127.0.0.1:8080/RezRes/admin/users/modify"  method="post"role="form" accept-charset="ISO-8859-1">
					<div class="form-group">
						<label>Prénom</label> <input type="text" class="form-control"
							name="prenom"  value="${utilisateur.prenom}" required>
					</div>
					<div class="form-group">
						<label>Nom</label> <input type="text" class="form-control"
							name="nom" value="${utilisateur.nom}"required>
					</div>
					<div class="form-group">
						<label>Mail</label> <input type="email" class="form-control"
							name="mail"  value="${utilisateur.mail}"required>
					</div>
					<div class="form-group">
						<label>Téléphone</label> <input type="text" class="form-control"
							name="telephone" value="${utilisateur.telephone}" type="tel" pattern="^((\+\d{1,3}(-| )?\(?\d\)?(-| )?\d{1,5})|(\(?\d{2,6}\)?))(-| )?(\d{3,4})(-| )?(\d{4})(( x| ext)\d{1,5}){0,1}$" required>
					</div>
					<div class="form-group">
					<input type="hidden" class="form-control" name="login" value="${utilisateur.login}" required>
					<input type="hidden" class="form-control" name="id" value="${utilisateur.id}" required>
					</div>
					<div class="form-group">
						<label>Type</label><select name="type" class="form-control">
							<option value=0 selected="selected">Utilisateur classique</option>
							<option value=1>Administrateur</option>
						</select>
					</div>
					<button type="submit" class="btn btn-default btn-sm">
						<span class="glyphicon glyphicon-ok"></span> Modifier
					</button>
				</form>
<!--FIN  CAS DE MODIFICATION D'UN UTILISATEUR -->
</c:when>

<c:otherwise>
<!-- CAS de CREATION OU DE VISUALISATION -->
		<ul class="nav nav-tabs" id="tabs"  data-tabs="tabs">
			<li class="${pano}"><a data-toggle="tab" href="#pano">Panorama</a></li>
			<li class="${create}"><a data-toggle="tab" href="#create">créer</a></li>
		</ul>

		<div class="tab-content">
		<div id="pano" class="tab-pane fade in ${pano}" aria-expanded="true">
			
			<c:if test ="${modifOK==true}">
				<div class="alert alert-success">
  					<strong>OK!</strong> Utilisateur modifié.
				</div>
			</c:if>
				<!-- affichage du panorama des utilisateur-->
				<table class="table table-bordered table-hover">
					<thead>
					<tr>
						<td>Prénom</td>
						<td>Nom</td>
						<td>Login</td>
						<td>Mail</td>
						<td>Téléphone</td>
						<td>Type</td>
						<td>Modifier</td>
						<td>Supprimer</td>
						</tr>
					</thead>

					<tbody>
						<c:forEach items="${Utilisateurs}" var="U">
							<tr>
								<td>${U.prenom}</td>
								<td>${U.nom}</td>
								<td>${U.login}</td>
								<td>${U.mail}</td>
								<td>${U.telephone}</td>
								<td>${U.type}</td>
								<td><form action="http://127.0.0.1:8080/RezRes/admin/users/modify" class="form-inline"
										role="form" method="post">
										<input name="id" type="hidden" value="${U.id}" />
										<button type="submit" class="btn btn-default btn-sm">
											<span class="glyphicon glyphicon-pencil"></span> Modifier
										</button>
									</form></td>

								<td>
									<form action="http://127.0.0.1:8080/RezRes/admin/users/delete" class="form-inline" role="form"
										method="post">
										<input name="id" type="hidden" value="${U.id}" />
										<button type="submit" class="btn btn-default btn-sm">
											<span class="glyphicon glyphicon-remove"></span> Supprimer
										</button>
									</form>
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
				<!--  Fin de zone d'afichage du panorama des Utilisateurs -->
			</div>
		
			<div id="create" class="tab-pane ${create}" aria-expanded="false">
				<!--  Creation d'un utilisateur -->
				<c:if test ="${loginExistant==true}">
				<div class="alert alert-danger">
  					<strong>Refusé!</strong> Ce login est déjà pris.
				</div>
				</c:if>
				<c:if test ="${loginCreer==true}">
				<div class="alert alert-success">
  					<strong>OK!</strong> Utilisateur correctement créé.
				</div>
				</c:if>
				<form action="http://127.0.0.1:8080/RezRes/admin/users/create"  method="post"role="form" accept-charset="ISO-8859-1">
					<div class="form-group">
						<label>Prénom</label> <input type="text" class="form-control"
							name="prenom" required>
					</div>
					<div class="form-group">
						<label>Nom</label> <input type="text" class="form-control"
							name="nom" required>
					</div>
					<div class="form-group">
						<label>Mail</label> <input type="email" class="form-control"
							name="mail" required>
					</div>
					<div class="form-group">
						<label>Téléphone</label> <input type="text" class="form-control"
							name="telephone" type="tel" pattern="^((\+\d{1,3}(-| )?\(?\d\)?(-| )?\d{1,5})|(\(?\d{2,6}\)?))(-| )?(\d{3,4})(-| )?(\d{4})(( x| ext)\d{1,5}){0,1}$" required>
					</div>
					<div class="form-group">
						<label>Login</label> <input type="text" class="form-control"
							name="login" required>
					</div>
					<div class="form-group">
						<label>Mot de passe</label> <input type="password"
							class="form-control" name="pwd" required>
					</div>
					<div class="form-group">
						<label>Type</label><select name="type" class="form-control">
							<option value=0 selected="selected">Utilisateur classique</option>
							<option value=1>Administrateur</option>
						</select>
					</div>
					<button type="submit" class="btn btn-default btn-sm">
						<span class="glyphicon glyphicon-ok"></span> Créer
					</button>
				</form>

				<!--  fin de la creation d'un utilisateur -->
			</div>
	</div>

</c:otherwise>

</c:choose>
	</div>

	</div>


<script type="text/javascript">
    jQuery(document).ready(function ($) {
        $('#tabs').tab();
    });
</script>  
   
<jsp:include page="/JSP/common/footer.jsp"></jsp:include>