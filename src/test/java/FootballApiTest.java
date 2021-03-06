import config.FootballApiConfig;
import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import org.testng.annotations.Test;



import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;


public class FootballApiTest extends FootballApiConfig {


    @Test
    public void getDetailsOfOneArea(){
        given()
                .queryParam("areas",2072).
        when()
                .get("areas");
    }

    @Test
    public void getDateFounded(){
        given().when().get("teams/57").then().body("founded",equalTo(1905));
    }

    @Test
    public void getFirstTeamName(){
        given().when().get("competitions/2021/teams").then().body("teams.name[0]",equalTo("Arsenal FC"));
    }

    @Test
    public void getAllTeamData(){
        String responseBody = get("teams/57").asString();
        System.out.println(responseBody);
    }

    //Extract body of HTTP Response
    @Test
    public void getAllTeamData_DoCheckFirst(){
        Response response =
                given().
                when()
                        .get("teams/57")
                        .then()
                        .contentType(ContentType.JSON)
                        .extract().response();

        String jsonResponse = response.asString();
    }

    @Test
    public void extractHeaders(){
        Response response =
                given().when().get("teams/57").then().contentType(ContentType.JSON).extract().response();

        Headers headers = response.getHeaders();

        String contentType = response.getHeader("Content-Type");
    }

    @Test
    public void extractFirstTeamName(){
        String firstTeamName = get("competitions/2021/teams").jsonPath().getString("teams.name[0]");

        System.out.println(firstTeamName);
    }


}
