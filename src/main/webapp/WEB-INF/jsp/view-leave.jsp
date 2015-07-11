<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<style type="text/css">
table {
	table-layout: fixed;
}

.modal-header,.modal-body {
	background: white;
}

.tdSelected {
	border: 2px solid blue !important;
	background-color: rgb(82, 146, 247) !important;
}

.trMyself {
	border: 3px solid red !important;
}

.tdHoliday {
	background-color: grey !important;
}

.tdWeekend {
	background-color: rgb(58, 55, 55) !important
}

.tdLeaveNew {
	background-color: yellow !important;
}

.tdLeaveViewed {
	background-color: rgb(175, 216, 248) !important;
}

.tdLeaveApproved {
	background-color: green !important;
}

.tdLeaveRejected {
	background-color: red !important;
}

.tdLeaveCancelled {
	background-color: brown !important;
}

.tdLeave {
	padding: 0px !important;
	text-align: center;
}

.noselect {
    -webkit-touch-callout: none;
    -webkit-user-select: none;
    -khtml-user-select: none;
    -moz-user-select: none;
    -ms-user-select: none;
    user-select: none;
}

table tbody tr.even{
  background-color: whitesmoke;
}

#trHeaderDay {
	font-size:11px;
}
</style>




<div class="page-content">
	<div class="page-header">
		<h1>View Leaves</h1>
	</div>
	<!-- /.page-header -->

	<div class="row">
		<div class="col-xs-12">
			<!-- PAGE CONTENT BEGINS -->

			<div class="row">
				<div class="col-md-offset-1 col-xs-9">
					<div class="table-responsive">
						<table id="employee_info" class="table table-bordered ">
							<tr>
								<th>Line manager</th>
								<td colspan="3">
									<c:forEach var="lineManager" items="${lineManagers}" varStatus="counter">
										${lineManager}<br/>
									</c:forEach>
								</td>
							</tr>
							<tr>
								<th>
									<div id="nav-search">
								
											<span class="input-icon" style="width: 100%"> 
												<input type="text" placeholder="Search Employee ..." id="txtEmployeeName" autocomplete="off" class="col-xs-12"/> 
												<i class="icon-search nav-search-icon"></i>
											</span>
									
									</div>
								</th>

								<td>
									<form action="">
										<c:forEach var="department" items="${departments}">
											<input type="checkbox" name="chkDepartment" value="${department.id}">&nbsp;${department.name}<br>
										</c:forEach>
									</form>
								</td>
								<td>
									<form id="toggle-request" action="">
										<input type="checkbox" id="chkRequest" value="request" onclick="toggle('#leave-type-radio', this);" class="ace ace-switch ace-switch-5" /> 
										<span class="lbl">&nbsp;:&nbsp;Add Leave Request</span>
									</form>
								</td>
								<td>
									<form id="leave-type-radio" style="visibility: hidden;" action="">
										<c:forEach var="leaveType" items="${leaveTypes}" varStatus="index">
											<c:if test="${empty leaveType.parent}">
												<input type="radio" name="rdoLeaveType" value="${leaveType.id}" alt="${leaveType.name}">&nbsp;${leaveType.name}<br>
											</c:if>
										</c:forEach>
									</form>
								</td>
							</tr>
						</table>
					</div>
					<!-- /.table-responsive -->
				</div>
				<!-- /span -->
			</div>
			<!-- /row -->

			<div class="well">
				<fieldset>
					<div class="control-group">
						<div class="controls">
							<div class="input-prepend input-group">
								<span class="add-on input-group-addon"> Draw By&nbsp; <input type="radio" name="rdoDrawBy" value="type"
									checked="checked" onchange="changeRdoDrawBy();">&nbsp;Type&nbsp;&nbsp; <input type="radio"
									name="rdoDrawBy" value="status" onchange="changeRdoDrawBy();">&nbsp;Status
								</span> <span class="add-on input-group-addon"> <i class="glyphicon glyphicon-calendar fa fa-calendar"></i>
								</span> <input type="text" style="width: 200px" name="dateRange" id="dateRange" class="form-control" value="" />
								&nbsp;
							</div>
						</div>
					</div>
				</fieldset>
			</div>
			<center>
				<table style="font-size: smaller; color: #545454; border: 4px solid #ccc; padding: 3px; display: none;"
					id="tblLegendStatus">
					<tbody>
						<tr>
							<td class="legendColorBox"><div style="border: 1px solid #ccc; padding: 1px">
									<div style="width: 14px; height: 10px; overflow: hidden" class="tdLeaveNew"></div>
								</div></td>
							<td class="legendLabel">New Leave</td>
							<td class="legendColorBox"><div style="border: 1px solid #ccc; padding: 1px">
									<div style="width: 14px; height: 10px; overflow: hidden" class="tdLeaveViewed"></div>
								</div></td>
							<td class="legendLabel">Viewed Leave</td>
							<td class="legendColorBox"><div style="border: 1px solid #ccc; padding: 1px">
									<div style="width: 14px; height: 10px; overflow: hidden" class="tdLeaveApproved"></div>
								</div></td>
							<td class="legendLabel">Approved Leave</td>
							<%-- 
							<td class="legendColorBox"><div style="border:1px solid #ccc;padding:1px"><div style="width:14px;height:10px;overflow:hidden" class="tdLeaveRejected"></div></div></td>
							<td class="legendLabel">Rejected Leave</td>
							<td class="legendColorBox"><div style="border:1px solid #ccc;padding:1px"><div style="width:14px;height:10px;overflow:hidden" class="tdLeaveCancelled"></div></div></td>
							<td class="legendLabel">Cancelled Leave</td>
							--%>
							<td class="legendColorBox"><div style="border: 1px solid #ccc; padding: 1px">
									<div style="width: 14px; height: 10px; overflow: hidden" class="tdHoliday"></div>
								</div></td>
							<td class="legendLabel">Holiday</td>
							<td class="legendColorBox"><div style="border: 1px solid #ccc; padding: 1px">
									<div style="width: 14px; height: 10px; overflow: hidden" class="tdWeekend"></div>
								</div></td>
							<td class="legendLabel">Weekend</td>
						</tr>
					</tbody>
				</table>
				<table style="font-size: smaller; color: #545454; border: 4px solid #ccc; padding: 3px; display: none;"
					id="tblLegendType">
					<tbody>
						<tr>
							<c:forEach var="leaveType" items="${leaveTypes}" varStatus="counter">
								<c:if test="${empty leaveType.parent}">
									<td class="legendColorBox">
										<div style="border: 1px solid #ccc; padding: 1px">
											<div style="width:14px;height:10px;overflow:hidden;background-color: ${leaveType.color} !important;"></div>
										</div>
									</td>
									<td class="legendLabel">${leaveType.name}</td>
								</c:if>	
							</c:forEach>
							<td class="legendColorBox"><div style="border: 1px solid #ccc; padding: 1px">
									<div style="width: 14px; height: 10px; overflow: hidden" class="tdHoliday"></div>
								</div></td>
							<td class="legendLabel">Holiday</td>
							<td class="legendColorBox"><div style="border: 1px solid #ccc; padding: 1px">
									<div style="width: 14px; height: 10px; overflow: hidden" class="tdWeekend"></div>
								</div></td>
							<td class="legendLabel">Weekend</td>
						</tr>
					</tbody>
				</table>
			</center>
			<div class="row">
				<div class="col-xs-12">
					<div class="table-responsive" id="divleaveTable">

					</div>
					<!-- /.table-responsive -->
				</div>
				<!-- /span -->
			</div>
			<!-- /row -->
		</div>
		<!-- PAGE CONTENT ENDS -->
	</div>
	<!-- /.col -->
