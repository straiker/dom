package mod.rait.model;
import lombok.Getter;
import lombok.Setter;

public class Solve{
    @Getter @Setter
    private boolean success;
    @Getter @Setter
    private int lives;
    @Getter @Setter
    private int score;
    @Getter @Setter
    private int highScore;
    @Getter @Setter
    private int turn;
    @Getter @Setter
    private String message;
    @Getter @Setter
    private int gold;
}
