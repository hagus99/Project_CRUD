package user;

import java.util.*;

public class User {
	public static String id;
	
	public static int login() {
		UserDAO userDAO = new UserDAO();
		Scanner scan = new Scanner(System.in);
		String password;
		
		System.out.println("***************[로그인]***************");
		System.out.print("아이디 : ");
		id=scan.nextLine();
		System.out.print("비밀번호 : ");
		password=scan.nextLine();
		int result = userDAO.loginPermission(id,password);
		
		if (result == 1){ // 로그인 성공
			System.out.println("Welcome "+id);
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
	
	public static void join() {
		System.out.println("***************[회원가입]***************");
	}
	
	
	

}
