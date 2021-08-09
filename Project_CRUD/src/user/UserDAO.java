package user;

import java.sql.*;
import java.util.*;

import database.Connect;

public class UserDAO {
	
	public UserDAO() {
	}
	/* 어떤 계정에 대한 실제로 로그인을 시도하는 함수, 인자값으로 ID와 Password를 받아 login을 판단함.*/
	public int loginCheck(String userID, String userPassword) {
		Connection conn=Connect.connect();
		PreparedStatement pstmt=Connect.pstmt;
		ResultSet rs=Connect.rs;
		// 실제로 DB에 입력될 명령어를 SQL 문장으로 만듦
		String SQL = "SELECT userPassword FROM user WHERE userID = ?";
		try {
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1,  userID);
			// 결과를 받아오는 ResultSet 타입의 rs 변수에 쿼리문을 실행한 결과를 넣어줌
			rs = pstmt.executeQuery(); 
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
	
	public int insertData(UserDTO dto){

		int result = 0;

		Connection conn = Connect.connect();
		PreparedStatement pstmt = null;
		String sql;

		try {

			sql = "insert into user(userId,userPassword,userName)";
			sql+= "values (?,?,?)";

			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, dto.getUserId());
			pstmt.setString(2, dto.getUserPassword());
			pstmt.setString(3, dto.getUserName());	

			result = pstmt.executeUpdate();
			pstmt.close();
			conn.close();

		} catch (Exception e) {

			System.out.println(e.toString());

		}

		return result;
	}
	
	/* selectAll */
	public List<UserDTO> getList() {
		List<UserDTO> lists = new ArrayList<UserDTO>();
		Connection conn = Connect.connect();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql;

		try {
	
			sql = "select userId,userPassword,userName"
					+"from user";
			pstmt = conn.prepareStatement(sql);
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
			conn.close();
	
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return lists;
	}
	
	/* update */
	public int updateData(UserDTO dto){
		int result = 0;
		Connection conn = Connect.connect();
		PreparedStatement pstmt = null;
		String sql;
		try {
			//password 변경
			sql = "update user set userPassword=?"
					+ "where userId=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getUserPassword());
			pstmt.setString(2, dto.getUserId());
			result = pstmt.executeUpdate();

			pstmt.close();
			conn.close();
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return result;
	}
	
	/* delete */

	public int deleteData(String id, String pw){
		int result = 0;
		Connection conn = Connect.connect();
		PreparedStatement pstmt = null;
		String sql;
		
		try {
			sql = "delete from user where userId=? and userPassword=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, pw);
			result = pstmt.executeUpdate();
			pstmt.close();
			conn.close();

		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return result;	
	}

}
