<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<c:url value="/report/download/token" var="downloadTokenUrl"/>
<c:url value="/report/download/progress" var="downloadProgressUrl"/>

<div class="page-content col-xs-12">
	<div class="page-header">
		<h1>History Log</h1>
	</div>
	<!-- /.page-header -->

	<div class="row ">
		<div class="col-xs-12">
			<!-- PAGE CONTENT BEGINS -->

			<div class="row">
				<div class="col-sm-offset-1 col-sm-10">
					<div class="table-responsive">

						<div class="col-sm-3">
							<b>Employee Name : </b> 
							<div id="nav-search">
								<span class="input-icon" style="width: 100%"> <input type="text" placeholder="Search Employee ..." id="txtEmployeeName"
									autocomplete="off"  class="col-xs-12"/> <i class="icon-search nav-search-icon"></i>
								</span>
							</div>
						</div>

						<div class="col-sm-2">
							<b>Leave Type : </b> 
							<select class="col-sm-12" id="selLeaveType">
								<option value="" alt="All">All</option>
								<c:forEach var="leaveType" items="${leaveTypes}" varStatus="index">
									<c:if test="${empty leaveType.parent}">
										<option value="${leaveType.id}">${leaveType.name}</option>
									</c:if>
								</c:forEach>
							</select>
						</div>
						
						<div class="col-sm-2">
							<b>Leave Status : </b> 
							<select class="col-sm-12" id="selLeaveStatus">
								<option value="" alt="All">All</option>
								<option value="${leaveRequestStatusNEW.id}">${leaveRequestStatusNEW.name}</option>
								<option value="${leaveRequestStatusVIEWED.id}">${leaveRequestStatusVIEWED.name}</option>
								<option value="${leaveRequestStatusAPPROVED.id}">${leaveRequestStatusAPPROVED.name}</option>
							</select>
						</div>

						<div class="col-sm-2">
							<b>Leave Date : </b> 
							<div class="input-group col-sm-12 no-padding-left">
								<input class="form-control date-picker" id="txtLeaveDate" type="text" data-date-format="dd/mm/yyyy" /> <span
									class="input-group-addon"> <i class="icon-calendar bigger-110"></i>
								</span>
							</div>
						</div>

						<div class="col-sm-2"  style="padding-top: 13px">
							<button class="btn btn-info" type="button" id="btnSearch" onclick="search();">
								<i class="icon-search"></i> Search
							</button>
							&nbsp;
							<button class="btn btn-success" type="button" id="btnExport">
								<i class="icon-file"></i> Export
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
				<table id="tblHistoryLog" class="table table-striped table-bordered table-hover" style="width: 100%;">
					<thead>
						<tr>
							<th>Employee Name</th>
							<th>Leave Type</th>
							<th>Leave Status</th>
							<th>Request Date</th>
							<th>Request Note</th>
							<th>Details</th>
						</tr>
					</thead>

					<tbody>

					</tbody>
				</table>
			</div>

		</div>
		<!-- /.col -->
	</div>
	<!-- /.row -->
</div>
<!-- /.page-content -->

<div class="modal fade" id="modalHistoryLogDetail" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
	aria-hidden="true" style="overflow-x: hidden; overflow-y: hidden;">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">
					<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
				</button>
				<h4 class="modal-title" id="myModalLabel">Leave information</h4>
			</div>
			<div class="modal-body">
				<table id="tblHistoryLogDetail" class="table table-striped table-bordered table-hover striped-table">
					<thead>
						<tr>
							<th>Leave Date</th>
							<th>From Time</th>
							<th>To Time</th>
							<th>AM</th>
							<th>PM</th>
						</tr>
					</thead>
				</table>
				<div id="divRejectNote" style="display: none;">
					<br /> Reject Note :<br>
					<textarea rows="5" style="width: 100%" id="txtRejectNote" readonly class="input-disabled" cols=""></textarea>
				</div>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
			</div>
		</div>
	</div>
</div>

<div id="popoverExportContent" style="display: none;">
	<div class="radio">
		<label>
			<input name="rdoTypeFile" type="radio" class="ace" value="xls" checked="checked"/>
			<span class="lbl"> Excel </span>
		</label>
	</div>

	<div class="radio">
		<label>
			<input name="rdoTypeFile" type="radio" class="ace" value="pdf"/>
			<span class="lbl"> Pdf</span>
		</label>
	</div>
	<center>
		<button type="button" class="btn-info" id="btnConfirmReject" onclick="download();">Confirm</button>
	</center>
