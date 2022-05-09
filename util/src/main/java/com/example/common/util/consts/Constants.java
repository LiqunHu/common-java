package com.example.common.util.consts;

import java.math.BigDecimal;
import java.math.RoundingMode;

public interface Constants {
	/**
	 * UTF-8 字符集
	 */
	String UTF8 = "UTF-8";
	// session

	// request parameter
	String LOGIN_WEB_TYPE = "WEB";
	String LOGIN_MOBILE_TYPE = "MOBILE";

	String REDIS_AUTH = "AUTH";
	String AUTH_USER = "AUTH_USER";
	String CURRENT_USER = "CURRENT_USER";
	String CURRENT_USER_AUTH = "CURRENT_USER_AUTH";
	String SECRET_KEY = "SECRET_KEY";

	// mock mobile user password
	String ERROR_MESSAGE = "ERROR_MESSAGE";

	String DATE_FORMAT_PATTERN_PURE = "yyyyMMdd";
	String DATE_FORMAT_PATTERN_DOT = "yyyy.MM.dd";
	String DATE_FORMAT_PATTERN_LINE = "yyyy-MM-dd";
	String TIMESTAMP_FORMAT_PATTERN = "yyyy-MM-dd HH:mm:ss";
	String TIME_FORMAT_PATTERN = "yyyy-MM-dd HH:mm";
	String ZH_TIME_FORMAT_PATTERN = "yyyy年M月d日 HH:mm";
	String HM_FORMAT_PATTERN = "H:mm";
	String MD_FORMAT_PATTERN = "M月d日";
	String YM_FORMAT_PATTERN = "yyyy年M月";

	int SEASON_DAYS = 90;// 90;
	int YEAR_DAYS = 365;// 365;
	int MONTH_DAYS = 30;
	
	int HOUR_SECOND = 60 * 60;
	int HOUR_MILLISECOND = HOUR_SECOND * 1000;
	int HOUR_6_SECOND = HOUR_SECOND * 6;
	int HOUR_4_SECOND = HOUR_SECOND * 4;
	int HOUR_4_MILLISECOND = HOUR_MILLISECOND * 4;
	int DAY_SECOND = HOUR_SECOND * 24;
	long DAY_MILLISECOND = 1000 * DAY_SECOND;
	long DAYS_3_MILLISECOND = DAY_MILLISECOND * 3;
	long DAYS_45_MILLISECOND = DAY_MILLISECOND * 45;
	long MONTH_MILLISECOND = DAY_MILLISECOND * MONTH_DAYS;
	long YEAR_MILLISECOND = DAY_MILLISECOND * YEAR_DAYS;

	String DISTANCE_100M = "100m";
	String DISTANCE_200M = "200m";
	String DISTANCE_300M = "300m";
	String DISTANCE_1000M = "1000m";

	BigDecimal BD_0_5 = new BigDecimal(0.5);
	BigDecimal BD_3_6 = new BigDecimal(3.6);
	BigDecimal BD_2 = new BigDecimal(2);
	BigDecimal BD_10 = new BigDecimal(10);
	BigDecimal BD_15 = new BigDecimal(15);
	BigDecimal BD_20 = new BigDecimal(20);
	BigDecimal BD_30 = new BigDecimal(30);

	BigDecimal BD_36 = new BigDecimal(36);
	BigDecimal BD_100 = new BigDecimal(100);
	BigDecimal BD_300 = new BigDecimal(300);
	BigDecimal BD_1000 = new BigDecimal(1000);
	BigDecimal BD_1852 = new BigDecimal(1852);
	BigDecimal BD_MILLION = new BigDecimal(1000000);

	/** Maximum valid latitude in degrees. */
	double MAX_LAT = 90.0;
	/** Minimum valid latitude in degrees. */
	double MIN_LAT = -90.0;
	/** Maximum valid longitude in degrees. */
	double MAX_LON = 180.0;
	/** Minimum valid longitude in degrees. */
	double MIN_LON = -180.0;

	BigDecimal MAX_SPEED = BD_30;
	BigDecimal MAX_SPEED_METER = BD_30.multiply(BD_1852).divide(BD_100, 1, RoundingMode.HALF_UP).divide(BD_36, 1,
			RoundingMode.HALF_UP);

	int CARGO_FLOWTO_UNLOAD = 2;
	int CARGO_FLOWTO_LOAD = 1;

	String SYSTEM = "system";
	String INPUT = "input";
	String SYSTEM_INPUT = "system_input";
	String CARGO_FLOWTO_IMPORT = "import";
	Integer NM_UNIT = 185200;

	String CARGO_FLOWTO_QUEUE = "push.search.queue";

}
