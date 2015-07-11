<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<meta name="description" content="with draggable and editable events" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />

<div class="page-content">
	<div class="page-header">
		<h1>Holiday</h1>
	</div>
	<!-- /.page-header -->

	<div class="row">
		<div class="col-xs-12">
			<!-- PAGE CONTENT BEGINS -->

			<div class="row">
				<div class="col-sm-9">
					<div id="calendar"></div>
				</div>

				<div class="col-sm-3">
					<c:if test="${loginEmployee.role.id == 3}">
					<div class="widget-box transparent">
						<div class="widget-header">
							<h4>Draggable holidays</h4>
						</div>

						<div class="widget-body">
							<div class="widget-main no-padding">
								<div id="external-events">
									<div class="external-event label-info" data-class="label-info">
										<i class="icon-move"></i> New Year's Day
									</div>

									<div class="external-event label-info" data-class="label-info">
										<i class="icon-move"></i> Songkran Day
									</div>

									<div class="external-event label-info" data-class="label-info">
										<i class="icon-move"></i> Chakri Day
									</div>

									<div class="external-event label-info" data-class="label-info">
										<i class="icon-move"></i> Makha Bucha Day
									</div>

									<div class="external-event label-info" data-class="label-info">
										<i class="icon-move"></i> National Labor Day
									</div>

									<div class="external-event label-info" data-class="label-info">
										<i class="icon-move"></i> Coronation Day
									</div>

									<div class="external-event label-info" data-class="label-info">
										<i class="icon-move"></i> Wisakha Bucha Day
									</div>

									<div class="external-event label-info" data-class="label-info">
										<i class="icon-move"></i> Asalha Bucha Day
									</div>

									<div class="external-event label-info" data-class="label-info">
										<i class="icon-move"></i> Her Majesty The Queen's Birthday
									</div>

									<div class="external-event label-info" data-class="label-info">
										<i class="icon-move"></i> Chulalongkorn Memorial Day
									</div>

									<div class="external-event label-info" data-class="label-info">
										<i class="icon-move"></i> Subtitution for H.M. the King's Birthday
									</div>

									<div class="external-event label-info" data-class="label-info">
										<i class="icon-move"></i> Constitution Day
									</div>

									<div class="external-event label-info" data-class="label-info">
										<i class="icon-move"></i> New Year's Eve
									</div>

									<%-- 
									<label> 
										<input type="checkbox" class="ace ace-checkbox" id="drop-remove" /> 
										<span class="lbl">Remove after drop</span>
									</label>
									--%>
									
								</div>
							</div>
						</div>
					</div>
					</c:if>
					<button type='submit' class='btn btn-lg btn-success' id="btnSummary">Summary</button>
				</div>
			</div>

			<!-- PAGE CONTENT ENDS -->
		</div>
		<!-- /.col -->
	</div>
	<!-- /.row -->
</div>
<!-- /.page-content -->

<div class="modal fade" id="modalSummary" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true"
	style="overflow-x: hidden; overflow-y: hidden;">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">
					<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
				</button>
				<h4 class="modal-title" id="myModalLabel">Holiday Summary</h4>
			</div>
			<div class="modal-body">
				<table id="tblSummary" class="table table-striped table-bordered table-hover striped-table" style="width: 100%; table-layout: fixed; white-space: nowrap;">
					<thead>
						<tr>
							<th>Date</th>
							<th>Name</th>
						</tr>
					</thead>
				</table>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
			</div>
		</div>
	</div>
</div>

