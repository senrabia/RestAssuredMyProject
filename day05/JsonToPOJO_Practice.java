package day05;

import io.restassured.RestAssured;
import io.restassured.authentication.AuthenticationScheme;
import io.restassured.authentication.BasicAuthScheme;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pojo.Spartan;
import pojo.Spartan2;

import java.util.Base64;
import java.util.List;

import static io.restassured.RestAssured.*;
import static io.restassured.specification.ProxySpecification.auth;

public class JsonToPOJO_Practice {

    @BeforeAll
    public static void init(){

        RestAssured.baseURI = "http://54.160.106.84" ;
        RestAssured.port = 8000;
        RestAssured.basePath = "/api" ;
    }

    @DisplayName("Json to Pojo GET /spartans/{id} ")
    @Test
    public void testSpartanJsonToSpartanObject(){

        int newID = 368; //SecureSpartanTest.createRandomSpartan();

        Response response = given()
                //.auth().basic("admin","admin")
                .log().all()
                .pathParam("id", newID).
                        when()
                .get("/spartans/{id}")
                .prettyPeek() ;
        // as method from response
        // accept a class type to define what is the type object you are trying to store the response as
        //  Spartan2 class we created has all the fields that match the json fields from the response
        // So it will map all the json fields to the java fields and return Spartan2 POJO Object
        // in a simple word turn below json into Java object
        /**
         * {
         *     "id": 261,
         *     "name": "Elma",
         *     "gender": "Male",
         *     "phone": 9999999998
         * }
         *
         * into  new Spartan2(261,"Elma","Male",9999999998L) Java Object
         * so we can work with the data using java object directly
         */
         // Spartan2 sp = response.as( Spartan2.class ) ;
        // above line is almost as if you are doing below line
        Spartan2 sp = new Spartan2(261,"Elma","Male",9999999998L);
        System.out.println("sp = " + sp);

    }

    @DisplayName("Search Request and save 1st Result as Spartan2 Pojo")
    @Test
    public void gettingNestedJsonAsPojo(){

        Response response =   given()
                .log().all()
                .auth().basic("admin","admin")
                .queryParam("gender","Male").
                        when()
                .get("/spartans/search") ;
        //.prettyPeek();

        System.out.println("response.statusCode() = " + response.statusCode());
        // print out the first id and name from the response
        JsonPath jp = response.jsonPath() ;
        System.out.println("First guy ID " +   jp.getInt("content[0].id")   );
        System.out.println("First guy Name " + jp.getString("content[0].name")  )  ;

        // lets save the entire first json object in the array into Spartan2 POJO
        // getObject method accept 2 parameters ,
        //  first parameter to provide the path to your json
        //  second parameter to provide the type you want to store as  : Spartan2.class
        // eventually it will work just like  new Spartan(317,'Robert',Male,3252645223)

        Spartan2 firstMaleSpartan = jp.getObject("content[0]", Spartan2.class) ;
        //System.out.println("firstMaleSpartan = " + firstMaleSpartan);
        //System.out.println("The Spartan id from POJO is "       + firstMaleSpartan.getId() );
        System.out.println("The Spartan name from POJO is "     + firstMaleSpartan.getName() );
        System.out.println("The Spartan gender from POJO is "   + firstMaleSpartan.getGender() );
        System.out.println("The Spartan phone from POJO is "    + firstMaleSpartan.getPhone() );


    }

    // I already know how to store one Spartan json into Spartan2 POJO
    // How can I store the entire jsonArray into the List<Spartan2>
    @DisplayName("Save the json array as List<Spartan>")
    @Test
    public void testJsonArrayToListOfPojo(){

        Response response =  given()
                .auth().basic("admin","admin")
                .queryParam("gender","Female").
                        when()
                .get("/spartans/search");
        // store all ids as list of Integer
        JsonPath jp = response.jsonPath() ;
        List<Integer> ids = jp.getList("content.id") ;
        System.out.println(ids);
        List<String> names = jp.getList("content.name") ;
        System.out.println(names);

        // Store the entire jsonArray as list of spartan2
        // "content" is the json path for the jsonArray from the response [{},{},{}....]
        //  getList is a method that return a list out of the array
        //  Spartan2.class is specifying explicitly what is the data type of the items inside the list
        //   so jackson data-bind can automatically map each json object inside array into
        //    each Spartan2 POJO inside List<Spartan2>
        List<Spartan2> spartan2List = jp.getList("content" , Spartan2.class) ;

        System.out.println("spartan2List = " + spartan2List);
//        for( Spartan2 each : spartan2List ){
//            System.out.println(each);
//        }
        // this is optionally lambda way of for each
        spartan2List.forEach( each-> System.out.println(each) );

    }


}
