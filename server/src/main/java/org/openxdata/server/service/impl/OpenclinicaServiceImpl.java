package org.openxdata.server.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.openxdata.oc.transport.OpenClinicaSoapClientImpl;
import org.openxdata.oc.transport.factory.ConnectionURLFactory;
import org.openxdata.server.admin.model.FormData;
import org.openxdata.server.admin.model.FormDef;
import org.openxdata.server.admin.model.OpenclinicaStudy;
import org.openxdata.server.admin.model.StudyDef;
import org.openxdata.server.dao.EditableDAO;
import org.openxdata.server.dao.FormDataDAO;
import org.openxdata.server.dao.SettingDAO;
import org.openxdata.server.dao.StudyDAO;
import org.openxdata.server.service.OpenclinicaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.annotation.Secured;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service("OpenClinicaService")
public class OpenclinicaServiceImpl implements OpenclinicaService {

	@Autowired
	private StudyDAO studyDao;	
	
	@Autowired
	private FormDataDAO formDataDAO;
	
	@Autowired
	private EditableDAO editableDAO;
	
	@Autowired
	private SettingDAO settingDAO;
	
	private OpenClinicaSoapClientImpl getClient() {
		
		OpenClinicaSoapClientImpl client = null;
		if(client == null){
			String host = settingDAO.getSetting("openClinicaWebServiceHost");
			String userName = settingDAO.getSetting("OpenClinicaUserName");
			String password = settingDAO.getSetting("OpenClinicaUserHashedPassword");
			
			ConnectionURLFactory factory = new ConnectionURLFactory(host);
			
			client = new OpenClinicaSoapClientImpl(userName, password);
			client.setConnectionFactory(factory);
		}
		return client;
	}
	
	@Override
	@Transactional(readOnly=true)
	@Secured("Perm_View_Form_Data")
	public Boolean hasStudyData(String studyKey) {
		StudyDef study = studyDao.getStudy(studyKey);
		return editableDAO.hasEditableData(study);
	}
	
	@Override
	public List<OpenclinicaStudy> getOpenClinicaStudies() {
		
		List<OpenclinicaStudy> returnStudies = new ArrayList<OpenclinicaStudy>();
		List<org.openxdata.oc.model.OpenclinicaStudy> studies = new ArrayList<org.openxdata.oc.model.OpenclinicaStudy>();
		
		studies = getClient().listAll();
		
		for(org.openxdata.oc.model.OpenclinicaStudy s : studies) {
			OpenclinicaStudy ocStudy = new OpenclinicaStudy();
			ocStudy.setName(s.getName());
			ocStudy.setOID(s.getOID());
			ocStudy.setIdentifier(s.getIdentifier());
			returnStudies.add(ocStudy);
		}
		
		return returnStudies;
	}

	@Override
	public String importOpenClinicaStudy(String identifier) {
		
		Collection<String> subjectKeys = getClient().getSubjectKeys(identifier);
		
		String xml = "";
		if(subjectKeys != null && subjectKeys.size() > 0){
			xml = getClient().getOpenxdataForm(identifier, subjectKeys);
		}
		
		return xml;
	}

	@Override
	public void exportOpenClinicaStudyData(String studyKey) {
		StudyDef study = studyDao.getStudy(studyKey);
		List<String> allData = new ArrayList<String>(); 
		for (FormDef form : study.getForms()) {
			List<FormData> dataList = formDataDAO.getFormDataList(form);
			for (FormData formData: dataList) {
				allData.add(formData.getData());
			}
		}
		getClient().importData(allData);	
	}

}
