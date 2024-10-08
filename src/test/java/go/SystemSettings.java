package go;

import org.openqa.selenium.chrome.ChromeOptions;

public class SystemSettings {

	public static String APP_URL = "http://localhost:8484";
	public static String HOST_URL = "http://localhost:4444/wd/hub";

	public static ChromeOptions chromeOption() {
		ChromeOptions opt = new ChromeOptions();
		opt.addArguments("--ignore-ssl-errors=yes");
		opt.addArguments("--ignore-certificate-errors");
		opt.addArguments("--window-size=1920,1080");
		opt.addArguments("--no-sandbox");
		opt.addArguments("--disable-dev-shm-usage");
		opt.addArguments("--disable-gpu");
		return opt;
	}
}