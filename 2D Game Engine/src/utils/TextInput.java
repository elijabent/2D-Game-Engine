package utils;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public class TextInput {
	
	//variables assigned in the constructor
	public int x;
	public int y;
	public int width;
	public int height;
	
	//variables with default values
	public int textSize = 40;
	public Color bgColor = Color.WHITE;
	public Color prebgColor = Color.LIGHT_GRAY;
	public Color textColor = Color.BLACK;
	public Color preTextColor = Color.GRAY;
	public int borderWidth = 2;
	public Color borderColor = Color.BLACK;
	public int borderRadius = 10;
	public String font = Font.SANS_SERIF;
	public int fontStyle = Font.BOLD;
	public String preFont = Font.SANS_SERIF;
	public int preFontStyle = Font.CENTER_BASELINE;
	public String value = "test2";
	public String placeHolder = "test";
	public boolean selected = false;
	public int cursorIndex = this.value.length();
	public Color cursorColor = Color.BLACK;
	//for cursor to blink
	public int frames = 0;

	public TextInput(int x, int y, int width, int height) {
		
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		
	}
	
	public void draw(Graphics c) {
		
		if (!selected){
			
			c.setColor(this.prebgColor);
			c.fillRoundRect(this.x, this.y, this.width, this.height, this.borderRadius, this.borderRadius);
			c.setColor(this.preTextColor);
			c.setFont(new Font(this.preFont, this.preFontStyle, this.textSize));
			FontMetrics metrics = c.getFontMetrics();
			c.drawString(this.placeHolder, this.x + (this.width / 2) - (metrics.stringWidth(this.placeHolder) / 2), (this.y + this.height/2) + (metrics.getAscent() - metrics.getDescent() - metrics.getLeading()) / 2);

		} else{

			c.setColor(this.bgColor);
			c.fillRoundRect(this.x, this.y, this.width, this.height, this.borderRadius, this.borderRadius);
			c.setColor(this.textColor);
			c.setFont(new Font(this.font, this.fontStyle, this.textSize));
			FontMetrics metrics = c.getFontMetrics();
			c.drawString(this.value, this.x + (this.width / 2) - (metrics.stringWidth(this.value) / 2), (this.y + this.height/2) + (metrics.getAscent() - metrics.getDescent() - metrics.getLeading()) / 2);
			c.setColor(this.bgColor);

			//places the cursor and makes it blink:
			if (this.frames >= 60){
				this.frames = 0;
			} else if (frames < 30){

				//draws the cursor
				c.setColor(this.cursorColor);
				c.fillRect(
					this.x + (this.width / 2) - (metrics.stringWidth(this.value) / 2) + metrics.stringWidth(this.value.substring(0, this.cursorIndex)),
					this.y + (this.height / 10),
					2,
					this.height - (this.height / 5)
				);

			}

			this.frames++;

		}

		c.setColor(this.borderColor);
		Graphics2D c2 = (Graphics2D) c;
		c2.setStroke(new BasicStroke(this.borderWidth));
		c.drawRoundRect(this.x, this.y, this.width, this.height, this.borderRadius, this.borderRadius);

	}
	
	public boolean clicked(MouseEvent e) {
		
		if (e.getY() > this.y && e.getY() < this.y + this.height && e.getX() > this.textSize && e.getX() < this.x + this.width) {
			return true;
		} else {
			return false;
		}
		
	}
	
	public void keyPressed(KeyEvent e) {
		//test if key pressed is a letter
		if (KeyEvent.getKeyText(e.getKeyCode()).length() == 1) {
			this.value = new StringBuilder(this.value).insert(this.cursorIndex, e.getKeyChar()).toString();
			this.cursorIndex++;
		}
		
		//handles back spaces
		else if (e.getKeyCode() == KeyEvent.VK_BACK_SPACE){
			if (this.value.length() == 0) {
				return;
			} else {
				this.value = new StringBuilder(this.value).deleteCharAt(this.cursorIndex - 1).toString();
				this.cursorIndex--;
			}
		}
		
		// handles spaces
		else if (e.getKeyCode() == KeyEvent.VK_SPACE) {
			this.value += " ";
			this.cursorIndex++;
		} 
		
		//handles left arrows
		else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			if (this.cursorIndex != 0) {
				this.cursorIndex--;
			}
		}
		
		//handles right arrows
		else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			if (this.cursorIndex != this.value.length()) {
				this.cursorIndex++;
			}
		}
	}
	
}
