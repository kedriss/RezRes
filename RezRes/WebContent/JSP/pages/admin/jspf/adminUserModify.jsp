<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>



Modification des information de l'utilisateur : ${utilisateur.login}
<form action="<c:url value="/admin/users/modify"/>" method="post"role="form">
					<div class="form-group">
						<label>Prénom</label> <input type="text" class="form-control"
							name="prenom" value="${utilisateur.prenom}" required>
					</div>
					<div class="form-group">
						<label>Nom</label> <input type="text" class="form-control"
							name="nom" value="${utilisateur.nom}" required>
					</div>
					<div class="form-group">
						<label>Mail</label> <input type="email" class="form-control"
							name="mail" value="${utilisateur.mail}" required>
					</div>
					<div class="form-group">
						<label>Téléphone</label> <input type="text" class="form-control"
							name="telephone" value="${utilisateur.telephone}" type="tel"
							pattern="^((\+\d{1,3}(-| )?\(?\d\)?(-| )?\d{1,5})|(\(?\d{2,6}\)?))(-| )?(\d{3,4})(-| )?(\d{4})(( x| ext)\d{1,5}){0,1}$"
							required>
					</div>
					<div class="form-group">
						<input type="hidden" class="form-control" name="login"
							value="${utilisateur.login}" required> <input
							type="hidden" class="form-control" name="id"
							value="${utilisateur.id}" required>
					</div>
					<div class="form-group">
						<label>Type</label><select name="type" class="form-control">
							<option value=0 selected="selected">Utilisateur
								classique</option>
							<option value=1>Administrateur</option>
						</select>
					</div>
					<button type="submit" class="btn btn-default btn-sm">
						<span class="glyphicon glyphicon-ok"></span> Modifier
					</button>
				</form>