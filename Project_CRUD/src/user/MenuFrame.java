package user;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

import Frame.FriendsFrame;
import chatting.MainChat;

public class MenuFrame extends JFrame implements ActionListener{
	Container container = getContentPane();
    JLabel friendsLabel = new JLabel("친구관리");
    JLabel chattingLabel = new JLabel("채팅방 입장");
    JLabel myPageLabel = new JLabel("마이페이지");
    //JLabel passwordLabel = new JLabel("PASSWORD");
    //JTextField userTextField = new JTextField();
    //JPasswordField passwordField = new JPasswordField();
    JButton friendListButton = new JButton("친구 목록");
    JButton friendAddButton = new JButton("친구추가");
    JButton friendDeleteButton = new JButton("친구삭제");
    JButton chattingButton = new JButton("채팅");
    JButton passwordEditButton = new JButton("비밀번호 변경");
    JButton accountDeleteButton = new JButton("탈퇴");
    JButton logOutButton = new JButton("로그아웃");
    //JCheckBox showPassword = new JCheckBox("Show Password");
    private Dimension frameSize, screenSize;
    public String userText;
    public String pwdText;
    
    public int result;
    public int login;
    
    public MenuFrame(){
    	setLayoutManager();
        setLocationAndSize();
        addComponentsToContainer();
        addActionEvent();
        
        setTitle("Menu");
        setVisible(true);
        setBounds(10,10,370,600);
        screenSizeLocation();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
    }
    
    public void setLayoutManager() {
        container.setLayout(null);
    }
    
 // 화면 중앙에 Frame 위치
    public void screenSizeLocation() {
    frameSize = getSize(); // 컴포넌트 크기
    screenSize = Toolkit.getDefaultToolkit().getScreenSize(); // 모니터 화면의 크기 구하기
    // (모니터화면 가로 - 프레임화면 가로) / 2, (모니터화면 세로 - 프레임화면 세로) / 2
    setLocation((screenSize.width - frameSize.width)/2, (screenSize.height - frameSize.height)/2);
    }
 
    public void setLocationAndSize() {
        //userLabel.setBounds(50, 150, 100, 30);
        //passwordLabel.setBounds(50, 220, 100, 30);
        //userTextField.setBounds(150, 150, 150, 30);
        //passwordField.setBounds(150, 220, 150, 30);
        //showPassword.setBounds(150, 250, 150, 30);
    	friendsLabel.setBounds(170, 100, 100, 30);
    	friendListButton.setBounds(40, 130, 100, 30);
    	friendAddButton.setBounds(140, 130, 100, 30);
    	friendDeleteButton.setBounds(240, 130, 100, 30);
    	
        chattingButton.setBounds(40,210, 300, 30);
        
        myPageLabel.setBounds(160, 270, 100, 30);
        passwordEditButton.setBounds(40,300, 100, 30);
        accountDeleteButton.setBounds(140,300, 100, 30);
        logOutButton.setBounds(240,300, 100, 30);
    }
 
    public void addComponentsToContainer() {
        //container.add(userLabel);
        //container.add(passwordLabel);
        //container.add(userTextField);
        //container.add(passwordField);
        //container.add(showPassword);
    	container.add(friendsLabel);
    	container.add(friendListButton);
    	container.add(friendDeleteButton);
        container.add(friendAddButton);
        container.add(chattingButton);
        container.add(myPageLabel);
        container.add(passwordEditButton);
        container.add(logOutButton);
        container.add(accountDeleteButton);
    }
 
    public void addActionEvent() {
    	friendListButton.addActionListener(this);
    	friendDeleteButton.addActionListener(this);
    	friendAddButton.addActionListener(this);
    	accountDeleteButton.addActionListener(this);
        chattingButton.addActionListener(this);
        passwordEditButton.addActionListener(this);
        logOutButton.addActionListener(this);
        //showPassword.addActionListener(this);
    }
    
	@Override
	public void actionPerformed(ActionEvent e) {
		FriendsFrame friendsFrame = new FriendsFrame();
		if (e.getSource() == friendListButton) {
			try {
				friendsFrame.friendsList();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
        	setVisible(false);
		}
		
		if(e.getSource() == chattingButton) {
			new MainChat();
		}

		
	}
	
  
}
