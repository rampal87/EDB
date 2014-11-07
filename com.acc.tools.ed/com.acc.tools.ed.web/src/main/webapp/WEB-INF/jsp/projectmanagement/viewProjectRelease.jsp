<%@taglib prefix="joda" uri="http://www.joda.org/joda/time/tags"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<head>
	<script>
	 $(document).ready(function () {
		 
		 $( "#compEndDate" ).datepicker();
		 $( "#compStartDate" ).datepicker();
			
		 /* $( "#compStartDate" ).datepicker({
			 beforeShow: function(input, inst) {									
				 
				 var relStartDate = $('#relStartDate').text();
				 var relEndDate = $('#relEndDate').text();
				 
				 relStartDate = relStartDate.trim();
				 
		 		 $('#compStartDate').datepicker('option', 'minDate', relStartDate);
				 $('#compEndDate').datepicker('option', 'maxDate', relEndDate);
		 	 },
		 	 onSelect: function (date) {
		 		   
		 		   var relStartDate = $('#relStartDate').text();
				   var relEndDate = $('#relEndDate').text();
	               
				   var z = date.split("/");
	               var date1 = parseInt(z[1])+1;
				   var month1 = z[0];
				   var year1 = z[2];
					
				   var StDate = month1 + "/" + date1 + "/" + year1;
				   $('#compEndDate').datepicker('option', 'minDate', StDate);
				   $('#compEndDate').datepicker('option', 'maxDate', relEndDate);
	              
	          }
	  	 });  */
		 
		 $( "#editRelStartDate" ).datepicker({
			 
			 beforeShow: function (input, inst) {
				 var dt2 = $('#editRelStartDate').datepicker('getDate');				 
	             $('#editRelStartDate').datepicker('option', 'minDate', dt2);
	         }
		 });
		 
		 $( "#editRelEndDate" ).datepicker({
			 
			 beforeShow: function (input, inst) {
				 var dt2 = $('#editRelStartDate').datepicker('getDate');				 
	             $('#editRelEndDate').datepicker('option', 'minDate', dt2);
	         }
		 });
		 
		 $( "#editPrjStartDate" ).datepicker({
			 
			 beforeShow: function (input, inst) {
				 var dt2 = $('#editPrjStartDate').datepicker('getDate');
				 $('#editPrjStartDate').datepicker('option', 'minDate', dt2);
	         }
		 });
		 
		 $( "#editPrjEndDate" ).datepicker({
			 
			 beforeShow: function (input, inst) {
				 var dt2 = $('#editPrjStartDate').datepicker('getDate');				 
	             $('#editPrjEndDate').datepicker('option', 'minDate', dt2);
	         }
		 });
				 
		 $("#editproject-popup").dialog({ 
			 autoOpen: false,
			 height : 450,
			 width : 650,
			 modal : true,
			 buttons : {
					"Edit Project" : function() { 
						
						var selectedProject=$("#projects").val();
						var startDate=$("#editPrjStartDate").val();
						var desc=$("#editPrjDesc").val();
						var endDate=$("#editPrjEndDate").val();						
												
						$.ajax({
							type : "POST",
							url : "./editProject.do",
							data : {projectId:selectedProject,editPrjStartDate:startDate,editPrjEndDate:endDate,editPrjDesc:desc},												
							dataType : 'json',		
							beforeSend:function(){
							  },
							success : function(response) {
								
								for(var obj in response){
									$('#prjDesc').html(response[obj].editPrjDescResp);
									$('#prjStartDate').html(response[obj].editPrjStartDateResp);
									$('#prjEndDate').html(response[obj].editPrjEndDateResp);
									$('#editproject-popup').dialog("close");	
								}
							},
							error : function(data) {	
								$("#mainContainer").html("Application error! Please call help desk. Error:"+data.status);
							}
						});	
					},
					Cancel : function() {
						$("#editproject-popup").dialog("close");
					},
				},

		 });
		 
		 
		 $("#editrelease-popup").dialog({ 
			 autoOpen: false,
			 height : 300,
			 width : 650,
			 modal : true,
			 buttons : {
					"Edit Release" : function() { 
						
						var selectedRelease=$("#releases").val();
						var startDate=$("#editRelStartDate").val();
						var desc=$("#editRelArti").val();
						var endDate=$("#editRelEndDate").val();						
						
						$.ajax({
							type : "POST",
							url : "./editRelease.do",
							data : {releaseId:selectedRelease, releaseEdDate:endDate, editRelStartDate:startDate, editRelArti:desc},												
							dataType : 'json',						
							success : function(response) {
								
								for(var obj in response){
									$('#relArti').html(response[obj].editRelArtiResp);
									$('#relStartDate').html(response[obj].editRelStartDateResp);
									$('#relEndDate').html(response[obj].editRelEndDateResp);
								}
								$('#editrelease-popup').dialog("close");
							},
							error : function(data) {	
								$('#editrelease-popup').dialog("close");
								$("#mainContainer").html("Application error! Please call help desk. Error:"+data.status);
							}
						});	
					},
					Cancel : function() {
						$("#editrelease-popup").dialog("close");
					},
				},

		 });
		 
		 $("#delPrj-popup").dialog({ 
			 autoOpen: false,
			 height : 150,
			 width : 350,
			 modal : true,
			 buttons : {
					"Delete" : function() { 
						var selectedProject=$("#projects").val();
						
						$.ajax({
							type : "POST",
							url : "./deleteProject.do",
							data : {projectId:selectedProject},												
							dataType : 'json',							
							error : function(data) {
								if(data.status == 200) {
									$("#delPrj-popup").dialog("close");
									window.location.reload();
								}
							}
						});	
					},
					Cancel : function() {
						$("#delPrj-popup").dialog("close");
					},
				},

		 });
		 
		 $("#delRel-popup").dialog({ 
			 autoOpen: false,
			 height : 150,
			 width : 350,
			 modal : true,
			 buttons : {
					"Delete" : function() { 
						var selectedRelease=$("#releases").val();
						
						$.ajax({
							type : "POST",
							url : "./deleteRelease.do",
							data : {releaseId:selectedRelease},												
							dataType : 'json',							
							error : function(data) {
								if(data.status == 200) {
									$("#delRel-popup").dialog("close");
									window.location.reload();
								}
								//$("#mainContainer").html("Application error! Please call help desk. Error:"+data.status);
							}
						});	
					},
					Cancel : function() {
						$("#delRel-popup").dialog("close");
					},
				},

		 });
		 
		 $("#editPrj").click(function() {
			 $("#editproject-popup").dialog('open');   
		});
		 
		$("#editRel").click(function() {
			 $("#editrelease-popup").dialog('open');   
		});
		
		$("#delPrj").click(function() {
			 $("#delPrj-popup").dialog('open');   
		});
		
		$("#delRel").click(function() {
			 $("#delRel-popup").dialog('open');   
		});
		
		
		
		$("#addCompnt").click(function() {
			
			var lComponentName = $("#componentName").val();
			var lFunctionalDesc= $("#functionalDesc").val();
			var lCompStartDate = $("#compStartDate").val();
			var lCompEndDate = $("#compEndDate").val();
			var lCompResource = $("#compResourceList option:selected").val();
			var lProjectId = $("#projects").val();
			var lselectedRelease=$("#releases").val();
			
			$.ajax({
				type : "POST",
				url : "./addComponent.do",
				data : {componentName:lComponentName, functionalDesc:lFunctionalDesc, compStartDate:lCompStartDate, compEndDate:lCompEndDate, compResource:lCompResource, projectId:lProjectId, releaseId:lselectedRelease},												
				dataType : 'json',
				success : function(response) {
					var tableResp = '';
					for(var obj in response) {
						tableResp = '<tr><td width="165px;" id="compName">'+response[obj].componentName+'</td>'+
						'<td width="385px;" id="compFuncDesc"><div style="height:20px;display:table-cell;vertical-align:middle;">'+response[obj].functionalDesc+'</div></td>'+
						'<td width="100px;" id="comStDate">'+response[obj].startDate+'</td>'+
						'<td width="100px;" id="compEtDate">'+response[obj].endDate+'</td>'+
						'<td width="180px;" id="compResName">'+response[obj].resourceName+'</td>'+
						'<td><img alt="edit project" src="./resources/edit.gif"	width="20px;"></td>'+
						'<td><img alt="delete project" src="./resources/delete.gif"	width="20px;"></td></tr>';
					}
					
					
					if($('#children').length){
						$('#componentTable > tbody:first').append(tableResp);	
					} else {
						$("#noComponetMsg").hide();
						$('#componentTable').append('<tbody>'+tableResp+'</tbody>');
					}
					
					
				},
				error : function(data) {	
					$("#mainContainer").html("Application error! Please call help desk. Error:"+data.status);
				}
			});	  
		});
	 });
	 
	 function setCharAt(str,index,chr) {
			if(index > str.length-1) return str;
			return str.substr(0,index) + chr + str.substr(index+1);
		}

	</script>
