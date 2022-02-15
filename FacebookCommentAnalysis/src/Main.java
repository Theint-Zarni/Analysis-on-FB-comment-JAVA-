

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import facebook4j.Comment;
import facebook4j.FacebookException;
import facebook4j.FacebookFactory;
import facebook4j.Paging;
import facebook4j.ResponseList;
import facebook4j.auth.AccessToken;

@SuppressWarnings("serial")
public class Main extends JFrame {
	private JPanel contentPane;
	static String[] postIdList = {};
	static List<String[]> hospitalList = new ArrayList<>();

	JTextArea textArea = new JTextArea();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
					Main frame = new Main();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	static String[] Lt = {};

	/**
	 * Create the frame.
	 */
	@SuppressWarnings("unchecked")
	public Main() {
		String languages[] = { "Hospital-1", "Hospital-2", "Hospital-3", "Hospital-4", "Hospital-5" };
		String hospital1[] = { "10646929759_10155793210484760", "10646929759_10156047479534760",
				"10646929759_10156047479534760", "10646929759_10156047479534760" };
		String hospital2[] = { "10646929759_10155793210484760", "10646929759_10156047479534760",
				"10646929759_10156047479534760", "10646929759_10156047479534760" };
		String hospital3[] = { "10646929759_10155793210484760", "10646929759_10156047479534760",
				"10646929759_10156047479534760", "10646929759_10156047479534760" };
		String hospital4[] = { "10646929759_10155793210484760", "10646929759_10156047479534760",
				"10646929759_10156047479534760", "10646929759_10156047479534760" };
		String hospital5[] = { "10646929759_10155793210484760", "10646929759_10156047479534760",
				"10646929759_10156047479534760", "10646929759_10156047479534760" };

		hospitalList.add(hospital1);
		hospitalList.add(hospital2);
		hospitalList.add(hospital3);
		hospitalList.add(hospital4);
		hospitalList.add(hospital5);

		postIdList = hospitalList.get(0);

		JPanel panel = new JPanel();

		@SuppressWarnings("rawtypes")
		JComboBox comboBox = new JComboBox(languages);
		comboBox.setBounds(185, 14, 193, 20);
		panel.add(comboBox);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 610, 423);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);

		JButton btnClear = new JButton("Clear");

		btnClear.setBounds(464, 323, 89, 23);
		panel.add(btnClear);

		JButton btnResult = new JButton("Extract");
		btnResult.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String[] array = suffleArray(readFile());

					for (String W : array)
						textArea.append(W + "\n");
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnResult.setBounds(42, 323, 89, 23);
		panel.add(btnResult);
		JButton btnTest = new JButton("Result");
		btnTest.setBounds(328, 323, 89, 23);
		panel.add(btnTest);

		Piechart chart = new Piechart();
		JPanel panel_piechart = new JPanel();
		panel_piechart.setBounds(160, 79, 218, 182);
		panel.add(panel_piechart);
		panel_piechart.setLayout(new BorderLayout(0, 0));
		panel_piechart.add(chart, BorderLayout.CENTER);
		chart.setLayout(null);
		panel_piechart.setVisible(false);

		List<Integer> scores = new ArrayList<Integer>();
		Random random = new Random();
		int maxDataPoints = 16;
		int maxScore = 20;
		for (int i = 0; i < maxDataPoints; i++) {
			scores.add(random.nextInt(maxScore));
		}

		TestChart testChart = new TestChart("SVN Result" ,
		         "Project Result Line Graph");
		JPanel panel_line_chart = new JPanel();
		panel_line_chart.setBounds(10, 45, 543, 276);
	
		JFreeChart lineChart = ChartFactory.createLineChart(
		         "SVN Result",
		         "Number Of Counting","Correct Percentage",
		         createDataset(),
		         PlotOrientation.VERTICAL,
		         true,true,false);
		         
		      ChartPanel chartPanel = new ChartPanel( lineChart );
		      chartPanel.setBounds(42, 59, 500, 229);

		panel.add(panel_line_chart);
		panel_line_chart.setLayout(new BorderLayout(0, 0));
		panel_line_chart.add(chartPanel, BorderLayout.CENTER);
		testChart.getContentPane().setLayout(null);
		panel_line_chart.setVisible(false);

		JPanel panel_2 = new JPanel();
		panel_2.setBounds(28, 45, 525, 255);
		panel.add(panel_2);
		panel_2.setLayout(new BorderLayout(0, 0));
		panel_2.add(textArea);
		JScrollPane scrollPane = new JScrollPane(textArea);
		panel_2.add(scrollPane, BorderLayout.CENTER);
		JButton btnSvm = new JButton("SVM");
		btnSvm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				panel_piechart.setVisible(true);
				panel_line_chart.setVisible(false);
				textArea.setText("");
			}
		});
		btnSvm.setBounds(185, 323, 89, 23);
		panel.add(btnSvm);
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				panel_piechart.setVisible(false);
				panel_line_chart.setVisible(false);
				textArea.setText("");
			}
		});
		btnTest.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				panel_line_chart.setVisible(true);
				panel_piechart.setVisible(false);
				textArea.setText("");
			}
		});
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

	@SuppressWarnings("unused")
	public void getCommentList() throws FacebookException, IOException {
		String accessToken = "EAAC0GoFFD2kBACo1YbYaVy1TQWqKLbv7LQ646IGgGfUFzZCLyQ3tsqHkF3zOjCtPJr28rhWB1RW8xgZAhkSKoT5s0ZC5XZCyXw8eJjtr5HZCYzZAFF5FPSyVPJbJKfCKxYIWGEWOxmw8aZAl2SPgVhqkrptDsnTRl9KK41XAKcPkKIpVODll4ygFqYeKeZCZCB5IZD";

		facebook4j.Facebook facebook = new FacebookFactory().getInstance();
		facebook.setOAuthAppId("2111622605751255", "8045ea7c18d51a314344d4e872058317");
		facebook.setOAuthPermissions("email,publish_stream");
		facebook.setOAuthAccessToken(new AccessToken(accessToken, null));
		StringBuilder comments = new StringBuilder();

		for (int i = 0; i < postIdList.length; i++) {

			ResponseList<Comment> commentList = facebook.getPostComments(postIdList[i]);
			FileInputStream fis = new FileInputStream(new File("D:\\Htet Aung\\ha\\dataList.xlsx"));
			@SuppressWarnings("resource")
			XSSFWorkbook workbook = new XSSFWorkbook(fis);
			XSSFSheet sheet = workbook.getSheetAt(0);
			XSSFRow row1 = sheet.createRow(0);
			XSSFCell r1c1 = row1.createCell(0);
			r1c1.setCellValue("No");
			XSSFCell r1c2 = row1.createCell(1);
			r1c2.setCellValue("Comments");

			Paging<Comment> paging = null;

			int index = 0;

			do {
				for (Comment c : commentList) {
					paging = commentList.getPaging();
					if (c.getMessage() != null) {
						index = index + 1;
						String statusMsg = "";
						if (WordFilter.filter(c.getMessage())) {
							statusMsg = "Bad";
							int badcount = index + 1;
						} else {
							statusMsg = "Good";
							int goodcount = index + 1;
						}
						XSSFRow dataRow = sheet.createRow(index);
						XSSFCell number = dataRow.createCell(0);
						number.setCellValue(index);
						XSSFCell comment = dataRow.createCell(1);
						comment.setCellValue(c.getMessage());
						XSSFCell status = dataRow.createCell(2);
						status.setCellValue(statusMsg);

						System.out.println(c.getMessage());
						comments.append(c.getMessage() + "\n");
					}
				}

			} while ((paging != null) && ((commentList = facebook.fetchNext(paging)) != null));
			textArea.setText(comments.toString());
		}
	}

	@SuppressWarnings("resource")
	static String[] readFile() throws IOException {
		String line;
		List<String> wordList = new ArrayList<String>();

		FileReader reader = new FileReader("D:\\Theint\\tz\\" + "commentList" + ".txt");
		try {
			BufferedReader bufferedReader = new BufferedReader(reader);
			while ((line = bufferedReader.readLine()) != null) {
				wordList.add(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				reader.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		String[] words = new String[wordList.size()];
		return wordList.toArray(words);
	}

	static String[] suffleArray(String[] arr) {
		Random rnd = ThreadLocalRandom.current();
		for (int i = arr.length - 1; i > 0; i--) {
			int index = rnd.nextInt(i + 1);
			String a = arr[index];
			arr[index] = arr[i];
			arr[i] = a;
		}
		return arr;
	}
}
