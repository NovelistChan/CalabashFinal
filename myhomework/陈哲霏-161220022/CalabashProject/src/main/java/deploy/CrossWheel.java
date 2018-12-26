package deploy;
import creature.*;

//衡轭
public class CrossWheel {
    public static void deployCrossWheel(int index, Being[] arr){
        if(index == 0){
            for(int i = 0; i < 7; i++) {
                if (i % 2 == 0) {
                    arr[i].changePosX(index);
                } else arr[i].changePosX(index + 1);
                arr[i].changePosY(i);
            }
        } else{
            for(int i = 0; i < 7; i++){
                if(i % 2 == 0){
                    arr[i].changePosX(index);
                } else arr[i].changePosX(index - 1);
                arr[i].changePosY(i);
            }
        }
    }
}
