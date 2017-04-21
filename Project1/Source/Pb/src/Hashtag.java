import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONStringer;

public class Hashtag {
	BufferedReader br;
	StringBuilder str;
	public void fn() throws Exception
	{
		TreeMap<String,Integer> map=new TreeMap<String,Integer>();
		TreeMap<Integer,String> tr=new TreeMap<Integer,String>();
	 String result=null;
	try
	{
		br=new BufferedReader(new FileReader("C:/Users/VenkatNag/Desktop/twitter_50k.json"));
		 str=new StringBuilder();
		String line=br.readLine();
		while(line!=null)
		{
			str.append(line);
			line=br.readLine();
		
		result=str.toString();
		str.delete(0, str.length());
	 
	  
	
		JSONObject	object=new JSONObject(result);
		if(object.has("retweeted_status"))
		{
		JSONObject re=object.getJSONObject("retweeted_status");
		JSONObject e=re.getJSONObject("entities");
		JSONArray hash=e.getJSONArray("hashtags");
		if(hash.length()!=0){
		JSONObject t=hash.getJSONObject(0);
		
		for(int i=0;i<1;i++)
		{
			String s=t.getString("text");
		
		if(map.containsKey(s.toLowerCase())){
			int j=map.get(s.toLowerCase());
			map.put(s.toLowerCase(),j+1);
		}
		
		else
		{
map.put(s.toLowerCase(),1);		
		}
	/*	String z=t.getString("text");
		JSONObject h=object.getJSONObject("entities");
		JSONArray v=h.getJSONArray("hashtags");
		JSONObject text=v.getJSONObject(1);*/
		
	}}}}


		Set<Entry<String, Integer>> set = map.entrySet();
	      Iterator<Entry<String, Integer>> iterator = set.iterator();
	      while(iterator.hasNext()) {
	         Map.Entry<String,Integer> mentry = (Map.Entry<String,Integer>)iterator.next();
	        if(mentry.getValue()>=10)
	        {
	        	tr.put(mentry.getValue(),mentry.getKey());
	       //  System.out.print("key is: "+ mentry.getKey() + " & Value is: ");
	       //  System.out.println(mentry.getValue());
	      }
		}      
	}
	
	catch(Exception e)
	{
		System.out.println(e.getMessage());
	}
	finally{
		br.close();
	}
	tr.lastEntry();
	//Set<Entry<Integer,String>> set = tr.entrySet();
   // Iterator<Entry<Integer,String>> iterator = set.iterator();
	int i=1;
	ArrayList<String> a=new ArrayList<String>();
    while(i<=10) {
       Map.Entry<Integer,String> mentry = (Map.Entry<Integer,String>)tr.lastEntry();
     
      	
       System.out.print("key is: "+ mentry.getKey() + " & Value is: ");
       System.out.println(mentry.getValue());
       a.add(mentry.getValue());
       tr.remove(mentry.getKey());
       i++;
       
      
    }
    fileout(a);
	}
	
   //writing tweets to outpt files based on hashtag
 void fileout(ArrayList<String> a)
 {
    BufferedWriter bw = null;
    FileWriter fw;
	
	//File f=new File("");
	
	//FileWriter fw2 = new FileWriter("E:/UMKC/1st Sem-Jan/PB/Project/Others.txt",true);
	//FileWriter fw3 = new FileWriter("E:/UMKC/1st Sem-Jan/PB/Project/Others.txt",true);

    try
    {
    //	FileWriter fw1 = new FileWriter("E:/UMKC/1st Sem-Jan/PB/Project/none.txt",true);
    	@SuppressWarnings("resource")
		BufferedReader br1=new BufferedReader(new FileReader("C:/Users/VenkatNag/Desktop/twitter_50k.json"));
    	String line=br1.readLine();
    	while(line!=null)
		{
			
    	JSONObject	object=new JSONObject(line.toString());
		if(object.has("retweeted_status"))
		{
		JSONObject re=object.getJSONObject("retweeted_status");
		JSONObject e=re.getJSONObject("entities");
		JSONArray hash=e.getJSONArray("hashtags");
		if(hash.length()!=0){
		JSONObject t=hash.getJSONObject(0);

		for(int j=0;j<1;j++)
		{
			String s=t.getString("text");
			if(a.contains(s.toLowerCase()))
{
				String name="E:\\UMKC\\1st Sem-Jan\\PB\\Project\\"+s+".txt";
				File f=new File(name);
				if (!f.exists()) {
					f.createNewFile();
				}
	
	fw= new FileWriter(f,true);
	bw=new BufferedWriter(fw);
     bw.write(line);
     bw.flush();
     bw=null;
	
}
			else
			{
				bw=null;
				fw= new FileWriter("E:\\UMKC\\1st Sem-Jan\\PB\\Project\\Others.txt",true);
				bw=new BufferedWriter(fw);
		         bw.write(line);
		         bw.flush();
		        
			}
		}}
		else
		{
			bw=null;
			fw= new FileWriter("E:\\UMKC\\1st Sem-Jan\\PB\\Project\\none.txt",true);
			bw=new BufferedWriter(fw);
	         bw.write(line);
	         bw.flush();
		}
    }
		else
		{
			bw=null;
			fw= new FileWriter("E:\\UMKC\\1st Sem-Jan\\PB\\Project\\none.txt",true);
			bw=new BufferedWriter(fw);
	         bw.write(line);
	         bw.flush();
		}
		line=br1.readLine();
		}
    }catch(Exception e)
    {
    	e.getMessage();
    }
    
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
Hashtag h=new Hashtag();
try {
	h.fn();
} catch (Exception e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}
	}

}



