package main.java.com.netty;

/**
 * @Description: TODO
 * @author : david
 * @date Date : 2019年06月02日 23:17
 * @version V1.0
 */

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class ChatFrame extends Frame {
	private TextArea textArea = new TextArea();
	private TextField textField = new TextField();
	private Client client;

	public ChatFrame() throws Exception {
		client = new Client();
		setSize(800, 600);
		setResizable(false);
		setTitle("Tank War");
		setVisible(true);
		this.add(textArea, BorderLayout.CENTER);
		this.add(textField, BorderLayout.SOUTH);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				client.exit();
				System.exit(0);
			}
		});

		textField.addActionListener(e -> {
			try {
				send(textField.getText());
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			textField.setText("");
		});
		new Thread(() -> {
			StringBuffer hitory = new StringBuffer();
			int length = client.getHistorySize();
			while (true) {
				if (length < client.getHistorySize()) {
					hitory.append(client.getCurrent() + "\n");
					length = client.getHistorySize();
				}
				textArea.setText(hitory.toString());
			}
		}).start();

		client.hold();
	}

	private void send(String text) throws Exception {
		client.send(text);
		textArea.setText(textArea.getText() + "\n" + client.getCurrent());
	}

	public static void main(String[] args) throws Exception {
		new ChatFrame();
	}
}