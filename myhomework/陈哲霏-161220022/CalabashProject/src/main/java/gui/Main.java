package gui;
import board.ChessBoard;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import java.util.*;

/**
 * 主函数，程序的入口
 * 实现了键盘监听以方便响应用户的操作
 */
public class Main extends Application {
//    BattlePhase phase = new BattlePhase();

    @Override
    public void start(Stage primaryStage){
        try {
            primaryStage.setTitle("葫芦娃VS妖精 Version2 Q改变随机阵型 空格开始 L读档 S存档");
         //   phase.root.setPrefSize(1200, 560);
            Scene scene = new Scene(BattlePhase.getPhase().root, 1200, 660);
            scene.setOnKeyReleased(new EventHandler<KeyEvent>() {
                @Override
                public void handle(KeyEvent event) {
                    try{
                        if(event.getCode() == KeyCode.Q){//改变初始阵型
                            BattlePhase.getPhase().textArea.appendText("双方阵营随机变换阵型！\n");
                         //   System.out.println("Start got!");
                            Random ra = new Random();
                            int key1 = ra.nextInt(8) + 1;
                            int key2 = ra.nextInt(8) + 1;
                            ChessBoard.getBoard().changeDeploy(key1, BattlePhase.getPhase().bros);
                            ChessBoard.getBoard().changeDeploy(key2, BattlePhase.getPhase().monsters);
                            ChessBoard.getBoard().setSnakeMonarchess(BattlePhase.getPhase().SF);
                            ChessBoard.getBoard().setGrandFather(BattlePhase.getPhase().GF);
                            BattlePhase.getPhase().setCreaturesPos();
                            BattlePhase.getPhase().changeLayOut();
                        } else if(event.getCode() == KeyCode.SPACE){
                            BattlePhase.getPhase().textArea.appendText("战斗开始!\n");
                            BattlePhase.getPhase().startGame();
                        } else if(event.getCode() == KeyCode.L){
                            if(!BattlePhase.getPhase().getInGame()){
                                BattlePhase.getPhase().textArea.appendText("准备读取存档...\n");
                                BattlePhase.getPhase().startReplay();
                            }
                        } else if(event.getCode() == KeyCode.S){
                            if(!BattlePhase.getPhase().getInGame()){
                                BattlePhase.getPhase().textArea.appendText("准备保存记录...\n");
                                BattlePhase.getPhase().saveFileList();
                                BattlePhase.getPhase().textArea.appendText("保存记录成功!\n");
                            }
                        }
                    } catch(Exception e){
                        e.printStackTrace();
                    }
                }
            });
            primaryStage.setScene(scene);
            //primaryStage.setResizable(false);
            primaryStage.show();
            BattlePhase.getPhase().jumpInto(primaryStage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}

