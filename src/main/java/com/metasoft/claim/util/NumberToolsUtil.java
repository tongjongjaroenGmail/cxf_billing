package com.metasoft.claim.util;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;

import org.apache.commons.lang.StringUtils;

/**
 * Use org.apache.commons.lang.StringUtils
 * 
 * @author Artit
 *
 */
public class NumberToolsUtil {

	public static String decimalFormat(Float num) {
		if (num == null)
			return "";
		NumberFormat formatter = new DecimalFormat("#,##0.00");
		return formatter.format(num);
	}

	public static String decimalFormatMinusParenthesis(Float num) {
		if (num == null)
			return "";
		NumberFormat formatter = new DecimalFormat("#,##0.00");
		NumberFormat formatter2 = new DecimalFormat("(#,##0.00)");
		if (num < 0)
			return formatter2.format(num * -1);
		return formatter.format(num);
	}

	public static String customFormat(Float num, String format) {
		if (num == null)
			return "";
		NumberFormat formatter = new DecimalFormat(format);
		return formatter.format(num);
	}

	public static String integerFormat(Integer num) {
		if (num == null)
			return "";
		NumberFormat formatter = new DecimalFormat("#,##0");
		return formatter.format(num);
	}

	public static String integerFormat(Long num) {
		if (num == null)
			return "";
		NumberFormat formatter = new DecimalFormat("#,##0");
		return formatter.format(num);
	}

	public static Double nullToDouble(Object o) {
		if (o == null) {
			return 0d;
		}
		return Double.parseDouble(o.toString());
	}

	public static Integer nullToInteger(Object o) {
		if (o == null) {
			return 0;
		}
		return Integer.parseInt(o.toString());
	}

	public static Long parseToLong(String val) {
		if (StringUtils.isNotBlank(val) && isNumeric(val)) {
			return Long.parseLong(val);
		}
		return null;
	}

	public static Float parseToFloat(String val) {
		if (StringUtils.isNotBlank(val) && isNumeric(val)) {
			return Float.parseFloat(val);
		}
		return null;
	}

	public static Integer parseToInteger(String val) {
		if (StringUtils.isNotBlank(val) && isNumeric(val)) {
			return Integer.parseInt(val);
		}
		return null;
	}
	
	public static Float parseFormatToFloat(String val) {
		if (StringUtils.isNotBlank(val)) {
			String newVal = val.replaceAll(",", "");
			return parseToFloat(newVal);
		}
		return null;
	}

	public static boolean isNumeric(String str) {
		return str.matches("-?\\d+(\\.\\d+)?");
	}
}
