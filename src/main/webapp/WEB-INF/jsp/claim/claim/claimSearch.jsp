<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div class="page-content col-xs-12">
	<div class="page-header">
		<h1>เรื่องเรียกร้อง
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
				</tr>
			</thead>

			<tbody>

			</tbody>
		</table>
	</div>
	
	<jsp:include page = "modalClaimSave.jsp" flush="false"/>
</div>
<!-- /.page-content -->

<script type="text/javascript">    
$('.date-picker').datepicker({autoclose:true}).next().on(ace.click_event, function(){
	$(this).prev().focus();
});

var tblClaimDt;
var firstTime = true;
var isSearch = false;
var selJobStatusOptions = {
		<c:forEach var="jobStatus" items="${jobStatuses}" varStatus="index">		
			${jobStatus.id} : '${jobStatus.name}',		
		</c:forEach>
	};


$(document).ready(function() {
	tblClaimDt = $("#tblClaim").dataTable({
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
				{ "mData" : "claimId",
					"bSortable": false,
					"mRender" : function (data, type, full) {
						return '<button id="btnEdit" class="btn-info" type="button" onclick="find(' + data + ')">Edit</button>';
					}	
				}],
				columnDefs: [{ type: 'date-dd/mm/yyyy', targets:  [0,8]}],
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
	
	$( "#btnAdd" ).click(function() {
		setPageForAdd();
		
		$('#modalSave').modal(
			{
				backdrop:'static'
			}
		);
	});
});

function search(){
	delay(function(){
		tblClaimDt.fnDraw();
	}, 1000 );
	
	isSearch = true;
}

function setPageForAdd(){
	$('#modalSaveHeaderLabelFunction').html("เพิ่ม");
	$("#modalSave").find('input,textarea,select').each(function() {
        $(this).val("");
    });  
	changeSelJobStatus(0);
	setOptionSelJobStatus(-1);
	$("#modalSave").find("#selJobStatus").prop('disabled', true);
	$("#modalSave").find("#txtJobDate").val(moment().format('DD/MM/') +( parseInt( moment().format('YYYY')) + 543));
	$("#modalSave").find("#divReceiveMoneyType").hide();
	$("#btnSave").show();
	changePartyInsurance(0,"");
}

function setPageForEdit(selAgent){
	var selJobStatus = $("#modalSave").find("#selJobStatus").val();		
	var selPartyInsurance = $("#modalSave").find("#selPartyInsurance").val();		
	changeSelJobStatus(0);
	setOptionSelJobStatus(-1);
	
	$('#modalSaveHeaderLabelFunction').html("แก้ไข");			
	$("#modalSave").find("#selJobStatus").prop('disabled', false);			
	
	setOptionSelJobStatus(selJobStatus);
	changeSelJobStatus(selJobStatus)
	
	if(selJobStatus == 2 || selJobStatus == 3){
		$("#btnSave").hide();
	}else{
		$("#btnSave").show();
	}
	if(selJobStatus == 2){
		$("#modalSave").find("#divReceiveMoneyType").show();
	}else{
		$("#modalSave").find("#divReceiveMoneyType").hide();
	}
	
	if($("#modalSave").find("#txtAccidentDate").val() != ""){
		var txtAccidentDate = $("#modalSave").find("#txtAccidentDate").val(),
	    dateParts = txtAccidentDate.match(/(\d+)/g)
	    accidentDate = new Date((parseInt(dateParts[2],10) - 543) , dateParts[1] - 1, dateParts[0]);  
	                                    // months are 0-based!	                                    
		$("#modalSave").find("#txtAccidentDate").datepicker("setDate", accidentDate);   
		$("#modalSave").find("#txtAccidentDate").datepicker('update');
	}else{
		$("#modalSave").find("#txtAccidentDate").datepicker("setDate", new Date());   
		$("#modalSave").find("#txtAccidentDate").datepicker('update');
		$("#modalSave").find("#txtAccidentDate").val("");
	}
	
	if($("#modalSave").find("#txtMaturityDate").val() != ""){
		var txtMaturityDate = $("#modalSave").find("#txtMaturityDate").val(),
	    dateParts = txtMaturityDate.match(/(\d+)/g)
	    maturityDate = new Date((parseInt(dateParts[2],10) - 543) , dateParts[1] - 1, dateParts[0]);  
	                                    // months are 0-based!   
		$("#modalSave").find("#txtMaturityDate").datepicker("setDate", maturityDate);       
		$("#modalSave").find("#txtMaturityDate").datepicker('update');
	}else{
		$("#modalSave").find("#txtMaturityDate").datepicker("setDate", new Date());   
		$("#modalSave").find("#txtMaturityDate").datepicker('update');
		$("#modalSave").find("#txtMaturityDate").val("");
	}
	

	changePartyInsurance(selPartyInsurance, selAgent );	
}

function find(id){
	$.ajax({
		url : '${pageContext.request.contextPath}/claim/find/' + id,
		dataType: 'json',
		type : "GET",
		contentType: 'application/json',
	    mimeType: 'application/json',
		success : function(data) {
			if(data.message !=  ""){			
				alert(data.message);
			}
			
			if(data.error == false){
				setPageForAdd();
				
				$.each( data.result, function( key, value ) {
					  $("#modalSave").find("#" + key).val(value);
				});
				setPageForEdit(data.result.selAgent);

				$('#modalSave').modal({backdrop:'static'});
			}
		}
	});
}

function save(){
	if(!validate("modalSave")){
		return;
	}
	
	var oJson = new Object();
	
	$("#modalSave").find('input,textarea,select').each(function() {
        oJson[$(this).attr('id')] = $(this).val();
    });  

	$.ajax({
		url : '${pageContext.request.contextPath}/claim/save',
		dataType: 'json',
		type : "POST",
		data : JSON.stringify(oJson),
		contentType: 'application/json',
	    mimeType: 'application/json',
		success : function(data) {
			if(data.message !=  ""){			
				alert(data.message);
			}
			
			if(data.error == false){
				find(data.result.id);
				
				if(isSearch){
					delay(function(){
						tblClaimDt.fnDraw();
					}, 1000 );
				}
			}
		}
	});
}

function setOptionSelJobStatus(jobStatusVal){
	$("#modalSave").find("#selJobStatus").html("");
	$.each(selJobStatusOptions, function(val, text) {
		if(		 jobStatusVal == -1 ||
				(jobStatusVal == 0 && (val == 0 || val == 1 || val == 3)) || 
				(jobStatusVal == 1 && (val == 1 || val == 2 || val == 3)) ||
				(jobStatusVal == 2 && (val == 2)) ||
				(jobStatusVal == 3 && (val == 3))){
			$("#modalSave").find("#selJobStatus").append(
				     $('<option></option>').val(val).html(text)
				);
		}
	});
}

function changeSelJobStatus(jobStatusVal){
	$("#modalSave").find("#txtReceiveRemark").attr('readonly','readonly');
	$("#modalSave").find("#txtFollowRemark").attr('readonly','readonly');
	$("#modalSave").find("#txtCloseRemark").attr('readonly','readonly');
	$("#modalSave").find("#txtCancelRemark").attr('readonly','readonly');
	
	$("#modalSave").find("#divReceiveMoneyType").hide();
	$("#modalSave").find("#selReceiveMoneyType").removeClass("require");
	$("#modalSave").find("#selClaimType").removeClass("require");
	if(jobStatusVal == 0){
		$("#modalSave").find("#txtReceiveRemark").removeAttr('readonly');
		$('.nav-tabs li:eq(0) a').tab('show'); 
	}else if(jobStatusVal == 1){
		$("#modalSave").find("#txtFollowRemark").removeAttr('readonly');
		$('.nav-tabs li:eq(1) a').tab('show'); 
	}else if(jobStatusVal == 2){
		$("#modalSave").find("#txtCloseRemark").removeAttr('readonly');
		$('.nav-tabs li:eq(2) a').tab('show'); 
		$("#modalSave").find("#divReceiveMoneyType").show();
		$("#modalSave").find("#selReceiveMoneyType").addClass("require");
		$("#modalSave").find("#selClaimType").addClass("require");
	}else if(jobStatusVal == 3){
		$("#modalSave").find("#txtCancelRemark").removeAttr('readonly');
		$('.nav-tabs li:eq(3) a').tab('show'); 
		
	}
}

function changePartyInsurance(id , defaultValue){
	$("#modalSave").find("#selAgent").val("");
	$("#modalSave").find("#selAgent").html("");
	$("#modalSave").find("#selAgent").append($('<option></option>').val("").html(""));
	
	if(id != null && id != "" && id != 0){
		$.ajax({
			url : '${pageContext.request.contextPath}/claim/searchUserFromInsurance/' + id,
			dataType: 'json',
			type : "GET",
			contentType: 'application/json',
		    mimeType: 'application/json',
			success : function(data) {
				if(data){		
					
					$.each(data, function(index, value) {
						var name = value.name;
						if(value.lastName){
							name +=  " " + value.lastName;
						}
						$("#modalSave").find("#selAgent").append($('<option></option>').val(value.id).html(name));
					});
					
					if(defaultValue != null && defaultValue != "" && defaultValue != 0){
						$("#modalSave").find("#selAgent").val(defaultValue);
					}else{
						$("#modalSave").find("#selAgent").val($("#modalSave #selAgent option:eq(1)").val());
					}
				}
			}
		});
	}
}
</script>

<div id='msgbox' title='' style='display:none'></div>