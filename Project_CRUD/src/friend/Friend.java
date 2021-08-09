package friend;

import java.sql.SQLException;
import java.util.*;

import user.User;
import user.UserDAO;

public class Friend {
	public static FriendDAO friend = new FriendDAO();
	public static User login;
	public static Scanner scan = new Scanner(System.in);
	
	public void printFriend() throws SQLException {
		List<String> friends = friend.readFriends();
		int count=1;
		System.out.println("\n"+User.id+"님의 친구 목록 입니다.");
		for(String friend:friends) {
			System.out.println(count+"."+friend);
			count++;
		}
		System.out.println();
	}
	
	/* 친구추가 */
	public void addFriend() throws SQLException {
		System.out.print("추가할 친구 아이디: ");
		String friendId = scan.nextLine();
		System.out.print("추가할 친구 이름: ");
		String friendName = scan.nextLine();
		friend.addFriend(friendName, friendId,login.id);
	}
	
	/* 친구삭제 */
	public void delete() {
		try {
			String id;
			System.out.print("삭제 할 친구 아이디:");
			id = scan.next();
			
			int result = friend.deleteData(id);
			if(result!=0)
				System.out.println("성공적으로 삭제하였습니다.\n");
			else
				System.out.println("삭제 실패했습니다.");
		} catch (Exception e) {
			System.out.println(e.toString());
		}
	}
}
