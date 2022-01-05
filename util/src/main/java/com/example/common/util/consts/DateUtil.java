package com.example.common.util.consts;

import java.text.SimpleDateFormat;

import org.joda.time.DateTimeUtils;
import org.joda.time.Duration;
import org.joda.time.Period;

import java.time.*;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;

public class DateUtil {

	public static boolean isSameMonth(Calendar a, Calendar b) {
		if (a == null || b == null) {
			return false;
		}
		int m = a.get(Calendar.MONTH);
		int m2 = b.get(Calendar.MONTH);
		return m == m2;
	}

	public static boolean isSameMonth(Date a, Date b) {
		if (a == null || b == null) {
			return false;
		}
		return isSameMonth(DateUtils.toCalendar(a), DateUtils.toCalendar(b));
	}

	public static boolean isSameYear(Calendar a, Calendar b) {
		if (a == null || b == null) {
			return false;
		}
		int m = a.get(Calendar.YEAR);
		int m2 = b.get(Calendar.YEAR);
		return m == m2;
	}

	public static boolean isSameYear(Date a, Date b) {
		if (a == null || b == null) {
			return false;
		}
		return isSameYear(DateUtils.toCalendar(a), DateUtils.toCalendar(b));
	}

	public static boolean isSameDay(Date a, Date b) {
		if (a == null || b == null) {
			return false;
		}
		Date a1 = getStartTime(a);
		Date b1 = getStartTime(b);
		return a1.equals(b1);
	}

	public static boolean isSameDay(Date a, LocalDate b) {
		if (a == null || b == null) {
			return false;
		}
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(a);
		return calendar.get(Calendar.YEAR) == b.getYear() && calendar.get(Calendar.MONTH) + 1 == b.getMonthValue()
				&& calendar.get(Calendar.DATE) == b.getDayOfMonth();
	}

	public static Calendar getFristDayOfYear(int year) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(year, Calendar.JANUARY, 1, 0, 0, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		return calendar;
	}

