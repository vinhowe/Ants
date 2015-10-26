package ants;

import java.awt.event.KeyEvent;
import java.util.ArrayList;

import processing.core.PApplet;
import processing.core.PFont;
import processing.core.PGraphics;
import processing.core.PVector;

public class Ants extends PApplet {

	ArrayList<Particle> particles;
	PFont font;
	
	PGraphics dots;
	
	private int iterationsPerDraw = 1;
	
	private int particleCount = 100;
	
	private float scaleIteration = -0.001f;
	
	private float scale = 1;
	
	private int drawingHeight;
	
	private int drawingWidth;
	
	boolean running = true;


	public void settings() {
		fullScreen(FX2D);
	}

	public void setup() {
		background(10);
		//particles = new ArrayList<Particle>();
		//drawingWidth = width * 3;
		//drawingHeight = width * 3;
		//generateParticles(particleCount);
		//font = createFont("Arial Light", 32);
		//textFont(font);
		//dots = createGraphics(drawingWidth, drawingHeight, FX2D);
		//dots.beginDraw();
		//dots.background(10);
		//dots.noStroke();
		//dots.endDraw();
		imageMode(CENTER);
		//thread("drawThread");
	}

	public void draw() {
		//pushMatrix();
		background(10);
		//translate(width/2,height/2); // use translate around scale
		//scale(scale = scale + scaleIteration);
		//scale(0.25f);
		//translate(-width/2,-height/2); // to scale from the center
		//fill(0, 2);
		//rect(-15, -15, width+15, height+15);
		set(0, 0, 255);
		//image(dots,0,0,drawingWidth,drawingHeight);
		//dots.endDraw();
		//filter(THRESHOLD);
		//popMatrix();
	}
	
	public void drawThread() {
		
		while(running) {
			dots.beginDraw();
			dots.scale(2);
			for(int i = 0; i < iterationsPerDraw; i++) {
				doParticleLogic();
				//text("word", 10, 30);
				for (int p = 0; p < particles.size(); p++) {
					PVector currentParticleLocation = particles.get(p).getLocation();
					dots.fill(particles.get(p).color.getRed());
					dots.set((int)currentParticleLocation.x, (int)currentParticleLocation.y, particles.get(p).color.getRGB());//rect(currentParticleLocation.x, currentParticleLocation.y, 1, 1);
					//System.out.println(currentParticleLocation.x + " " + currentParticleLocation.y);
				}
				
			}
			
		}
		
	}

	
	public void generateParticles(int count) {
		for (int i = 0; i < count; i++) {
			PVector location = new PVector();
			location.x = (int) random(drawingWidth);
			location.y = (int) random(drawingHeight);
			particles.add(new Particle(location));
		}
	}

	public void doParticleLogic() {
		for (int p = 0; p < particles.size(); p++) {
			if(particles.get(p).dead)
			{
				continue;
			}
			boolean claimed = true;
			int tries = 0;
			//while (claimed && tries < 5) {
				PVector prospectiveLocation = particles.get(p)
						.getLocation();
				prospectiveLocation.add(randomBoolean(0.5f) ? -1 : +1, randomBoolean(0.5f) ? -1 : +1, 0);
				
			/*	if(checkPositionClaimed(prospectiveLocation, particles.get(p)) == true) {
					//System.out.println(" " + particles.get(p).particleName + " " + particles.get(p).getLocation());
					tries++;
					//System.out.println(tries);
					continue;
				}
				
				claimed = false;*/
				particles.get(p).setLocation(prospectiveLocation);
			/*}
			if(tries >= 5) {
				particles.get(p).dead = true;
			}*/
		}
	}

	public boolean checkPositionClaimed(PVector location, Particle particle) {
		//for (int p2 = 0; p2 < particles.size(); p2++) {
			for (int l = 5; l < particle.locations.size(); l++) {
				if (location.dist(particle.locations.get(l)) < 3) {
					//System.out.println(l + " " + particle.locations.size());
					return true;
				}
			}
		//}
		return false;
	}
	
	public boolean randomBoolean(float chance) {
		return Math.random() < chance;
	}

	public static void main(String _args[]) {
		PApplet.main(new String[] { ants.Ants.class.getName() });
	}
	
	public void keyPressed() {
		switch(key) {
			case 'k':
				dots.beginDraw();
				dots.save("enigma.png");
				dots.endDraw();
				break;
		}
	}
}
