package scenes;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public class Scene {
	
	public Graphics c;
	public Color BACKGROUNDCOLOR;
	public String changeSceneTo = null;
	public int WIDTH;
	public int HEIGHT;
	
	public void refresh(Graphics c) {
		c.setColor(BACKGROUNDCOLOR);
		c.fillRect(0, 0, this.WIDTH, this.HEIGHT);
	}
	
	public void render(Graphics c) {
		c.fillRect(0, 0, this.WIDTH, this.HEIGHT);
	}
	
	public void mouseClicked(MouseEvent e) {
		System.out.println("Mouse Clicked!\nX: " + e.getX() + "\nY: " + e.getY());
	}
	
	public void keyTyped(KeyEvent e) {
		System.out.println("Key Typed: " + e.getKeyChar());
	}
	
	public void keyPressed(KeyEvent e) {
		System.out.println("Key Pressed: " + e.getKeyCode());
	}
	
}
