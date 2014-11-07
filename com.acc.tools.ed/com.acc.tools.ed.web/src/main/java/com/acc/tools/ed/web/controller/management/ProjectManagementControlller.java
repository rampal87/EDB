package com.acc.tools.ed.web.controller.management;

import java.util.List;

import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.acc.tools.ed.integration.dto.ComponentForm;
import com.acc.tools.ed.integration.dto.ProjectForm;
import com.acc.tools.ed.integration.dto.ProjectPlanData;
import com.acc.tools.ed.integration.dto.ReferenceData;
import com.acc.tools.ed.integration.dto.ReleaseForm;
import com.acc.tools.ed.web.controller.common.AbstractEdbBaseController;

@Controller
@SessionAttributes({ "edbUser" })
public class ProjectManagementControlller extends AbstractEdbBaseController {

	private static final Logger LOG = LoggerFactory
			.getLogger(ProjectManagementControlller.class);


	@RequestMapping(value = "/projectStatus.do")
	public String projectStatus(Model model) {
		return "/projectmanagement/projectStatus";
	}

	@RequestMapping(value = "/projectPlan.do")
	public String projectPlan(Model model) {
		model.addAttribute("addProjectForm", new ProjectForm());
		model.addAttribute("addReleaseForm", new ReleaseForm());
		return "/projectmanagement/projectPlan";
	}

	@RequestMapping(value = "/addProject.do")
	public String addProject(
			@ModelAttribute("addProjectForm") ProjectForm addProjectForm,
			@ModelAttribute("projectList") List<ReferenceData> projectList,
			Model model) {
		LOG.debug("Project Name:{}", addProjectForm.getProjectName());
		final ReferenceData newProject = getProjectManagementService().addProject(addProjectForm);
		projectList.add(newProject);		
		model.addAttribute("projectList", projectList);
		LOG.debug("Add Project retruned : {}", newProject.getId());
		return "/projectmanagement/index";
	}
	
	
	@RequestMapping(value = "/getPrjDate.do")
	public @ResponseBody List<ReferenceData> getPrjDate(
			@RequestParam("projectId") String projectId,
			Model model) {
		
		LOG.debug("Project Name:[{--}] GET PROJECT Date:[{}]",projectId);
		
		return getProjectManagementService().getProjectDate(projectId);
	}
	
	@RequestMapping(value = "/addRelease.do")
	public @ResponseBody ReferenceData addRelease(
			@RequestBody  ReleaseForm addReleaseForm,
			Model model) {
		LOG.debug("Project Name:[{--}] Release Name:[{}]",
				addReleaseForm.getProjName(), addReleaseForm.getReleaseName());
		final ReferenceData refData = getProjectManagementService().addRelease(addReleaseForm);
//		String json="{\"id\":"+refData.getId()+", \"label\":\""+refData.getLabel()+"\"}";
		return refData;
	}
	
	@RequestMapping(value = "/editProject.do")
	public @ResponseBody List<ReferenceData> editProject(
			@RequestParam("editPrjDesc") String editPrjDesc,
			@RequestParam("editPrjStartDate") String editPrjStartDate,
			@RequestParam("editPrjEndDate") String editPrjEndDate,
			@RequestParam("projectId") String projectId,
			Model model) {
		LOG.debug("Project Name:[{--}] EDIT PROJECT NAME Name:[{}]",editPrjDesc+","+editPrjStartDate+","+editPrjEndDate+","+projectId);
		
		return getProjectManagementService().editProject(projectId, editPrjDesc, editPrjStartDate, editPrjEndDate);
	}
	
	@RequestMapping(value = "/editRelease.do")
	public @ResponseBody List<ReferenceData> editRelease(
			@RequestParam("editRelArti") String editRelArti,
			@RequestParam("editRelStartDate") String editRelStartDate,
			@RequestParam("releaseEdDate") String releaseEndDate,
			@RequestParam("releaseId") String releaseId,
			Model model) {
		LOG.debug("Release Name:[{--}] EDIT RELEASE NAME Name:[{}]",editRelArti+","+editRelStartDate+","+releaseId);
		
		return getProjectManagementService().editRelease(releaseId, editRelArti, editRelStartDate, releaseEndDate);
	}	
	
