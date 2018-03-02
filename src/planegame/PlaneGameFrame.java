package planegame;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Date;
import myutil.GameUtil;
import myutil.MyFrame;

public class PlaneGameFrame extends MyFrame {
	Image bg = GameUtil.getImage("images/bg.jpg"); // 背景
	Plane player = new Plane("images/player.png", 175, 550); // 飞机
	ArrayList bulletList = new ArrayList(); // 子弹
	Explode boom; // 爆炸
	Date startTime;
	Date endTime;

	public void paint(Graphics g) {
		g.drawImage(bg, 0, 0, null); // 背景
		player.draw(g); // 飞机
		for (int i = 0; i < bulletList.size(); i++) { // 子弹
			Bullet b = (Bullet) bulletList.get(i);
			b.draw(g);

			// 检测碰撞
			boolean bm = b.getRect().intersects(player.getRect());
			if (bm) {
				player.setLive(false);
			}
		}

		// 死亡处理
		if (!player.isLive()) {
			if (boom == null) {
				boom = new Explode(player.x, player.y);
				endTime = new Date();
			}
			boom.draw(g);
			// 打印结果
			printInfo(g, "GAME OVER", 80, 300, 50, Color.white);
			long period = (endTime.getTime() - startTime.getTime()) / 1000;
			printInfo(g, "时间:" + period + "秒", 100, 350, 30, Color.green);
			printInfo(g, "分数:" + period * 13, 50, 50, 10, Color.green);
		}
	}

	// 打印信息:信息/位置（x/y）/尺寸/颜色
	public void printInfo(Graphics g, String str, int x, int y, int size, Color color) {
		Color c = g.getColor();
		g.setColor(color);
		Font f = new Font("宋体", Font.BOLD, size);
		g.setFont(f);
		g.drawString(str, x, y);
		g.setColor(c);
	}

	public static void main(String[] args) {
		new PlaneGameFrame().launchFrame();
	}

	public void launchFrame() {
		super.launchFrame();
		addKeyListener(new KeyMonitor());
		// 生成子弹
		for (int i = 0; i < 20; i++) {
			Bullet b = new Bullet();
			bulletList.add(b);
		}
		// 开始计时
		startTime = new Date();
	}

	// 定义内部类
	class KeyMonitor extends KeyAdapter {

		public void keyPressed(KeyEvent e) {
			player.changeDirection(e);
			System.out.println("按下:" + e.getKeyCode());
		}

		public void keyReleased(KeyEvent e) {
			player.recoveryDirection(e);
			System.out.println("释放:" + e.getKeyCode());
		}
	}
}
