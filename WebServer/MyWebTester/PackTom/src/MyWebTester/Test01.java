package MyWebTester;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class Test01 {
	public static void main(String []args) throws IOException {
		Scanner sc = new Scanner(System.in);
		System.out.println("请输入端口号");
		int port = 0;
		port = sc.nextInt();
		//System.out.println("端口号是"+port);
		//System.out.println("请输入url");
		Scanner test1 = new Scanner(System.in);
		String url = test1.nextLine();
		doGet(url);
	}
	
	public static void doGet(String url) throws ClientProtocolException, IOException {
		CloseableHttpClient httpClient = HttpClients.createDefault();
		String[] att = url.split("/");
		HttpGet get = new HttpGet(url);
		HttpResponse response = httpClient.execute(get);
		int code = response.getStatusLine().getStatusCode();
		System.out.println(code);
		if(code==200) {
			HttpEntity entity = response.getEntity();
			String context = EntityUtils.toString(entity,"utf-8");
			FileWriter fileWriter = new FileWriter("D:/"+att[3]+"1.txt");//创建文本文件
			fileWriter.write(context);
			fileWriter.flush();
			fileWriter.close();
		}
		get.abort();
		httpClient.close();
		response.getEntity().getContent().close();
	}
}
