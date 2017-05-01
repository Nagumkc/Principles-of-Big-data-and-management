
import org.apache.spark.SparkContext;
import org.apache.spark.sql.Row;
import org.apache.spark.SparkConf;
import net.liftweb.json.DefaultFormats
import net.liftweb.json._
import org.apache.spark;
import org.apache.spark.sql.SQLContext;
import org.apache.spark.SparkConf;;
import sys.process._
import org.apache.spark.sql.functions.explode;



object helloworld {
  
  def main(args:Array[String])=
  {
    
    implicit val formats = net.liftweb.json.DefaultFormats;
    
    case class trend(url:Array[String],query:Array[String],volume:Array[Int],name:Array[String],content:Array[String]);
    case class tre(name:String,vol:Int)
    val conf = new SparkConf().setAppName("first").setMaster("local[2]");
 val sc=new SparkContext(conf);
 val sq=new SQLContext(sc);
val d=sc.textFile("/home/nag/trend.csv");
 

 val x=d.map( line => {
   val parts=line.split(",")
   (parts(5),parts(4))
  
 })
 


 val y=x.map{case (k,v)=>(k->v)}
 val z=y.reduceByKey(_+_)
 
z.write.format("com.databricks.spark.csv").option("header", "true").save("/home/nag/Desktop/output.csv")
 

}
 
}


  
  
