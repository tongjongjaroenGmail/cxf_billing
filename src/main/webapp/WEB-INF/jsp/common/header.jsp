
<div class="navbar navbar-default" id="navbar">
	<script type="text/javascript">
		try {
			ace.settings.check('navbar', 'fixed')
		} catch (e) {
		}
	</script>

	<div class="navbar-container" id="navbar-container">
		<div class="navbar-header pull-left">
			<a href="#" class="navbar-brand"> <small> <img alt="" src="img/logo.png"> Leave Management
			</small>
			</a>
			<!-- /.brand -->
		</div>
		<!-- /.navbar-header -->

		<div class="navbar-header pull-right" role="navigation">
			<ul class="nav ace-nav">
				<li class="light-blue"><a data-toggle="dropdown" href="#" class="dropdown-toggle"> 
					<%-- <img class="nav-user-photo" src="assets/avatars/user.jpg" alt="Jason's Photo" /> --%>
					<span class="user-info"> 
					<small>Welcome,</small>
							${SPRING_SECURITY_CONTEXT.authentication.principal.cn[0]}
					</span> 
					<i class="icon-caret-down"></i>
				</a>

					<ul class="user-menu pull-right dropdown-menu dropdown-yellow dropdown-caret dropdown-close">

						<li><a href="${pageContext.request.contextPath}/summary"> <i class="icon-user"></i>Profile
						</a></li>

						<li class="divider"></li>

						<li><a href="${pageContext.request.contextPath}/j_spring_security_logout"> <i class="icon-off"></i>
								Logout
						</a></li>
					</ul></li>
			</ul>
			<!-- /.ace-nav -->
		</div>
		<!-- /.navbar-header -->
	</div>
	<!-- /.container -->
</div>