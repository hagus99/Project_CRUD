package friend;

import java.sql.*;
import java.util.*;

import database.Connect;
import user.User;

public class FriendDAO {
	public FriendDAO() {
	}
	
	public List<String> readFriends() throws SQLException{
		Connection conn=Connect.connect();
		PreparedStatement pstmt=Connect.pstmt;
		ResultSet rs=Connect.rs;
		List<String> friends = new ArrayList<>();
		String query = "SELECT friendId FROM friend WHERE userId = ?";
		pstmt = conn.prepareStatement(query);
		pstmt.setString(1, User.id);
		rs=pstmt.executeQuery();
		while(rs.next()) {
			friends.add(rs.getString("friendId"));
		}
		rs.close();
		pstmt.close();
		conn.close();
		
		return friends;
	}
	
	
	public void addFriend(String friendName, String friendId, String userId) throws SQLException{
		Connection conn=Connect.connect();
		PreparedStatement pstmt=Connect.pstmt;
		ResultSet rs=Connect.rs;
		String sql = "INSERT INTO friend(friendName,friendId,userId) " +
                "VALUES ('"+friendName+"','"+friendId+"','"+userId+"')"; 
		try { 
	          pstmt = conn.prepareStatement(sql);
	          pstmt.executeUpdate();
	          pstmt.close();
	          conn.close();
	          
        } catch ( Exception e ) {
          System.err.println( e.getClass().getName() + ": " + e.getMessage() );
          System.exit(0);
        }
		System.out.println("Records created successfully");
	}
	
	public int deleteData(String friendId){
		int result = 0;
		Connection conn=Connect.connect();
		PreparedStatement pstmt = null;
		String sql;
		try {
			sql = "delete from friend where friendId=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, friendId);
			result = pstmt.executeUpdate();
			pstmt.close();
			conn.close();

		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return result;	
	}
	
	
}
