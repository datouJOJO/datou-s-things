

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.Scanner;

public class connect {
	public static void main(String []args) throws IOException {
		Scanner sc = new Scanner(System.in);
		System.out.println("������˿ں�");
		int port = 0;
		port = sc.nextInt();
		//System.out.println("�˿ں���"+port);
		//System.out.println("������url");
		Scanner test1 = new Scanner(System.in);
		String url = test1.nextLine();
		doGet(port,url);
	}
	
	public static void doGet(int port,String url) throws UnknownHostException, IOException {
		String[] att = url.split("/");
		URL myurl = new URL(url);
		Socket socket = new Socket(myurl.getHost(),port);//���������Ͷ˿�
		System.out.println("�������ӳɹ���" + socket.isConnected());// ���������Ƿ�ɹ�
		OutputStream outputStream = socket.getOutputStream();//��socket�л�ȡoutputStream����������д����
		StringBuffer add = new StringBuffer();
		add.append("GET /"+att[3]+" HTTP/1.1\r\n");
		add.append("Host: "+ "127.0.0.1"+ "\r\n");
		add.append("Connection: keep-alive\r\n");
		add.append("User-Agent: "+"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.106 Safari/537.36\r\n");
		add.append("Accept: text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9\r\n");
		add.append("cookie: JSESSIONID=C69FFE9929D411ECE081762E21048F3E");
		add.append("\r\n");//һ��Ҫ�ӻ��У�����ҳ���ϵĻ��У�
		System.out.println("get�����ǣ�" + add.toString());
		outputStream.write(add.toString().getBytes());
		outputStream.flush();
		InputStream inputStream = socket.getInputStream();//������������շ������˷��ص�����
		if(inputStream==null) {
			System.out.print("δ����");
		}
		InputStreamReader inputStreamReader = new InputStreamReader(inputStream,"utf-8");
		BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
		String line = null;
		FileWriter fileWriter = new FileWriter("D:/"+att[3]+".txt");//�����ı��ļ�
		while ((line = bufferedReader.readLine()) != null) {
			fileWriter.write(line+"\r\n");
		}
		fileWriter.flush();
		fileWriter.close();

		//�ر�
		bufferedReader.close();
		inputStreamReader.close();
		inputStream.close();
		outputStream.close();
		socket.close();
		System.in.read();
	}
	
	public void doPost() throws IOException{
		
	}
}
