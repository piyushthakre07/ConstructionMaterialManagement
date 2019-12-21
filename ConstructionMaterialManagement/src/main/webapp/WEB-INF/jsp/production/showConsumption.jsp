<!DOCTYPE html>
<html lang="en">
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <!-- Meta, title, CSS, favicons, etc. -->
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="icon" href="images/favicon.ico" type="image/ico" />

    <title>ConstructionManagement! | </title>
		
 <script src="/js/jquery-3.3.1.min.js"></script>
<script src="/js/jquery-ui-1.12.1/jquery-ui.min.js"></script>
<script>
$(document).ready(function () {
	$("#consumptionTableContainer").jsGrid({
	       width: "100%",
	       filtering: true,
	       editing: false,
	       sorting: true,
	       paging: true,
	       autoload: true,
	       inserting: false,
	       deleting:false,
	       editButton: false,                               // show edit button
	       deleteButton: false,                             // show delete button
	       clearFilterButton: false,                        // show clear filter button
	       modeSwitchButton: false,   
	       pageSize: 5,
	       pageButtonCount: 5,
	       controller: {
	           loadData: function(filter) {
	           	var d = $.Deferred();
	               $.ajax({
	                   type: 'GET',
	                   contentType: "application/json; charset=utf-8",
	                   url: '/consumption/getAllConsumption',
	                   dataType: "json",
	                   data:filter,
	                     success: function (data) {
	                     	var data1 = $.grep(data, function(data) {
	                            return (!filter.consumptionName || (data.consumptionName+'').toUpperCase().indexOf(filter.consumptionName.toUpperCase()) > -1)
	                       });   
                   	d.resolve(data1);
	                   },
	                   error: function(e) {
	                       alert("error: " + e.responseText);
	                   } 
	                  
	               });
	                return d.promise();
	           },
	        
	       },
	       fields: [
	    	       { name: "materialConsumptionId", type: "hidden", title: "Id"},
		           { name: "consumptionDateString", type: "hidden",   title: "Date"},
		           { name: "contractor.contractorName", type: "hidden",   title: "Contractor Name"},
		           { name: "sites.siteName", type: "hidden",   title: "Site Name"},
		           { name: "sites.siteAddress", type: "hidden",  title: "Site Address"},
		           { name: "materialCategory.materialCategoryName", type: "hidden", title: "Material Category Name"},
		           { name: "item.itemName", type: "hidden",   title: "Item Name"},
		           { name: "item.unit.unitName", type: "hidden",  title: "Unit"},
		           { name: "consumptionQuantity", type: "hidden",   title: "Quantity"},
		           { name: "approveBy.fullName", type: "hidden",   title: "Approve By"},
		           { name: "workType", type: "hidden",  title: "Work Type"},
		           { name: "remark", type: "hidden",   title: "Remark"}
		       ]
	
}); 
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
                    <h2>Material Consumption Details</h2>
                    <div class="clearfix"></div>
                  </div>
                  
                  <div class="x_content">
                    <div class="table-responsive">
					<div id="consumptionTableContainer"></div>
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
          <div class="pull-right">
          
          </div>
          <div class="clearfix"></div>
        </footer>
        <!-- /footer content -->
      </div>
    </div>
    <!-- jQuery -->

    <!-- Bootstrap -->
   <script src="../vendors/bootstrap/dist/js/bootstrap.bundle.min.js"></script>
    <!-- FastClick -->
    <script src="../../vendors/fastclick/lib/fastclick.js"></script>
    <!-- NProgress -->
    <script src="/vendors/nprogress/nprogress.js"></script>
    <!-- iCheck -->
    <script src="../vendors/iCheck/icheck.min.js"></script>

    <!-- Custom Theme Scripts -->
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