</head>
<form id="projectForm">
<table class="ebdtableheader">
	<tr>
		<td style="width: 70px; font-weight: bold;">Project<br>Name
		</td>
		<td style="background-color: #eaead9; width: 130px;">${viewProjRelDetails.projectName}</td>
		<td style="font-weight: bold;">Description</td>
		<td style="background-color: #eaead9;width: 220px; overflow: auto;"><div id="prjDesc">${viewProjRelDetails.projectDescription}</div></td>
		<td style="font-weight: bold;">Phase</td>
		<td style="background-color: #eaead9;width: 75px; overflow: auto;">${viewProjRelDetails.phases}</td>
		<td style="font-weight: bold;">Start Date</td>
		<td style="background-color: #eaead9;"><div id="prjStartDate"><joda:format
				value="${viewProjRelDetails.startDate}" pattern="MM/dd/yyyy" /></div></td>
		<td style="font-weight: bold;">End Date</td>
		<td style="background-color: #eaead9;"><div id="prjEndDate"><joda:format
				value="${viewProjRelDetails.endDate}" pattern="MM/dd/yyyy" /></div></td>
		<td><a href="#" id="editPrj"><img alt="edit project" src="./resources/edit.gif"
			width="20px;"/></a></td>
		<td><a href="#"  id="delPrj"><img alt="delete project" src="./resources/delete.gif"></a></td>
	</tr>
	<tr>
		<td style="font-weight: bold;">Release Name</td>
		<td style="background-color: #eaead9;">${viewProjRelDetails.releaseForm.releaseName}</td>
		<td style="font-weight: bold;">Release<br>Artifacts
		</td>
		<td colspan="3"
			style="background-color: #eaead9; width: 350px; overflow: auto;"><div id="relArti">${viewProjRelDetails.releaseForm.releaseArtifacts}</div></td>
		<td style="font-weight: bold;">Start Date</td>
		<td style="background-color: #eaead9;"><div id="relStartDate">
				${viewProjRelDetails.releaseForm.releaseStartDate}</div></td>
		<td style="font-weight: bold;">End Date</td>
		<td style="background-color: #eaead9;">
				<div id="relEndDate">${viewProjRelDetails.releaseForm.releaseEndDate}</div></td>
		<td><a href="#" id="editRel"><img alt="edit project" src="./resources/edit.gif"
			width="20px;"></td>
		<td><a href="#"  id="delRel"><img alt="delete project" src="./resources/delete.gif"></a></td>
	</tr>