	@RequestMapping(value = "/deleteProject.do")
	public String deleteProject(
			@RequestParam("projectId") String projectId,
			Model model) {
		LOG.debug("Project Name:[{--}] DELETE PROJECT NAME Name:[{}]", projectId);
		
		getProjectManagementService().deleteProject(projectId);
		model.addAttribute("addProjectForm", new ProjectForm());
		model.addAttribute("addReleaseForm", new ReleaseForm());
		return "/projectmanagement/projectPlan";
	}
	
	
	@RequestMapping(value = "/deleteRelease.do")
	public String deleteRelease(
			@RequestParam("releaseId") String releaseId,
			Model model) {
		LOG.debug("Project Name:[{--}] DELETE RELEASE NAME Name:[{}]", releaseId);
		
		getProjectManagementService().deleteRelease(releaseId);
		model.addAttribute("addProjectForm", new ProjectForm());
		model.addAttribute("addReleaseForm", new ReleaseForm());
		return "/projectmanagement/projectPlan";
	}
	
	
	@RequestMapping(value = "/fetchReleases.do" ,method = RequestMethod.POST)
	public @ResponseBody
	List<ReferenceData> getReleaseList(@RequestParam("projectId") String projectId,Model model) {
		return getProjectManagementService().getProjectReleaseIds(projectId);
	}
	

	@RequestMapping(value = "/viewProjectReleaseDetails.do")
	public String viewProjectReleaseDetails(@RequestBody ReleaseForm addReleaseForm,
				Model model) {
		LOG.debug("Select Project Id:[{}] and Selected Release Id:[{}]",
				addReleaseForm.getProjectId(), addReleaseForm.getReleaseId());
		
		ProjectPlanData planData = getProjectManagementService().getProjectPlanDetails(addReleaseForm.getReleaseId(), addReleaseForm.getProjectId());
		
		ProjectForm projectForm = new ProjectForm();
		projectForm.setProjectName(planData.getProjectName());
		projectForm.setProjectDescription(planData.getProjectDescription());
		projectForm.setPhases(planData.getPhases());
		projectForm.setStartDate(new DateTime(planData.getProjectStartDate()));
		projectForm.setEndDate(new DateTime(planData.getProjectEndDate()));

		ReleaseForm release = new ReleaseForm();
		release.setReleaseName(planData.getReleaseName());
		release.setReleaseArtifacts(planData.getReleaseArtifacts());
		release.setReleaseEndDate(planData.getReleaseEndDate());
		release.setReleaseStartDate(planData.getReleaseStartDate());
		
		projectForm.setReleaseForm(release);
		
		projectForm.setComponentList(planData.getComponentName());
		
		model.addAttribute("viewProjRelDetails", projectForm);
		return "/projectmanagement/viewProjectRelease";
	}
	
	@RequestMapping(value = "/addComponent.do")
	public @ResponseBody List<ComponentForm> addComponent(
			@RequestParam("projectId") String projectId,
			@RequestParam("componentName") String componentName,
			@RequestParam("functionalDesc") String functionalDesc,
			@RequestParam("compStartDate") String compStartDate,
			@RequestParam("compEndDate") String compEndDate,
			@RequestParam("compResource") String compResource,
			@RequestParam("releaseId") String releaseId,
			Model model) {
		LOG.debug("addComponent :[{}]",projectId+","+componentName+","+functionalDesc+","+compStartDate+","+compEndDate+","+compResource+","+releaseId);
		ProjectPlanData planData = getProjectManagementService().addComponent(projectId,componentName,functionalDesc,compStartDate,compEndDate,compResource,releaseId);
		return planData.getComponentName();		
	}
	

}
