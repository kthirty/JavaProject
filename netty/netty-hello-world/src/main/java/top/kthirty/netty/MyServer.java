package top.kthirty.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * 服务端启动类
 */
public class MyServer {
    public static void main(String[] args) throws InterruptedException {
        // 创建服务端所需的两个线程组
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            // 创建服务端启动对象并设置所需参数
            ServerBootstrap bootstrap = new ServerBootstrap();
            // 设置两个线程组，bossGroup和workerGroup
            bootstrap.group(bossGroup, workerGroup)
                    // 设置服务端通道实现类型
                    .channel(NioServerSocketChannel.class)
                    // 设置线程队列链接数
                    .option(ChannelOption.SO_BACKLOG,128)
                    // 这是保持活动链接状态
                    .childOption(ChannelOption.SO_KEEPALIVE,true)
                    // 使用匿名内部类初始化通道对象
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) {
                            // 设置信息处理器
                            socketChannel.pipeline().addLast(new MyServerHandler());
                        }
                    });
            System.out.println("----Netty服务端准备就绪---");
            // 绑定端口号并启动
            ChannelFuture channelFuture = bootstrap.bind(5555).sync();
            // 对关闭通道进行监听
            channelFuture.channel().closeFuture().sync();
        }finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }
}
