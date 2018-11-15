package mod.rait.helper;
import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.post;

import java.util.Arrays;
import java.util.List;

import io.restassured.RestAssured;
import mod.rait.model.*;

public class RequestHelper{

    private String URL = "https://www.dragonsofmugloar.com/api/v2/";

    public Game startGame(){
        RestAssured.baseURI = URL;
        return post("game/start").as( Game.class );
    }

    public Investigation investigate(String gameId){
        RestAssured.baseURI = URL;
        return post(gameId + "/investigate/reputation").as( Investigation.class );
    }

    public List<Message> getMessages(String gameId){
        RestAssured.baseURI = URL;
        Message[] messages = get(gameId+"/messages").as( Message[].class );
        return Arrays.asList( messages );
    }

    public Solve solveAd(String gameId, String adId){
        RestAssured.baseURI = URL;
        return post( gameId + "/solve/" + adId ).as( Solve.class );
    }

    public List<ShopItem> getShopList(String gameId){
        RestAssured.baseURI = URL;
        ShopItem[] shopItems = get(gameId+"/shop").as( ShopItem[].class );
        return Arrays.asList( shopItems );
    }

    public Purchase purchaseItemRequest(String gameId, String itemId){
        RestAssured.baseURI = URL;
        return post( gameId + "/shop/buy/" + itemId).as( Purchase.class );
    }

}
