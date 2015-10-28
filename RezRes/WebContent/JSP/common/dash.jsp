<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- class="active" -->


<div class="row">
	<div class="col-sm-3 col-md-2 sidebar">
		<ul class="nav nav-sidebar">
			<li><h3>Utilistateur :</h3></li>
			<li <c:if test="${menu_entry == 0}" >class="active"</c:if>><a href="<c:url value="/user"/>">Mes réservations</a></li>
			<li <c:if test="${menu_entry == 1}" >class="active"</c:if>><a href="<c:url value="/user/reservation"/>">Réserver</a></li>
		</ul>
		<c:if test="${admin==true }">
		
			<ul class="nav nav-sidebar">
				<li><h3>Administrateur :</h3></li>
				<li <c:if test="${menu_entry == 2}" >class="active"</c:if>><a href="<c:url value="/admin"/>">Panorama</a></li>
				<li <c:if test="${menu_entry == 3}" >class="active"</c:if>><a href="<c:url value="/admin/types"/>">Gestion des types</a></li>
				<li <c:if test="${menu_entry == 4}" >class="active"</c:if>><a href="<c:url value="/admin/ressources"/>">Gestion des ressources</a></li>
				<li <c:if test="${menu_entry == 5}" >class="active"</c:if>><a href="<c:url value="/admin/users"/>">Gestion des utilisateurs</a></li>
			</ul>
		</c:if>
	</div>
</div>
