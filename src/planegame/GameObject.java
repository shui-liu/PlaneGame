package planegame;

import java.awt.Image;
import java.awt.Rectangle;

public class GameObject {
	Image img;
	double x, y;
	int width, height;
	int speed = 5;

	public Rectangle getRect() {
		return new Rectangle((int) x, (int) y, width, height);
	}

	public GameObject() {
	}

	public GameObject(Image img, double x, double y, int width, int height, int speed) {
		super();
		this.img = img;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.speed = speed;
	}
}
