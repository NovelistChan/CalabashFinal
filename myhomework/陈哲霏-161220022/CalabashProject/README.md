# Java程序设计 FinalProject: CalabashProject(葫芦娃VS妖精)
###### 161220022 陈哲霏 2018/12/27
### 设计思想
- 主体分类
  - 将整个工程所要操作的主体分为: 生物体、战场、GUI三个部分，其中生物体与战场共用关系:阵型，三个主体共用关系:战斗流程与文件I/O
- 实现流程
  - 类似上帝一样，创建各个生物的实体、战场实体、GUI框架->创建它们在战场中的映射->创建它们与GUI的映射->实现它们与文件的关联->用一个看不见的手(BattlePhase)来管理战斗流程，并将线程的运行体现在GUI上
- 游戏模型设计
  - 将葫芦娃VS妖精视为一款目前还在开发当中的儿童用模型玩具，当前的模型玩具可以自己移动并发现敌方阵营的玩具将其击倒。双方阵营均有8中不同的排兵布阵的方式，需要玩家在游戏开始时自主选择阵型将这些玩具摆放好，然后它们才能开始自动战斗。本Java程序设计模拟了这个摆放玩具到自动战斗到存储战斗记录的整个流程，以测试玩具的可玩性。
  - 游戏需要玩家摆放阵型来启动，于是设计了BattlePhase类来响应，对应实现玩家摆放棋盘、玩具自己战斗、存储战斗记录的几个接口。
  - 本款玩具智能地提供了每个玩具都能记录自己战斗过程的功能(每个玩具都有自己的history记录)，要记录战斗流程，只需玩家准备一个存储文件即可，对应了FileSystem类，在BattlePhase中可调用FileSystem中的读取与存储方法。
  - 玩具摆放需要一个棋盘，对应ChessBoard类。
  - 葫芦娃模型与妖精模型虽有不同，但都是属于玩具大类，所以它们都继承自Being类。
  - 玩具能摆放的阵型是固定地写在说明书里的，对应deploy包里的8个阵型类。
  
### 工程概述
- 总工程实现了图形化界面下的葫芦娃VS妖精对战模型，进入游戏时界面如下(已按下Q随机布阵一次)：

