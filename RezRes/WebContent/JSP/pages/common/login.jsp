<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<!-- BootStrap -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap-theme.min.css">
<link rel="stylesheet" href="/RezRes/CSS/login.css">

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
			<a class="navbar-brand" href="<c:url value="/home"/>">RezRes</a>
			<!-- TODO: Utiliser JSTL pour créer le lien. -->
		</div>

		<!-- Collect the nav links, forms, and other content for toggling -->
		<div class="collapse navbar-collapse"
			id="bs-example-navbar-collapse-1">

			<ul class="nav navbar-nav navbar-right">
				<li><p class="navbar-btn"><a role="button" class="btn btn-success" href="/RezRes/login">Login</a></p></li>
			</ul>
		</div>
		<!-- /.navbar-collapse -->
	</div>
	<!-- /.container-fluid --> </nav>

	<div id="container">
		<c:if test="${connectionRefused==true}">
			<div class="alert alert-danger">
  					<strong>Refusé!</strong> Vérifier votre login et mot de passe
			</div>
		</c:if>
		<form class="form-signin" method="post" role="form" action="<c:url value="/login"/>">
			<h2 class="form-signin-heading">Log in</h2>

			<label for="login" class="sr-only">Pseudo</label> 
			<input name="login" type="text" id="login" placeholder="login" class="form-control" required autofocus> 
			
			<label for="pwd" class="sr-only">Password</label> 
			<input name="pwd" type="password" id="pwd" class="form-control" placeholder="Password" required>

			<button class="btn btn-lg btn-primary btn-block" type="submit">Log in</button>
		</form>
	</div>
	
	<jsp:include page="/JSP/common/footer.jsp"></jsp:include>


