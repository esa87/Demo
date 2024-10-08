package po;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class BoockigTimePage extends BasePage {
	private By reservedList = By.xpath("//div[@class='booking-time-reservation-table']/ol/li");
	private By confirmSelectSlot = By.xpath("//button[@id='confirm']");
	private By confirmButtonNonActive = By.xpath("//button[@disabled='disabled']");
	private By cancelAllSlot = By.id("cancel");
	private By cancelAllSlotDisabled = By.xpath("//button[@id='cancel' and @disabled='disabled']");
	private By loaderScrean = By.xpath("//*[@id='loader_screen']");
	private By msgMaxInterval = By
			.xpath("//div[contains(text(),'Вами ранее уже было выбрано время записи. Всего до')]");
	private Integer countListReservation = 0;
	private Boolean findReservedSlot = false;

	public BoockigTimePage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	public boolean isGetSlotList() {
		try {
			return waitVisibility(reservedList).equals(true);
		} catch (NoSuchElementException e) {
			e.printStackTrace();
			return false;
		}
	}

	public BookingConfirmationPage selectSlot() {
		waitVisibility(reservedList);
		driver.findElement(cancelAllSlot).click();
		waitInvisibility(loaderScrean);
		waitVisibility(confirmButtonNonActive);
		List<WebElement> listReservation = driver.findElements(reservedList);
		while (!findReservedSlot) {
			if (listReservation.get(countListReservation).getAttribute("class").contains("available")) {
				findReservedSlot = true;
				System.out.println("Select slot: " + listReservation.get(countListReservation).getText());
			}
			countListReservation++;
		}
		listReservation.get(--countListReservation).click();
		waitInvisibility(confirmButtonNonActive);
		waitAndClick(confirmSelectSlot);
		return new BookingConfirmationPage(driver);
	}

	public BookingConfirmationPage selectSlotChangeDate() {
		waitVisibility(reservedList);
		List<WebElement> listReservation = driver.findElements(reservedList);
		while (!findReservedSlot) {
			if (listReservation.get(countListReservation).equals(driver.findElement(By.className("available")))) {
				findReservedSlot = true;
				System.out.println("Select slot: " + listReservation.get(countListReservation).getText());
			}
			countListReservation++;
		}
		listReservation.get(--countListReservation).click();
		return new BookingConfirmationPage(driver);
	}

	public BoockigTimePage onlySelectSlot(int counSelect) {
		waitVisibility(reservedList);
		driver.findElement(cancelAllSlot).click();
		waitInvisibility(loaderScrean);
		waitVisibility(confirmButtonNonActive);
		for (int i = 1; i <= counSelect; i++) {
			List<WebElement> listReservation = driver.findElements(reservedList);
			while (findReservedSlot == false) {
				System.out.println("Status: " + findReservedSlot + " Step: " + i + "  countSlot: "
						+ countListReservation + " Select slot: " + listReservation.get(countListReservation).getText()
						+ " class value: " + listReservation.get(countListReservation).getAttribute("class"));
				if (listReservation.get(countListReservation).getAttribute("class").contains("available")) {

					if (listReservation.get(countListReservation).getAttribute("class").contains("ui-selected")) {
						System.out.println("skip");
					} else {
						System.out.println("click");
						findReservedSlot = true;
						System.out.println("Select slot: " + listReservation.get(countListReservation).getText());
						listReservation.get(countListReservation).click();
					}
				}
				countListReservation++;
			}
			waitInvisibility(loaderScrean);
			countListReservation = 0;
			findReservedSlot = false;
		}
		return this;
	}

	public Boolean getMsgMaxRecords() {
		return driver.findElement(msgMaxInterval).isDisplayed();
	}
}
