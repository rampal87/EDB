package com.acc.tools.ed.integration.dto;

import java.io.Serializable;
import java.util.List;

import org.joda.time.DateTime;

public class ProjectPlanData implements Serializable	{

	private static final long serialVersionUID = 1L;
	
	private String projectName;
	private String projectDescription;
	private List<String> phases;
	private DateTime projectStartDate;
	private DateTime projectEndDate;

	private String releaseName;
	private String releaseArtifacts;
	private String releaseStartDate;
	private String releaseEndDate;

	private List<ComponentForm> componentName;

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getProjectDescription() {
		return projectDescription;
	}

	public void setProjectDescription(String projectDescription) {
		this.projectDescription = projectDescription;
	}

	public List<String> getPhases() {
		return phases;
	}

	public void setPhases(List<String> phases) {
		this.phases = phases;
	}

	public DateTime getProjectStartDate() {
		return projectStartDate;
	}

	public void setProjectStartDate(DateTime projectStartDate) {
		this.projectStartDate = projectStartDate;
	}

	public DateTime getProjectEndDate() {
		return projectEndDate;
	}

	public void setProjectEndDate(DateTime projectEndDate) {
		this.projectEndDate = projectEndDate;
	}

	public String getReleaseName() {
		return releaseName;
	}

	public void setReleaseName(String releaseName) {
		this.releaseName = releaseName;
	}

	public String getReleaseArtifacts() {
		return releaseArtifacts;
	}

	public void setReleaseArtifacts(String releaseArtifacts) {
		this.releaseArtifacts = releaseArtifacts;
	}

	public String getReleaseStartDate() {
		return releaseStartDate;
	}

	public void setReleaseStartDate(String releaseStartDate) {
		this.releaseStartDate = releaseStartDate;
	}

	public String getReleaseEndDate() {
		return releaseEndDate;
	}

	public void setReleaseEndDate(String releaseEndDate) {
		this.releaseEndDate = releaseEndDate;
	}

	public List<ComponentForm> getComponentName() {
		return componentName;
	}

	public void setComponentName(List<ComponentForm> componentName) {
		this.componentName = componentName;
	}

}
