package board;

import creature.*;
import javafx.scene.image.*;
public class ChessBoardElement {
    private boolean taken;
    public Being element;
 /*   private boolean takenByCalabash;
    private boolean takenByMonster;
    private boolean takenByGrandFa;
    private boolean takenBySnake;
    private boolean takenByScorpion;*/
    ChessBoardElement(){
        taken = false;
        element = new Being("空");
    }
    public boolean getTakenValue(){
        return taken;
    }
    public Being getElement(){
        return element;
    }
    public void changeTaken(boolean newTaken){
        taken = newTaken;
    }
/*    public boolean getTakenByCalabash(){
        return takenByCalabash;
    }
    public boolean getTakenByMonster(){
        return takenByMonster;
    }
    public boolean getTakenByGrandFa(){
        return takenByGrandFa;
    }
    public boolean getTakenByScorpion(){
        return takenByScorpion;
    }
    public boolean getTakenBySnake(){
        return takenBySnake;
    }
    public void changeTaken(boolean newTaken){
        taken = newTaken;
    }
    public void changeTakenByCalabash(boolean newTakenByCalabash){
        takenByCalabash = newTakenByCalabash;
    }
    public void changeTakenByMonster(boolean newTakenByMonster){
        takenByMonster = newTakenByMonster;
    }
    public void changeTakenByScorpion(boolean newTakenByScorpion){
        takenByScorpion = newTakenByScorpion;
    }
    public void changeTakenByGrandFa(boolean newTakenByGrandFa){
        takenByGrandFa = newTakenByGrandFa;
    }
    public void changeTakenBySnake(boolean newTakenBySnake){
        takenBySnake = newTakenBySnake;
    }*/
}

