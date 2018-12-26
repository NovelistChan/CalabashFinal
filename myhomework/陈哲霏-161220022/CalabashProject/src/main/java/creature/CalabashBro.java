package creature;
import javafx.scene.image.*;
enum CalabashVariety{
    FIRST(1), SECOND(2), THIRD(3), FOURTH(4), FIFTH(5), SIXTH(6), SEVENTH(7);
    private int broNum;
    private String broName;
    private String broColor;
    private Image broImage;
    CalabashVariety(int num) {
        switch(num){
            case 1:
                broNum = 1; broName = "老大"; broColor = "赤"; broImage = new Image("hulu_1.jpg"); break;
            case 2:
                broNum = 2; broName = "老二"; broColor = "橙"; broImage = new Image("hulu_2.jpg"); break;
            case 3:
                broNum = 3; broName = "老三"; broColor = "黄"; broImage = new Image("hulu_3.jpg"); break;
            case 4:
                broNum = 4; broName = "老四"; broColor = "绿"; broImage = new Image("hulu_4.jpg"); break;
            case 5:
                broNum = 5; broName = "老五"; broColor = "青"; broImage = new Image("hulu_5.jpg"); break;
            case 6:
                broNum = 6; broName = "老六"; broColor = "蓝"; broImage = new Image("hulu_6.jpg"); break;
            case 7:
                broNum = 7; broName = "老七"; broColor = "紫"; broImage = new Image("hulu_7.jpg"); break;
//            default: System.out.println("葫芦娃只有七个！num应在1-7之间"); break;
        }
    }
    public int getBroNum(){
        return broNum;
    }
    public String getBroName(){
        return broName;
    }
    public String getBroColor(){
        String color;
        switch(broColor){
            case "赤": color = "红色"; break;
            case "橙": color = "橙色"; break;
            case "黄": color = "黄色"; break;
            case "绿": color = "绿色"; break;
            case "青": color = "青色"; break;
            case "蓝": color = "蓝色"; break;
            case "紫": color = "紫色"; break;
            default: color = "颜色错误"; break;
        }
        return color;
    }
    public Image getBroImage(){
        return broImage;
    }
}

public class CalabashBro extends GoodCreature{
    private String color;
    private int rank;

    public CalabashBro(int i){
        super(CalabashVariety.values()[i].getBroName());
        //setCamp("Good");
        this.color = CalabashVariety.values()[i].getBroColor();
        this.rank = CalabashVariety.values()[i].getBroNum();
        this.image = CalabashVariety.values()[i].getBroImage();
        setImageView();
        setLabel();
    }

    public int getRank(){
        return this.rank;
    }

    public String getColor(){
        return this.color;
    }
}
