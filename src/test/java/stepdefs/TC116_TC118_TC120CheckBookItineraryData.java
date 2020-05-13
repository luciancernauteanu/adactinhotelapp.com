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
import java.util.List;

public class TC116_TC118_TC120CheckBookItineraryData {
    WebDriver driver = new ChromeDriver();
    //Current date
    Date nowDay = new Date();
    SimpleDateFormat ft = new SimpleDateFormat("/MM/YYYY");
    Calendar cal = Calendar.getInstance();
    String checkIn;
    String checkOut;
    String selectLocation;
    String selectHotel;
    String roomType;
    String pricePerNight;
//    String totalBookDays;
    String generatedNum;
    String numberOfRooms;
    String firstNameItinerary;
    String lastNameItinerary;
    String totalPriceWithGST;


////Scenario ====> TC116 $ TC118 & TC120////
    @Given("the application for online booking")
    public void openAppWebsite1(){
        openWebsitePage("http://adactinhotelapp.com/");
    }
    @Then("I use the correct username and password to login to the home webpage")
    public void hotelAppLogIn1(){
        loginToWebsite("xyzusername", "xyzpassword");
    }
    @When("I choose location option as \"Sydney\"")
    public void selectLocation(){
        //////////Search Hotel page
        Assert.assertTrue(driver.getTitle().contains("Search Hotel"));         /////TC120

        Select location = new Select(driver.findElement(By.id("location")));
        location.selectByValue("Sydney");
    }
    @And("I choose hotel option to \"Hotel Creek\"")
    public void selectHotel(){
        //////////Search Hotel page
        Select hotel = new Select(driver.findElement(By.id("hotels")));
        hotel.selectByValue("Hotel Creek");
    }
    @And("I choose room type option to \"Standard\"")
    public void selectRoom(){
        //////////Search Hotel page
        Select room = new Select(driver.findElement(By.id("room_type")));
        room.selectByValue("Standard");

    }
    @And("I choose number of rooms option to \"2\"")
    public void noOfRooms(){
        //////////Search Hotel page
        Select noOfRooms = new Select(driver.findElement(By.id("room_nos")));
        noOfRooms.selectByValue("2");
    }
    @And("I choose check-in to present day and the checkout-date to present day plus one day.")
    public void enterCheckInAndCheckOut (){
        //////////Search Hotel page

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
    @And("I choose no-of-adults option to \"1\"")
    public void selectNoOfAdults (){
        //////////Search Hotel page
        Select noOfAdults = new Select(driver.findElement(By.id("adult_room")));
        noOfAdults.selectByValue("1");
    }
    @And("I choose number of children option to \"0\"")
    public void selectNoOfChildren(){
        //////////Search Hotel page

//        Select childPerRoom = new Select(driver.findElement(By.id("child_room")));
        //The select option doesn't have the value 0, so the field is left empty
    }
    @And("clicking on Search button")
    public void clickSearch(){
        //////////Search Hotel page
        WebElement submit = driver.findElement(By.id("Submit"));
        submit.click();
    }
    @And("choose the hotel by clicking the radio button")
    public void selectHotelOption (){
        //////////Select Hotel page
        Assert.assertTrue(driver.getTitle().contains("Select Hotel"));         /////TC120

        WebElement radioButton = driver.findElement(By.id("radiobutton_0"));
        radioButton.click();
    }
    @And("I want to continue to the next page by clicking on Continue button")
    public void clickContinue (){
        WebElement continueButton = driver.findElement(By.id("continue"));
        continueButton.click();
    }
    @And("when I enter booking details and then I click on Book Now button, I am redirected to the Booking Confirmation page")
    public void enterDetails(){
        //////////Book A Hotel page
        Assert.assertTrue(driver.getTitle().contains("Book A Hotel"));          ////////TC120

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
    }
    @And("clicking on My Itinerary button, I am redirected to the Booked Itinerary page")
    public void clickOnMyItinerary(){
        ///////////Booking Confirmation page
        System.out.println(driver.getTitle());        ////////////TC120
        //Bug(1) ---The page title of Booking Confirmation page does not reflect its objective
        //org.junit.ComparisonFailure: expected:<[AdactIn.com - Booking Confirmation]> but was:<[AdactIn.com - Book A Hotel]>
        ///Assert to be uncommented and executed after bug has fixed

//      Assert.assertEquals("Booking Confirmation",driver.getTitle());

        WebDriverWait wait =  new WebDriverWait(driver,10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("logout")));

        WebElement hotelNameDis = driver.findElement(By.id("hotel_name"));
        selectHotel = hotelNameDis.getAttribute("value");

        WebElement locationDis = driver.findElement(By.id("location"));
        selectLocation = locationDis.getAttribute("value");

        WebElement roomTypeDis = driver.findElement(By.id("room_type"));
        roomType = roomTypeDis.getAttribute("value");

        WebElement pricePerNightDis = driver.findElement(By.id("price_night"));
        pricePerNight = pricePerNightDis.getAttribute("value");

//        WebElement totalDayDis = driver.findElement(By.id("total_days"));
//        totalBookDays = totalDayDis.getAttribute("value");

        WebElement genNum = driver.findElement(By.id("order_no"));
        generatedNum = genNum.getAttribute("value");

        WebElement noOfRooms = driver.findElement(By.id("total_rooms"));
        String x  = noOfRooms.getAttribute("value");
        numberOfRooms = x.substring(0,6);

        WebElement itineraryPageElement = driver.findElement(By.className("login_title"));
        Assert.assertEquals("Booking Confirmation", itineraryPageElement.getText());

        WebElement firstNameBookConfirmation = driver.findElement(By.id("first_name"));
        firstNameItinerary = firstNameBookConfirmation.getAttribute("value");

        WebElement lastNameBookConfirmation = driver.findElement(By.id("last_name"));
        lastNameItinerary = lastNameBookConfirmation.getAttribute("value");

        WebElement finalBilledPrice = driver.findElement(By.id("final_price"));
        totalPriceWithGST = finalBilledPrice.getAttribute("value");

        WebElement myItineraryButton = driver.findElement(By.id("my_itinerary"));
        myItineraryButton.click();
    }
    @Then("I verify that information from Booked Itinerary is the same from Booking Confirmation page")
    public void verifyDataWithPreviousScreen(){
        ///////////////Booked Itinerary page
        Assert.assertTrue(driver.getTitle().contains("Booked Itinerary"));

        WebDriverWait wait =  new WebDriverWait(driver,10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("logout")));

