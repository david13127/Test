package com.mashibing.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.util.ReferenceCountUtil;
import io.netty.util.concurrent.GlobalEventExecutor;

import java.net.InetSocketAddress;

/**
 * @Description: TODO
 * @author : david
 * @date Date : 2019年06月02日 20:21
 * @version V1.0
 */
public class Server {
	static ChannelGroup clients = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

	public static void main(String[] args) throws Exception {
		EventLoopGroup bossGroup = new NioEventLoopGroup(1);
		EventLoopGroup workerGroup = new NioEventLoopGroup(2);
		try {
			ServerBootstrap b = new ServerBootstrap();
			ChannelFuture f = b.group(bossGroup, workerGroup).channel(NioServerSocketChannel.class)
					.childHandler(new ChannelInitializer<SocketChannel>() {
						@Override
						protected void initChannel(SocketChannel ch) {
							ChannelPipeline channelPipeline = ch.pipeline();
							channelPipeline.addLast(new ServerChildHandler());
						}
					}).bind(8888).sync();
			System.out.println("server started!");

			f.channel().closeFuture().sync();
		} finally {
			bossGroup.shutdownGracefully();
			workerGroup.shutdownGracefully();
		}
	}
}

class ServerChildHandler extends ChannelInboundHandlerAdapter {

	private void sendAllExceptSelf(ChannelHandlerContext ctx, String msg, boolean isNotice) {
		InetSocketAddress ipSocket = (InetSocketAddress) ctx.channel().remoteAddress();
		String clientIp = ipSocket.getAddress().getHostAddress();
		int port = ipSocket.getPort();

		Channel current = ctx.channel();
		for (Channel next : Server.clients) {
			if (!next.id().equals(current.id())) {
				ByteBuf byteBuf;
				if (!isNotice) {
					byteBuf = Unpooled.copiedBuffer((clientIp + "(" + port + "): " + msg).getBytes());
				} else {
					byteBuf = Unpooled.copiedBuffer(("[notice]: " + msg).getBytes());
				}
				next.writeAndFlush(byteBuf);
			}
		}
	}


	@Override
	public void channelActive(ChannelHandlerContext ctx) {
		Server.clients.add(ctx.channel());
	}

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) {
		ByteBuf buf = null;
		try {
			buf = (ByteBuf) msg;
			byte[] bytes = new byte[buf.readableBytes()];
			buf.getBytes(buf.readerIndex(), bytes);
			String message = new String(bytes);
			System.out.println("server recieved: " + new String(bytes));
			sendAllExceptSelf(ctx, message, false);
		}
		finally {
			if (buf != null) {
				ReferenceCountUtil.release(buf);
				System.out.println(buf.refCnt());
			}
		}
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
		Server.clients.remove(ctx.channel());
		ctx.close();
	}

	@Override
	public void channelRegistered(ChannelHandlerContext ctx) {
		InetSocketAddress ipSocket = (InetSocketAddress) ctx.channel().remoteAddress();
		String clientIp = ipSocket.getAddress().getHostAddress();
		int port = ipSocket.getPort();
		sendAllExceptSelf(ctx, clientIp + "(" + port + ") is online!", true);
	}

	@Override
	public void channelUnregistered(ChannelHandlerContext ctx) {
		InetSocketAddress ipSocket = (InetSocketAddress) ctx.channel().remoteAddress();
		String clientIp = ipSocket.getAddress().getHostAddress();
		int port = ipSocket.getPort();
		sendAllExceptSelf(ctx, "Bye Everyone!", false);
		sendAllExceptSelf(ctx, clientIp + "(" + port + ") is offline!", true);
		Server.clients.remove(ctx.channel());
	}
}