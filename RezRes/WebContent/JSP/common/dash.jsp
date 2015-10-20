<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- class="active" -->


<div class="row">
	<div class="col-sm-3 col-md-2 sidebar">
		<ul class="nav nav-sidebar">
			<li><h3>Utilistateur :</h3></li>
			<li <c:if test="${menu_entry == 0}" >class="active"</c:if>><a href="/RezRes/user">Panorama</a></li>
			<li <c:if test="${menu_entry == 1}" >class="active"</c:if>><a href="/RezRes/user/reservation">Réserver</a></li>
		</ul>

		<ul class="nav nav-sidebar">
			<li><h3>Administrateur :</h3></li>
			<li <c:if test="${menu_entry == 2}" >class="active"</c:if>><a href="/RezRes/admin">Panorama</a></li>
			<li <c:if test="${menu_entry == 3}" >class="active"</c:if>><a href="/RezRes/admin/types">Gestion des types</a></li>
			<li <c:if test="${menu_entry == 4}" >class="active"</c:if>><a href="/RezRes/admin/ressources">Gestion des ressources</a></li>
			<li <c:if test="${menu_entry == 5}" >class="active"</c:if>><a href="/RezRes/admin/users">Gestion des utilisateurs</a></li>
		</ul>
	</div>
</div>
