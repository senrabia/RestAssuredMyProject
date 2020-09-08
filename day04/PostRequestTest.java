package day04;
import com.github.javafaker.Faker;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.*;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
//Burada biz String seklinde islem yaptik


public class PostRequestTest {

    @BeforeAll
    public static void init() {

        RestAssured.baseURI = "http://54.158.178.13";
        RestAssured.port = 8000;
        RestAssured.basePath = "/api";

    }

    @DisplayName("Post request with String as body")
    @Test
    public void testPostWithStringBody() {

        // lets try to get random names rather than same ADAM each time
        Faker faker = new Faker();
        String randomName = faker.name().firstName();
        System.out.println("randomName = " + randomName);
        // try to randomize gender and phone num on your own at home.
        String bodyString = "{\n" +
                "  \"name\"  : \"" + randomName + "\",\n" +
                "  \"gender\": \"Female\",\n" +
                "  \"phone\": 6234567890\n" +
                "}";

        given()

                .log().all()
                .contentType(ContentType.JSON)
                .body(bodyString).
                when()
                .post("/spartans").
                then()
                .log().all()
                .statusCode(201)
                .body("data.name", is(randomName));
        ;


    }

    //Burada File dan alacagiz (Biz spartan.Json page den aldik ), Intelij de file( disardan excel olusturmuyoruz )olusturduktan sonra
    //File (Intelij de hard coding yaptigimiz islem gibi) olusturuyoruz.
    @DisplayName("Posting with external json file")
    @Test
    public void testPostWithExternalFile() {

        // create a file object that point to spartan.json you just added
        // so we can use this as body in post request
        File file1 = new File("spartan.json");//Spartan.json path ile bulabiliriz
        given()
                .log().all()
                .contentType(ContentType.JSON)
                .body(file1).
                when()
                .post("/spartans").
                then()
                .log().all()
                .statusCode(201)
                .body("data.name", is("From File"));

    }

    @DisplayName("Posting with Map object as body")
    @Test
    public void testPostWithMapAsBody() {

        // please add dependency jackson-databind

        // create a Map<String,Object> as hashMap
        // add name , gender , phone
        // Back at 3:00PM EST
        Map<String, Object> bodyMap = new LinkedHashMap<>();
        //Map<String, Object> bodyMap = new HashMap<>(); //Aradaki fark nedir Hash map and LinkedHashMap

        //Map<Key imiz bizim, ama object kismimiz degisebilir diyoruz> body map= new
        bodyMap.put("name", "Vincent");
        bodyMap.put("gender", "Male");
        bodyMap.put("phone", 3476346789l);

        System.out.println("bodyMap = " + bodyMap);
        // j
        given()
                .log().all()
                .contentType(ContentType.JSON)
                .body(bodyMap).
                when()
                .post("/spartans").
                then()
                .log().all()
                .statusCode(201);

    }
}







