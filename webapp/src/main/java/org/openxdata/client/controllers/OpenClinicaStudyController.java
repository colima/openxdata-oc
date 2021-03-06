package org.openxdata.client.controllers;

import java.util.List;

import org.openxdata.client.AppMessages;
import org.openxdata.client.EmitAsyncCallback;
import org.openxdata.client.model.OpenclinicaStudySummary;
import org.openxdata.client.util.ProgressIndicator;
import org.openxdata.client.views.OpenClincaStudyView;
import org.openxdata.server.admin.client.service.OpenclinicaServiceAsync;
import org.openxdata.server.admin.client.service.SettingServiceAsync;
import org.openxdata.server.admin.model.OpenclinicaStudy;
import org.openxdata.server.admin.model.Setting;
import org.openxdata.server.admin.model.SettingGroup;
import org.openxdata.server.admin.model.StudyDef;

import com.extjs.gxt.ui.client.event.EventType;
import com.extjs.gxt.ui.client.mvc.AppEvent;
import com.extjs.gxt.ui.client.mvc.Controller;
import com.extjs.gxt.ui.client.widget.MessageBox;
import com.google.gwt.core.client.GWT;

public class OpenClinicaStudyController extends Controller {
	
	AppMessages appMessages = GWT.create(AppMessages.class);
	
	private OpenClincaStudyView view;
	private List<OpenclinicaStudy> studies;

	private SettingServiceAsync settingService;
	private OpenclinicaServiceAsync openclinicaService;

	public static final EventType LOADOPECLINICASTUDIES = new EventType();
	
	public OpenClinicaStudyController(OpenclinicaServiceAsync openclinicaService, SettingServiceAsync settingService){
		view = new OpenClincaStudyView (this);
		this.openclinicaService = openclinicaService;
		this.settingService = settingService;
		registerEventTypes(LOADOPECLINICASTUDIES);
	}

	@Override
	public void handleEvent(AppEvent event) {
		GWT.log("OpenClinicaStudyController: HandleEvent");
		validateOpenClinicaSettings(event);
		
	}

	public void importOpenClinicaStudy(String identifier) {
		
		openclinicaService.importOpenClinicaStudy(identifier, new EmitAsyncCallback<StudyDef>() {
			
			@Override
			public void onSuccess(StudyDef study) {
				GWT.log("OpenClinicaStudyController : Conversion complete with: " + study.getName());
			}
		});
	}

	void getOpenclinicaStudies(){		
		openclinicaService.getOpenClinicaStudies(new EmitAsyncCallback<List<OpenclinicaStudy>>() {

			@Override
			public void onSuccess(List<OpenclinicaStudy> result) {
				studies = result;
				view.setStudies(studies);
			}
		});
	}

	private void validateOpenClinicaSettings(final AppEvent event) {
		settingService.getSettingGroup("OpenClinica", new EmitAsyncCallback<SettingGroup>() {
			@Override
			public void onSuccess(SettingGroup group) {
				userChangedOpenclinicaSettings(group, event);
			}
		});
	}

	protected void userChangedOpenclinicaSettings(SettingGroup openclinicaSettingGroup, AppEvent event) {
		if(openclinicaSettingGroup != null){
			for(Setting setting : openclinicaSettingGroup.getSettings()){
				if(setting.getName().equals("OpenClinicaUserHashedPassword")){
					
					if(setting.getValue().equals("****")){
						MessageBox.info(appMessages.changeSettings(), appMessages.changeOpenclinicaSettings(), null);
					}
					else{
						ProgressIndicator.showProgressBar();
						getOpenclinicaStudies();
						forwardToView(view, event);
					}
				}
			}
		}
	}

	public void exportStudyToOpenclinica(final OpenclinicaStudySummary studySummary) {
		openclinicaService.hasStudyData(studySummary.getOID(), new EmitAsyncCallback<Boolean>() {
			@Override
			public void onSuccess(Boolean result) {
				if (result) {
					doExport(studySummary);
				}else {
					MessageBox.info(appMessages.noData(), appMessages.noDataToExport(), null);
				}
				
			}
		});
	}

	protected void doExport(OpenclinicaStudySummary studySummary) {
		openclinicaService.exportOpenclinicaStudyData(studySummary.getOID(), new EmitAsyncCallback<Void>() {

			@Override
			public void onSuccess(Void result) {
				MessageBox.info(appMessages.export(), appMessages.exportSuccess(), null);
			}
		});
	}
}
