/**
 * 
 */
package lab2;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * @author 20171001234 xxx
 *
 *假设在 codes/lab1/目录下存在以下结构的文件组织：
*├─Java课内实习-201710001234-xxx-实习1
*│  ├─Java课内实习-20171000123-xxx-实习1
*│  │  └─lab1_code
*│  │      ├─rules
*│  │      └─turtle
*│  └─lab1_code
*│      ├─rules
*│      └─turtle
*├─Java课内实习-20171001235-xxx-实习一
*│  └─lab1
*│      └─lab1_code
*│          └─lab1_code
*│              ├─bin
*│              │  ├─rules
*│              │  └─turtle
*│              ├─rules
*│              └─turtle
*├─Java课内实习-20171001236-xxxx-实习一
*│  ├─rules
*│  └─turtle
*└─Java课内实习20171001237-xxxx-实习一
*    └─Java课内实习20171001237-xxx-实习一
*       └─Java课内实习20171001237-xxxx-实习一
*            └─lab1_code
*               ├─123
*                ├─rules
*                │  └─bin
*               └─turtle
*                    └─bin
*
*/
public class LabClosestMatches {
	
	/**
	 * 用于评价各相关目录下，指定文件的相似性。
	 * Similarity   子目录1                        子目录2
	 * 100%        Java课内实习-201710001234-xxx-实习1     Java课内实习-201710001235-xxx-实习1
	 * 89%         Java课内实习-201710001234-xxx-实习1     Java课内实习-201710001236-xxx-实习1
	 * ....
	 * @param path 作业文件所在的目录，比如这里是：codes/lab1
	 * @param fileNameMatches：用来过滤进行比较的文件名的文件名或者正则表达式.
	 * 如 "DrawableTurtle.java"，"*.java","turtle/*.java"
	 * 如果一个子目录下有多个符合条件的文件，将多个文件合并成一个文件。
	 * 
	 * @param topRate:取值范围从[0,100],输出控制的阈值
	 * 	从高往低输出高于topRate%相似列表，如
	 * @param removeComments:是否移除注释内容	
	 * 	 */
	public static void closestCodes(String path, String fileNameMatches,double topRate,boolean removeComments)
	{
		ArrayList<String> mylist=getFileList(path,fileNameMatches);
		System.out.println("Similarity                                                             子目录1                                                                                                                                                                                子目录2");
    	ArrayList<String> list=new ArrayList<String>();
    	for(int j=0;j<mylist.size();j++) {
    		list.add(mylist.get(j));
    	}
    	for(int i=0;i<mylist.size();i++) {
    		for(int j=0;j<mylist.size();j++) {
    			if(j!=i) {
    			String url1=list.get(i);
    			String url2=list.get(j);
    			ArrayList<String> sample1=new ArrayList<String>();
	    		try {
	    	    	Scanner sc=new Scanner(new File(url1));
	    			while(sc.hasNext()) {
	    				String temp=sc.next();
	    				sample1.add(temp);
	    			}
	    		}
	    		catch(IOException e) {
	    			e.printStackTrace();
	    		}
		        
		        //sample2.code
				ArrayList<String> sample2=new ArrayList<String>();
	    		try {
	    	    	Scanner sc=new Scanner(new File(url2));
	    			while(sc.hasNext()) {
	    				String temp=sc.next();
	    				sample2.add(temp);
	    			}
	    		}
	    		catch(IOException e) {
	    			e.printStackTrace();
	    		}
		            
		        //计算两个样本的词频及找到共同特征值
		        Map<String, Integer> sample1map = getCount(sample1);
		        Map<String, Integer> sample2map = getCount(sample2);
		        Map<String,Integer> wordFeature1 = getFeature1(sample1map,sample2map); 
		        Map<String,Integer> wordFeature2 = getFeature2(sample1map,wordFeature1); 
		        ArrayList<Integer> list1=new ArrayList<Integer>();
		        ArrayList<Integer> list2=new ArrayList<Integer>();
		        for (String key : wordFeature1.keySet()) {
		            list1.add(wordFeature1.get(key));
		        }
		        for (String key : wordFeature2.keySet()) {
		            list2.add(wordFeature2.get(key));
		        }
		        
		        //计算分母，即平方和的平方根
		        float sample1sqrt = calculatesqrt(sample1map);
		        float sample2sqrt = calculatesqrt(sample2map);

		        //计算余弦公式的分子
		        float molecule = getValue(list1,list2);
		        
		        //计算余弦值
		        //System.out.println(molecule/(sample1sqrt*sample2sqrt));
		        float cos=molecule/(sample1sqrt*sample2sqrt);
		        System.out.print(String.format("%.7f",cos));
			    System.out.print("         "+url1);
			    System.out.println("                 "+url2);
    		}
    	}
    }
    	
		// 计算文件间的相似性，输出
}
	
