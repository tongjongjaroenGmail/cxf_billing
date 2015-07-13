<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

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
	
	<c:set var="currentUrl" value="${requestScope['javax.servlet.forward.servlet_path']}"/>

	<ul class="nav nav-list">
		<li <c:if test="${fn:startsWith(currentUrl, '/claim')}">class="active open"</c:if>>
			<a href="#" class="dropdown-toggle">
				<i class="icon-tag"></i>
				<span class="menu-text">สร้างเรื่องเรียกร้อง</span>
				<b class="arrow icon-angle-down"></b>
			</a>
			<ul class="submenu">
				<c:if test="${loginUser.stdPosition.id == 1}">
					<li <c:if test="${requestScope['javax.servlet.forward.servlet_path'] == '/claimImport'}">class="active"</c:if>>
						<a href="${pageContext.request.contextPath}/claimImport"> 
							<i class="icon-double-angle-right"></i> 
							<span class="menu-text">นำเข้าไฟล์</span>
						</a>
					</li>
       			</c:if>
				

				<li <c:if test="${requestScope['javax.servlet.forward.servlet_path'] == '/claim'}">class="active"</c:if>>
					<a href="${pageContext.request.contextPath}/claimSearch"> 
						<i class="icon-double-angle-right"></i> 
						<span class="menu-text">เรื่องเรียกร้อง</span>
					</a>
				</li>
			</ul>
		</li>
		
		<li <c:if test="${fn:startsWith(currentUrl, '/report')}">class="active open"</c:if>>
			<a href="#" class="dropdown-toggle">
				<i class="icon-tag"></i>
				<span class="menu-text">รายงาน</span>
				<b class="arrow icon-angle-down"></b>
			</a>
			<ul class="submenu">
				<li <c:if test="${requestScope['javax.servlet.forward.servlet_path'] == '/report1'}">class="active"</c:if>>
					<a href="${pageContext.request.contextPath}/report1"> 
						<i class="icon-double-angle-right"></i> 
						<span class="menu-text">ออกหนังสือติดตาม</span>
					</a>
				</li>

				<c:if test="${loginUser.stdPosition.id == 1}">
					<li <c:if test="${requestScope['javax.servlet.forward.servlet_path'] == '/report2'}">class="active"</c:if>>
						<a href="${pageContext.request.contextPath}/report2"> 
							<i class="icon-double-angle-right"></i> 
							<span class="menu-text">จ่ายค่าแรง</span>
						</a>
					</li>
					
					<li <c:if test="${requestScope['javax.servlet.forward.servlet_path'] == '/report3'}">class="active"</c:if>>
						<a href="${pageContext.request.contextPath}/report3"> 
							<i class="icon-double-angle-right"></i> 
							<span class="menu-text">วางบิล</span>
						</a>
					</li>
				</c:if>
				
				<li <c:if test="${requestScope['javax.servlet.forward.servlet_path'] == '/report4'}">class="active"</c:if>>
					<a href="${pageContext.request.contextPath}/report4"> 
						<i class="icon-double-angle-right"></i> 
						<span class="menu-text">รายงานการปฏิบัติงาน</span>
					</a>
				</li>
			</ul>
		</li>
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


