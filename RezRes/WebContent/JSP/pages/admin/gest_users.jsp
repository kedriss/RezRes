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
		<ul class="nav nav-tabs">
  <li class="active"><a data-toggle="tab" href="#pano">Panorama</a></li>
  <li><a data-toggle="tab" href="#create">créer</a></li>

</ul>

<div class="tab-content">
  <div id="pano" class="tab-pane fade in active">
    	<!--  Creation d'un utilisateur 
		<form action="users/create" class="form-inline" method="post" role="form">
		
		 <div class="form-group">
			<label>Prénom</label>
			<input type="text" class="form-control"name="prenom">
		</div>
		 <div class="form-group">
			<label>Nom</label>
			<input type="text" class="form-control"name="nom">
		</div>
		  <div class="form-group">
			<label>Mail</label>
			<input type="text"class="form-control" name="mail">
		</div>
		  <div class="form-group">
			<label>Téléphone</label>
			<input type="text" class="form-control"name="telephone">
		</div>
		<div class="form-group">
			<label>Type</label><select name="type" class="form-control">
				<!--  <option value=0 default> Utilisateur classique </option> 
				<option value=1> Administrateur </option>
			</select>
		</div>
		<button type="submit"  class="btn btn-default btn-sm">
        <span class="glyphicon glyphicon-ok"></span> Create 
        </button> 
		</form>

		<!--  fin de la creation d'un utilisateur 
  </div>
  <div id="create" class="tab-pane fade">
    <!-- affichage du panorama des utilisateur 
		<table class ="table table-bordered table-hover">
		
		<thead>
		<td>Prénom</td>
		<td>Nom</td>
		<td>Mail</td>
		<td>Téléphone</td>
		<td>Type</td>
		<td>Modifier</td>
		<td>Supprimer</td>
		</thead>
		
		<tbody>
		<c:forEach items="${Utilisateurs}" var="U">
		<tr>
		<td>${U.prenom}</td>
		<td>${U.nom}</td>
		<td>${U.mail}</td>
		<td>${U.telephone}</td>
		<td>${U.type}</td>
		<td><form action="users/modify" class ="form-inline" role="form" method="post">
				<input name="id" type="hidden" value ="${U.id}"/>
				<button type="submit" class="btn btn-default btn-sm">
		          <span class="glyphicon glyphicon-pencil"></span> Pencil 
		        </button>
	        </form>
        </td>
        
		<td>
		<form action="users/delete" class ="form-inline" role="form" method="post">
			<input name="id" type="hidden" value ="${U.id}"/>
			<button type="submit"  class="btn btn-default btn-sm">
          <span class="glyphicon glyphicon-remove"></span> Remove 
        </button>
        </form>
        </td>
		</tr>
		</c:forEach>
		</tbody></table>
		<!--  Fin de zone d'afichage du panorama des Utilisateurs -->
  </div>
</div>
		
	</div>
</div>


<jsp:include page="/JSP/common/footer.jsp"></jsp:include>