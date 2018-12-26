# Java程序设计 FinalProject: CalabashProject(葫芦娃VS妖精)
###### 161220022 陈哲霏 2018/12/27
### 设计思想

### 工程概述
- 总工程实现了图形化界面下的葫芦娃VS妖精对战模型，进入游戏时界面如下(已按下Q随机布阵一次)：

![初始界面(按下Q随机布阵后)](https://github.com/NovelistChan/CalabashFinal/blob/master/myhomework/%E9%99%88%E5%93%B2%E9%9C%8F-161220022/CalabashProject/BattlePrepare.png)

- 按下空格后开始战斗，各生物体按一定概率向周围4个方向随机移动，在检测到周围相距2个方格内有敌人后(考虑到相距1格可能会在后期使得战场被尸体分割而无法继续战斗)，向该敌人发起攻击，并以一定概率决定生还者，死者留下半透明处理的尸体在原处，将占据一个格子。一个最终结果的例子如下：

![战斗最终结果](https://github.com/NovelistChan/CalabashFinal/blob/master/myhomework/%E9%99%88%E5%93%B2%E9%9C%8F-161220022/CalabashProject/BattleEnd.png)

- 在战斗结束后，用户可按下S键来存储本次对战流程的全部信息。在战斗开始前和战斗结束后，用户均可通过按下L键来加载某次记录文件，该记录文件将自动播放至战斗结束。文件列表界面如下：

### 类与模块划分
- 总工程共分为5个包：creature、util、deploy、board、gui.
  - creature包:包含了所有生物体的类以及两个生物阵营的管理类(CalabashManage与MonsterManage)，所有生物体均继承自父类Being，Being继承了Runnable接口以实现线程工作。
  - util包:包含了一个cheer接口(实际上并没有在GUI中体现)，因为还未想到怎么很好地去实现加油助威的方法，故未加入GUI。
  - deploy包:包含了生物体所能排列形成的共8个阵型类，每个类中实现了阵型的排列方法，在board包中被调用来将战场与阵法相连通。
  - board包:实现了战场(棋盘)类的定义，在本次工程中，我将战场视为一个7x15的棋盘，好人阵营与坏人阵营在一开始分占棋盘的两侧，在战斗开始后逐步逼近对方构成战斗流程。ChessBoard类中实例化了一个board对象，它统帅了整个棋盘布局，存储了当前战场的所有信息，防止排兵布阵出现错误，也为GUI做铺垫。
  - gui包:实现了三个类：BattlePhase、FileSystem以及Main，连接了GUI、主战斗流程、文件IO与程序入口。与ChessBoard类似，BattlePhase与FileSystem有各自的实例，统筹管理所有线程的战斗与存储读取信息操作。
  
### Java面向对象与本次工程
- 继承与多态
- 异常处理
- 输入输出
- 并发与多线程
- Lamda表达式
- 泛型
- 设计模式
