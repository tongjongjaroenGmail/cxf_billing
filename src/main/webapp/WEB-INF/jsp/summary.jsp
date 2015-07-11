
<div class="page-content col-sm-12">
	<div class="page-header">
		<h1>
			Employee Information
			<!--<small>
				<i class="icon-double-angle-right"></i>
				Static &amp; Dynamic Tables
			</small>-->
		</h1>
	</div>
	<!-- /.page-header -->

	<div class="row">
		<div class="col-xs-12">
			<!-- PAGE CONTENT BEGINS -->

			<div class="row">
				<div class="col-md-offset-1 col-xs-9">
					<div class="table-responsive">
						<table id="employee_info" class="table table-bordered ">

						</table>
					</div>
					<!-- /.table-responsive -->
				</div>
				<!-- /span -->
			</div>
			<!-- /row -->

			<div class="row">
				<div class="col-xs-12">
					<h3 class="header smaller lighter blue">Leave Records</h3>
					<div class="table-header">Recent leave records</div>

					<div class="table-responsive">
						<table class="table table-striped table-bordered table-hover" style="width: 100%; " id="leaveTable">
							<thead>
								<tr>
									<th>Leave type</th>
									<th>From Time</th>
									<th>To Time</th>
									<th>Period</th>
									<th>Status</th>
									<th>By</th>
									<th>Request Note</th>
									<th>Delete</th>
									<th>Detail</th>
								</tr>
							</thead>
						</table>
					</div>

					<div class="modal-footer no-margin-top"></div>
				</div>
			</div>
		</div>
	</div>
</div>

<div class="modal fade" id="modalSubLeave" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true"
	style="overflow-x: hidden; overflow-y: hidden;">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">
					<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
				</button>
				<h4 class="modal-title" id="myModalLabel">Leave information</h4>
			</div>
			<div class="modal-body">
				<table id="subLeaveTable" class="table table-striped table-bordered table-hover striped-table">
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
					<textarea rows="5" style="width: 100%" id="txtRejectNote" readonly class="input-disabled"></textarea>
				</div>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
			</div>
		</div>
	</div>
</div>

