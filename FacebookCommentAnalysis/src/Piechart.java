

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Arc2D;

import javax.swing.JPanel;

public class Piechart extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1543481447927576952L;

	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;

		Arc2D.Float arc = new Arc2D.Float(Arc2D.PIE);
		arc.setFrame(50, 10, 150, 150);

		int value = (int) Math.floor(Math.random() * 100);
		int value2 = (int) Math.floor(Math.random() * 100);
		value2 += value;
		int value3 = 360 - value2;
		int valueOnePercentage = (100 * value) / 360;
		int valueThreePercentage = (100 * value3) / 360;
		int valueTwoPercentage =100 - (valueOnePercentage + valueThreePercentage);

		@SuppressWarnings("unused")
		int [] intArr = {valueOnePercentage, valueTwoPercentage, valueThreePercentage};

		// 0 - 80 degrees
		arc.setAngleStart(0);
		arc.setAngleExtent(value);
		g2.setColor(Color.gray);
		g2.draw(arc);
		g2.setColor(Color.red);
		g2.drawString("Bad "+valueOnePercentage + " %", 0, 20);
		g2.fill(arc);

		// 80 - 200 degrees
		arc.setAngleStart(value);
		arc.setAngleExtent(value2);
		g2.setColor(Color.gray);
		g2.draw(arc);
		g2.setColor(Color.blue);
		g2.drawString("Average"+valueTwoPercentage + " %", 0, 40);
		g2.fill(arc);	

		// 200 - 360 degrees
		arc.setAngleStart(value2);
		arc.setAngleExtent(value3);
		g2.setColor(Color.gray);
		g2.draw(arc);
		g2.setColor(Color.green);
		g2.drawString("Good"+valueThreePercentage + " %", 0, 60);
		g2.fill(arc);
		
	}
}