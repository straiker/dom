package mod.rait.model;
import lombok.Getter;
import lombok.Setter;

public class Purchase{
    @Getter @Setter
    private boolean shoppingSuccess;
    @Getter @Setter
    private int gold;
    @Getter @Setter
    private int lives;
    @Getter @Setter
    private int level;
    @Getter @Setter
    private int turn;
}