	public static ArrayList<String> getFileList(String path, String fileNameMatches){
		File file=new File(path);
		File[] filelist=file.listFiles();
		ArrayList<String> mylist=new ArrayList<String>();
		for(File f:filelist) {
			if(f.isFile()) {
				if(f.getName().matches(fileNameMatches)) {
					mylist.add(f.getAbsolutePath());
				}
			}
		}
		ArrayList<String> res=new ArrayList<String>();
		if(mylist.size()!=0) {
			res.addAll(mylist);
		}
		for(File f:filelist) {
			if(!f.isFile()) {
				ArrayList<String> tmp=getFileList(f.getAbsolutePath(),fileNameMatches);
				if(tmp!=null) {
					res.addAll(tmp);
				}
			}
		}
		return res;
	}
	
	//计算样本单词的词频
    public static  Map<String,Integer> getCount(ArrayList<String> sample){
        Map<String,Integer> wordCountMap = new HashMap<String,Integer>();
        int count = 0;
        for(String s:sample){
                count = 1;
                if(wordCountMap.containsKey(s))
                {
                    count = wordCountMap.get(s) + 1;
                    wordCountMap.remove(s);
                }
                wordCountMap.put(s,count);
        }
        return wordCountMap;
    }
    
    //根据样本1，找到样本2中共有的特征值
    private static Map<String,Integer> getFeature1(Map<String,Integer> sample1feature,Map<String, Integer> sample2feature){
        Map<String,Integer> feature =new HashMap<String,Integer>();
        Collection<String> word1 =  sample1feature.keySet();
        Collection<String> word2 =  sample2feature.keySet();
        for(String s1 :word1){
        	for(String s2:word2) {
        		if(s1.equals(s2)) {
        			feature.put(s1, sample2feature.get(s2));
        		}
        	}
        }
        return feature;
    }
    
    //根据样本2，找到样本1中共有的特征值
    private static Map<String,Integer> getFeature2(Map<String,Integer> sample1feature,Map<String, Integer> sample2feature){
        Map<String,Integer> feature =new HashMap<String,Integer>();
        Collection<String> word1 =  sample1feature.keySet();
        Collection<String> word2 =  sample2feature.keySet();
        for(String s2 :word2){
        	for(String s1:word1) {
        		if(s2.equals(s1)) {
        			feature.put(s2, sample1feature.get(s1));
        		}
        	}
        }
        return feature;
    }
    
    //计算分母
    private static float calculatesqrt(Map<String, Integer> sample) {
        float result = 0;
        Collection<Integer> valueset =  sample.values();
        for(Integer temp : valueset){
            if (temp > 0) {
                result += temp * temp;
            }
        }
        return (float) Math.sqrt(result);
    }
    
    //计算分子
    private static float getValue(ArrayList<Integer> sample1, ArrayList<Integer> sample2) {
        float molecule  = 0;
        float cur1 = 0;
        float cur2 = 0;
        for (int i = 0; i < sample1.size(); i++) {
            cur1 = sample1.get(i);
            cur2 = sample2.get(i);
            if (cur1 > 0 && cur2 > 0) {
            	molecule += cur1 * cur2;
            }
        }
        return (float)(molecule);
    }
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		LabClosestMatches.closestCodes("D:\\lab2\\lab1-codes-fortest", "TurtleSoup.java", 0, true);
		// TODO Auto-generated method stub
	}

}
