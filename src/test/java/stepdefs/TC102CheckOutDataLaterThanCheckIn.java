package stepdefs;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class TC102CheckOutDataLaterThanCheckIn {

    WebDriver driver = new ChromeDriver();

    //Current date
    Date nowDay = new Date();
    SimpleDateFormat ft = new SimpleDateFormat ("/MM/YYYY");
    Calendar cal = Calendar.getInstance();

    @Given("the hotel reservation application website.")
    public void openAppWebsite1(){
        openWebsitePage("http://adactinhotelapp.com/");
    }
    @Then("I login to the application using valid username and password.")
    public void hotelAppLogIn1(){
        loginToWebsite("xyzusername", "xyzpassword");
    }
    @And("I select location to \"Sydney\".")
    public void selectLocation(){
        Select location = new Select(driver.findElement(By.id("location")));
        location.selectByValue("Sydney");
    }
    @And("I select hotel to \"Hotel Creek\".")
    public void selectHotel(){
        Select hotel = new Select(driver.findElement(By.id("hotels")));
        hotel.selectByValue("Hotel Creek");
    }
    @And("I select room type to \"Standard\".")
    public void selectRoom(){
        Select room = new Select(driver.findElement(By.id("room_type")));
        room.selectByValue("Standard");
    }
    @And("I select no-of-rooms to \"1\".")
    public void noOfRooms(){
        Select noOfRooms = new Select(driver.findElement(By.id("room_nos")));
        noOfRooms.selectByValue("1");
    }
    @And("I enter check-in-date to \"7\" days from now and the checkout-date to \"5\" days from now.")
    public void enterLaterDate (){
        WebElement checkInField = driver.findElement(By.id("datepick_in"));
        checkInField.clear();
        //Manipulate date
        int nowPlusSeven = cal.get(Calendar.DAY_OF_MONTH)+7;
        checkInField.sendKeys(nowPlusSeven+ft.format(nowDay));

        WebElement checkOutField = driver.findElement(By.id("datepick_out"));
        checkOutField.clear();
        int nowPlusFive= cal.get(Calendar.DAY_OF_MONTH);
        checkOutField.sendKeys(nowPlusFive+ft.format(nowDay));
    }
    @And("I click on Search button.")
    public void clickSearch(){
        WebElement submit = driver.findElement(By.id("Submit"));
        submit.click();
    }
    @Then("verify that system gives an error saying‘check-in-date should not be later than check-out-date’.")
    public void verifyError(){
        WebElement regError = driver.findElement(By.id("checkin_span"));
        String s = "Check-In Date shall be before than Check-Out Date";
        Assert.assertTrue(s.equals(regError.getText()));
        driver.close();
    }




    public void openWebsitePage(String s){
        driver.manage().window().maximize();
        driver.get(s);
    }
    public void loginToWebsite(String user, String pass) {

        WebElement username = driver.findElement(By.id("username"));
        username.sendKeys("xyzusername");
        WebElement password = driver.findElement(By.id("password"));
        password.sendKeys("xyzpassword");

        WebElement loginButton = driver.findElement(By.id("login"));
        loginButton.click();
        Assert.assertEquals("AdactIn.com - Search Hotel", driver.getTitle());
    }
}
