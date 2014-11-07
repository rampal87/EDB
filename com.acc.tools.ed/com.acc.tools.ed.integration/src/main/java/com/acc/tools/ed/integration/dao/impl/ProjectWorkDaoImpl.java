package com.acc.tools.ed.integration.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Service;

import com.acc.tools.ed.integration.dao.ProjectWorkDao;
import com.acc.tools.ed.integration.dto.ComponentForm;
import com.acc.tools.ed.integration.dto.TaskForm;

/**
 * 
 * @author dinesh.sridhar
 *
 */

@Service("projectWorkDao")
public class ProjectWorkDaoImpl extends AbstractEdbDao implements ProjectWorkDao {
	
public List<ComponentForm> getComponentList(String userId) {
		
		List<ComponentForm> componentList = null;
		HashMap<Integer,ComponentForm> map = new HashMap<Integer, ComponentForm>();
		try{
			final String componentTable= "SELECT a.*,b.* FROM EDB_PROJ_COMPNT a LEFT JOIN EDB_TASK_MASTER b ON a.COMPNT_ID = b.COMPNT_ID where a.EMP_EMPLOYEE_ID='"+userId+"'";
			Statement stmt=getConnection().createStatement();
			ResultSet rs=stmt.executeQuery(componentTable);
			while(rs.next()){
				int componentId = rs.getInt(1);
				if(!map.isEmpty() && map.containsKey(componentId)){
					TaskForm taskForm = new TaskForm();
					taskForm.setTaskName(rs.getString("TASK_NAME"));
					taskForm.setComponentId(componentId);
					taskForm.setTaskId(rs.getInt("TASK_ID"));
					taskForm.setTaskDesc(rs.getString("TASK_DESC"));
					taskForm.setTaskHrs(rs.getInt("TASK_HRS"));
					List<TaskForm> taskList =  map.get(componentId).getTaskFormList();
					taskList.add(taskForm);
					map.get(componentId).setTaskFormList(taskList);
				}else{
					ComponentForm compnt = new ComponentForm();
					compnt.setProjectId(rs.getInt("PROJ_ID"));
					compnt.setComponentId(componentId);
					compnt.setComponentName(rs.getString("COMPNT_NAME"));
					compnt.setFunctionalDesc(rs.getString("COMPNT_FUNC_DESC"));
					
					SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
					Date compStDate =  sdf1.parse(rs.getString("COMPNT_ST_DT"));
					sdf1.applyPattern("MM/dd/yyyy");
					compnt.setStartDate(sdf1.format(compStDate));
					
					SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
					Date compEtDate =  sdf2.parse(rs.getString("COMPNT_END_DT"));
					sdf2.applyPattern("MM/dd/yyyy");			
					compnt.setEndDate(sdf2.format(compEtDate));
					
					List<TaskForm> taskList = new ArrayList<TaskForm>();
					TaskForm taskForm = new TaskForm();
					taskForm.setTaskName(rs.getString("TASK_NAME"));
					taskForm.setComponentId(componentId);
					taskForm.setTaskId(rs.getInt("TASK_ID"));
					taskForm.setTaskDesc(rs.getString("TASK_DESC"));
					taskForm.setTaskHrs(rs.getInt("TASK_HRS"));
					taskList.add(taskForm);
					compnt.setTaskFormList(taskList);
					map.put(compnt.getComponentId(),compnt);
				}
			}
			
			componentList = new ArrayList<ComponentForm>(map.values());
			
			
			stmt.close();
		}catch(Exception e)	{
			log.error("Error retreiving employee table :",e);
			return null;
		}
		
		return componentList;
	}

	public List<ComponentForm> addTasks(String taskName, String taskDesc,long taskHrs,int componentId, String userId) {
		
		List<ComponentForm> componentList = new ArrayList<ComponentForm>();
		
		try {
			
			String addTaskQuery = "insert into EDB_TASK_MASTER(COMPNT_ID,TASK_NAME,TASK_DESC,TASK_HRS) values (?,?,?,?)";
			PreparedStatement pstm = getConnection().prepareStatement(addTaskQuery);
			pstm.setInt(1, componentId);
			pstm.setString(2, taskName);
			pstm.setString(3, taskDesc);
			pstm.setInt(4, Integer.parseInt(String.valueOf(taskHrs)));
			pstm.executeUpdate();
			pstm.close();
			
			componentList = getComponentList(userId);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return componentList;
	}

}
