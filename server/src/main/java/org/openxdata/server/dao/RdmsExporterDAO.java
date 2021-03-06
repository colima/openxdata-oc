package org.openxdata.server.dao;

import java.util.List;

import org.openxdata.server.admin.model.exception.OpenXdataDataAccessException;
import org.openxdata.server.export.rdbms.engine.DataQuery;

/**
 * Handles the data access layer for the RDMS Exporter task
 * @author dagmar@cell-life.org.za
 */
public interface RdmsExporterDAO {

    /**
     * Checks if the form_data has already been exported before (indicating that the existing data should be updated)
     * @param formDataId Integer identifier of the formData
     * @param tableName String name of the exported formData table
     * @return boolean true if the data already exists in the specified table
     */
    public boolean dataExists(Integer formDataId, String tableName) throws OpenXdataDataAccessException;

    /**
     * Checks if a table exists in the database
     * @param tableName String name of the table
     * @return boolean true if the table already exists in the database
     */
    public boolean tableExists(String tableName) throws OpenXdataDataAccessException;

    /**
     * Runs the specified SQL statement
     * @param sql String containing valid SQL
     */
    public void executeSql(String sql) throws OpenXdataDataAccessException;
    
    /**
     * Runs the specified SQL statements in a prepared statement. 
     * It also runs the statements within a transaction, so if one fails, the others are rolled back
     * 
     * @param sql List of DataQuery which contain the sql to execute
     */
    void executeSql(List<DataQuery> statements) throws OpenXdataDataAccessException;

    /**
     * Deletes any data in the given table that is associated to the formDataId
     * @param formDataId Integer the id of the deleted form_data
     * @param tableName String name of the table
     * @return int the number of rows deleted
     */
	int deleteData(Integer formDataId, String tableName);

	/**
	 * This methods deletes the specified table if the table contains no data.
	 * 
	 * @param tableName
	 * @return true if the table was deleted
	 */
	boolean deleteTableIfEmtpy(String tableName);
}