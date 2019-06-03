package main.java.com.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.*;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.util.concurrent.GlobalEventExecutor;

import java.util.Iterator;

/**
 * @Description: TODO
 * @author : david
 * @date Date : 2019年06月02日 20:21
 * @version V1.0
 */
public class Server {
	public static ChannelGroup clients = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

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

	private void sendAllExceptSelf(ChannelHandlerContext ctx, Object msg) {
		Channel current = ctx.channel();
		Iterator<Channel> iterator = Server.clients.iterator();
		while (iterator.hasNext()) {
			Channel next = iterator.next();
			if (!next.id().equals(current.id())) {
				next.writeAndFlush(msg);
			}
		}
	}
	@Override
	public void channelActive(ChannelHandlerContext ctx) {
		Server.clients.add(ctx.channel());
	}

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) {
		ByteBuf buf = (ByteBuf) msg;
		byte[] bytes = new byte[buf.readableBytes()];
		buf.getBytes(buf.readerIndex(), bytes);
		System.out.println("server recieved: " + new String(bytes));
		sendAllExceptSelf(ctx, msg);
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		cause.printStackTrace();
		ctx.close();
	}

	@Override
	public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
		sendAllExceptSelf(ctx, "welcome2");
	}

	@Override
	public void channelInactive(ChannelHandlerContext ctx) throws Exception {
		sendAllExceptSelf(ctx, "bye1");
	}

	@Override
	public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
		sendAllExceptSelf(ctx, "bye2");

	}
}