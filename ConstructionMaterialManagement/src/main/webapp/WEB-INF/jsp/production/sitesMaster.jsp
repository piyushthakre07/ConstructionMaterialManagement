<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!-- Meta, title, CSS, favicons, etc. -->
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="icon" href="images/favicon.ico" type="image/ico" />

<title>ConstructionManagement! |</title>

<script src="/js/jquery-3.3.1.min.js"></script>
<script src="/js/jquery-ui-1.12.1/jquery-ui.min.js"></script>
<script>
$(document).ready(function () {
	
	 var objectDataSites=[];
	 firstFunction();
	function firstFunction(){
	 $.ajax({
        type: 'GET',
        contentType: "application/json; charset=utf-8",
        url: '/contractor/getAllContractor',
        dataType: "json",
	}).done(function(data) {
		 $.each(data,function(key,value){
        	 var objectData={};
    		 objectData['Name']=value.contractorName;
    		 objectData['Id']=value.contractorId;
    		 objectDataSites.push(objectData);
    		
        	 });
		 secondFunction();
	});
	}
	function secondFunction(){
	$("#sitesTableContainer").jsGrid({
	       width: "100%",
	       filtering: true,
	       editing: true,
	       sorting: true,
	       paging: true,
	       autoload: true,
	       inserting: true,
	       deleting:true,
	       pageSize: 5,
	       pageButtonCount: 5,
	       controller: {
	           loadData: function(filter) {
	           	var d = $.Deferred();
	               $.ajax({
	                   type: 'GET',
	                   contentType: "application/json; charset=utf-8",
	                   url: '/sites/getAllSites',
	                   dataType: "json",
	                   data:filter,
	                     success: function (data) {
	                     	var data1 = $.grep(data, function(data) {
	                            return (!filter.siteName || (data.siteName+'').toUpperCase().indexOf(filter.siteName.toUpperCase()) > -1)
	                       });   
                   	d.resolve(data1);
	                   },
	                   error: function(e) {
	                       alert("error: " + e.responseText);
	                   } 
	                  
	               });
	                return d.promise();
	           },
	         insertItem: function(insertingClient) {
	    	       $.ajax({
	    	                   type: 'POST',
	    	                   url: '/sites/saveSite',
	    	                   dataType: "json",
	    	                   contentType: 'application/json',
	    	                   data: JSON.stringify(insertingClient),
	    	                   success: function (data) {
	    	                   	alert("insertion done successfully");
	    	                   	window.location.reload();
	    	                   }
	    	   	});
	    	       
	    	   },

	    	   updateItem: function(updatingClient) {
	    	   	$.ajax({
	    	                   type: 'PUT',
	    	                   url: '/sites/updateSite',
	    	                   dataType: "json",
	    	                   contentType: 'application/json',
	    	                   data: JSON.stringify(updatingClient),
	    	                   success: function (data) {
	    	                   	alert("updation done successfully");
	    	                 	window.location.reload();
	    	                   }
	    	   	});  
	    	  
	    	   },

	    	   deleteItem: function(deletingClient) {
	    	   	$.ajax({
	    	                   type: 'DELETE',
	    	                   url: '/sites/deleteSite/'+deletingClient.siteId,
	    	                   dataType: "json",
	    	                   contentType: 'application/json',
	    	                   success: function (data) {
	    	                   	alert("deletion done successfully");
	    	                   	window.location.reload();
	    	                   }
	    	   	});  
	    	   }
	    	  
	       },
	   
	       fields: [
	    	       { name: "siteId", type: "hidden",  width:75, title: "Site Id"},
		           { name: "siteName", type: "text",  width:75, title: "Site Name"},
		           { name: "siteAddress", type: "text",  width:75, title: "Site Address"},
		          { name: "contractor.contractorId", type: "select", items: objectDataSites, valueField: "Id",textField: "Name",  width:75, title: "Contractor"},
		           { type: "control" } 
		       ]
		       });
			
	}
	
}); 

</script>
</head>

<body class="nav-md">
	<div class="container body">
		<div class="main_container">
			<!--   Header File Include -->
			<jsp:include page="header.jsp" />
			<!--    Header File CLose -->
			<!-- page content -->
			<div class="right_col" role="main">
				<div class="">
					<div class="clearfix"></div>
					<div class="row" style="display: block;">
						<div class="clearfix"></div>
						<div class="clearfix"></div>
						<div class="col-md-12 col-sm-12  ">
							<div class="x_panel">
								<div class="x_title">
									<h2>
										Site <small>Master</small>
									</h2>
									<div class="clearfix"></div>
								</div>


								<div class="x_content">
									<div class="table-responsive">
										<div id="sitesTableContainer"></div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
			<!-- /page content -->

			<!-- footer content -->
			<footer>
				<div class="pull-right"></div>
				<div class="clearfix"></div>
			</footer>
			<!-- /footer content -->
		</div>
	</div>
	<script src="../vendors/bootstrap/dist/js/bootstrap.bundle.min.js"></script>
	<script src="../build/js/custom.min.js"></script>
	<script src="../../jsgrid/jsgrid.core.js"></script>
	<script src="../../jsgrid/jsgrid.load-indicator.js"></script>
	<script src="../../jsgrid/jsgrid.load-strategies.js"></script>
	<script src="../../jsgrid/jsgrid.sort-strategies.js"></script>
	<script src="../../jsgrid/jsgrid.field.js"></script>
	<script src="../../jsgrid/fields/jsgrid.field.text.js"></script>
	<script src="../../jsgrid/fields/jsgrid.field.number.js"></script>
	<script src="../../jsgrid/fields/jsgrid.field.select.js"></script>
	<script src="../../jsgrid/fields/jsgrid.field.checkbox.js"></script>
	<script src="../../jsgrid/fields/jsgrid.field.control.js"></script>
</body>
</html>
