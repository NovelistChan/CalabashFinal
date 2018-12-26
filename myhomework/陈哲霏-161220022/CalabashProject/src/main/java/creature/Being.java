package creature;
import board.ChessBoard;
import gui.*;
import javafx.scene.image.*;
import javafx.scene.control.Label;

import java.util.ArrayList;
import java.util.Random;

enum State{BATTLE, MOVE}//定义了生物体线程中的两种状态：移动，战斗

/**
 * 生命体大类，是所有不同生物的共同父类
 */
public class Being implements Runnable{
    private String name; //生物体名字
    private boolean alive; //是否存活
    private ArrayList<String> history; //历史记录
    private int historyPointer; //用来读取历史记录
    private int posX;
    private int posY; //坐标
    private String camp; //所属阵营
//    private int atk;
//    private int def;
    private State state; //线程状态
    private int lp; //生命点数
    public Image image; //脸
    public ImageView imageView; //脸的GUI对象
    public Label label; //脸放在一个Label里显示

    public Being(String name){
        this.name = name;
        this.alive = true;
        this.posX = 0;
        this.posY = 0;
        this.lp = 10;
        this.state = State.MOVE;
        this.history = new ArrayList<String>();
        this.historyPointer = 0;
    }

    public void die(){//翘辫子
        this.alive = false;
        System.out.println(this.name + "已阵亡！");
        BattlePhase.getPhase().textArea.appendText(this.name + "已阵亡!\n");
    //    saveDieToHistory();
    }
    public void setAlive(boolean cond){
        alive = cond;
    }
    public ArrayList<String> getHistory() {
        return history;
    }
    public void setHistory(ArrayList<String> list){
        //this.history = new ArrayList<String>();
        this.history = list;
        this.historyPointer = 0;
    }
    public void setStateMove(){
        this.state = State.MOVE;
    }
    public String getName(){
        return this.name;
    }
    public String getCamp() { return this.camp; }
    public boolean aliveCheck(){
        return this.alive;
    }
    public int getPosX(){
        return this.posX;
    }
    public int getPosY(){
        return this.posY;
    }
    public void changePosX(int newPosX){
        this.posX = newPosX;
    }
    public void changePosY(int newPosY){
        this.posY  = newPosY;
    }
    public void setCamp(String str) { this.camp = str; }
    public void setImage(Image newImage) { this.image = newImage; }
    public void setImageView() {
        this.imageView = new ImageView(this.image);
        imageView.setFitWidth(80); imageView.setFitHeight(80);
    }
    public void setLabel(){
        this.label = new Label("", this.imageView);
    }
    public void setLifePoint(int newLifePoint){ this.lp = newLifePoint; }

    /**
     * 修改生物体对应GUI的所在位置来实现动态GUI
     */
    public void changeLayOut(){
        this.label.setLayoutX(posX * 80);
        this.label.setLayoutY(posY * 80);
    }

    public void saveMoveToHistory(){
        String strPosX = String.valueOf(posX);
        if(posX <= 9 && posX >= 0){
            strPosX = "0" + strPosX;
        }
        String strPosY = String.valueOf(posY);
        strPosY = "0" + strPosY;//y的取值必在0-6之间
        history.add(strPosX + strPosY);
    }

    public void saveDieToHistory(){
        String dying = "DEAD";
        history.add(dying);
    }

    public void saveFakeDieToHistory(){
        String fake = "FAKE";
        history.add(fake);
    }

    private String getChange(){//获取该步的变化信息，是一个长度为4的字符串，可能为XY坐标，也可能为DEAD表示死亡
        String str = new String();
        try{
            str = history.get(0).substring(this.historyPointer, this.historyPointer + 4);
            this.historyPointer = this.historyPointer + 4;
        } catch (Exception e){
            System.out.println(e.getClass() + "inChange");
        }
        System.out.println(this.name + "的pointer读到:" + historyPointer + " 获取到的操作信息str:" + str);
        return str;
    }

    public void setInitialPos(){
        this.historyPointer = 0;
        String str = getChange();
        String strPosX = str.substring(0, 2);
        String strPosY = str.substring(2, 4);
        int newPosX = Integer.valueOf(strPosX);
        int newPosY = Integer.valueOf(strPosY);
        changePosX(newPosX);
        changePosY(newPosY);
//        System.out.println("x:" + newPosX + " y:" + newPosY);
        changeLayOut();
    }

