package com.project.API;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import java.util.Map;

public class Builder {

    public static RequestSpecification requestSpecification(Map<String,String>formParams)
    {
        return new RequestSpecBuilder().setBaseUri("https://automationexercise.com")
                .setContentType(ContentType.URLENC)
                .addFormParams(formParams)
                .build();
    }
}
