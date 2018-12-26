package creature;
import javafx.scene.image.*;
public class Pawn extends BadCreature {
    Pawn(){
        super("喽啰");
        this.image = new Image("pawn.jpg");
        setImageView();
        setLabel();
    }
}
