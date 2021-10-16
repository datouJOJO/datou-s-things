package gis;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import com.csvreader.CsvReader;

public class SpendTime {
	public static int temp = 1;
	static HashMap<Integer,Integer> StimeMap = new HashMap<Integer,Integer>();	
	public static void getFile(File f,String str)throws FileNotFoundException,IOException{		 	
		if(f.isDirectory()){    //�����������Ŀ¼�������ȡ
			File[]subfiles = f.listFiles();
			for(File fi:subfiles){
				getFile(fi,str);
			}
		}
		else{		
			if(f.getName().indexOf(str)>0) {  
				Getinfo(f);
				temp++;
			}				
		}
	}

	public static void Getinfo(File f)throws FileNotFoundException,IOException {
	    try {
            ArrayList<String[]> csvList = new ArrayList<String[]>(); 
            CsvReader reader = new CsvReader(f.getPath(),',',Charset.forName("GBK"));
//          reader.readHeaders(); //������ͷ,��������ע�͵�
            while(reader.readRecord()){
                csvList.add(reader.getValues()); //���ж�ȡ������ÿһ�е�������ӵ�list����
            }
            reader.close();
            for(int row=0;row<csvList.size();row++){   
            	String name = csvList.get(row)[0];
            	int start = Integer.parseInt(csvList.get(row)[1].substring(11, 13))*60 + Integer.parseInt(csvList.get(row)[1].substring(14, 16));
                int finish = Integer.parseInt(csvList.get(row)[4].substring(11, 13))*60 + Integer.parseInt(csvList.get(row)[4].substring(14, 16)); 
                int spend = finish - start;
                if(StimeMap.get(spend) != null && spend>=0) {  //��91������ ��������  ����
                	int ttt = StimeMap.get(spend);
                	ttt ++;
                	StimeMap.put(spend,ttt);
                }
                else if(spend>=0){
                	StimeMap.put(spend,1);
                }
                
    			RandomAccessFile fff = new RandomAccessFile(f.getParent()+"/" + "��ʱͳ��.txt","rw");
    			long fileLength = fff.length();
    			// ��д�ļ�ָ���Ƶ��ļ�β��
    			fff.seek(fileLength);
    			fff.write((temp+"("+name+"):"+spend+"\r\n").getBytes() );
    			fff.close();   		  			
    			System.out.println("ͳ�Ƶ�"+temp +"�������"+row+"��");   			
            }
       
        } catch (Exception e) {
            e.printStackTrace();
        }		
	}
	
	public static void main(String[] args)throws FileNotFoundException,IOException {
		File f = new File("D:\\All\\5.������\\GIS����\\������0416");   

        File file = new File(f.getPath() + "/" + "��ʱͳ��.txt");
        if (file.exists()) file.delete();		
        else  file.createNewFile();

		getFile(f,".csv");	
			
		FileWriter fw = null;
		fw = new FileWriter(f.getPath()+"/" + "��ʱͳ��2.txt"); 
		Iterator<Integer> iter = StimeMap.keySet().iterator();
		while (iter.hasNext()) {
			Object key = iter.next();
			Object val = StimeMap.get(key);
			fw.write(key +":"+val+"\r\n");
		}		
		fw.flush();
	   	fw.close();  
	   	
	}
}
