package project3;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;



public class tweetcount {
	public static void main(String[] args )
	{
		SparkSession spark = SparkSession
				  .builder()
				  .appName("Java Spark SQL basic example")
				  .master("local[2]")
				  .getOrCreate();
		Dataset<Row> df=spark.read().json("/home/nag/barcelona.json");
        Dataset<Row> r1=df.select("retweeted_status");
        
     Dataset<Row> r2=r1.select("retweeted_status.created_at");
      r2.registerTempTable("tweet");
    //  r2.select("created_at").show();
    Dataset<Row> r3=spark.sql("select (SUBSTR(created_at,5,4)) as month,count(SUBSTR(created_at,5,4)) from tweet where created_at is not null group by 1");
    r3.coalesce(1).write().format("com.databricks.spark.csv").save("/home/nag/Desktop/tweetcount");
	
	
	}

}
