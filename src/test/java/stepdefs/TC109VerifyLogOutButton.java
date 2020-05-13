package stepdefs;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
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


public class TC109VerifyLogOutButton {

    WebDriver driver = new ChromeDriver();
    //Current date
    Date nowDay = new Date();
    SimpleDateFormat ft = new SimpleDateFormat("/MM/YYYY");
    Calendar cal = Calendar.getInstance();

    @Given("the Booking website Adactin.com")
    public void openAppWebsite1(){
        openWebsitePage("http://adactinhotelapp.com/");
    }
    @Then("I successfully login to the website with my username and password")
    public void hotelAppLogIn1(){
        loginToWebsite("xyzusername", "xyzpassword");
    }
    @And("I select the location option as \"Sydney\".")
    public void selectLocation(){
        Select location = new Select(driver.findElement(By.id("location")));
        location.selectByValue("Sydney");
    }
    @And("I select the hotel option as \"Hotel Creek\".")
    public void selectHotel(){
        Select hotel = new Select(driver.findElement(By.id("hotels")));
        hotel.selectByValue("Hotel Creek");
        WebElement hotelCreek = hotel.getFirstSelectedOption();

    }
    @And("I select the room type option as \"Standard\".")
    public void selectRoom(){
        Select room = new Select(driver.findElement(By.id("room_type")));
        room.selectByValue("Standard");
    }
    @And("I select the number of rooms option as \"2\".")
    public void noOfRooms(){
        Select noOfRooms = new Select(driver.findElement(By.id("room_nos")));
        noOfRooms.selectByValue("2");
    }
    @And("I select check-in-date to present date and the checkout-date to present date plus one day.")
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
    @And("I select the no-of-adults option as \"1\".")
    public void selectNoOfAdults (){
        Select noOfAdults = new Select(driver.findElement(By.id("adult_room")));
        noOfAdults.selectByValue("1");
    }
    @And("I select the number of children option as \"0\".")
    public void selectNoOfChildren(){
        //The select option doesn't have the value 0, so the field is left empty
    }
    @And("then I click the Search button")
    public void clickSearch(){
        WebElement submit = driver.findElement(By.id("Submit"));
        submit.click();
    }
    @And("I select the hotel option that I want to book.")
    public void selectHotelOption (){
        WebElement radioButton = driver.findElement(By.id("radiobutton_0"));
        radioButton.click();
    }
    @And("then I click Continue button")
    public void clickContinue (){
        WebElement continueButton = driver.findElement(By.id("continue"));
        continueButton.click();
    }
    @And("I enter the details and click on book now.")
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
    }
    @Then("I check the details,click on logout and verify we have been logged out of the application.")
    public void logoutButton(){

        WebDriverWait wait =  new WebDriverWait(driver,10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("logout")));

        WebElement logoutButton = driver.findElement(By.id("logout"));
        logoutButton.click();

        System.out.println();

        WebElement linkText = driver.findElement(By.className("reg_success"));
        Assert.assertEquals("You have successfully logged out. Click here to login again", linkText.getText());
        driver.close();
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
