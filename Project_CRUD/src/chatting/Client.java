package chatting;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

import user.User;
 
public class Client {
 
    private Socket socket;
    private DataInputStream in;
    private DataOutputStream out;
    private ClientGui gui;
    private String msg;
    private String userId=User.id;
    private int room;
 
    public final void setGui(ClientGui gui) {
        this.gui = gui;
    }
 
    public void connet() {
        try {
            socket = new Socket("172.17.179.39", 7777);
            System.out.println("서버 연결됨.");

 
            out = new DataOutputStream(socket.getOutputStream());
            in = new DataInputStream(socket.getInputStream());
 
            // 접속하자마자 닉네임 전송하면. 서버가 이걸 닉네임으로 인식을 하고서 맵에 집어넣겠지요?
//            Scanner scanner = new Scanner(System.in);
//            System.out.print("당신의 닉네임부터 설정하세요 : ");
//            userId = scanner.nextLine();

        	out.writeUTF(userId);
        	System.out.println("클라이언트 : 메시지 전송완료");
            while (in != null) {
                msg = in.readUTF();
                gui.appendMsg(msg);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
 
//    public static void main(String[] args) {
//        Client clientBackground = new Client();
//        clientBackground.connet();
//    }
 
    public void sendMessage(String msg2) {
        try {
            out.writeUTF(msg2);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
 
    public void setuserId(String userId) {
        this.userId = userId;
    }
 
}
