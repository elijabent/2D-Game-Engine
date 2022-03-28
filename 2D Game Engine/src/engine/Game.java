package engine;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

import scenes.HomeScreen;
import scenes.Scene;

import javax.swing.JFrame;

public class Game extends Canvas implements Runnable{
	
	private static final long serialVersionUID = 1L;
	
	public static final int WIDTH = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
	public static final int HEIGHT = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight();
	public static final int SCALE = 1;
	public static final String NAME = "Game";
	
	public Scene scene = new HomeScreen(WIDTH, HEIGHT);
	
	private JFrame frame;
	
	public boolean running = false;
	public int tickCount = 0;
	
	private BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
	private int[] pixels = ((DataBufferInt)image.getRaster().getDataBuffer()).getData();
	
	//put this in any class to have it handle key presses or mouse clicks
	public Input input;
	public MouseInput mouseInput;
	
	public Game() {
		setMinimumSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
		setMaximumSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
		setPreferredSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
		
		frame = new JFrame(NAME);

		frame.setUndecorated(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new BorderLayout());
		frame.add(this, BorderLayout.CENTER);
		frame.pack();
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.setVisible(true);
	}
	
	public void run() {
		
		//put this in any class to handle key presses or mouse clicks
		input = new Input(this);
		mouseInput = new MouseInput(this);
		
		long lastTime = System.nanoTime();
		double nsPerTick = 1000000000D/60;
		
		int ticks = 0;
		int frames = 0;
		
		long lastTimer = System.currentTimeMillis();
		double delta = 0;
		
		while(running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / nsPerTick;
			lastTime = now;
			boolean shouldRender = false;
			
			while (delta >= 1) {
				ticks++;
				tick();
				delta -= 1;
				shouldRender = true;
			}
			
			try {
				Thread.sleep(2);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			if (shouldRender) {
				render();
				frames++;
			}
			
			if (System.currentTimeMillis() - lastTimer >= 1000) {
				lastTimer += 1000;
				System.out.println(frames + ", " + ticks);
				frames = 0;
				ticks = 0;
			}
		}
	}
	
	public static void main(String[] args) {
		new Game().start();
	}
	
	public void tick() {
		tickCount++;
		
		if (input.up.isPressed()) {System.out.println("Moving up");}
		if (input.down.isPressed()) {System.out.println("Moving down");}
		if (input.left.isPressed()) {System.out.println("Moving left");}
		if (input.right.isPressed()) {System.out.println("Moving right");}
	}
	
	public void render() {
		BufferStrategy bs = getBufferStrategy();
		if (bs == null) {
			createBufferStrategy(3);
			return;
		}
		Graphics c = bs.getDrawGraphics();

		//controls game graphcis
		scene.render(c);
		
		c.dispose();
		bs.show();
	}
	
	private synchronized void start() {
		running = true;
		new Thread(this).start();
	}
	
	private synchronized void stop() {
		running = false;
	}
}