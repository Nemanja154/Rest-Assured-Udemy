import config.VideoGameConfig;
import config.VideoGameEndpoints;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class VideoGameDbTests extends VideoGameConfig {

    @Test
    public void getAllGames(){
        given().when().get(VideoGameEndpoints.ALL_VIDEO_GAMES).then();
    }

    @Test
    public void createNewGameByJSON(){
        String gameBodyJson = "{\n" +
                "  \"id\": 11,\n" +
                "  \"name\": \"MyNewGame\",\n" +
                "  \"releaseDate\": \"2022-06-13T17:37:41.790Z\",\n" +
                "  \"reviewScore\": 88,\n" +
                "  \"category\": \"Shooter\",\n" +
                "  \"rating\": \"Mature\"\n" +
                "}";

        given().body(gameBodyJson).when().post(VideoGameEndpoints.ALL_VIDEO_GAMES).then();
    }

    @Test
    public void updateGame(){
        String gameBodyJson = "{\n" +
                "  \"id\": 1,\n" +
                "  \"name\": \"MyNewGame\",\n" +
                "  \"releaseDate\": \"2022-06-13T18:30:19.439Z\",\n" +
                "  \"reviewScore\": 0,\n" +
                "  \"category\": \"string\",\n" +
                "  \"rating\": \"Univerasal\"\n" +
                "}";

        given().body(gameBodyJson).
                when().put("videogames/1").then();
    }

    @Test
    public void deleteGame(){
        given().
                when().
                delete("videogames/1").
        then();
    }

    @Test
    public void getSingleGame(){
        given().pathParams("videoGameId",5).when().get(VideoGameEndpoints.SINGLE_VIDEO_GAME).then();
    }

}
