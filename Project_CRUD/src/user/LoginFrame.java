package user;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

	
public class LoginFrame extends JFrame implements ActionListener {
	Container container = getContentPane();
    JLabel userLabel = new JLabel("ID");
    JLabel passwordLabel = new JLabel("PASSWORD");
    JTextField userTextField = new JTextField();
    JPasswordField passwordField = new JPasswordField();
    JButton loginButton = new JButton("LOGIN");
    JButton joinButton = new JButton("JOIN");
    JCheckBox showPassword = new JCheckBox("Show Password");
    private Dimension frameSize, screenSize;
    public String userText;
    public String pwdText;
    
    public int result;
    public int login;
    private UserDAO userDAO=new UserDAO();
 
    public LoginFrame() {
        setLayoutManager();
        setLocationAndSize();
        addComponentsToContainer();
        addActionEvent();
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
        userLabel.setBounds(50, 150, 100, 30);
        passwordLabel.setBounds(50, 220, 100, 30);
        userTextField.setBounds(150, 150, 150, 30);
        passwordField.setBounds(150, 220, 150, 30);
        showPassword.setBounds(150, 250, 150, 30);
        loginButton.setBounds(50, 300, 100, 30);
        joinButton.setBounds(200, 300, 100, 30);
    }
 
    public void addComponentsToContainer() {
        container.add(userLabel);
        container.add(passwordLabel);
        container.add(userTextField);
        container.add(passwordField);
        container.add(showPassword);
        container.add(loginButton);
        container.add(joinButton);
    }
 
    public void addActionEvent() {
        loginButton.addActionListener(this);
        joinButton.addActionListener(this);
        showPassword.addActionListener(this);
    }
 
 
    @Override
    public void actionPerformed(ActionEvent e) {
        //LOGIN button
        if (e.getSource() == loginButton) {
            userText = userTextField.getText();
            pwdText = passwordField.getText();
            login = userDAO.loginCheck(userText,pwdText);
            if (login==1) {
                //result=JOptionPane.showMessageDialog(this, "WELCOME "+userText);
//                if(result==JOptionPane.OK_OPTION) {
//                	result=1;
//                	setVisible(false);
//                }
            	JOptionPane.showMessageDialog(this, "WELCOME "+userText);
            	User.id=userText;
            	new MenuFrame();
            	setVisible(false);
            } else if(login==0){
                JOptionPane.showMessageDialog(this, "Invalid Password");
                result=0;
            }else if(login==-1){
                JOptionPane.showMessageDialog(this, "Invalid Id");
                result=-1;
            }else if(login==-2){
                JOptionPane.showMessageDialog(this, "DataBase Error");
                result=-2;
            }
        }
        //Coding Part of RESET button
        if (e.getSource() == joinButton) {
            userTextField.setText("");
            passwordField.setText("");
        }
       //Coding Part of showPassword JCheckBox
        if (e.getSource() == showPassword) {
            if (showPassword.isSelected()) {
                passwordField.setEchoChar((char) 0);
            } else {
                passwordField.setEchoChar('*');
            }
        } 
    }
}
