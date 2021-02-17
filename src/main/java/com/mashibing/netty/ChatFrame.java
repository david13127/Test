package com.mashibing.netty;

import io.netty.util.internal.StringUtil;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * @Description: ChatFrame
 * @author : david
 * @date Date : 2019年06月02日 23:17
 * @version V1.0
 */
public class ChatFrame extends Frame {

	static final ChatFrame INSTANCE = new ChatFrame();

	private TextArea textArea = new TextArea();
	private TextField textField = new TextField();
	private Client client = new Client();

	private ChatFrame() {

	}

	private void start() {
		setSize(800, 600);
		setResizable(false);
		setTitle("Chaaaaat");
		setVisible(true);
		this.add(textArea, BorderLayout.CENTER);
		this.add(textField, BorderLayout.SOUTH);
		textField.addActionListener(e -> {
			try {
				client.send(textField.getText());
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			textField.setText("");
		});

		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				client.exit();
				System.exit(0);
			}
		});

		connectToServer();
	}

	private void connectToServer() {
		try {
			client.connect("127.0.0.1", 8888);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	void updateText(String msg) {
		if (StringUtil.isNullOrEmpty(textArea.getText())) {
			textArea.setText(msg);
		} else {
			textArea.setText(textArea.getText() + System.getProperty("line.separator") + msg);
		}
	}

	public static void main(String[] args) {
		ChatFrame.INSTANCE.start();
	}
}