package go;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;
import java.util.regex.Pattern;

public class Helper {

	public static Integer getCountMatcher(String inputText, String regExp) {
		Integer counterInteger = 0;
		Pattern pattern = Pattern.compile(regExp);
		java.util.regex.Matcher matcher = pattern.matcher(inputText);
		while (matcher.find()) {
			counterInteger++;
		}
		return counterInteger;
	}

	public static String genDate(Integer days) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
		Date date = new Date();
		date.setDate(date.getDate() + days);
		return formatter.format(date);		
	}

	public static String genDateRU(Integer days) {
		SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
		Date date = new Date();
		date.setDate(date.getDate() + days);
		return formatter.format(date);		
	}

	public static String genTorid() {
		UUID uuid = UUID.randomUUID();
		String uuidAsString = uuid.toString();
		return "auto-" + uuidAsString.substring(24);
	}

	public static Boolean checkDateForSendingMessageAboutCancelAppointments(String dateGet) throws ParseException {
		SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy hh:mm");
		Date date = format.parse(dateGet);
		System.out.println(date);
		Date minDate = new Date();
		Date maxDate = new Date();
		maxDate.setDate(maxDate.getDate() + 1);
		maxDate.setHours(23);
		maxDate.setMinutes(59);
		maxDate.setSeconds(59);
		minDate.setHours(17);
		minDate.setMinutes(00);
		minDate.setSeconds(00);
		System.out.println(maxDate);
		System.out.println(minDate);
		Boolean rez;
		if (date.before(minDate) || date.after(maxDate))
			rez = false;
		else
			rez = true;
		return rez;
	}

	public String getUid() {
		UUID uuid = UUID.randomUUID();
		return uuid.toString();
	}

	public static String convertMomentutc(String momentUtc, Integer typeFormat) {
		long ll = Long.valueOf(momentUtc);
		Date date = new Date(ll);
		SimpleDateFormat format;
		if (typeFormat == 1) {
			format = new SimpleDateFormat("yyyy-M-dd");
		} else
			format = new SimpleDateFormat("yyyy-MM-dd");
		return format.format(date);
	}

	public static String Calculate(int a) {		
		Integer result = a + a;
		return result.toString();
	}

	

}
