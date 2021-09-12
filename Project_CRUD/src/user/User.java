package user;

import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class User{
	private Scanner scan = new Scanner(System.in);
	private UserDAO userDAO = new UserDAO();
	public static String id;

	public int login() {
		LoginFrame frame= new LoginFrame();
        frame.setTitle("Login");
        frame.setVisible(true);
        frame.setBounds(10,10,370,600);
        frame.screenSizeLocation();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        
		String password;
		
		System.out.println("---------------------------------");
		System.out.println("	    [Login]");
		System.out.println("---------------------------------");
		System.out.print("아이디 : ");
		id=scan.next();
		System.out.print("비밀번호 : ");
		password=scan.next();
		int result = userDAO.loginCheck(id,password);
		
		if (result == 1){ // 로그인 성공
			System.out.println("\n** WELCOME "+id+" **\n");
		}
		else if (result == 0){ 
			System.out.println("비밀번호가 틀립니다.");
		}
		else if (result == -1){ 
			System.out.println("존재하지 않는 아이디 입니다.");
		}
		else if (result == -2){ 
			System.out.println("데이터 베이스에 오류가 발생하였습니다.");
		}
		
		return result;
	}
	
	/* 회원가입 */
	public void add(){
		//String pw2=null;
		boolean id=true;
		boolean pw=true;
		boolean name=true;
		System.out.println("                 회원가입");
		System.out.println("-------------------------------------");
		try {
			UserDTO dto = new UserDTO();
			do{
				try{
					System.out.print("아이디:");
					dto.setUserId(scan.next());
					//ne.idFormat(dto.getId);
					id=false;

					}catch (Exception e) {
						System.out.println(e.toString());
					}
				} while(id);
			
				do{
					try{
						System.out.print("비밀번호:");
						dto.setUserPassword(scan.next());
//						System.out.print("비밀번호 확인:");
//						pw2 = scan.next();
//						ne.pwCheck(dto.getPw(), pw2);
						pw=false;
					}catch (Exception e) {
						System.out.println(e.toString());
					}
				} while(pw);
				do{
					try{
						System.out.print("이름:");
						dto.setUserName(scan.next());
						//ne.nameCheck(dto.getUserName());
						name=false;
					}catch (Exception e) {
						System.out.println(e.toString());
					}
				}while(name);

				int result = userDAO.insertData(dto);

				if(result!=0){

					System.out.println();
					System.out.println("**성공적으로 가입이 되었습니다!**");
					System.out.println();
					System.out.println("-----------[회원가입 확인]-----------");
					System.out.println(dto.toString());	
				}
				else
					System.out.println("회원가입에 실패했습니다");		

			} catch (Exception e) {
				System.out.println(e.toString());
			}
		}
	
	/* 회원 전체출력 */

	public void selectAll() {
		List<UserDTO> lists = userDAO.getList();
		Iterator<UserDTO> it = lists.iterator();

		int i=1;
		while(it.hasNext()){
			UserDTO dto = it.next();
			System.out.println("[회원 "+i+"]");
			System.out.println(dto.toString());
			i++;
		}
	}
	
	/* 비밀번호 변경 */
	public void update() {
		try {
			UserDTO dto = new UserDTO();
			System.out.println("현재 아이디:"+User.id);
			dto.setUserId(User.id);
			System.out.print("수정할 비밀번호:");
			dto.setUserPassword(scan.next());

			int result = userDAO.updateData(dto);

			if(result!=0)
				System.out.println("회원정보가 수정되었습니다");
			else
				System.out.println("회원정보수정에 실패했습니다");
		} catch (Exception e) {
			System.out.println(e.toString());
		}
	}
	
	/* 탈퇴 */
	public void delete() {
		try {
			String id, pw;
			System.out.print("탈퇴 할 아이디:");
			id = scan.next();
			System.out.print("비밀번호 확인:");
			pw = scan.next();
			int result = userDAO.deleteData(id,pw);
			if(result!=0)
				System.out.println("성공적으로 탈퇴하였습니다.\n다음에 다시 가입해주세요!");
			else
				System.out.println("탈퇴에 실패했습니다.");
		} catch (Exception e) {
			System.out.println(e.toString());
		}
	}

}
