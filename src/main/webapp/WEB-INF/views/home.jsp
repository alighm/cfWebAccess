<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
	<head>
		<title>Ali Moghadam using Cloud Foundry - Java VCAP Client</title>
		<link href="http://dev.sencha.com/deploy/ext-4.0.7-gpl/resources/css/ext-all-gray.css" rel="stylesheet" type="text/css" />
		<link href="http://dev.sencha.com/deploy/ext-4.0.7-gpl/examples/shared/example.css" rel="stylesheet" type="text/css" />
		<link href="resources/css/styles.css" rel="stylesheet" type="text/css" />
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
		<meta name="description" content="Ali Moghadam & Cloud Foundry" />
		<meta name="author" content="Ali Moghadam" />
		<meta name="copyright" content="Copyright VMware 2012. All Rights Reserved." />
	</head>

	<body id="home" class="home">
		<header role="banner">
			<nav>
				<security:authorize access="isAuthenticated()">
					<ul class="container">
						<li><security:authentication property="principal.username" /> (<a href="logout" >Sign Out</a>)</li>
					</ul>
				</security:authorize>
				<security:authorize access="isAnonymous()">
					<ul class="container">
						<li id="signIn">Sponsored by <a href="http://www.linkedin.com/in/alighm" target="_blank" rel="external">Ali Moghadam</a></li>
					</ul>
				</security:authorize>
			</nav>
			<hgroup>
				<h1><a href="http://www.cloudfoundry.com" rel="external" target="_blank">Cloud Foundry</a></h1>
				<h2>The open platform as a service project</h2>
			</hgroup>
		</header>

		<section class="line" role="main">
			<p id="intro">Sample Spring Application Using <a href="https://github.com/cloudfoundry/vcap-java-client" target="_blank" rel="external">vcap-java-client</a></p>
		</section>
		<section><p id="target">(http://api.cloudfoundry.com)</p></section>
		<section id="main"></section>

		<footer class="footer"></footer>

		<script type="text/javascript" src="http://dev.sencha.com/deploy/ext-4.0.7-gpl/bootstrap.js"></script>
		<script type="text/javascript" src="http://dev.sencha.com/deploy/ext-4.0.7-gpl/examples/shared/examples.js"></script>
		<security:authorize access="isAuthenticated()">
			<script data-main="resources/js/mainApp" src="resources/js/libs/require/require.js"></script>
		</security:authorize>
		<security:authorize access="isAnonymous()">
			<script data-main="resources/js/mainLogin" src="resources/js/libs/require/require.js"></script>
		</security:authorize>
	</body>
</html>