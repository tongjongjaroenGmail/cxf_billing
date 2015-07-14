<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<!DOCTYPE html>
<html lang="en">
<head>

<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<meta name="description" content="Static &amp; Dynamic Tables" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />

<title><tiles:insertAttribute name="title" ignore="true"></tiles:insertAttribute></title>
<link href="img/favicon.ico" rel="shortcut icon" type="image/vnd.microsoft.icon" />
<!-- basic styles -->
<link href="assets/css/bootstrap.min.css" rel="stylesheet" />
<link rel="stylesheet" href="assets/css/font-awesome.min.css" />

<!--[if IE 7]>
  <link rel="stylesheet" href="assets/css/font-awesome-ie7.min.css" />
<![endif]-->

<!-- page specific plugin styles -->
<link rel="stylesheet" href="assets/css/jquery-ui-1.10.3.full.min.css" />
<link rel="stylesheet" href="assets/css/fullcalendar.css" />
<link rel='stylesheet' href='assets/css/fullcalendar.print.css' media='print' />
<link rel="stylesheet" href="assets/css/daterangepicker.css" />
<link rel="stylesheet" href="assets/css/datepicker.css" />
<link rel="stylesheet" type="text/css" media="all" href="daterangepicker/daterangepicker-bs3.css" />
<link rel="stylesheet" href="assets/css/jquery-ui-1.10.3.full.min.css" />
<link rel="stylesheet" href="assets/css/ui.jqgrid.css" />

<link rel="stylesheet" href="assets/css/ace-fonts.css" />
<link rel="stylesheet" href="assets/css/ace.min.css" />
<link rel="stylesheet" href="assets/css/ace-rtl.min.css" />
<link rel="stylesheet" href="assets/css/ace-skins.min.css" />
<link rel="stylesheet" href="css/css.css" />
<link rel="stylesheet" href="css/dataTables.fixedColumns.min.css" />
<!--[if lte IE 8]>
  <link rel="stylesheet" href="assets/css/ace-ie.min.css" />
<![endif]-->

<!-- inline styles related to this page -->

<!-- ace settings handler -->
<script src="assets/js/ace-extra.min.js"></script>
<script src="assets/js/jquery-2.0.3.min.js"></script>
<script src="assets/js/jquery.dataTables.min.js"></script>
<script src="assets/js/jquery.dataTables.bootstrap.js"></script>
<script src="assets/js/jquery.mobile.custom.min.js"></script>
<script src="assets/js/bootstrap.min.js"></script>
<script src="assets/js/typeahead-bs2.min.js"></script>
<script src="assets/js/ace-elements.min.js"></script>
<script src="assets/js/ace.min.js"></script>

<script src="assets/js/jquery.dataTables.min.js"></script>
<script src="assets/js/jquery.dataTables.bootstrap.js"></script>

<script type="text/javascript" src="daterangepicker/moment.js"></script>
<script type="text/javascript" src="daterangepicker/daterangepicker.js"></script>

<script src="assets/js/jquery-ui-1.10.3.custom.min.js"></script>
<script src="assets/js/jquery.ui.touch-punch.min.js"></script>
<script src="assets/js/chosen.jquery.min.js"></script>
<script src="assets/js/fuelux/fuelux.spinner.min.js"></script>
<%-- 
<script src="assets/js/date-time/bootstrap-datepicker.min.js"></script>
--%>
<script type="text/javascript" src="js/bootstrap-datepicker-thai-thai/js/bootstrap-datepicker.js"></script>
<script type="text/javascript" src="js/bootstrap-datepicker-thai-thai/js/bootstrap-datepicker-thai.js"></script>
<script type="text/javascript" src="js/bootstrap-datepicker-thai-thai/js/locales/bootstrap-datepicker.th.js"></script>

<script src="assets/js/date-time/bootstrap-timepicker.min.js"></script>
<script src="assets/js/bootstrap-colorpicker.min.js"></script>
<script src="assets/js/jquery.knob.min.js"></script>
<script src="assets/js/jquery.autosize.min.js"></script>
<script src="assets/js/jquery.inputlimiter.1.3.1.min.js"></script>
<script src="assets/js/jquery.maskedinput.min.js"></script>
<script src="assets/js/bootstrap-tag.min.js"></script>

<script src="assets/js/jqGrid/jquery.jqGrid.min.js"></script>
<script src="assets/js/jqGrid/i18n/grid.locale-en.js"></script>

