package top.kthirty.netty;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

public class MyTaskQueueServerHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        // 获取到线程池eventLoop，添加线程，执行
        ctx.channel().eventLoop().execute(() -> {
            try{
                // 模拟长时间操作
                Thread.sleep(2000);
                System.out.println("多耗时业务处理结束");
            }catch (Exception e){
                e.printStackTrace();
            }
        });
    }
}
