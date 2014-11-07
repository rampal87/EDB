package com.acc.tools.ed.integration.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.acc.tools.ed.integration.dao.ProjectWorkDao;
import com.acc.tools.ed.integration.dto.ComponentForm;
import com.acc.tools.ed.integration.service.ProjectWorkService;

@Service("projectWorkService")
public class ProjectWorkServiceImpl implements ProjectWorkService {
	
	@Autowired
	private ProjectWorkDao projectWorkDao;
	
	public List<ComponentForm> getComponentList(String userId){
		return projectWorkDao.getComponentList(userId);
	}

	public List<ComponentForm> addTasks(String taskName, String taskDesc,long taskHrs,int componentId, String userId) {
		
		return projectWorkDao.addTasks(taskName, taskDesc, taskHrs,componentId,userId);
	}

}
