package stepdefs;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import cucumber.api.java.en_scouse.An;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class TC104_TC105_TC106_TC107DataInSelectHotelSameAsSelectHotel {

    WebDriver driver = new ChromeDriver();
    //Current date
    Date nowDay = new Date();
    SimpleDateFormat ft = new SimpleDateFormat("/MM/YYYY");
    Calendar cal = Calendar.getInstance();
    String verifyHotel;
    String verifyCheckOut;
    String verifyCheckIn;
    String verifyNoOfRooms;
    String verifyRoomType;

    ////Scenario ====> TC104////

    @Given("the Adactin.com Booking website.")
    public void openAppWebsite1(){
        openWebsitePage("http://adactinhotelapp.com/");
    }
    @Then("I login to website successfully with my credentials.")
    public void hotelAppLogIn1(){
        loginToWebsite("xyzusername", "xyzpassword");
    }
    @And("I select my location as \"Sydney\".")
    public void selectLocation(){
        Select location = new Select(driver.findElement(By.id("location")));
        location.selectByValue("Sydney");
    }
    @And("I select my hotel as \"Hotel Creek\".")
    public void selectHotel(){
        Select hotel = new Select(driver.findElement(By.id("hotels")));
        hotel.selectByValue("Hotel Creek");
        WebElement hotelCreek = hotel.getFirstSelectedOption();
        verifyHotel=hotelCreek.getText();
    }
    @And("I select my room type as \"Standard\".")
    public void selectRoom(){
        Select room = new Select(driver.findElement(By.id("room_type")));
        room.selectByValue("Standard");
    }
    @And("I select number of rooms to \"1\".")
    public void noOfRoomsTC104(){
        Select noOfRooms = new Select(driver.findElement(By.id("room_nos")));
        noOfRooms.selectByValue("1");
    }
    @And("I enter check-in-date to today’s date and the checkout-date is \"1\" day from today.")
    public void enterCheckInAndCheckOutDate (){

        WebElement checkInField = driver.findElement(By.id("datepick_in"));
        checkInField.clear();

        //Present Date
        int now = cal.get(Calendar.DAY_OF_MONTH);
        String s = String.valueOf(now);

        checkInField.sendKeys(s+ft.format(nowDay));//send present date

        WebElement checkOutField = driver.findElement(By.id("datepick_out"));
        checkOutField.clear();

        //Manipulate date
        int nowPlusOne = cal.get(Calendar.DAY_OF_MONTH)+1;
        checkOutField.sendKeys(nowPlusOne+ft.format(nowDay));//send manipulated date

        verifyCheckIn = s+ft.format(nowDay);
        verifyCheckOut = nowPlusOne+ft.format(nowDay);
    }
    @And("I select No-of-adults as \"1\"")
    public void selectNoOfAdults (){
        Select noOfAdults = new Select(driver.findElement(By.id("adult_room")));
        noOfAdults.selectByValue("1");
    }
    @And("number of children as \"0\"")
    public void selectNoOfChildren(){
        //The select option doesn't have the value 0, so the field is left empty
    }
    @And("I click on Search button")
    public void clickSearch(){
        WebElement submit = driver.findElement(By.id("Submit"));
        submit.click();
    }
    @Then("I verify that hotel displayed in the Select Hotel page is the same as selected in search Hotel form.")
    public void verifyIfHotelIsDisplayedOnBothPage (){

      WebElement selectHotelPage = driver.findElement(By.id("hotel_name_0"));

        Assert.assertEquals(selectHotelPage.getAttribute("value"),verifyHotel);
    }

    ////Scenario ====> TC105////
    @Then("verify thatCheck-in date and Check Out date selected in the Select Hotel page are the same according to the dates selected in Search Hotel page.")
    public void verifyCheckInAndCheckOutDate (){

        WebElement arrivalDate = driver.findElement(By.id("arr_date_0"));
        WebElement departureDate = driver.findElement(By.id("dep_date_0"));

        Assert.assertEquals(verifyCheckIn, arrivalDate.getAttribute("value"));
        Assert.assertEquals(verifyCheckOut,departureDate.getAttribute("value"));

        driver.close();
    }

    ////Scenario ====> TC106////
    @And("I select number of rooms to \"3\".")
    public void noOfRoomsTC105(){
        Select noOfRooms = new Select(driver.findElement(By.id("room_nos")));
        noOfRooms.selectByValue("3");

        String s = noOfRooms.getFirstSelectedOption().getText();
        verifyNoOfRooms = s.substring(0,1);
    }
    @Then("verify that number of rooms displayed in the Select Hotel page are the same according to the number of rooms selected in Search Hotel page.")
    public void VerifyDatesAreDisplayedOnBothPage(){
        WebElement selectedNoOfRooms = driver.findElement(By.id("rooms_0"));
        Assert.assertEquals(verifyNoOfRooms+" Rooms", selectedNoOfRooms.getAttribute("value"));
        driver.close();
    }

    ////Scenario ====> TC107////
    @And("I select my room type as \"Deluxe\".")
    public void changeSelectRoom(){
        Select room = new Select(driver.findElement(By.id("room_type")));
        room.selectByValue("Deluxe");
        verifyRoomType = room.getFirstSelectedOption().getText();
    }
    @Then("verify that room type displayed in the Select Hotel page are the same according to the room type selected in Search Hotel page.")
    public void verifyRoomTypeIsDisplayedOnBothPage (){
        WebElement roomTypeSelectedHotelPage = driver.findElement(By.id("room_type_0"));
        Assert.assertEquals(verifyRoomType, roomTypeSelectedHotelPage.getAttribute("value"));
    }

    ///////My methods
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
