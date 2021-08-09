package chatting;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Scanner;
 
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import user.User;
 
public class ClientGui extends JFrame implements ActionListener {
 
    private static final long serialVersionUID = 1L;
    private JTextArea jta = new JTextArea(40, 25);
    private JTextField jtf = new JTextField(25);
    private Client client = new Client();
    private static String userId=User.id;
 
    public ClientGui() {
 
        add(jta, BorderLayout.CENTER);
        add(jtf, BorderLayout.SOUTH);
        jtf.addActionListener(this);
 
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        setBounds(800, 100, 400, 600);
        setTitle("채팅방");
 
        client.setGui(this);
        client.setuserId(userId);
        client.connet();
    }
 
//    public static void main(String[] args) {
//        Scanner scanner = new Scanner(System.in);
//        System.out.print("당신의 닉네임부터 설정하세요 : ");
//        userId = scanner.nextLine();
//        scanner.close();
// 
//        new ClientGui();
// 
//    }
 
    @Override
    //메세지 보내는 부분
    public void actionPerformed(ActionEvent e) {
        String msg = User.id + ":" + jtf.getText() + "\n";
        client.sendMessage(msg);
        jtf.setText("");
    }
 
    public void appendMsg(String msg) {
        jta.append(msg);
    }
 
}
