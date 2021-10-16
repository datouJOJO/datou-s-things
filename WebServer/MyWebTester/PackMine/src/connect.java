

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
		System.out.println("请输入端口号");
		int port = 0;
		port = sc.nextInt();
		//System.out.println("端口号是"+port);
		//System.out.println("请输入url");
		Scanner test1 = new Scanner(System.in);
		String url = test1.nextLine();
		doGet(port,url);
	}
	
	public static void doGet(int port,String url) throws UnknownHostException, IOException {
		String[] att = url.split("/");
		URL myurl = new URL(url);
		Socket socket = new Socket(myurl.getHost(),port);//设置域名和端口
		System.out.println("网络连接成功：" + socket.isConnected());// 检验连接是否成功
		OutputStream outputStream = socket.getOutputStream();//从socket中获取outputStream后，再往里面写数据
		StringBuffer add = new StringBuffer();
		add.append("GET /"+att[3]+" HTTP/1.1\r\n");
		add.append("Host: "+ "127.0.0.1"+ "\r\n");
		add.append("Connection: keep-alive\r\n");
		add.append("User-Agent: "+"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.106 Safari/537.36\r\n");
		add.append("Accept: text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9\r\n");
		add.append("cookie: JSESSIONID=C69FFE9929D411ECE081762E21048F3E");
		add.append("\r\n");//一定要加换行（这是页面上的换行）
		System.out.println("get请求是：" + add.toString());
		outputStream.write(add.toString().getBytes());
		outputStream.flush();
		InputStream inputStream = socket.getInputStream();//输出流用来接收服务器端返回的数据
		if(inputStream==null) {
			System.out.print("未连接");
		}
		InputStreamReader inputStreamReader = new InputStreamReader(inputStream,"utf-8");
		BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
		String line = null;
		FileWriter fileWriter = new FileWriter("D:/"+att[3]+".txt");//创建文本文件
		while ((line = bufferedReader.readLine()) != null) {
			fileWriter.write(line+"\r\n");
		}
		fileWriter.flush();
		fileWriter.close();

		//关闭
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
