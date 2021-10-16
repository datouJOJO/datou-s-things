package gis;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import com.csvreader.CsvReader;

public class GetLength {	
	public static int temp = 1;
	static HashMap<Integer,Integer>LengthMap = new HashMap<Integer,Integer>();	
	
	//地球平均半径
    private static final double EARTH_RADIUS = 6378137;
    //把经纬度转为度（°）
    private static double rad(double d){
       return d * Math.PI / 180.0;
    }

    public static double getDistance(double lng1, double lat1, double lng2, double lat2){
        double radLat1 = rad(lat1);
        double radLat2 = rad(lat2);
        double a = radLat1 - radLat2;
        double b = rad(lng1) - rad(lng2);
        double s = 2 * Math.asin(
 			Math.sqrt(
 			    Math.pow(Math.sin(a/2),2) 
 			    + Math.cos(radLat1)*Math.cos(radLat2)*Math.pow(Math.sin(b/2),2)
 			)
 		);
        s = s * EARTH_RADIUS;
        s = Math.round(s * 10000) / 10000;
        return s;
     }
  
	public static boolean isInt(String str) {   //判断整型
		return str.matches("^\\d+$$");
	}
    
    public static void Getinfo(File f)throws FileNotFoundException,IOException {  	
	    try {
            ArrayList<String[]> csvList = new ArrayList<String[]>(); 
            CsvReader reader = new CsvReader(f.getPath(),',',Charset.forName("GBK"));
//          //reader.readHeaders(); //跳过表头,不跳可以注释掉
            while(reader.readRecord()){
                csvList.add(reader.getValues()); //按行读取，并把每一行的数据添加到list集合
            }
            reader.close();
            int num = 0;
            for(int row=0;row<csvList.size();row++){   //csvList.size()            	
            	//System.out.print(csvList.get(row)[0]+",");           	
            	//System.out.println(csvList.get(row).length-1);  //列数  六十列就是  0 - 59    -2
            	double lll = 0.0;
            	int tag = 0;
            	for(int j = 0;j<csvList.get(row).length-2;j++) {
            		if(isInt(csvList.get(row)[j]))tag = 1;
            	}
            	if(tag == 0) {
	            	for(int j = 2;j<csvList.get(row).length-2;j+=2) {	            	
	            		//double d1[]= {Double.parseDouble(csvList.get(row)[j-2]),Double.parseDouble(csvList.get(row)[j-1])};
	            		//double d2[]= {Double.parseDouble(csvList.get(row)[j]),Double.parseDouble(csvList.get(row)[j+1])};
	            	    double length0 = getDistance(Double.parseDouble(csvList.get(row)[j-2]),Double.parseDouble(csvList.get(row)[j-1]),Double.parseDouble(csvList.get(row)[j]),Double.parseDouble(csvList.get(row)[j+1]));
	            	    lll+=length0;	            	    
	            	}	            	            	
	            	int length  = new Double(lll).intValue();  		
	        	    
	        	    if(LengthMap.get(length) != null) { 
	                	int t1 = LengthMap.get(length);
	                	t1 ++;
	                	LengthMap.put(length,t1);
	                }
	                else {
	                	LengthMap.put(length,1);
	                }
            	}
            	else {
            		num++;
            	}                   	
            }                                            
            System.out.print(num);                   
        } catch (Exception e) {
            e.printStackTrace();
        }		
	}
    
	public static void main(String[] args)throws FileNotFoundException,IOException {		
		File f = new File("D:\\All\\5.大三上\\GIS课设\\题目2补充数据\\03-骑行轨迹0313-0317.csv");   		   
		Getinfo(f);
			
		FileWriter fw = null;
		fw = new FileWriter(f.getParent()+"/" + "网络距离统计.txt"); 
		Iterator<Integer> iter = LengthMap.keySet().iterator();
		int count = 0;
		while (iter.hasNext()) {
			Object key = iter.next();
			Object val = LengthMap.get(key);
			fw.write(key +":"+val+"\r\n");
			
			int sss = Integer.parseInt(String.valueOf(val));
			count +=sss;
		}		
		fw.flush();
	   	fw.close();  
	   	System.out.print("    "+count);     
	}
}
