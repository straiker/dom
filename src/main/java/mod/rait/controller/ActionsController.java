package mod.rait.controller;
import java.util.List;

import com.google.gson.Gson;

import mod.rait.helper.RequestHelper;
import mod.rait.model.Message;
import mod.rait.model.Purchase;
import mod.rait.model.ShopItem;
import mod.rait.model.Solve;

public class ActionsController{
    RequestHelper helper = new RequestHelper();
    String gameId;

    ActionsController(String gameId){
        this.gameId = gameId;
    }

    public void purchaseRandomItem(int gold){
        List<ShopItem> shopInventory = helper.getShopList( gameId );
        Purchase purchase;
        System.out.println("Gold: "+gold);
        for( ShopItem item : shopInventory ){
            if( item.getCost() < gold ){
                purchase = helper.purchaseItemRequest( gameId, item.getId() );
                if( purchase.isShoppingSuccess() ){
                    System.out.println("Purchased " + item.getName() + " for " + item.getCost() + "gold. Remaining gold: "+purchase.getGold());
                } else {
                    System.out.println("Failed to purchase item "+ item.getName() + " for " + item.getCost() + "gold.");
                }
                gold = purchase.getGold();
            }
        }
    }

    public List<Message> getAllMessages(){
        List<Message> messages = helper.getMessages( gameId );
        System.out.println("Found " + messages.size() + " messages.");
        System.out.println("---------------");
        for( Message m : messages ){
            System.out.println(m.getAdId() + " | " + m.getMessage() + " | " + m.getProbability() + " | " + m.getExpiresIn());
        }
        System.out.println("---------------");
        return messages;
    }

    public Solve solveAd(String adId){
        Solve solve = helper.solveAd( this.gameId, adId );

        System.out.println("Message: "+solve.getMessage() +" | Turn: " + solve.getTurn() +" | Score: "+solve.getScore() +" | Gold:  "+solve.getGold()+" | Lives: "+solve.getLives());

        if( solve.getGold() >= 100 ){
            System.out.println( "Buying item" );
            purchaseRandomItem( solve.getGold() );
        }

        return solve;
    }
}
