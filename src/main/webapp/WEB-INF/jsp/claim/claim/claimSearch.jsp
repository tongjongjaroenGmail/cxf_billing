<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div class="page-content col-xs-12">
	<div class="page-header">
		<h1>เรื่องเรียกร้อง</h1>
	</div>
	<!-- /.page-header -->


	<!-- PAGE CONTENT BEGINS -->

	<div class="row">
		<div class="col-sm-offset-1 col-sm-10">
			<div class="table-responsive">
				<div class="col-sm-3">		
					<div class="input-group col-sm-12 no-padding-left">
						<b>วันที่รับงาน : </b> 
						<input class="form-control date-picker" id="txtJobDateStart" type="text" data-date-format="dd/mm/yyyy" /> 
						<span class="input-group-addon"> 
							<i class="icon-calendar bigger-110"></i>
						</span>
					</div>
				</div>
				<div class="col-sm-3">	
					<div class="input-group col-sm-12 no-padding-left">
						<b>ถึงวันที่ : </b> 
						<input class="form-control date-picker" id="txtJobDateEnd" type="text" data-date-format="dd/mm/yyyy" /> 
						<span class="input-group-addon"> 
							<i class="icon-calendar bigger-110"></i>
						</span>
					</div>
				</div>
			</div>
		</div>		
	</div>
	
	<div class="row">
		<div class="col-sm-offset-1 col-sm-10">
			<div class="table-responsive">
				<div class="col-sm-3">		
					<div class="input-group col-sm-12 no-padding-left">
						<b>บริษัทประกัน : </b> 
						<select class="col-sm-12" id="selInsurance">
							<option value="">ทั้งหมด</option>
							<c:forEach var="insurance" items="${insurances}" varStatus="index">		
								<option value="${insurance.id}">${insurance.name}</option>					
							</c:forEach>
					</select>
					</div>
				</div>
				<div class="col-sm-3">	
					<div class="input-group col-sm-12 no-padding-left">
						<b>จำนวนวันที่จะหมดอายุความ : </b> 
						<input class="form-control input-mask-number" id="txtTotalDay" type="text" maxlength="3" /> 
						<b> วัน</b> 
					</div>
				</div>
			</div>
		</div>		
	</div>
	
	<div class="row">
		<div class="col-sm-offset-1 col-sm-10">
			<div class="table-responsive">
				<div class="col-sm-3">		
					<div class="input-group col-sm-12 no-padding-left">
						<b>ประเภทเคลม : </b> 
						<select class="col-sm-12" id="selInsurance">
							<option value="">ทั้งหมด</option>
							<c:forEach var="claimType" items="${claimTypes}" varStatus="index">		
								<option value="${claimType.id}">${claimType.name}</option>					
							</c:forEach>
					</select>
					</div>
				</div>
			</div>
		</div>		
	</div>
	
	<div class="row">
		<div class="col-sm-offset-1 col-sm-10">
			<div class="table-responsive">
				<div class="col-sm-3">		
					<div class="input-group col-sm-12 no-padding-left">
						<b>เลขเคลม : </b> 
						<input class="form-control" id="txtClaimNumber" type="text" maxlength="20" /> 
					</div>
				</div>
			</div>
		</div>		
	</div>
	
	<div class="row">
		<div class="col-sm-offset-1 col-sm-10">
			<div class="table-responsive">
				<div class="col-sm-3">		
					<div class="input-group col-sm-12 no-padding-left">
						<b>สถานะ : </b> 
						<select class="col-sm-12" id="selInsurance">
							<option value="">ทั้งหมด</option>
							<c:forEach var="jobStatus" items="${jobStatuses}" varStatus="index">		
								<option value="${jobStatus.id}">${jobStatus.name}</option>					
							</c:forEach>
					</select>
					</div>
				</div>
			</div>
		</div>		
	</div>
	
	<div class="row">
		<div class="col-sm-offset-1 col-sm-10">
			<div class="table-responsive">
				<div class="col-sm-2"  style="padding-top: 13px">
					<button class="btn btn-info" type="button" id="btnSearch" onclick="search();">
						<i class="icon-search"></i> ค้นหา
					</button>
				</div>
			</div>
			<!-- /.table-responsive -->
		</div>
		<!-- /span -->
	</div>
	<!-- /row -->
</div>
<!-- /.page-content -->

<script type="text/javascript">    
$('.date-picker').datepicker({autoclose:true}).next().on(ace.click_event, function(){
	$(this).prev().focus();
});

$('.input-mask-number').mask('9,999');
</script>

<div id='msgbox' title='' style='display:none'></div>