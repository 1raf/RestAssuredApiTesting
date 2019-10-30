package apiTesting.test;

import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.http.Method;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;

public class GetRequest3 {

    @Test
    void googleMapsTest(){

        // Specify base URI
        RestAssured.baseURI = "https://maps.googleapis.com";

        // Request object
        RequestSpecification httpRequest = RestAssured.given();

        // Response object
        Response response = httpRequest.request(Method.GET,"/maps/api/place/nearbysearch/xml?" +
                "location=-33.8670522,151.1957362&radius=1500&type=supermarket&key=AIzaSyBjGCE3VpLU4lgTqSTDmHmJ2HoELb4Jy1s");

        // Print response in console
        String responseBody = response.getBody().asString();
        System.out.println("Response body is: " + responseBody);

//                                // Validating headers
//        String contentType = response.header("Content-Type"); //Capture details of Content-Type header
//        System.out.println("Content type is: " + contentType);
//        Assert.assertEquals(contentType, "application/xml; charset=UTF-8");
//
//        String contentEncoding = response.header("Content-Encoding"); //Capture details of Content-Encoding header
//        System.out.println("Content-Encoding is: " + contentEncoding);
//        Assert.assertEquals(contentEncoding, "gzip");
//
//        String server = response.header("Server");
//        System.out.println("Server is " + server);
//        Assert.assertEquals(server, "scaffolding on HTTPServer2");

                                        // Validating all headers

        Headers allHeaders = response.headers();
        for(Header header:allHeaders){
            System.out.println(header.getName() + " " + header.getValue());
        }
    }
}
