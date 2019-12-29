<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Add User</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!-- Meta, title, CSS, favicons, etc. -->
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="icon" href="images/favicon.ico" type="image/ico" />
<title>ConstructionManagement! |</title>
<script src="/js/jquery-3.3.1.min.js"></script>
<!-- Bootstrap -->
<link href="/vendors/bootstrap/dist/css/bootstrap.min.css"
	rel="stylesheet">
<!-- Font Awesome -->
<link href="/css/font-awesome.min.css" rel="stylesheet">
<!-- NProgress -->
<link href="/css/nprogress.css" rel="stylesheet">
<!-- iCheck -->
<link href="/css/green.css" rel="stylesheet">

<!-- bootstrap-progressbar -->
<link href="/css/bootstrap-progressbar-3.3.4.min.css" rel="stylesheet">
<!-- JQVMap -->
<link href="/vendors/jqvmap/dist/jqvmap.min.css" rel="stylesheet" />
<!-- bootstrap-daterangepicker -->
<link href="/vendors/bootstrap-daterangepicker/daterangepicker.css"
	rel="stylesheet">

<!-- Custom Theme Style -->
<link href="/build/css/custom.min.css" rel="stylesheet">
<script>
    $(document).ready(function() {
    $("#userFormId").on("submit", function(e) {
		e.preventDefault();
		var formData = $('#userFormId').serializeArray();
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
			url : '/user/saveUser',
			data : JSON.stringify(data),
			contentType : "application/json",
			
			success : function(result) {
				var resultSting = JSON.stringify(result);
				var jsonResult = JSON.parse(resultSting);
				if(jsonResult.status){
				alert(jsonResult.message);
				window.location.href = "/user/showUser";
				
				}
			},
			error : function(result) {
				var resultSting = JSON.stringify(result);
				var jsonResult = JSON.parse(resultSting);
				alert(jsonResult.message);
				
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
								<h2>Add User</h2>
								<div class="clearfix"></div>
							</div>
							<div class="x_content">
								<br />
								<form id="userFormId" class="form-horizontal form-label-left">
									<div class="form-group row ">
										<label class="control-label col-md-3 col-sm-3 ">Full
											Name</label>
										<div class="col-md-6 col-sm-9 ">
											<input type="text" name="fullName" class="form-control"
												placeholder="Enter Full Name">
										</div>
									</div>
									<div class="form-group row ">
										<label class="control-label col-md-3 col-sm-3 ">User
											Name</label>
										<div class="col-md-6 col-sm-9 ">
											<input type="text" name="userName" class="form-control"
												placeholder="Enter User Name">
										</div>
									</div>
									<div class="form-group row ">
										<label class="control-label col-md-3 col-sm-3 ">Password</label>
										<div class="col-md-6 col-sm-9 ">
											<input type="password" name="password" class="form-control"
												placeholder="Enter Password">
										</div>
									</div>
									<div class="form-group row ">
										<label class="control-label col-md-3 col-sm-3 ">Confirm
											Password</label>
										<div class="col-md-6 col-sm-9 ">
											<input type="password" name="confirmPassword"
												class="form-control" placeholder="Enter Confirm Password">
										</div>
									</div>
									<div class="form-group row">
										<label class="control-label col-md-3 col-sm-3 ">Select
											Role</label>
										<div class="col-md-6 col-sm-9 ">
											<select name="role.roleId"
												class="select2_single form-control" tabindex="-1">
												<option value="-1">Select</option>
												<c:forEach items="${role}" var="role">
													<option value="${role.roleId}">${role.roleName}</option>
												</c:forEach>
											</select>

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
	<!-- jQuery -->
	<script src="/vendors/jquery/dist/jquery.min.js"></script>
	<!-- Bootstrap -->
	<script src="/vendors/bootstrap/dist/js/bootstrap.bundle.min.js"></script>
	<!-- FastClick -->
	<script src="/vendors/fastclick/lib/fastclick.js"></script>
	<!-- NProgress -->
	<script src="/vendors/nprogress/nprogress.js"></script>
	<!-- bootstrap-progressbar -->
	<script
		src="/vendors/bootstrap-progressbar/bootstrap-progressbar.min.js"></script>
	<!-- iCheck -->
	<script src="/vendors/iCheck/icheck.min.js"></script>
	<!-- Skycons -->
	<script src="/vendors/skycons/skycons.js"></script>
	<!-- Flot -->
	<script src="/vendors/Flot/jquery.flot.js"></script>
	<script src="/vendors/Flot/jquery.flot.pie.js"></script>
	<script src="/vendors/Flot/jquery.flot.time.js"></script>
	<script src="/vendors/Flot/jquery.flot.stack.js"></script>
	<script src="/vendors/Flot/jquery.flot.resize.js"></script>
	<!-- Flot plugins -->
	<script src="/vendors/flot.orderbars/js/jquery.flot.orderBars.js"></script>
	<script src="/vendors/flot-spline/js/jquery.flot.spline.min.js"></script>
	<script src="/vendors/flot.curvedlines/curvedLines.js"></script>
	<!-- DateJS -->
	<script src="/vendors/DateJS/build/date.js"></script>
	<!-- JQVMap -->
	<script src="/vendors/jqvmap/dist/jquery.vmap.js"></script>
	<script src="/vendors/jqvmap/dist/maps/jquery.vmap.world.js"></script>
	<script src="/vendors/jqvmap/examples/js/jquery.vmap.sampledata.js"></script>
	<!-- bootstrap-daterangepicker -->
	<script src="/vendors/moment/min/moment.min.js"></script>
	<script src="/vendors/bootstrap-daterangepicker/daterangepicker.js"></script>
	<!-- Chart.js -->
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