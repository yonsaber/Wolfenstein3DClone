package com.base.engine;

public class MainComponent {
	
	//Constants
	public static final int WIDTH = 800;
	public static final int HEIGHT = 600;
	public static final String TITLE = "3D Game Engine";
	public static final double FRAME_CAP = 5000.0;
	
	private boolean isRunning;
	private Game game;
	
	public MainComponent() {
		System.out.println(RenderUtil.getOpenGLVersion());
		RenderUtil.initGraphics();
		isRunning = false;
		game = new Game();
	}
	
	//Start the game
	public void start() {
		if (isRunning)
			return;
		run();
	}

	//End the game
	public void stop(){
		if (!isRunning)
			return;
		isRunning = false;
	}
	
	//What to do whilst game is running
	private void run() {
		isRunning = true;
		
		int frames = 0;
		long frameCounter = 0;
		
		final double frameTime = 1.0 / FRAME_CAP;
		
		//Time that the previous frame began drawing
		long lastTime = Time.getTime();
		//How many times we still need to update
		double unprocessedTime = 0;

		while (isRunning) {
			boolean render = false;
			
			//Frame drawing timings
			long startTime = Time.getTime();
			long passedTime = startTime - lastTime;
			lastTime = startTime;
			
			//How much time has passed in double form
			unprocessedTime += passedTime / (double)Time.SECOND;
			frameCounter += passedTime;			
			
			while (unprocessedTime > frameTime) {
				render = true;
				
				unprocessedTime -= frameTime;
				
				if (Window.isCloseRequested())
					stop();
				
				Time.setDelta(frameTime);
				
				game.input();
				Input.update();
				
				game.update();

				if (frameCounter >= Time.SECOND) {
					System.out.println(frames);
					frames = 0;
					frameCounter = 0;
				}
			}
			
			if (render) { 
				render();
				frames++;
			} else {
				try {
					Thread.sleep(1);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
		
		cleanUp();
	}
	
	//Render the elements to the screen
	private void render() {
		RenderUtil.clearScreen();
		game.render();
		Window.render();
	}
	
	//Clean up
	private void cleanUp() {
		Window.dispose();
	}
	
	//main method
	public static void main(String[] args) {
		Window.createWindow(WIDTH, HEIGHT, TITLE);

		MainComponent game = new MainComponent();

		game.start();
	}
}
