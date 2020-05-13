package stepdefs;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.Alert;
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

public class TC117_TC119SearchAndCancelOrderId {
    WebDriver driver = new ChromeDriver();
    //Current date
    Date nowDay = new Date();
    SimpleDateFormat ft = new SimpleDateFormat("/MM/YYYY");
    Calendar cal = Calendar.getInstance();
    String checkIn;
    String checkOut;
    String generatedOrderNumber;
    String orderNumber;


////Scenario ====> TC117
    @Given("the book a hotel site")
    public void openAppWebsite1(){
        openWebsitePage("http://adactinhotelapp.com/");
    }
    @Then("I use my current user and pass to login to the app")
    public void hotelAppLogIn1(){
        loginToWebsite("xyzusername", "xyzpassword");
    }
    @When("the location option is selected as \"Sydney\"")
    public void selectLocation(){
        Select location = new Select(driver.findElement(By.id("location")));
        location.selectByValue("Sydney");

    }
    @And("the hotel option is selected as \"Hotel Creek\"")
    public void selectHotel(){
        Select hotel = new Select(driver.findElement(By.id("hotels")));
        hotel.selectByValue("Hotel Creek");

    }
    @And("the room type option is selected as \"Standard\"")
    public void selectRoom(){
        Select room = new Select(driver.findElement(By.id("room_type")));
        room.selectByValue("Standard");

    }
    @And("the number of rooms option  is selected as \"2\"")
    public void noOfRooms(){
        Select noOfRooms = new Select(driver.findElement(By.id("room_nos")));
        noOfRooms.selectByValue("2");
    }
    @And("the check-in is selected as present day and the checkout-date is selected to next day")
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
    @And("the no-of-adults is selected as \"1\"")
    public void selectNoOfAdults (){
        Select noOfAdults = new Select(driver.findElement(By.id("adult_room")));
        noOfAdults.selectByValue("1");
    }
    @And("the number of children option is selected as none")
    public void selectNoOfChildren(){
//        Select childPerRoom = new Select(driver.findElement(By.id("child_room")));
        //The select option doesn't have the value 0, so the field is left empty
    }
    @And("when Search is clicked")
    public void clickSearch(){
        WebElement submit = driver.findElement(By.id("Submit"));
        submit.click();
    }
    @And("the hotel is selected by clicking the radio button")
    public void selectHotelOption (){

        WebElement radioButton = driver.findElement(By.id("radiobutton_0"));
        radioButton.click();
    }
    @And("if Continue button is clicked, the next page is opened")
    public void clickContinue (){
        WebElement continueButton = driver.findElement(By.id("continue"));
        continueButton.click();
    }
    @When("booking details is entered in the fields and clicking on Book Now button and the Booking Confirmation page is opened")
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

        WebDriverWait wait =  new WebDriverWait(driver,10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("logout")));
    }
    @Then("a order number is generated")
    public void generatedOrderNum (){
        WebElement orderNum = driver.findElement(By.id("order_no"));
        generatedOrderNumber = orderNum.getAttribute("value");
        Assert.assertTrue(!generatedOrderNumber.equals(""));
    }
    @When("My Itinerary button is clicked, I am redirected to the Booked Itinerary page")
    public void clickOnMyItinerary(){

        WebElement myItineraryButton = driver.findElement(By.id("my_itinerary"));
        myItineraryButton.click();
    }
    @And("I enter the the order Id into the \"Search Order Id\" field in the upper right corner of the page and click Go button")
    public void enterIdIntoSearchIdField(){
        WebElement searchQuery = driver.findElement(By.id("order_id_text"));
        searchQuery.sendKeys(generatedOrderNumber);

        WebElement goButton = driver.findElement(By.id("search_hotel_id"));
        goButton.click();
    }
    @Then("all the relevant details for this order Id are displayed")
    public void orderIdInfoIsDisplayed (){
        WebElement orderIdSearched = driver.findElement(By.xpath("/html/body/table[2]/tbody/tr[2]/td/form/table/tbody/tr[2]/td/table/tbody/tr[2]/td[2]/input"));
        orderNumber = orderIdSearched.getAttribute("value");

        Assert.assertEquals(generatedOrderNumber,orderNumber);
    }

    ////Scenario ====> TC119

    @Then("clicking Cancel order will cancel the order")
    public void cancelOrder(){
        WebElement cancelButton = driver.findElement(By.xpath("/html/body/table[2]/tbody/tr[2]/td/form/table/tbody/tr[2]/td/table/tbody/tr[2]/td[3]/input"));
        cancelButton.click();
        Alert alert = driver.switchTo().alert();
        alert.accept();

        Assert.assertTrue(!driver.getPageSource().contains(orderNumber));

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
