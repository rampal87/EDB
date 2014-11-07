package com.acc.tools.ed.integration.dao;

import java.util.List;

import com.acc.tools.ed.integration.dto.ComponentForm;

public interface ProjectWorkDao {

	public List<ComponentForm> getComponentList(String userId);
	public List<ComponentForm> addTasks(String taskName, String taskDesc,long taskHrs, int componentId, String userId);
}