![初始界面(按下Q随机布阵后)](https://github.com/NovelistChan/CalabashFinal/blob/master/myhomework/%E9%99%88%E5%93%B2%E9%9C%8F-161220022/CalabashProject/BattlePrepare.png)

- 按下空格后开始战斗，各生物体按一定概率向周围4个方向随机移动，在检测到周围相距2个方格内有敌人后(考虑到相距1格可能会在后期使得战场被尸体分割而无法继续战斗)，向该敌人发起攻击，并以一定概率决定生还者，死者留下半透明处理的尸体在原处，将占据一个格子。一个最终结果的例子如下：

![战斗最终结果](https://github.com/NovelistChan/CalabashFinal/blob/master/myhomework/%E9%99%88%E5%93%B2%E9%9C%8F-161220022/CalabashProject/BattleEnd.png)

- 在战斗结束后，用户可按下S键来存储本次对战流程的全部信息。在战斗开始前和战斗结束后，用户均可通过按下L键来加载某次记录文件，该记录文件将自动播放至战斗结束。文件列表界面如下：

![保存文件页面(读取界面与此相同)](https://github.com/NovelistChan/CalabashFinal/blob/master/myhomework/%E9%99%88%E5%93%B2%E9%9C%8F-161220022/CalabashProject/SaveFile.png)

### 类与模块划分
- 总工程共分为5个包：creature、util、deploy、board、gui.
  - creature包:包含了所有生物体的类以及两个生物阵营的管理类(CalabashManage与MonsterManage)，所有生物体均继承自父类Being，Being继承了Runnable接口以实现线程工作。
  - util包:包含了一个cheer接口(实际上并没有在GUI中体现)，因为还未想到怎么很好地去实现加油助威的方法，故未加入GUI。
  - deploy包:包含了生物体所能排列形成的共8个阵型类，每个类中实现了阵型的排列方法，在board包中被调用来将战场与阵法相连通。
  - board包:实现了战场(棋盘)类的定义，在本次工程中，我将战场视为一个7x15的棋盘，好人阵营与坏人阵营在一开始分占棋盘的两侧，在战斗开始后逐步逼近对方构成战斗流程。ChessBoard类中实例化了一个board对象，它统帅了整个棋盘布局，存储了当前战场的所有信息，防止排兵布阵出现错误，也为GUI做铺垫。
  - gui包:实现了三个类：BattlePhase、FileSystem以及Main，连接了GUI、主战斗流程、文件IO与程序入口。与ChessBoard类似，BattlePhase与FileSystem有各自的实例，统筹管理所有线程的战斗与存储读取信息操作。
  
### Java面向对象与本次工程
- 继承与多态
  - creature包内的UML图，展示了各个生物体类之间的继承关系

![Creature包内的UML图](https://github.com/NovelistChan/CalabashFinal/blob/master/myhomework/%E9%99%88%E5%93%B2%E9%9C%8F-161220022/CalabashProject/CreaturePakage.png)

  - 以Calabash为例的子类生成展现继承的过程，特别地，此处还使用了枚举类(CalabashVariety定义了所有葫芦娃的可枚举实体)
```java
public class CalabashBro extends GoodCreature{
    private String color;
    private int rank;
    public CalabashBro(int i){
        super(CalabashVariety.values()[i].getBroName());
        this.color = CalabashVariety.values()[i].getBroColor();
        this.rank = CalabashVariety.values()[i].getBroNum();
        this.image = CalabashVariety.values()[i].getBroImage();
        setImageView();
        setLabel();
    }
    ...
}
```

  - 多态过程主要体现在BattlePhase中对生物总体的管理
```java
public void setCreatures(){
        for(int i = 0; i < 7; i++){
            creatures[i] = bros[i];
            creatures[i + 7] = monsters[i];
        }
        creatures[14] = GF;
        creatures[15] = SF;//用一个creatures数组管理所有初始化的生物体
    }
```
  
- 异常处理
  - 以存取文件为例
```java
public void saveToHistory(File file){
        FileWriter fileWriter = null;
        try{
            fileWriter = new FileWriter(file);
            ...
        } catch(Exception e){
            e.printStackTrace();
        } finally {
            try {
                fileWriter.close();
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }
```

- 输入输出
  - 除上述的写文件外，添加了键盘监听输入
```java
Scene scene = new Scene(BattlePhase.getPhase().root, 1200, 660);
scene.setOnKeyReleased(new EventHandler<KeyEvent>() {
         @Override
         public void handle(KeyEvent event) {
             try{
                 if(event.getCode() == KeyCode.Q){//改变初始阵型
                            ...
                 } else if(event.getCode() == KeyCode.SPACE){//开始战斗
                            ...
                 } else if(event.getCode() == KeyCode.L){//读档
                            ...
                 } else if(event.getCode() == KeyCode.S){//存档
                            ...
                 }
             } catch(Exception e){
                 e.printStackTrace();
             }
         }
});
```

- 并发与多线程
  - 各个生物体均为一个线程，并发处理主要体现在线程的run()函数内部
```java
public class Being implements Runnable{
         ...
         @override
         public void run(){
                  ...
                  //当要处理生物体移动时
                  synchronized (ChessBoard.getBoard()){//一个棋盘位置只能由一个生物占领
                          ...
                  }
                  //当要处理生物体战斗时
                  synchronized (ChessBoard.getBoard()) {
                          Being target = ChessBoard.getBoard().getChessBoardView()[i][j].element;
                          if (target.getName() != "空") {
                                  synchronized (target) {//一个战斗流程中的两个角色不允许被另一个战斗流程包含
                                          ...
                                  }
                          }
                  }
         }
}
```

- 单元测试
  - 测试单个生物体的生成
```java
@Test
public void testBeing(){
    Being test = new Being("测试");
    if(test.getName() != "测试"){
        assertTrue("生成生物出错！", false);
    }
    assertTrue(true);
}
```

- 集合类型
  - 在历史记录设置时使用了ArrayList
```java
public class FileSystem {
    private static FileSystem instance = new FileSystem();
    private ArrayList<ArrayList<String>> history;
    ...
    ...
    }
```

- 设计模式
  - 装饰器模式
```java
FileWriter fileWriter = null;
BufferedReader bufferedReader = null;
```

  - 迭代器模式
```java
Iterator<ArrayList<String>> iterator = history.iterator();
while(iterator.hasNext()){
    for(String s : iterator.next()){
         fileWriter.write(s);
    }
}   
```

  - 组合模式
```java
root = new Pane();
root.getChildren().add(backgroundView);
textArea = new TextArea();
ScrollPane scrollPane = new ScrollPane(textArea);
root.getChildren().add(scrollPane);
```

### 精彩战斗回顾
- 本次提交中包含了一个rs3.txt文件，存储了到目前为止我认为最精彩的一场战斗，理由如下
  - 各个生物体的移动都比较常规，使得战斗流程不至于过慢
  - 在战斗过程中，妖精阵营方在流程到一半时仅剩下蝎子精1人，而葫芦阵营还剩6个战斗力，但蝎子精仍顽强地连斩4人，虽然最终倒下，但值得记录，下面是战斗半程和战斗结束后的截图

![战斗半程](https://github.com/NovelistChan/CalabashFinal/blob/master/myhomework/%E9%99%88%E5%93%B2%E9%9C%8F-161220022/CalabashProject/InBattleRs.png)

![战斗结束](https://github.com/NovelistChan/CalabashFinal/blob/master/myhomework/%E9%99%88%E5%93%B2%E9%9C%8F-161220022/CalabashProject/EndBattleRs.png)
