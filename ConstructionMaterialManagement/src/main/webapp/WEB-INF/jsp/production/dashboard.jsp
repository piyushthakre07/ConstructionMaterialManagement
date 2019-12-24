<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta charset="ISO-8859-1">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!-- Meta, title, CSS, favicons, etc. -->
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="icon" href="images/favicon.ico" type="image/ico" />

<title>Dashboard! |</title>
<script src="/js/jquery-3.3.1.min.js"></script>
<!-- Bootstrap -->
<link href="/vendors/bootstrap/dist/css/bootstrap.min.css"
	rel="stylesheet">
<link href="/css/daterangepicker.css"
	rel="stylesheet">
<!-- Custom Theme Style -->
<link href="/build/css/custom.min.css" rel="stylesheet">
<script>
    $(document).ready(function() {
    	getAllItems();

    });
    function  getAllItems()
    		{
    	    	 
    	 $('#itemId').html('');
    	    		      $.ajax({
    	    		             url:'/item/getAllItems',
    	    		             type:'GET',
    	    		             dataType:'json',
    	    		             contentType: "application/json",
    	    		             success: function (data) {
    	    		            	
    	    		            	 $('#itemId').append('<option value="-1">Select</option>');
    	    		            	 $.each(data,function(key,value){
    	    		                          $('#itemId').append('<option value="'+value.itemId+'">'+value.itemName+'</option>');
    	    		                    });
    	    		             },
    	    		             error:
    	    		                function (data) {
    	    		                   alert(JSON.stringify(data));
    	    		             }
    	    		       });
    	    		
    	
    		}
 
    
   
    </script>
</head>

<body class="nav-md">
	<div class="container body">
		<div class="main_container">
			<!-- Header File -->
			<jsp:include page="header.jsp" />
			<!-- End Header File -->

			<!-- page content -->
			<div class="right_col" role="main">
				<!-- top tiles -->
				<div class="">
					<div class="tile_count">
						<div class="col-md-3 col-sm-4  tile_stats_count">
							<span class="count_top"><i class=""></i> Total Contractor</span>
							<div class="count">
								<a href="/contractor/showContractor">${totalCount.contractorTtl}</a>
							</div>
						</div>
						<div class="col-md-3 col-sm-4  tile_stats_count">
							<span class="count_top"><i class=""></i> Total Vendor</span>
							<div class="count">
								<a href="/vendor/showVendor">${totalCount.vendorTtl}</a>
							</div>


						</div>
						<div class="col-md-3 col-sm-4  tile_stats_count">
							<span class="count_top"><i class=""></i> Total Users</span>
							<div class="count green">
								<a href="/user/showUser">${totalCount.userTtl}</a>
							</div>

						</div>
						<div class="col-md-3 col-sm-4  tile_stats_count">
							<span class="count_top"><i class=""></i> Total Sites</span>
							<div class="count">
								<a href="/sites/showSite">${totalCount.sitesTtl}</a>
							</div>
						</div>
					</div>
				</div>
				<!-- /top tiles -->


				<br />

				<div class="row">
					<div class="col-md-6 col-sm-4  ">
						<div class="x_panel">
							<div class="x_title">
								<h2>Items In Stock</h2>
								<div class="clearfix"></div>
							</div>
							<div class="x_content">

								<div id="echart_pie" style="height: 350px;"></div>

							</div>
						</div>
					</div>

					<div class="col-md-6 col-sm-4  ">
						<div class="x_panel">
							<div class="x_title">
								<h2>Item Stock</h2>

								<div class="clearfix"></div>
							</div>
							<div class="x_content">
								<!-- <div class="form-group row">
										<label class="control-label col-md-3 col-sm-3 ">Select
											Items</label>
										<div class="col-md-6 col-sm-9 ">
											<select name="item.itemId" id="itemId" class="select2_single form-control"
												tabindex="-1">
												<option value="-1">Select</option>
											</select>

										</div>
									</div> -->
								<div id="echart_gauge" style="height: 370px;"></div>
							</div>
						</div>
					</div>

				</div>

				<!-- footer content -->
				<footer>
					<div class="pull-right"></div>
					<div class="clearfix"></div>
				</footer>
				<!-- /footer content -->
			</div>
		</div>
	</div>
		<!-- Custom Theme Scripts -->
	
	<script src="/vendors/bootstrap/dist/js/bootstrap.bundle.min.js"></script>
	
	<!-- Chart.js -->
	<script src="/build/js/Chart.min.js"></script>
	<!-- gauge.js -->
	<script src="/build/js/gauge.min.js"></script>
	<!-- ECharts -->
	<script src="/build/js/echarts.min.js"></script>
	<script src="/vendors/echarts/map/js/world.js"></script>
	<script src="/js/moment.js"></script>
	<script src="/build/js/custom.js"></script>
    <script src="/js/daterangepicker.js"></script>
</body>
</html>