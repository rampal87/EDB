	$(document)
			.ready(
					function() {
						
						/*
						 * Date Format :yyyy-mm-dd
						 */
						Date.prototype.yyyymmdd = function() {         
					        
					        var yyyy = this.getFullYear().toString();                                    
					        var mm = (this.getMonth()+1).toString(); // getMonth() is zero-based         
					        var dd  = this.getDate().toString();             
					                            
					        return yyyy + '-' + (mm[1]?mm:"0"+mm[0]) + '-' + (dd[1]?dd:"0"+dd[0]);
					   }; 
					   
						/**
						 * Activate subtab1 on main tab 'Project Plan' active
						 */
						$('#subtab1').parent().addClass('subtab-active');
						$('#subtab1').css({
							"pointer-events": "none",
							"cursor": "default"
						});
						
						/*
						 * Add New Project Dialog box
						 */
						var addProjectDialog = $("#addproject-popup").dialog({
							autoOpen : false,
							height : 550,
							width : 650,
							modal : true,
							buttons : {
								"Add Project" : function() { 
									
									var progName=$("#programs option:selected").text();
									var progId=$("#programs").val();
									var progName1=$("#program").val();
									if(progId == "NONE" && progName1 == ''){
										alert("Please select Program!");
									} else if (progName == progName1) {
										alert("Program already exist!");
									} else if ((progId == "NONE" && progName != progName1) || progId != "NONE") {
										$("#addProjectForm").submit();																		
									}
									
									},
								Cancel : function() {
									addProjectDialog.dialog("close");
								},
							},

						});

						$("#addProject").button().on("click", function() {
							addProjectDialog.dialog("open");
						});
						
						
						/*
						 * Add New Release Dialog box
						 */
						var addReleaseDialog = $("#addrelease-popup").dialog({
							autoOpen : false,
							height : 400,
							width : 650,
							modal : true,
							buttons : {
								"Add Release" : function() { 
									var releaseIdCount=$('#releases option').size(); 
									var loginForm = $('#addReleaseForm').serializeArray();
									var jsonString = "{";
									$.each(loginForm,
									    function(i, v) {
										if(v.name=="releaseStartDate" || v.name=="releaseEndDate"){
											var dataValue=new Date(v.value);
											jsonString=jsonString+" \""+v.name+"\":\""+dataValue.yyyymmdd()+"\",";
										} else {
											jsonString=jsonString+" \""+v.name+"\":\""+v.value+"\",";
										}
									 });
									jsonString=jsonString+"\"releaseId\":\""+releaseIdCount+"\"";
									jsonString=jsonString+"}";									
									$.ajax({
										type : "POST",
										url : "./addRelease.do",
										data :jsonString ,												
										contentType : 'application/json; charset=utf-8',
										dataType : 'json',		
										beforeSend:function(){
										  },
										success : function(response) {
											$('#releases').append('<option selected value="'+response.id+'">'+response.label+'</option>').change();
											addReleaseDialog.dialog("close");		
										},
										error : function(data) {	
											$("#mainContainer").html("Application error! Please call help desk. Error:"+data.status);
										}
									});	
								},
								Cancel : function() {
									addReleaseDialog.dialog("close");
								}
							},

						});

						$("#addRelease").button().unbind("click").on("click", function() {
							var projectId=$("#projects option:selected").text();
							var prjName = $("#projects").val();							
							if(projectId=="SP"){
								alert("Please select Project!");
							} else {
								$.ajax({
									type : "POST",
									url : "./getPrjDate.do",
									data : {projectId:projectId},									
									dataType : 'json',		
									beforeSend:function(){
									  },
									success : function(response) {
										
										for(var obj in response){
											
											var prStDt = response[obj].prjStDate;
											var prEtDt = response[obj].prjEtDate;
											
											var x = prStDt.split("/");
											var y = prEtDt.split("/");
											
											var date = parseInt(x[1])+1;
											var month = x[0];
											var year = x[2];
											
											var prjStartDate = month + "/" + date + "/" + year;
											var prjEndDate = y[0] + "/" + y[1] + "/" + y[2];
											
										};
										
										
										$( "#releaseEndDate" ).datepicker();
										
										$( "#releaseStartDate" ).datepicker({
											
											beforeShow: function(input, inst) {												 
												$('#releaseStartDate').datepicker('option', 'minDate', prjStartDate);
												$('#releaseStartDate').datepicker('option', 'maxDate', prjEndDate);
											},
											
											onSelect: function (date) {
								               var z = date.split("/");
								               var date1 = parseInt(z[1])+1;
											   var month1 = z[0];
											   var year1 = z[2];
												
											   var StDate = month1 + "/" + date1 + "/" + year1;
											   $('#releaseEndDate').datepicker('option', 'minDate', StDate);
												$('#releaseEndDate').datepicker('option', 'maxDate', prjEndDate);
								              
								            }
										 }); 
									},
									error : function(data) {	
										$("#mainContainer").html("Application error! Please call help desk. Error:"+data.status);
									}
								});	
								
								addReleaseDialog.dialog("open");
								$("#projectId").text(projectId);								
								document.getElementById("projName").value=prjName;
							}
						});
						
						/*
						 * On selecting a project from drop down, 
						 * this function will fetch the corresponding releases.
						 */
						
						$("#projects").unbind("change").on("change", function() {
							var selectedProject=$("#projects").val();
							$.ajax({
								type : "POST",
								url : "./fetchReleases.do",
								data : {projectId:selectedProject},
								dataType : 'json',		
								beforeSend:function(){
								  },
								success : function(response) {
						
									$('#releases')
									  	.find('option')
									  	.remove();
									$('#releases').append('<option value="SR">Select Release</option>');
									for(var obj in response){
										$('#releases').append('<option value='+response[obj].id+'>'+response[obj].label+'</option>');
									};
								},
								error : function(data) {	
									$("#mainContainer").html("Application error! Please call help desk. Error:"+data.status);
								}
							});	
						});
						
						/*
						 * On selecting a release from drop down, 
						 * this function will fetch Project and Release details.
						 */
						
						$("#releases").unbind("change").on("change", function() {
							var selectedProject=$("#projects").val();
							var selectedRelease=$("#releases").val();
							var jsonRequest="{ \"projectId\" : \""+selectedProject+"\", \"releaseId\" : \""+selectedRelease+"\"}";
							$.ajax({
								type : "POST",
								url : "./viewProjectReleaseDetails.do",
								data : jsonRequest,
								contentType : 'application/json; charset=utf-8',
								beforeSend:function(){
								  },
								success : function(response) {
									$("#viewProjectAndReleaseDetails").html(response);
								},
								error : function(data) {	
									$("#mainContainer").html("Application error! Please call help desk.  Error:"+data.status);
								}
							});	
						});
						
						
					});

	/*
	 * This function will be to update resource excel file into the system.
	 * FormData will work only in IE >=10
	 */
	
	function uploadResourceFile(){
		$('#result').html('');

		//Callback handler for form submit event
		$("#resourceFileUploadForm").submit(function(e){
		    var formObj = $(this);
		    var formURL = formObj.attr("action");
		    var formData = new FormData(this);
		    alert(formData);
		    $.ajax({
		        url: formURL,
		        type: 'POST',
		        data:  formData,
		        mimeType:"multipart/form-data",
		        contentType: false,
		        cache: false,
		        processData:false,
		        success: function(data, textStatus, jqXHR){
		        	$('#result').html(data);
		        },
		        error: function(jqXHR, textStatus, errorThrown){
		        	$('#result').html('Error uploading resource file.'+textStatus);
		        }         
		    });
		    e.preventDefault(); //Prevent Default action.
		    e.unbind();
		});
		$("#resourceFileUploadForm").submit(); //Submit the form
	}

