package creature;

import java.util.Random;

/**
 * 用来管理6个喽啰+1个蝎子精的队列，具体方法同CalabashManage
 */
public class MonsterManage {
    private static Being[] Monsters;
    static Random random = new Random();

    public MonsterManage(){
        Monsters = new Being[7];
        for(int i = 0; i < 6; i++){
            Monsters[i] = new Pawn();
        }
        Monsters[6] = new Scorpion();
    }

    public static Being[] getMonsters(){
        return Monsters;
    }

    public static void shuffleMonsters(){
        for(int i = 0; i < 7; i++){
            int p = random.nextInt(7);
            Being tmp = Monsters[p];
            Monsters[p] = Monsters[i];
            Monsters[i] = tmp;
        }
    }
}
