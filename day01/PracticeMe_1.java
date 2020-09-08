package day01;

import io.restassured.RestAssured;
import io.restassured.matcher.ResponseAwareMatcher;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.List;
import static io.restassured.RestAssured.* ;
import static io.restassured.matcher.RestAssuredMatchers.* ;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
//--------------------------------
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;


public class PracticeMe_1 {
    //http://100.24.235.129:8000/api/hello
    @Test
    public void test1() {

        Response response = get("http://54.174.216.245:8000/api/hello");
        System.out.println("status code of this response : " + response.statusCode());
        System.out.println("getting status line of this response " + response.statusLine());
        System.out.println("status code of this response : " + response.getStatusCode());
        System.out.println("Getting the value of date header " + response.header("Date"));
        System.out.println("Getting the value of date header " + response.getHeader("Date"));
        System.out.println("Content-Type header " + response.header("Content-Type"));
        System.out.println("Content-Length header " + response.header("Content-Length"));
        System.out.println(response.contentType());
        System.out.println(response.getContentType());
    }
    @DisplayName("Testing /hello endpoint")
    @Test
    public void testHello() {
        Response response = get("http://54.174.216.245:8000/api/hello");
        assertEquals(200, response.statusCode());
        assertEquals("text/plain;charset=UTF-8", response.header("Content-Type"));
        assertEquals("text/plain;charset=UTF-8", response.getHeader("Content-Type"));
        assertEquals("text/plain;charset=UTF-8", response.contentType());
        assertEquals("17", response.header("Content-length"));
    }

public class Practice2 {
        @Test
    public void test1(){
            int num1 = 5 ;
            int num2 = 4 ;

        assertThat( num1 + num2 ,  is(9)   );
        assertThat( num1 + num2 ,  equalTo(9)   );
        assertThat(num1+num2,    is (equalTo(9) ) ) ;
        assertThat(num1+num2 , not(11) );
        assertThat(num1+num2, is( not(11) ) );
        String firstName = "Kaisar "; // there is a space in last character here
        String lastName = "Anvar";

        assertThat(firstName+lastName, is("Kaisar Anvar")  );
        assertThat(firstName+lastName, equalTo("Kaisar Anvar")  );
        assertThat(firstName+lastName, is( equalTo("Kaisar Anvar"))  );
        assertThat(firstName , equalToIgnoringCase("kaisar ") );
        assertThat(firstName, equalToCompressingWhiteSpace("Kaisar"));

    }

    @DisplayName("Support for collection")
    @Test
    public void test2() {

        List<Integer> numList = Arrays.asList(11, 44, 3, 55, 88, 5);
        assertThat(numList, hasSize(6));
        assertThat(numList, hasItem(11));
        assertThat(numList, hasItems(11, 3, 5));
        assertThat(numList, contains(11, 44, 3, 55, 88, 5));
        assertThat(numList, containsInAnyOrder(11, 44, 55, 3, 88, 5));

    }}
public class NameTest{
    @Test
        public void calculatorTest(){
                System.out.println("Hayat cok zooor");
                assertEquals(7, 7+7);
            }
            @DisplayName("Hadi bunu test edelim")
            @Test
        public void testTest(){
                String firstName="Rabia";
                String lastName="Sen";
                assertEquals("Rabia Sen ", firstName+" "+lastName);





}}}


