package deploy;
import creature.*;

//雁行
public class GooseFly {
    public static void deployGooseFly(int index, Being[] arr){
        if(index == 0){
            for(int i = 0; i < 7; i++){
                arr[i].changePosX(i); arr[i].changePosY(i);
            }
        } else{
            for(int i = 0; i < 7; i++){
                arr[i].changePosX(14 - i); arr[i].changePosY(i);
            }
        }
    }
}
