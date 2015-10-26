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
		<jsp:include page="/JSP/common/warning.jsp"></jsp:include>
		<c:choose>

			<c:when test="${modification==true}">
				<!-- CAS DE MODIFICATION D'UN UTILISATEUR -->
				<jsp:include page="/JSP/pages/admin/jspf/adminUserModify.jsp"></jsp:include>
				<!--FIN  CAS DE MODIFICATION D'UN UTILISATEUR -->
			</c:when>
			<c:otherwise>
				<!-- CAS de CREATION OU DE VISUALISATION -->
				<ul class="nav nav-tabs" id="tabs" data-tabs="tabs">
					<li class="${pano}"><a data-toggle="tab" href="#pano">Panorama</a></li>
					<li class="${create}"><a data-toggle="tab" href="#create">créer</a></li>
				</ul>
				<div class="tab-content">
					<jsp:include page="/JSP/pages/admin/jspf/listeUserAdmin.jsp"></jsp:include>
					<jsp:include page="/JSP/pages/admin/jspf/adminUserCreateForm.jsp"></jsp:include>	
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