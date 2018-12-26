package deploy;
import creature.*;

//鱼鳞
public class Scale {
    public static void deployScale(int index, Being[] arr){
        if(index == 0){
            arr[0].changePosX(1); arr[0].changePosY(0);
            arr[1].changePosX(2); arr[1].changePosY(1);
            arr[2].changePosX(3); arr[2].changePosY(2);
            arr[3].changePosX(2); arr[3].changePosY(2);
            arr[4].changePosX(2); arr[4].changePosY(3);
            arr[5].changePosX(1); arr[5].changePosY(2);
            arr[6].changePosX(0); arr[6].changePosY(2);
        } else{
            arr[0].changePosX(13); arr[0].changePosY(0);
            arr[1].changePosX(12); arr[1].changePosY(1);
            arr[2].changePosX(11); arr[2].changePosY(2);
            arr[3].changePosX(12); arr[3].changePosY(2);
            arr[4].changePosX(12); arr[4].changePosY(3);
            arr[5].changePosX(13); arr[5].changePosY(2);
            arr[6].changePosX(14); arr[6].changePosY(2);
        }
    }
}
