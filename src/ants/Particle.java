package ants;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;

import processing.core.PVector;

public class Particle {
	ArrayList<PVector> locations;
	boolean dead = false;
	String particleName;
	int tries = 255;
	static final String AB = "\30123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz&!";
	static Random rnd = new Random();
	int life = 0;
	Color color;

	public Particle(PVector location) {
		locations = new ArrayList<PVector>();
		setLocation(location);
		particleName = generateName(12);
		int randomDesaturated = rnd.nextInt(255);
		color = new Color(randomDesaturated,randomDesaturated,randomDesaturated);
		life = rnd.nextInt(10000)+1000;
		//System.out.println(particleName + " " + getLocation().toString());
	}

	public Particle(int x, int y) {
		this(new PVector(x, y));
	}

	public void setLocation(PVector newLocation) {
		locations.clear();
		locations.add(newLocation);
	}

	public PVector getLocation() {
		return locations.get(locations.size() - 1);
	}

	String generateName(int len) {
		StringBuilder sb = new StringBuilder(len);
		for (int i = 0; i < len; i++)
			sb.append(AB.charAt(rnd.nextInt(AB.length())));
		return sb.toString();
	}
}