	public static Calendar getLastDayOfYear(int year) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(year, Calendar.DECEMBER, 31, 23, 59, 59);
		calendar.set(Calendar.MILLISECOND, 999);
		return calendar;
	}

	public static int getYear(long millis) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(millis);
		int year = calendar.get(Calendar.YEAR);
		return year;
	}

	public static Date getEndDate(Calendar d) {
		Calendar c = (Calendar) d.clone();
		c.set(Calendar.HOUR_OF_DAY, 23);
		c.set(Calendar.MINUTE, 59);
		c.set(Calendar.SECOND, 59);
		c.set(Calendar.MILLISECOND, 999);
		return c.getTime();
	}

	public static Calendar getCalendar(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar;
	}

	public static int get(Date date, int field) {
		Calendar calendar = getCalendar(date);
		return calendar.get(field);
	}

	public static Date getFristDayOfMonth(Date date) {
		Calendar todayStart = Calendar.getInstance();
		todayStart.setTime(date);
		todayStart.set(Calendar.HOUR_OF_DAY, 0);// 24H
		todayStart.set(Calendar.MINUTE, 0);
		todayStart.set(Calendar.SECOND, 0);
		todayStart.set(Calendar.MILLISECOND, 0);
		todayStart.set(Calendar.DATE, 1);
		return todayStart.getTime();
	}

	public static Date getLastDayOfMonth(Date date) {
		Calendar todayStart = Calendar.getInstance();
		todayStart.setTime(date);
		todayStart.set(Calendar.HOUR_OF_DAY, 0);// 24H
		todayStart.set(Calendar.MINUTE, 0);
		todayStart.set(Calendar.SECOND, 0);
		todayStart.set(Calendar.MILLISECOND, 0);
		todayStart.set(Calendar.DATE, 1);
		todayStart.add(Calendar.MONTH, 1);
		todayStart.add(Calendar.MILLISECOND, -1);
		return todayStart.getTime();
	}

	public static Date getStartTime(Date date) {
		Calendar todayStart = Calendar.getInstance();
		todayStart.setTime(date);
		todayStart.set(Calendar.HOUR_OF_DAY, 0);// 24H
		todayStart.set(Calendar.MINUTE, 0);
		todayStart.set(Calendar.SECOND, 0);
		todayStart.set(Calendar.MILLISECOND, 0);
		return todayStart.getTime();
	}

	public static Date getEndTime(Date date) {
		Calendar todayEnd = Calendar.getInstance();
		todayEnd.setTime(date);
		todayEnd.set(Calendar.HOUR_OF_DAY, 23);
		todayEnd.set(Calendar.MINUTE, 59);
		todayEnd.set(Calendar.SECOND, 59);
		todayEnd.set(Calendar.MILLISECOND, 999);
		return todayEnd.getTime();
	}

	public static Date parseDate(String str, String pattern) {
		if (str == null) {
			return null;
		}
		try {
			java.text.SimpleDateFormat formatter = new SimpleDateFormat(pattern);
			return formatter.parse(str);
		} catch (Exception e) {
			return null;
		}
	}

	public static Date getFristDayOfWeek(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.HOUR_OF_DAY, 0);// 24H
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);

		int day = cal.get(Calendar.DAY_OF_WEEK);
		int amount = day - Calendar.MONDAY;
		amount = amount < 0 ? 6 : amount;
		cal.add(Calendar.DATE, -amount);

		return cal.getTime();
	}

	public static String formatDate(Date date, String pattern) {
		if (date == null) {
			return "";
		}
		return DateFormatUtils.format(date, pattern);
	}

	public static String formatDate(Date date) {
		return formatDate(date, "yyyy-MM-dd");
	}

	public static String formatDateTime(Date date) {
		return formatDate(date, "yyyy-MM-dd HH:mm:ss");
	}

	public static String formatDateCompareToday(Date date) {
		return formatDateCompareToday(date, new Date());
	}

	public static String formatDateCompareToday(Date date, Date now) {
		if (date == null) {
			return "";
		}

		if (isSameDay(date, now)) {
			return formatDate(date, "H:mm");
		} else if (DateUtil.isSameYear(date, now)) {
			return formatDate(date, "M月d日");
		} else {
			return formatDate(date, "yyyy年M月");
		}
	}

	public static LocalDate toLocalDate(Date date) {
		return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
	}

	public static LocalDateTime toLocalDateTime(Date date) {
		return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
	}

	public static Date toDate(LocalDateTime localDateTime) {
		return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
	}

	public static Date toDate(LocalDate localDate) {
		LocalDateTime startOfDay = localDate.atStartOfDay();
		return toDate(startOfDay);
	}

	public static Double dateDiff(Date startTime, Date endTime, TimeUnit timeUnit) {
		long mls = ChronoUnit.MILLIS.between(startTime.toInstant(), endTime.toInstant());
		Duration m = Duration.millis(mls);
		Period period = m.toPeriod().normalizedStandard();
		int day = period.getDays();
		int hour = period.getHours();
		int min = period.getMinutes();
		int son = period.getSeconds();

		if (timeUnit.equals(TimeUnit.DAYS)){
			return (double) day + (hour/24);
		}else if(timeUnit.equals(TimeUnit.HOURS)){
			return (double) day*24 + hour + min/60;
		}else if(timeUnit.equals(TimeUnit.MINUTES)){
			return (double) min + day*24*60 + hour*60 + son/60;
		}else if (timeUnit.equals(TimeUnit.SECONDS)){
			return (double) son + day*24*60*60 + min*60 + hour*60*60;
		}else{
			return 0d;
		}
	}

	public static void main(String[] args) throws Exception {
		Date s = DateUtil.parseDate("2020-06-21 10:10:00","yyyy-MM-dd HH:mm:ss");
		Date e = DateUtil.parseDate("2020-06-22 30:10:00","yyyy-MM-dd HH:mm:ss");
		System.out.println(DateUtil.dateDiff(s,e,TimeUnit.HOURS));
	}

}
