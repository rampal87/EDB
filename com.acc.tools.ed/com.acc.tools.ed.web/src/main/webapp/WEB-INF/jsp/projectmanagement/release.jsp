	<div id="addrelease-popup" title="Add New Release">
		<p class="validateTips">All form fields are required.</p>
		<form id="addReleaseForm">
			<fieldset>
				<legend>Add Release</legend>
				<div>
					<table class="ebdtable">
						<tr>
							<th style="text-align: right; height: 25px;">Project Name</th>
							<td><div id="projectId"/>
							</td>
								<input type="hidden" id="projName" name="projName"/>
							<th style="text-align: right;">Release Name</th>
							<td><input type="text" name="releaseName" class="textbox" /></td>
						</tr>
 						<tr>
							<th style="text-align: right;">Start Date</th>
							<td style="width: 200px;"><input type="text" id="releaseStartDate" name="releaseStartDate" class="textbox" />								
							</td>
							<th style="text-align: right;">End Date</th>
							<td><input type="text" id="releaseEndDate" name="releaseEndDate" class="textbox" /></td>
						</tr>						
						<tr>
							<th style="text-align: right;">Release Artifacts</th>
							<td  colspan="3">
								<textarea style="overflow: auto; resize: none" rows="6" name="releaseArtifacts"
									cols="82" class="textarea" ></textarea>  
							</td>
 						</tr>
					</table>
				</div>				
			</fieldset>
		</form>
	</div>