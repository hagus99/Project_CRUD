package chatting;

import java.io.*;
import java.net.*;

import user.User;

//서버가 보내온 데이터를 읽어와 화면에 출력하는 스레드
class ReceiveThread extends Thread{
	private Socket sock;
	public ReceiveThread(Socket sock) {
		this.sock=sock;
	}
	
	public void run() {
		try {
			//서버가 보내온 데이터를 읽어오기 위한 스트림 객체
			BufferedReader br=
					new BufferedReader(new InputStreamReader(sock.getInputStream()));
			
			while(true) {
				//서버가 보내온 문자열 읽어오기
				String msg = br.readLine();
				if(msg==null) {
					System.out.println("접속이 종료되어요");
					break;
				}
				System.out.println(msg);
			}
			br.close();
			sock.close();
		}catch(IOException ie) {
			System.out.println(ie.getMessage());
		}
	}
}

//서버에 문자열을 보내는 스레드
class SendThread extends Thread{
	private Socket sock;
	public SendThread(Socket sock) {
		this.sock=sock;
	}
	public void run() {
		//키보드로부터 입력받기 위한 스트림객체 생성
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		try {
			//서버에 데이터를 보내기 위한 출력스트림객체 생성하기
			PrintWriter pw = new PrintWriter(sock.getOutputStream());
			System.out.println("대화명 입력");
			
			String ss = br.readLine();
			
			//대화명 서버에 보내기
			pw.println(ss);
			//pw.println(User.id);
			pw.flush();
			
			while(true) {
				//키보드로 문자열 받기
				String msg = br.readLine();
				if(msg.equals("exit")) break;
				
				//서버에 문자열 보내기
				pw.println(msg);
				pw.flush();
			}
			pw.close();
			sock.close();
		}catch(IOException ie) {
			System.out.println(ie.getMessage());
		}
	}
}

public class TestClient {

	public static void main(String[] args){
		try {
			Socket sock = new Socket("192.168.0.30",3001);
			System.out.println("서버접속성공!");
			
			new ReceiveThread(sock).start();
			
			new SendThread(sock).start();
		}catch(UnknownHostException ue) {
			System.out.println(ue.getMessage());
		}catch(IOException ie) {
			System.out.println(ie.getMessage());
		}
	}

	/*
	public static void TestClient(){
		try {
			Socket sock = new Socket("192.168.0.30",3001);
			System.out.println("서버접속성공!");
			
			//서버에서 보내온 데이터를 수신하는 스레드 실행
			new ReceiveThread(sock).start();
			
			//서버에 데이터를 보내는 스레드 실행
			new SendThread(sock).start();
		}catch(UnknownHostException ue) {
			System.out.println(ue.getMessage());
		}catch(IOException ie) {
			System.out.println(ie.getMessage());
		}
	}
	*/
}
