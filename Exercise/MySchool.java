package Exercise;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import utility.ConfigurationReader;

import java.util.List;
import java.util.Map;

import static io.restassured.path.json.JsonPath.given;


public class MySchool {}
  /*  @BeforeAll
    public static void setUp(){
        baseURI= ConfigurationReader.getProperty("mySchoolBaseURL");
    }
    @DisplayName("Test1 all data ")
    @Test
    public void test1() {
        Response response = given()
                .accept(ContentType.JSON)
                .get("/student/all")
                // .prettyPeek()  // sayfanin altina gitmek zorunda kalmamak icin kapattim
                ;
        JsonPath jp = response.jsonPath();
        // [ ile basladigi icin List, { ile basliyorsa Map, key ler hep string,
        // ? cunku bazen value lar string bazen integer o yuzden ? koyuyoruz
        List<Map<String, ?>> students = jp.getList("students");  // students datadaki ilk [ den onceki isim
        System.out.println("students.size() = " + students.size());
        for (Map<String, ?> eachstudent : students) {    // herbir datayi almak icin
            System.out.println(eachstudent);
        }
        String lastname = jp.getString("students[0].lastName");  // her iki sekildede kullanabilirim bu yada alttaki
        //  String lastname=response.jsonPath().getString("students[0].lastName");
        System.out.println("lastname = " + lastname);   //yolcu ilk students in last name
        List<String> lastnames = jp.getList("students.lastName");  // butun lastnameleri aliyorum
        System.out.println("lastnames = " + lastnames);
        for (String eachlastnames : lastnames) {
            System.out.println("eachlastnames = " + eachlastnames);
        }
        // Not : " "  sadece bunu path olarak koydugumuzda icerisine giriyor datanin [ ] in
        int batch = jp.getInt("students[1].batch");
        //int batch =jp.getInt("students.batch[1]");  // bu ikiside ayni 1. indexteki batch in ne oldugunu veriyor
        System.out.println("batch = " + batch);
        Map<String, Object> contact0 = jp.getMap("students[0].contact");
        System.out.println("contact0 = " + contact0);     // contact 0 indexdeki tum datayi verdi
        // neden Map ile basladik cunku contact { ile basliyor ve key value iceriyor
        String phone = jp.getString("students[4].contact.phone");   // burda neden sadece string kullandik Map yada
        // List kullanmadik cunku sadece value yu almak istiyoruz liste halinde almak istemiyoruz
        System.out.println("phone = " + phone);
        int zipcode = jp.getInt("students[1].company.address.zipCode");
        System.out.println("zipcode = " + zipcode);
        //POJO : plain old java object
        // serialization ==> Java to JSON
        //deserialization ==>JSON to Java
        //students ==> list of object
        // company==> object
        // bazi yerlerde name.class yaziyorduk bu referans type demek
*/
