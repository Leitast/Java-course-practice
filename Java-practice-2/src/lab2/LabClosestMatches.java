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
 *������ codes/lab1/Ŀ¼�´������½ṹ���ļ���֯��
*����Java����ʵϰ-201710001234-xxx-ʵϰ1
*��  ����Java����ʵϰ-20171000123-xxx-ʵϰ1
*��  ��  ����lab1_code
*��  ��      ����rules
*��  ��      ����turtle
*��  ����lab1_code
*��      ����rules
*��      ����turtle
*����Java����ʵϰ-20171001235-xxx-ʵϰһ
*��  ����lab1
*��      ����lab1_code
*��          ����lab1_code
*��              ����bin
*��              ��  ����rules
*��              ��  ����turtle
*��              ����rules
*��              ����turtle
*����Java����ʵϰ-20171001236-xxxx-ʵϰһ
*��  ����rules
*��  ����turtle
*����Java����ʵϰ20171001237-xxxx-ʵϰһ
*    ����Java����ʵϰ20171001237-xxx-ʵϰһ
*       ����Java����ʵϰ20171001237-xxxx-ʵϰһ
*            ����lab1_code
*               ����123
*                ����rules
*                ��  ����bin
*               ����turtle
*                    ����bin
*
*/
public class LabClosestMatches {
	
	/**
	 * �������۸����Ŀ¼�£�ָ���ļ��������ԡ�
	 * Similarity   ��Ŀ¼1                        ��Ŀ¼2
	 * 100%        Java����ʵϰ-201710001234-xxx-ʵϰ1     Java����ʵϰ-201710001235-xxx-ʵϰ1
	 * 89%         Java����ʵϰ-201710001234-xxx-ʵϰ1     Java����ʵϰ-201710001236-xxx-ʵϰ1
	 * ....
	 * @param path ��ҵ�ļ����ڵ�Ŀ¼�����������ǣ�codes/lab1
	 * @param fileNameMatches���������˽��бȽϵ��ļ������ļ�������������ʽ.
	 * �� "DrawableTurtle.java"��"*.java","turtle/*.java"
	 * ���һ����Ŀ¼���ж�������������ļ���������ļ��ϲ���һ���ļ���
	 * 
	 * @param topRate:ȡֵ��Χ��[0,100],������Ƶ���ֵ
	 * 	�Ӹ������������topRate%�����б���
	 * @param removeComments:�Ƿ��Ƴ�ע������	
	 * 	 */
	public static void closestCodes(String path, String fileNameMatches,double topRate,boolean removeComments)
	{
		ArrayList<String> mylist=getFileList(path,fileNameMatches);
		System.out.println("Similarity                                                             ��Ŀ¼1                                                                                                                                                                                ��Ŀ¼2");
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
		        //System.out.println(molecule/(sample1sqrt*sample2sqrt));
		        float cos=molecule/(sample1sqrt*sample2sqrt);
		        System.out.print(String.format("%.7f",cos));
			    System.out.print("         "+url1);
			    System.out.println("                 "+url2);
    		}
    	}
    }
    	
		// �����ļ���������ԣ����
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
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		LabClosestMatches.closestCodes("D:\\lab2\\lab1-codes-fortest", "TurtleSoup.java", 0, true);
		// TODO Auto-generated method stub
	}

}
