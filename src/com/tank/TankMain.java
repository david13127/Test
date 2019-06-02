package com.tank;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * @Description: TODO
 * @author : david
 * @date Date : 2019年06月02日 18:28
 * @version V1.0
 */
public class TankMain {
	public static void main(String[] args) throws InterruptedException {
		Frame frame = new TankFrame();

		while (true) {
			Thread.sleep(2000);
			frame.repaint();
		}
	}
}