</div>
<!-- /.row -->



<div class="modal fade" id="leaveInformationModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
	aria-hidden="true" style="overflow-x: hidden; overflow-y: auto;">
	<div class="modal-dialog">
		<div class="modal-content" style="width: 700px">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">
					<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
				</button>
				<h4 class="modal-title" id="myModalLabel">Leave information</h4>
			</div>
			<div class="modal-body">
				<b>Employee Name</b> : <span id="employeeName"></span><br>
				<div id="divLeaveInformation"></div>
			</div>
			<div class="modal-footer"></div>
		</div>
	</div>
</div>

<div class="modal fade" id="leaveRequestModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
	aria-hidden="true" style="overflow-x: hidden; overflow-y: auto;">
	<div class="modal-dialog">
		<div class="modal-content" style="width: 700px">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">
					<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
				</button>
				<h4 class="modal-title" id="myModalLabel">Leave Request</h4>
			</div>
			<div class="modal-body">
				<b>Employee Name</b> : <span id="employeeName"></span> <input type="hidden" id="employeeId" /> <br>

				<table style='width: 100%'>
					<tr>
						<td width="38%"><b>Request Date</b> : <span id="requestDate"></span></td>
						<td><b>Status</b> : New</td>
						
					</tr>
					<tr>
						<td><b>Leave Type</b> : <span id="leaveType"></td>	
						<td id="tdSelSubLeaveType"><b>Sub Leave Type</b> : 
							<select id="selSubLeaveType">
								<option value="">--</option>
							</select>
						</td>
					</tr>
				</table>
				<br>
				<table class='table table-striped table-bordered table-hover'
					style='width: 100%; table-layout: fixed; white-space: nowrap;' id="tblLeaveRequest">
					<thead>
						<tr>
							<th>leave Date</th>
							<th>AM</th>
							<th>PM</th>
						</tr>
					</thead>
					<tbody>
					</tbody>
				</table>

				Request Note :<br>
				<textarea rows="5" style="width: 100%" id="txtRequestNote"></textarea>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-success" onclick="saveLeaveRequest();">Save</button>
				<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
			</div>
		</div>
	</div>
</div>

<div id="popoverRejectContent" style="display: none;">
	<textarea rows="5" cols="25" id="txtRejectNote"></textarea>
	<center>
		<button type="button" class="btn-info" id="btnConfirmReject" onclick="updateLeaveRequestStatus(leaveRequestIdForReject,3);">Confirm</button>
	</center>
