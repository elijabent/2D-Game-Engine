package scenes;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import utils.Button;
import utils.TextInput;

public class HomeScreen extends Scene{
	
	public TextInput textInput;
	public Button testButton;
	
	public HomeScreen(int width, int height) {
		
		this.WIDTH = width;
		this.HEIGHT = height;
		
		this.BACKGROUNDCOLOR = new Color(118, 247, 255);
		
		this.textInput = new TextInput(0, 0, 200, 50);
		this.testButton = new Button(200, 0, 200, 50, "Hello");
	}
	
	@Override
	public void render(Graphics c) {

		this.refresh(c);
		
		//graphics drawn here
		this.textInput.draw(c);
		this.testButton.draw(c);
		
	}
	
	@Override // handles all mouse clicks for this scene
	public void mouseClicked(MouseEvent e) {
		if (this.textInput.clicked(e)) {
			this.textInput.selected = true;
		}
	}
	
	@Override //handles all typed characters for this scene
	public void keyPressed(KeyEvent e) {
		if (this.textInput.selected) {
			this.textInput.keyPressed(e);
		}
	}
	
}