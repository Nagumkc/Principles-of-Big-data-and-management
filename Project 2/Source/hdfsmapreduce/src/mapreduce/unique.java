package mapreduce;
	import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.URI;
import java.util.*;

import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
	import org.apache.hadoop.conf.*;
    import org.apache.hadoop.io.*;
    import org.apache.hadoop.mapred.*;
    import org.apache.hadoop.util.*;
import org.json.JSONObject;
    
public class unique {
	static int u=0;
	static int d=0;
	public static class Map extends MapReduceBase implements Mapper<LongWritable, Text, Text, IntWritable> {
 private final static IntWritable one = new IntWritable(1);
private Text word = new Text();

	 public void map(LongWritable key, Text value, OutputCollector<Text, IntWritable> output, Reporter reporter) throws IOException {
String line = value.toString();
		 JSONObject	object=new JSONObject(line);
		 if(object.has("text")){
		 String str=object.getString("text");
		 StringTokenizer tokenizer = new StringTokenizer(str);
		 while (tokenizer.hasMoreTokens()) {
	  word.set(tokenizer.nextToken().toLowerCase().replaceAll("\\W",""));
	   output.collect(word, one);
		}
		  }
	 }
	}
	 public static class Reduce extends MapReduceBase implements Reducer<Text, IntWritable, Text, IntWritable> {
		 public void reduce(Text key, Iterator<IntWritable> values, OutputCollector<Text, IntWritable> output, Reporter reporter) throws IOException {
		    int sum = 0;
		  while (values.hasNext()) {
		   sum += values.next().get();
	 }
		  if(sum==1)
		  {
		 output.collect(key, new IntWritable(sum));
		FileWriter fw= new FileWriter("/home/nag/Desktop/Pb/unique.txt",true);
			BufferedWriter bw=new BufferedWriter(fw);
	         bw.write(key+"   "+sum +"\n");
	         bw.flush();
	         bw.close();
	         u++;
		 }
		  else
		  {
			  output.collect(key, new IntWritable(sum));
				FileWriter fw= new FileWriter("/home/nag/Desktop/Pb/duplicate.txt",true);
					BufferedWriter bw=new BufferedWriter(fw);
			         bw.write(key+"   "+sum +"\n");
			         bw.flush();
			         bw.close();
			         d++; 
		  }
		 }
	     
		  }
	 
	 public static void main(String[] args) throws Exception {
		URI url=new URI("hdfs://localhost:9000"); //-------> (url where hdfs located)
	     Configuration con = new Configuration();
FileSystem f=FileSystem.get(url,con);
JobConf conf = new JobConf(unique.class);
 conf.setJobName("wordcount");

conf.setOutputKeyClass(Text.class);
conf.setOutputValueClass(IntWritable.class);

 conf.setMapperClass(Map.class);
 conf.setCombinerClass(Reduce.class);
 conf.setReducerClass(Reduce.class);

 conf.setInputFormat(TextInputFormat.class);
 conf.setOutputFormat(TextOutputFormat.class);
	 FileInputFormat.setInputPaths(conf,f.makeQualified(new Path("/user/project/input/")));
     FileOutputFormat.setOutputPath(conf, f.makeQualified(new Path("/user/project/Output/")));

   JobClient.runJob(conf);
   System.out.println("\n unique words : duplicate words ratio is"+"   "+"1 :"+d/u);
 }
}
