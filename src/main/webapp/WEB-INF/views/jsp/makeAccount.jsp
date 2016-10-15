<%@ page contentType="text/html; charset=UTF-8" session="false"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<html lang="en">
<!--<![endif]-->
<head>
<!-- Title -->
<title>PiggyBank Нова сметка</title>
<!-- Meta -->
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<meta name="description" content="">
<meta name="author" content="">
<meta name="viewport"
	content="width=device-width, initial-scale=1, maximum-scale=1" />
<!-- Favicon -->
<link href="favicon.ico" rel="shortcut icon">
<link rel="icon" href="img/prase.png">
<!-- Bootstrap Core CSS -->
<link rel="stylesheet" href="css/bootstrap.css">
<!-- Template CSS -->
<link rel="stylesheet" href="css/animate.css">
<link rel="stylesheet" href="css/font-awesome.css">
<link rel="stylesheet" href="css/nexus.css">
<link rel="stylesheet" href="css/responsive.css">
<link rel="stylesheet" href="css/custom.css">
<!-- Google Fonts-->
<link href="http://fonts.googleapis.com/css?family=Raleway:100,300,400"
	type="text/css" rel="stylesheet">
<link href="http://fonts.googleapis.com/css?family=Roboto:400,300"
	type="text/css" rel="stylesheet">
</head>
<body>
	<%
		if (request.getSession(false) == null) {
			response.sendRedirect("login.jsp");
		}
	%>
	<!-- Header -->
	<div id="header"
		style="background-position: 50% 0%; <br />
<b>Notice</b>:  Undefined variable: full_page in <b>C:\xampp\htdocs\bootstrap\html\php\header.php</b> on line <b>46</b><br />
"
		data-stellar-background-ratio="0.5">
		<div class="container">
			<div class="row">
				<!-- Logo -->
				<div class="logo">
					<a href="index.html" title=""><img src="img/logo_bank.png"
						alt="Logo" /></a>
				</div>
				<!-- End Logo -->
			</div>
			<!-- Top Menu -->
			<div id="hornav" class="row text-light">
				<div class="col-md-12">
					<div class="text-center visible-lg">
						<ul id="hornavmenu" class="nav navbar-nav">
							<li><a href="index.html" class="fa-home active">НАЧАО</a></li>
							<li><span class="fa-gears ">ПРОФИЛ</span>
								<ul>
									<li><a href="personalInfo.html">Лична информация</a></li>
									<li><a href="changePassword.html">Смяна на паролата</a></li>
								</ul></li>
							<li><span class="fa-copy ">ТРАНСАКЦИИ</span>
								<ul>
									<li><a href="transactions.html">Моите трансакции</a></li>
									<li><a href="makeTransaction.html">Нова трансакция</a></li>
								</ul></li>
							<li><span class="fa-th ">КАРТИ</span>
								<ul>
									<li><a href="myCards.html">Моите карти</a></li>
									<li><a href="createCard.html">Направи нова карта</a></li>
								</ul></li>
							<li><span class="fa-font ">СМЕТКИ</span>
								<ul>
									<li><a href="myAccounts.html">Моите сметки</a></li>
									<li><a href="makeAccount.html">Направи нова сметка</a></li>
								</ul></li>
							<li><a href="contact.html" class="fa-comment ">КОНТАКТИ</a>
							</li>
						</ul>
					</div>
				</div>
			</div>
			<!-- End Top Menu -->
		</div>
	</div>
	<!-- End Header -->
	<!-- === END HEADER === -->
	<!-- === BEGIN CONTENT === -->
	<div id="content">
		<div class="container background-white">
			<div class="row margin-vert-40">
				<!-- Begin Sidebar Menu -->
				<div class="col-md-3">
					<ul class="list-group sidebar-nav" id="sidebar-nav">
						<li class="list-group-item"><a href="myAccounts.html">Моите
								сметки</a></li>
						<li class="list-group-item"><a href="makeAccount.html">Нова
								сметка</a></li>
					</ul>
				</div>
				<!-- Main Text -->
				<div class="col-md-9">
					<h2 class="margin-bottom-30">Нова сметка</h2>
					<div style="color: red;">
						<c:if test="${not empty errorMessage}">
							<c:out value="${errorMessage}" />
						</c:if>
					</div>
					<form method="post">
						<label>Име на сметката: </label>
						<div class="row margin-bottom-20">
							<div class="col-md-6 col-md-offset-0">
								<input class="form-control" type="text" name="name"
									required="required" data-validation="alphanumeric" data-validation-length="min3">
							</div>
						</div>
						<label>Тип: </label>
						<div class="row margin-bottom-20">
							<div class="col-md-6 col-md-offset-0">
								<select name="type">
									<c:forEach var="type" items="${accounts}">
										<option value="${type}">${type}</option>
									</c:forEach>
								</select>
							</div>
						</div>
						<label>Валута: </label>
						<div class="row margin-bottom-20">
							<div class="col-md-6 col-md-offset-0">
								<select name="currency">
									<c:forEach var="currency" items="${currencies}">
										<option value="${currency}">${currency}</option>
									</c:forEach>
								</select>
							</div>
						</div>
						<label>Сума: </label>
						<div class="row margin-bottom-20">
							<div class="col-md-6 col-md-offset-0">
								<input class="form-control" type="number" name="sum"
									required="required" data-validation="number" data-validation-ignore="$">
							</div>
						</div>
						<p>
							<button type="submit" class="btn btn-primary">Направи</button>
						</p>
					</form>
				</div>
				<!-- End Main Text -->
			</div>
		</div>
	</div>
	<!-- End Content -->


	<script type="text/javascript" src="js/jquery.min.js"></script>
	<script type="text/javascript" src="js/bootstrap.min.js"></script>
	<script type="text/javascript" src="js/scripts.js"></script>
	<!-- Isotope - Portfolio Sorting -->
	<script type="text/javascript" src="js/jquery.isotope.js"></script>
	<!-- Mobile Menu - Slicknav -->
	<script type="text/javascript" src="js/jquery.slicknav.js"></script>
	<!-- Animate on Scroll-->
	<script type="text/javascript" src="js/jquery.visible.js"
		charset="utf-8"></script>
	<!-- Stellar Parallax -->
	<script type="text/javascript" src="js/jquery.stellar.js"
		charset="utf-8"></script>
	<!-- Sticky Div -->
	<script type="text/javascript" src="js/jquery.sticky.js"
		charset="utf-8"></script>
	<!-- Slimbox2-->
	<script type="text/javascript" src="js/slimbox2.js" charset="utf-8"></script>
	<!-- Modernizr -->
	<script src="js/modernizr.custom.js" type="text/javascript"></script>
	<!-- End JS -->
	<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/jquery-form-validator/2.3.26/jquery.form-validator.min.js"></script>
	<script>
		$.validate();
	</script>
</body>
</html>
<!-- === END FOOTER === -->