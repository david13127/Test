package com.mashibing.netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.ReferenceCountUtil;

/**
 * @Description: Client
 * @author : david
 * @date Date : 2019年06月02日 20:36
 * @version V1.0
 */
class Client {

	private Channel channel;

	void connect(String ip, int port) throws Exception {
		EventLoopGroup group = new NioEventLoopGroup(1);
		try {
			Bootstrap b = new Bootstrap();
			ChannelFuture f = b.group(group).channel(NioSocketChannel.class).handler(new ClientChannelInitializer())
					.connect(ip, port);
			f.addListener((ChannelFutureListener) channelFuture -> {
				if (!channelFuture.isSuccess()) {
					System.out.println("not connect!");
				} else {
					System.out.println("connected!");
					channel = f.channel();
				}
			});
			f.channel().closeFuture().sync();
		}
		finally {
			group.shutdownGracefully();
		}
	}


	void exit(){
		channel.disconnect();
	}

	void send(String content) {
		ChatFrame.INSTANCE.updateText("[我]：" + content);
		ByteBuf buf = Unpooled.copiedBuffer(content.getBytes());
		channel.writeAndFlush(buf);
	}

	class ClientChannelInitializer extends ChannelInitializer<SocketChannel> {

		@Override
		protected void initChannel(SocketChannel socketChannel) {
			socketChannel.pipeline().addLast(new ClientHandler());
		}
	}

	class ClientHandler extends ChannelInboundHandlerAdapter {

		@Override
		public void channelActive(ChannelHandlerContext ctx) {
			ByteBuf buf = Unpooled.copiedBuffer("Hello Everyone".getBytes());
			ctx.writeAndFlush(buf);
		}

		@Override
		public void channelRead(ChannelHandlerContext ctx, Object msg) {
			ByteBuf buf = null;
			try {
				buf = (ByteBuf) msg;
				byte[] bytes = new byte[buf.readableBytes()];
				buf.getBytes(buf.readerIndex(), bytes);
				ChatFrame.INSTANCE.updateText(new String(bytes));
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