<script>
		var subLeaveTable;
		var leaveTable;
		//---------- TABLE LEAVE ---------------------------------
		$(document).ready(function() {
			getDataEmployee();
			
			leaveTable = $('#leaveTable').dataTable( {
				//"bPaginate": false,  
				"bProcessing" : true,
				"sAjaxSource" : "${pageContext.request.contextPath}/employee/listBy",
				"fnServerParams": function ( aoData ) {
				      aoData.push( { "name": "id", "value": "1" } );
				    },
				"aoColumns"   : [
					{ "mData" : "leaveType" },
					{ "mData" : "fromDate"  },
					{ "mData" : "toDate"    },
					{ "mData" : "period"    },
					{ "mData" : "status"    },
					{ "mData" : "by"        },
					{ "mData" : "requestNote"        },
					{ "mData" : "cancelStatus",
						"bSortable": false,
						"mRender" : function (data, type, full) {
							if (data == true) {
								return '<button id="btnCancel" class="btn-danger" type="button">Delete</button>';
							}
							else {
								return '';
							}
						}	
					},
					{ "mData" : "",
						"bSortable": false,
						"mRender" : function (data, type, full) {
							return '<button id="btnLeaveDetail" class="btn-info" type="button">Detail</button>';
						}	
					}
				],
				columnDefs: [
			                  { type: 'date-dd/mm/yyyy', targets: [1,2] }
			                ]
			} );
			
			subLeaveTable = $('#subLeaveTable').dataTable({
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
			}
					
			);
					
			//-----   CLICK BUTTON  ---------------------------------------
		
				$('#leaveTable tbody').on( 'click', '#btnCancel', function () {
					var tr = $(this).closest('tr');
					var dataRow = $('#leaveTable').dataTable().fnGetData(tr.get(0)._DT_RowIndex);
					$.ajax({
						url : '${pageContext.request.contextPath}/employee/cancelLeave',
						dataType: 'json',
						data : JSON.stringify(dataRow),
						contentType: 'application/json',
					    mimeType: 'application/json',
						type : "POST",
						success : function(data) {
							if(data.error == false){
								$.ajax( {
									url : '${pageContext.request.contextPath}/employee/listBy',
									dataType: 'json',
									data : {id : 1},
									contentType: 'application/json',
								    mimeType: 'application/json',
									success : function(arrData) {	
										leaveTable.fnDeleteRow(tr.get(0)._DT_RowIndex);
										getDataEmployee();
									}
								});
							}
							if(data.message != ""){
								alert(data.message);
							}
						}
					});
				} );
				
				$('#leaveTable tbody').on( 'click', '#btnLeaveDetail', function () {
					var tr = $(this).closest('tr');
					var dataRow = $('#leaveTable').dataTable().fnGetData(tr.get(0)._DT_RowIndex);
					var arrEmployeeSubLeaveVos = dataRow.employeeSubLeaveVos;

					subLeaveTable.fnClearTable();
					subLeaveTable.fnAddData(arrEmployeeSubLeaveVos);
					subLeaveTable.fnDraw();
					
					if(dataRow.statusId == ${leaveRequestStatusREJECTED.id}){
						$('#modalSubLeave').find("#txtRejectNote").val(dataRow.rejectNote);
						$('#modalSubLeave').find("#divRejectNote").show();
					}else 
						$('#modalSubLeave').find("#divRejectNote").hide();
					
					$('#modalSubLeave').modal(
						{
							backdrop:'static'
						}
					);
				});
			}
		);
		
		function getDataEmployee(){
			$.ajax( {
				url : '${pageContext.request.contextPath}/employee/view',
				dataType: 'json',
				contentType: 'application/json',
			    mimeType: 'application/json',
				success : function(data) {
					var htmlRow = '';
					htmlRow += '<tr>';
					htmlRow += 	'<th class="col-xs-1">Name</th>';
					htmlRow += 	'<td class="col-xs-2">'+ data.firstname + " " + data.lastname +'</td>';
					htmlRow += 	'<th class="col-xs-1">Employee Id</th>';
					htmlRow += 	'<td class="col-xs-1">'+ data.employeeId +'</td>';
					htmlRow += 	'<th class="col-xs-1">Available leaves</th>';
					htmlRow += 	'<td class="col-xs-1">'+ data.availableLeave +'</td>';
					htmlRow += 	'<th class="col-xs-1">Carry up days</th>';
					htmlRow += 	'<td class="col-xs-1">'+ data.carryForward +'</td>';
					htmlRow += 	'<th class="col-xs-1">Remain leaves</th>';
					htmlRow += 	'<td class="col-xs-1">'+ (data.availableLeave + data.carryForward - data.takenAnnualLeave) +'</td>';
					htmlRow += '</tr>';
					
					htmlRow += '<tr>';
					htmlRow += 	'<th>Line manager</th>';
					htmlRow += 	"<td>";
					
					$.each(data.lineManagers, function( index, value ) {
						htmlRow += value + "</br>";
					});
					
					htmlRow += "</td>";
					htmlRow += 	'<th class="col-xs-1">Join Date</th>';
					htmlRow += 	'<td class="col-xs-1">'+ data.joinDate +'</td>';
					htmlRow +=  "<th>Taken annual leaves</th>";
					htmlRow += 	"<td>"+ data.takenAnnualLeave +"</td>";
					htmlRow += 	"<th>Taken sick leaves</th>";
					htmlRow +=  "<td>"+ data.takenSickLeave +"</td>";
					htmlRow +=	'<th class="col-xs-1">Other leaves</th>';
					htmlRow +=  '<td class="col-xs-1">'+ data.takenOtherLeave +'</td>'
					htmlRow += '</tr>';
					
					$('#employee_info').html(htmlRow);
				}
			} );
		}
	</script>
