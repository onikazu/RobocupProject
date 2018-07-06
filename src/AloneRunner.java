//==============================================
//審判の笛を聴く
public class AloneRunner extends PlayerLv02 {
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
            command = "(turn -50)";
        else
            command = "(dash -50)";
        send(command);
    }

    //==============================================
    //メイン
    public static void main(String[] args) {
        AloneRunner player = new AloneRunner(); //選手を一人分作
        String teamname = "Lv03Right";
        player.initialize(23, teamname, "localhost", 6000);

    }
}
