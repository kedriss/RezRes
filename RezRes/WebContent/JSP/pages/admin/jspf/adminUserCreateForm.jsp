<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
    
    

<div id="create" class="tab-pane ${create}" aria-expanded="false">
						<!--  Creation d'un utilisateur -->
						<c:if test="${loginExistant==true}">
							<div class="alert alert-danger">
								<strong>Refusé!</strong> Ce login est déjà pris.
							</div>
						</c:if>
						<c:if test="${loginCreer==true}">
							<div class="alert alert-success">
								<strong>OK!</strong> Utilisateur correctement créé.
							</div>
						</c:if>
						<form action="<c:url value="/admin/users/create"/>" method="post"
							role="form">
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
									name="telephone" type="tel"
									pattern="^((\+\d{1,3}(-| )?\(?\d\)?(-| )?\d{1,5})|(\(?\d{2,6}\)?))(-| )?(\d{3,4})(-| )?(\d{4})(( x| ext)\d{1,5}){0,1}$"
									required>
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
									<option value=0 selected="selected">Utilisateur
										classique</option>
									<option value=1>Administrateur</option>
								</select>
							</div>
							<button type="submit" class="btn btn-default btn-sm">
								<span class="glyphicon glyphicon-ok"></span> Créer
							</button>
						</form>

						<!--  fin de la creation d'un utilisateur -->
					</div>