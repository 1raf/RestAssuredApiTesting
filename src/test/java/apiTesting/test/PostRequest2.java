package apiTesting.test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

public class PostRequest2 {
    @Test
    void registrationSuccessful(){

        // Specify base URI
        RestAssured.baseURI = "http://restapi.demoqa.com/customer";

        // Request object
        RequestSpecification httpRequest = RestAssured.given();

        // Request payload sending along with post request
        JSONObject requestParams = new JSONObject();
        requestParams.put("FirstName", "Karapetik");
        requestParams.put("LastName", "Totovenc");
        requestParams.put("UserName", "TotoKara");
        requestParams.put("Password", "Kara777");
        requestParams.put("Email", "karapet.totoven@rambler.ru");

        httpRequest.header("Content-Type", "application/json");
        httpRequest.body(requestParams.toJSONString()); //attach data to the request

        // Response object
        Response response = httpRequest.request(Method.POST,"/register");

        // Print response in console
        String responseBody = response.getBody().asString();
        System.out.println("Response body is: " + responseBody);

        // Status code validation
        int statusCode = response.getStatusCode();
        System.out.println("Status code is: " + statusCode);
        Assert.assertEquals(statusCode, 201);

        // Success code validation
        String successCode = response.jsonPath().get("SuccessCode");
        Assert.assertEquals(successCode, "OPERATION_SUCCESS");

        // Message validation
        String message = response.jsonPath().get("Message");
        Assert.assertEquals(message, "Operation completed successfully");
    }
}

