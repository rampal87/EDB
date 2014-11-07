package com.acc.tools.ed.integration.service;

import java.util.List;

import com.acc.tools.ed.integration.dto.ComponentForm;

public interface ProjectWorkService {
	
	public List<ComponentForm> getComponentList(String userId);
	public List<ComponentForm> addTasks(String taskName, String taskDesc, long taskHrs, int componentId,String userId);

}
