package day02;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

public class ZipCode_Test {
    @BeforeAll
    public static void setUp() {
        RestAssured.baseURI = "api.zippopotam.us";
        RestAssured.basePath = "/us/";
    }

    @DisplayName("Testing zipcode")
    @Test
    public void TestingZipCode() {
        given()
                .log().all()
                .pathParam("zipcode", 22030)
                .when()
                //   .get("{zipcode}")
                .then()
                .log().all()
                .statusCode(is(200))
                //  .body("JSON PATH", is("THE VALUE"))
                .body("zipcode", is(22030))
                .body("state", is("Virginia"))
                .body("state abbreviation", is("VA"))
                .body("latitude", is(38.8458))
                .body("longitude", is(-77.3242))
        ;
    }
/*

    @DisplayName("Testing ZipCodeTest")
    @Test
    public void testZipCode(){
        given()
                .log().all()
                .pathParam("zipcode",33064)
                .when()
                .get(":{zipcode}").
                then()
                .log().all()
                .statusCode(is(200))
                .contentType(ContentType.JSON)
                .body(" 'post code' ", is(33064))
                .body("country", is("United States"))
                .body("longitude", is(-80.1157))
                .body("state", is("FL"));
    }    */
}
