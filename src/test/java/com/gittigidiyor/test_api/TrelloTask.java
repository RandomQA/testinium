package com.gittigidiyor.test_api;
import com.gittigidiyor.utilities.ConfigReader;
import com.gittigidiyor.utilities.RandomMaker;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import static io.restassured.RestAssured.*;

 public class TrelloTask {
    Response response;
    JsonPath json;
    String endPoint = ConfigReader.getProperty("baseEndPoint");
    RequestSpecification spec;
    String apiKey = ConfigReader.getProperty("apiKey");
    String token = ConfigReader.getProperty("token");
    String boardName = ConfigReader.getProperty("trelloBoardName");
    Map<String, String> data = new HashMap<>();
    List<String> cardIdList = new ArrayList<>();

    @BeforeClass
    public void setUp() throws Exception {
        spec = new RequestSpecBuilder().setBaseUri(endPoint).build();
    }

    @Test (priority = 1)
    public void createBoard() {
        boardName = RandomMaker.makeRandom(boardName);
        spec.contentType(ContentType.JSON);
        spec.queryParams("key", apiKey, "token", token, "name", boardName);
        response = given()
                .spec(spec)
                .when()
                .post("boards/");
        response.then().assertThat().statusCode(200);
        response.prettyPrint();
        json = response.jsonPath();
        String boardName = json.getString("shortUrl");
        boardName = boardName.substring(boardName.lastIndexOf("/") + 1);
        String boardId=json.getString("id");
        data.put("boardName", boardName);
        data.put("boardId",boardId);
    }


    @Test (priority = 2)
    public void createList() {
        String listName = RandomMaker.makeRandom("MyList");
        spec.contentType(ContentType.JSON);
        spec.queryParams("key", apiKey, "token", token, "name", listName);
        response = given()
                .spec(spec)
                .when()
                .post("boards/" + data.get("boardName") + "/lists");
        response.then().assertThat().statusCode(200);
        response.prettyPrint();
        json = response.jsonPath();
        String listId = json.getString("id");
        data.put("listId", listId);
        System.out.println(listId);
    }

    @Test (priority = 3)
     public void createCard() {
        String listId=data.get("listId");
        System.out.println(listId);
        String cardName = RandomMaker.makeRandom("ProjectCard");
        spec.contentType(ContentType.JSON);
        spec.queryParams("key", apiKey, "token", token, "idList", listId, "name", cardName);
        for (int i = 0; i < 2; i++) {
            response = given()
                    .spec(spec)
                    .when()
                    .post("cards");
            response.then().assertThat().statusCode(200);
            json = response.jsonPath();
            String cardId = json.getString("id");
            cardIdList.add(cardId);
        }
    }

    @Test (priority = 4)
     public void updateCard() {
        int randomIndex= (int) (Math.random()*2);
        String selectedCardID = cardIdList.get(randomIndex);
        String updatedName = "Name was updated";
        updatedName = RandomMaker.makeRandom(updatedName);
        spec.header("Accept", "application/json");
        spec.contentType(ContentType.JSON);
        spec.queryParams("key", apiKey, "token", token, "name", updatedName);
        response = given()
                .spec(spec)
                .when()
                .put("cards/" + selectedCardID);
        response.then().assertThat().statusCode(200);
    }

    @Test (priority = 5)
     public void deleteCard() {
        spec.contentType(ContentType.JSON);
        for (int i = 0; i < cardIdList.size(); i++) {
            response = given()
                    .spec(spec)
                    .when()
                    .delete("cards/" + cardIdList.get(i));
            response.then().assertThat().statusCode(200);
        }
    }

    @Test (priority = 6)
     public void deleteBoard() {
        spec.contentType(ContentType.JSON);
        spec.queryParams("key", apiKey, "token", token);
        response = given()
                .spec(spec)
                .when()
                .delete("boards/" + data.get("boardId"));
        response.then().assertThat().statusCode(200);
        }
}

