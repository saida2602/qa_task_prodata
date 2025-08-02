package stepdefinitions;

import io.cucumber.java.en.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.net.MalformedURLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LoginStepDefinitions {

    WebDriver driver;

    @Given("I open the login page")
    public void i_open_the_login_page() throws MalformedURLException {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--incognito");
        driver = new ChromeDriver(options);
        driver.get("https://www.saucedemo.com/v1/");
        waitSeconds(1);
//        driver = new ChromeDriver();
    }

    public void waitSeconds(int seconds) {
        try {
            Thread.sleep(seconds * 1000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @When("I enter username {string}")
    public void i_enter_username(String username) {
        driver.findElement(By.id("user-name")).sendKeys(username);
        waitSeconds(1);
    }

    @When("I enter password {string}")
    public void i_enter_password(String password) {
        driver.findElement(By.id("password")).sendKeys(password);
        waitSeconds(1);
    }

    @When("I click the login button")
    public void i_click_the_login_button() {
        driver.findElement(By.id("login-button")).click();
        waitSeconds(1);
    }

    @Then("I should be redirected to the inventory page")
    public void i_should_be_redirected_to_the_inventory_page() {
        assertTrue(driver.getCurrentUrl().contains("inventory"));
    }

    @And("I select product id to the cart:")
    public void i_select_product_ids_to_the_cart(List<Integer> ids)  {
        List<WebElement> addToCartButtons = driver.findElements(By.cssSelector(".btn_primary.btn_inventory"));
        for (Integer index : ids) {
            addToCartButtons.get(index).click();
            waitSeconds(1);
        }

    }

    @And("I go to the cart page")
    public void i_go_to_the_cart_page() {
        driver.findElement(By.className("shopping_cart_link")).click();
        waitSeconds(1);
        System.out.println("driver current url=" + driver.getCurrentUrl());
    }

    @Then("I should be on the cart page")
    public void i_should_be_on_the_cart_page() {
        assertTrue(driver.getCurrentUrl().contains("cart"));
        waitSeconds(1);

    }

    @And("I go to the checkout page")
    public void i_go_to_the_checkout_page() {
        driver.findElement(By.className("checkout_button")).click();
        System.out.println("driver current url=" + driver.getCurrentUrl());
        waitSeconds(1);

    }

    @Then("I should be on the checkout page")
    public void i_should_be_on_the_checkout_page() {
        assertTrue(driver.getCurrentUrl().contains("checkout-step-one"));
        waitSeconds(1);
    }

    @And("I enter firstname {string}")
    public void i_enter_firstname(String firstname) {
        driver.findElement(By.id("first-name")).sendKeys(firstname);
        waitSeconds(1);
    }

    @And("I enter lastname {string}")
    public void i_enter_lastname(String lastname) {
        driver.findElement(By.id("last-name")).sendKeys(lastname);
        waitSeconds(1);
    }

    @And("I enter postal-code {string}")
    public void i_enter_postal_codee(String postalCode)  {
        driver.findElement(By.id("postal-code")).sendKeys(postalCode);
        waitSeconds(1);
    }

    @And("I click continue button")
    public void i_clikc_continue_button() {
        driver.findElement(By.className("cart_button")).click();
        waitSeconds(1);
    }

    @Then("I should be redirected to the cart page")
    public void i_should_be_redirected_to_the_checkout_step_two_page() {
        assertTrue(driver.getCurrentUrl().contains("checkout-step-two"));
        waitSeconds(1);

    }

    @And("I click finish order button")
    public void i_click_finish_order_button(){
        driver.findElement(By.className("cart_button")).click();
        waitSeconds(1);
    }

    @Then("I should be redirected to the checkout-complete page")
    public void i_should_be_redirected_to_the_checkout_complete_page() {
        WebElement confirmationMessage = driver.findElement(By.cssSelector("h2.complete-header"));
        String actualText = confirmationMessage.getText();
        assertTrue(driver.getCurrentUrl().contains("checkout-complete"));
        assertEquals("THANK YOU FOR YOUR ORDER", actualText);
        waitSeconds(5);
        driver.quit();
    }
}