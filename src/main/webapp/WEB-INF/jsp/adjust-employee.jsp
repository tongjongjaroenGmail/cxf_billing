<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<div class="page-content">
	<div class="page-header">
		<h1>Adjust Employee</h1>
	</div>
	<!-- /.page-header -->

	<div class="row">
		<div class="col-xs-12">
			<!-- PAGE CONTENT BEGINS -->

			<table id="grid-table"></table>

			<div id="grid-pager"></div>

			<script type="text/javascript">
				var $path_base = "/";//this will be used in gritter alerts containing images
			</script>

			<!-- PAGE CONTENT ENDS -->
		</div>
		<!-- /.col -->
	</div>
	<!-- /.row -->
</div>
<!-- /.page-content -->

<script type="text/javascript">
	jQuery(function($) {
		var grid_selector = "#grid-table";
		var pager_selector = "#grid-pager";
		var actionresponse;
		var hasEditButton = ${loginEmployee.role.id == 3};
		
		var objRole = ":-;<c:forEach var="role" items="${roles}" varStatus="counter">${role.id}:${role.name};</c:forEach>";
		objRole = objRole.substring(0,(objRole.length - 1));
		
		var objWorkingPolicy = ":-;<c:forEach var="workingPolicy" items="${workingPolicys}" varStatus="counter">${workingPolicy.id}:${workingPolicy.name};</c:forEach>";
		objWorkingPolicy = objWorkingPolicy.substring(0,(objWorkingPolicy.length - 1));
		
		var objDepartment = ":-;<c:forEach var="department" items="${departments}" varStatus="counter">${department.id}:${department.name};</c:forEach>";
		objDepartment = objDepartment.substring(0,(objDepartment.length - 1));

		jQuery(grid_selector).jqGrid({
			datatype : 'local',
			height : 450,
			colNames : [ ' ', 'Name', 'Department','Role', 'Working Policy','' ],
			colModel : [
					{
						name : 'myac',
						index : '',
						width : 80,
						fixed : true,
						sortable : false,
						resize : false,
						search : false,
						formatter : 'actions',
						formatoptions : {
							keys : false,
							delbutton : false,
							editbutton : hasEditButton,
							onSuccess : function(jqXHR) {
								actionresponse = jqXHR;
								return true;
							},
							afterSave : function(rowID) {
								if (JSON.parse(actionresponse.responseText).message != "") {
									alert(JSON.parse(actionresponse.responseText).message);
								}
							}
						}
					}, {
						name : 'employeeName',
						index : 'employeeName',
						width : 200,
						searchoptions:{sopt:['cn','bw','eq','bn','nc','ew','en']},
						editable : false
					}, {
						name : 'departmentId',
						index : 'departmentId',
						width : 150,
						formatter: 'select', 
						search : true,
						stype:"select",
						editable : false,
						edittype:'select',
						editoptions : {
							value: objDepartment
						}
					}, {
						name : 'roleId',
						index : 'roleId',
						width : 150,
						formatter: 'select', 
						search : true,
						stype:"select",
						editable : true,
						edittype:'select',
						editoptions : {
							value: objRole
						},
						editrules:{
					        required:true
					    }
					}, {
						name : 'workingPolicyId',
						index : 'workingPolicyId',
						width : 150,
						formatter: 'select', 
						search : true,
						stype:"select",
						editable : true,
						edittype:'select',
						editoptions : {
							value: objWorkingPolicy
						},
						editrules:{
					        required:true
					    }
					}, {
						name : 'employeeId',
						index : 'employeeId',
						width : 100,
						search : false,
						editable : true,
						editrules : {
							required : true,
							edithidden : true
						},
						hidden : true,
						editoptions : {
							readonly : 'readonly'
						}
					} ],
			rownumbers : true, // show row numbers
			viewrecords : true,
			rowNum : 15,
			rowList : [ 15, 20, 30 ,100],
			pager : pager_selector,
			altRows : true,

			multiselect : false,
			multiboxonly : true,

			loadComplete : function() {
				var table = this;
				setTimeout(function() {
					styleCheckbox(table);

					updateActionIcons(table);
					updatePagerIcons(table);
					enableTooltips(table);
				}, 0);
			},
			editurl : '${pageContext.request.contextPath}/adjust-employee/update',
			autowidth : true
		});

		fetchGridData();

		function fetchGridData() {
			var gridArrayData = [];
			// show loading message
			$(grid_selector)[0].grid.beginReq();
			$
					.ajax({
						url : '${pageContext.request.contextPath}/adjust-employee/adjustEmployeeList',
						contentType : 'application/json',
						mimeType : 'application/json',
						success : function(result) {
							for ( var i = 0; i < result.length; i++) {
								var item = result[i];

								gridArrayData.push({
									employeeId : item.employeeId,
									employeeName : item.employeeName,
									departmentId : item.departmentId,
									roleId : item.roleId,
									roleName : item.roleName,
									workingPolicyId : item.workingPolicyId,
									workingPolicyName : item.workingPolicyName,
								});
							}
							// set the new data
							$(grid_selector).jqGrid('setGridParam', {
								data : gridArrayData
							});
							// hide the show message
							$(grid_selector)[0].grid.endReq();
							// refresh the grid
							$(grid_selector).trigger('reloadGrid');
						}
					});
		}

		//switch element when editing inline
		function aceSwitch(cellvalue, options, cell) {
			setTimeout(function() {
				$(cell).find('input[type=checkbox]').wrap(
						'<label class="inline" />').addClass(
						'ace ace-switch ace-switch-5').after(
						'<span class="lbl"></span>');
			}, 0);
		}
		//enable datepicker
		function pickDate(cellvalue, options, cell) {
			setTimeout(function() {
				$(cell).find('input[type=text]').datepicker({
					format : 'yyyy-mm-dd',
					autoclose : true
				});
			}, 0);
		}

		//navButtons
		jQuery(grid_selector).jqGrid(
				'navGrid',
				pager_selector,
				{ //navbar options
					edit : false,
					editicon : 'icon-pencil blue',
					add : false,
					addicon : 'icon-plus-sign purple',
					del : false,
					delicon : 'icon-trash red',
					search : false,
					searchicon : 'icon-search orange',
					refresh : false,
					refreshicon : 'icon-refresh green',
					view : false,
					viewicon : 'icon-zoom-in grey',
				},
				{
					//edit record form
					//closeAfterEdit: true,
					recreateForm : true,
					beforeShowForm : function(e) {
						var form = $(e[0]);
						form.closest('.ui-jqdialog').find(
								'.ui-jqdialog-titlebar').wrapInner(
								'<div class="widget-header" />')
						style_edit_form(form);
					}
				},
				{
					//new record form
					closeAfterAdd : true,
					recreateForm : true,
					viewPagerButtons : false,
					beforeShowForm : function(e) {
						var form = $(e[0]);
						form.closest('.ui-jqdialog').find(
								'.ui-jqdialog-titlebar').wrapInner(
								'<div class="widget-header" />')
						style_edit_form(form);
					}
				},
				{
					//delete record form
					recreateForm : true,
					beforeShowForm : function(e) {
						var form = $(e[0]);
						if (form.data('styled'))
							return false;

						form.closest('.ui-jqdialog').find(
								'.ui-jqdialog-titlebar').wrapInner(
								'<div class="widget-header" />')
						style_delete_form(form);

						form.data('styled', true);
					},
					onClick : function(e) {
						alert(1);
					}
				},
				{
					//search form
					recreateForm : true,
					afterShowSearch : function(e) {
						var form = $(e[0]);
						form.closest('.ui-jqdialog').find('.ui-jqdialog-title')
								.wrap('<div class="widget-header" />')
						style_search_form(form);
					},
					afterRedraw : function() {
						style_search_filters($(this));
					},
					multipleSearch : true,
				/**
				multipleGroup:true,
				showQuery: true
				 */
				},
				{
					//view record form
					recreateForm : true,
					beforeShowForm : function(e) {
						var form = $(e[0]);
						form.closest('.ui-jqdialog').find('.ui-jqdialog-title')
								.wrap('<div class="widget-header" />')
					}
				})

		function style_edit_form(form) {
			//enable datepicker on "sdate" field and switches for "stock" field
			form.find('input[name=sdate]').datepicker({
				format : 'yyyy-mm-dd',
				autoclose : true
			}).end().find('input[name=stock]').addClass(
					'ace ace-switch ace-switch-5').wrap(
					'<label class="inline" />').after(
					'<span class="lbl"></span>');

			//update buttons classes
			var buttons = form.next().find('.EditButton .fm-button');
			buttons.addClass('btn btn-sm').find('[class*="-icon"]').remove();//ui-icon, s-icon
			buttons.eq(0).addClass('btn-primary').prepend(
					'<i class="icon-ok"></i>');
			buttons.eq(1).prepend('<i class="icon-remove"></i>')

			buttons = form.next().find('.navButton a');
			buttons.find('.ui-icon').remove();
			buttons.eq(0).append('<i class="icon-chevron-left"></i>');
			buttons.eq(1).append('<i class="icon-chevron-right"></i>');
		}

		function style_delete_form(form) {
			var buttons = form.next().find('.EditButton .fm-button');
			buttons.addClass('btn btn-sm').find('[class*="-icon"]').remove();//ui-icon, s-icon
			buttons.eq(0).addClass('btn-danger').prepend(
					'<i class="icon-trash"></i>');
			buttons.eq(1).prepend('<i class="icon-remove"></i>')
		}

		function style_search_filters(form) {
			form.find('.delete-rule').val('X');
			form.find('.add-rule').addClass('btn btn-xs btn-primary');
			form.find('.add-group').addClass('btn btn-xs btn-success');
			form.find('.delete-group').addClass('btn btn-xs btn-danger');
		}
		function style_search_form(form) {
			var dialog = form.closest('.ui-jqdialog');
			var buttons = dialog.find('.EditTable')
			buttons.find('.EditButton a[id*="_reset"]').addClass(
					'btn btn-sm btn-info').find('.ui-icon').attr('class',
					'icon-retweet');
			buttons.find('.EditButton a[id*="_query"]').addClass(
					'btn btn-sm btn-inverse').find('.ui-icon').attr('class',
					'icon-comment-alt');
			buttons.find('.EditButton a[id*="_search"]').addClass(
					'btn btn-sm btn-purple').find('.ui-icon').attr('class',
					'icon-search');
		}

		function beforeDeleteCallback(e) {
			var form = $(e[0]);
			if (form.data('styled'))
				return false;

			form.closest('.ui-jqdialog').find('.ui-jqdialog-titlebar')
					.wrapInner('<div class="widget-header" />')
			style_delete_form(form);

			form.data('styled', true);
		}

		function beforeEditCallback(e) {
			var form = $(e[0]);
			form.closest('.ui-jqdialog').find('.ui-jqdialog-titlebar')
					.wrapInner('<div class="widget-header" />')
			style_edit_form(form);
		}

		//it causes some flicker when reloading or navigating grid
		//it may be possible to have some custom formatter to do this as the grid is being created to prevent this
		//or go back to default browser checkbox styles for the grid
		function styleCheckbox(table) {
			/**
				$(table).find('input:checkbox').addClass('ace')
				.wrap('<label />')
				.after('<span class="lbl align-top" />')
			
			
				$('.ui-jqgrid-labels th[id*="_cb"]:first-child')
				.find('input.cbox[type=checkbox]').addClass('ace')
				.wrap('<label />').after('<span class="lbl align-top" />');
			 */
		}

		//unlike navButtons icons, action icons in rows seem to be hard-coded
		//you can change them like this in here if you want
		function updateActionIcons(table) {
			/**
			var replacement = 
			{
				'ui-icon-pencil' : 'icon-pencil blue',
				'ui-icon-trash' : 'icon-trash red',
				'ui-icon-disk' : 'icon-ok green',
				'ui-icon-cancel' : 'icon-remove red'
			};
			$(table).find('.ui-pg-div span.ui-icon').each(function(){
				var icon = $(this);
				var $class = $.trim(icon.attr('class').replace('ui-icon', ''));
				if($class in replacement) icon.attr('class', 'ui-icon '+replacement[$class]);
			})
			 */
		}

		//replace icons with FontAwesome icons like above
		function updatePagerIcons(table) {
			var replacement = {
				'ui-icon-seek-first' : 'icon-double-angle-left bigger-140',
				'ui-icon-seek-prev' : 'icon-angle-left bigger-140',
				'ui-icon-seek-next' : 'icon-angle-right bigger-140',
				'ui-icon-seek-end' : 'icon-double-angle-right bigger-140'
			};
			$(
					'.ui-pg-table:not(.navtable) > tbody > tr > .ui-pg-button > .ui-icon')
					.each(
							function() {
								var icon = $(this);
								var $class = $.trim(icon.attr('class').replace(
										'ui-icon', ''));

								if ($class in replacement)
									icon.attr('class', 'ui-icon '
											+ replacement[$class]);
							})
		}

		function enableTooltips(table) {
			$('.navtable .ui-pg-button').tooltip({
				container : 'body'
			});
			$(table).find('.ui-pg-div').tooltip({
				container : 'body'
			});
		}

		//var selr = jQuery(grid_selector).jqGrid('getGridParam','selrow');

		jQuery(grid_selector).jqGrid('filterToolbar',{searchOperators : true});
	});
</script>

