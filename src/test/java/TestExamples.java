import io.restassured.http.ContentType;
import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItems;

public class TestExamples {

    @Test
    public void test_2(){
        baseURI = "https://reqres.in/api";
        given().get("/users?page=2").
        then().
            statusCode(200).
        body("data[1].id",equalTo(8)).log().all();

    }

    @Test
    public void testGet(){
        baseURI = "https://reqres.in/api";
        given().
                get("/users?page=2").
        then().
                statusCode(200).
        body("data[4].first_name",equalTo("George")).
        body("data.first_name",hasItems("George","Rachel"));

    }

    @Test
    public void testPost(){
        Map<String,Object> map = new HashMap<>();
//        map.put("name","Nemanja");
//        map.put("job","Teacher");
//
//        System.out.println(map);

        JSONObject request = new JSONObject(map);

        request.put("name","Nemanja");
        request.put("job","Teacher");
        System.out.println(request);

        baseURI = "https://reqres.in/api";

        given().
                header("Content-Type","application/json").
                contentType(ContentType.JSON).accept(ContentType.JSON).
                body(request.toJSONString()).
        when().
                post("/users").
        then().
                statusCode(201).log().all();
    }

    @Test
    public void testPut(){
        JSONObject request = new JSONObject();

        request.put("name","Nemanja");
        request.put("job","Teacher");
        System.out.println(request);

        baseURI = "https://reqres.in/api";

        given().
                header("Content-Type","application/json").
                contentType(ContentType.JSON).accept(ContentType.JSON).
                body(request.toJSONString()).
                when().
                put("/users/2").
                then().
                statusCode(200).log().all();
    }

    @Test
    public void testPatch(){
        JSONObject request = new JSONObject();

        request.put("name","Nemanj");
        request.put("job","Teacher");
        System.out.println(request);

        baseURI = "https://reqres.in/";

        given().
                header("Content-Type","application/json").
                contentType(ContentType.JSON).accept(ContentType.JSON).
                body(request.toJSONString()).
                when().
                patch("api/users/2").
                then().
                statusCode(200).log().all();
    }

    @Test
    public void testDelete(){
        baseURI = "https://reqres.in/api";

        delete("/uses/2").then().statusCode(204).log().all();
    }

}
