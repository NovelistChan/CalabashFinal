package cn.edu.nju.czf;
import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import static org.junit.Assert.assertTrue;

import board.ChessBoard;
import creature.CalabashBro;
import creature.CalabashManage;
import creature.MonsterManage;
import gui.BattlePhase;
import gui.FileSystem;
import gui.Main;
import javafx.stage.Stage;
import org.junit.Test;
import creature.*;
import board.*;
/**
 * Unit test for simple App.
 */
public class AppTest 
{
    /**
     * Rigorous Test :-)
     */
    @Test
    public void testBeing(){
        Being test = new Being("测试");
        if(test.getName() != "测试"){
            assertTrue("生成生物出错！", false);
        }
        assertTrue(true);
    }
}
