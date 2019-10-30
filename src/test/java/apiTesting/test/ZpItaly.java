package apiTesting.test;

import org.junit.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class ZpItaly {
    @Test
    public void placeName(){
        given().
        when().
            get("http://zippopotam.us/it/00010").
        then().
            assertThat().
            body("places[0].'place name'", equalTo("Casape"));
    }

    @Test
    public void getStatusCode(){
        given().
        when().
            get("http://zippopotam.us/it/00010").
        then().
            statusCode(200);
    }

    @Test
    public void getLog(){
        given().log().all().
        when().
            get("http://zippopotam.us/it/00010").
        then().
            log().body();
    }

    @Test
    public void otherPlaceName(){
        given().
        when()
            .get("http://zippopotam.us/it/00010").
        then().body("places.'place name'", hasItem("Montorio Romano"));

    }
}

