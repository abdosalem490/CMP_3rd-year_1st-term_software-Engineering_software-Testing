package mobile.Pages;


import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * This class for NoNlegit app starting page with activity""
 */

public class StartingPage extends Pages{

    /**
     *  declare the driver which used to access elements
     */
    AndroidDriver driver;

    /**
     * this is the login email input text
     */
    @FindBy(xpath = "//android.widget.EditText[1]")
    WebElement loginEmailInputText;

    /**
     * this is the login password input text
     */
    @FindBy(xpath = "//android.widget.EditText[2]")
    WebElement loginPasswordInputText;

    /**
     * this is the continue button in the login page
     */
    @FindBy(xpath = "//android.widget.Button[@content-desc=\"Continue\"]")
    WebElement loginContinueButton;

    /**
     * this is the 1st error message in the login page
     */
    @FindBy(xpath = "//android.view.View[@content-desc=\"User Not Found\"]")
    WebElement errorMessage1;

    /**
     * this is the button that will take us to the forget username/password page
     */
    @FindBy(xpath = "//android.widget.Button[@content-desc=\"Forget passward\"]")
    WebElement forgetPass;

    /**
     * this is the button that takes us to the sign up page
     */
    @FindBy(xpath = "//android.widget.Button[@content-desc=\"Sign up\"]")
    WebElement SignUpButton;

    /**
     * this is the google login button
     */
    @FindBy(xpath = "//android.widget.Button[@content-desc=\"Continue with Google\"]")
    WebElement googleLoginBtn;

    /**
     * this is the facebook login button
     */
    @FindBy(xpath = "//android.widget.Button[@content-desc=\"Continue with Face Book\"]")
    WebElement facebookLoginBtn;

    /**
     * this is the constructor of the class and it initializes all of its members using PageFactory class
     *
     * @param driver : which is generated by selenium testcase
     */
    public StartingPage(AndroidDriver driver) {
        super(driver);
        this.driver = driver;
    }

    /**
     * this is a function used to login and return the next page if success
     * @return HomePage: object of the home page after login in
     */
    public Home NormalLogin(String username, String password){
        loginEmailInputText.click();
        threadSleep(1);
        loginEmailInputText.sendKeys(username);
        driver.hideKeyboard();
        loginPasswordInputText.click();
        threadSleep(1);
        loginPasswordInputText.sendKeys(password);
        driver.hideKeyboard();

        if(!loginContinueButton.isEnabled())
            return null;

        loginContinueButton.click();


        try {
            waitForWebElementToAppear(errorMessage1, 3);
            if(errorMessage1 != null)
                return null;

        }catch (Exception e){
            return new Home(driver);
        }


        return null;

    }

    /**
     * this method is used to take us to the forget username/password page
     */
    public ForgetPage gotoForgetUserNamePassPage(){
        forgetPass.click();
        threadSleep(1);
        return new ForgetPage(driver);
    }

    /**
     * this methods takes us to the sign up page
     */
    public SignUpPage gotoSignUpPage(){
        SignUpButton.click();
        threadSleep(1);
        return new SignUpPage(driver);
    }


    /**
     * this is a function used to login using google and return the next page if success
     * @return HomePage: the home page in case of success
     */
    public Home googleLogin(String email, String password) {
        googleLoginBtn.click();
        try {
            waitForWebElementToDisappear(googleLoginBtn, 2);
        }catch (Exception e){
            return null;
        }

        return new Home(driver);
    }

    /**
     * this is a function used to login using facebook and return the next page if success
     * @return Home: the home page in case of success
     */
    public Home facebookLogin(String email, String password) {
        facebookLoginBtn.click();
        try {
            waitForWebElementToDisappear(facebookLoginBtn, 2);
        }catch (Exception e){
            return null;
        }

        return new Home(driver);
    }

    /**
     * this is a function used to signup using facebook and return the next page if success
     * @return Home: the home page in case of success
     */
    public Home googleSignUp(String email, String password) {
        SignUpButton.click();
        threadSleep(1);
        googleLoginBtn.click();
        try {
            waitForWebElementToDisappear(googleLoginBtn, 2);
        }catch (Exception e){
            return null;
        }

        return new Home(driver);
    }


    /**
     * this is a function used to login using facebook and return the next page if success
     * @return Home: the home page in case of success
     */
    public Home facebookSignup(String email, String password) {
        SignUpButton.click();
        threadSleep(1);
        facebookLoginBtn.click();
        try {
            waitForWebElementToDisappear(facebookLoginBtn, 2);
        }catch (Exception e){
            return null;
        }
        return new Home(driver);
    }



}
