package myutil;

import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class MyFrame extends Frame {
	// 加载窗口
	public void launchFrame() {
		setSize(Constant.WIDTH, Constant.HEIGHT);
		setLocation(100, 100);
		setVisible(true);
		new PaintThread().start(); // 启动重画线程

		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
	}

	// 重画窗口
	class PaintThread extends Thread {
		public void run() {
			while (true) {
				repaint();
				try {
					Thread.sleep(40);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

	// 消除闪烁
	private Image offScreenImage = null;

	public void update(Graphics g) {
		if (offScreenImage == null) {
			offScreenImage = this.createImage(Constant.WIDTH, Constant.HEIGHT);
		}
		Graphics gOff = offScreenImage.getGraphics();
		paint(gOff);
		g.drawImage(offScreenImage, 0, 0, null);
	}
}