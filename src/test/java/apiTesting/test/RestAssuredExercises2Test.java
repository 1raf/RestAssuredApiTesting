package apiTesting.test;

import com.tngtech.java.junit.dataprovider.*;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.junit.*;
import org.junit.runner.RunWith;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

@RunWith(DataProviderRunner.class)
public class RestAssuredExercises2Test {

    private static RequestSpecification requestSpec;

    @BeforeClass
    public static void createRequestSpecification() {

        requestSpec = new RequestSpecBuilder().
                setBaseUri("http://api.zippopotam.us").
                build();
    }

    /*******************************************************
     * Create a DataProvider with three test data rows:
     * country code - zip code - state
     * us           - 90210    - California
     * us           - 12345    - New York
     * ca           - Y1A      - Yukon
     ******************************************************/

    @DataProvider
    public static Object[][] zipCodesAndPlaces(){
        return new Object[][]{
                {"us", "90210", "California" },
                {"us", "12345", "New York" },
                {"ca", "Y1A", "Yukon"}
        };
    }


    /*******************************************************
     * Request zip code data for given country / zip
     * combinations by sending a GET to /<countryCode>/<zipCode>.
     * Use the test data collection created
     * above. Check that the state returned by the API
     * matches the expected value. The
     ******************************************************/

    @Test
    @UseDataProvider("zipCodesAndPlaces")
    public void checkStateForCountryCodeAndZipCode(String countryCode, String zipCode, String expectedState) {

        given().
            spec(requestSpec).
            pathParam("countryCode", countryCode).pathParam("zipCode",zipCode).
        when().
            get("{countryCode}/{zipCode}").
        then().
            assertThat().
                body("places[0].state", equalTo(expectedState));
    }
}