    /**
     * 线程的具体运行操作
     * 实现时分为两种状态处理，并通过函数与BattlePhase相关联
     */
    @Override
    public void run(){
       // System.out.println("inRun");
        if(BattlePhase.getPhase().getReplay()){
        //    setInitialPos();
        }else if(BattlePhase.getPhase().getInGame()){
            saveMoveToHistory();
        }
        try{
            //System.out.println("inTry");
            //String str = getChange();
            boolean fakeFlag = false;
            while(lp != 0){
                if(BattlePhase.getPhase().getReplay()){
                    //if(lp == 0) break;
                    //if(historyPointer >= history.get(0).length()){
                      //  System.out.println(history.get(0).length() + name);
                      //  lp = 0;
                      //  break;
                    //}
                    String str = getChange();
                    //boolean changeFlag = false;//这步操作是否成功映射
                    if(str.equals("FAKE")){
                        this.lp = 0;
                        fakeFlag = true;
                        break;
                    }else if(str.equals("DEAD")){
                        this.lp = 0;
                        this.label.setRotate(180);
                        this.label.setOpacity(0.5);
                        //this.die();
                        //changeFlag = true;
                        break;
                    } else {
                        String strPosX = str.substring(0, 2);
                        String strPosY = str.substring(2, 4);
                        int newPosX = Integer.valueOf(strPosX);
                        int newPosY = Integer.valueOf(strPosY);
                        changePosX(newPosX);
                        changePosY(newPosY);
                        changeLayOut();
                    } //else{//没有发生改变时，读取指针要回退，避免略过步骤
                        //this.historyPointer = this.historyPointer - 4;
                    //}
                    //if(!changeFlag){
                    //    this.historyPointer = this.historyPointer - 4;
                    //}
                    //if(state == State.MOVE){
                      //  state = State.BATTLE;
                    //} else state = State.MOVE;
                } else {
                    if(this.state == State.MOVE){//移动状态下，随机选取下一个空的棋盘坐标，若没找到则不动
                        Random ra = new Random();
                        int nextX = posX; int nextY = posY;
                        double raNum = ra.nextDouble();
                        if(raNum > 0.6) {
                            if(this.camp == "Good"){
                                nextX = posX + 1; nextY = posY;
                            } else {
                                nextX = posX - 1; nextY = posY;
                            }
                        } else if(raNum > 0.4) {
                            nextX = posX; nextY = posY + 1;
                        } else if(raNum > 0.2) {
                            nextX = posX; nextY = posY - 1;
                        } else if(raNum > 0) {
                            if(this.camp == "Good"){
                                nextX = posX - 1; nextY = posY;
                            } else {
                                nextX = posX + 1; nextY = posY;
                            }
                        }
                        if(nextX > 14){
                            nextX -= 2;
                        }
                        if(nextX < 0){
                            nextX += 2;
                        }
                        if(nextY > 6){
                            nextY -= 2;
                        }
                        if(nextY < 0){
                            nextY += 2;
                        }
                        synchronized (ChessBoard.getBoard()){//一个棋盘位置只能由一个生物占领
                            if(ChessBoard.getBoard().getChessBoardView()[nextY][nextX].element.getName() == "空"){
                                ChessBoard.getBoard().removeInChessBoard(this);
                                changePosX(nextX);
                                changePosY(nextY);
                                //save file posx+posy
                                //saveMoveToHistory();
                                ChessBoard.getBoard().putInChessBoard(this);
                                changeLayOut();
                            }
                        }
                        saveMoveToHistory();
                        this.state = State.BATTLE;
                    } else if(this.state == State.BATTLE) {//对其余8个方向的棋盘位置发起进攻，在血量为0时结束循环
                        for (int i = posY - 2; i <= posY + 2; i++) {//增加了攻击距离为2，考虑到距离为1的情况下，会出现被尸体阻隔而无法移动、攻击或被攻击的情况
                            for (int j = posX - 2; j <= posX + 2; j++) {
                                if (this.lp == 0) break;
                                if (i <= 6 && i >= 0 && j <= 14 && j >= 0) {
                                    synchronized (ChessBoard.getBoard()) {
                                        Being target = ChessBoard.getBoard().getChessBoardView()[i][j].element;
                                        if (target.getName() != "空") {
                                            synchronized (target) {//一个战斗流程中的两个角色不允许被另一个战斗流程包含
                                                if (this.camp != target.camp && target.lp != 0) {
                                                    BattlePhase.getPhase().textArea.appendText(this.name + "与" + target.getName() + "相遇，开始战斗！\n");
                                                    Random ra1 = new Random();
                                                    double winPercent = ra1.nextDouble();
                                                    if (winPercent > 0.5) {
                                                        this.lp = 0;
                                                        //ChessBoard.getBoard().removeInChessBoard(this);
                                                        this.label.setRotate(180);
                                                        this.label.setOpacity(0.5);
                                                     //   this.die();
                                                     //   this.saveDieToHistory();
                                                        target.notify();
                                                    } else {
                                                        target.lp = 0;
                                                        target.label.setRotate(180);
                                                        target.label.setOpacity(0.5);
                                                     //   target.die();
                                                     //   target.saveDieToHistory();
                                                        target.notify();
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                            if (this.lp == 0) break;
                        }
                        this.state = State.MOVE;
                    }
                }
                synchronized (this){
                    if(lp == 0) break;
                    wait(500);
                }
                //lp -= 1;
            }
            if(this.lp == 0 && !BattlePhase.getPhase().getReplay() && BattlePhase.getPhase().getInGame()){
                this.die();
                this.saveDieToHistory();
            }
            if(this.lp == 0 && !BattlePhase.getPhase().getReplay() && !BattlePhase.getPhase().getInGame()){
                this.saveFakeDieToHistory();
            }
            if(this.camp == "Good" && !fakeFlag){
                BattlePhase.getPhase().deGoodCnt();
            } else if(this.camp == "Bad" && !fakeFlag){
                BattlePhase.getPhase().deBadCnt();
            }
        }  catch (Exception e) {
            e.printStackTrace();
        }
    }
}