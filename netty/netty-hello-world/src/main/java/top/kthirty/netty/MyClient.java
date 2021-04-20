package top.kthirty.netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * Netty客户端
 */
public class MyClient {
    public static void main(String[] args) throws InterruptedException {
        // 创建所需线程组
        NioEventLoopGroup eventExecutors = new NioEventLoopGroup();
        try{
            // 创建bootstrap对象
            Bootstrap bootstrap = new Bootstrap();
            // 设置线程组
            bootstrap.group(eventExecutors)
                    // 客户端消息实现类
                    .channel(NioSocketChannel.class)
                    // 构建消息处理器
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) {
                            // 设置自定义消息处理器
                            socketChannel.pipeline().addLast(new MyClientHandler());
                        }
                    });
            System.out.println("=====客户端准备就绪=====");
            // 链接服务端
            ChannelFuture channelFuture = bootstrap.connect("127.0.0.1",5555).sync();
            // 监听通道关闭
            channelFuture.channel().closeFuture().sync();
        }finally {
            // 关闭线程组
            eventExecutors.shutdownGracefully();
        }
    }
}
