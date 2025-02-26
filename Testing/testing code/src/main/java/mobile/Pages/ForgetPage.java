package mobile.Pages;


import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ForgetPage extends Pages {

    /**
     *  declare the driver which used to access elements
     */
    AndroidDriver driver;

    /**
     * this is the button that will take us to the forget username custom page
     */
    @FindBy(xpath = "//android.widget.Button[@content-desc=\"Forget Username?\"]")
    WebElement forgetUserNameBtn;

    /**
     * this is the input text through which we will enter the usermail
     */
    @FindBy(xpath = "//android.widget.EditText[1]")
    WebElement forgetUserEmailInputText;

    /**
     * this is the input text through which we will enter the usermail
     */
    @FindBy(xpath = "//android.widget.EditText[2]")
    WebElement forgetUserEmailInputText2;

    /**
     * this is the continue button
     */
    @FindBy(xpath = "//android.widget.Button[@content-desc=\"Continue\"]")
    WebElement continueBtn;

    /**
     * this is the constructor of the class and it initializes all of its members using PageFactory class
     *
     * @param driver : which is generated by selenium testcase
     */
    public ForgetPage(AndroidDriver driver) {
        super(driver);
        this.driver = driver;
    }

    /**
     * this function is used to forget the username
     */
    public boolean forgetUserName(String email){
        forgetUserNameBtn.click();
        waitForWebElementToAppear(forgetUserEmailInputText, 3);
        forgetUserEmailInputText.click();
        threadSleep(1);
        forgetUserEmailInputText.sendKeys(email);
        threadSleep(1);

        if (!continueBtn.isEnabled())
            return false;
        else{
            continueBtn.click();
            threadSleep(1);
            return true;
        }
    }

    /**
     * this function is used to forget the password
     */
    public boolean forgetUserPass(String email, String username){
        forgetUserEmailInputText2.click();
        threadSleep(1);
        forgetUserEmailInputText2.sendKeys(email);
        threadSleep(1);
        driver.hideKeyboard();
        forgetUserEmailInputText.click();
        threadSleep(1);
        forgetUserEmailInputText.sendKeys(username);
        driver.hideKeyboard();

        if (!continueBtn.isEnabled())
            return false;
        else{
            continueBtn.click();
            threadSleep(3);
            return true;
        }
    }

}
