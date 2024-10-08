package po;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class BookingDatePage extends BasePage {

	private By calendarBody = By.xpath("//*[@id='CalendarBody']");
	private By calendarBodyList = By.xpath("//*[@id='CalendarBody']/tr/td");
	private static final String monthFormat = "MM";
	private static final String siteFormat = "M/d/yyyy";
	private Date testDate;
	private Boolean switchWork = false;
	static SimpleDateFormat dateFormat = new SimpleDateFormat(monthFormat);
	static SimpleDateFormat dateFormatSite = new SimpleDateFormat(siteFormat);
	private String[] availableRecordsForDay;
	private Boolean findRecord = false;
	private Integer countListCalendar = 0;
	private By loader = By.id("loader");
	private static final String nMonth = dateFormat.format(new Date()).toString();
	private static final String nDate = dateFormatSite.format(new Date()).toString();

	public BookingDatePage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	public boolean isGetCalendar() {
		try {
			return waitVisibility(calendarBody).equals(true);
		} catch (NoSuchElementException e) {
			e.printStackTrace();
			return false;
		}
	}

	public BoockigTimePage getNexDay(int getMinOpenSlot) {
		waitInvisibility(loader);
		List<WebElement> listCalendar = driver.findElements(calendarBodyList);
		while (!findRecord) {
			dateFormat.applyPattern(siteFormat);
			try {
				testDate = dateFormat.parse(listCalendar.get(countListCalendar).getAttribute("date"));
			} catch (ParseException e) {
				e.printStackTrace();
			}
			dateFormat.applyLocalizedPattern(monthFormat);
			if (nDate.equals(listCalendar.get(countListCalendar).getAttribute("date").toString())) {
				switchWork = true;
			}
			if (switchWork == true) {
				if (listCalendar.get(countListCalendar).findElements(By.xpath("./span")).size() > 0) {
					availableRecordsForDay = listCalendar.get(countListCalendar).findElement(By.xpath("./span"))
							.getText().split("\\/");
					if (new Integer(availableRecordsForDay[0]) >= getMinOpenSlot) {
						findRecord = true;
						System.out.println("Select day: " + listCalendar.get(countListCalendar).getAttribute("date"));
					}
				}
			}
			countListCalendar++;
		}

		js.scrollTo(listCalendar.get(--countListCalendar));
		listCalendar.get(countListCalendar).click();
		return new BoockigTimePage(driver);
	}
}