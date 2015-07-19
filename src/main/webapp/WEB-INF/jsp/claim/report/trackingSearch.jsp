<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<div class="page-content col-xs-12">
	<div class="page-header">
		<h1>
			ออกหนังสือติดตาม
			<button class="btn btn-success btn-xs" id="btnAdd">
				<I class="icon-plus  bigger-110 icon-only"></I>
			</button>
		</h1>
	</div>
	<!-- /.page-header -->

	<div id="divParamSearch">
		<!-- PAGE CONTENT BEGINS -->
		<div class="row">
			<div class="col-sm-12">
				<div class="table-responsive">
					<div class="col-sm-2">
						<div class="input-group col-sm-12 no-padding-left"
							style="text-align: right;">
							<b>วันที่ออกหนังสือติดตาม : </b>
						</div>
					</div>
					<div class="col-sm-3">
						<div class="input-group col-sm-12 no-padding-left">
							<input class="form-control date-picker" id="txtJobDateStart"
								type="text" data-date-format="dd/mm/yyyy"
								data-date-language="th-th" /> <span class="input-group-addon">
								<i class="icon-calendar bigger-110"></i>
							</span>
						</div>
					</div>
					<div class="col-sm-2">
						<div class="input-group col-sm-12 no-padding-left"
							style="text-align: right;">
							<b>ถึงวันที่ : </b>
						</div>
					</div>

					<div class="col-sm-3">
						<div class="input-group col-sm-12 no-padding-left">
							<input class="form-control date-picker" id="txtJobDateEnd"
								type="text" data-date-format="dd/mm/yyyy"
								data-date-language="th-th" /> <span class="input-group-addon">
								<i class="icon-calendar bigger-110"></i>
							</span>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="space-4"></div>

		<div class="row">
			<div class="col-sm-12">
				<div class="table-responsive">
					<div class="col-sm-2">
						<div class="input-group col-sm-12 no-padding-left"
							style="text-align: right;">
							<b>บริษัทประกัน : </b>
						</div>
					</div>
					<div class="col-sm-3">
						<div class="input-group col-sm-12 no-padding-left">
							<select class="col-sm-12" id="selInsurance">
								<option value="">ทั้งหมด</option>
								<c:forEach var="insurance" items="${insurances}"
									varStatus="index">
									<option value="${insurance.id}">${insurance.name}</option>
								</c:forEach>
							</select>
						</div>
					</div>

				</div>

			</div>
			<div class="space-4"></div>


			<div class="row">
				<div class="col-sm-12">
					<div class="table-responsive">
						<div class="col-sm-2">
							<div class="input-group col-sm-12 no-padding-left"
								style="text-align: right;">
								<b>ประเภทเคลม : </b>
							</div>
						</div>
						<div class="col-sm-3">
							<div class="input-group col-sm-12 no-padding-left">
								<select class="col-sm-12" id="selClaimType">
									<option value="">ทั้งหมด</option>
									<c:forEach var="claimType" items="${claimTypes}"
										varStatus="index">
										<option value="${claimType.id}">${claimType.name}</option>
									</c:forEach>
								</select>
							</div>
						</div>

					</div>
				</div>
			</div>
		</div>

		<div class="space-4"></div>

		<div class="space-4"></div>
	</div>
		<div class="row">
			<div class="col-sm-offset-1 col-sm-10" style="text-align: right;">
				<div class="table-responsive">
					<div class="col-sm-12">
						<button class="btn btn-info" type="button" id="btnSearch"
							onclick="search();">
							<i class="icon-search"></i> ค้นหา
						</button>
						<button class="btn btn-info" type="button" id="btnPrint"
							onclick="print();">
							<i class="icon-print"></i> พิมพ์
						</button>
					</div>					
				</div>
				<!-- /.table-responsive -->
			</div>
			<!-- /span -->
		</div>
		<!-- /row -->

		<div class="table-responsive">
			<br> <br>
			<table id="tblClaim"
				class="table table-striped table-bordered table-hover"
				style="width: 100%;">
				<thead>
					<tr>
						<th>วันที่่ออกหนังสือ</th>
						<th>วันที่เกิดเหตุ</th>
						<th>เลขกรมธรรม์</th>
						<th>เลขเคลม</th>
						<th>เลขทะเบียน</th>
						<th>จำนวนเงิน</th>
						<th>บริษัทประกัน</th>
						<th>ประเภทเคลม</th>

					</tr>
				</thead>

				<tbody>

				</tbody>
			</table>
		</div>


		<%-- 	<jsp:include page = "modalClaimSave.jsp" flush="false"/> --%>
	</div>
	<!-- /.page-content -->

	<script type="text/javascript">
		$('.date-picker').datepicker({
			autoclose : true
		}).next().on(ace.click_event, function() {
			$(this).prev().focus();
		});

		var tblClaimDt;
		var firstTime = true;
		var pageName = "tracking"

		$(document).ready(
						function() {
							tblClaimDt = $("#tblClaim").dataTable(
											{"aoColumns" : [ {"mData" : "jobDate"}, 
											                 {"mData" : "accidentDate"}, 
											                 {"mData" : "policyNo"}, 
															 {"mData" : "claimNumber"}, 
															 {"mData" : "licenseNumber"}, 
															 {"mData" : "claimAmount"}, 
															 {"mData" : "insuranceName"}, 
															 {"mData" : "claimType"} 
															],
												columnDefs : [ {
													type : 'date-dd/mm/yyyy',
													targets : 0
												} ],
												"processing" : true,
												"serverSide" : true,
												"ajax" : {
													"url" : '${pageContext.request.contextPath}/tracking/search',
													"type" : "POST",
													"data" : function(d) {
																d.paramJobDateStart = $("#txtJobDateStart").val(),
																d.paramJobDateEnd = $("#txtJobDateEnd").val(),
																d.paramPartyInsuranceId = $("#selInsurance").val(),
																d.paramClaimTypeId = $("#selClaimType").val(),
																d.paramFirstTime = firstTime,
																d.paramPageName = pageName
															}
												},
												"fnDrawCallback" : function() {
													firstTime = false;
												}
											});

						});
		
	


		function search() {
			delay(function() {
				tblClaimDt.fnDraw();
			}, 1000);
		}
		
		function print(token){
// 			var param = "employeeName=" + $("#txtEmployeeName").val() +
// 			"&leaveDate=" + $("#txtLeaveDate").val() +   
// 			"&leaveTypeId=" + $("#selLeaveType").val()  +  
// 			"&leaveStatus=" + $("#selLeaveStatus").val()  +  
// 			"&type=" + $("input[name='rdoTypeFile']:checked").val() +
// 			"&token=" + token;
			window.location = '${pageContext.request.contextPath}/report/trackingRTP.jsp}'
		}
		
// 		function print(){
// 			$.ajax({
// 						"url" : '${pageContext.request.contextPath}/tracking/print',
// 						"type" : "POST",
// 						"data" : function(d) {
// 									d.paramJobDateStart = $("#txtJobDateStart").val(),
// 									d.paramJobDateEnd = $("#txtJobDateEnd").val(),
// 									d.paramPartyInsuranceId = $("#selInsurance").val(),
// 									d.paramClaimTypeId = $("#selClaimType").val(),
// 									d.paramFirstTime = firstTime,
// 									d.paramPageName = "tracking"
// 						} });
// 					}

		
	</script>

	<div id='msgbox' title='' style='display: none'></div>