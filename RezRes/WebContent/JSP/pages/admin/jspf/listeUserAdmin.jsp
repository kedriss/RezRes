<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
    
    
    

					<div id="pano" class="tab-pane fade in ${pano}"
						aria-expanded="true">

						<c:if test="${modifOK==true}">
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
										<td>
										<c:if test="${U.type==0}">
											Utilisateur classique
										</c:if>
										<c:if test="${U.type==1}">
											Administrateur
										</c:if>
										</td>
										<td><form action="<c:url value="/admin/users/modify"/>"
												class="form-inline" role="form" method="post">
												<input name="id" type="hidden" value="${U.id}" />
												<button type="submit" class="btn btn-default btn-sm">
													<span class="glyphicon glyphicon-pencil"></span> Modifier
												</button>
											</form></td>

										<td>
											<form action="<c:url value="/admin/users/delete"/>"
												class="form-inline" role="form" method="post">
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
					