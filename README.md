# RobocupProject
## 概要
ロボカップの2Dサッカーシミュレーションについての操作を確認するプログラム。  
プログラムはjavaで書くこととする。  
いくつかのミッションをクリアしていく形で理解を深めることにする。  

## はじめに
まずプログラムを走らせるために以下のソフトをダウンロードの必要がある。  

- rcssserveer
- rcssmonitor

https://github.com/herodrigues/robocup2d-tutorial/blob/master/sections/installing-the-soccer-simulator.md  
を参考にインストールすべし

## 使い方、プログラムの説明
### formation.java
エージェントの初期位置を変えるプログラムである。27行目〜37行目までのcase文内の変数m_dKickOffX
（x座標）とm_dKickOffY（y座標）を変更することで任意のフォーメーションを取ることができる。
このプログラムでは両チーム同じようにフォーメーションをとるようにしている。また、初期値として
ゴールから対面のゴールに向かい一直線のプレーヤーが並ぶように設定した。








## 参考文献
http://www.morikita.co.jp/books/book/2189
