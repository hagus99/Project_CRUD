package chatting;

import java.io.*;
import java.net.*;
import java.util.*;

class EchoThread extends Thread{
	//모든 클라이언트와 연결된 Socket객체를 담은 객체 배열
	private ArrayList<Socket> list;
	
	//클라이언트와 연결된 Socket 객체
	private Socket sock;
	private String name; //대화명
	
	public EchoThread(ArrayList<Socket>list, Socket sock){
		this.list=list;
		this.sock=sock;
	}
	
	public void run() {
		try {
			//소켓을 통해 데이터를 읽기위한 스트림 얻어오기
			InputStream is = sock.getInputStream();
			
			//1바이트처리스트림을 2바이트처리스트림으로 변환하기
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			
			//맨처음에 전송된 문자열은 무조건 대화명이 됨
			name = br.readLine();
			
			//다른 클라이언트들에게 입장 알리기
			sendMsg("["+name+"] 님이 입장하셨습니다.");
			
			while(true) {
				//클라이언트가 보낸 메시지 읽어오기
				String msg = br.readLine();
				
				if(msg==null) {//상대방이 종료한 경우
					sendMsg("["+name+"] 님이 퇴장하셨습니다.");
					
					//ArrayList에서 클라이언트와 연결된 Socket제거하기
					list.remove(sock);
					
					//스트림 닫기
					br.close();
					
					//소켓닫기
					sock.close();
					break;
				}
				
				//전달받은메시지를 다른 클라이언트에 보냄
				sendMsg("["+name+"]"+msg);
			}
		}catch(IOException ie) {
			System.out.println(ie.getMessage());
		}
	}
	public void sendMsg(String msg) {
		for(int i =0; i<list.size();i++) {
			//ArrayList에 담긴 Socket객체 꺼내오기
			Socket soc = list.get(i);
			
			//데이터를 수신받은 Socket객체가 아닌 경우
			if(soc!=sock) {
				try {
					//클라이언트에 데이터를 보내기 위한 출력스트림 얻어오기
					PrintWriter pw = new PrintWriter(soc.getOutputStream());
					
					//연결된 클라이언트에 데이터 보내기
					pw.println(msg);
					pw.flush();
				}catch(IOException ie) {
					System.out.println(ie.getMessage());
				}
			}
		}
	}
}

public class TestServer{
	public static void main(String[] args) {
		ServerSocket server = null;
		
		//클라이언트와 연결된 Socket객체를 배열처럼 담기 위한 객체 생성
		ArrayList<Socket> list = new ArrayList<Socket>();
		
		try {
			server = new ServerSocket(3001);
			
			while(true) {
				System.out.println("클라이언트 접속 대기중..");
				
				//클라이언트 접속 허용하기
				Socket sock = server.accept();
				//클라이언트의 아이피주소에 대한 정보를 갖는 객체 얻어오기
				InetAddress ia = sock.getInetAddress();
				//클라이언트의 아이피주소 출력하기
				System.out.println("ip주소["+ia.getHostAddress()+"]가 접속함");
				//클라이언트와 연결된 Socket객체를 list에 담기
				list.add(sock);
				
				//데이터를 수신하는 스레드 실행하기
				new EchoThread(list,sock).start();
			}
		}catch(IOException ie) {
			System.out.println(ie.getMessage());
		}
	}
}