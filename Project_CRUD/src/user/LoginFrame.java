package user;

import java.awt.Container;
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
                result=JOptionPane.showConfirmDialog(this, "WELCOME "+userText,"OK",
                		JOptionPane.OK_CANCEL_OPTION);
                if(result==JOptionPane.OK_OPTION) {
                	result=1;
                	setVisible(false);
                }
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
