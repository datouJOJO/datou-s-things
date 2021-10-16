package Jsp;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class JspIfoParse {
	private String clsName;		//����
	private String method1;		//���÷���
	private String method2;
	
	public JspIfoParse(String path, String method) throws IOException {
		super();
		//����������
		this.clsName = "Jsp."+path.substring(path.lastIndexOf("/")+1,path.lastIndexOf("."));
		System.out.println("cls---->"+this.clsName);
		if(method.length()==3) {
			method1 = "doGet";
			method2 = "doPost";
		}
		else {
			method1 = "doPost";
			method2 = "doGet";
		}
		
		FileReader fr = new FileReader(new File(path));
		BufferedReader br = new BufferedReader(fr);
		String line = "";
		String fIfo = "";
		while((line=br.readLine())!=null) {
			fIfo+=line+"\n";//�ӻس�������洦��
		}
//		System.out.println("ifo---->");
//		System.out.println(fIfo);
		fIfo = fIfo.replace("\"", "\\\"");
		String file1= "src/Jsp/"+path.substring (path.lastIndexOf ("/")+1,path.lastIndexOf ("."))+".java"; 
		System.out.println(file1);
		FileWriter filer = new FileWriter(file1);
		//ǰ���ͳһ����
		String head = "package Jsp;\n\nimport java.io.*;\nimport MyWebServer.MyRequest;\n"+
					"import MyWebServer.MyResponse;\nimport MyWebServer.MyServlet;\n\n"+
					"public class "+path.substring (path.lastIndexOf ("/")+1,path.lastIndexOf ("."))+" extends MyServlet {\n\n"+
					"public void"+" "+method1+"(MyRequest request, MyResponse response){\n";
		filer.write(head);
		filer.write("PrintWriter out = new PrintWriter(response.getOut());\n");
		filer.write("out.println(\"HTTP-1.0 200 OK\\r\\n\");"+"\n");
	
		if(fIfo.indexOf("<html>")!=-1) {//�����html���ţ�����html����ǰ�������
			if(fIfo.indexOf("<html>")>1) {
				fIfo = fIfo.substring(fIfo.indexOf("<html>")-1);
			}
			else {
				fIfo = fIfo.substring(fIfo.indexOf("<html>"));
			}
		}
		
		if(fIfo.indexOf("<%=")!=-1) {//�Ƿ�����Ҫout.println������
        	int if_try = 0;//�쳣�ж�
			if(fIfo.indexOf("getParameter")!=-1) {//���Ҫ�û�id�������Ҫ�׳��쳣
	        	if_try = 1;
	        	filer.write("try {\n");
			}
        	String[]file = fIfo.split("\n");//��ǰ�����������һ���зֿ�����
        	for(String temp:file ) {
        		if(temp.indexOf("<%=")!=-1) {//��ȡ��Ҫout.println������
        			temp = temp.replace("\\\"", "\"");
        			String store = temp;
        			while(store.indexOf("<%=")!=-1) {
        				if(store.indexOf("<%=")>0) {
        					filer.write("out.println(\"" + store.substring(0, store.indexOf("<%=")) + "\"" + ");\n");
        					store = store.substring(store.indexOf("<%=")+"<%=".length());
        				}
        				filer.write("out.println(" + store.substring(0, store.indexOf("%>")) + ");\n");
        				store = store.substring(store.indexOf("%>")+"%>".length());
        			}
        			filer.write("out.println(\"" + store + "\"" + ");\n");
        		}
        		else {
        			filer.write("out.println(\"" + temp + "\"" + ");\n");
        		}
        	}
        	if(if_try==1) {
        		filer.write("} catch (IOException e) {\r\n" + "	e.printStackTrace();\r\n" + "}");
        	}
        }
        else {
			while (fIfo.lastIndexOf("%>") != -1) {//�ҵ�<%��%>�м��jsp����
				String store = fIfo.substring(0, fIfo.indexOf("<%"));
				while (store.indexOf("\n") != -1) {
					filer.write("out.println(\"" + store.substring(0, store.indexOf("\n")) + "\"" + ");\n");
					store = store.substring(store.indexOf("\n") + "\n".length());
				}
				String fina = fIfo.substring(fIfo.indexOf("<%") + "<%".length(), fIfo.indexOf("%>"));
				if (fina.indexOf("=") == 0)
					filer.write("out.println(" + fina.substring(1) + ");\n");
				else {
					fina = fina.replace("\\\"", "\"");
					filer.write(fina + "\n");
				}
				fIfo = fIfo.substring(fIfo.indexOf("%>") + "%>".length());
			}
			if(!fIfo.equals (null)){
				String temp = fIfo;
				while (temp.indexOf ("\n")!=-1){
					filer.write("out.println(\""+temp.substring(0,temp.indexOf("\n")-1)+"\""+");\n");
					temp = temp.substring(temp.indexOf("\n")+"\n".length());
				}
			}
        }
        filer.write("out.flush();\nout.close();\n");
        filer.write("}\n"+"\r\n" + "protected void"+" "+ method2+ "(MyRequest request, MyResponse resp){}\n}");
        filer.flush ();
        filer.close ();
	}
	
	//�õ���������
	public String getClsName() {
		return clsName;
	}
}
