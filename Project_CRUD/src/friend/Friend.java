package friend;

import java.sql.SQLException;
import java.util.*;

import user.User;
import user.UserDAO;

public class Friend {
	public static FriendDAO friend = new FriendDAO();
	public static User login;
	public static Scanner scan = new Scanner(System.in);
	
	public static void printFriend() throws SQLException {
		List<String> friends = friend.readFriends();
		System.out.println(friends);
	}
	
	public static void addFriend() throws SQLException {
		System.out.print("추가할 친구 아이디: ");
		String friendId = scan.nextLine();
		System.out.print("추가할 친구 이름: ");
		String friendName = scan.nextLine();
		friend.createFriends(friendName, friendId,login.id);
	}
}
