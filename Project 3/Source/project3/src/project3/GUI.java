package project3;




import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStream;
import java.util.Map;
import java.util.concurrent.Future;

import org.json.JSONObject;

import com.google.gson.JsonObject;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.async.Callback;
import com.mashape.unirest.http.exceptions.UnirestException;

import org.jfree.chart.*;
import org.jfree.data.category.*;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.xy.*;
import org.jfree.data.*;
import org.jfree.chart.renderer.category.*;
import org.jfree.chart.plot.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;

import javax.swing.BoxLayout;
import javax.swing.JApplet;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import java.awt.event.*;

public class hi extends JApplet implements ActionListener{
	 JButton button;
	 JButton button1;
	 JButton button2;
	  public void init(){
	        this.setSize(400,400);
	        this.add(getCustPanel());
	        this.setVisible(true);
	    }
	  private JPanel getCustPanel() {
	        JPanel panel = new JPanel ();
	        panel.setLayout ((LayoutManager) new BoxLayout(panel, BoxLayout.Y_AXIS));
	         button= new JButton ("Top Trends");
	        button.setPreferredSize (new Dimension(100,20));
	        button.setAlignmentX (Component.LEFT_ALIGNMENT);
	        button.addActionListener(this);
	        panel.add (button);
	        button1= new JButton ("Sentiment analysis for The Fate of The Furious Movie");
	        button1.setPreferredSize (new Dimension(100,20));
	        button1.setAlignmentX (Component.LEFT_ALIGNMENT);
	        button1.addActionListener(this);
	        panel.add (button1);
	        button2= new JButton ("No of tweets tweeted for Barcelona Football");
	        button2.setPreferredSize (new Dimension(100,20));
	        button2.setAlignmentX (Component.LEFT_ALIGNMENT);
	        button2.addActionListener(this);
	        panel.add (button2);
	        
	        return panel;
	    }
	  
	    public void actionPerformed(ActionEvent e)
	    {
	    	if(e.getSource()==button){
	    		 trend();
	    	}
	        if(e.getSource()==button1)
	        {
	        	score();
	        }
	        if(e.getSource()==button2)
	        {
	        	count();
	        }
	        }
	    
	    public void trend()
	    {
	    	DefaultCategoryDataset dataset = new DefaultCategoryDataset();
    		try{
    		@SuppressWarnings("resource")
			BufferedReader	 br=new BufferedReader(new FileReader("/home/nag/Desktop/output.csv/hi.csv"));
    		String line=br.readLine();
    		while(line!=null)
    		{
    			String[] result=line.split(",");
    			 dataset.setValue(Integer.parseInt(result[1]), "Top Trends", result[0]);
    			line=br.readLine();
    	   }
    		}catch(Exception e1)
    		{
    			System.out.println(e1.getMessage());
    		} 
        JFreeChart chart = ChartFactory.createBarChart ("Top trends","Trends Name", "Tweet_vloume", dataset, PlotOrientation.VERTICAL, true,true, false); 
        ChartFrame frame1=new ChartFrame("Bar Chart",chart);
        frame1.setVisible(true);
        frame1.setSize(400,350);
	    }
	    
	    public void score()
	    {
	    	 DefaultPieDataset dataset = new DefaultPieDataset( );
	    	 try{
	     		@SuppressWarnings("resource")
	 			BufferedReader	 br=new BufferedReader(new FileReader("/home/nag/Desktop/final/sentimentres.csv"));
	     		String line=br.readLine();
	     		while(line!=null)
	     		{
	     			String[] result=line.split(",");
	     			 dataset.setValue(result[0],Integer.parseInt(result[1]));
	     			line=br.readLine();
	     	   }
	     		}catch(Exception e1)
	     		{
	     			System.out.println(e1.getMessage());
	     		} 
	    	 
	    	 JFreeChart chart = ChartFactory.createPieChart("Review on The Fate of the Furious Movie ", dataset, true,true, false); 
	         ChartFrame frame1=new ChartFrame("Pie Chart",chart);
	         frame1.setVisible(true);
	         frame1.setSize(400,350);
	    }
	    
	    public void count()
	    {
	    	DefaultCategoryDataset dataset = new DefaultCategoryDataset();
    		try{
    		@SuppressWarnings("resource")
			BufferedReader	 br=new BufferedReader(new FileReader("/home/nag/Desktop/tweetcount/count.csv"));
    		String line=br.readLine();
    		while(line!=null)
    		{
    			String[] result=line.split(",");
    			 dataset.setValue(Integer.parseInt(result[1]), "Tweets count", result[0]);
    			line=br.readLine();
    	   }
    		}catch(Exception e1)
    		{
    			System.out.println(e1.getMessage());
    		} 
    		JFreeChart lineChart = ChartFactory.createLineChart("Tweets tweeted for Barcelona Football",
    		         "Months","Number of Tweets",
    		         dataset,
    		         PlotOrientation.VERTICAL,
    		         true,true,false);
    		ChartFrame frame1=new ChartFrame("Pie Chart",lineChart);
	         frame1.setVisible(true);
	         frame1.setSize(400,350);
	    }
	}

