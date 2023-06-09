import Pages.HomePage;
import Pages.SignInPage;
import api.ApiBase;
import api.CreateUserDto;
import api.enums.EndPoint;
import com.github.javafaker.Faker;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ui.BaseTest;


public class CreateUserApiVerifyUiTest extends BaseTest {
    CreateUserDto createUserDto;
    private final String full_name = new Faker().name().fullName();
    private final String email = new Faker().internet().emailAddress();
    private final String password = new Faker().internet().password();

    @BeforeMethod(onlyForGroups = {"positive"})
    public void precondition() {
        createUserDto = new CreateUserDto();
        createUserDto.setFull_name(full_name);
        createUserDto.setEmail(email);
        createUserDto.setPassword(password);
        new ApiBase().doPostRequest(EndPoint.CREATE_USER, 201, createUserDto);
    }

    @Test(groups = {"positive"})
    public void checkCreatedUserInUi() {
        new HomePage().clickSignInButton();
        new SignInPage().getAuth(email, password);
        new HomePage().checkHeaderIsNotVisible();
    }

    @AfterMethod
    public void tearDown() {
        new ApiBase().doDeleteRequest(EndPoint.DELETE_USER, 200, email);
    }
}
