import java.sql.*;
import java.util.*;

import database.Connect;
import user.User;
import friend.Friend;

public class Chatting{
	public static void main(String[] args)throws Exception{
		Scanner scan = new Scanner(System.in);
		int menu=0;
		int answer=0;
		int login=0;
		int join=0;
		System.out.println("1. 로그인 2. 회원가입");
		answer = scan.nextInt();
		if(answer==1)
			login=User.login();
		else if(answer==2)
			
		while(login==1 && menu!=4) {
			System.out.println("***************[Menu]***************");
			System.out.println("\t1. 친구목록 조회");
			System.out.println("\t2. 친구추가");
			System.out.println("\t3. 채팅방 입장");
			System.out.println("\t4. 로그아웃");
			System.out.print("메뉴 입력 : ");
			menu=scan.nextInt();
			if(menu==1) {
				//친구목록 조회
				Friend.printFriend();
			}
			else if(menu==2) {
				//친구추가
				//유저 아이디에 있는지 확인
				Friend.addFriend();
			}
			else if(menu==4) {
				System.out.println("BYE~");
				break;
			}
				
		}
	}
}