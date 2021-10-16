

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class CodeFile {
	
	public static void main(String[] args) {  	
	    String fileName1 = "D:\\all.html.txt";
		String fileName2 = "D:\\all.html1.txt";
		CodeFile test = new CodeFile();
        System.out.println(test.toString(fileName1,fileName2));
        fileName1 = "D:\\test1.html.txt";
        fileName2 = "D:\\test1.html1.txt";
        System.out.println(test.toString(fileName1,fileName2));
        fileName1 = "D:\\test2.jsp.txt";
        fileName2 = "D:\\test2.jsp1.txt";
        System.out.println(test.toString(fileName1,fileName2));
        fileName1 = "D:\\test3.html.txt";
        fileName2 = "D:\\test3.html1.txt";
        System.out.println(test.toString(fileName1,fileName2));
//        fileName1 = "D:\\test4.jsp.txt";
//        fileName2 = "D:\\test4.jsp1.txt";
//        System.out.println(test.toString(fileName1,fileName2));
        fileName1 = "D:\\test6.jsp.txt";
        fileName2 = "D:\\test6.jsp1.txt";
        System.out.println(test.toString(fileName1,fileName2));
//        fileName1 = "D:\\show3.jsp.txt";
//        fileName2 = "D:\\show3.jsp1.txt";
//        System.out.println(test.toString(fileName1,fileName2));
        fileName1 = "D:\\show5.jsp.txt";
        fileName2 = "D:\\show5.jsp1.txt";
        System.out.println(test.toString(fileName1,fileName2));
	}
	
	
	public String toString(String fileName1,String fileName2) {
		float res = calculate(fileName1,fileName2);
		return fileName1+"和"+fileName2+"的相似度为"+res;
	}
	
	public float calculate(String fileName1,String fileName2) {
		List<String> content = get_content(fileName1);
		List<String> content1 = get_content(fileName2);
		Map<String, Integer> file1_map = get_freq(content);
        Map<String, Integer> file2_map = get_freq(content1);
        Map<String,Integer> feature1 = get_feature(file1_map,file2_map); 
        Map<String,Integer> feature2 = get_feature(feature1,file1_map); 
        List<Integer> list1=new ArrayList<Integer>();
        List<Integer> list2=new ArrayList<Integer>();
        for (String key : feature1.keySet()) {
            list1.add(feature1.get(key));
        }
        for (String key : feature2.keySet()) {
            list2.add(feature2.get(key));
        }
        
        //计算分母，即平方和的平方根
        float denominator1 = get_denominator(file1_map);
        float denominator2 = get_denominator(file2_map);

        //计算余弦公式的分子
        float molecule = get_molecule(list1,list2);
        return (molecule/(denominator1*denominator2));
	}
	
	public List<String> get_content(String fileName){
		List<String> content = new ArrayList<>();
		try {
	    	Scanner sc=new Scanner(new File(fileName));
			while(sc.hasNext()) {
				String temp = sc.next();
				content.add(temp);
			}
			sc.close();
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		return content;
	}

	//计算样本单词的词频
	public Map<String,Integer> get_freq( List<String> content){
		Map<String,Integer> freq_map = new HashMap<String,Integer>();
		int count = 0;
		for(String s:content){
            count = 1;
            if(freq_map.containsKey(s))
            {
                count = freq_map.get(s) + 1;
                freq_map.remove(s);
            }
            freq_map.put(s,count);
		}
		return freq_map;
	}

	//根据样本1，找到样本2中共有的特征值
	public Map<String,Integer> get_feature(Map<String,Integer> feature1,Map<String, Integer> feature2){
		Map<String,Integer> feature =new HashMap<String,Integer>();
		Collection<String> word1 =  feature1.keySet();
		Collection<String> word2 =  feature2.keySet();
		for(String s1 :word1){
			for(String s2:word2) {
				if(s1.equals(s2)) {
					feature.put(s1, feature2.get(s2));
				}
			}
		}
		return feature;
	}

	//计算分母
	public float get_denominator(Map<String, Integer> feature) {
		float result = 0;
		Collection<Integer> valueset =  feature.values();
		for(Integer temp : valueset){
			if (temp > 0) {
				result += temp * temp;
			}
		}
		return (float) Math.sqrt(result);
	}

	//计算分子
	public float get_molecule(List<Integer> feature1, List<Integer> feature2) {
		float molecule  = 0;
		float cur1 = 0;
		float cur2 = 0;
		for (int i = 0; i < feature1.size(); i++) {
			cur1 = feature1.get(i);
			cur2 = feature2.get(i);
			if (cur1 > 0 && cur2 > 0) {
				molecule += cur1 * cur2;
			}
		}
		return (float)(molecule);
	}

}
