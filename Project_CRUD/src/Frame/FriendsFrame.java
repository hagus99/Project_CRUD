package Frame;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;

import friend.Friend;
import friend.FriendDAO;
import user.User;

public class FriendsFrame extends JFrame implements ActionListener{
	private Dimension frameSize, screenSize;
	Container container = getContentPane();
	public static FriendDAO friend = new FriendDAO();
	
	public void friendsList() throws SQLException {
		List<String> friends = friend.readFriends();
		String friendsList="<html>"+User.id+"님의 친구 목록 입니다.<br/><br/>";
		
		int count=1;
		System.out.println("\n"+User.id+"님의 친구 목록 입니다.");
		
		for(String str:friends) {
			friendsList+="&nbsp;&nbsp;&nbsp;&nbsp;"+count+". "+str+"<br/>";
			count++;
		}
		friendsList+="</html>";
		JLabel friendsListArea = new JLabel(friendsList);
		
		container.setLayout(null);
		friendsListArea.setBounds(100, 75, 200, 200);
		container.add(friendsListArea);
	
		setTitle("친구목록");
        setVisible(true);
        setBounds(10,10,370,600);
        screenSizeLocation();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
	}
    
 // 화면 중앙에 Frame 위치
    public void screenSizeLocation() {
    frameSize = getSize(); // 컴포넌트 크기
    screenSize = Toolkit.getDefaultToolkit().getScreenSize(); // 모니터 화면의 크기 구하기
    // (모니터화면 가로 - 프레임화면 가로) / 2, (모니터화면 세로 - 프레임화면 세로) / 2
    setLocation((screenSize.width - frameSize.width)/2, (screenSize.height - frameSize.height)/2);
    }
 
    public void setLocationAndSize() {
    	
    }
 
    public void addComponentsToContainer() {
        //container.add(userLabel);
        
    }
 
    public void addActionEvent() {
        //showPassword.addActionListener(this);
    }

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}
