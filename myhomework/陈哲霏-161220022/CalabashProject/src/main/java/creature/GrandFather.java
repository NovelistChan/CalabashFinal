package creature;
import util.Cheer;
import javafx.scene.image.*;
public class GrandFather extends GoodCreature implements Cheer{
//    private Image father = new Image("/Image/father.jpg");
    public GrandFather(){
        super("爷爷");
        this.image = new Image("father.jpg");
        setImageView();
        setLabel();
    }

    @Override
    public void cheerUp(){
        System.out.println("爷爷为葫芦娃助威：乖孩子们加油！！！");
    }
}
