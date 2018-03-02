package planegame;

import java.awt.Color;
import java.awt.Graphics;
import myutil.Constant;

public class Bullet extends GameObject {
	double degree;

	public Bullet() {
		x = Constant.WIDTH / 2;
		y = Constant.HEIGHT / 2;
		width = 6;
		height = 6;
		degree = Math.random() * Math.PI * 2;
	}

	public void draw(Graphics g) {
		Color c = g.getColor();
		g.setColor(Color.yellow);
		g.fillOval((int) x, (int) y, width, height);

		x += speed * Math.cos(degree);
		y += speed * Math.sin(degree);

		if (x < 0 || x > Constant.WIDTH - width) {
			degree = Math.PI - degree;
		}
		if (y < 0 || y > Constant.HEIGHT - height) {
			degree = -degree;
		}

		g.setColor(c);
	}
}