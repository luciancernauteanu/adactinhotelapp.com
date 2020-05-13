package stepdefs;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class TC103CheckErrorIfDateIsInThePast {

    WebDriver driver = new ChromeDriver();
    //Current date
    Date nowDay = new Date();
    SimpleDateFormat ft = new SimpleDateFormat ("/MM/YYYY");
    Calendar cal = Calendar.getInstance();

    @Given("the hotel application website.")
    public void openAppWebsite1(){
        openWebsitePage("http://adactinhotelapp.com/");
    }
    @Then("I login successfully to the application")
    public void hotelAppLogIn1(){
        loginToWebsite("xyzusername", "xyzpassword");
    }
    @And("I select location as \"Sydney\".")
    public void selectLocation(){
        Select location = new Select(driver.findElement(By.id("location")));
        location.selectByValue("Sydney");
    }
    @And("I select hotel as \"Hotel Creek\".")
    public void selectHotel(){
        Select hotel = new Select(driver.findElement(By.id("hotels")));
        hotel.selectByValue("Hotel Creek");
    }
    @And("I select room type as \"Standard\".")
    public void selectRoom(){
        Select room = new Select(driver.findElement(By.id("room_type")));
        room.selectByValue("Standard");
    }
    @And("I select no-of-rooms as \"1\".")
    public void noOfRooms(){
        Select noOfRooms = new Select(driver.findElement(By.id("room_nos")));
        noOfRooms.selectByValue("1");
    }
    @And("I enter check-in-date to \"-5\" days from now and the checkout-date to \"-3\" days from now.")
    public void enterLaterDate (){
        WebElement checkInField = driver.findElement(By.id("datepick_in"));
        checkInField.clear();

        //Manipulate date
        int nowMinusFive = cal.get(Calendar.DAY_OF_MONTH)-5;
        //Manipulate date
        checkInField.sendKeys(nowMinusFive+ft.format(nowDay));



        WebElement checkOutField = driver.findElement(By.id("datepick_out"));
        checkOutField.clear();

        int nowMinusThree = cal.get(Calendar.DAY_OF_MONTH)-3;
        checkOutField.sendKeys(nowMinusThree+ft.format(nowDay));
    }
    @And("I click the Search button")
    public void clickSearch(){
        WebElement submit = driver.findElement(By.id("Submit"));
        submit.click();
    }
    @Then("verify that System is reporting the error message ‘Enter Valid dates’.")
    public void verifyError(){

        //to be implemented when the "bug(ID)" is fixed
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
