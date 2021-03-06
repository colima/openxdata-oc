package org.openxdata.server.admin.model;

import net.sf.gilead.pojo.gwt.LightEntity;

/**
 * Lightweight FormDef object used when just the name
 * needs to be displayed to the user - instead of retrieving
 * the whole FormDef object (e.g. in lists)
 */
public class FormDefHeader extends LightEntity {
	private static final long serialVersionUID = 2596766993739570623L;

	private int id = 0;
	
	/** descriptive name of the form */
	private String name;
	
	/** study access indicates that a user has access to this form via the form's study*/
	private boolean studyAccess = false;
	
	public FormDefHeader() {
		// default constructor for GWT serialization
	}

	public FormDefHeader(int id, String name) {
		this.id = id;
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isStudyAccess() {
		return studyAccess;
	}

	public void setStudyAccess(boolean studyAccess) {
		this.studyAccess = studyAccess;
	}
}