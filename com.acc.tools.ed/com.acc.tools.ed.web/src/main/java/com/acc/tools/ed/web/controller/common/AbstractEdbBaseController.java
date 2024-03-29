package com.acc.tools.ed.web.controller.common;

import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.acc.tools.ed.integration.dto.ReferenceData;
import com.acc.tools.ed.integration.service.ProjectManagementService;
import com.acc.tools.ed.integration.service.ProjectWorkService;
import com.acc.tools.ed.web.editor.DateTimeEditor;

public abstract class AbstractEdbBaseController {
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(DateTime.class, new DateTimeEditor("MM/dd/yyyy", true));
	}
	
	@Autowired
	private ProjectManagementService projectManagementService;
	
	@Autowired
	private ProjectWorkService projectWorkService;
	
	/**
	 * This method will be used to display the programs in the JSP
	 * @return
	 */

	@ModelAttribute("programList")
	public List<ReferenceData> getProgramList(){
		final List<ReferenceData> programId= projectManagementService.getProgramList();
		return programId;
	}	
	
	/**
	 * This method will be used to display the role's in the JSP
	 * @return
	 */

	@ModelAttribute("projectList")
	public List<ReferenceData> getProjectList()
	{
		final List<ReferenceData> projectIds=projectManagementService.getAllProjectIds();
		return projectIds;
	}

	@ModelAttribute("projLeadList")
	public List<ReferenceData> getProjectLeadList()
	{
		return projectManagementService.getPrjLeadList();
	}
	
	/**
	 * This method will be used to display the role's in the JSP
	 * @return
	 */

	@ModelAttribute("roleList")
	public List<ReferenceData> getRoleList()
	{
		List<ReferenceData> roleList=new ArrayList<ReferenceData>();
		ReferenceData developer=new ReferenceData();
		developer.setId("dev");
		developer.setLabel("Developer");
		roleList.add(developer);
		
		ReferenceData supervisor=new ReferenceData();
		supervisor.setId("sup");
		supervisor.setLabel("Supervisor");
		roleList.add(supervisor);
		
		return roleList;
	}
	
	
	/**
	 * This method will be used to display the designation's in the JSP
	 * @return
	 */

	@ModelAttribute("designationList")
	public List<ReferenceData> getDesignationList()
	{
		List<ReferenceData> roleList=new ArrayList<ReferenceData>();
		ReferenceData ase=new ReferenceData();
		ase.setId("ase");
		ase.setLabel("ASE");
		roleList.add(ase);
		
		ReferenceData se=new ReferenceData();
		se.setId("se");
		se.setLabel("SE");
		roleList.add(se);
		
		ReferenceData sse=new ReferenceData();
		sse.setId("sse");
		sse.setLabel("SSE");
		roleList.add(sse);
		
		ReferenceData tl=new ReferenceData();
		tl.setId("tl");
		tl.setLabel("TL");
		roleList.add(tl);
		
		ReferenceData am=new ReferenceData();
		am.setId("am");
		am.setLabel("AM");
		roleList.add(am);
		
		ReferenceData m=new ReferenceData();
		m.setId("m");
		m.setLabel("M");
		roleList.add(m);
		
		return roleList;
	}
	
	/**
	 * This method will be used to display the phases in the JSP
	 * @return
	 */

	@ModelAttribute("phaseList")
	public List<String> getPhaseList()
	{
		List<String> phaseList=new ArrayList<String>();

		phaseList.add("Analysis");
		phaseList.add("Design");
		phaseList.add("Build");
		phaseList.add("Test");
		phaseList.add("Support");
		
		return phaseList;
	}
	
	/**
	 * This method will be used to display the resources in the JSP
	 * @return
	 */

	@ModelAttribute("resourceList")
	public List<ReferenceData> getResourceList()
	{
		return projectManagementService.getResourceList();
	}

	public ProjectManagementService getProjectManagementService() {
		return this.projectManagementService;
	}
	
	public ProjectWorkService getProjectWorkService() {
		return this.projectWorkService;
	}
	
}
