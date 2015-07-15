package com.metasoft.claim.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DateToolsUtil {
	public static final String DATE_PATTERN_VIEW_DDMMYYYY = "dd/MM/yyyy";
	public static final String DATE_PATTERN_VIEW_YYYYMMDD = "yyyyMMdd";
	
	public static final Locale LOCALE_TH = new Locale("th", "TH");
	
	/**
	 * Convert string to date (format dd/MM/yyyy)
	 * 
	 * @param date
	 * @return
	 */
	public static Date convertStringToDate(String date, Locale locale) {
		return convertStringToDate(date, DATE_PATTERN_VIEW_DDMMYYYY, locale);
	}

	/**
	 * Convert String to Date
	 * 
	 * @param date
	 * @param format
	 * @param locale
	 * @return
	 */
	public static Date convertStringToDate(String date, String format, Locale locale) {
		DateFormat df = null;
		Date newDate = null;
		if (date != null && !date.trim().equals("")) {
			try {
				df = new SimpleDateFormat(format, locale);
				newDate = df.parse(date);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return newDate;
	}


	/**
	 * Convert to String
	 * 
	 * @param d
	 */
	public static String convertToString(Date d, Locale locale) {
		return convertToString(d, DATE_PATTERN_VIEW_DDMMYYYY, locale);
	}
	
	/**
	 * Convert to String with Specific Format
	 * 
	 * @param d
	 * @param format
	 * @param locale
	 * @return
	 */
	public static String convertToString(Date d, String format, Locale locale) {
		if (d == null) return "";
		return new SimpleDateFormat(format, locale).format(d);
	}

}