<script type="text/javascript">
	jQuery(function($) {

		var tblSummary = $('#tblSummary').dataTable({
		     columnDefs: [
		                  { type: 'date-dd-mmm-yyyy', targets: 0 }
		                ]
		             } );
		
		/* initialize the external events
		 -----------------------------------------------------------------*/

		$('#external-events div.external-event').each(function() {

			// create an Event Object (http://arshaw.com/fullcalendar/docs/event_data/Event_Object/)
			// it doesn't need to have a start or end
			var eventObject = {
				title : $.trim($(this).text())
			// use the element's text as the event title
			};

			// store the Event Object in the DOM element so we can get to it later
			$(this).data('eventObject', eventObject);

			// make the event draggable using jQuery UI
			$(this).draggable({
				zIndex : 999,
				revert : true, // will cause the event to go back to its
				revertDuration : 0
			//  original position after the drag
			});

		});

		/* initialize the calendar
		-----------------------------------------------------------------*/

		var date = new Date();
		var d = date.getDate();
		var m = date.getMonth();
		var y = date.getFullYear();

		var calendar = $('#calendar')
				.fullCalendar(
						{
							height: 650,
			
							events: ${HolidayEventVos},
							header : {
								left: 'today',
						        center: 'prev title next',
						        right: ''
							},
							
							weekends : false
							<c:if test="${loginEmployee.role.id == 3}">
							,editable : true,
							
							droppable : true, // this allows things to be dropped onto the calendar !!!
							drop : function(date, allDay) { // this function is called when something is dropped
								var okToAdd = true;
				                $('#calendar').fullCalendar('clientEvents', function (event) {
				                    if ( date.format('DD/MM/YYYY') == event.start.format('DD/MM/YYYY'))
				                        okToAdd = false;
				                });

				                if(okToAdd){
									// retrieve the dropped element's stored Event Object
									var originalEventObject = $(this).data('eventObject');
									var $extraEventClass = $(this).attr('data-class');
									// we need to copy it, so that multiple events don't have a reference to the same object
									var copiedEventObject = $.extend({},originalEventObject);
	
									// assign it the date that was reported
									copiedEventObject.start = date;
									copiedEventObject.allDay = allDay;
									if ($extraEventClass)
										copiedEventObject['className'] = [ $extraEventClass ];
	
									// render the event on the calendar
									// the last `true` argument determines if the event "sticks" (http://arshaw.com/fullcalendar/docs/event_rendering/renderEvent/)
									$('#calendar').fullCalendar('renderEvent',
											copiedEventObject, true);
	
									saveHoliday("",copiedEventObject.title ,copiedEventObject.start.format('DD/MM/YYYY'));
									// is the "remove after drop" checkbox checked?
									if ($('#drop-remove').is(':checked')) {
										// if so, remove the element from the "Draggable Events" list
										$(this).remove();
									}
				                }
							},
							
						    eventDrop: function(event, delta, revertFunc, jsEvent, ui, view) {
						    	var okToAdd = true;
						    	var countDup = 0;
				                $('#calendar').fullCalendar('clientEvents', function (clientEvent) {
				                    if ( event.start.format('DD/MM/YYYY') == clientEvent.start.format('DD/MM/YYYY'))
				                    	countDup++;
				                });
	
				                if(countDup < 2){
							    	var oldStart = moment(event.start);
							    	var oldStart = oldStart.subtract(delta.asDays(), 'day');
							    	saveHoliday(oldStart.format('DD/MM/YYYY') , event.title, event.start.format('DD/MM/YYYY') );
				                }else{
				                	revertFunc();
				                }
						    },
							eventClick : function(calEvent, jsEvent, view) {
								var form = $("<form class='form-inline'><label>Change holiday name &nbsp;</label></form>");
								form.append("<input class='middle' autocomplete=off type='text' value='" + (calEvent.title).replace(/'/g,'&#039;') + "' style='width:300px;'/> ");
								form.append("<button type='submit' class='btn btn-sm btn-success'><i class='icon-ok'></i> Save</button>");

								var div = bootbox
										.dialog({
											message : form,

											buttons : {
												"delete" : {
													"label" : "<i class='icon-trash'></i> Delete Holiday",
													"className" : "btn-sm btn-danger",
													"callback" : function() {
														saveHoliday(calEvent.start.format('DD/MM/YYYY'),"" ,"");
														
														calendar
																.fullCalendar(
																		'removeEvents',
																		function(
																				ev) {
																			return (ev._id == calEvent._id);
																		})
													}
												},
												"close" : {
													"label" : "<i class='icon-remove'></i> Close",
													"className" : "btn-sm"
												}
											}

										});

								form.on('submit', function() {
									calEvent.title = form.find( "input[type='text']").val();
									calendar.fullCalendar('updateEvent', calEvent);
									saveHoliday(calEvent.start.format('DD/MM/YYYY'),calEvent.title , calEvent.start.format('DD/MM/YYYY'));
									div.modal("hide");
									return false;
								});

								//console.log(calEvent.id);
								//console.log(jsEvent);
								//console.log(view);

								// change the border color just for fun
								//$(this).css('border-color', 'red');

							},
							dayClick: function(date, jsEvent, view) {
								var okToAdd = true;
				                $('#calendar').fullCalendar('clientEvents', function (event) {
				                    if ( date.format('DD/MM/YYYY') == event.start.format('DD/MM/YYYY'))
				                        okToAdd = false;
				                });

				                if(okToAdd){
				                	bootbox.prompt("New Holiday Name :", function(title) {
										if (title !== null) {
											calendar.fullCalendar('renderEvent',
												{
													title: title,
													start: date,
													allDay: true
												},
												true // make the event "stick"
											);
											
											saveHoliday("" , title, date.format('DD/MM/YYYY'));
										}	
									});
				                }	
							}	
							</c:if>
						});
		
		$( "#btnSummary" ).click(function() {
			var dataSet = new Array();
			var i = 0;
			$('#calendar').fullCalendar('clientEvents', function (event) {
				var data = [event.start.format('DD-MMM-YYYY') , event.title];
				dataSet[i++] = data;
            });
			
			tblSummary.fnClearTable();
			tblSummary.fnAddData(dataSet);
			tblSummary.fnDraw();
			
			$('#modalSummary').modal(
				{
					backdrop:'static'
				}
			);
		});
		
		$('#tblSummary tbody').on( 'click', 'tr', function () {
		    var data = tblSummary.fnGetData( this );
		    $('#calendar').fullCalendar( 'gotoDate', moment(data[0], 'DD-MMM-YYYY') );
		    $('#modalSummary').modal('hide');
		  } );
	})
	
	
	function saveHoliday(oldStart , newTitle, newStart){		
		var oJson = {
				oldStart : oldStart,
				newTitle : newTitle,  
				newStart : newStart
			};

		$.ajax({
			url : '${pageContext.request.contextPath}/holiday/save',
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
					
				}
			}
		});
	}
</script>