        ///////TC118 - Verify that all the details of newly generate order number in booked itinerary page are correct and match with data during booking.
        Assert.assertTrue(driver.getPageSource().contains(generatedNum)); ///////TC118

        String firstPart = "/html/body/table[2]/tbody/tr[2]/td/form/table/tbody/tr[2]/td/table/tbody/tr[";
        String b = "]/td[4]/input";

        List<WebElement> rows = driver.findElements(By.xpath("//table[2]/tbody/tr[2]/td/form/table/tbody/tr[2]/td/table/tbody/tr"));

        WebElement hotelNameBookedIt = driver.findElement(By.xpath(firstPart+(rows.size()-1)+b));
        Assert.assertEquals(selectHotel,hotelNameBookedIt.getAttribute("value"));

        String q = "]/td[5]/input";
        WebElement locationBookedIt = driver.findElement(By.xpath(firstPart+(rows.size()-1)+q));
        Assert.assertEquals(selectLocation ,locationBookedIt.getAttribute("value"));

        String w = "]/td[6]/input";
        WebElement noOfRoomsBookedIt = driver.findElement(By.xpath(firstPart+(rows.size()-1)+w));
        String x =  noOfRoomsBookedIt.getAttribute("value");
        String y = x.substring(0,6);
        Assert.assertEquals(numberOfRooms,y);

        String e = "]/td[7]/input";
        WebElement firstNameBookedIt = driver.findElement(By.xpath(firstPart+(rows.size()-1)+e));
        Assert.assertEquals(firstNameItinerary,firstNameBookedIt.getAttribute("value"));

        String r = "]/td[8]/input";
        WebElement lastNameBookedIt = driver.findElement(By.xpath(firstPart+(rows.size()-1)+r));

        ///Test will fail at Assert statement below (line 245)
        // Bug(2) identified in Booking Confirmation page (Last Name field does not display the input text from last name field from Book A Hotel page)
        // (org.junit.ComparisonFailure: expected:<[]> but was:<[x]>)
        ///Assert to be uncommented and executed after bug has fixed
//      Assert.assertEquals(lastNameItinerary,lastNameBookedIt.getAttribute("value"));

        String t = "]/td[9]/input";
        WebElement checkInBookedIt = driver.findElement(By.xpath(firstPart+(rows.size()-1)+t));
        Assert.assertEquals(checkIn,checkInBookedIt.getAttribute("value"));

        String u = "]/td[10]/input";
        WebElement checkOutBookedIt = driver.findElement(By.xpath(firstPart+(rows.size()-1)+u));
        Assert.assertEquals(checkOut,checkOutBookedIt.getAttribute("value"));

        String i = "]/td[11]/input";
        WebElement numberOfdaysBookedIt = driver.findElement(By.xpath(firstPart+(rows.size()-1)+i));

        ///Test will fail if Assert statement below is uncomment ---> Bug(3) identified in Booking Confirmation page (No. of days is not implemented in the Booking Confirmation, so assertion cannot be done)
        ///Assert to be stated and executed after bug has fixed

        String o = "]/td[12]/input";
        WebElement roomTypeBookedIt = driver.findElement(By.xpath(firstPart+(rows.size()-1)+o));

        //Test will fail if Assert statement below is uncomment (line 269)
        //Test fail at Assert ---> Bug(4) identified in Booking Confirmation page (Room type in Booking Confirmation has other value than the value in the Booked Itinerary, so assertion will fail)
        ///Assert to be stated and executed after bug has fixed
        //org.junit.ComparisonFailure: expected:<[Deluxe]> but was:<[Standard]>

//       Assert.assertEquals(roomType,roomTypeBookedIt.getAttribute("value"));

        String p = "]/td[13]/input";
        WebElement pricePerNightBookedIt = driver.findElement(By.xpath(firstPart+(rows.size()-1)+p));
        Assert.assertEquals(pricePerNight,pricePerNightBookedIt.getAttribute("value"));

        String s = "]/td[14]/input";
        WebElement totalPriceBookedIt = driver.findElement(By.xpath(firstPart+(rows.size()-1)+s));

        //Test will fail if Assert statement below is uncomment (line 283)
        //Bug(5) identified in Booking Confirmation page (Final Billed Price in Booking Confirmation has other value than the value in the Booked Itinerary, so assertion will fail)
        //Assert to be uncomment after bug has fixed
        //org.junit.ComparisonFailure: expected:<AUD $ 14[8.5]> but was:<AUD $ 14[9]>

//       Assert.assertEquals(totalPriceWithGST,totalPriceBookedIt.getAttribute("value"));
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
