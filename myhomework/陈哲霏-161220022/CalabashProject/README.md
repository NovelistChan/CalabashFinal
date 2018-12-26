# Java程序设计 FinalProject: CalabashProject(葫芦娃VS妖精)
###### 161220022 陈哲霏 2018/12/27
### 工程概述
- 总工程实现了图形化界面下的葫芦娃VS妖精对战模型，进入游戏时界面如下(已按下Q随机布阵一次)：

![初始界面(按下Q随机布阵后)](https://github.com/NovelistChan/CalabashFinal/blob/master/myhomework/%E9%99%88%E5%93%B2%E9%9C%8F-161220022/CalabashProject/BattlePrepare.png)

- 按下空格后开始战斗，各生物体按一定概率向周围4个方向随机移动，在检测到周围相距2个方格内有敌人后(考虑到相距1格可能会在后期使得战场被尸体分割而无法继续战斗)，向该敌人发起攻击，并以一定概率决定生还者，死者留下半透明处理的尸体在原处，将占据一个格子。一个最终结果的例子如下：

![战斗最终结果](https://github.com/NovelistChan/CalabashFinal/blob/master/myhomework/%E9%99%88%E5%93%B2%E9%9C%8F-161220022/CalabashProject/BattleEnd.png)

- 在战斗结束后，用户可按下S键来存储本次对战流程的全部信息。在战斗开始前和战斗结束后，用户均可通过按下L键来加载某次记录文件，该记录文件将自动播放至战斗结束。文件列表界面如下：

### 类与模块划分
