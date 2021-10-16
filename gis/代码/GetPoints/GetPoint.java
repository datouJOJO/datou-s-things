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

public class GetPoint {	
	//public static int temp = 1;
 
	public static boolean isInt(String str) {   //�ж�����
		return str.matches("^\\d+$$");
	}
	
	public static ArrayList<String> a=new ArrayList<String>();
	public static ArrayList<String> b=new ArrayList<String>();
    public static void Getinfo(File f)throws FileNotFoundException,IOException {  	
	    try {
            ArrayList<String[]> csvList = new ArrayList<String[]>(); 
            CsvReader reader = new CsvReader(f.getPath(),',',Charset.forName("GBK"));
            while(reader.readRecord()){
                csvList.add(reader.getValues());
            }
            reader.close();            
            for(int row=0;row <csvList.size() ;row++){    	//csvList.size()
            	//System.out.print(csvList.get(row)[0]+",");           	
            	//System.out.println(csvList.get(row).length-1);  //����  ��ʮ�о���  0 - 59    -2           
	            	for(int j = 0;j<csvList.get(row).length-2;j+=2) {	
	            		if(!isInt(csvList.get(row)[j])) {
	            			//double d1[]= {Double.parseDouble(csvList.get(row)[j-2]),Double.parseDouble(csvList.get(row)[j-1])};
	            			//double d2[]= {Double.parseDouble(csvList.get(row)[j]),Double.parseDouble(csvList.get(row)[j+1])};
	            			
//	            			RandomAccessFile fff = new RandomAccessFile(f.getParent()+"/" + "points.txt","rw");
//	    	    			long fileLength = fff.length();
//	    	    			// ��д�ļ�ָ���Ƶ��ļ�β
//	    	    			fff.seek(fileLength);
//	    	    			//fff.write((row+1+":"+length+"\r\n").getBytes() );
//	    	    			fff.write((csvList.get(row)[j]+"\t"+csvList.get(row)[j+1]+"\r\n").getBytes());
//	    	    			fff.close();   		
	            				    	    			
	    	    			a.add(csvList.get(row)[j]);
	    	    			b.add(csvList.get(row)[j+1]);
	            		}    
	            	}	            	            	
	           	                	
            }                                                                 
        } catch (Exception e) {
            e.printStackTrace();
        }		
	}
       
	public static void main(String[] args)throws FileNotFoundException,IOException {		
		File f = new File("D:\\All\\5.������\\GIS����\\��Ŀ2��������\\03-���й켣0313-0317.csv");   		   
		
//		File file = new File(f.getParent() + "/" + "points.txt");
//        if (file.exists()) file.delete();		
//        else  file.createNewFile();                      //�������ָ��ĩ��д�ļ���1:48��3:16 ������
        
		Getinfo(f);
				
		FileWriter fw = null;
		fw = new FileWriter(f.getParent()+"/" + "Points.txt"); 	 //������д����
		
		Object[] o =a.toArray();
		Object[] oo =b.toArray();
		for(int i=0;i<o.length;i++) {
			fw.write(o[i].toString()+"\t"+oo[i].toString()+"\r\n");			
		}
		
		fw.flush();
	   	fw.close();  
	}
}

