package creature;
import javafx.scene.image.*;
public class Scorpion extends BadCreature{
    Scorpion(){
        super("蝎子精");
        this.image = new Image("Scorp.jpg");
        setImageView();
        setLabel();
    }
}
