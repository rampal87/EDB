					<ul>
						<jstl:choose>
							<jstl:when test="${edbUser.role =='MANAGER'}">
								<li><a href="#projectPlanTab">Project Management</a></li>
								<li><a id="projectWorkMenu" href="./projectWork.do">Project Work</a></li>
								<li><a href="./resourceManagement.do">Resource Management</a></li>
								<li><a href="#">Report</a></li>
								<li><a href="#">Profile</a></li>
							</jstl:when>
							<jstl:when	test="${(edbUser.role =='SUPERVISOR') || (edbUser.role =='Lead')}">
								<li><a href="#projectPlanTab">Project Management</a></li>
								<li><a id="projectWorkMenu" href="./projectWork.do">Project Work</a></li>
								<li><a href="./resourceManagement.do">Resource Management</a></li>
								<li><a href="#">Profile</a></li>
							</jstl:when>
							<jstl:otherwise><!-- DEVELOPER -->
								<li><a id="projectWorkMenu" href="#projectWorkTab">Project Work</a></li>
							</jstl:otherwise>
						</jstl:choose>
					</ul>