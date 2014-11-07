package com.acc.tools.ed.integration.dto;

import java.io.Serializable;

import org.joda.time.DateTime;
import org.springframework.format.annotation.DateTimeFormat;

public class ReleaseForm implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String projectId;
	private String releaseId;
	private String projName;
	private String releaseName;
	/*
	 * CR,Defects and release components/tasks
	 */
	private String releaseArtifacts;
	private String releaseStartDate;
	private String releaseEndDate;
	
	public String getProjName() {
		return projName;
	}
	public void setProjName(String projName) {
		this.projName = projName;
	}
	
	public String getReleaseId() {
		return releaseId;
	}
	public void setReleaseId(String releaseId) {
		this.releaseId = releaseId;
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
	public String getProjectId() {
		return projectId;
	}
	public void setProjectId(String projectId) {
		this.projectId = projectId;
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
	

}