</div>
<div id="popoverRejectTitle" style="display: none">
	<span> Confirm </span>
</div>


<script>
	var startDate;
	var endDate;
	var isMouseDown = false;
	var leaveDatas;
	var leaveTableDT;
	var leaveRequestIdForReject = "";

	var roleId = ${loginEmployee.role.id};
	var loginEmployeeDepartmentId = ${loginEmployee.department.id};
	var tdLeaveColor = new Object();
	tdLeaveColor.New = "yellow";
	tdLeaveColor.Viewed = "rgb(175,216,248)";
	tdLeaveColor.Approved = "green";
	tdLeaveColor.Rejected = "red";
	tdLeaveColor.Cancelled = "brown";
	
	var arrHoliday = [];
	<c:forEach var="holiday" items="${holidays}" varStatus="counter">
	arrHoliday[${counter.index}] = '<fmt:formatDate pattern="dd/MM/yyyy"  value="${holiday.date}" />';
	</c:forEach>
	
	var arrLeaveType = [];
	<c:forEach var="leaveType" items="${leaveTypes}" varStatus="counter">
		arrLeaveType[${counter.index}] = {id: "${leaveType.id}", name:"${leaveType.name}", parentId: "${leaveType.parent.id}" };
	</c:forEach>

		$(document).ready(function() {
			$('#dateRange').daterangepicker({
				timePicker : false,
				format :'DD/MM/YYYY',
				startDate: moment().subtract(45, 'days'),
                endDate: moment().add(45, 'days'),
				dateLimit: { days: 180 }
			}, function(start, end, label) {
				startDate = start;
				endDate = end;
			});	
			
			startDate = $('#dateRange').data('daterangepicker').startDate;
			endDate = $('#dateRange').data('daterangepicker').endDate;
			$('#dateRange').val(startDate.format('DD/MM/YYYY') + " - " + endDate.format('DD/MM/YYYY'));
	
			$("[name='rdoLeaveType']:first").prop( "checked", true );
			$("input[name='chkDepartment']").prop( "checked", true );
			
			if(roleId == 1){
				$('#txtEmployeeName').val("${loginEmployee.fullName}");
			}
			createLeaveTable();
		});
		
		function toggle(className, obj) {
		    var $input = $(obj);
		    if ($input.prop('checked')){
		    	$(className).css("visibility", "visible");
		    }
		    else {
		    	$(className).css("visibility", "hidden");
		    	$("#leaveTable tbody tr").find(".tdSelected").removeClass("tdSelected");
		    }
		}
		
		$('#dateRange').on('apply.daterangepicker', function(ev, picker) {
			startDate = picker.startDate;
			endDate = picker.endDate;
			
			createLeaveTable();
		});
		
		function changeRdoDrawBy(){
			var drawBy = $("input[name='rdoDrawBy']:checked").val();
			if(drawBy == "type"){
				$("#tblLegendType").show();
				$("#tblLegendStatus").hide();
			}else{
				$("#tblLegendType").hide();
				$("#tblLegendStatus").show();
			}
			
			leaveTableDT.fnStandingRedraw();
		}

		function createLeaveTable(){
			var drawBy = $("input[name='rdoDrawBy']:checked").val();
			if(drawBy == "type"){
				$("#tblLegendType").show();
				$("#tblLegendStatus").hide();
			}else{
				$("#tblLegendType").hide();
				$("#tblLegendStatus").show();
			}
			
			if(leaveTableDT != null){
				leaveTableDT.fnDestroy();
			}
			
		    var objSDate = new Date(startDate.valueOf()); 
		    var objEDate = new Date(endDate.valueOf());   

		    var arrHeaderMonth = [];
		    var arrColumn = [];
		    var iColumn = 0;
		    var iMonth = 0;
		    var oldMonth = 0;
		    var oldYear = 0;
		    var classTdHoliday = "";
		    var classTdWeekend = "";
		    var todayYYYYMMDD = moment(new Date()).format("DD/MM/YYYY");
		    var drawBy = $("input[name='rdoDrawBy']:checked").val();
		    var trHeaderDay = "";
		    var trHeaderMonth = "";
		    var firstTd = true;
			while (objSDate <= objEDate) {
				if(objSDate.getMonth() != oldMonth || objSDate.getFullYear() != oldYear){
					if(objSDate.getMonth() == objEDate.getMonth() && objSDate.getFullYear() == objEDate.getFullYear()){
				    	lastDayOfMonth = objEDate.getDate();
				    }else{
				    	lastDayOfMonth = (new Date(objSDate.getFullYear(),(objSDate.getMonth() + 1), 0)).getDate();
				    }
					
					arrHeaderMonth[iMonth++] = [moment(objSDate).format("MMM") ,lastDayOfMonth - objSDate.getDate() + 1];
				}
				
				var objSDateYYYYMMDD = moment(objSDate).format("DD/MM/YYYY");
				
				classTdHoliday = "";
				if(isHoliday(objSDateYYYYMMDD)){
					classTdHoliday = "tdHoliday";
				}
				classTdWeekend = "";
				if(isWeekend(objSDate)){
					classTdWeekend = "tdWeekend";
				}
				var cssBorder = "";
				if(todayYYYYMMDD == objSDateYYYYMMDD){
					cssBorder = "border-left: 2px solid red;";
				}
				trHeaderDay += "<th style='text-align: center;padding:0px;" + cssBorder + "' class='" + classTdHoliday + " " + classTdWeekend + "'>" + objSDate.getDate() + "</th>";

			    oldMonth = objSDate.getMonth();
			    oldYear = objSDate.getFullYear();
			    
			    objSDate.setDate(objSDate.getDate() + 1);
			    firstTd = false;
			    
			    arrColumn[iColumn] = ++iColumn;
			}
	
			for(var i = 0;i < arrHeaderMonth.length;i++){
				var colWidth = (parseInt( arrHeaderMonth[i][1],10) * 14) + "px  !important;";
				trHeaderMonth += "<th colspan='" + arrHeaderMonth[i][1] + "' style='text-align: center;width:" + colWidth + ";'>" + arrHeaderMonth[i][0] + "</th>";
			}

			$("#divleaveTable").html(
				'<table class="table table-striped table-bordered table-hover noselect" ' + 
					'style="width: 100%; table-layout: fixed; white-space: nowrap;" id="leaveTable">' + 
					'<thead>' + 
						'<tr id="trHeaderMonth">' + 
							'<th style="width:250px  !important;"></th>' + 
							trHeaderMonth + 
						'</tr>' + 
						'<tr class="small" id="trHeaderDay">' + 
							'<th>name</th>' + 
							trHeaderDay +
						'</tr>' + 
					'</thead>' + 
					'<tbody id="tbodyLeaveTable">' + 
					'</tbody>' + 
				'</table>'
			);
			
			/* ------------ create event ------------ */  
		    jQuery('#leaveTable')
		      .on('mouseup', 'td:not(:first-child)', function() {
		    	  if(isMouseDown){
					  openLeaveRequestPopup();
				  }
				  isMouseDown = false;
				  $(this).find(".tdSelected").removeClass("tdSelected");
		      })
		      .on('mousedown', 'td:not(:first-child)', function() {
		    	  var rowData = leaveTableDT.fnGetData($("#leaveTable tbody tr").index($(this).parent("tr")));
		    	  
		    	  if( $("#chkRequest").prop( "checked" ) && ($(this).parent("tr").hasClass( "trMyself" ) || (roleId == 2 && loginEmployeeDepartmentId == rowData.departmentId) || roleId == 3)){
					  $("#leaveTable tbody td").removeClass("tdSelected");
					  isMouseDown = true;
					  $(this).addClass( "tdSelected" );
				  }else if($(this).attr('class') == "tdLeave"){
					  openLeaveInformationPopup(this);
				  } 
			  })
			  .on('mouseover', 'td:not(:first-child)', function() {
				  if(isMouseDown){
					  if($("#leaveTable tbody tr").index($(this).parent("tr")) == $("#leaveTable tbody tr").index($(".tdSelected").parent("tr"))){
						  $(this).addClass( "tdSelected" );
					  }else{
						  openLeaveRequestPopup();
						  
						  isMouseDown = false;
						  $(this).find(".tdSelected").removeClass("tdSelected");
					  }  
				  }
			  });
			  
			jQuery('#leaveTable')
		      .on('mouseleave', 'tbody', function() {
				  if(isMouseDown){
					  openLeaveRequestPopup();
				  }
				  isMouseDown = false;
				  $(this).find(".tdSelected").removeClass("tdSelected");
			  });
			/* ------------ create event ------------ */
			
			leaveTableDT = $("#leaveTable").dataTable({
				//bFilter: false, 
				//bInfo: false,
				  "lengthMenu": [[20, 40, 60, 100], [20, 40, 60, 100]],
				  "bSort" : false,
				  "scrollY": "500px",
				  "scrollX": true,
				  "scrollCollapse": false,	  
			      "aoColumnDefs": [
					   { 'width': "250px", 
						 'aTargets': 0 ,
						 "mData": "employeeName",
						 "fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
							 $(nTd).css('background-color', 'rgb(201, 201, 207)');
						 }
					   },
                       { 'width': "14px",
						 'aTargets': arrColumn,
						 "mData":function( data, type, val ) {
						        return "";
						    },
						 "fnCreatedCell": function (nTd, sData, oData, iRow, iCol) {
							 var drawBy = $("input[name='rdoDrawBy']:checked").val();
							 var obj = oData.viewLeaveDateVos[iCol - 1];
							 if(obj.viewLeaveDateDetailVos.length > 0){
								 var leaveColorAm = "";
								 var leaveColorPm = "";
								 var leaveStatusNameAm = "";
								 var leaveStatusNamePm = "";
								 var arrLeaveRequestId = new Array();
								 var index = 0;
								 $.each(obj.viewLeaveDateDetailVos, function( index, value ) {
									 arrLeaveRequestId[index++] = value.leaveRequestId;
									 
									 var objLeaveRequest = $.grep(oData.viewLeaveLeaveRequestVos, function(e){ 
											return e.leaveRequestId == value.leaveRequestId; 
										});
									 
									 if(value.am){
										leaveStatusNameAm = objLeaveRequest[0].statusName;
										if(drawBy == "type"){
											leaveColorAm = objLeaveRequest[0].leaveTypeColor;
										}else{
											leaveColorAm = tdLeaveColor[objLeaveRequest[0].statusName];
										}			
									 }
									 if(value.pm){
										leaveStatusNamePm = objLeaveRequest[0].statusName;
									 	if(drawBy == "type"){
											leaveColorPm = objLeaveRequest[0].leaveTypeColor;
										}else{
											leaveColorPm = tdLeaveColor[objLeaveRequest[0].statusName];
										}		
									 }
								 });
								 
								var colorAM = "";
								var colorPM = "";
								var divIcon = "";
								var approveFullDay = false;
								if(leaveStatusNameAm == "${leaveRequestStatusAPPROVED.name}" && leaveStatusNamePm == "${leaveRequestStatusAPPROVED.name}"){
									colorAM = "color-stop(50%," + leaveColorAm + ")";
									colorPM = "color-stop(50%," + leaveColorPm + ")";
									if(drawBy == "type"){
										approveFullDay = true;
										divIcon += "<div class='divIconAm'><i class='glyphicon glyphicon-ok'></i>&nbsp;</div>";
									}
								}else{
									if(leaveColorAm != ""){
										colorAM = "color-stop(50%," + leaveColorAm + ")";
										if(drawBy == "type"){
											if(leaveStatusNameAm == "${leaveRequestStatusAPPROVED.name}"){
												divIcon += "<div class='divIconAm'><i class='glyphicon glyphicon-ok'></i>&nbsp;</div>";
											}else{
												divIcon += "<div class='divIconAm'>&nbsp;</div>";
											}
										}		
									}else{
										colorAM = "color-stop(50%,rgba(0,0,0,0))";
										leaveColorAm = "rgba(0,0,0,0)";
										divIcon += "<div class='divIconAm'>&nbsp;</div>";
									}
									if(leaveColorPm != ""){
										colorPM = "color-stop(50%," + leaveColorPm + ")";
										if(drawBy == "type"){
											if(leaveStatusNamePm == "${leaveRequestStatusAPPROVED.name}"){
												divIcon += "<div class='divIconPm'><i class='glyphicon glyphicon-ok'></i>&nbsp;</div>";
											}else{
												divIcon += "<div class='divIconPm'>&nbsp;</div>";
											}
										}
									}else{
										colorPM = "color-stop(50%,rgba(0,0,0,0))";	
										leaveColorPm = "rgba(0,0,0,0)";
										divIcon += "<div class='divIconPm'>&nbsp;</div>";
									}
								}
								if(approveFullDay){
									$(nTd).css('vertical-align', 'middle');
								}
								$(nTd).addClass( "tdLeave" );
								$(nTd).css('background', "-webkit-gradient(linear, left top, right bottom, " + colorAM + ", " + colorPM + ")");
								$(nTd).css('background', "-moz-linear-gradient(top,  " + leaveColorAm + " 0%, " + leaveColorAm + " 50%, " + leaveColorPm + " 51%, " + leaveColorPm + " 100%)");
								$(nTd).css('background', "-ms-linear-gradient(top,  " + leaveColorAm + " 0%," + leaveColorAm + " 50%," + leaveColorPm + " 51%," + leaveColorPm + " 100%)");
								$(nTd).html(divIcon + "<input type='hidden' name='leaveRequestIds' value='" + arrLeaveRequestId.join() + "' />" );
							 }else if(obj.holiday){
								 $(nTd).addClass( "tdHoliday" );
							 }else if(obj.weekend){
								 $(nTd).addClass( "tdWeekend" );
							 }
							 
							 if(obj.today){
								$(nTd).css('border-left', '2px solid red');
							}
					      }
                       }
                    ],
                    
                    "processing": true,
                    "serverSide": true,
                    "ajax": {
                        "url": '${pageContext.request.contextPath}/view-leave/viewLeaveList',
                        "type": "POST",
                        "data": function ( d ) {
                            d.startDate = startDate.format("DD/MM/YYYY");
                            d.endDate = endDate.format("DD/MM/YYYY");
                            d.employeeName = $('#txtEmployeeName').val();
                            d.departmentIds = $("input[name='chkDepartment']:checked").map(function() {
                                return this.value;
                            }).get().join();
                        }
                    },
					"createdRow": function ( row, data, index ) {
			            if ( data.employeeId == '${loginEmployee.id}') {
			                $(row).addClass('trMyself');
			            }
			        }
             });

			$('#leaveTable_filter').hide();
			
			new $.fn.dataTable.FixedColumns( leaveTableDT);
			$( ".DTFC_LeftBodyLiner" ).css("overflow", "hidden");
			$( ".DTFC_LeftBodyLiner" ).css("width", "");

			$(leaveTableDT).on( 'draw.dt', function () {
				$( ".DTFC_LeftBodyLiner" ).css("overflow", "hidden");
				$( ".DTFC_LeftBodyLiner" ).css("width", "");
			} );
			
			$('.dataTables_scrollBody').on("scrollstop",function(){
				var p = $( ".dataTables_scrollBody > table" );
				var offset = p.offset();
				$('.DTFC_LeftBodyLiner > table').offset({ top: offset.top});
			 });        
		}
		
		$('#txtEmployeeName').keyup( function() {
			delay(function(){
				leaveTableDT.fnDraw();
			}, 1000 );
	    } );
		
		$("input[name='chkDepartment']").click( function() {
			delay(function(){
				leaveTableDT.fnDraw();
			}, 1000 );
	    } );
		 
		function openLeaveRequestPopup(){
			var rowData = leaveTableDT.fnGetData($("#leaveTable tbody tr").index($(".tdSelected").parent("tr")));

			$("#leaveRequestModal").find("#employeeName").html(rowData.employeeName);
			$("#leaveRequestModal").find("#employeeId").val(rowData.employeeId);
			$("#leaveRequestModal").find("#requestDate").html(moment().format("DD/MM/YYYY"));
			$("#leaveRequestModal").find("#leaveType").html($("input[name='rdoLeaveType']:checked").attr("alt"));
			$("#leaveRequestModal").find("#txtRequestNote").val("");
			
			var objLeaveTypes = $.grep(arrLeaveType, function(e){ 
				return e.parentId == $("input[name='rdoLeaveType']:checked").val(); 
			});
			if(objLeaveTypes.length == 0){
				$("#tdSelSubLeaveType").hide();
			}else{
				$("#tdSelSubLeaveType").show();
				
				var mySelect = $("#leaveRequestModal").find("#selSubLeaveType");
				$("#selSubLeaveType option:gt(0)").remove();
				$.each(objLeaveTypes, function(val, obj) {
				    mySelect.append( $('<option></option>').val(obj.id).html(obj.name));
				});
			}		
			
			$("#tblLeaveRequest tbody").find("tr").remove();
			
			var isCanOpenModal = false;
			$( ".tdSelected" ).each(function() {
				var indexTd = $(this).parent().children().index($(this)) - 1;
				var date = rowData.viewLeaveDateVos[indexTd].date;
				if(!($( this ).hasClass( "tdHoliday" ) || $( this ).hasClass( "tdWeekend" ))){
					var am = "";
					var pm = "";
					if($( this ).hasClass( "tdLeave" )){
						$.each(rowData.viewLeaveDateVos[indexTd].viewLeaveDateDetailVos, function(val, obj) {
							if(obj.am){
								am = "disabled";
							}else{
								isCanOpenModal = true;
							}
							if(obj.pm){
								pm = "disabled";
							}else{
								isCanOpenModal = true;
							}
						});
					}else{
						isCanOpenModal = true;
					}
					
				
					$("#tblLeaveRequest tbody").append(
						"<tr> " + 
						"<td>" + date + "</td> " + 
						"<td><input type='checkBox' name='chkAm' checked " + am + "></td> " + 
						"<td><input type='checkBox' name='chkPm' checked " + pm + "></td> " + 
						"</tr> "
					);
				}
			});
			
			if(isCanOpenModal){
				$('#leaveRequestModal').modal({
					backdrop: "static"
				});
			}
		}
		
		function openLeaveInformationPopup(objTd){		
			$("#divLeaveInformation").html("");

			var leaveRequestIds = $(objTd).find("input[name='leaveRequestIds']").val().split(",");	
			var rowData = leaveTableDT.fnGetData($("#leaveTable tbody tr").index($(objTd).parent("tr")));
			var employeeId = rowData.employeeId;
			$("#leaveInformationModal").find("#employeeName").html(rowData.employeeName);
			var htmlLeaveRequest = "";
			for ( var i = 0; i < leaveRequestIds.length; i++) {
				var objLeaveRequest = $.grep(rowData.viewLeaveLeaveRequestVos, function(e){ 
					return e.leaveRequestId == leaveRequestIds[i]; 
				});
				
				var leaveRequestId = objLeaveRequest[0].leaveRequestId;
				var statusId = objLeaveRequest[0].statusId;
				var statusName = objLeaveRequest[0].statusName;
				var requestDate = objLeaveRequest[0].requestDate;
				var leaveTypeId = objLeaveRequest[0].leaveTypeId;
				var leaveTypeName = objLeaveRequest[0].leaveTypeName;
				var requestNote = objLeaveRequest[0].requestNote || "";
				
				var btnChangeStatuses = "";
				if(statusId == ${leaveRequestStatusNEW.id} || statusId == ${leaveRequestStatusVIEWED.id}){
					if(roleId == 2 && loginEmployeeDepartmentId == rowData.departmentId){
						if(statusId == ${leaveRequestStatusNEW.id}){
							updateLeaveRequestStatus( leaveRequestId , ${leaveRequestStatusVIEWED.id});
							statusName = "${leaveRequestStatusVIEWED.name}";
						}
						btnChangeStatuses += 
							' <input type="button" class="btn btn-success" onclick="updateLeaveRequestStatus(' + leaveRequestId + 
							"," + ${leaveRequestStatusAPPROVED.id} + 
							')" value="Approve"/>' +
							' <input type="button" class="btn btn-warning" name="popoverReject" value="Reject" onclick="leaveRequestIdForReject = ' + leaveRequestId + ';"/>';
					}
					
					if(roleId == 3 || employeeId == ${loginEmployee.id}){
						btnChangeStatuses += 
							' <input type="button" class="btn btn-danger" onclick="updateLeaveRequestStatus(' + leaveRequestId + 
							"," + -1 + 
							')" value="Delete"/>';
					}			
				}else if(statusId == ${leaveRequestStatusAPPROVED.id}){
					if(roleId == 2 && loginEmployeeDepartmentId == rowData.departmentId){
						btnChangeStatuses += 
							' <input type="button" class="btn btn-warning" name="popoverReject" value="Reject" onclick="leaveRequestIdForReject = ' + leaveRequestId + ';"/>';
					}else if(roleId == 3){
						btnChangeStatuses += 
							' <input type="button" class="btn btn-danger" onclick="updateLeaveRequestStatus(' + leaveRequestId + 
							"," + ${leaveRequestStatusCANCELLED.id} + 
							')" value="Cancel"/>';
					}
				}else if(statusId == ${leaveRequestStatusREJECTED.id}){
					if(roleId == 2 && loginEmployeeDepartmentId == rowData.departmentId){
						btnChangeStatuses += 
							' <input type="button" class="btn btn-success" onclick="updateLeaveRequestStatus(' + leaveRequestId + 
							"," + ${leaveRequestStatusAPPROVED.id} + 
							')" value="Approve"/>';
					}else if(roleId == 3){
						btnChangeStatuses += 
							' <input type="button" class="btn btn-danger" onclick="updateLeaveRequestStatus(' + leaveRequestId + 
							"," + ${leaveRequestStatusCANCELLED.id} + 
							')" value="Cancel"/>';
					}
				}else if(statusId == ${leaveRequestStatusCANCELLED.id}){
					if(roleId == 3){
						btnChangeStatuses += 
							' <input type="button" class="btn btn-danger" onclick="updateLeaveRequestStatus(' + leaveRequestId + 
							"," + -1 + 
							')" value="Delete"/>';
					}
				}	
				
				htmlLeaveRequest = 
				"<h5>" + (i + 1) + ". Leave Request</h5> " + 
				"<table style='width:100%'> " + 
					"<tr> " + 
						"<td width='38%'><b>Request Date</b> : " + requestDate + "</td> " + 
						"<td width='62%'><b>Status</b> : " + statusName	+ "</td> " + 		
					"</tr> " + 
					"<tr> ";
				
				var hasParentLeaveType = false;
				$.each(arrLeaveType, function(val, obj) {
				    if(leaveTypeId == obj.id){
				    	$.each(arrLeaveType, function(val, obj2) {
						    if(obj.parentId == obj2.id){
						    	htmlLeaveRequest += "<td><b>Leave Type</b> : " + obj2.name + "</td>";
						    	htmlLeaveRequest += "<td><b>Sub Leave Type</b> : " + leaveTypeName + "</td> ";
						    	hasParentLeaveType = true;
						    	return false;
						    }
						});
				    	return false;
				    }
				});
				
				if(!hasParentLeaveType){
					htmlLeaveRequest += "<td><b>Leave Type</b> : " + leaveTypeName + "</td>";
				}
	
				htmlLeaveRequest += "</tr> " + 
					"<tr> " + 
						"<td colspan='3' align='center'>" +
						btnChangeStatuses +
						"</td> " + 	
					"</tr> " + 
				"</table><br> " + 
				"<table class='table table-striped table-bordered table-hover' style='width: 100%; table-layout: fixed; white-space: nowrap;'> " + 
					"<thead><tr> " + 
						"<th>leave Date</th> " + 
						"<th>AM</th> " + 
						"<th>PM</th> " + 
					"</tr></thead><tbody>";
		
				$.each(objLeaveRequest[0].viewLeaveLeaveVos, function( index, value ) {
					var iconAM = "glyphicon-remove";
					var iconPM = "glyphicon-remove";
					if(value.am){
						iconAM = "glyphicon-ok";
					}
					if(value.pm){
						iconPM = "glyphicon-ok";
					}
					
					htmlLeaveRequest += "<tr> " + 
					"<td>" + value.date + "</td> " + 
					"<td><i class='glyphicon " + iconAM + "'></i></td> " + 
					"<td><i class='glyphicon " + iconPM + "'></i></td> " + 
					"</tr>";
				});	
				htmlLeaveRequest +=  "</tbody></table> ";
				htmlLeaveRequest += 'Request Note :<br><textarea rows="5" style="width: 100%" id="txtRequestNote" readonly="readonly" class="input-disabled">' + requestNote + '</textarea>';
				$("#divLeaveInformation").append(htmlLeaveRequest);
			}
			
			$("input[name='popoverReject']").popover({
		        html : true, 
		        content: function() {
		          $("input[name='popoverReject']").popover('hide');
		          $("#txtRejectNote").val("");
		          return $('#popoverRejectContent').html();
		        },
		        title: function() {
		          return $('#popoverRejectTitle').html();
		        },
		        placement: 'top'       
		    });
			
			$('#leaveInformationModal').modal();
		}
		
		function updateLeaveRequestStatus(leaveRequestId , newLeaveStatus){
			var requestData = new Object();
			requestData.newLeaveStatus = newLeaveStatus;
			requestData.leaveRequestId = leaveRequestId;
			requestData.rejectNote = $('#txtRejectNote').val();
			$.ajax( {
				url : '${pageContext.request.contextPath}/view-leave/updateLeaveStatus',
				dataType: 'json',
				data : requestData,
				contentType: 'application/json',
			    mimeType: 'application/json',
				success : function(data) {
					if(data.message != ""){
						alert(data.message);
					}
	
					leaveTableDT.fnStandingRedraw();
					if(newLeaveStatus != "1" && data.error == false){
						$('#leaveInformationModal').modal('hide');
					}
				}
			} );
		}
		
		function isWeekend(pDate){
			if(pDate.getDay() == 0 || pDate.getDay() == 6){
				return true;
			}else{
				return false;
			}
		}
		
		var weekday = new Array(7);
		weekday[0]=  "SUN";
		weekday[1] = "MON";
		weekday[2] = "TUE";
		weekday[3] = "WED";
		weekday[4] = "THU";
		weekday[5] = "FRI";
		weekday[6] = "SAT";
		
		function isWeekendEmployee(pDate , weekendDescription){
			var arrWeekEnd = weekendDescription.split(",");
			for(var i = 0;i < arrWeekEnd.length;i++){
				if(arrWeekEnd[i].trim() == weekday[pDate.getDay()].trim()){
					return true;
				}
			}
			
			return false;
		}
		
		function isHoliday(pDate){
			var result = $.grep(arrHoliday, function(e){ 
				return e == pDate; 
			});
			if(result.length > 0){
				return true;
			}else{
				return false;
			}
		}
		
		function saveLeaveRequest(){
			var totalNoDays = ($("#tblLeaveRequest tbody").find("[name='chkAm']:checked:enabled").size() + $("#tblLeaveRequest tbody").find("[name='chkPm']:checked:enabled").size()) * 0.5;
			if(totalNoDays == 0){
				alert("Please select leave data.");
				return;
			}
			
			var objLeaveTypes = $.grep(arrLeaveType, function(e){ 
				return e.parentId == $("input[name='rdoLeaveType']:checked").val(); 
			});	
			
			var leaveTypeId = $("input[name='rdoLeaveType']:checked").val();
			if(objLeaveTypes.length > 0){
				if($("#selSubLeaveType").val() == ""){
					alert("Please select Sub Leave Type.");
					return;
				}else{
					leaveTypeId = $("#selSubLeaveType").val();
				}
			}
			
			var arrLeave = [];
			var i = 0;
			$( "#tblLeaveRequest tbody tr" ).each(function() {
				if(($(this).find("[name='chkAm']:checked:enabled").size() + $(this).find("[name='chkPm']:checked:enabled").size()) > 0){
					var oJsonLeave = {
						date : $(this).find("td:first").html(),
						am : $(this).find("[name='chkAm']:checked:enabled").size(),
						pm : $(this).find("[name='chkPm']:checked:enabled").size()
					};
					arrLeave[i++] = oJsonLeave;
				}
			});
	
			var oJson = {
					employeeId : $("#leaveRequestModal").find("#employeeId").val(),  
					leaveTypeId : leaveTypeId,
					totalNoDays : totalNoDays,
					viewLeaveLeaveVos : arrLeave,
					requestNote : $("#leaveRequestModal").find("#txtRequestNote").val()
				};
			
			$.ajax({
				url : '${pageContext.request.contextPath}/view-leave/saveLeaveRequest',
				dataType: 'json',
				data : JSON.stringify(oJson),
				contentType: 'application/json',
			    mimeType: 'application/json',
				type : "POST",
				success : function(data) {
					if(data.message != ""){
						alert(data.message);
					}
					if(data.error == false){			
						leaveTableDT.fnStandingRedraw();
						$("#chkRequest").prop( "checked", false );
						toggle('#leave-type-radio', $("#chkRequest"));
						
						$('#leaveRequestModal').modal('hide');
					}
				}
			});
		}
		
		var delay = (function(){
			  var timer = 0;
			  return function(callback, ms){
			    clearTimeout (timer);
			    timer = setTimeout(callback, ms);
			  };
			})();
	</script>

