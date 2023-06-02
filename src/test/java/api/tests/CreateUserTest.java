package api.tests;

import api.ApiBase;
import api.CreateUserDto;
import api.enums.EndPoint;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

public class CreateUserTest extends ApiBase {
    CreateUserDto createUserDto;
    Response response;
    String emailCreatedUser;

    @AfterMethod
    public void deleteContact() {
        doDeleteRequest(EndPoint.DELETE_USER, 200, emailCreatedUser);
    }
    @Test
    public void createUserApiTest() {
        createUserDto = new CreateUserDto();
        createUserDto.setFull_name(faker.name().fullName());
        createUserDto.setEmail(faker.internet().emailAddress());
        createUserDto.setPassword(faker.internet().password());

        response = doPostRequest(EndPoint.CREATE_USER, 201, createUserDto);
        emailCreatedUser = response.jsonPath().getString("email");

        Assert.assertEquals(response.jsonPath().getString("full_name"), createUserDto.getFull_name());
        Assert.assertEquals(response.jsonPath().getString("email"), createUserDto.getEmail());


    }
}
