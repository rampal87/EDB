	<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
	<%@ include file="/WEB-INF/jsp/includes/document-header.jsp"%>	
	<div id="signup-popup" title="Employee Registration">
		<form:form method="post" action="createLogin.do" commandName="signupForm" cssStyle="border-top: 1px solid black;">
			<table class="ebdtable" style="margin-left: auto; margin-right: auto;border: 1px solid gray;margin-top: 5px;color: black;">
				<tr>
					<th colspan="3" style="background-color: blue;font: bold;text-align: center;">Employee Sign Up</th>
				</tr>
				<tr>
					<th><span
						style="font-weight: bold; color: red; font-size: 15px;">*</span>Employee
						Id:</th>
					<td><form:input type="text" path="employeeId" size="20" class="textbox"></form:input></td>
					<td><a href="#" id="employeeIdSearch"><img alt="edit project" src="./resources/icon-search-small.gif" style="opacity: 0.6;filter: alpha(opacity=60);"></a></td>
				</tr>
				<tr>
					<th><span
						style="font-weight: bold; color: red; font-size: 15px;">*</span>Enterprise
						Id:</th>
					<td colspan="2"><input type="text" readonly id="enterpriseId" name="enterpriseId" size="25" class="textbox"></input></td>
				</tr>
				<tr>
					<th><span
						style="font-weight: bold; color: red; font-size: 15px;">*</span>Password:</th>
					<td colspan="2"><form:password  path="password" size="25" class="textbox" /></td>
				</tr>
				<tr>
					<th><span
						style="font-weight: bold; color: red; font-size: 15px;">*</span>Re
						Enter Password:</th>
					<td colspan="2"><input  type="password" name="reenterpass" id="reenterpass" size="25" class="textbox" /></td>
				</tr>
			</table>
		</form:form>
	</div>