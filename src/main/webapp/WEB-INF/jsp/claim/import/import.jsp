<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div class="page-content col-xs-12">
	<div class="page-header">
		<h1>
			โหลดข้อมูล
		</h1>
	</div>
	<!-- /.page-header -->

<form id="form1" method="post" action="/upload" enctype="multipart/form-data">
 
		<!-- PAGE CONTENT BEGINS -->
		<div class="row">
			<div class="col-sm-12">
				<div class="table-responsive">
					<div class="col-sm-3">		
						<div class="input-group col-sm-12 no-padding-left" style="text-align: right;">
							<b>นำเข้าข้อมูลเคลมเรียกร้อง : </b> 
						</div>
					</div>
					<div class="col-sm-3">		
						<div class="input-group col-sm-12 no-padding-left">
							<input type="file" id="file" />
						</div>
					</div>
					
					<div class="col-sm-3">	
						<div class="input-group col-sm-12 no-padding-left">
							<button class="btn btn-success" type="button" id="btnImport" onclick="uploadFormData()" >
								<i class="icon-upload"></i> นำเข้า
							</button>
						</div>
					</div>
				</div>
			</div>		
		</div>
		
		<div class="col-sm-12" id="divMainErrorClaimNumbers" style="display: none;">
			<div class="widget-box ">
				<div class="widget-header">
					<h4 class="lighter smaller">
						<i class="icon-warning-sign"></i>
						หมายเลขเคลมที่พบปัญหา
					</h4>
				</div>
	
				<div class="widget-body">
					<div class="widget-main no-padding">
						<div class="dialogs">
							
							<div class="table-responsive">
							
								<table id="tblClaimNumber" class="table table-striped table-bordered table-hover" style="width: 100%;">
									<thead>
										<tr>
											<th>หมายเลขเคลม</th>
											<th>เหตุผล</th>
										</tr>
									</thead>
						
									<tbody>
						
									</tbody>
								</table>
							</div>
							
						</div>	
					</div><!-- /widget-main -->
				</div><!-- /widget-body -->
			</div><!-- /widget-box -->
		</div>
</form>
</div>
<!-- /.page-content -->

<script type="text/javascript">
var fileAce;
jQuery(function($) {
	fileAce = $('#file').ace_file_input({
		no_file:'No File ...',
		btn_choose:'Choose',
		btn_change:'Change',
		droppable:false,
		onchange:null,
		thumbnail:false //| true | large
		//whitelist:'gif|png|jpg|jpeg'
		//blacklist:'exe|php'
		//onchange:''
		//
	});
});

var tblClaimNumberDT;
$(document).ready(function() {
	tblClaimNumberDt = $("#tblClaimNumber").dataTable();
});


//using FormData() object
function uploadFormData(){
	tblClaimNumberDt.fnClearTable();
	$('#divMainErrorClaimNumbers').hide();
	
	
 if(jQuery.type( file.files[0] ) === "undefined"){
	 alert("กรุณาเลือกไฟล์");
	 return;
 }
 
 var ext = $('#file').val().split('.').pop().toLowerCase();
 if($.inArray(ext, ['xlsx']) == -1) {
     alert('ไฟล์นามสกุลต้องเป็น xlsx เท่านั้น');
     return;
 }
	
  var oMyForm = new FormData();
  oMyForm.append("file", file.files[0]);
 
  $.ajax({
	url : '${pageContext.request.contextPath}/claim/import/upload',
    data: oMyForm,
    dataType: 'json',
    processData: false,
    contentType: false,
    type: 'POST',
    success: function(data){
    	$('#file').ace_file_input('reset_input');
    	
    	if(data.message !=  ""){			
			alert(data.message);
		}
		
		if(data.result){		
			$.each( data.result, function( key, value ) {
				tblClaimNumberDt.fnAddData( [
				   			            value.claimNumber,
				   			         	value.reason
				   			        ] );
			});
    		$('#divMainErrorClaimNumbers').show();
		}
    }
  });
}
</script>

<div id='msgbox' title='' style='display:none'></div>