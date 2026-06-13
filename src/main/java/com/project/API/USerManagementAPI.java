package com.project.API;

import com.project.utils.Validation.HardAssert;
import io.qameta.allure.Step;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;


public class USerManagementAPI  {

    protected final String createUserEndpoint="/api/createAccount";
    protected final  String deleteUserAccount="api/deleteAccount";

     RequestSpecification requestSpecification;
     Response response;
     HardAssert verification;
     public USerManagementAPI()
     {
         this.requestSpecification=given().log().all();
         verification=new HardAssert();

     }

            @Step("create user  account  in  full details")
    public USerManagementAPI createRegisterUSerAccount(String name,
                                                       String email,
                                                       String password,
                                                       String title,
                                                       String birth_date,
                                                       String birth_month,
                                                       String birth_year,
                                                       String firstName,
                                                       String lastName,
                                                       String company,
                                                       String address1,
                                                       String address2,
                                                       String country,
                                                       String zipCode,
                                                       String state,
                                                       String city,
                                                       String mobile)

    {

        Map<String,String>formPAram=new HashMap<>();
        formPAram.put("name", name);
        formPAram.put("email", email);
        formPAram.put("password", password);
        formPAram.put("title", title);
        formPAram.put("birth_date", birth_date);
        formPAram.put("birth_month", birth_month);
        formPAram.put("birth_year", birth_year);
        formPAram.put("firstname", firstName);
        formPAram.put("lastname", lastName);
        formPAram.put("company", company);
        formPAram.put("address1", address1);
        formPAram.put("address2", address2);
        formPAram.put("country", country);
        formPAram.put("zipcode", zipCode);
        formPAram.put("state", state);
        formPAram.put("city", city);
        formPAram.put("mobile_number", mobile);
        response=requestSpecification.spec(Builder.requestSpecification(formPAram)).when()
                .when().post("/api/createAccount")
                .then().log().all().extract().response();
        return this;
    }

    @Step("create  user in  some details")
    public USerManagementAPI createRegisterUSerAccount(String name,
                                                       String email,
                                                       String password,
                                                       String firstName,
                                                       String lastName
                                                       )

    {

        Map<String,String>formPAram=new HashMap<>();
        formPAram.put("name", name);
        formPAram.put("email", email);
        formPAram.put("password", password);
        formPAram.put("title", "Mr");
        formPAram.put("birth_date", "8");
        formPAram.put("birth_month", "8");
        formPAram.put("birth_year", "1998");
        formPAram.put("firstname", firstName);
        formPAram.put("lastname", lastName);
        formPAram.put("company", "company");
        formPAram.put("address1", "address1");
        formPAram.put("address2", "address2");
        formPAram.put("country", "India");
        formPAram.put("zipcode", "12345");
        formPAram.put("state", "state");
        formPAram.put("city", "city");
        formPAram.put("mobile_number", "1234567890");
       response= requestSpecification.spec(Builder.requestSpecification(formPAram)).when()
                .when().post(createUserEndpoint)
                .then().log().all().extract().response();
        return this;
    }
@Step("delete user account ")
    public USerManagementAPI deleteUserAccount(String Email,String password)
    {
        Map<String,String> formParam=new HashMap<>();
        formParam.put("email",Email);
        formParam.put("password",password);
        response=requestSpecification.spec(Builder.requestSpecification(formParam))
                .when().delete(deleteUserAccount)
                .then().log().all().extract().response();

        return  this;
    }


    //  validation

    @Step("verify user  created successfully")
    public USerManagementAPI verifyUserCreatedSuccessfully()
    {
        verification.assertEquals(response.jsonPath().getString("message"),"User created!");
        return this;
    }
@Step("verify user  deleted successfully")
    public USerManagementAPI verifyUserDeletedSuccessfully()
{
    verification.assertEquals(response.jsonPath().getString("message"),"Account deleted!");
    return this;

}



}
