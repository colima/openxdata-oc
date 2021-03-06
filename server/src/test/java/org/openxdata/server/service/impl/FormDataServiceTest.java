package org.openxdata.server.service.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.openxdata.server.admin.model.FormData;
import org.openxdata.server.admin.model.FormDataHeader;
import org.openxdata.server.admin.model.FormDef;
import org.openxdata.server.admin.model.User;
import org.openxdata.server.admin.model.paging.PagingLoadConfig;
import org.openxdata.server.admin.model.paging.PagingLoadResult;
import org.openxdata.server.security.util.OpenXDataSecurityUtil;
import org.openxdata.server.service.FormService;
import org.openxdata.test.BaseContextSensitiveTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

/**
 * Tests the FormService type contract.
 * 
 * @author daniel
 * 
 */
public class FormDataServiceTest extends BaseContextSensitiveTest {

	@Autowired
	protected FormService formService;
	
	@Test
	public void testGetFormDataShouldReturnNullIfNoFormDataFoundWithGivenId() throws Exception {
		assertNull("formData with id=-1 exists", formService.getFormData(new Integer(-1)));
	}
	
	@Test
	public void testGetFormDataShouldNotReturnNullIfFormDataFoundWithGivenId() throws Exception {
		assertNotNull("formData with id=1 doesn't exist", formService.getFormData(1));
	}

	@Test
	public void deleteFormDataSDeleteFormDataWithGivenId() throws Exception {
		FormData formData = formService.getFormData(new Integer(1));
		assertNotNull("form data does not exist", formData);
		formData.setChangedBy(OpenXDataSecurityUtil.getLoggedInUser());
		formData.setDateChanged(new Date());
		formService.deleteFormData(formData);
		assertNotNull("form data does not exist", formData);
		formService.deleteFormData(formData);
		assertTrue("formData is voided", formService.getFormData(new Integer(1)).isVoided());
	}
	
	@Test
	public void testSaveFormData() throws Exception {
		FormData fd = new FormData(1, "data", "description", new Date(), new User(1, "guyzb"));
		formService.saveFormData(fd);
		assertNotNull("Id has been set", fd.getId());
	}
	
    @Test public void testGetFormDataListGivenFormDef() {
    	
    	FormDef form = formService.getForm(1);
    	List<FormData> formDataList = formService.getFormData(form);
    	
    	assertNotNull(formDataList);
    	assertEquals(4, formDataList.size());
    	
    }
    @Test
    @Transactional(readOnly = true)
    public void getFormDataCount() {
        assertSame(4,formService.getFormResponseCount(1));
    }
	
	@Test
	public void getUnexportedData() throws Exception {
		PagingLoadResult<FormDataHeader> result = formService.getUnexportedFormData(new PagingLoadConfig(0,10));
		List<FormDataHeader> headers = result.getData();
		assertNotNull(headers);
		assertEquals(2, headers.size());
	}
	
	@Test
	public void countUnprocessedData() throws Exception {
		Integer count = formService.getUnprocessedDataCount(1);
		assertSame("Two unprocessed form data", 2, count);
	}
}
