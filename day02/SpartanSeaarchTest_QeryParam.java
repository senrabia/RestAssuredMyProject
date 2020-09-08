package day02;

import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;


public class SpartanSeaarchTest_QeryParam {
    @BeforeAll
    public static void setUp(){

        RestAssured.baseURI="http://54.174.216.145:8000";
        RestAssured.basePath="/api";

    }
    //http://54.174.216.245:8000/api/spartans/search?gender=male&nameContains=ea
    @DisplayName("Testing/spartans/search Endpoint")
    @Test
    public void testSearch(){
       given()
               .log().all()
               .queryParam("gender","male")
               .queryParam("nameContains").
       when().get("spartans/search").
       then().log().all()
             .statusCode(200)
             .body("numberOfElements", is(67));
    }
}
