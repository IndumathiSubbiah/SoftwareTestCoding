package pageObjects;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

public class TestProjectActions {

    static By GITHUB = By.xpath("/html/body/ng-view/div/a[4]");
    static By USERNAME = By.id("login_field");
    static By PASSWORD = By.id("password");
    static By LOGINBUTTON = By.name("commit");
    static By NEW_TODO_LIST = By.xpath("//input[@ng-model='home.list']");
    static By ADDLISTBUTTON = By.xpath("//button[@ng-click='home.list && home.add()']");
    static By LOGOUT = By.xpath("//button[@ng-click='home.signOut()']");
    static String DELETEITEM = "(//button[@ng-click='home.delete($index)'])[%s]";

    static  WebDriver driver;
    static boolean driverLaunch = false;
    Properties prop = new Properties();

    public void launchApplication() throws IOException {
        if(!driverLaunch) {
            System.setProperty("webdriver.chrome.driver", "src/test/resources/drivers/windows/chromedriver.exe");
            driver = new ChromeDriver();
            driver.manage().window().maximize();
            driverLaunch=true;

            prop.load(new FileInputStream("src/test/resources/Properties/testProject.properties"));
            driver.get(prop.getProperty("URL"));
        }
    }

    public void enterCredentials() throws IOException {
       String winHandleBefore = driver.getWindowHandle();
       driver.switchTo().window(winHandleBefore);
       WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
       wait.until(ExpectedConditions.visibilityOfElementLocated(GITHUB));
       driver.findElement(GITHUB).click();
       prop.load(new FileInputStream("src/test/resources/Properties/testProject.properties"));

       for(String winHandle : driver.getWindowHandles()){
            driver.switchTo().window(winHandle);
        }

        wait.until(ExpectedConditions.visibilityOfElementLocated(USERNAME));
        driver.findElement(USERNAME).sendKeys(prop.getProperty("Username"));
        driver.findElement(PASSWORD).sendKeys(new String(Base64.decodeBase64(prop.getProperty("Password"))));
        driver.findElement(LOGINBUTTON).click();
        driver.switchTo().window(winHandleBefore);
    }

    public void itemsAdded(int item) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.visibilityOfElementLocated(ADDLISTBUTTON));
        for (int i = 1; i <= item; i++) {
            driver.findElement(NEW_TODO_LIST).sendKeys(RandomStringUtils.randomAlphabetic(3)+" "+i);
            driver.findElement(ADDLISTBUTTON).click();
        }
    }

    public void logOut() {
        driver.findElement(LOGOUT).click();
        System.out.println("Logged Out Successfully");
    }

    public void loginAgain() {
        driver.findElement(GITHUB).click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.visibilityOfElementLocated(ADDLISTBUTTON));
    }

    public void itemsDeleted(int min, int max) {
        for (int i = max; i >= min; i--) {
            driver.findElement(By.xpath(String.format(DELETEITEM,i))).click();
        }
    }

    public void quit() {
        driver.close();
    }

}
