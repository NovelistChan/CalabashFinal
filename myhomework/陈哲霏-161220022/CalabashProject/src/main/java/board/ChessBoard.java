package board;
import deploy.*;
import creature.*;
import java.util.*;
import javafx.scene.image.*;

/**
 * 棋盘类，用来定义葫芦娃与妖怪们所战斗的区域
 * 用函数连接了deploy包，来实现生物体的变阵操作
 * 同时实时监控区域上每个点的具体情况
 */
public class ChessBoard {
    private static ChessBoard board = new ChessBoard();
    private static ChessBoardElement[][] chessBoardView;
    private static int deployIndex;

    public static ChessBoard getBoard(){
        return board;
    }

    public ChessBoard(){
        deployIndex = 0;
        chessBoardView = new ChessBoardElement[7][15];
        for(int i = 0; i < 7; i++){
            for(int j = 0; j < 15; j++){
                Image image = new Image("back_1.jpg");
                chessBoardView[i][j] = new ChessBoardElement();
                chessBoardView[i][j].element = new Being("空");
                chessBoardView[i][j].element.image = image;
                chessBoardView[i][j].element.imageView = new ImageView(image);
                chessBoardView[i][j].changeTaken(false);
            }
        }
    }

    public ChessBoardElement[][] getChessBoardView(){
        return chessBoardView;
    }

    public void cleanChessBoard(){
        for(int i = 0; i < 7 ; i++){
            for(int j = 0; j < 15; j++){
                Image image = new Image("back_1.jpg");
                chessBoardView[i][j].element = new Being("空");
                chessBoardView[i][j].element.image = image;
                chessBoardView[i][j].element.imageView = new ImageView(image);
                chessBoardView[i][j].changeTaken(false);
            }
        }
    }

    /**
     * 连接deploy包，实现阵法变换
     * @param key 阵法编号
     * @param arr 参与变阵的生物体集
     */
    public void changeDeploy(int key, Being[] arr){
        if(arr[0].getCamp() == "Good"){
            deployIndex = 0;
        }else deployIndex = 14;
        removeInChessBoard(arr);
        switch (key){
            case 1: LongSnake.deployLongSnake(deployIndex, arr); break;
            case 2: CraneWing.deployCraneWing(deployIndex, arr); break;
            case 3: NewMoon.deployNewMoon(deployIndex, arr); break;
            case 4: CrossWheel.deployCrossWheel(deployIndex, arr); break;
            case 5: Scale.deployScale(deployIndex, arr); break;
            case 6: Square.deploySquare(deployIndex, arr); break;
            case 7: Arrow.deployArrow(deployIndex, arr); break;
            case 8: GooseFly.deployGooseFly(deployIndex, arr); break;
            default: break;
        }
        putInChessBoard(arr);
    }

    public void printChessBoard(){
        System.out.println("当前对战情况如下:");
        for(int i = 0; i < 7; i++){
            for(int j = 0; j < 15; j++){
               System.out.print(chessBoardView[i][j].element.getName());
            }
            System.out.println();
        }
    }

    /**
     * 下面四个函数分为两类：1.从棋盘中抹掉该生物体/生物集的信息 2.将某生物体/生物集放入棋盘
     * @param arr
     */
    public void removeInChessBoard(Being[] arr){
        for(int i = 0; i < 7; i++){
            chessBoardView[arr[i].getPosY()][arr[i].getPosX()].element = new Being("空");
            chessBoardView[arr[i].getPosY()][arr[i].getPosX()].changeTaken(false);
        }
    }

    public void removeInChessBoard(Being a){
        chessBoardView[a.getPosY()][a.getPosX()].element = new Being("空");
        chessBoardView[a.getPosY()][a.getPosX()].changeTaken(false);
    }

    public void putInChessBoard(Being[] arr){
        for(int i = 0; i < 7; i++){
            chessBoardView[arr[i].getPosY()][arr[i].getPosX()].element = arr[i];
            chessBoardView[arr[i].getPosY()][arr[i].getPosX()].changeTaken(true);
        }
    }

    public void putInChessBoard(Being a){
        chessBoardView[a.getPosY()][a.getPosX()].element = a;
        chessBoardView[a.getPosY()][a.getPosX()].changeTaken(true);
    }

    /**
     * 放置爷爷的位置
     * @param grandFatherObject
     */
    public void setGrandFather(GrandFather grandFatherObject){
        Random ra = new Random();
        boolean findPosition = false;
        int PosX, PosY;
        while(!findPosition){
            PosX = ra.nextInt(7);
            PosY = ra.nextInt(4);
            if(!chessBoardView[PosX][PosY].getTakenValue()){
                if(chessBoardView[grandFatherObject.getPosY()][grandFatherObject.getPosX()].getElement().getName() == "爷爷"){
                    chessBoardView[grandFatherObject.getPosY()][grandFatherObject.getPosX()].changeTaken(false);
                    chessBoardView[grandFatherObject.getPosY()][grandFatherObject.getPosX()].element = new Being("空");
                    chessBoardView[grandFatherObject.getPosY()][grandFatherObject.getPosX()].element.image = new Image("back_1.jpg");
                    chessBoardView[grandFatherObject.getPosY()][grandFatherObject.getPosX()].element.imageView = new ImageView("back_1.jpg");
                }
                chessBoardView[PosX][PosY].changeTaken(true);
                chessBoardView[PosX][PosY].element = grandFatherObject;
                findPosition = true;
                grandFatherObject.changePosX(PosY);
                grandFatherObject.changePosY(PosX);
            }
        }
    }

    /**
     * 放置蛇精的位置
     * @param snakeObject
     */
    public void setSnakeMonarchess(SnakeFairy snakeObject) {
        Random ra = new Random();
        boolean findPosition = false;
        int PosX, PosY;
        while (!findPosition) {
            PosX = ra.nextInt(7);
            PosY = ra.nextInt(4) + 11;
            if (!chessBoardView[PosX][PosY].getTakenValue()) {
                if(chessBoardView[snakeObject.getPosY()][snakeObject.getPosX()].getElement().getName()=="蛇精"){
                    chessBoardView[snakeObject.getPosY()][snakeObject.getPosX()].changeTaken(false);
                    chessBoardView[snakeObject.getPosY()][snakeObject.getPosX()].element = new Being("空");
                    chessBoardView[snakeObject.getPosY()][snakeObject.getPosX()].element.image = new Image("back_1.jpg");
                    chessBoardView[snakeObject.getPosY()][snakeObject.getPosX()].element.imageView = new ImageView("back_1.jpg");
                }
                chessBoardView[PosX][PosY].changeTaken(true);
                chessBoardView[PosX][PosY].element = snakeObject;
                findPosition = true;
                snakeObject.changePosX(PosY);
                snakeObject.changePosY(PosX);
            }

        }
    }
}
