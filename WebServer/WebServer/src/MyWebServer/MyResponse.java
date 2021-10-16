package MyWebServer;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

public class MyResponse{
	private OutputStream out;		//输出字节流
	private BufferedWriter bw;			//字符缓冲流
	private StringBuilder content;		//正文
	private StringBuilder headInfo;		//协议头（状态行 请求头 回车）
	private int len;					//正文的字节数
	File f;
	private final String BLANK =" ";		//空格
	private final String CRLF = "\r\n";		//回车
	
	private boolean isPic;				//判断是不是照片
	public MyResponse(OutputStream out) throws IOException {
		content = new StringBuilder();
		headInfo = new StringBuilder();
		len = 0;
		this.out = out;
		bw = new BufferedWriter(new OutputStreamWriter(this.out));
		isPic = false;
	}
	
	private void createHeadInfo(int code,String kind) throws IOException {
		headInfo.append("HTTP/1.1").append(BLANK);
		headInfo.append(code).append(BLANK);
		switch(code) {
		case 200:
			headInfo.append("OK").append(CRLF);
			break;
		case 404:
			headInfo.append("NOT FOUND").append(CRLF);
			break;
		}
		//响应头
		if(code==200) {
			if(kind.equals("html")||kind.equals("jsp")) {
				if(kind.equals("html")) 
					headInfo.append("Content-Type: text/html;charset=utf-8").append(CRLF);
				if(kind.equals("jsp")) 
					headInfo.append("Content-Type: application/x-javascript").append(CRLF);
			}
			else if(kind.equals("jpg")||kind.equals("png")||kind.equals("gif")){
				if(kind.equals("jpg")) {
					headInfo.append("Content-Type: image/jpeg").append(CRLF);
				}
				else if(kind.equals("png")) {
					headInfo.append("Content-Type: image/png").append(CRLF);
				}
				else if(kind.equals("gif")) {
					headInfo.append("Content-Type: image/gif").append(CRLF);
				}
				int lenth = (int)f.length();
				content.append("Content-Length:").append(lenth).append(CRLF);
			}
			else {
				headInfo.append("Content-Type: text/html;charset=utf-8").append(CRLF);
			}
			headInfo.append(CRLF);
		}
	}
	
	public void createHtml(File file,String fKind) throws IOException {
		if(fKind.equals("first")) {
				//输出本地文件列表和文件
				File f = new File(Status.path);
				content.append("<html><head>");
				content.append("<meta chartset=\"utf-8\" />");
				content.append("</head><body>");
				content.append("<h1>"+f.getAbsolutePath()+"</h1>");
				loadPath(f);
		}
		//解析操作
		else{
			if(!fKind.equals("Doc")) {
				System.out.println("fKind------>"+fKind);
				if(fKind.equals("html")||fKind.equals("jsp")) {
					InputStream in =new FileInputStream(file);
					InputStreamReader reader = new InputStreamReader(in);
					BufferedReader bufferedReader = new BufferedReader(reader);
					String line = null;
					while((line=bufferedReader.readLine())!=null) {
						content.append(line).append(CRLF);
					}
					in.close();
					reader.close();
					bufferedReader.close();
				}
				else if(fKind.equals("jpg")||fKind.equals("png")||fKind.equals("gif")){
					bw.append(headInfo);
					bw.flush();
					InputStream in = new FileInputStream(file);
					int len = -1;
					byte[]buff = new byte[1024];
					while((len=in.read(buff))!=-1) {
						out.write(buff,0,len);
						out.flush();					
					}
					isPic =true;
				}
			}
			else {
				//如果是目录
				content.append("<h1>"+file.getAbsolutePath()+"</h1>");
				loadPath(file);
			}
		}
	}
	public void loadPath(File f) throws IOException {
			String path = "";
			File[]files = f.listFiles();
			for(File temp:files) {
				//找出与根目录不同的部分
				path = temp.getAbsolutePath().substring(Status.path.length());
//			System.out.println(path);
				if(temp.isDirectory()) {
					content.append("<li><a style=\"color:red\">"+temp.getName()+"</a></li><br />");
				}
				else {
					content.append("<a href="+path+">"+temp.getName()+"</a><br />");
				}
			}
			content.append("</body></html>");
		}
	public void run(String path) throws IOException {
		String allPath = Status.path+path;		//绝对路径
		File f = new File(allPath);
		this.f = f;
		if(f.exists()) {
			if(path.equals("")) {
				createHeadInfo(200,"Doc");
				createHtml(f, "first");
			}
			else {
				if(path.indexOf(".")==-1) {
				createHeadInfo(200,"Doc");
				createHtml(f, "Doc");
				}
				else {
					String kind = path.substring(path.indexOf(".")+1);
					createHeadInfo(200, kind);
					createHtml(f, kind);
				}
			}
		}
		else {
			createHeadInfo(404, "");
		}
		if(!isPic) {
			bw.append(headInfo);
			bw.append(content);
		}
		bw.flush();			
		bw.close();
	}

	public void setContentType(String string) {
		// TODO Auto-generated method stub
		
	}

	public PrintWriter getWriter() {
		// TODO Auto-generated method stub
		return new PrintWriter(out);
	}

	public void sendRedirect(String string,MyRequest req,MyResponse rep) {
		// TODO Auto-generated method stub
		String clsName = "Jsp."+string.substring(0,string.indexOf("."));
		Class jspServlet;
		try {
			jspServlet = Class.forName(clsName);
			MyServlet test = (MyServlet)jspServlet.newInstance();
			req.setMethod("GET");
			System.out.println(test);
			test.service(req, rep);
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public OutputStream getOut() {
		return out;
	}
}

