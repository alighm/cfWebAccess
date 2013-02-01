<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
	<head>
		<title>Cloudstaria - Open PaaS on Private Cloud</title>
		<link rel="shortcut icon" href="resources/images/favicon.ico" type="image/x-icon" />
		<link href="resources/js/libs/bootstrap/css/bootstrap.min.css" rel="stylesheet" media="screen">
		<link href="resources/css/styles-new.css" rel="stylesheet" media="screen">
	</head>
	<body>
		<div class="navbar navbar-inverse navbar-fixed-top">
			<div class="navbar-inner">
				<div class="container">
					<div class="container-fluid">
						<div class="nav-collapse collapse">
							<ul class="nav">
								<li><a href="#">Home</a></li>
								<li><a href="#">Accounts</a></li>
								<li><a href="#about">About</a></li>
								<li class="dropdown">
									<a class="dropdown-toggle" href="#" data-toggle="dropdown">Contact <strong class="caret"></strong></a>
									<div class="dropdown-menu" style="padding: 15px; padding-bottom: 0px;">
										<form action="[YOUR ACTION]" method="post" accept-charset="UTF-8">
											<input id="user_username" style="margin-bottom: 15px;" type="text" placeholder="Firstname" name="user[username]" size="30" />
											<input id="user_username" style="margin-bottom: 15px;" type="text" placeholder="Lastname" name="user[username]" size="30" />
											<input id="user_username" style="margin-bottom: 15px;" type="text" placeholder="Email" name="user[username]" size="30" />
											<textarea rows="5"></textarea>
											<input class="btn btn-warning" style="clear: left; width: 100%; height: 32px; font-size: 13px;" type="submit" name="commit" value="Send" />
										</form>
									</div>
								<li>
							</ul>
							<!-- The drop down menu -->
							<ul class="nav pull-right">
								<li><a href="/users/sign_up">Sign Up</a></li>
								<li class="divider-vertical"></li>
								<li class="dropdown">
									<a class="dropdown-toggle" href="#" data-toggle="dropdown">Sign In <strong class="caret"></strong></a>
									<div class="dropdown-menu" style="padding: 15px; padding-bottom: 0px;">
										<form action="[YOUR ACTION]" method="post" accept-charset="UTF-8">
											<input id="user_username" style="margin-bottom: 15px;" type="text" placeholder="Email" name="user[username]" size="30" />
											<input id="user_password" style="margin-bottom: 15px;" type="password" placeholder="Password" name="user[password]" size="30" />
											<input id="user_remember_me" style="float: left; margin-right: 10px;" type="checkbox" name="user[remember_me]" value="1" />
											<label class="string optional" for="user_remember_me"> Remember me</label>
											<input class="btn btn-warning" style="clear: left; width: 100%; height: 32px; font-size: 13px;" type="submit" name="commit" value="Sign In" />
										</form>
									</div>
								</li>
							</ul>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="top">
			<div class="name">Cloudstaria</div>
			<div class="first-message">Develop and Deploy apps</div>
			<button class="btn btn-large btn-warning" type="button">SIGN UP - TODAY</button>
			<div class="second-message">Open PaaS on Private IaaS</div>
			<div class="cf-core">
				<a href="http://core.cloudfoundry.org" target="_blank"><img src="resources/images/cf-core.png"></a>
			</div>
		</div>
		<div class="bottom-bg">
			<div class="container">
				<header class="main-header subhead">
					<h1>Work, launch and scale faster!</h1>
					<p class="lead">Why you don't have to worry about anything else other than your code.</p>
					<p class="free">Unlimited apps within <span style="color:#75b3d5;">2GB RAM</span> for free!</p>
				</header>
				<div class="row-fluid">
					<ul class="thumbnails">
						<li class="span4">
							<div class="thumbnail">
								<img src="resources/images/services.png" alt="Services" />
								<h3>Service Management</h3>
								<p>We often find ourselves struggling with database, in memory data store, or message bus setup.
								With Cloudstaria, you are able to create your needed service(s) in a matter of seconds.
								Our open source PaaS (Cloud Foundry) allows you to create and bind your needed services with your deployed applications,
								without the need of system administrators.</p>
								<p class="btn" href="#">More details &rsaquo;&rsaquo;</p>
							</div>
						</li>
						<li class="span4">
							<div class="thumbnail">
								<img src="resources/images/frameworks.png" alt="Runtimes & Frameworks" />
								<h3>Runtimes & Frameworks</h3>
								<p>You can now easily use any of your favorate frameworks and runtimes knowing that they will "just work" on the cloud.
								The vast support of frameworks and runtimes provided by Cloud Foundry makes it possible for developers to easily deploy their
								apps onto the cloud in no time. Get full advantages of the PaaS such as auto-reconfiguration when developing in our supported frameworks.</p>
								<p class="btn" href="#">More details &rsaquo;&rsaquo;</p>
							</div>
						</li>
						<li class="span4">
							<div class="thumbnail">
								<img src="resources/images/applications.png" alt="Application Templates" />
								<h3>Application Templates</h3>
								<p>Choose among the listed application templates to get a head start on your project.
								In seconds, Cloudstaria will generate the default source for your app and will deploy it for you onto the cloud.
								Some sample apps will also include persistence layers (i.e. Spring and MySQL sample).
								All app templates will have a link to the github repo so that you can review the source code.</p>
								<p class="btn" href="#">More details &rsaquo;&rsaquo;</p>
							</div>
						</li>
					</ul>
				</div>
				<!-- <div class="row-fluid">
					<div class="span12">
						<div id="myCarousel" class="carousel slide">
							<div class="carousel-inner">
								<div class="item">
									<img src="http://www.wysoko.org/images/portfolio/7.jpg" alt="" />
									<div class="carousel-caption">
										<h4>Layers of Cloud Infrastructure</h4>
										<p>testing 123</p>
									</div>
								</div>
								<div class="item">
									<img src="http://www.wysoko.org/images/portfolio/6.jpg" alt="" />
									<div class="carousel-caption">
										<h4>Second Thumbnail label</h4>
										<p>testing 123</p>
									</div>
								</div>
								<div class="item active">
									<img src="http://www.wysoko.org/images/portfolio/5.jpg" alt="" />
									<div class="carousel-caption">
										<h4>Third Thumbnail label</h4>
										<p>testing 123</p>
									</div>
								</div>
							</div>
							<a class="carousel-control left" href="#myCarousel" data-slide="prev">&lsaquo;</a>
							<a class="carousel-control right" href="#myCarousel" data-slide="next">&rsaquo;</a>
						</div>
					</div>
				</div> -->
			</div>
		</div>
		<footer>
			<div class="container">
				<div class="row">
					<div class="span1 footer-cloud"><img src="resources/images/footer-cloud.png" /></div>
					<div class="span4"><p class="muted credit">Cloudstaria &copy; 2012 - All Rights Reserved.</p></div>
				</div>
			</div>
		</footer>
		<script src="http://code.jquery.com/jquery-latest.js"></script>
		<script src="resources/js/libs/bootstrap/js/bootstrap.min.js"></script>
	</body>
</html>