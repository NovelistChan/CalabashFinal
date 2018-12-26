package deploy;
import creature.*;

//鹤翼
public class CraneWing {
    public static void deployCraneWing(int index, Being[] arr){
        if(index == 0){
            arr[0].changePosX(index++); arr[0].changePosY(0);
            arr[1].changePosX(index++); arr[1].changePosY(1);
            arr[2].changePosX(index++); arr[2].changePosY(2);
            arr[3].changePosX(index--); arr[3].changePosY(3);
            arr[4].changePosX(index--); arr[4].changePosY(4);
            arr[5].changePosX(index--); arr[5].changePosY(5);
            arr[6].changePosX(index); arr[6].changePosY(6);
        } else{
            arr[0].changePosX(index--); arr[0].changePosY(0);
            arr[1].changePosX(index--); arr[1].changePosY(1);
            arr[2].changePosX(index--); arr[2].changePosY(2);
            arr[3].changePosX(index++); arr[3].changePosY(3);
            arr[4].changePosX(index++); arr[4].changePosY(4);
            arr[5].changePosX(index++); arr[5].changePosY(5);
            arr[6].changePosX(index); arr[6].changePosY(6);
        }
    }
}
