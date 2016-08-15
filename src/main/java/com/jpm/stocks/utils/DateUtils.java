package com.jpm.stocks.utils;

import java.util.Calendar;
import java.util.Date;

/**
 * 
 * @author user
 *
 */
public class DateUtils {

	/**
	 * 
	 * @param timeRangeinMins
	 * @param inputDate
	 * @return boolean
	 */
	public static boolean isEligibleTimeRange(int timeRangeinMins, Date inputDate) {

		Calendar dateRange = Calendar.getInstance();
		if (timeRangeinMins == 0) {
			return true;
		}
		dateRange.add(Calendar.MINUTE, -timeRangeinMins);

		return dateRange.getTime().compareTo(inputDate) <= 0;

	}

	/**
	 * 
	 * @param timeRangeinMins
	 * @return Date
	 */
	public static Date getDateWithDelayInMins(int timeRangeinMins) {

		Calendar dateRange = Calendar.getInstance();
		dateRange.add(Calendar.MINUTE, -timeRangeinMins);
		return dateRange.getTime();

	}

}
