package gui;
import board.ChessBoard;
import creature.*;
import javafx.animation.*;
import javafx.application.Platform;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.layout.Pane;

import java.io.File;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 整个游戏的处理类
 * 管理所有生物体的线程，通过phase对象来调度一些线程处理函数和其他主要方法
 * 初始化了所有需要的生物体对象及其线程
 * 将每个生物体添加进GUI框架并显示
 */
public class BattlePhase {
    private static BattlePhase phase = new BattlePhase();
    public CalabashManage broManager;
    public CalabashBro[] bros;
    public GrandFather GF;
    public SnakeFairy SF;
    public MonsterManage monManager;
    public Being[] monsters;
    public Pane root;
    public Being[] creatures;
    public TextArea textArea;
    private Stage primaryStage;
    private int goodCnt;
    private int badCnt;
    private boolean inGame;
    private boolean replay;
    private ExecutorService exec;

    public BattlePhase() {
        inGame = false;
        root = new Pane();
        Image backgroundImage = new Image("background_2.jpg");
        ImageView backgroundView = new ImageView(backgroundImage);
        backgroundView.setFitWidth(1200);
        backgroundView.setFitHeight(560);
        root.getChildren().add(backgroundView);
        textArea = new TextArea();
        textArea.setPrefSize(1200, 100);
        //textArea.setLayoutX(0);
        //textArea.setLayoutY(560);
        textArea.setEditable(false);
        ScrollPane scrollPane = new ScrollPane(textArea);
        scrollPane.setPrefSize(1200, 100);
        scrollPane.setLayoutX(0);
        scrollPane.setLayoutY(560);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        root.getChildren().add(scrollPane);
        broManager = new CalabashManage();
        bros = broManager.getBros();
        GF = new GrandFather();
        SF = new SnakeFairy();
        monManager = new MonsterManage();
        monsters = monManager.getMonsters();
        goodCnt = 8;
        badCnt = 8;
        textArea.appendText("葫芦娃VS妖精 Version2 Q改变随机阵型 空格开始 L读档 S存档\n");
        textArea.appendText("当一个生物体进行移动时，他会攻击相距小于等于两个方格内(包括斜方向)的敌人，胜利则继续前进，死亡则留下尸体\n");
        textArea.appendText("尊重逝者，尸体不可踏过，即尸体所在位置不能有其他生物体存在\n");
        textArea.appendText("当某一阵营的角色全部阵亡时，另一方将获得胜利\n");
        textArea.appendText("当前版本尚未实现多次游戏功能，在读档重放后若想重新开始则需重新启动程序\n");
     /*   root.getChildren().add(SF.label);
        root.getChildren().add(GF.label);
        for (int i = 0; i < 7; i++) {
            root.getChildren().add(bros[i].label);
            root.getChildren().add(monsters[i].label);
        }*/
        creatures = new Being[16];
        setCreatures();
        for(Being ob: creatures){
            root.getChildren().add(ob.label);
        }
    }

    public boolean getInGame(){
        return inGame;
    }

    public static BattlePhase getPhase(){
        return phase;
    }

    public void setCreatures(){
        for(int i = 0; i < 7; i++){
            creatures[i] = bros[i];
            creatures[i + 7] = monsters[i];
        }
        creatures[14] = GF;
        creatures[15] = SF;
    }

    public void setCreaturesPos(){
        for(int i = 0; i < 7; i++){
            creatures[i].changePosX(bros[i].getPosX());
            creatures[i].changePosY(bros[i].getPosY());
            creatures[i + 7].changePosX(monsters[i].getPosX());
            creatures[i + 7].changePosY(monsters[i].getPosY());
        }
        creatures[14].changePosX(GF.getPosX());
        creatures[14].changePosY(GF.getPosY());
        creatures[15].changePosX(SF.getPosX());
        creatures[15].changePosY(SF.getPosY());
    }

    public void initial() {
     //   broManager.shuffleBros();
     //   monManager.shuffleMonsters();
        ChessBoard.getBoard().changeDeploy(1, bros);
        ChessBoard.getBoard().changeDeploy(1, monsters);
        ChessBoard.getBoard().setGrandFather(GF);
        ChessBoard.getBoard().setSnakeMonarchess(SF);
        setCreaturesPos();
        changeLayOut();
    }


    /**
     * 修改生物体对应GUI的所在位置来实现动态GUI
     */
    public void changeLayOut() {
        for(Being ob: creatures){
            ob.changeLayOut();
         //   ob.label.setLayoutX(ob.getPosX() * 80);
         //   ob.label.setLayoutY(ob.getPosY() * 80);
        }
    }

    /**
     * 从Main函数GUI初始化调度跳转至游戏战斗过程
     * 调用初始化布阵，记录生物体初始状态
     * @param primaryStage
     */
    public void jumpInto(Stage primaryStage) {
        this.primaryStage = primaryStage;
        initial();
        setCreaturesPos();
    }

    /**
     * 开始游戏，初始化两方阵营里的生物体数目，创建线程池并加入线程
     */
    public void startGame(){
        inGame = true;
        goodCnt = 8;
        badCnt = 8;
        exec = Executors.newCachedThreadPool();
        for(Being ob: creatures){
            exec.execute(ob);
        }
        exec.shutdown();
    }

    public void readFileList(){
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("读取历史记录文件");
        File file = fileChooser.showOpenDialog(primaryStage);
        if(file != null){
            FileSystem.getInstance().readFromHistory(file);
        }
    }

    public void saveFileList(){
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("保存记录");
        File file = fileChooser.showOpenDialog(primaryStage);
        if(file != null){
            FileSystem.getInstance().saveToHistory(file);
        }
    }

    public boolean getReplay(){
        return this.replay;
    }

    public void startReplay(){
        replay = true;
        inGame = true;
        goodCnt = 8;
        badCnt = 8;
        for(Being ob: creatures){
            ob.setLifePoint(10);
            ob.setStateMove();
            ob.label.setOpacity(1);
            ob.label.setRotate(0);
            ob.setAlive(true);
        }
        readFileList();
        for(Being ob: creatures){
            ob.setInitialPos();
        }
        BattlePhase.getPhase().textArea.appendText("读取存档成功，开始战斗回放!\n");
//        System.out.println(creatures[0].getChange());
//        creatures[0].setInitialPos();
        exec = Executors.newCachedThreadPool();
        for(Being ob: creatures){
            exec.execute(ob);
        }
        exec.shutdown();
    }

    /**
     * 下面两个函数实时改变当前线程中存活的属于Good和Bad阵营的生物体的数量
     * 当某一方先变为0时，另一方即获得游戏胜利，开始存储游戏记录
     */
    public void deBadCnt(){
        badCnt--;
        if(badCnt == 0 && inGame){
            inGame = false;
            for(Being ob: creatures){
                ob.setLifePoint(0);
            }
            if(!replay){
                FileSystem.getInstance().setHistory();
                textArea.appendText("妖怪全部阵亡，葫芦娃与爷爷获得胜利！\n");
                System.out.println("妖怪全部阵亡，葫芦娃与爷爷获得胜利！");
            }
//            saveFileList();
        }
    }

    public void deGoodCnt(){
        goodCnt--;
        if(goodCnt == 0 && inGame){
            inGame = false;
            for(Being ob: creatures){
                ob.setLifePoint(0);
            }
            if(!replay){
                FileSystem.getInstance().setHistory();
                textArea.appendText("葫芦娃与爷爷全部阵亡，妖怪阵营获得胜利！\n");
                System.out.println("葫芦娃与爷爷全部阵亡，妖怪阵营获得胜利！");
            }
//            saveFileList();
        }
    }
}