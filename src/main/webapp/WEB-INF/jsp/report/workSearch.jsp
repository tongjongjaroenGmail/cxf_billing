<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:url value="/report/download/token" var="downloadTokenUrl"/>
<c:url value="/report/download/progress" var="downloadProgressUrl"/>

<div class="page-content col-xs-12">
	<div class="page-header">
		<h1>รายงานการปฏิบัติงาน
		</h1>
	</div>
	<!-- /.page-header -->

<div id="divParamSearch">
	<!-- PAGE CONTENT BEGINS -->
	<div class="row">
		<div class="col-sm-12">
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
		<div class="col-sm-12">
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
						
						<input class="form-control" id="txtTotalDayOfMaturity" type="text" maxlength="3" style="text-align: right" onkeypress="return inputNum(event);"/> 
						
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
		<div class="col-sm-12">
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
		<div class="col-sm-12">
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
</div>	
	<div class="space-4"></div>
	
	<div class="row">
		<div class="col-sm-offset-1 col-sm-10" style="text-align: right;">
			<div class="table-responsive">
				<div class="col-sm-12">
					<button class="btn btn-info" type="button" id="btnSearch" onclick="search();">
						<i class="icon-search"></i> ค้นหา
					</button>
					
					<button class="btn btn-success" type="button" id="btnExport" onclick="download();">
						<i class="icon-file"></i> พิมพ์
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
				</tr>
			</thead>

			<tbody>

			</tbody>
		</table>
	</div>
	
</div>
<!-- /.page-content -->

<div class="modal fade" id="modalDownload" tabindex="-1" role="dialog" aria-labelledby="modalDownload"
	aria-hidden="true" style="overflow-x: hidden; overflow-y: hidden;">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				
			</div>
			<div class="modal-body">
				Processing download...
			</div>
			<div class="modal-footer">
				
			</div>
		</div>
	</div>
</div>

<script type="text/javascript">    
$('.date-picker').datepicker({autoclose:true}).next().on(ace.click_event, function(){
	$(this).prev().focus();
});

var tblClaimDt;
var firstTime = true;

$(document).ready(function() {
	tblClaimDt = $("#tblClaim").dataTable({
			"lengthMenu": [[10,15,20, 25, 50, 100,200,300,400,500,600,700,800,900,1000], [10,15,20, 25, 50, 100,200,300,400,500,600,700,800,900,1000]],
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
				],
				columnDefs: [{ type: 'date-dd/mm/yyyy', targets: [0,8] }],
				"processing": true,
                "serverSide": true,
                "bSort" : false,
                "bFilter": false,
                "ajax": {
                    "url": '${pageContext.request.contextPath}/claim/search',
                    "type": "POST",
                    "data": function ( d ) {
                         d.paramJobDateStart       =  $("#divParamSearch").find("#txtJobDateStart").val(),  
                         d.paramJobDateEnd         =  $("#divParamSearch").find("#txtJobDateEnd").val(),  
                         d.paramPartyInsuranceId   =  $("#divParamSearch").find("#selInsurance").val(),  
                         d.paramTotalDayOfMaturity =  $("#divParamSearch").find("#txtTotalDayOfMaturity").val(),  
                         d.paramClaimTypeId        =  $("#divParamSearch").find("#selClaimType").val(),  
                         d.paramClaimNumber        =  $("#divParamSearch").find("#txtClaimNumber").val(),  
                         d.paramJobStatusId        =  $("#divParamSearch").find("#selJobStatus").val(),
                         d.paramFirstTime          =  firstTime
                    }
                },
                "fnDrawCallback" : function() {
                	firstTime = false;
                }
	});
});

function search(){
	delay(function(){
		tblClaimDt.fnDraw();
	}, 1000 );
}

function download() {
	// Retrieve download token
	// When token is received, proceed with download
	$.get('${downloadTokenUrl}', function(response) {
		// Store token
		var token = response.message[0];
		
		// Show progress dialog
		$('#modalDownload').modal('show');
		
		// Start download
		exportFile(token);

		// Check periodically if download has started
		var frequency = 1000;
		var timer = setInterval(function() {
			$.get('${downloadProgressUrl}', {token: token}, 
					function(response) {
						// If token is not returned, download has started
						// Close progress dialog if started
						if (response.message[0] != token) {
							$('#modalDownload').modal('hide');
							clearInterval(timer);
						}
				});
		}, frequency);
		
	});
}

function exportFile(token){
	var param = "token=" + token;
	$("#divParamSearch").find('input,textarea,select').each(function() {
		param += "&" + $(this).attr('id') + "=" + $(this).val();
    });  
     
	window.location = '${pageContext.request.contextPath}/report/work?' + param;
}
</script>

<div id='msgbox' title='' style='display:none'></div>