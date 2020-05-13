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

public class TC108_TC112VerifyPrices {
    WebDriver driver = new ChromeDriver();
    //Current date
    Date nowDay = new Date();
    SimpleDateFormat ft = new SimpleDateFormat("/MM/YYYY");
    Calendar cal = Calendar.getInstance();
    int priceRoom;
    int noRooms;
    int noDays;

    ////Scenario ====> TC108////
    @Given("the Adactin Booking website.")
    public void openAppWebsite1(){
        openWebsitePage("http://adactinhotelapp.com/");
    }
    @Then("I login successfully to the website with my username and password")
    public void hotelAppLogIn1(){
        loginToWebsite("xyzusername", "xyzpassword");
    }
    @And("I select the location as \"Sydney\".")
    public void selectLocation(){
        Select location = new Select(driver.findElement(By.id("location")));
        location.selectByValue("Sydney");
    }
    @And("I select the hotel as \"Hotel Creek\".")
    public void selectHotel(){
        Select hotel = new Select(driver.findElement(By.id("hotels")));
        hotel.selectByValue("Hotel Creek");
        WebElement hotelCreek = hotel.getFirstSelectedOption();

    }
    @And("I select the room type as \"Standard\".")
    public void selectRoom(){
        Select room = new Select(driver.findElement(By.id("room_type")));
        room.selectByValue("Standard");
    }
    @And("I select the number of rooms to \"2\".")
    public void noOfRooms(){
        Select noOfRooms = new Select(driver.findElement(By.id("room_nos")));
        noOfRooms.selectByValue("2");
    }
    @And("I select check-in-date to today’s date and the checkout-date is \"1\" day from today.")
    public void enterCheckInAndCheckOut (){

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

    }
    @And("I select the no-of-adults as \"1\".")
    public void selectNoOfAdults (){
        Select noOfAdults = new Select(driver.findElement(By.id("adult_room")));
        noOfAdults.selectByValue("1");
    }
    @And("I select the number of children as \"0\".")
    public void selectNoOfChildren(){
        //The select option doesn't have the value 0, so the field is left empty
    }
    @And("then I click on Search button")
    public void clickSearch(){
        WebElement submit = driver.findElement(By.id("Submit"));
        submit.click();
    }
    @And("I select the hotel I want to book.")
    public void selectHotelOption (){
        WebElement radioButton = driver.findElement(By.id("radiobutton_0"));
        radioButton.click();
    }
    @And("I click Continue button")
    public void clickContinue (){
        WebElement continueButton = driver.findElement(By.id("continue"));
        continueButton.click();
    }
    @Then("I verify that total price is being calculated as price-per-night,no-of-rooms,no-of-days.")
    public void verifyPrice(){

        WebElement pricePerNight = driver.findElement(By.id("price_night_dis"));
        String s =  pricePerNight.getAttribute("value");
        String s1 = s.substring(6,s.length()+0);
        priceRoom = Integer.parseInt(s1);
        System.out.println(priceRoom);

        WebElement noOfRooms = driver.findElement(By.id("room_num_dis"));
        String p = noOfRooms.getAttribute("value");
        String p1 = p.substring(0,1);
        noRooms = Integer.parseInt(p1);
        System.out.println(noRooms);

        WebElement noOfDays = driver.findElement(By.id("total_days_dis"));
        String i = noOfDays.getAttribute("value");
        String i1 =i.substring(0,1);
        noDays = Integer.parseInt(i1);
        System.out.println(noDays);

        int t = priceRoom*noRooms*noDays;
        System.out.println(t);

        WebElement totalPrice = driver.findElement(By.id("total_price_dis"));
        String l = totalPrice.getAttribute("value");
        String l1 =l.substring(6,l.length()+0);
        int totalPriceInteger = Integer.parseInt(l1);
        System.out.println(l1);
        Assert.assertEquals(totalPriceInteger,t);

        //The test failed ----Total price is not updated correctly (the number of rooms * price per night is not calculated correctly)
        //Bug to be fixed!
    }

    ////Scenario ====> TC112////
    @Then("I verify that Final billed price in Book a Hotel page is Total Price plus \"10\" percent Total price.")
    public void verifyBilledPrice(){

        WebElement pricePerNight = driver.findElement(By.id("price_night_dis"));
        String s =  pricePerNight.getAttribute("value");
        String s1 = s.substring(6,s.length()+0);
        priceRoom = Integer.parseInt(s1);

        WebElement noOfRooms = driver.findElement(By.id("room_num_dis"));
        String p = noOfRooms.getAttribute("value");
        String p1 = p.substring(0,1);
        noRooms = Integer.parseInt(p1);

        WebElement noOfDays = driver.findElement(By.id("total_days_dis"));
        String i = noOfDays.getAttribute("value");
        String i1 =i.substring(0,1);
        noDays = Integer.parseInt(i1);

        int t = priceRoom*noRooms*noDays;

        double priceGST = t*10/100;
//        System.out.println(priceGST); 10%
        double finalBill = t + priceGST;

        String finalBillString = String.valueOf(finalBill);


        WebElement totalPrice = driver.findElement(By.id("final_price_dis"));
        String l = totalPrice.getAttribute("value");
        String finalBillDisplayed = l.substring(6,l.length()+0);

        Assert.assertEquals(finalBillString,finalBillDisplayed);

        //The test failed (org.junit.ComparisonFailure: expected:<[275.0]> but was:<[148.5]>)
        //Final Billed Price is not updated correctly (the number of rooms * price per night + GST is not calculated correctly)
        //Assert to be executed after bug is fixed
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
