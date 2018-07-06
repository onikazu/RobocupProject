//==============================================
//審判の笛を聴く
public class AloneRunner extends StopPlayers {
    //デバッグのフラグを用意する
    // private boolean m_debugLv03 = false;

    //==============================================
    //初期メッセージの解析
    protected void analyzeInitialMessage(String message) {
        super.analyzeInitialMessage(message);
        //デバッグ用の表示をする
//        if (m_debugLv03) {
//            System.out.println("m_strPlaymode = " + m_strPlayMode);
//        }
    }

    //==============================================
    //サーバから受け取ったメッセージを処理する
    protected void analyzeMessage(String message) {
        super.analyzeMessage(message);
        if (message.startsWith("(hear ")) {
            //聴覚メッセージ
            analyzeAuralMessage(message);
        }
    }

    //==============================================
    //聴覚メッセージを解析する
    protected void analyzeAuralMessage(String message) {
        /*messageの例 = "(hear 0 referee kick_off_l)"*/
        //聴覚メッセージを解析し、発言者と内容を得る
        int index0 = message.indexOf(" ");
        int index1 = message.indexOf(" ", index0 + 1);
        int index2 = message.indexOf(" ", index1 + 1);
        int index3 = message.indexOf(")", index2 + 1);
        String strSpeaker = message.substring(index1 + 1, index2);
        String strContent = message.substring(index2 + 1, index3);
        if (strSpeaker.startsWith("referee") == true) {
            //レフェリーの笛の処理
            m_strPlayMode = strContent;
//            if (m_debugLv03) {
//                System.out.println("m_strPlayMode = " + m_strPlayMode);
//            }
        }
    }

    //==============================================
    //命令を作る
    protected void play(String message) {
        super.play(message);
        String command = "";
        if (m_strPlayMode.startsWith("kick_off_"))
            command = "(dash -50)";
        else
            command = "(dash 50)";
        send(command);
    }

    //==============================================
    //メイン
    public static void main(String[] args) {
        StopPlayers[] player1 = new StopPlayers[11];
        Thread[] thread1 = new Thread[11];
        int i;
        for (i = 0; i < 11; i++) {
            String teamname = "all_stopper";
            player1[i] = new StopPlayers();
            thread1[i] = new Thread(player1[i]);
            player1[i].initialize((i % 11 + 1), teamname, "localhost", 6000);
            thread1[i].start();
        }
        StopPlayers[] player2 = new StopPlayers[11];
        Thread[] thread2 = new Thread[1];
        for (i = 0; i < 11; i++) {
            String teamname = "alone_runners";
            if (i == 10) {
                player2[i] = new AloneRunner();
                thread2[i] = new Thread(player2[i]);
                player2[i].initialize((i % 11 + 1), teamname, "localhost", 6000);
                thread2[i].start();
            }else{
                player2[i] = new StopPlayers();
                thread2[i] = new Thread(player2[i]);
                player2[i].initialize((i % 11 + 1), teamname, "localhost", 6000);
                thread2[i].start();
            }
        }
        //player1[10].m_debugLv07 = true;
        System.out.println("試合への登録終了");
    }
}

