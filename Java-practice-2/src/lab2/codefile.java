package lab2;

import java.io.File;
import java.io.IOException;
import java.util.*;


public class codefile {
	    public static void main(String[] args) {  	
	    	    //sample1.code
	    	    System.out.println("��һ��urlΪ��D:\\lab2\\sample1.code");
	    	    System.out.println("�ڶ���urlΪ��D:\\lab2\\sample2.code");
	    	    System.out.print("����url������������Ϊ��");
			    ArrayList<String> sample1=new ArrayList<String>();
	    		try {
	    	    	Scanner sc=new Scanner(new File("D:\\lab2\\sample1.code"));
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
	    	    	Scanner sc=new Scanner(new File("D:\\lab2\\sample2.code"));
	    			while(sc.hasNext()) {
	    				String temp=sc.next();
	    				sample2.add(temp);
	    			}
	    		}
	    		catch(IOException e) {
	    			e.printStackTrace();
	    		}
		            
		        //�������������Ĵ�Ƶ���ҵ���ͬ����ֵ
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
		        
		        //�����ĸ����ƽ���͵�ƽ����
		        float sample1sqrt = calculatesqrt(sample1map);
		        float sample2sqrt = calculatesqrt(sample2map);

		        //�������ҹ�ʽ�ķ���
		        float molecule = getValue(list1,list2);
		        
		        //��������ֵ
		        System.out.println(molecule/(sample1sqrt*sample2sqrt));
	    }
	    
	    //�����������ʵĴ�Ƶ
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
	    
	    //��������1���ҵ�����2�й��е�����ֵ
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
	    
	    //��������2���ҵ�����1�й��е�����ֵ
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
	    
	    //�����ĸ
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
	    
	    //�������
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