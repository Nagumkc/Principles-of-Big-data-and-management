import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.Map.Entry;

import org.json.JSONObject;


class besttime
{   
	BufferedReader br;
	String result=null;
	 TreeMap<String,Integer> t=new TreeMap<String,Integer>();
	 
	 TreeMap<Integer,String> tr=new TreeMap<Integer,String>();
	 
	public void fn()
	{
		try
		{
			br=new BufferedReader(new FileReader("C:/Users/VenkatNag/Desktop/twitter_1L.json"));
			StringBuilder	 str=new StringBuilder();
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
				JSONObject o=object.getJSONObject("retweeted_status");
			String s=o.getString("created_at");
			 map(s);
			}
			}
			reduce();
		}catch(Exception e)
			{
				System.out.println(e.getMessage());
			}
	}
		
	public void map(String s)throws Exception
	{
		String day=s.substring(0,3);
		int n=Integer.parseInt(s.substring(11, 13));
		if(n>=00 &n<02)
		{
			if(t.containsKey(day+" between 0 & 2"))
			{
				int y=t.get(day+" between 0 & 2");
			
			t.put(day+" between 0 & 2",y+1);
		}
			else
			{
				t.put(day+" between 0 & 2",1);
			}
		}
		else if(n>=02 &n<04)
		{
			if(t.containsKey(day+" between 2 & 4"))
			{
				int y=t.get(day+" between 2 & 4");
			
			t.put(day+" between 2 & 4",y+1);
		}
			else
			{
				t.put(day+" between 2 & 4",1);
			}
		}
		
		else if(n>=04 &n<06)
		{
			if(t.containsKey(day+" between 4 & 6"))
			{
				int y=t.get(day+" between 4 & 6");
			
			t.put(day+" between 4 & 6",y+1);
		}
			else
			{
				t.put(day+" between 4 & 6",1);
			}
		}
		else if(n>=06 &n<8)
		{
			if(t.containsKey(day+" between 6 & 8"))
			{
				int y=t.get(day+" between 6 & 8");
			
			t.put(day+" between 6 & 8",y+1);
		}
			else
			{
				t.put(day+" between 6 & 8",1);
			}
		}
		
		else if(n>=8&n<10)
		{
			if(t.containsKey(day+" between 8 & 10"))
			{
				int y=t.get(day+" between 8 & 10");
			
			t.put(day+" between 8 & 10",y+1);
		}
			else
			{
				t.put(day+" between 8 & 10",1);
			}
		}
		else if(n>=10&n<12)
		{
			if(t.containsKey(day+" between 10 & 12"))
			{
				int y=t.get(day+" between 10 & 12");
			
			t.put(day+" between 10 & 12",y+1);
		}
			else
			{
				t.put(day+" between 10 & 12",1);
			}
		}
		else if(n>=12&n<14)
		{
			if(t.containsKey(day+" between 12 & 14"))
			{
				int y=t.get(day+" between 12 & 14");
			
			t.put(day+" between 12 & 14",y+1);
		}
			else
			{
				t.put(day+" between 12 & 14",1);
			}
		}
		else if(n>=14&n<16)
		{
			if(t.containsKey(day+" between 14 & 16"))
			{
				int y=t.get(day+" between 14 & 16");
			
			t.put(day+" between 14 & 16",y+1);
		}
			else
			{
				t.put(day+" between 14 & 16",1);
			}
		}
		else if(n>=16&n<18)
		{
			if(t.containsKey(day+" between 16 & 18"))
			{
				int y=t.get(day+" between 16 & 18");
			
			t.put(day+" between 16 & 18",y+1);
		}
			else
			{
				t.put(day+" between 16 & 18",1);
			}
		}
		else if(n>=18&n<20)
		{
			if(t.containsKey(day+" between 18 & 20"))
			{
				int y=t.get(day+" between 18 & 20");
			
			t.put(day+" between 18 & 20",y+1);
		}
			else
			{
				t.put(day+" between 18 & 20",1);
			}
		}
		else if(n>=20&n<22)
		{
			if(t.containsKey(day+" between 20 & 22"))
			{
				int y=t.get(day+" between 20 & 22");
			
			t.put(day+" between 20 & 22",y+1);
		}
			else
			{
				t.put(day+" between 20 & 22",1);
			}
		}
		else if(n>=22&n<24)
		{
			if(t.containsKey(day+" between 22 & 24"))
			{
				int y=t.get(day+" between 22 & 24");
			
			t.put(day+" between 22 & 24",y+1);
		}
			else
			{
				t.put(day+" between 22 & 24",1);
			}
		}
	}
	
	public void reduce()
	{
		Set<Entry<String, Integer>> set = t.entrySet();
	      Iterator<Entry<String, Integer>> iterator = set.iterator();
	      while(iterator.hasNext()) {
		         Entry<String, Integer> mentry = (Map.Entry<String,Integer>)iterator.next();
		       System.out.println(mentry.getKey()+"  count : "+mentry.getValue());
                tr.put(mentry.getValue(),mentry.getKey());
	      }
	    
	  System.out.println("Top ten best time to post the tweets \n");
	      int k=1;
	     while(k<=10)
	     {
	         Entry<Integer,String> m = (Entry<Integer, String>)tr.lastEntry();

	    	 System.out.println(m.getValue());
	    	 k++;
	    	 tr.remove(m.getKey());
	     }
	}
	
	public static void main(String[] args) {
		besttime u=new besttime();
		
		u.fn();
	}
	
	
}
