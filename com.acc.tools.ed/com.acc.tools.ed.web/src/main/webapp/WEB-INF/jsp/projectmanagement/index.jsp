<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="f"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="jstl"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html >
<html>
<head>
<%@ include file="/WEB-INF/jsp/includes/document-header.jsp"%>
</head>
<body>
	<table>
		<!-- header -->
		<%@ include file="/WEB-INF/jsp/includes/header.jsp"%>
		<!-- main container -->
		<tr>
			<td>
				<div id="tabs" style="width: 100%; padding-right: 0px;">
					<!-- MAIN MENU -->
					<%@ include file="/WEB-INF/jsp/includes/menu.jsp"%>
					<div id="projectPlanTab" style="clear: both;">
					   <jstl:choose>
							<jstl:when test="${edbUser.role !='Dvlp'}">
								<!-- SUB MENU-->
								<%@ include file="/WEB-INF/jsp/projectmanagement/submenu.jsp"%>
								<div style="width: 1001px;padding-top:10px; height: 650px; overflow: auto;">
									<jsp:include page="/WEB-INF/jsp/projectmanagement/projectPlan.jsp"
										flush="true"></jsp:include>
								</div>
							</jstl:when>
							<jstl:otherwise><!-- DEVELOPER -->
								<div style="width: 1001px;padding-top:10px; height: 650px; overflow: auto;">
									<jsp:include page="/WEB-INF/jsp/projectwork/index.jsp"
										flush="true"></jsp:include>
								</div>
							</jstl:otherwise>
					   </jstl:choose>
					</div>
				</div>
			</td>
		</tr>
		<!-- footer -->
		<%@ include file="/WEB-INF/jsp/includes/footer.jsp"%>
	</table>
</body>
</html>
<script>	
	
	var selectedTab="${selectedTab}";
	if(selectedTab.length==0)
		selectedTab=0;
	/**
	 * This function will be used to load and activate tabs
	 */
	
	$("#tabs").tabs({
		active: selectedTab,
		beforeLoad : function(event, ui) {
			ui.jqXHR.error(function(jqXHR) {
				alert(jqXHR.statusText);
				ui.panel.html("Application error! Please call help desk.");
			});
		}
	});
</script>