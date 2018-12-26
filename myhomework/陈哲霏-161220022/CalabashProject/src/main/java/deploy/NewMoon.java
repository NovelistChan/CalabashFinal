package deploy;
import creature.*;

//偃月
public class NewMoon {
    public static void deployNewMoon(int index, Being[] arr){
        arr[3].changePosX(index); arr[3].changePosY(3);
        if(index == 0){
            index++;
            arr[2].changePosX(index); arr[2].changePosY(2);
            arr[4].changePosX(index); arr[4].changePosY(4);
            index++;
            arr[1].changePosX(index); arr[1].changePosY(1);
            arr[5].changePosX(index); arr[5].changePosY(5);
            index++;
            arr[0].changePosX(index); arr[0].changePosY(0);
            arr[6].changePosX(index); arr[6].changePosY(6);
        } else{
            index--;
            arr[2].changePosX(index); arr[2].changePosY(2);
            arr[4].changePosX(index); arr[4].changePosY(4);
            index--;
            arr[1].changePosX(index); arr[1].changePosY(1);
            arr[5].changePosX(index); arr[5].changePosY(5);
            index--;
            arr[0].changePosX(index); arr[0].changePosY(0);
            arr[6].changePosX(index); arr[6].changePosY(6);
        }
    }
}
