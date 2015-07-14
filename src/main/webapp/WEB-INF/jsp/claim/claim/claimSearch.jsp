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
		<div class="col-sm-10">
			<div class="table-responsive">
				<div class="col-sm-2">		
					<div class="input-group col-sm-12 no-padding-left" style="text-align: right;">
						<b>วันที่รับงาน : </b> 
					</div>
				</div>
				<div class="col-sm-3">		
					<div class="input-group col-sm-12 no-padding-left">
						<input class="form-control date-picker" id="txtJobDateStart" type="text" data-date-format="dd/mm/yyyy" data-date-language="th-th"/> 
						<span class="input-group-addon"> 
							<i class="icon-calendar bigger-110"></i>
						</span>
					</div>
				</div>
				<div class="col-sm-2">		
					<div class="input-group col-sm-12 no-padding-left" style="text-align: right;">
						<b>ถึงวันที่ : </b> 
					</div>
				</div>
				
				<div class="col-sm-3">	
					<div class="input-group col-sm-12 no-padding-left">
						<input class="form-control date-picker" id="txtJobDateEnd" type="text" data-date-format="dd/mm/yyyy" data-date-language="th-th"/> 
						<span class="input-group-addon"> 
							<i class="icon-calendar bigger-110"></i>
						</span>
					</div>
				</div>
			</div>
		</div>		
	</div>
	
	<div class="space-4"></div>

	<div class="row">
		<div class="col-sm-10">
			<div class="table-responsive">
				<div class="col-sm-2">		
					<div class="input-group col-sm-12 no-padding-left" style="text-align: right;">
						<b>บริษัทประกัน : </b> 
					</div>
				</div>
				<div class="col-sm-3">		
					<div class="input-group col-sm-12 no-padding-left">
						<select class="col-sm-12" id="selInsurance">
							<option value="">ทั้งหมด</option>
							<c:forEach var="insurance" items="${insurances}" varStatus="index">		
								<option value="${insurance.id}">${insurance.name}</option>					
							</c:forEach>
						</select>
					</div>
				</div>
				<div class="col-sm-3">		
					<div class="input-group col-sm-12 no-padding-left" style="text-align: right;">
						<b>จำนวนวันที่จะหมดอายุความ : </b> 
					</div>
				</div>
				<div class="col-sm-2">	
					<div class="input-group col-sm-12 no-padding-left">
						
						<input class="form-control input-mask-number" id="txtTotalDayOfMaturity" type="text" maxlength="3" style="text-align: right"/> 
						
					</div>
				</div>
				
				<div class="col-sm-1">		
					<div class="input-group col-sm-12 no-padding-left" style="text-align: left;">
						<b> วัน</b> 
					</div>
				</div>
			</div>
		</div>		
	</div>
	
	<div class="space-4"></div>
	
	<div class="row">
		<div class="col-sm-10">
			<div class="table-responsive">
				<div class="col-sm-2">		
					<div class="input-group col-sm-12 no-padding-left" style="text-align: right;">
						<b>ประเภทเคลม : </b> 
					</div>
				</div>
				<div class="col-sm-3">		
					<div class="input-group col-sm-12 no-padding-left">
						<select class="col-sm-12" id="selClaimType">
							<option value="">ทั้งหมด</option>
							<c:forEach var="claimType" items="${claimTypes}" varStatus="index">		
								<option value="${claimType.id}">${claimType.name}</option>					
							</c:forEach>
						</select>
					</div>
				</div>
				<div class="col-sm-3">		
					<div class="input-group col-sm-12 no-padding-left" style="text-align: right;">
						<b>เลขเคลม : </b> 
					</div>
				</div>
				<div class="col-sm-2">	
					<div class="input-group col-sm-12 no-padding-left">
						
						<input class="form-control" id="txtClaimNumber" type="text" maxlength="20" /> 
						
					</div>
				</div>
				
			</div>
		</div>		
	</div>
	
	<div class="space-4"></div>
	
	<div class="row">
		<div class="col-sm-10">
			<div class="table-responsive">
				<div class="col-sm-2">		
					<div class="input-group col-sm-12 no-padding-left" style="text-align: right;">
						<b>สถานะ : </b> 				
					</div>
				</div>
				
				<div class="col-sm-3">		
					<div class="input-group col-sm-12 no-padding-left">
						<select class="col-sm-12" id="selJobStatus">
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
	
	<div class="space-4"></div>
	
	<div class="row">
		<div class="col-sm-offset-1 col-sm-10" style="text-align: right;">
			<div class="table-responsive">
				<div class="col-sm-12">
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
	
	<div class="table-responsive">
				<br> <br>
				<table id="tblClaim" class="table table-striped table-bordered table-hover" style="width: 100%;">
					<thead>
						<tr>
							<th>วันที่รับงาน</th>
							<th>เลขเคลม</th>
							<th>เลขเรียกร้อง</th>
							<th>บริษัทประกัน</th>
							<th>ประเภทเคลม</th>
							<th>จำนวนเงินเรียกร้อง</th>
							<th>สถานะ</th>
							<th>ผู้รับผิดชอบ</th>
							<th>อายุความ</th>
							<th>แก้ไข</th>
							<th>แสดง</th>
						</tr>
					</thead>

					<tbody>

					</tbody>
				</table>
			</div>
	
</div>
<!-- /.page-content -->

<script type="text/javascript">    
$('.date-picker').datepicker({autoclose:true}).next().on(ace.click_event, function(){
	$(this).prev().focus();
});

$('.input-mask-number').mask('9,999');

var tblClaim;
var firstTime = true;

$(document).ready(function() {
	tblClaim = $("#tblClaim").dataTable({
			"aoColumns"   : [
				{ "mData" : "jobDate" },
				{ "mData" : "claimNumber"  },
				{ "mData" : "jobNo"  },
				{ "mData" : "insuranceName"    },
				{ "mData" : "claimType"    },
				{ "mData" : "requestAmount"    },
				{ "mData" : "jobStatus"    },
				{ "mData" : "agentName"    },
				{ "mData" : "maturityDate"    },
				{ "mData" : "",
					"bSortable": false,
					"mRender" : function (data, type, full) {
						return '<button id="btnEdit" class="btn-info" type="button">Edit</button>';
					}	
				},
				{ "mData" : "",
					"bSortable": false,
					"mRender" : function (data, type, full) {
						return '<button id="btnDetail" class="btn-info" type="button">Detail</button>';
					}	
				}],
				columnDefs: [ { type: 'date-dd/mm/yyyy', targets: 3 }],
				"processing": true,
                "serverSide": true,
                "ajax": {
                    "url": '${pageContext.request.contextPath}/claim/search',
                    "type": "POST",
                    "data": function ( d ) {
                         d.jobDateStart       =  $("#txtJobDateStart").val(),  
                         d.jobDateEnd         =  $("#txtJobDateEnd").val(),  
                         d.insuranceId        =  $("#selInsurance").val(),  
                         d.totalDayOfMaturity =  $("#txtTotalDayOfMaturity").val(),  
                         d.claimTypeId        =  $("#selClaimType").val(),  
                         d.claimNumber        =  $("#txtClaimNumber").val(),  
                         d.jobStatusId        =  $("#selJobStatus").val(),
                         d.firstTime          =  firstTime
                    }
                },
				"createdRow": function ( row, data, index ) {
					firstTime = false;
		        }
	});
});

function search(){
	delay(function(){
		tblClaim.fnDraw();
	}, 1000 );
}
</script>

<div id='msgbox' title='' style='display:none'></div>