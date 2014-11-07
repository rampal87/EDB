<%@taglib uri="http://www.springframework.org/tags/form" prefix="f"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="jstl"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!-- SUB MENU-->
<%@ include file="/WEB-INF/jsp/resourcemanagement/submenu.jsp"%>

<div id="mainContainer" style="margin-top: 20px;">
	<form id="resourceFileUploadForm" method="post" action="./upload.do" enctype="multipart/form-data">
	  	<!-- File input -->    
		<table class="ebdtableheader">
			<tr>
				<th colspan="2"><p style="text-align: left;height: 20px;">Upload resource file - <a href="./resources/datafiles/SampleResources.xlsx" style="color: blue;">Sample resource template<img alt="Sample resource tempalet" src="./resources/excel-icon.png" style="width: 20px;border: 0;"></a></p></th>
			</tr>
			<tr>
				<th> Upload </th>
				<td><input name="resourceFileUpload" id="resourceFileUpload" type="file" class="button"  style="width: 500px;"/></td>
			</tr>	
		</table>
		<table class="ebdtableheader" style="width: 555px;">
			<tr>
				<td colspan="2"><input class="button" type="submit" alt="Upload" value="Upload" class="button" style="margin-left: 250px;" /></td>
			</tr>
		</table>		
	</form>
</div>

<div id="result"></div>
<form id="resourceDataForm" method="post">
  	<table id = "ebdEmpTable" class = "ebdtable">
		<thead>      
			<th>Name</th>
			<th>Enterprise Id</th>
			<th>SAP ID</th>
			<th>Level</th>
			<th>Role</th>
			<th>Project</th>
			<th>Contact Number</th>
			<th>DOJ</th>
		<!-- 	<th>employeeRRDNo</th>
 			<th>employeeBillCode</th>
			<th>employeeActive</th>
 			<th>employeeWorkLocation</th>
			<th>employeeDeskNo</th>
			<th>employeeAssetTagNo</th>
			<th>employeeServiceTagNo</th>
			<th>employeeMachineCode</th>
			<th>employeeMachineRAMSize</th>
			<th>employeeHardLockDate</th>
			<th>employeeRollOffDate</th>
	 		<th>clientLANId</th>
 			<th>employeeRSATokenNo</th>
			<th>employeeRSAExpiryDate</th>
			<th>employeeSEZId</th>
			<th>employeeSEZIdExpiryDate</th>   -->
				</thead>    
		<tbody> 
	     	<c:forEach items="${empList}" var="emp">
	     		<tr><td>${emp.employeeName}</td>
	     			<td>${emp.employeeEnterpId}</td>
	     			<td>${emp.employeeSAPId}</td>
	     			<td>${emp.employeeLevel}</td>
	     			<td>${emp.employeeRole}</td>
					<td>${emp.employeeProjectName}</td>
	     			<td>${emp.employeePrimaryContactNo}</td>
	     			<td>${emp.accentureDOJ}</td>					
	     			<%-- <td>${emp.employeeRRDNo}</td>
	      			<td>${emp.employeeBillCode}</td>
	     			<td>${emp.employeeActive}</td>
	      			<td>${emp.employeeWorkLocation}</td>
	     			<td>${emp.employeeDeskNo}</td>
	     			<td>${emp.employeeAssetTagNo}</td>
	     			<td>${emp.employeeServiceTagNo}</td>
	     			<td>${emp.employeeMachineCode}</td>
	     			<td>${emp.employeeMachineRAMSize}</td>
	     			<td>${emp.employeeHardLockDate}</td>
	     			<td>${emp.employeeRollOffDate}</td>
	     			<td>${emp.clientLANId}</td>
 	     			<td>${emp.employeeRSATokenNo}</td>
	     			<td>${emp.employeeRSAExpiryDate}</td>
	     			<td>${emp.employeeSEZId}</td>
	     			<td>${emp.employeeSEZIdExpiryDate}</td> --%>
				</tr>
	     	</c:forEach>  
		</tbody>
	</table>
	<script>  $(function(){
					$("#ebdEmpTable").dataTable( {
				        columnDefs: [ {
				            targets: [ 0 ],
				            orderData: [ 0, 1 ]
				        }, {
				            targets: [ 1 ],
				            orderData: [ 1, 0 ]
				        }, {
				            targets: [ 4 ],
				            orderData: [ 4, 0 ]
				        } ]
				    } ); 
				});  
	</script>
</form>