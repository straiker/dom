package mod.rait.controller;
import java.util.Arrays;
import java.util.List;

import com.google.gson.Gson;

import lombok.Getter;
import lombok.Setter;
import mod.rait.helper.RequestHelper;
import mod.rait.model.Game;
import mod.rait.model.Message;
import mod.rait.model.Solve;

public class Main{
    /*
     * Your solution is considered acceptable when the
     * application is reliably able to achieve a score of at least 1000 points.
     * ----------------------------------------------------------------------
     * Starts a new game
     * Fetches a list of ads
     * Picks the best ads to solve and solves them
     * Optionally buys items to improve its chances.
     * Repeats from step two until lives run out.
     */

    private static final String[] probabilities = {"Piece of cake", "Walk in the park", "Sure thing", "Quite likely", "Hmmm...."};

    @Getter @Setter
    private static int lives;
    @Getter @Setter
    private static int count;
    @Getter @Setter
    private static List<Message> messages;
    @Getter @Setter
    private static int turn;
    @Getter @Setter
    private static int expires;
    @Getter @Setter
    private static int finalScore;

    public static void main( String args[] ){
        RequestHelper helper = new RequestHelper();
        Game game = helper.startGame();
        ActionsController actions = new ActionsController( game.getGameId() );
        setMessages( actions.getAllMessages());
        setExpires( getMessages().get( 0 ).getExpiresIn() );
        Gson g = new Gson();

        while( getTurn() < 100 ){
            System.out.println(getExpires());
            getMessages().stream().forEach( msg -> {
                Solve solve;
                if( Arrays.asList( probabilities ).contains( msg.getProbability() ) && hasNotExpired()){
                    solve = actions.solveAd( msg.getAdId() );
                    setLives( solve.getLives() );
                    setFinalScore( solve.getScore() );
                    setTurn( solve.getTurn() );
                }
            } );

            if( lives == 0 ){
                break;
            }

            System.out.println("---------------------");
            System.out.println("Getting new messages.");
            messages = actions.getAllMessages();
            setExpires( getExpires() + messages.get( 0 ).getExpiresIn() );
            setCount( messages.size());
            continue;
        }

        System.out.println("--------------------------------");
        System.out.println("The final score: "+getFinalScore());
    }

    private static boolean hasNotExpired(){
        return getTurn() < getExpires();
    }
}
