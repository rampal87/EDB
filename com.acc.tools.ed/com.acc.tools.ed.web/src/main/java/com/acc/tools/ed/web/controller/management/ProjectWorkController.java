package com.acc.tools.ed.web.controller.management;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.acc.tools.ed.integration.dto.ComponentForm;
import com.acc.tools.ed.integration.service.ProjectWorkService;
import com.acc.tools.ed.web.controller.common.AbstractEdbBaseController;

@Controller
@SessionAttributes({ "edbUser","componentList" })
public class ProjectWorkController extends AbstractEdbBaseController {
	
	private static final Logger LOG = LoggerFactory.getLogger(ProjectWorkController.class);
	
	@Autowired
	private ProjectWorkService projectWorkService;
	
	@RequestMapping(value = "/projectWork.do")
	public String resourceManagement(Model model) {
		return "/projectwork/index";
	}
	
	@RequestMapping(value = "/taskDetails.do")
	public String projectPlan(Model model) {
		return "/projectwork/taskDetails";
	}
	
	@RequestMapping(value = "/addTask.do")
	public List<ComponentForm> addTask(@RequestParam("taskName")String taskName, @RequestParam("taskDesc")String taskDesc,
			@RequestParam("taskHrs")long taskHrs,@RequestParam("componentId")int componentId,@RequestParam("userId")String userId, Model model) {
		
		LOG.debug("Project Name:[{--}] addTask:[{}]",taskName+","+taskDesc+","+taskHrs+","+componentId+","+userId);
		
		List<ComponentForm> list = projectWorkService.addTasks(taskName, taskDesc, taskHrs,componentId,userId);
		model.addAttribute("componentList", list);
		
		return list;
	}

}