</table>
<table class="ebdtable" style="margin-top: 25px;">
	<tr>
		<th style="width: 160px;">Component Name</th>
		<th style="width: 295px;">Functional Desc</th>
		<th style="width: 80px;">Start Date</th>
		<th style="width: 80px;">End Date</th>
		<th style="width: 150px;">Resource</th>
		<th colspan="2" style="width: 25px;"></th>
	</tr>
	<tr>
		<td><input type="text" id="componentName" name="componentName" class="textbox" /></td>
		<td><textarea id="functionalDesc" style="overflow: auto; resize: none" rows="6"
				cols="32" class="textarea"></textarea></td>
		<td><input type="text" id ="compStartDate" name="compStartDate" class="textbox" /></td>
		<td><input type="text" id="compEndDate" name="compEndDate" class="textbox" /></td>
		<td><select id = "compResourceList" name="compResourceList" class="textbox">
			<c:forEach items="${resourceList}" var="resource">
			        <option value="${resource.id}" <c:if test="${resource.selected==true}">selected</c:if> >${resource.label}</option>
			 </c:forEach>
		</select></td>
		<td colspan="2"><a href="#" id="addCompnt"><img class="imgLink"
				alt="add comnponent" src="./resources/addnews.gif" width="20px;"></a></td>		
	</tr>
