package MyWebServer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.print.attribute.HashAttributeSet;

public class MyRequest{
	private String requestInfo;		//Э����Ϣ
	private String method; 			//����ʽ
	private String url;				//�����url
	InputStreamReader reader = null;//ת����
	BufferedReader bufferedReader = null;//�ַ�������
	
	public String getMethod() {
		return method;
	}

	public String getUrl() {
		return url;
	}

	public MyRequest(InputStream in) throws IOException {
		super();
		//�õ�������
		byte[]data = new byte[1024*1024];
		int len;		//��������Ϣ����
		try {
			len = in.read(data);
			if(len!=-1) {
			this.requestInfo = new String(data,0,len);
			System.out.println(this.requestInfo);
			}
			else {
				return;
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		run();
	}
	
	public void run() {
		// TODO Auto-generated method stub
		try {
			//��ȡ����ʽ
			int index = this.requestInfo.indexOf(" ");
			this.method = this.requestInfo.substring(0,index);
			System.out.println(method);
			//��ȡurl
			int startIndex = this.requestInfo.indexOf("/")+1;
			int endIndex = this.requestInfo.indexOf("HTTP/")-1;
			this.url = this.requestInfo.substring(startIndex,endIndex);
			System.out.println(this.url);
				String temp = this.requestInfo.substring(this.requestInfo.lastIndexOf("Host:"),this.requestInfo.indexOf("Connection:"));
				String[] t = temp.split(" ");
				String host = t[1];
				System.out.println("�����豸----->"+host);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	//������ �ȶ��ó���
	public String getParameter(String string) throws IOException {
		// TODO Auto-generated method stub
		String result = "";
		System.out.println(requestInfo);
		if(string.equals("weight")) {
			int start = requestInfo.lastIndexOf(string)+string.length()+1;
			int end = requestInfo.indexOf("&");
			if(start!=-1&&end!=-1) {
				result = requestInfo.substring(start,end);
			}
		}
		else if(string.equals("legs")) {
			int start = requestInfo.lastIndexOf(string)+string.length()+1;
			if(start!=-1) {
				result = requestInfo.substring(requestInfo.lastIndexOf(string)+string.length()+1);
			}
		}
		else if(string.equals("username")) {
			int start = requestInfo.indexOf("username")+1+string.length();
			int end = requestInfo.lastIndexOf("&");
			if(start!=-1&&end!=-1) {
				result = requestInfo.substring(start,end);
			}
		}
		else if(string.equals("password")) {
			int start = requestInfo.indexOf("password")+1+string.length();
			if(start!=-1) {
				result = requestInfo.substring(start);
			}
		}
		System.out.println(string+"---->"+result);
		return result;
	}
	//��������
	private Map<String,String>map = new HashMap<String, String>();
	public void setAttribute(String string, String pet) {
		// TODO Auto-generated method stub
		map.put(string, pet);
	}
	
	public char[] getAttribute(String string) {
		// TODO Auto-generated method stub
		String temp = map.get(string);
		char att[] = {'n','u','l','l'};
		if(temp!=null) {
		att = temp.toCharArray();
		return att;
		}
		return att;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public MyRequest getSession() {
		// TODO Auto-generated method stub
		return this;
	}

	public RequestDispatcher getRequestDispatcher(String string) {
		RequestDispatcher rd = new RequestDispatcher(string);
		return rd;
	}
	
}
