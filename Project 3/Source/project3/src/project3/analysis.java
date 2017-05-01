package project3;
import org.apache.spark.sql.SparkSession;

import java.io.BufferedReader;
import java.io.FileReader;

import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;

import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.Future;

import org.json.JSONObject;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.async.Callback;
import com.mashape.unirest.http.exceptions.UnirestException;
;

public class analysis {
	
public static void main(String[] args )
{
	SparkSession spark = SparkSession
			  .builder()
			  .appName("Java Spark SQL basic example")
			  .master("local[2]")
			  .getOrCreate();
	Dataset<Row> df=spark.read().json("/home/nag/res.json");
	Dataset<Row> r1=df.select("text");
  r1.write().format("com.databricks.spark.csv").save("/home/nag/Desktop/out");
	
  fileread();
	Dataset<Row> ds = spark.read().csv("/home/nag/Desktop/final.csv");
	ds.registerTempTable("map");
	Dataset<Row> r2=spark.sql("select _c0 as sentient,count(_c0) as count from map group by 1");
	r2.show();
	
r2.coalesce(1).write().format("com.databricks.spark.csv").save("/home/nag/Desktop/final");
}

public static void fileread(){
	try
	 {
		 @SuppressWarnings("resource")
		BufferedReader	 br=new BufferedReader(new FileReader("/home/nag/Desktop/out/whole.csv"));
	String line=br.readLine();
	while(line!=null)
	{
		String result=line.toString();
		fn(result);
		
		line=br.readLine();
   }
   }catch(Exception e)
	 {
   	System.out.println(e.getMessage());
	 }
   }
public static void fn(String s)throws IOException, UnirestException
{
	s=s.replaceAll("\"", "");
	Future<HttpResponse<JsonNode>> response = Unirest.post("https://community-sentiment.p.mashape.com/text/")
			.header("X-Mashape-Key", "DAB2StQGxemshHLyCnXT7zOY6ijvp1wBNBUjsnEEaiYPyKHEfC")
			.header("Content-Type", "application/x-www-form-urlencoded")
			.header("Accept", "application/json")
			.field("txt",s)
			.asJsonAsync(new Callback<JsonNode>(){
					
					 public void failed(UnirestException e) {
		        System.out.println("The request has failed");
		    }

		    public void completed(HttpResponse<JsonNode> response)  {
		         int code = response.getStatus();
		         JsonNode body = response.getBody();
		         InputStream rawBody = response.getRawBody();
		       
		         JSONObject n=body.getObject();
		         JSONObject x=n.getJSONObject("result");		        
		         String res=x.getString("sentiment");
		  
		         FileWriter pw;
				try {
					pw = new FileWriter("/home/nag/Desktop/final.csv",true);
					 pw.append(res);
			         pw.append("\n");
			         pw.flush();
			         pw.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		    
		        
		    }

		    public void cancelled() {
		        System.out.println("The request has been cancelled");
		    }
});
      
}
}