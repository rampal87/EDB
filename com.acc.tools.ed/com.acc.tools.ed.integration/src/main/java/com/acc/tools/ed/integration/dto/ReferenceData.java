package com.acc.tools.ed.integration.dto;

import java.io.Serializable;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class ReferenceData implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String id;
	private String label;
	private boolean selected;
	
	/**
	 * Edit Project response values
	 */
	private String editPrjDescResp;
	private String editPrjStartDateResp;
	private String editPrjEndDateResp;
	
	
	/**
	 * Edit Release response values
	 */
	private String editRelArtiResp;
	private String editRelStartDateResp;
	private String editRelEndDateResp;
	
	/**
	 * 
	 * Program Details
	 */
	
	private String programId;
	private String programLabel;
	
	private String projLeadLabel;
	private String projLeadId;
	private String roleName;
	
	private String prjStDate;
	private String prjEtDate;
	
	public String getProjLeadLabel() {
		return projLeadLabel;
	}
	
	public void setProjLeadLabel(String projLeadLabel) {
		this.projLeadLabel = projLeadLabel;
	}
	
	public String getProjLeadId() {
		return projLeadId;
	}

	public void setProjLeadId(String projLeadId) {
		this.projLeadId = projLeadId;
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	@Override
	public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this);
	}
	@Override
	public boolean equals(Object o) {
		return EqualsBuilder.reflectionEquals(this, o);
	}
	public boolean isSelected() {
		return selected;
	}
	public void setSelected(boolean selected) {
		this.selected = selected;
	}
	public String getEditPrjDescResp() {
		return editPrjDescResp;
	}
	public void setEditPrjDescResp(String editPrjDescResp) {
		this.editPrjDescResp = editPrjDescResp;
	}
	public String getEditPrjStartDateResp() {
		return editPrjStartDateResp;
	}
	public void setEditPrjStartDateResp(String editPrjStartDateResp) {
		this.editPrjStartDateResp = editPrjStartDateResp;
	}
	public String getEditPrjEndDateResp() {
		return editPrjEndDateResp;
	}
	public void setEditPrjEndDateResp(String editPrjEndDateResp) {
		this.editPrjEndDateResp = editPrjEndDateResp;
	}
	public String getEditRelDescResp() {
		return editRelArtiResp;
	}
	public void setEditRelArtiResp(String editRelArtiResp) {
		this.editRelArtiResp = editRelArtiResp;
	}
	public String getEditRelStartDateResp() {
		return editRelStartDateResp;
	}
	public void setEditRelStartDateResp(String editRelStartDateResp) {
		this.editRelStartDateResp = editRelStartDateResp;
	}
	public String getEditRelEndDateResp() {
		return editRelEndDateResp;
	}
	public void setEditRelEndDateResp(String editRelEndDateResp) {
		this.editRelEndDateResp = editRelEndDateResp;
	}
	public String getProgramId() {
		return programId;
	}
	public void setProgramId(String programId) {
		this.programId = programId;
	}
	public String getProgramLabel() {
		return programLabel;
	}
	public void setProgramLabel(String programLabel) {
		this.programLabel = programLabel;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getPrjStDate() {
		return prjStDate;
	}

	public void setPrjStDate(String prjStDate) {
		this.prjStDate = prjStDate;
	}

	public String getPrjEtDate() {
		return prjEtDate;
	}

	public void setPrjEtDate(String prjEtDate) {
		this.prjEtDate = prjEtDate;
	}

}
