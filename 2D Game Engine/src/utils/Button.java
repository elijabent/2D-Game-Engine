package utils;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;

public class Button {
	
	//variables assigned in constructor
	public int x;
	public int y;
	public int width;
	public int height;
	public String text;
	
	//variables with default values
	public int textSize = 40;
	public Color buttonColor = Color.GRAY;
	public Color textColor = Color.BLACK;
	public String font = Font.SANS_SERIF;
	public int fontStyle = Font.BOLD;
	public int borderRadius = 10;
	public Color borderColor = Color.BLACK;
	public int borderWidth = 2;
	
	public Button(int x, int y, int width, int height, String text) {

		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.text = text;
		
	}
	
	public void draw(Graphics c) {
		
		c.setFont(new Font(font, fontStyle, textSize));
		c.setColor(this.buttonColor);
		FontMetrics metrics = c.getFontMetrics();
		c.fillRoundRect(this.x, this.y, this.width, this.height, this.borderRadius, this.borderRadius);
		c.setColor(this.borderColor);
		Graphics2D c2 = (Graphics2D) c;
		c2.setStroke(new BasicStroke(this.borderWidth));
		c.drawRoundRect(this.x, this.y, this.width, this.height, this.borderRadius, this.borderRadius);
		c.setColor(this.textColor);
		c.drawString(this.text, this.x + (this.width / 2) - (metrics.stringWidth(this.text) / 2), (this.y + this.height/2) + (metrics.getAscent() - metrics.getDescent() - metrics.getLeading()) / 2);
		
	}

	public boolean clicked(MouseEvent e) {
		
		if (e.getY() > this.y && e.getY() < this.y + this.height && e.getX() > this.textSize && e.getX() < this.x + this.width) {
			return true;
		} else {
			return false;
		}
		
	}
	
}
