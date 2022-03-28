package engine;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import scenes.Scene;

public class MouseInput extends MouseAdapter{
	
	public Scene currentScene;
	
	public MouseInput(Game game) {
		game.addMouseListener(this);
		this.currentScene = game.scene;
	}
	
	//you can kinda do whatever you want with this
	
	@Override
	public void mouseClicked(MouseEvent e) {
		this.currentScene.mouseClicked(e);
	}
}
