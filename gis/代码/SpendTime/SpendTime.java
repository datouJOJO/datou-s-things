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
		if(f.isDirectory()){    //如果还存在子目录则继续读取
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
//          reader.readHeaders(); //跳过表头,不跳可以注释掉
            while(reader.readRecord()){
                csvList.add(reader.getValues()); //按行读取，并把每一行的数据添加到list集合
            }
            reader.close();
            for(int row=0;row<csvList.size();row++){   
            	String name = csvList.get(row)[0];
            	int start = Integer.parseInt(csvList.get(row)[1].substring(11, 13))*60 + Integer.parseInt(csvList.get(row)[1].substring(14, 16));
                int finish = Integer.parseInt(csvList.get(row)[4].substring(11, 13))*60 + Integer.parseInt(csvList.get(row)[4].substring(14, 16)); 
                int spend = finish - start;
                if(StimeMap.get(spend) != null && spend>=0) {  //有91个错误 负的数据  舍了
                	int ttt = StimeMap.get(spend);
                	ttt ++;
                	StimeMap.put(spend,ttt);
                }
                else if(spend>=0){
                	StimeMap.put(spend,1);
                }
                
    			RandomAccessFile fff = new RandomAccessFile(f.getParent()+"/" + "用时统计.txt","rw");
    			long fileLength = fff.length();
    			// 将写文件指针移到文件尾。
    			fff.seek(fileLength);
    			fff.write((temp+"("+name+"):"+spend+"\r\n").getBytes() );
    			fff.close();   		  			
    			System.out.println("统计第"+temp +"个对象第"+row+"行");   			
            }
       
        } catch (Exception e) {
            e.printStackTrace();
        }		
	}
	
	public static void main(String[] args)throws FileNotFoundException,IOException {
		File f = new File("D:\\All\\5.大三上\\GIS课设\\共享单车0416");   

        File file = new File(f.getPath() + "/" + "用时统计.txt");
        if (file.exists()) file.delete();		
        else  file.createNewFile();

		getFile(f,".csv");	
			
		FileWriter fw = null;
		fw = new FileWriter(f.getPath()+"/" + "用时统计2.txt"); 
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
