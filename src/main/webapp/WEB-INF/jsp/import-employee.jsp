<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<div class="page-content col-sm-12">
	<div class="page-header">
		<h1>Import Employee</h1>
	</div><!-- /.page-header -->
    <div class="row">
    	<div class="col-xs-12">
    		<!-- PAGE CONTENT BEGINS -->
            <c:if test="${not empty result}">
            <div class="row">
                <div class="col-xs-6">
                    <div class="alert alert-success">
                        <strong><i class="icon-ok"></i> ${fn:length(result.addedEmployees)}</strong> Employee has been added.
                        <br>
                    </div>
                    <div class="alert alert-warning">
                        <strong><i class="icon-ok"></i> ${fn:length(result.newUpdatedEmployees)}</strong> Employee has been changed.
                        <br>
                    </div>
                    <div class="alert alert-info">
                        <strong><i class="icon-ok"></i> ${fn:length(result.unchangedEmployees)}</strong> Employees has no changes.
                        <br>
                    </div>
                </div>
            </div>
            </c:if>
            <div class="row">
            	<div class="col-xs-12">
                    <form action="${pageContext.request.contextPath}/import-employee" method="post">
                        <button class="btn btn-primary">Import from LDAP</button>
                    </form>
            	</div>
            </div>
            <!-- PAGE CONTENT ENDS -->
        </div><!-- /.col -->
    </div>
</div>
