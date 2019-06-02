package com.tank;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * @Description: TODO
 * @author : david
 * @date Date : 2019年06月02日 18:35
 * @version V1.0
 */
public class TankFrame extends Frame {
	int x = 200;
	int y = 200;

	boolean bL = false;
	boolean bR = false;
	boolean bU = false;
	boolean bD = false;

	public TankFrame() {
		setSize(800, 600);
		setResizable(false);
		setTitle("Tank War");
		setVisible(true);

		addKeyListener(new MyKeyListener());

		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
	}

	@Override
	public void paint(Graphics graphics) {
		if (bL) {
			x -= 10;
		}
		if (bR) {
			x += 10;
		}
		if (bU) {
			y -= 10;
		}
		if (bD) {
			y += 10;
		}
		graphics.fillRect(x, y, 50, 50);
	}

	class MyKeyListener extends KeyAdapter {

		@Override
		public void keyPressed(KeyEvent keyEvent) {
			int keyCode = keyEvent.getKeyCode();
			switch (keyCode) {
				case KeyEvent.VK_LEFT:
					bL = true;
					break;
				case KeyEvent.VK_UP:
					bU = true;
					break;
				case KeyEvent.VK_RIGHT:
					bR = true;
					break;
				case KeyEvent.VK_DOWN:
					bD = true;
					break;
				default:
					break;
			}
			repaint();
		}

		@Override
		public void keyReleased(KeyEvent keyEvent) {
			int keyCode = keyEvent.getKeyCode();
			switch (keyCode) {
				case KeyEvent.VK_LEFT:
					bL = false;
					break;
				case KeyEvent.VK_UP:
					bU = false;
					break;
				case KeyEvent.VK_RIGHT:
					bR = false;
					break;
				case KeyEvent.VK_DOWN:
					bD = false;
					break;
				default:
					break;
			}
			repaint();
		}
	}
}
