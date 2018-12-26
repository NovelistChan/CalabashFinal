package creature;
import util.Cheer;
import javafx.scene.image.*;
public class SnakeFairy extends BadCreature implements Cheer{
    public SnakeFairy(){
        super("蛇精");
        this.image = new Image("Snake_1.jpg");
        setImageView();
        setLabel();
    }

    @Override
    public void cheerUp(){
        System.out.println("蛇精为妖怪们助威：小的们，上！！！");
    }
}