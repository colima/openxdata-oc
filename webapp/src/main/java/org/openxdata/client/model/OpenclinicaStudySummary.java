package org.openxdata.client.model;

import java.util.Set;

import org.openxdata.server.admin.model.OpenclinicaStudy;

import com.extjs.gxt.ui.client.data.BaseModel;

@SuppressWarnings("serial")
public class OpenclinicaStudySummary extends BaseModel {

	public OpenclinicaStudySummary(OpenclinicaStudy model) {
		set("name", model.getName());
		set("OID", model.getOID());
		set("identifier", model.getIdentifier());
		set("subjects", model.getSubjects());
	}

	public String getName() {
		return (String) get("name");
	}

	public void setName(String name) {
		set("name", name);
	}

	public String getOID() {
		return (String) get("OID");
	}

	public void setOID(String oid) {
		set("OID", oid);
	}

	public String getIdentifier() {
		return (String) get("identifier");
	}

	public void setIdentifier(String identifier) {
		set("identifier", identifier);
	}
	
	public void setSubjects(Set<String> subjects){
		set("subjects", subjects);
	}
	
	@SuppressWarnings("unchecked")
	public Set<String> getSubjects(){
		return (Set<String>)get("subjects");
	}

}
