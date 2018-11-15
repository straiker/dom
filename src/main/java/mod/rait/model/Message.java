package mod.rait.model;
import lombok.Getter;
import lombok.Setter;

public class Message{

    @Getter @Setter
    private String adId;
    @Getter @Setter
    private String message;
    @Getter @Setter
    private String reward;
    @Getter @Setter
    private int expiresIn;
    @Getter @Setter
    private String probability;

    /*
    *
    {
        "adId": "sBsXc0FJ", String
        "message": "Help Anita Christians to sell an unordinary sheep on the local market", String
        "reward": 26, String
        "expiresIn": 6, int
        "encrypted": null,
        "probability": "Piece of cake" String
    }
    *
    * */

}
