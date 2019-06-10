package lab2;

import java.io.File;
import java.io.IOException;
import java.util.*;


public class ClosestCodeMatch {
	    public static void main(String[] args) {  
	    	Scanner scanner=new Scanner(System.in);
	    	ArrayList<String> list=new ArrayList<String>();
	    	Map<Float,Map> res=new HashMap<Float,Map>();
	    	System.out.print("请输入URL的个数：");
	    	int number=scanner.nextInt();
	    	System.out.println("请输入相应的URL：");
	    	for(int j=0;j<number;j++) {
	    		list.add(scanner.next());
	    	}
	    	for(int i=0;i<number;i++) {
	    		for(int j=i+1;j<number;j++) {
	    			Map<String,String> strmap=new HashMap<String,String>();
	    			String url1=list.get(i);
	    			String url2=list.get(j);
	    			strmap.put(url1, url2);
	    			//System.out.println(strmap);
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
			        res.put(cos, strmap);
	    		}
	    	}
	    	
	    	System.out.println(res);
	    	//打印url
	    	ArrayList<Float> cosresult=new ArrayList<Float>();
	    	for(Float key:res.keySet()) {
	    		cosresult.add(key);
	    	}
	    	Float max=(float) -1;
	    	for(int i=0;i<cosresult.size();i++) {
	    		if(cosresult.get(i)>max) {
	    			max=cosresult.get(i);
	    		}
	    	}
	    	System.out.print("最大相似度为：");
	    	System.out.println(max);
	    	System.out.println("最相似的URL为：");
	    	Collection<String> mxurl1=res.get(max).keySet();
	    	Collection<String> maxurl2=res.get(max).values();
	    	System.out.println(mxurl1);
	    	System.out.println(maxurl2);

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
}