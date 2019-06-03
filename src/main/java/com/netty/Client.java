package com.netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.ReferenceCountUtil;

import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description: TODO
 * @author : david
 * @date Date : 2019年06月02日 20:36
 * @version V1.0
 */
public class Client {
	EventLoopGroup group;
	private ChannelFuture f;
	private List<String> history;
	private String sendMessage = "";

	public Client() throws Exception {
		group = new NioEventLoopGroup(1);
		history = new ArrayList<>();
		Bootstrap b = new Bootstrap();
		f = b.group(group).channel(NioSocketChannel.class).handler(new ClientChannelInitializer())
				.connect("localhost", 8888);
		f.addListener((ChannelFutureListener) channelFuture -> {
			if (!channelFuture.isSuccess()) {
				System.out.println("not connect!");
			} else {
				System.out.println("connected!");
			}
		});
	}

	public void hold() throws Exception {
		f.channel().closeFuture().sync();
		group.shutdownGracefully();
	}

	public void exit(){
		f.channel().pipeline().disconnect();
	}

	public void send(String content) throws Exception {
		history.add("[我]：" + content);
		sendMessage = content;
		f.channel().pipeline().fireChannelActive();
	}

	public int getHistorySize() {
		if (history != null && history.size() > 0) {
			return history.size();
		}
		return 0;
	}

	public String getCurrent() {
		if (history != null && history.size() > 0) {
			return history.get(history.size() - 1);
		}
		return "";
	}

	class ClientChannelInitializer extends ChannelInitializer<SocketChannel> {

		@Override
		protected void initChannel(SocketChannel socketChannel) {
			socketChannel.pipeline().addLast(new ClientHandler());
		}
	}

	class ClientHandler extends ChannelInboundHandlerAdapter {

		@Override
		public void channelActive(ChannelHandlerContext ctx) throws Exception {
			ByteBuf buf;
			if (history == null || history.size() == 0) {
				buf = Unpooled.copiedBuffer("Hello Everyone".getBytes());
			}
			else {
				buf = Unpooled.copiedBuffer(sendMessage.getBytes());
			}
			ctx.writeAndFlush(buf);
		}

		@Override
		public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
			ByteBuf buf = null;
			try {
				buf = (ByteBuf) msg;
				byte[] bytes = new byte[buf.readableBytes()];
				buf.getBytes(buf.readerIndex(), bytes);
				history.add(new String(bytes));
				System.out.println("client recieved: " + new String(bytes));
			} finally {
				if (buf != null) {
					ReferenceCountUtil.release(buf);
					System.out.println(buf.refCnt());
				}
			}
		}

	}
}