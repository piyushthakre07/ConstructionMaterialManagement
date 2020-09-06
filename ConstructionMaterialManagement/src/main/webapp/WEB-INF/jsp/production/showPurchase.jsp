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
	$("#purchaseTableContainer").jsGrid({
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
	                   url: '/purchase/getAllPurchase',
	                   dataType: "json",
	                   data:filter,
	                     success: function (data) {
	                     	var data1 = $.grep(data, function(data) {
	                            return (!filter.purchaseName || (data.purchaseName+'').toUpperCase().indexOf(filter.purchaseName.toUpperCase()) > -1)
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
	    	       { name: "purchaseId", type: "hidden",  width:75, title: "Purchase Id"},
		           { name: "item.itemName", type: "hidden",  width:75, title: "Item Name"},
		           { name: "item.unit.unitName", type: "hidden",  width:75, title: "Unit"},
		           { name: "vendor.vendorName", type: "hidden",  width:75, title: "Vendor Name"},
		           { name: "quantity", type: "hidden",  width:75, title: "Quantity"},
		           { name: "remark", type: "hidden",  width:75, title: "Remark"}
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
                    <h2>Purchase <small>Details</small></h2>
                    <div class="clearfix"></div>
                  </div>
                  
                  <a href="/purchase/addPurchaseItem"><div class="btn btn-round btn-success">Add Purchase Item</div></a>
                  <div class="x_content">
                    <div class="table-responsive">
					<div id="purchaseTableContainer"></div>
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
