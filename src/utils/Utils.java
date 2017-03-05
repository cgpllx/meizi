package utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class Utils {
	 public static String toGMTString() {
	        SimpleDateFormat sdf = new SimpleDateFormat("d MMM y HH:mm:ss 'GMT'", Locale.US);
	        TimeZone gmtZone = TimeZone.getTimeZone("GMT");
	        sdf.setTimeZone(gmtZone);
	        return sdf.format(new Date());
	 }

}
