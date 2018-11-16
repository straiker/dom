package dom.rait.controller;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import com.google.gson.Gson;

import dom.rait.model.Solve;
import lombok.Getter;
import lombok.Setter;
import dom.rait.helper.RequestHelper;
import dom.rait.model.Game;
import dom.rait.model.Message;

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

    private static final String[] probabilities = {"Piece of cake", "Walk in the park", "Sure thing", "Quite likely", "Hmmm....", "Gamble",
            "Rather detrimental", "Playing with fire", "Risky", "Impossible"};
    @Getter
    @Setter
    private static int lives = 3;
    @Getter
    @Setter
    private static boolean success;
    @Getter
    @Setter
    private static List<Message> messages;
    @Getter
    @Setter
    private static int turn;
    @Getter
    @Setter
    private static int expires;
    @Getter
    @Setter
    private static int finalScore;

    public static void main( String args[] ){
        RequestHelper helper = new RequestHelper();
        Game game = helper.startGame();
        ActionsController actions = new ActionsController( game.getGameId() );

        while( true ){
            try{
                setMessages( actions.getAllMessages() );
                System.out.println( getTurn() );
                System.out.println( "--------------------------------" );
                setExpires( getExpires() + messages.get( 0 ).getExpiresIn() );
                setSuccess( true );
                getMessages().stream().sorted( Comparator.comparing( Message::getExpiresIn ) ).forEach( msg -> {
                    Solve solve;
                    if( canSolve( msg.getProbability() ) && isSuccess() ){
                        solve = actions.solveAd( msg.getAdId() );
                        if( solve.isSuccess() ){
                            setFinalScore( solve.getScore() );
                            setLives( solve.getLives() );
                            setTurn( solve.getTurn() );
                        }
                        setSuccess( solve.isSuccess() );
                    }
                } );

                if( getLives() == 0 ){
                    break;
                }
            }catch( Exception ex ){
                break;
            }
        }

        System.out.println( "--------------------------------" );
        System.out.println( "The final score: " + getFinalScore() );
    }

    private static boolean canSolve( String probability ){
        return Arrays.asList( probabilities ).contains( probability ) && getTurn() < getExpires() && getLives() > 0;
    }
}
