<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<a class="menu-toggler" id="menu-toggler" href="#"> <span class="menu-text"></span></a>
			
<div class="sidebar" id="sidebar">
	<script type="text/javascript">
		try {
			ace.settings.check('sidebar', 'fixed')
		} catch (e) {
		}
	</script>

	<div class="sidebar-shortcuts" id="sidebar-shortcuts">
	<%-- 
		<div class="sidebar-shortcuts-large" id="sidebar-shortcuts-large">
			<button class="btn btn-success">
				<i class="icon-signal"></i>
			</button>

			<button class="btn btn-info">
				<i class="icon-pencil"></i>
			</button>

			<button class="btn btn-warning">
				<i class="icon-group"></i>
			</button>

			<button class="btn btn-danger">
				<i class="icon-cogs"></i>
			</button>
		</div>

		<div class="sidebar-shortcuts-mini" id="sidebar-shortcuts-mini">
			<span class="btn btn-success"></span> <span class="btn btn-info"></span> <span class="btn btn-warning"></span> <span
				class="btn btn-danger"></span>
		</div>
		--%>
	</div>

	<!-- #sidebar-shortcuts -->
	<ul class="nav nav-list">
		<li <c:if test="${requestScope['javax.servlet.forward.servlet_path'] == '/summary'}">class="active"</c:if>><a
			href="${pageContext.request.contextPath}/summary"> <i class="icon-home"></i> <span class="menu-text">
					Summary </span>
		</a></li>
		<li <c:if test="${requestScope['javax.servlet.forward.servlet_path'] == '/view-leave'}">class="active"</c:if>><a
			href="${pageContext.request.contextPath}/view-leave"> <i class="icon-list-alt"></i> <span class="menu-text">
					View Leaves </span>
		</a></li>
		<li <c:if test="${requestScope['javax.servlet.forward.servlet_path'] == '/history-log'}">class="active"</c:if>><a
			href="${pageContext.request.contextPath}/history-log"> <i class="icon-list"></i> <span class="menu-text">
					History Log </span>
		</a></li>
		<li <c:if test="${requestScope['javax.servlet.forward.servlet_path'] == '/holiday'}">class="active"</c:if>><a
			href="${pageContext.request.contextPath}/holiday"> <i class="icon-calendar"></i> <span class="menu-text">
					Holiday </span>
		</a></li>
		
		<c:if test="${loginEmployee.role.id == 2 or loginEmployee.role.id == 3}">
			<li <c:if test="${requestScope['javax.servlet.forward.servlet_path'] == '/adjust-leave'}">class="active"</c:if>><a
				href="${pageContext.request.contextPath}/adjust-leave"> <i class="icon-edit"></i> <span class="menu-text">
						Adjust Leave </span>
			</a></li>
			<li <c:if test="${requestScope['javax.servlet.forward.servlet_path'] == '/adjust-employee'}">class="active"</c:if>><a
				href="${pageContext.request.contextPath}/adjust-employee"> <i class="icon-edit"></i> <span class="menu-text">
						Adjust Employee </span>
			</a></li>
	        <li class="${requestScope['javax.servlet.forward.servlet_path'] eq '/import-employee'?'active':''}"><a
	            href="${pageContext.request.contextPath}/import-employee"> <i class="icon-cogs"></i> <span class="menu-text">
	                    Import Employee </span>
	        </a></li>
        </c:if>
	</ul>

	<div class="sidebar-collapse" id="sidebar-collapse">
		<i class="icon-double-angle-left" data-icon1="icon-double-angle-left" data-icon2="icon-double-angle-right"></i>
	</div>

	<script type="text/javascript">
		try {
			ace.settings.check('sidebar', 'collapsed')
		} catch (e) {
		}
	</script>
</div>


