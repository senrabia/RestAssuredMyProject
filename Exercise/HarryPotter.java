package Exercise;


import io.restassured.http.ContentType;
import org.apiguardian.api.API;
import org.junit.jupiter.api.Assertions;
import utility.ConfigurationReader;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
//import utulities.ConfigurationReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static io.restassured.RestAssured.* ;
import static org.hamcrest.core.StringContains.containsString;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class HarryPotter {
    @BeforeAll
    public static void setUp() {
        baseURI = ConfigurationReader.getProperty("harryPotterApiBaseURL");

    }

    @Test
    @DisplayName("Verify sorting hat")
    public void test1() {
        //Verify soring hat
        //1. Send a get request to /sortingHat. Request includes:
        //2.Verify status code 200, content type application/json;charset=utf-8
        //3.Verify that response body contains one of the following houses:
        //  "Gryffindor", "Ravenclaw", "Slytherin", "Hufflepuff"
        // prettyprint devam etmeyecegi icin bunu kullaniyorsun prettypeek de devam edebilirsin
        // assortion yapacaksan prettypeek kullanman daha mantikli cunku then ile devam edebiliyorsun


        List<String> expected = new ArrayList<>(Arrays.asList("\"Gryffindor\"",
                "\"Ravenclaw\"", "\"Slytherin\"", "\"Hufflepuff\""));

        Response response = get("/sortingHat").prettyPeek();

        Assertions.assertEquals(200, response.statusCode());
        Assertions.assertEquals("application/json; charset=utf-8", response.contentType());
        Assertions.assertTrue(expected.contains(response.body().asString()));

       /*when().get("/sortingHat").prettyPeek();
              .then().assertThat().statusCode(200).contentType("application/json; charset=utf-8")
               .body(containsString(oneOf("Gryffindor","Slytherin")))
               .body(is())*/
    }

    @DisplayName("Verify bad key")
    @Test
    public void test2() {
        //1. Send a get request to /characters. Request includes:
        //    . Header Accept with value application/json
        //    . Query param key with value invalid
        //2. Verify status code 401, content type application/json; charset=utf-8
        //3.Verify response status line include message Unauthorized
        //4.Verify that response body says "error": "API Key Not Found"
        String expected = "{\"error\":\"API Key Not Found\"}";
        given()
                .log().all()
                .accept(ContentType.JSON)
                .queryParam("key", "invalid")
                .when()
                .get("/characters")
                .then()
                .log().all()
                .statusCode(401)
                .contentType("application/json; charset=utf-8")
                .statusLine(containsString("Unauthorized"))
                .body(containsString(expected));


    }

    @DisplayName("Verify no key")
    @Test
    public void test3() {
        // 1. Send a get request to /characters. Request includes:
        //  .Header Accept with value application/json
        //2. Verify status code 409, content type application/json; charset=utf-8
        //3. Verify response status line include message Conflict
        //4. Verify that response body says "error": "Must pass API key for request"
        String expected = "{\"error\":\"Must pass API key for request\"}";
        given()
                .log().all()
                .accept(ContentType.JSON)
                .when()
                .get("/characters")
                .then()
                .log().all()
                .statusCode(409)
                .contentType("application/json; charset=utf-8")
                .statusLine(containsString("Conflict"))
                .body(containsString(expected));

    }}
