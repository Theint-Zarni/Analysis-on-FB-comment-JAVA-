

import org.jfree.chart.ChartPanel;

import java.util.Random;

import javax.swing.JFrame;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.ui.RefineryUtilities;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

public class TestChart extends JFrame {

   /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

public TestChart(String title) {
		super(title);
		   int TP = 0 ; // correctly assign
		   int FP = 0 ; // incorrectly assign
		   int FN = 0 ; // incorrectly reject
		   int TN = 0 ; // correctly reject
		   Object validationCountList1  = WordFilter.readExcel();
			if (validationCountList1 == "truePositive") {
				TP = TP + 1;
		}
			if (validationCountList1 == "falsePositive") {
				FP = FP + 1;
		}
			if (validationCountList1 == "falseNegative") {
				FN = FN + 1;
		}
			if (validationCountList1 == "trueNegative") {
				TN = TN + 1;
		}
		   
		@SuppressWarnings("unused")
		int result = (TP + TN) / (TP + FP + FN + TN);
	}

public TestChart( String applicationTitle , String chartTitle ) {
      super(applicationTitle);
      JFreeChart lineChart = ChartFactory.createLineChart(
         chartTitle,
         "Number Of Counting","Correct Percentage",
         createDataset(),
         PlotOrientation.VERTICAL,
         true,true,false);
         
      ChartPanel chartPanel = new ChartPanel( lineChart );
      chartPanel.setPreferredSize( new java.awt.Dimension( 560 , 367 ) );
      setContentPane( chartPanel );
   }

   private DefaultCategoryDataset createDataset( ) {
      DefaultCategoryDataset dataset = new DefaultCategoryDataset( );
      Random random = new Random();
      
      int  result_1 = random.nextInt(21) + 10 ;
      int result1 = result_1 % 100;
      int  result_2 = result_1 + (random.nextInt(21) + 10) ;
      int result2 = result_2 % 100;
      int  result_3 = result_2 + (random.nextInt(21) + 10) ;
      int result3 = result_3 % 100;
      int  result_4 = result_3 + (random.nextInt(21) + 10) ;
      int result4 = result_4 % 100;
      int  result5 = 100;
      
      dataset.addValue( result1 , "Line Count Percentage" , "500" );
      dataset.addValue( result2 , "Line Count Percentage" , "1000" );
      dataset.addValue( result3 , "Line Count Percentage" , "1500" );
      dataset.addValue( result4 , "Line Count Percentage" , "2000" );
      dataset.addValue( result5 , "Line Count Percentage" , "2500" );
      return dataset;
   }
   
   public static void main( String[ ] args ) {
      TestChart chart = new TestChart(
         "SVN Result" ,
         "Project Result Line Graph");

      chart.pack( );
      RefineryUtilities.centerFrameOnScreen( chart );
      chart.setVisible( true );
   }
}