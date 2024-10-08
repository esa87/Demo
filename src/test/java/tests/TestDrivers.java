package tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import io.qameta.allure.Feature;
import po.DriverPage;
import po.LoginPage;
import po.MenuPage;
import po.VehiclesPage;

@Feature("Тесты проверки создания удаления водителей и ТС")
@ExtendWith(TestListener.class)
public class TestDrivers extends AbstractTest {

	private static String loginAdminKD = "adm";
	private static String pwdAdminKD = "123";

	private static String loginOper = "oper";
	private static String pwdOper = "123";

	private static String loginUser = "user";
	private static String pwdUser = "123";

	@Test
	@DisplayName("Создание удаление карточки водителя под ролью администратора деп.")
	public void createAndDeleteDriverAdmin() throws InterruptedException {
		new LoginPage(driver).loginAs(loginAdminKD, pwdAdminKD);
		Boolean rez = new MenuPage(driver).selectTabMenuDrivers().createDriverSmallData().bigPhotoMessageEmpty();
		if (rez != false) {
			new DriverPage(driver).clickBigPhotoMessage();
			System.out.println("!!!!Wrong message");
		}
		String getSurname = new DriverPage(driver).getSurname("Сидоров");
		Assertions.assertEquals("СИДОРОВ", getSurname);
		String rez2 = new MenuPage(driver).selectTabMenuDrivers().deleteDriver("Сидоров");
		Assertions.assertNotEquals("СИДОРОВ", rez2);
	}

	@Test
	@DisplayName("Создание удаление карточки водителя под ролью экспедитора")
	public void createAndDeleteDriverOrg() throws InterruptedException {
		new LoginPage(driver).loginAs(loginOper, pwdOper);
		Boolean rez = new MenuPage(driver).selectTabMenuDrivers().createDriverSmallData().bigPhotoMessageEmpty();
		if (rez != false) {
			new DriverPage(driver).clickBigPhotoMessage();
			System.out.println("!!!!Wrong message");
		}
		String getSurname = new DriverPage(driver).getSurname("Сидоров");
		Assertions.assertEquals("СИДОРОВ", getSurname);
		String rez2 = new MenuPage(driver).selectTabMenuDrivers().deleteDriver("Сидоров");
		Assertions.assertNotEquals("СИДОРОВ", rez2);
	}

	@Test
	@DisplayName("Создание удаление карточки водителя под ролью покупателя")
	public void createAndDeleteDriverBuyer() throws InterruptedException {
		new LoginPage(driver).loginAs(loginUser, pwdUser);
		Boolean rez = new MenuPage(driver).selectTabMenuDrivers().createDriverSmallData().bigPhotoMessageEmpty();
		if (rez != false) {
			new DriverPage(driver).clickBigPhotoMessage();
			System.out.println("!!!!Wrong message");
		}
		String getSurname = new DriverPage(driver).getSurname("Сидоров");
		Assertions.assertEquals("СИДОРОВ", getSurname);
		String rez2 = new MenuPage(driver).selectTabMenuDrivers().deleteDriver("Сидоров");
		Assertions.assertNotEquals("СИДОРОВ", rez2);
	}

	@Test
	@DisplayName("Создание удаление карточки ТС под ролью администратора деп.")
	public void createAndDeleteVehicle() throws InterruptedException {
		new LoginPage(driver).loginAs(loginAdminKD, pwdAdminKD);
		String rezString = new MenuPage(driver).selectTabMenuDrivers().goToVehiclesPage().createVehicle()
				.getCarNumberValue();
		Assertions.assertEquals(rezString, "12355");
		Boolean rez2 = new VehiclesPage(driver).deleteVehicle();
		Assertions.assertTrue(rez2);
	}

	@Test
	@DisplayName("Создание удаление карточки ТС под ролью экспедитора")
	public void createAndDeleteVehicleOrg() throws InterruptedException {
		new LoginPage(driver).loginAs(loginOper, pwdOper);
		String rezString = new MenuPage(driver).selectTabMenuDrivers().goToVehiclesPage().createVehicle()
				.getCarNumberValue();
		Assertions.assertEquals(rezString, "12355");
		Boolean rez2 = new VehiclesPage(driver).deleteVehicle();
		Assertions.assertTrue(rez2);
	}

	@Test
	@DisplayName("Создание удаление карточки ТС под ролью покупателя")
	public void createAndDeleteVehicleBuyer() throws InterruptedException {
		new LoginPage(driver).loginAs(loginUser, pwdUser);
		String rezString = new MenuPage(driver).selectTabMenuDrivers().goToVehiclesPage().createVehicle()
				.getCarNumberValue();
		Assertions.assertEquals(rezString, "12355");
		Boolean rez2 = new VehiclesPage(driver).deleteVehicle();
		Assertions.assertTrue(rez2);
	}
}
