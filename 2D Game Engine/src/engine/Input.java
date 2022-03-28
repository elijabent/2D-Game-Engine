package engine;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;

import scenes.Scene;

public class Input implements KeyListener{
	
	public Scene currentScene;
	
	public Input(Game game) {
		game.addKeyListener(this);
		this.currentScene = game.scene;
	}

	public class Key{
		private boolean pressed = false;
		
		public boolean isPressed() {
			return pressed;
		}
		
		public void toggle(boolean isPressed) {
			pressed = isPressed;
		}
	}
	
	public List<Key> keys = new ArrayList<Key>();
	
	//KEYS YOU WANT TO LOG
	public Key up = new Key();
	public Key down = new Key();
	public Key left = new Key();
	public Key right = new Key();
	
	@Override
	public void keyTyped(KeyEvent e) {
		this.currentScene.keyTyped(e);
	}

	@Override
	public void keyPressed(KeyEvent e) {
		this.currentScene.keyPressed(e);
	}

	@Override
	public void keyReleased(KeyEvent e) {
		toggleKey(e.getKeyCode(), false);
	}
	
	public void toggleKey(int keyCode, boolean isPressed) {
		if (keyCode == KeyEvent.VK_W) {up.toggle(isPressed);}
		if (keyCode == KeyEvent.VK_S) {down.toggle(isPressed);}
		if (keyCode == KeyEvent.VK_A) {left.toggle(isPressed);}
		if (keyCode == KeyEvent.VK_D) {right.toggle(isPressed);}
	}
}