package user;

import java.sql.*;
import java.util.*;

import database.Connect;

public class UserDAO {
	
	public UserDAO() {
	}
	
	public int loginPermission(String userID, String userPassword) { // 어떤 계정에 대한 실제로 로그인을 시도하는 함수, 인자값으로 ID와 Password를 받아 login을 판단함.
		Connection conn=Connect.connect();
		PreparedStatement pstmt=Connect.pstmt;
		ResultSet rs=Connect.rs;
		String SQL = "SELECT userPassword FROM user WHERE userID = ?"; // 실제로 DB에 입력될 명령어를 SQL 문장으로 만듬.
		try {
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1,  userID);
			rs = pstmt.executeQuery(); // 어떠한 결과를 받아오는 ResultSet 타입의 rs 변수에 쿼리문을 실행한 결과를 넣어줌 
			if (rs.next()) {
				if (rs.getString(1).contentEquals(userPassword)) {
					rs.close();
					pstmt.close();
					conn.close();
					return 1; // 로그인 성공
				}
				else {
					return 0; // 비밀번호 불일치
				}
			}
			
			return -1; // 아이디가 없음
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
	        if (rs != null) try { rs.close(); } catch(SQLException ex) {}
	        if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
	        if (conn != null) try { conn.close(); } catch(SQLException ex) {}
	    }
		return -2; // DB 오류 
	}
	
	/* searchId */
	public List<UserDTO> getList(String id){

		List<UserDTO> lists = new ArrayList<UserDTO>();

		Connection conn = Connect.connect();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql;

		try{

			sql = "select userId,userPassword,userName";
			sql+= "from user where id=?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();

			while(rs.next()){
				UserDTO dto = new UserDTO();

				dto.setUserId(rs.getString("userId"));
				dto.setUserPassword(rs.getString("userPassword"));
				dto.setUserName(rs.getString("userName"));

				lists.add(dto);
			}

			rs.close();
			pstmt.close();
		}catch (Exception e) {
			System.out.println(e.toString());
		}
		return lists;

	}

}
