package org.openxdata.server.dao;

import java.util.List;
import java.util.Map;

import org.openxdata.server.admin.model.FormDef;
import org.openxdata.server.admin.model.User;
import org.openxdata.server.admin.model.mapping.UserFormMap;
import org.openxdata.server.admin.model.paging.PagingLoadConfig;
import org.openxdata.server.admin.model.paging.PagingLoadResult;

/**
 *
 */
public interface UserFormMapDAO extends BaseDAO<UserFormMap> {
	
	/**
	 * Fetches a list of <code>UserFormMap</code> definitions from the database.
	 * @return List of <code>UserFormMap</code> definitions.
	 */
	List<UserFormMap> getUserMappedForms();
	
	/**
	 * Fetches a list of <code>UserFormMap</code> definitions from the database for a specified Form
	 * @param formId Integer id of specified form
	 * @return List of <code>UserFormMap</code> definitions.
	 */
	List<UserFormMap> getUserMappedForms(Integer formId);
	
	/**
	 * Retrieves a specific UserFormMap
	 * @param userId
	 * @param formId
	 * @return
	 */
	UserFormMap getUserMappedForm(Integer userId, Integer formId);

	/**
	 * Persists a given <code>UserFormMap</code> to the database.
	 * @param map map to persist.
	 */
	void saveUserMappedForm(UserFormMap map);

	/**
	 * Deletes a given <code>UserFormMap</code> from the database.
	 * @param map map to delete.
	 */
	void deleteUserMappedForm(UserFormMap map);
	
	/**
	 * Gets all the forms that are mapped to the specified user
	 * @param user User
	 * @param loadConfig PagingLoadConfig to specify page size etc
	 * @return List of FormDef
	 */
	PagingLoadResult<FormDef> getUserMappedForms(User user, PagingLoadConfig loadConfig);
	
	/**
	 * Gets all the form names that are mapped to the specified user in a specified study
	 * 
	 * @param user User
	 * @param studyDefId Integer
	 * @return Map of Integer form id to String form name
	 */
	Map<Integer, String> getFormNamesForUser(User user, Integer studyDefId);

	/**
	 * Deletes all UserMappedForm entries for the given form.
	 * 
	 * @param formId
	 */
	void deleteUserMappedForms(int formId);
}