</table>
<table class="innertable2" id="componentTable"
	style="border-width: 1px; border-style: solid; border-color: #999999;">
	<c:choose>
        <c:when test="${empty viewProjRelDetails.componentList}">
			<div id="noComponetMsg" class="boxmsg border-boxmsg" style="width: 780px;color: red;">
			    <p>No <u>Components/Tasks</u> , <u>Change Requests(CR)</u> or <u>Defects</u> are configured for Project :<u>${viewProjRelDetails.projectName}</u> and Release :<u>${viewProjRelDetails.releaseForm.releaseName}</u> .<br>
			    Please add <u>Component/Task/CR/Defect</u> and assign a resource accordingly using the above form.
			    </p>
			    <b class="border-notch notch"></b>
			    <b class="notch"></b>
			</div>
        </c:when>
        <c:otherwise>
        	<c:forEach var="component" items="${viewProjRelDetails.componentList}">
				<tr>
					<td width="165px;" id="compName">${component.componentName}</td>
					<td width="385px;" id="compFuncDesc"><div style="height:20px;display:table-cell;vertical-align:middle;">${component.functionalDesc}</div></td>
					<td width="100px;" id="comStDate">${component.startDate}</td>
					<td width="100px;" id="compEtDate">${component.endDate}</td>
					<td width="180px;" id="compResName">${component.resourceName}</td>
					<td><img alt="edit project" src="./resources/edit.gif"
						width="20px;"></td>
					<td><img alt="delete project" src="./resources/delete.gif"
						width="20px;"></td>
				</tr>
			</c:forEach>
        </c:otherwise>
    </c:choose>
</table>
	<div id="editproject-popup" title="Edit Project Details">
		<p class="validateTips">All form fields are required.</p>
			<fieldset>
				<legend>Edit Project</legend>
				<div>
					<table class="ebdtable">
						<tr>
							<th style="text-align: right; height: 25px;">Task Name</th>
							<td><input type="text" id="taskName" name="taskName" value="" /></td>
							<th style="text-align: right;">Task Description</th>
							<td><input type="text" id="taskDesc" name="taskDesc" value="" /></td>
						</tr>
						<tr>
							<th style="text-align: right;">Phase</th>
							<td  colspan="3">
								<c:forEach items="${phaseList}" var="i">
									 <input type="checkbox" id="editPrjPhase">
									 	<option value="${i}">${i}</option>
									 </input>
								</c:forEach>
								</input>	
							</td>
						</tr>
						<tr>
							<th style="text-align: right;">Start Date</th>
							<td style="width: 200px;"><input type="text" id="editPrjStartDate" name="editPrjStartDate" value="${viewProjRelDetails.releaseForm.releaseStartDate}" class="textbox" /></td>
							<th style="text-align: right;">End Date</th>
							<td><input type="text" id="editPrjEndDate" name="editPrjEndDate" value="${viewProjRelDetails.releaseForm.releaseEndDate}" class="textbox" /></td>
						</tr>
					</table>
				</div>				
			</fieldset>
	</div>
	<div id="editrelease-popup" title="Edit Release Details">
		<p class="validateTips">All form fields are required.</p>
			<fieldset>
				<legend>Edit Release</legend>
				<div>
					<table class="ebdtable">
						<tr>
							<th style="text-align: right; height: 25px;">Release Name</th>
							<td>${viewProjRelDetails.releaseForm.releaseName}</td>
							
							<th style="text-align: right;">Release Artifacts</th>
							<td  colspan="3">
								<textarea style="overflow: auto; resize: none" rows="4" 
									cols="40" class="textarea" id="editRelArti" name="editRelArti" value="${viewProjRelDetails.releaseForm.releaseArtifacts}"></textarea>  
							</td>
						</tr>						
						<tr>
							<th style="text-align: right;">Start Date</th>
							<td style="width: 200px;"><input type="text" id="editRelStartDate" name="editRelStartDate" value="${viewProjRelDetails.releaseForm.releaseStartDate}" class="textbox" /></td>
							<th style="text-align: right;">End Date</th>
							<td><input type="text" id="editRelEndDate" name="editRelEndDate" value="${viewProjRelDetails.releaseForm.releaseEndDate}" class="textbox" /></td>
						</tr>
					</table>
				</div>				
			</fieldset>
	</div>
	
	<div id="delPrj-popup" title="Delete Project">
		<p style="margin-top:25px;margin-left:20px;">Do you want to delete the project?</p>
	</div>
	
	<div id="delRel-popup" title="Delete Release">
		<p style="margin-top:25px;margin-left:20px;">Do you want to delete the release?</p>
	</div>
	
	</form>
	