</div>
<div id="popoverExportTitle" style="display: none">
	<span> Select Type File </span>
</div>

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
		var tblHistoryLog;
		var tblHistoryLogDetail;

			$(document).ready(function() {
				tblHistoryLog = $("#tblHistoryLog").dataTable({
						"aoColumns"   : [
							{ "mData" : "employeeName" },
							{ "mData" : "leaveTypeName"  },
							{ "mData" : "leaveRequestStatus"  },
							{ "mData" : "requestDate"    },
							{ "mData" : "requestNote"    },
							{ "mData" : "",
								"bSortable": false,
								"mRender" : function (data, type, full) {
									return '<button id="btnLeaveDetail" class="btn-info" type="button">Detail</button>';
								}	
							}	],
							columnDefs: [
						                  { type: 'date-dd/mm/yyyy', targets: 3 }
							                ]
				}
				);
				
				tblHistoryLogDetail = $('#tblHistoryLogDetail').dataTable({
					"aoColumns"   : [
										{ "mData" : "date" },
										{ "mData" : "fromTime"  },
										{ "mData" : "toTime"    },
										{ "mData" : "am",
											"bSortable": false,
											"mRender" : function (data, type, full) {
												if (data == true) {
													return "<i class='glyphicon glyphicon-ok'></i>";
												}
												else {
													return "<i class='glyphicon glyphicon-remove'></i>";
												}
											}	
										},
										{ "mData" : "pm",
											"bSortable": false,
											"mRender" : function (data, type, full) {
												if (data == true) {
													return "<i class='glyphicon glyphicon-ok'></i>";
												}
												else {
													return "<i class='glyphicon glyphicon-remove'></i>";
												}
											}	
										}
									],
									columnDefs: [
								                  { type: 'date-dd/mm/yyyy', targets: 0 }
									                ]
								});
				
				$('#tblHistoryLog tbody').on( 'click', '#btnLeaveDetail', function () {
					var tr = $(this).closest('tr');
					var dataRow = $('#tblHistoryLog').dataTable().fnGetData(tr.get(0)._DT_RowIndex);
					var arrHistoryLogDetail = dataRow.historyLogDetailVos;

					if(dataRow.leaveRequestStatusId == ${leaveRequestStatusREJECTED.id}){
						$('#modalHistoryLogDetail').find("#txtRejectNote").val(dataRow.rejectNote);
						$('#modalHistoryLogDetail').find("#divRejectNote").show();
					}else 
						$('#modalHistoryLogDetail').find("#divRejectNote").hide();
				
					tblHistoryLogDetail.fnClearTable();
					tblHistoryLogDetail.fnAddData(arrHistoryLogDetail);
					tblHistoryLogDetail.fnDraw();
					
					$('#modalHistoryLogDetail').modal(
						{
							backdrop:'static'
						}
					);
				});
			
				$("#btnExport").popover({
			        html : true, 
			        content: function() {
			          $("#btnExport").popover('hide');
			          return $('#popoverExportContent').html();
			        },
			        title: function() {
			          return $('#popoverExportTitle').html();
			        },
			        placement: 'top'       
			    });
			});
			
			$("#txtEmployeeName").keypress(function(event){
			    if(event.keyCode == 13){
			    	 search();
			    }
			});
			
			function search(){
				var oJson = {
						employeeName: $("#txtEmployeeName").val(),  
						leaveDate:  $("#txtLeaveDate").val(),  
						leaveTypeId :  $("#selLeaveType").val()  ,
						leaveStatus :  $("#selLeaveStatus").val()  
				}

				$.ajax({
					url : '${pageContext.request.contextPath}/history-log/historyLogList',
					dataType: 'json',
					data : oJson,
					contentType: 'application/json',
				    mimeType: 'application/json',
					success : function(datas) {
						tblHistoryLog.fnClearTable();					
						if(datas.length != 0){		
							tblHistoryLog.fnAddData(datas);
							tblHistoryLog.fnDraw();	
						}
					}
				});
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
				var param = "employeeName=" + $("#txtEmployeeName").val() +
				"&leaveDate=" + $("#txtLeaveDate").val() +   
				"&leaveTypeId=" + $("#selLeaveType").val()  +  
				"&leaveStatus=" + $("#selLeaveStatus").val()  +  
				"&type=" + $("input[name='rdoTypeFile']:checked").val() +
				"&token=" + token;
				window.location = '${pageContext.request.contextPath}/report/historyLog?' + param;
			}
		</script>

		<div id='msgbox' title='' style='display:none'></div>