<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:url value="/report/download/token" var="downloadTokenUrl"/>
<c:url value="/report/download/progress" var="downloadProgressUrl"/>
<div class="page-content col-xs-12">
	<div class="page-header">
		<h1>จ่ายค่าแรง
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
					<div class="input-group col-sm-12 no-padding-left" style="text-align: right;">
						<b>วันที่ปิดงาน : </b> 
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
						<b>ผู้รับผิดชอบ: </b> 
					</div>
				</div>
				<div class="col-sm-3">		
<!-- 					<div class="input-group col-sm-12 no-padding-left"> -->
					<div class="input-group col-sm-12 no-padding-left" style="text-align: left;">
							<input class="form-control" id="agentName" type="text" style="text-align: left "/> 
					</div>
<!-- 						<select class="col-sm-12" id="selInsurance"> -->
<!-- 							<option value="">ทั้งหมด</option> -->
<%-- 							<c:forEach var="insurance" items="${insurances}" varStatus="index">		 --%>
<%-- 								<option value="${insurance.id}">${insurance.name}</option>					 --%>
<%-- 							</c:forEach> --%>
<!-- 						</select> -->
<!-- 					</div> -->
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
				
				
				
			</div>
		</div>		
	</div>
	
	<div class="space-4"></div>
	
	<div class="row">
		<div class="col-sm-12">
			
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
		<table id="tblClaimBill" class="table table-striped table-bordered table-hover" style="width: 100%;">
			<thead>
				<tr>
					<th>วันที่ปิดงาน</th>
					<th>เลขเคลม</th>
					<th>บริษัทประกัน</th>
					<th>ประเภทเคลม</th>
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

var tblClaimBill;
var firstTime = true;
var pageName = "labor"

$(document).ready(function() {
	tblClaimBill = $("#tblClaimBill").dataTable(
				{"aoColumns" : [{ "mData" : "closeDate" },
								{ "mData" : "claimNumber"  },
								{ "mData" : "insuranceName" },
								{ "mData" : "claimType" }
							   ],
				columnDefs: [{ type: 'date-dd/mm/yyyy', targets: 0 }],
				"processing": true,
                "serverSide": true,
                "ajax": {
                	"url": '${pageContext.request.contextPath}/tracking/labor',
                    "type": "POST",
                    "data": function ( d ) {
                         d.paramJobDateStart       =  $("#divParamSearch").find("#txtJobDateStart").val(),  
                         d.paramJobDateEnd         =  $("#divParamSearch").find("#txtJobDateEnd").val(),  
                         d.paramPartyInsuranceId   =  $("#divParamSearch").find("#agentName").val(),   
                         d.paramClaimTypeId        =  $("#divParamSearch").find("#selClaimType").val(),  
                         d.paramFirstTime          =  firstTime,
                         d.paramPageName		   =  pageName
                    }
                },
                "fnDrawCallback" : function() {
                	firstTime = false;
                }
	});
	
});
	

function search(){
	delay(function(){
		tblClaimBill.fnDraw();
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
	window.location = '${pageContext.request.contextPath}/report/labor?' + param;
}

</script>

<div id='msgbox' title='' style='display:none'></div>