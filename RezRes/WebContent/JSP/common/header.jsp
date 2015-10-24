<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<!-- BootStrap -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap-theme.min.css">
<link rel="stylesheet" href="/RezRes/CSS/dash.css">

<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
   <script type="text/javascript" src="../bootstrap/js/bootstrap.js"></script>

<title>${title}</title>
</head>
<body>

	<nav class="navbar navbar-default navbar-fixed-top">
	<div class="container-fluid">
		<!-- Brand and toggle get grouped for better mobile display -->
		<div class="navbar-header">
			<button type="button" class="navbar-toggle collapsed"
				data-toggle="collapse" data-target="#bs-example-navbar-collapse-1"
				aria-expanded="false">
				<span class="sr-only">Toggle navigation</span> <span
					class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="/RezRes/home">RezRes</a>
			<!-- TODO: Utiliser JSTL pour créer le lien. -->
		</div>

		<!-- Collect the nav links, forms, and other content for toggling -->
		<div class="collapse navbar-collapse"
			id="bs-example-navbar-collapse-1">
			<ul class="nav navbar-nav">
				<li><a href="/RezRes/user">Panorama</a></li>

				<li><a href="/RezRes/user/reservation">Réserver</a></li>
				<!-- TODO: Utiliser JSTL pour créer le lien. -->
			</ul>

			<ul class="nav navbar-nav navbar-right">
			<c:choose >
				<c:when test="${connected == true}"> 
				 
				<li><p class="navbar-btn">Bonjour ${utilisateurConnecte.login} !<a role="button" class="btn btn-success" href="/RezRes/login/out">Déconnexion</a></p></li>
				</c:when>
				<c:otherwise><li><p class="navbar-btn"><a role="button" class="btn btn-success" href="/RezRes/login">Connexion</a></p></li></c:otherwise>
				</c:choose>
				
			</ul>
		</div>
		<!-- /.navbar-collapse -->
	</div>
	<!-- /.container-fluid --> </nav>

	<div id="header"></div>

	