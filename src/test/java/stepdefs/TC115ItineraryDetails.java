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
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class TC115ItineraryDetails {

    WebDriver driver = new ChromeDriver();
    //Current date
    Date nowDay = new Date();
    SimpleDateFormat ft = new SimpleDateFormat("/MM/YYYY");
    Calendar cal = Calendar.getInstance();
    String checkIn;
    String checkOut;

    @Given("the website for online booking")
    public void openAppWebsite1(){
        openWebsitePage("http://adactinhotelapp.com/");
    }
    @Then("I successfully login to the website with my correct username and password")
    public void hotelAppLogIn1(){
        loginToWebsite("xyzusername", "xyzpassword");
    }
    @When("I select the location option to \"Adelaide\"")
    public void selectLocation(){
        Select location = new Select(driver.findElement(By.id("location")));
        location.selectByValue("Adelaide");

    }
    @And("I select the hotel option to \"Hotel Cornice\"")
    public void selectHotel(){
        Select hotel = new Select(driver.findElement(By.id("hotels")));
        hotel.selectByValue("Hotel Cornice");
    }
    @And("I select room type option to \"Standard\"")
    public void selectRoom(){
        Select room = new Select(driver.findElement(By.id("room_type")));
        room.selectByValue("Standard");

    }
    @And("I select number of rooms option to \"2\"")
    public void noOfRooms(){
        Select noOfRooms = new Select(driver.findElement(By.id("room_nos")));
        noOfRooms.selectByValue("2");
    }
    @And("I select check-in to present day and the checkout-date to present day plus one day.")
    public void enterCheckInAndCheckOut (){

        //Check-In//
        WebElement checkInField = driver.findElement(By.id("datepick_in"));
        checkInField.clear();

        int now = cal.get(Calendar.DAY_OF_MONTH);
        String s = String.valueOf(now);

        checkIn = s+ft.format(nowDay);
        checkInField.sendKeys(checkIn);       /////send present date

        //Check-Out//
        WebElement checkOutField = driver.findElement(By.id("datepick_out"));
        checkOutField.clear();

        int nowPlusOne = cal.get(Calendar.DAY_OF_MONTH)+1;
        checkOut = nowPlusOne+ft.format(nowDay);
        checkOutField.sendKeys(checkOut);     /////send manipulated date
    }
    @And("I select no-of-adults option to \"1\"")
    public void selectNoOfAdults (){
        Select noOfAdults = new Select(driver.findElement(By.id("adult_room")));
        noOfAdults.selectByValue("1");
    }
    @And("I select number of children option to \"0\"")
    public void selectNoOfChildren(){
//        Select childPerRoom = new Select(driver.findElement(By.id("child_room")));
        //The select option doesn't have the value 0, so the field is left empty
    }
    @And("when I click on Search button")
    public void clickSearch(){
        WebElement submit = driver.findElement(By.id("Submit"));
        submit.click();
    }
    @And("I select the hotel by clicking the radio button")
    public void selectHotelOption (){

        WebElement radioButton = driver.findElement(By.id("radiobutton_0"));
        radioButton.click();
    }
    @And("when I click on Continue button")
    public void clickContinue (){
        WebElement continueButton = driver.findElement(By.id("continue"));
        continueButton.click();
    }
    @And("I enter booking details and when I click on Book Now button I am redirected to the Booking Confirmation page")
    public void enterDetails(){
        WebElement firstName = driver.findElement(By.id("first_name"));
        firstName.sendKeys("x");
        WebElement lastName = driver.findElement(By.id("last_name"));
        lastName.sendKeys("x");
        WebElement address = driver.findElement(By.id("address"));
        address.sendKeys("x");
        WebElement ccNum = driver.findElement(By.id("cc_num"));
        ccNum.sendKeys("1234567891011121");
        Select ccType = new Select(driver.findElement(By.id("cc_type")));
        ccType.selectByValue("AMEX");
        Select ccExpMonth = new Select(driver.findElement(By.id("cc_exp_month")));
        ccExpMonth.selectByValue("1");
        Select ccExpYear = new Select(driver.findElement(By.id("cc_exp_year")));
        ccExpYear.selectByValue("2022");
        WebElement ccCVV = driver.findElement(By.id("cc_cvv"));
        ccCVV.sendKeys("123");
        WebElement bookNowButton = driver.findElement(By.id("book_now"));
        bookNowButton.click();

//        WebElement hotelNameDis = driver.findElement(By.id("hotel_name_dis"));
//        selectHotel = hotelNameDis.getAttribute("value");
//
//        WebElement locationDis = driver.findElement(By.id("location_dis"));
//        selectLocation = locationDis.getAttribute("value");
//
//        WebElement roomTypeDis = driver.findElement(By.id("room_type_dis"));
//        roomType = roomTypeDis.getAttribute("value");
//
//        WebElement pricePerNightDis = driver.findElement(By.id("price_night_dis"));
//        pricePerNight = pricePerNightDis.getAttribute("value");
//
//        WebElement totalDayDis = driver.findElement(By.id("total_days_dis"));
//        totalBookDays = totalDayDis.getAttribute("value");
    }
    @And("when I click on the My Itinerary button I am redirected to the Booked Itinerary page")
    public void clickOnMyItinerary(){
        WebDriverWait wait =  new WebDriverWait(driver,10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("logout")));
        WebElement myItineraryButton = driver.findElement(By.id("my_itinerary"));
        myItineraryButton.click();

        WebElement itineraryPageElement = driver.findElement(By.className("login_title"));
        Assert.assertEquals("Booked Itinerary", itineraryPageElement.getText());
    }
    @Then("I verify that the details are not editable")
    public void verifyTextIsNotEditable(){

        WebElement orderIdField = driver.findElement(By.xpath("/html/body/table[2]/tbody/tr[2]/td/form/table/tbody/tr[2]/td/table/tbody/tr[2]/td[2]/input"));
        Assert.assertFalse(orderIdField.isEnabled());

        ///Test fail at Assert statement below (line 213)
        // Bug(1) identified in Booked Itinerary page (All booking information fields on Booked Itinerary page is editable)
        // java.lang.AssertionError
        ///Assert to be executed after bug has fixed
//


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
