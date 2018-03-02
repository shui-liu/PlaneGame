package planegame;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import myutil.GameUtil;

public class Plane extends GameObject {
	private boolean left, up, right, down;
	private boolean flag = true;

	public boolean isLive() {
		return flag;
	}

	public void setLive(boolean flag) {
		this.flag = flag;
	}

	public Plane() {
	}

	public Plane(String imgpath, double x, double y) {
		this.img = GameUtil.getImage("images/player.png");
		this.x = x;
		this.y = y;
		this.width = img.getWidth(null);
		this.height = img.getHeight(null);
	}

	public void draw(Graphics g) {
		if (flag) {
			g.drawImage(img, (int) x, (int) y, null);
			move();
		}
	}

	public void changeDirection(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_LEFT:
			left = true;
			break;
		case KeyEvent.VK_UP:
			up = true;
			break;
		case KeyEvent.VK_RIGHT:
			right = true;
			break;
		case KeyEvent.VK_DOWN:
			down = true;
			break;
		}
	}

	public void recoveryDirection(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_LEFT:
			left = false;
			break;
		case KeyEvent.VK_UP:
			up = false;
			break;
		case KeyEvent.VK_RIGHT:
			right = false;
			break;
		case KeyEvent.VK_DOWN:
			down = false;
			break;
		}
	}

	public void move() {
		if (left) {
			x -= speed;
		}
		if (up) {
			y -= speed;
		}
		if (right) {
			x += speed;
		}
		if (down) {
			y += speed;
		}
	}
}