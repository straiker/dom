package mod.rait.model;
import lombok.Getter;
import lombok.Setter;

public class Game{
    @Getter @Setter
    private String gameId;
    @Getter @Setter
    private int lives;
    @Getter @Setter
    private int gold;
    @Getter @Setter
    private int level;
    @Getter @Setter
    private int score;
    @Getter @Setter
    private int highScore;
    @Getter @Setter
    private int turn;
}


/*
*
{
    "gameId": "6ckgIwgN",
    "lives": 3,
    "gold": 0,
    "level": 0,
    "score": 0,
    "highScore": 4321,
    "turn": 0
}
* */