package apiTesting.test;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.junit.BeforeClass;
import org.junit.Test;
import org.testng.Assert;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class ZpItalySpec {
    private static RequestSpecification requestSpec;

    @BeforeClass
    public static void createRequestSpecification(){

        requestSpec = new RequestSpecBuilder().
                setBaseUri("http://api.zippopotam.us").
                build();
    }

    @Test
    public void statusCode(){
        given().
            spec(requestSpec).
        when().
            get("it/00010").
        then().
            assertThat().
            statusCode(200);
    }

    private static ResponseSpecification responseSpec;

    @BeforeClass
    public static void createResponseSpecification(){

        responseSpec = new ResponseSpecBuilder().
                expectStatusCode(200).
                expectContentType(ContentType.JSON).
                build();
    }

    @Test
    public void placeName(){
        given().
            spec(requestSpec).
        when().
            get("it/00010").
        then().
            spec(responseSpec).
                and().
                assertThat().
                body("places[0].'place name'", equalTo("Casape"));
    }

    @Test
    public void placeNameExtract(){

        String placeName =
        given().
            spec(requestSpec).
        when().
            get("it/00010").
        then().
            extract().
            path("places[0].'place name'");

        Assert.assertEquals(placeName, "Casape");
    }
}

