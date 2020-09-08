package day11;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import utility.ConfigurationReader;
import utility.DB_Utility;

public class GettingDataFormOtherRequests {
    @BeforeAll
    public static void init(){

        RestAssured.baseURI = ConfigurationReader.getProperty("spartan1.base_url");
        RestAssured.basePath = "/api" ;
        DB_Utility.createConnection("spartan1");//BURASI ONEMLI==>BU METOD ILE SPARTAN 1 DATASINA GIT DEMEK. //CONNECTION SACCESSFULL

    }

/**
 * We want to test GET /Spartans/{id}
 * and the ID is dynamic , and also different in different environments
 * we want to always work with available data
 * without dealing with 404 issue because data does not exists
 *
 * ACTION   ITEMS
 * 1-Send a GET /spartans endpoint
 * 2-store the result as List of pojo
 * 3-initially just the the first data and use it for GET /Spartans/{id} request
 * and use the name ,gender, phone, for body validation
 *  eventually randomize the way you get the ID
 */

@Test
public void testTheDinamicID(){
    Response response=get("/spartans");
}

}


















