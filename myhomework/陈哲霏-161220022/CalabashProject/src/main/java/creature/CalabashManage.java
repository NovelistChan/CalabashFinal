package creature;
import java.util.*;

/**
 * 原本设计的想法是用这个类来统领葫芦娃，来实现阵型改变等操作，但后来发现实现过程比较乱
 * 这个最后的用途就是初始化一个bros[]数组来存放葫芦兄弟以及随机化排序(也就是第二次作业的内容)
 */
public class CalabashManage {
    private static CalabashBro[] bros;
    static Random random = new Random();

    public CalabashManage(){
        bros = new CalabashBro[7];
        for(int i = 0; i < 7; i++){
            bros[i] = new CalabashBro(i);
        }
    }

    public static CalabashBro[] getBros(){
        return bros;
    }

    /**
     * 随机化排序，不过其实后面的实现过程中直接用了阵法，这个方法就用不到了
     */
    public static void shuffleBros(){
        for(int i = 0; i < 7; i++){
            int p = random.nextInt(7);
            CalabashBro tmp = bros[p];
            bros[p] = bros[i];
            bros[i] = tmp;
        }
    }
}
