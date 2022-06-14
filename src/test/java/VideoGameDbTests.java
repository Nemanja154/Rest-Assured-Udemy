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
        String gameBodyJson = """
                {
                  "id": 11,
                  "name": "MyNewGame",
                  "releaseDate": "2022-06-13T17:37:41.790Z",
                  "reviewScore": 88,
                  "category": "Shooter",
                  "rating": "Mature"
                }""";

        given().body(gameBodyJson).when().post(VideoGameEndpoints.ALL_VIDEO_GAMES).then();
    }

    @Test
    public void updateGame(){
        String gameBodyJson = """
                {
                  "id": 1,
                  "name": "MyNewGame",
                  "releaseDate": "2022-06-13T18:30:19.439Z",
                  "reviewScore": 0,
                  "category": "string",
                  "rating": "Univerasal"
                }""";

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
