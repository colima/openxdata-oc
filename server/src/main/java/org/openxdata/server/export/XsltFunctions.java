package org.openxdata.server.export;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Custom fuctions for use in XSLT processing
 * 
 * @author Simon Kelly <simon@cell-life.org>
 *
 */
public class XsltFunctions {
	
	private static final Logger log = LoggerFactory.getLogger(XsltFunctions.class);

	public static String convertDateToWeekOfYear(String dateString){
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
		try {
			Date date = dateFormat.parse(dateString);
			return new SimpleDateFormat("yyyyWw").format(date);
		} catch (ParseException e) {
			log.error("Error converting date: " + dateString, e);
		}
		return dateString;
	}
}