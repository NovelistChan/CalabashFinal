package deploy;
import creature.*;

//长蛇
public class LongSnake {
    public static void deployLongSnake(int index, Being[] arr){
        for(int i = 0; i < arr.length; i++){
            arr[i].changePosX(index);
            arr[i].changePosY(i);
        }
    }
}
