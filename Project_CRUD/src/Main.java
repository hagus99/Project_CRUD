import java.sql.*;
import java.util.*;

import javax.swing.JFrame;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;
import javax.swing.*;
import javax.swing.JProgressBar;

import chatting.Client;
import chatting.ClientGui;
import chatting.Room;
import chatting.TestClient;
import database.Connect;
import user.User;
import friend.Friend;

public class Main{
	public static void main(String[] args)throws Exception{
		User user = new User();
		Friend friend = new Friend();
		Scanner scan = new Scanner(System.in);
		
		int menu=0;
		int login=0;
		
		while(login!=1) {
			login=user.login();	
		}
		
		while(login==1 && menu!=6) {
			System.out.println("---------------------------------");
			System.out.println("	    [menu]");
			System.out.println("---------------------------------");
			System.out.println("\t1. 친구관리");
			System.out.println("\t  1.1. 친구목록 조회");
			System.out.println("\t  1.2. 친구추가");
			System.out.println("\t  1.3. 친구삭제");
			System.out.println("\t2. 채팅방 입장");
			System.out.println("\t3. 마이페이지");
			System.out.println("\t  3.1. 비밀번호 변경");
			System.out.println("\t  3.2. 탈퇴");
			System.out.println("\t4. 로그아웃");
			System.out.print("메뉴 입력 : ");
			menu=scan.nextInt();
			if(menu==1) {
				//친구목록 조회
				System.out.println("1. 친구목록 조회 2. 친구추가 3. 친구삭제");
				menu=scan.nextInt();
				if(menu==1)
					friend.printFriend();
				else if(menu==2)
					friend.addFriend();
				else if(menu==3)
					friend.delete();
			}
			else if(menu==2) {
				//채팅방 입장
		        new ClientGui();
			}
			
			else if(menu==3) {
				// 마이페이지
				System.out.println("1. 비밀번호 변경 2.탈퇴");
				menu=scan.nextInt();
				if(menu==1)
					user.update();
				else if(menu==2)
					user.delete();
			}
			else if(menu==4) {
				//로그아웃
				System.out.println("BYE~");
				break;
			}				
		}
	}
}