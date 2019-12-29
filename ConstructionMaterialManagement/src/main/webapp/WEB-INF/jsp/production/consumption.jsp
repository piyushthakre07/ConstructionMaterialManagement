<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>

<head>
<meta charset="ISO-8859-1">
<title>consumption</title>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!-- Meta, title, CSS, favicons, etc. -->
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="icon" href="images/favicon.ico" type="image/ico" />
<script src="/js/jquery-3.3.1.min.js"></script>
<link href="/css/font-awesome.min.css" rel="stylesheet">
<link href="/css/nprogress.css" rel="stylesheet">
<link href="/css/green.css" rel="stylesheet">
<link href="/css/bootstrap-progressbar-3.3.4.min.css" rel="stylesheet">
 <link href="/css/daterangepicker.css" rel="stylesheet">
<link href="/build/css/custom.min.css" rel="stylesheet">
<script>
    $(document).ready(function() {
    	
    $("#consumptionFormId").on("submit", function(e) {
		e.preventDefault();
		var formData = $('#consumptionFormId').serializeArray();
		var s = '';
		var data = {};
		for (s in formData) {
			var fieldName=formData[s]['name'];
			if(fieldName.indexOf(".") != -1){
			var innerObjectJson={};
			innerObjectJson[fieldName.split(".")[1]]=formData[s]['value'];
				data[fieldName.split(".")[0]]=innerObjectJson;
		}else{
			 data[formData[s]['name']] = formData[s]['value']
		}
		}
		$.ajax({
			type : 'POST',
			url : '/consumption/saveMaterialConsumption',
			data : JSON.stringify(data),
			contentType : "application/json",
			
			success : function(result) {
				var resultSting = JSON.stringify(result);
				var jsonResult = JSON.parse(resultSting);
				if(jsonResult.status){
				alert(jsonResult.message);
				window.location.href = "/consumption/viewConsumption";
				
				}
			},
			error : function(result) {
				var resultSting = JSON.stringify(result);
				var jsonResult = JSON.parse(resultSting);
				alert(jsonResult.message);
				
			}
		});
		
	});
    
    $('#contractorId').on('change',function()
    		{
    		      var selectedContractor=$(this).find('option:selected').val();
    		      $.ajax({
    		             url:'/sites/getSitesByConstractorId/'+selectedContractor,
    		             type:'GET',
    		             dataType:'json',
    		             contentType: "application/json",
    		             success: function (data) {
    		            	 $('#sitesId').html('');
    		            	 $('#sitesId').append('<option value="-1">Select</option>');
    		            	 $.each(data,function(key,value){
    		                          $('#sitesId').append('<option value="'+value.siteId+'">'+value.siteName+'</option>');
    		                    });
    		             },
    		             error:
    		                function (data) {
    		                   
    		             }
    		       });
    		});
    
    $('#materialCategoryId').on('change',function()
    		{
    	 $('#openingId').html('');
    		      var selectedMaterialCategoryId=$(this).find('option:selected').val();
    		      $.ajax({
    		             url:'/item/getItemsByMaterialCategoryId/'+selectedMaterialCategoryId,
    		             type:'GET',
    		             dataType:'json',
    		             contentType: "application/json",
    		             success: function (data) {
    		            	 $('#itemId').html('');
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
    		});
    
 
    $('#itemId').on('change',function()
     {
    	 $('#openingId').html('');
    		      var selectedItemId=$(this).find('option:selected').val();
    		      $.ajax({
    		             url:'/stock/getStockDetailsByItemId/'+selectedItemId,
    		             type:'GET',
    		             dataType:'json',
    		             contentType: "application/json",
    		             success: function (data) {
    		            	 
    		              if (typeof  data.itemQuantity === 'undefined')
    		              {
    		            	  $('#openingId').html('');
    		              }
    		               else
    		            	 $('#openingId').html(data.itemQuantity+" "+data.unitName);
    		            	
    		             },
    		             error:
    		                function (data) {
    		                  
    		             }
    		       });
    		});
    });
 
   
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

				<br />

				<div class="row">

					<div class="col-md-12 ">
						<div class="x_panel">
							<div class="x_title">
								<h2>
									Consumption <small>Item</small>
								</h2>
								<div class="clearfix"></div>
							</div>
							<div class="x_content">
								<br />
								<form id="consumptionFormId"
									class="form-horizontal form-label-left">
                                     <div class="form-group row ">
										<label class="control-label col-md-3 col-sm-3 ">Date</label>
										<div class="col-md-6 col-sm-9 ">
											<input type="date" name="consumptionDate" id="consumptionDate" class="form-control">
										</div>
									</div>
									
									<div class="form-group row">
										<label class="control-label col-md-3 col-sm-3 ">Select
											Contractor</label>
										<div class="col-md-6 col-sm-9 ">

											<select name="contractor.contractorId" id="contractorId"
												class="select2_single form-control" tabindex="-1">
												<option value="-1">Select</option>
												<c:forEach items="${contractors}" var="contractor">
													<option value="${contractor.contractorId}">${contractor.contractorName}</option>
												</c:forEach>
											</select>
										</div>
									</div>
									<div class="form-group row">
										<label class="control-label col-md-3 col-sm-3 ">Select
											Sites</label>
										<div class="col-md-6 col-sm-9 ">
											<select name="sites.siteId" id="sitesId" class="select2_single form-control"
												tabindex="-1">
												<option value="-1">Select</option>
											</select>

										</div>
									</div>
									
									<div class="form-group row">
										<label class="control-label col-md-3 col-sm-3 ">Select
											Material Category</label>
										<div class="col-md-6 col-sm-9 ">

											<select name="materialCategory.materialCategoryId" id="materialCategoryId"
												class="select2_single form-control" tabindex="-1">
												<option value="-1">Select</option>
												<c:forEach items="${materialCategories}" var="materialCategory">
													<option value="${materialCategory.materialCategoryId}">${materialCategory.materialCategoryName}</option>
												</c:forEach>
											</select>
										</div>
									</div>
										<div class="form-group row">
										<label class="control-label col-md-3 col-sm-3 ">Select
											Items</label>
										<div class="col-md-6 col-sm-9 ">
											<select name="item.itemId" id="itemId" class="select2_single form-control"
												tabindex="-1">
												<option value="-1">Select</option>
											</select>

										</div>
									</div>
									<div class="form-group row ">
										<label class="control-label col-md-3 col-sm-3 ">Opening</label>
										<div class="col-md-6 col-sm-9 " id="openingId">
											
										</div>
									</div>
									<div class="form-group row">
										<label class="control-label col-md-3 col-sm-3 ">Select
											Approve By</label>
										<div class="col-md-6 col-sm-9 ">

											<select name="approveBy.userId" id="userId"
												class="select2_single form-control" tabindex="-1">
												<option value="-1">Select</option>
												<c:forEach items="${users}" var="user">
													<option value="${user.userId}">${user.fullName}</option>
												</c:forEach>
											</select>
										</div>
									</div>
									<div class="form-group row ">
										<label class="control-label col-md-3 col-sm-3 ">Quantity</label>
										<div class="col-md-6 col-sm-9 ">
											<input type="number" name="consumptionQuantity" class="form-control"
												placeholder="Enter Quantity">
										</div>
									</div>
									<div class="form-group row ">
										<label class="control-label col-md-3 col-sm-3 ">Type Of Work</label>
										<div class="col-md-6 col-sm-9 ">
											<input type="text" name="workType" id="workType" class="form-control">
										</div>
									</div>
									
									<div class="form-group row ">
										<label class="control-label col-md-3 col-sm-3 ">Remark</label>
										<div class="col-md-6 col-sm-9 ">
											<textarea name="remark"></textarea>
										</div>
									</div>
									<div class="ln_solid"></div>
									<div class="form-group">
										<div class="col-md-6 col-sm-9  offset-md-3">
											<button type="button" class="btn btn-primary">Cancel</button>
											<button type="reset" class="btn btn-primary">Reset</button>
											<button type="submit" class="btn btn-success">Submit</button>
										</div>
									</div>

								</form>
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

	<!-- Bootstrap -->
	<script src="/vendors/bootstrap/dist/js/bootstrap.bundle.min.js"></script>
	<script src="/vendors/jqvmap/dist/maps/jquery.vmap.world.js"></script>
	<script src="/vendors/jqvmap/examples/js/jquery.vmap.sampledata.js"></script>
	<script src="/build/js/Chart.min.js"></script>
	<!-- gauge.js -->
	<script src="/build/js/gauge.min.js"></script>
	<!-- ECharts -->
	<script src="/build/js/echarts.min.js"></script>
	<script src="/vendors/echarts/map/js/world.js"></script>

	<!-- Custom Theme Scripts -->
	<script src="/build/js/custom.js"></script>


</body>
</html>