<script src="assets/js/fullcalendar.min.js"></script>
<script src="assets/js/bootbox.min.js"></script>
<script src="js/date-dd-MMM-yyyy.js"></script>
<script src="js/dataTables.fixedColumns.js"></script>
<script src="js/fnStandingRedraw.js"></script>
<!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->

<!--[if lt IE 9]>
<script src="assets/js/html5shiv.js"></script>
<script src="assets/js/respond.min.js"></script>
<![endif]-->
</head>

<body>

	<tiles:insertAttribute name="header"></tiles:insertAttribute>

	<div class="main-container" id="main-container">
		<script type="text/javascript">
				try{ace.settings.check('main-container' , 'fixed')}catch(e){}
			</script>

		<div class="main-container-inner">
			<tiles:insertAttribute name="menu"></tiles:insertAttribute>

			<div class="main-content">
				<div class="breadcrumbs" id="breadcrumbs">
					<script type="text/javascript">
						try{ace.settings.check('breadcrumbs' , 'fixed')}catch(e){}
					</script>

					<ul class="breadcrumb">
						<li><i class="icon-home home-icon"></i> <a href="#">Home</a></li>
						<li class="active"><tiles:insertAttribute name="title" ignore="true"></tiles:insertAttribute></li>
					</ul>
					<!-- .breadcrumb -->
				</div>

				<tiles:insertAttribute name="body"></tiles:insertAttribute>
			</div>
			<!-- /.main-content -->
		</div>
		<!-- /.main-container-inner -->

		<a href="#" id="btn-scroll-up" class="btn-scroll-up btn btn-sm btn-inverse"> <i
			class="icon-double-angle-up icon-only bigger-110"></i>
		</a>
	</div>

	<!-- /.main-container -->

		<!-- basic scripts -->

		<!--[if !IE]> -->

		<script type="text/javascript">
			window.jQuery || document.write("<script src='assets/js/jquery-2.0.3.min.js'>"+"<"+"/script>");
		</script>

		<!-- <![endif]-->

		<!--[if IE]>
<script type="text/javascript">
 window.jQuery || document.write("<script src='assets/js/jquery-1.10.2.min.js'>"+"<"+"/script>");
</script>
<![endif]-->

		<script type="text/javascript">
			if("ontouchend" in document) document.write("<script src='assets/js/jquery.mobile.custom.min.js'>"+"<"+"/script>");
		</script>

	<!-- inline scripts related to this page -->
	<script type="text/javascript" src="js/jquery.blockUI.js"></script>
	<script type="text/javascript">
	
		// block when ajax activity start 
	    $(document).ajaxStart(function() {
	    	 $.blockUI({ css: { 
		            border: 'none', 
		            padding: '15px', 
		            backgroundColor: '#000', 
		            '-webkit-border-radius': '10px', 
		            '-moz-border-radius': '10px', 
		            opacity: .5, 
		            color: '#fff' 
		        } }); 
	   	});
	    $(document).ajaxStop($.unblockUI); 
	    
	    $( document ).ajaxError(function( event, jqxhr, settings, thrownError ) {
	    	if(jqxhr.status == 901){
	        	alert("Your session time out.");
	        	window.location = "${pageContext.request.contextPath}/j_spring_security_logout";
	        }else{
	        	alert("Error. Please contact Administrator.");
	        }
    	});

		jQuery(function($) {
			$('table th input:checkbox').on('click' , function(){
				var that = this;
				$(this).closest('table').find('tr > td:first-child input:checkbox')
				.each(function(){
					this.checked = that.checked;
					$(this).closest('tr').toggleClass('selected');
				});
					
			});
		
			$('[data-rel="tooltip"]').tooltip({placement: tooltip_placement});
			function tooltip_placement(context, source) {
				var $source = $(source);
				var $parent = $source.closest('table')
				var off1 = $parent.offset();
				var w1 = $parent.width();
		
				var off2 = $source.offset();
				var w2 = $source.width();
		
				if( parseInt(off2.left) < parseInt(off1.left) + parseInt(w1 / 2) ) return 'right';
				return 'left';
			}
		})
		
		var delay = (function(){
			  var timer = 0;
			  return function(callback, ms){
			    clearTimeout (timer);
			    timer = setTimeout(callback, ms);
			  };
			})();
	</script>
</body>
</html>
