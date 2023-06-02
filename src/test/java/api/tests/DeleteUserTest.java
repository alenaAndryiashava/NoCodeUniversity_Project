package api.tests;

import api.ApiBase;
import api.CreateUserDto;
import api.enums.EndPoint;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class DeleteUserTest extends ApiBase {
    CreateUserDto createUserDto;
    Response response;
    String emailCreatedUser;
    String wrongEmail;

    @BeforeMethod(onlyForGroups = {"positive"})
    public void precondition(){
        String full_name = faker.name().fullName();
        String email = faker.internet().emailAddress();
        String password = faker.internet().password();
        createUserDto = new CreateUserDto();
        createUserDto.setFull_name(full_name);
        createUserDto.setEmail(email);
        createUserDto.setPassword(password);

        response = doPostRequest(EndPoint.CREATE_USER, 201, createUserDto);
        emailCreatedUser = response.jsonPath().getString("email");
    }
    @Test(groups = {"positive"})
    public void deleteUser(){
        doDeleteRequest(EndPoint.DELETE_USER,200, emailCreatedUser);

    }
    @Test
    public void deleteUserWithWrongEmail(){
        wrongEmail = getWrongEmail();
        response = doDeleteRequest(EndPoint.DELETE_USER, 404, wrongEmail);
        String expectedErrorMessage = "User with email: " + wrongEmail + " not found";

        Assert.assertEquals(response.jsonPath().getString("message"), expectedErrorMessage);
    }
}
